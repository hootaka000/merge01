package jp.co.cyberagent.infosys.ams.action.admin.assign;

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

import com.opensymphony.xwork2.ActionSupport;

/**
 * 社員のアクセス権設定を行うアクションクラス.
 * @author shimizu.kenji
 *
 */
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class EmployeeAssignSettingAction extends ActionSupport {

    /** Serial Version UID. */
    private static final long serialVersionUID = -4510143950302396174L;

    /** アクセス権を設定する社員ID. */
    private List<Long> employeeIds;
    /** アクセス権を設定する社員情報. */
    private Employee employee;
    /** 表示する社員にアクセス権のない全顧客情報. */
    private List<Customer> customers;
    /** アクセス権を設定する顧客ID一覧. */
    private List<Long> customerIds;
    /** Belongs Service. */
    private transient BelongsService belongsService;
    /** Customer Service. */
    private transient CustomerService customerService;
    /** Access Control Service. */
    private transient AccessControlService accessControlService;

    /**
     * アクセス権設定ダイアログを表示する.
     * @return アクセス権設定ダイアログ
     * @throws AMSException ダイアログの表示に失敗した場合
     */
    @Override
    @Action("/admin/assign/employee-setting-dialog")
    public String execute() throws AMSException {
        customers = customerService.getAllCustomers();
        if (employeeIds == null) {
            employeeIds = new LinkedList<>();
        } else if (employeeIds.size() == 1) {
            long employeeId = employeeIds.get(0);
            employee = belongsService.getEmployee(employeeId);
            for (Customer customer : new LinkedList<>(customers)) {
                if (exists(employee.getCustomers(), customer)) {
                    customers.remove(customer);
                }
            }
        }
        return SUCCESS;
    }

    /**
     * アクセス権設定を更新する.
     * @return アクセス権設定ページヘのリダイレクト
     * @throws AMSException アクセス権の設定に失敗した場合
     */
    @Action("/admin/assign/employee-setting-update")
    public String update() throws AMSException {
        if (customerIds == null) {
            customerIds = new LinkedList<>();
        }
        if (employeeIds == null) {
            employeeIds = new LinkedList<>();
        }
        if (employeeIds.size() == 1) {
            accessControlService.setAccessControl(employeeIds.get(0), customerIds);
        } else {
            accessControlService.setAccessControl(employeeIds, customerIds);
        }
        return "redirect_assign_setting";
    }
    /**
     * 顧客情報一覧に指定の顧客情報が含まれているか確認する.
     * @param customers 顧客情報一覧
     * @param customer 顧客情報
     * @return 含まれている場合true、含まれていない場合false
     */
    private boolean exists(final List<Customer> customers, final Customer customer) {
        for (Customer c : customers) {
            if (c.getId() == customer.getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 全表示する社員にアクセス権のない顧客情報を返す.
     * @return 全顧客情報
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * アクセス権を設定する社員IDを設定する.
     * @param employeeIds 社員ID
     */
    public void setEmployeeIds(final List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }
    /**
     * アクセス権を設定する社員IDを返す.
     * @return 社員ID
     */
    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    /**
     * アクセス権を設定する社員情報を返す.
     * @return 社員情報
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * アクセス権を設定する顧客ID一覧を設定する.
     * @param selectedCustomerIds 顧客ID一覧
     */
    public void setCustomerIds(final List<Long> selectedCustomerIds) {
        this.customerIds = selectedCustomerIds;
    }

    /**
     * {@link BelongsService} を設定する.
     * @param belongsService {@link BelongsService}
     */
    public void setBelongsService(final BelongsService belongsService) {
        this.belongsService = belongsService;
    }

    /**
     * {@link CustomerService} を設定する.
     * @param customerService {@link CustomerService}
     */
    public void setCustomerService(final CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * {@link AccessControlService} を設定する.
     * @param accessControlService {@link AccessControlService}
     */
    public void setAccessControlService(final AccessControlService accessControlService) {
        this.accessControlService = accessControlService;
    }
}
