package jp.co.cyberagent.infosys.ams.action.admin.customer;

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
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 顧客社員用アクセス権設定アクションクラス.
 * @author ootaka
 */
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class CustomerGroupAclAction extends ActionSupport {

    /** 顧客モデルID. */
    private long customerId;
    /** 顧客モデル. */
    private Customer customer;
    /** 全グループモデル. */
    private List<Group> allGroups;
    /** 選択グループモデルID. */
    private List<Long> selectGroupsId;

    /** 顧客管理サービス. */
    @Autowired
    private CustomerService customerService;
    /** グループ管理サービス. */
    @Autowired
    private GroupService groupService;
    /** アクセス権コントロールサービス. */
    @Autowired
    private AccessControlService aclService;

    /**
     * アクセス権設定一覧表示.
     * @return 成功:input
     * @throws AMSException 一覧の取得に失敗した場合
     */
    @Action("/admin/customer/customer-group-acl")
    public String execute() throws AMSException {

        allGroups = groupService.getAll();
        customer = customerService.getCustomer(customerId);

        return INPUT;
    }

    /**
     * アクセス権設定処理.
     * @return 成功:null(HTTPステータス:200)
     * @throws AMSException アクセス設定に失敗した場合
     */
    @Action("/admin/customer/customer-group-acl-update")
    public String update() throws AMSException {
        List<Long> groupIds = new LinkedList<Long>();
        for (Long v : selectGroupsId) {
            if (v != null) {
                groupIds.add(v);
            }
        }
        aclService.setGroupAccessControlByCustomer(customerId, groupIds);
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
     * 全グループモデルを取得する.
     * @return allEmployees 全グループモデル
     */
    public List<Group> getAllGroups() {
        return allGroups;
    }

    /**
     * 全グループモデルを設定する.
     * @param allGroups 全グループモデル
     */
    public void setAllGroups(final List<Group> allGroups) {
        this.allGroups = allGroups;
    }

    /**
     * 選択グループモデルIDを取得する.
     * @return selectGroupsId 選択グループモデルID
     */
    public List<Long> getSelectGroupsId() {
        return selectGroupsId;
    }

    /**
     * 選択グループモデルIDを設定する.
     * @param selectGroupsId 選択グループモデルID
     */
    public void setSelectGroupsId(final List<Long> selectGroupsId) {
        this.selectGroupsId = selectGroupsId;
    }

}
