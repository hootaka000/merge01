package jp.co.cyberagent.infosys.ams.action.admin.assign;

import java.util.LinkedList;
import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Customer;
import jp.co.cyberagent.infosys.ams.model.Group;
import jp.co.cyberagent.infosys.ams.service.AccessControlService;
import jp.co.cyberagent.infosys.ams.service.CustomerService;
import jp.co.cyberagent.infosys.ams.service.GroupService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ActionSupport;

/**
 * グループのアクセス権設定を行うアクションクラス.
 * @author shimizu.kenji
 *
 */
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class GroupAssignSettingAction extends ActionSupport {

    /** Serial Version UID. */
    private static final long serialVersionUID = -4510143950302396174L;

    /** アクセス権を設定するGroupID. */
    private Long groupId;
    /** アクセス権を設定するグループ情報. */
    private Group group;
    /** 全グループ情報. */
    private List<Group> groups;
    /** 表示するグループにアクセス権のない全顧客情報. */
    private List<Customer> customers;
    /** アクセス権を設定する顧客ID一覧. */
    private List<Long> customerIds;
    /** Group Service. */
    private transient GroupService groupService;
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
    @Action("/admin/assign/group-setting-dialog")
    public String execute() throws AMSException {
        customers = customerService.getAllCustomers();
        groups = groupService.getAll();
        if (groupId == null) {
            if (groups.isEmpty()) {
                return "redirect_assign_setting";
            }
            group = groups.get(0);
        } else {
            group = groupService.getGroup(groupId);
        }
        for (Customer customer : new LinkedList<>(customers)) {
            if (exists(group.getCustomers(), customer)) {
                customers.remove(customer);
            }
        }
        return SUCCESS;
    }

    /**
     * アクセス権設定を更新する.
     * @return アクセス権設定ページヘのリダイレクト
     * @throws AMSException アクセス権の設定に失敗した場合
     */
    @Action("/admin/assign/group-setting-update")
    public String update() throws AMSException {
        if (customerIds == null) {
            customerIds = new LinkedList<>();
        }
        accessControlService.setGroupAccessControl(groupId, customerIds);
        return "redirect_assign_setting";
    }
    /**
     * 顧客情報一覧に指定の顧客情報が含まれているか確認する.
     * @param customers 顧客情報一覧
     * @param customer 顧客情報
     * @return 含まれている場合true、含まれていない場合false
     */
    private boolean exists(final List<Customer> customers, final Customer customer) {
        if (customers == null) {
            return false;
        }
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
     * アクセス権を設定するグループIDを設定する.
     * @param groupId グループID
     */
    public void setGroupId(final long groupId) {
        this.groupId = groupId;
    }
    /**
     * アクセス権を設定するグループ情報を返す.
     * @return グループ情報
     */
    public Group getGroup() {
        return group;
    }
    /**
     * 全グループ情報を返す.
     * @return 全グループ情報
     */
    public List<Group> getGroups() {
        return groups;
    }
    /**
     * アクセス権を設定する顧客ID一覧を設定する.
     * @param selectedCustomerIds 顧客ID一覧
     */
    public void setCustomerIds(final List<Long> selectedCustomerIds) {
        this.customerIds = selectedCustomerIds;
    }

    /**
     * {@link GroupService} を設定する.
     * @param groupService {@link GroupService}
     */
    public void setGroupService(final GroupService groupService) {
        this.groupService = groupService;
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
