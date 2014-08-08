package jp.co.cyberagent.infosys.ams.action.admin.system;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.service.SystemService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;


/**
 * リンケージキー削除用アクションクラス.
 * @author ootaka
 */
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class LnkageSystemDeleteAction extends ActionSupport {

    /** リンケージキーモデルID. */
    private Long id;

    /** システム管理サービス. */
    @Autowired
    private SystemService service;

    /**
     * 削除処理.
     * @return 成功:null(HTTPステータス:200)
     * @throws AMSException リンケージキーの削除に失敗した場合
     */
    @Action("/admin/system/linkage-system-delete")
    public String execute() throws AMSException {
        service.removeLinkageSystem(id);
        return null;
    }

    /**
     * リンケージキーモデルIDを取得する.
     * @return id リンケージキーモデルID
     */
    public Long getId() {
        return id;
    }

    /**
     * リンケージキーモデルIDを設定する.
     * @param id リンケージキーモデルID
     */
    public void setId(final Long id) {
        this.id = id;
    }

}
