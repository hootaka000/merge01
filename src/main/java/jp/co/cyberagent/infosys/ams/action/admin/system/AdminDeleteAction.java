package jp.co.cyberagent.infosys.ams.action.admin.system;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.service.SystemService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 管理者削除用アクションクラス.
 * @author ootaka
 */
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class AdminDeleteAction extends ActionSupport {

    /** 管理者モデルID. */
    private Long id;

    /** システム管理サービス. */
    @Autowired
    private SystemService service;

    /**
     * 削除処理.
     * @return 成功:null(HTTPステータス:200)
     * @throws AMSException 管理者の削除に失敗した場合
     */
    @Action("/admin/system/admin-delete")
    public String execute() throws AMSException {
        service.removeUser(id);
        return null;
    }

    /**
     * 管理者モデルIDを取得する.
     * @return id 管理者モデルID
     */
    public Long getId() {
        return id;
    }

    /**
     * 管理者モデルIDを設定する.
     * @param id 管理者モデルID
     */
    public void setId(final Long id) {
        this.id = id;
    }

}
