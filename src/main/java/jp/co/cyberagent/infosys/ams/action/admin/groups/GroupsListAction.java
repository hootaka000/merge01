package jp.co.cyberagent.infosys.ams.action.admin.groups;

import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Employee;
import jp.co.cyberagent.infosys.ams.model.Group;
import jp.co.cyberagent.infosys.ams.service.GroupService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

/**
 * グループ一覧用アクションクラス.
 * @author ootaka
 */
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class GroupsListAction extends ActionSupport {

    /** グループモデルID. */
    private long id;

    /** グループ一覧情報. */
    private List<Group> groups;

    /** グループメンバ一覧情報. */
    private List<Employee> employees;

    /** グループ管理サービス. */
    @Autowired
    private GroupService service;

    /**
     * グループ一覧ページ情報表示処理.
     * @return 成功:success
     * @throws AMSException グループ情報の取得に失敗した場合
     */
    @Action("/admin/groups/groups-list")
    public String execute() throws AMSException {

        groups = service.getAll();
        if (groups != null) {
            if (id == 0) {
                id = groups.get(0).getId();
                employees = groups.get(0).getEmployees();
            } else {
                for (Group g : groups) {
                    if (id == g.getId().longValue()) {
                        employees = g.getEmployees();
                        break;
                    }
                }
            }
        }

        return SUCCESS;
    }

    /**
     * グループモデルIDを取得する.
     * @return id　グループモデルID
     */
    public long getId() {
        return id;
    }

    /**
     * グループモデルIDを設定する.
     * @param id グループモデルID
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * グループ一覧情報を取得する.
     * @return groups グループ一覧
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * グループメンバ一覧情報を取得する.
     * @return employees グループメンバ一覧情報
     */
    public List<Employee> getEmployees() {
        return employees;
    }

}
