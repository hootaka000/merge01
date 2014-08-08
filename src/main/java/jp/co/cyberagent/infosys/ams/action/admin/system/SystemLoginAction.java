package jp.co.cyberagent.infosys.ams.action.admin.system;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.User;
import jp.co.cyberagent.infosys.ams.service.SystemService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * ログインアクションクラス.
 * @author shimizu.kenji
 *
 */
@Validations(
        requiredStrings = {
            @RequiredStringValidator(
                fieldName = "username",
                message = "${getText('errors.systemlogin.username.required')}"),
            @RequiredStringValidator(
                fieldName = "password",
                message = "${getText('errors.systemlogin.password.required')}")
        }
    )
@ParentPackage("admin")
public final class SystemLoginAction extends ActionSupport implements ServletRequestAware {

    /** Serial Version UID. */
    private static final long serialVersionUID = 669220616909334438L;
    /** ユーザー名. */
    private String username;
    /** パスワード. */
    private String password;
    /** System Service. */
    private transient SystemService systemService;
    /** HTTP リクエスト. */
    private transient HttpServletRequest request;

    /**
     * ログイン画面を表示する.
     * @return ログイン画面
     */
    @Action("/admin/system/login")
    @SkipValidation
    public String execute() {
        String errorMessage = (String) request.getSession(false).getAttribute("flush_notice");
        setActionErrors(Arrays.asList(errorMessage));
        return LOGIN;
    }

    /**
     * ログイン処理.
     * @return ログインに成功した場合アクセス権設定画面、ログインに失敗した場合ログイン画面
     */
    @Action("/admin/system/login_do")
    @SkipValidation
    public String login() {
        try {
            User user = systemService.login(username, password);
            if (user == null) {
                request.getSession(false).setAttribute("flush_notice", "ログインに失敗しました。社員番号またはパスワードが間違っています。");
                return "redirect_system_login";
            }
            request.getSession(false).setAttribute("login_user", user);
            return "redirect_assign_setting";
        } catch (AMSException e) {
            request.getSession(false).setAttribute("flush_notice", "ログインに失敗しました。社員番号またはパスワードが間違っています。");
            return "redirect_system_login";
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setServletRequest(final HttpServletRequest request) {
        this.request = request;
    }

    /**
     * ユーザー名を設定する.
     * @param username ユーザー名
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * パスワードを設定する.
     * @param password パスワード
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * {@link SystemService} を設定する.
     * @param systemService {@link SystemService}
     */
    public void setSystemService(final SystemService systemService) {
        this.systemService = systemService;
    }
}
