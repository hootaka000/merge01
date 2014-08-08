package jp.co.cyberagent.infosys.ams.action.admin.belongs;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.service.BelongsService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 所属削除用アクションクラス.
 * @author ootaka
 */
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class BelongsDeleteAction extends ActionSupport {

    /** 所属モデルID. */
    private Long id;

    /** 所属管理サービス. */
    @Autowired
    private BelongsService service;

    /**
     * 削除処理.
     * @return 成功:null(HTTPステータス:200)
     * @throws AMSException 所属の削除に失敗した場合
     */
    @Action("/admin/belongs/belongs-delete")
    public String execute() throws AMSException {
        service.removeBelongs(id);
        return null;
    }

    /**
     * 所属モデルIDを取得する.
     * @return id 所属モデルID
     */
    public Long getId() {
        return id;
    }

    /**
     * 所属モデルIDを設定する.
     * @param id 所属モデルID
     */
    public void setId(final Long id) {
        this.id = id;
    }

}
