package jp.co.cyberagent.infosys.ams.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * ログイン状態の確認を行うインターセプター.
 * @author shimizu.kenji
 *
 */
public final class LoginInterceptor extends AbstractInterceptor {

    /** Serial Version UID. */
    private static final long serialVersionUID = -103450620012735728L;

    /**
     * ログイン状態を確認する.
     * {@inheritDoc}
     */
    @Override
    public String intercept(final ActionInvocation invocation) throws Exception {
        Map<String, Object> session = invocation.getInvocationContext().getSession();
        if (session.get("login_user") == null) {
            return "redirect_system_login";
        }
        return invocation.invoke();
    }

}
