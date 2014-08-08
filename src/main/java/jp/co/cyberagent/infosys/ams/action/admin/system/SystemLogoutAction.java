package jp.co.cyberagent.infosys.ams.action.admin.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ログアウトアクションクラス.
 * @author shimizu.kenji
 *
 */
@ParentPackage("admin")
public final class SystemLogoutAction extends ActionSupport implements ServletRequestAware {

    /** Serial Version UID. */
    private static final long serialVersionUID = 4738731103256518397L;
    /** HTTP リクエスト. */
    private transient HttpServletRequest request;

    /**
     * ログアウト処理.
     * @return ログイン画面
     */
    @Action("/admin/system/logout")
    public String logout() {
        request.getSession(false).removeAttribute("login_user");
        return "redirect_system_login";
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setServletRequest(final HttpServletRequest request) {
        this.request = request;
    }
}
