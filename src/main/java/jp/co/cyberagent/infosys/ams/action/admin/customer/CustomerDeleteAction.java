package jp.co.cyberagent.infosys.ams.action.admin.customer;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.service.CustomerService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 顧客削除用アクションクラス.
 * @author ootaka
 */
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class CustomerDeleteAction extends ActionSupport {

    /** 顧客モデルID. */
    private Long id;

    /** 顧客管理サービス. */
    @Autowired
    private CustomerService service;

    /**
     * 削除処理.
     * @return 成功:null(HTTPステータス:200)
     * @throws AMSException 顧客情報の削除に失敗した場合
     */
    @Action("/admin/customer/customer-delete")
    public String execute() throws AMSException {
        service.removeCustomer(id);
        return null;
    }

    /**
     * 顧客モデルIDを取得する.
     * @return id 顧客モデルID
     */
    public Long getId() {
        return id;
    }

    /**
     * 顧客モデルIDを設定する.
     * @param id 顧客モデルID
     */
    public void setId(final Long id) {
        this.id = id;
    }

}
