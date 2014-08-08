package jp.co.cyberagent.infosys.ams.action.admin.customer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Customer;
import jp.co.cyberagent.infosys.ams.service.CustomerService;
import jp.co.cyberagent.infosys.ams.service.ItemLoadOperator;
import jp.co.cyberagent.infosys.ams.utils.LoadError;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 顧客一覧CSVインポートアクションクラス.
 * @author ootaka
 */
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class CustomerImportAction extends ActionSupport {

    /** 顧客CSV. */
    private File csv;
    /** インポートエラー 情報. */
    private List<LoadError<Integer, String>> loadErrors;

    /** 顧客管理サービス. */
    @Autowired
    private CustomerService service;

   @Override
    public void validate() {
       if (csv == null) {
           addActionError(getText("errors.required.customer.import"));
       }
    }

    /**
     * インポート処理.
     * @return 成功:null(HTTPステータス:200), 失敗:input
     * @throws AMSException 顧客情報の追加に失敗した場合
     */
    @Action("/admin/customer/customer-import")
    public String execute() throws AMSException {
        try {
            loadErrors = service.loadCsv(new FileInputStream(csv), new ItemLoadOperator<Customer>() {
                @Override
                public void execute(final Customer item) throws AMSException {
                    service.addCustomer(item);
                }
            });
        } catch (FileNotFoundException e) {
            throw new AMSException(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 顧客CSVを設定する.
     * @param csv 顧客CSV
     */
    public void setCsv(final File csv) {
        this.csv = csv;
    }

    /**
     * インポートエラー 情報を取得する.
     * @return loadErrors インポートエラー 情報
     */
    public List<LoadError<Integer, String>> getLoadErrors() {
        return loadErrors;
    }

}
