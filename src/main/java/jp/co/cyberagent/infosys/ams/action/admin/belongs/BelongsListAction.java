package jp.co.cyberagent.infosys.ams.action.admin.belongs;

import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Belongs;
import jp.co.cyberagent.infosys.ams.model.Employee;
import jp.co.cyberagent.infosys.ams.service.BelongsService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 所属一覧用アクションクラス.
 * @author ootaka
 */
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class BelongsListAction extends ActionSupport {

    /** 所属モデルID. */
    private long id;

    /** 所属一覧情報. */
    private List<Belongs> belongs;

    /** 所属メンバ一覧情報. */
    private List<Employee> employees;

    /** 所属管理サービス. */
    @Autowired
    private BelongsService service;

    /**
     * 所属一覧ページ情報表示処理.
     * @return 成功:success
     * @throws AMSException 所属情報の取得に失敗した場合
     */
    @Action("/admin/belongs/belongs-list")
    public String execute() throws AMSException {

        belongs = service.getAllBelongs();
        if (belongs != null) {
            if (id == 0) {
                id = belongs.get(0).getId();
                employees = belongs.get(0).getEmployees();
            } else {
                for (Belongs g : belongs) {
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
     * 所属モデルIDを取得する.
     * @return id　所属モデルID
     */
    public long getId() {
        return id;
    }

    /**
     * 所属モデルIDを設定する.
     * @param id 所属モデルID
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * 所属一覧情報を取得する.
     * @return belongs 所属一覧
     */
    public List<Belongs> getBelongs() {
        return belongs;
    }

    /**
     * 所属メンバ一覧情報を取得する.
     * @return employees 所属メンバ一覧情報
     */
    public List<Employee> getEmployees() {
        return employees;
    }

}
