package jp.co.cyberagent.infosys.ams.action.admin.customer;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Customer;
import jp.co.cyberagent.infosys.ams.service.CustomerService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * CAONID更新用アクションクラス.
 * @author ootaka
 */
@Validations(
        requiredStrings = {
                @RequiredStringValidator(
                        fieldName = "customer.caonId",
                        message = "${getText('errors.required.customer.caonid')}")
    }
)
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class CustomerUpdateAction extends ActionSupport {

    /** 顧客モデル. */
    private Customer customer;

    /** 顧客管理サービス. */
    @Autowired
    private CustomerService service;

    /**
     * バリデーション.
     * <p>登録済CAONIDの重複チェックをする.
     */
    @Override
    public void validate() {
        try {

            // 登録済CAONIDの重複チェックをする.
            if (customer != null) {
                Customer c = service.getCustomer(customer.getCaonId());
                if (c != null) {
                    addActionError(getText("errors.validation.customer.duplicate.caonid"));
                    return;
                }
            }

        } catch (Exception e) {
            addActionError(getText("errors.common.system"));
        }
    }

    /**
     * 更新処理.
     * @return 成功:null(HTTPステータス:200), 失敗:input
     * @throws AMSException 顧客情報の追加に失敗した場合
     */
    @Action("/admin/customer/customer-update")
    public String execute() throws AMSException {
        service.updateCaonId(customer.getId(), customer.getCaonId());
        return null;
    }

    /**
     * 顧客モデルを取得する.
     * @return 顧客
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * 顧客モデルを設定する.
     * @param customer 顧客
     */
    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

}
