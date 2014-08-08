package jp.co.cyberagent.infosys.ams.action.admin.customer;

import java.util.LinkedList;
import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Customer;
import jp.co.cyberagent.infosys.ams.model.Employee;
import jp.co.cyberagent.infosys.ams.service.AccessControlService;
import jp.co.cyberagent.infosys.ams.service.BelongsService;
import jp.co.cyberagent.infosys.ams.service.CustomerService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 顧客社員用アクセス権設定アクションクラス.
 * @author ootaka
 */
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class CustomerEmployeeAclAction extends ActionSupport {

    /** 顧客モデルID. */
    private long customerId;
    /** 顧客モデル. */
    private Customer customer;
    /** 全社員モデル. */
    private List<Employee> allEmployees;
    /** 選択社員モデルID. */
    private List<Long> selectEmployeesId;

    /** 顧客管理サービス. */
    @Autowired
    private CustomerService customerService;
    /** 所属管理サービス. */
    @Autowired
    private BelongsService belongsService;
    /** アクセス権コントロールサービス. */
    @Autowired
    private AccessControlService aclService;

    /**
     * アクセス権設定一覧表示.
     * @return 成功:input
     * @throws AMSException 一覧の取得に失敗した場合
     */
    @Action("/admin/customer/customer-employee-acl")
    public String execute() throws AMSException {
        allEmployees = belongsService.getAllEmployees();
        customer = customerService.getCustomer(customerId);

        return INPUT;
    }

    /**
     * アクセス権設定処理.
     * @return 成功:null(HTTPステータス:200)
     * @throws AMSException 顧客情報の追加に失敗した場合
     */
    @Action("/admin/customer/customer-employee-acl-update")
    public String update() throws AMSException {
        List<Long> employeeIds = new LinkedList<Long>();
        //NULLを省く
        for (Long v : selectEmployeesId) {
            if (v != null) {
                employeeIds.add(v);
            }
        }
        aclService.setAccessControlByCustomer(customerId, employeeIds);
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

    /**
     * 顧客モデルIDを取得する.
     * @return customerId 顧客モデルID
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 顧客モデルを設定する.
     * @param customerId 顧客モデルID
     */
    public void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 全社員モデルを取得する.
     * @return allEmployees 全社員モデル
     */
    public List<Employee> getAllEmployees() {
        return allEmployees;
    }

    /**
     * 全社員モデルを設定する.
     * @param allEmployees 全社員モデル
     */
    public void setAllEmployees(final List<Employee> allEmployees) {
        this.allEmployees = allEmployees;
    }

    /**
     * 選択社員モデルIDを取得する.
     * @return selectEmployeesId 選択社員モデルID
     */
    public List<Long> getSelectEmployeesId() {
        return selectEmployeesId;
    }

    /**
     * 選択社員モデルIDを設定する.
     * @param selectEmployeesId 選択社員モデルID
     */
    public void setSelectEmployeesId(final List<Long> selectEmployeesId) {
        this.selectEmployeesId = selectEmployeesId;
    }

}
