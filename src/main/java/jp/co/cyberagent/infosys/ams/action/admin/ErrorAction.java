package jp.co.cyberagent.infosys.ams.action.admin;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * エラー用アクションクラス.
 * @author ootaka
 */
public final class ErrorAction extends ActionSupport {

    /**
     * エラー処理.
     * @return 成功:success
     */
    @Action("/admin/error")
    public String execute() {
        return SUCCESS;
    }

}
