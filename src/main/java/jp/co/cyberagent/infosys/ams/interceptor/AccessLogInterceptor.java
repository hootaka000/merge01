package jp.co.cyberagent.infosys.ams.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * アクセスログを記録するインターセプター.
 * @author shimizu.kenji
 *
 */
public final class AccessLogInterceptor extends AbstractInterceptor {

    /** Serial Version UID. */
    private static final long serialVersionUID = -8334541282439370821L;

    /**
     * 全リクエストでアクセスログを記録する.
     * {@inheritDoc}
     */
    @Override
    public String intercept(final ActionInvocation invocation) throws Exception {
        // TODO 未実装
        return null;
    }
}
