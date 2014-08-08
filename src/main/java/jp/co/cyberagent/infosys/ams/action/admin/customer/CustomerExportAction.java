package jp.co.cyberagent.infosys.ams.action.admin.customer;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Customer;
import jp.co.cyberagent.infosys.ams.service.CustomerService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 顧客一覧CSVエクスポートアクションクラス.
 * @author ootaka
 */
@Results({
    @Result(name = "download", type = "stream"
        , params = {
            "inputName", "inputStream",
            "contentType", "application/octet-stream; charset=UTF-8",
            "contentLength", "${ contentLength }",
            "contentDisposition", "attachment; filename = ${fileName}"
        })
})
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class CustomerExportAction extends ActionSupport {

    /** EXPORTファイル名. */
    private String fileName;
    /** EXPORTファイルContent Length. */
    private long contentLength;
    /** EXPORTファイルInputStream. */
    private InputStream inputStream;

    /** 顧客管理サービス. */
    @Autowired
    private CustomerService service;

    /**
     * エクスポート処理.
     * @return 成功:success
     * @throws AMSException 顧客情報の出力に失敗した場合
     */
    @Action("/admin/customer/customer-export")
    public String execute() throws AMSException {
        try {
            List<Customer> customers = service.getAllCustomers();
            Map<Long, InputStream> csv = service.createCsv(customers);
            for (Long key : csv.keySet()) {
                this.inputStream = csv.get(key);
                this.contentLength = key;
            }
            this.fileName = URLEncoder.encode("顧客一覧.csv", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new AMSException(e.getMessage(), e);
        }
        return "download";
    }

    /**
     * EXPORTファイル名を取得する.
     * @return fileName エクスポートファイル名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * EXPORTファイルContentLengthを取得する.
     * @return contentLength EXPORTファイルContentLength
     */
    public long getContentLength() {
        return contentLength;
    }

    /**
     * EXPORTファイルInputStreamを取得する.
     * @return inputStream EXPORTファイルInputStream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

}
