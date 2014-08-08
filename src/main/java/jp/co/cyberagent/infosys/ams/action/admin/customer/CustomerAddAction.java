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
 * 顧客追加用アクションクラス.
 * @author ootaka
 */
@Validations(
        requiredStrings = {
                @RequiredStringValidator(
                        fieldName = "customer.name",
                        message = "${getText('errors.required.customer.name')}")
    }
)
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class CustomerAddAction extends ActionSupport {

    /** 顧客モデル. */
    private Customer customer;

    /** 顧客管理サービス. */
    @Autowired
    private CustomerService service;

    /**
     * バリデーション.
     * <p>1)CAONIDが入力されている場合、登録済CAONIDの企業名と入力された企業名が一致しているかチェックする.
     * <p>2)企業名あり/事業部名なし/サービスあり、はエラーにする.
     * <p>3)入力値が以下のパターンの重複登録をチェックする.
     * <p>  ・企業名あり/事業部名なし/サービスなし.
     * <p>  ・企業名あり/事業部名あり/サービスなし.
     * <p>  ・企業名あり/事業部名あり/サービスあり.
     */
    @Override
    public void validate() {
        try {

            if (customer != null) {

                // 1)CAONIDが入力されている場合、登録済CAONIDの企業名と入力された企業名が一致しているかチェックする.
                if (customer.getCaonId() != null) {
                    Customer c = service.getCustomer(customer.getCaonId());
                    if (c != null && !c.getName().equals(customer.getName())) {
                        addActionError(getText("errors.validation.customer.diff.caonid.name"));
                        return;
                    }
                }

                // 2)企業名あり/事業部名なし/サービスあり、はエラーにする.
                if (customer.getName() != null
                        && customer.getDevision() == null
                        && customer.getService() != null) {
                    addActionError(getText("errors.validation.customer.nodivision"));
                    return;
                }

                // 3)入力値が以下のパターンの重複登録をチェックする.
                //  ・企業名あり/事業部名なし/サービスなし.
                //  ・企業名あり/事業部名あり/サービスなし.
                //  ・企業名あり/事業部名あり/サービスあり.
                Customer c = service.getCustomer(customer.getName(), customer.getDevision(), customer.getService());
                if (c != null) {
                    addActionError(getText("errors.validation.customer.duplicate"));
                    return;
                }
            }

        } catch (AMSException e) {
            addActionError(getText("errors.common.system"));
        }
    }

    /**
     * 追加処理.
     * @return 成功:null(HTTPステータス:200), 失敗:input
     * @throws AMSException 顧客情報の追加に失敗した場合
     */
    @Action("/admin/customer/customer-add")
    public String execute() throws AMSException {
        service.addCustomer(customer);
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
