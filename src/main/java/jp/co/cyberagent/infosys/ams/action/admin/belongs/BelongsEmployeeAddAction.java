package jp.co.cyberagent.infosys.ams.action.admin.belongs;

import java.util.LinkedList;
import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Employee;
import jp.co.cyberagent.infosys.ams.model.Belongs;
import jp.co.cyberagent.infosys.ams.service.BelongsService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 所属社員設定アクションクラス.
 * @author ootaka
 */
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class BelongsEmployeeAddAction extends ActionSupport {

    /** 所属モデルID. */
    private long belongId;
    /** 所属モデル. */
    private Belongs belong;
    /** 全社員モデル. */
    private List<Employee> allEmployees;
    /** 選択社員モデルID. */
    private List<Long> selectEmployeesId;

    /** 所属管理サービス. */
    @Autowired
    private BelongsService belongsService;

    /**
     * 所属社員設定一覧表示.
     * @return 成功:input
     * @throws AMSException 一覧の取得に失敗した場合
     */
    @Action("/admin/belongs/belongs-employee-add")
    public String execute() throws AMSException {
        allEmployees = belongsService.getAllEmployees();

        return INPUT;
    }

    /**
     * 所属社員設定処理.
     * @return 成功:null(HTTPステータス:200)
     * @throws AMSException 所属社員の設定に失敗した場合
     */
    @Action("/admin/belongs/belongs-employee-add-update")
    public String update() throws AMSException {
        List<Long> employeeIds = new LinkedList<Long>();
        //NULLを省く
        for (Long v : selectEmployeesId) {
            if (v != null) {
                employeeIds.add(v);
            }
        }
        belongsService.addEmployee(belongId, employeeIds);
        return null;
    }

    /**
     * 所属モデルを取得する.
     * @return 所属
     */
    public Belongs getBelong() {
        return belong;
    }

    /**
     * 所属モデルを設定する.
     * @param belong 所属
     */
    public void setBelong(final Belongs belong) {
        this.belong = belong;
    }

    /**
     * 所属モデルIDを取得する.
     * @return belongId 所属モデルID
     */
    public Long getBelongId() {
        return belongId;
    }

    /**
     * 所属モデルを設定する.
     * @param belongId 所属モデルID
     */
    public void setBelongId(final Long belongId) {
        this.belongId = belongId;
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
