package jp.co.cyberagent.infosys.ams.action.admin.system;

import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.LinkageSystem;
import jp.co.cyberagent.infosys.ams.model.User;
import jp.co.cyberagent.infosys.ams.service.SystemService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ActionSupport;

/**
 * システム設定画面を表示するアクションクラス.
 * @author shimizu.kenji
 *
 */
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class SystemSettingAction extends ActionSupport {

    /** Serial Version UID. */
    private static final long serialVersionUID = -1221045500271034836L;

    /** 管理者ユーザー一覧. */
    private List<User> adminUsers;
    /** 連携システム情報一覧. */
    private List<LinkageSystem> linkageSystems;

    /** System Service. */
    private transient SystemService systemService;
    /**
     * システム設定画面を表示する.
     * @return システム設定画面
     * @throws AMSException システム情報の取得に失敗した場合
     */
    @Override
    @Action("/admin/system/setting")
    public String execute() throws AMSException {
        adminUsers = systemService.getAdminUsers();
        linkageSystems = systemService.getLinkageSystems();
        return SUCCESS;
    }

    /**
     * 管理者ユーザー一覧を返す.
     * @return 管理者ユーザー一覧
     */
    public List<User> getAdminUsers() {
        return adminUsers;
    }
    /**
     * 連携システム情報一覧をかえす.
     * @return 連携システム情報一覧
     */
    public List<LinkageSystem> getLinkageSystems() {
        return linkageSystems;
    }
    /**
     * {@link SystemService} を設定する.
     * @param systemService {@link SystemService}
     */
    public void setSystemService(final SystemService systemService) {
        this.systemService = systemService;
    }
}
