package jp.co.cyberagent.infosys.ams.action.admin.groups;

import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Employee;
import jp.co.cyberagent.infosys.ams.model.Group;
import jp.co.cyberagent.infosys.ams.service.BelongsService;
import jp.co.cyberagent.infosys.ams.service.GroupService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

/**
 * グループ社員設定アクションクラス.
 * @author ootaka
 */
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class GroupsEmployeeAddAction extends ActionSupport {

    /** グループモデルID. */
    private long groupId;
    /** グループモデル. */
    private Group group;
    /** 全社員モデル. */
    private List<Employee> allEmployees;
    /** 選択社員モデルID. */
    private List<Long> selectEmployeesId;

    /** グループ管理サービス. */
    @Autowired
    private GroupService groupService;
    /** 所属管理サービス. */
    @Autowired
    private BelongsService belongsService;

    /**
     * グループ社員設定一覧表示.
     * @return 成功:input
     * @throws AMSException 一覧の取得に失敗した場合
     */
    @Action("/admin/groups/groups-employee-add")
    public String execute() throws AMSException {
        allEmployees = belongsService.getAllEmployees();

        return INPUT;
    }

    /**
     * グループ社員設定処理.
     * @return 成功:null(HTTPステータス:200)
     * @throws AMSException グループ社員の設定に失敗した場合
     */
    @Action("/admin/groups/groups-employee-add-update")
    public String update() throws AMSException {
        //NULLを省く
        for (Long v : selectEmployeesId) {
            if (v != null) {
                groupService.addEmployee(groupId, v);
            }
        }
        return null;
    }

    /**
     * グループモデルを取得する.
     * @return グループ
     */
    public Group getGroup() {
        return group;
    }

    /**
     * グループモデルを設定する.
     * @param group グループ
     */
    public void setGroup(final Group group) {
        this.group = group;
    }

    /**
     * グループモデルIDを取得する.
     * @return groupId グループモデルID
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * グループモデルを設定する.
     * @param groupId グループモデルID
     */
    public void setGroupId(final Long groupId) {
        this.groupId = groupId;
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
