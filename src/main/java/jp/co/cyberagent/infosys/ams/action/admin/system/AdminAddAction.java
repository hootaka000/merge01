package jp.co.cyberagent.infosys.ams.action.admin.system;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.service.SystemService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 管理者を追加するアクションクラス.
 * @author A13413
 *
 */
/** 入力チェック. */
@Validations(
        requiredStrings = {
                @RequiredStringValidator(
                        fieldName = "employeeNumber",
                        message = "${getText('errors.required.employee.number')}")
    }
)
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class AdminAddAction extends ActionSupport {

    /** Serial Version UID. */
    private static final long serialVersionUID = -762471547007325893L;

    /** 追加する社員番号. */
    private String employeeNumber;
    /** System Service. */
    private transient SystemService systemService;

    /**
     * 管理者を追加する.
     * @return 成功:null(HTTPステータス:200)
     * @throws AMSException 管理者の追加に失敗した場合
     */
    @Action("/admin/system/admin-add")
    public String update() throws AMSException {
        systemService.addAdminUser(employeeNumber);
        return null;
    }

    /**
     * 社員番号を設定する.
     * @param employeeNumber 社員番号
     */
    public void setEmployeeNumber(final String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    /**
     * 社員番号を取得する.
     * @return employeeNumber 社員番号
     */
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    /**
     * {@link SystemService} を設定する.
     * @param systemService {@link SystemService}
     */
    public void setSystemService(final SystemService systemService) {
        this.systemService = systemService;
    }
}
