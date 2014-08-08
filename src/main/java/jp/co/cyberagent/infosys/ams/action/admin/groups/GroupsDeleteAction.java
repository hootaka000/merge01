package jp.co.cyberagent.infosys.ams.action.admin.groups;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.service.GroupService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;


/**
 * グループ削除用アクションクラス.
 * @author ootaka
 */
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class GroupsDeleteAction extends ActionSupport {

    /** グループモデルID. */
    private Long id;

    /** グループ管理サービス. */
    @Autowired
    private GroupService service;

    /**
     * 削除処理.
     * @return 成功:null(HTTPステータス:200)
     * @throws AMSException グループの削除に失敗した場合
     */
    @Action("/admin/groups/groups-delete")
    public String execute() throws AMSException {
        service.removeGroup(id);
        return null;
    }

    /**
     * グループモデルIDを取得する.
     * @return id グループモデルID
     */
    public Long getId() {
        return id;
    }

    /**
     * グループモデルIDを設定する.
     * @param id グループモデルID
     */
    public void setId(final Long id) {
        this.id = id;
    }

}
