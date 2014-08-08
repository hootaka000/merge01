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
 * リンケージキーを追加するアクションクラス.
 * @author ootaka
 *
 */
/** 入力チェック. */
@Validations(
        requiredStrings = {
                @RequiredStringValidator(
                        fieldName = "linkageNumber",
                        message = "${getText('errors.required.linkage.number')}"),
                @RequiredStringValidator(
                        fieldName = "linkageName",
                        message = "${getText('errors.required.linkage.name')}")
    }
)
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class LnkageSystemAddAction extends ActionSupport {

    /** Serial Version UID. */
    private static final long serialVersionUID = -762471547007325893L;

    /** 社員番号. */
    private String linkageNumber;

    /** システム名. */
    private String linkageName;

    /** System Service. */
    private transient SystemService systemService;

    /**
     * リンケージキーを追加する.
     * @return 成功:null(HTTPステータス:200)
     * @throws AMSException リンケージキーの追加に失敗した場合
     */
    @Action("/admin/system/linkage-system-add")
    public String update() throws AMSException {
        systemService.addLinkageSystem(linkageNumber, linkageName);
        return null;
    }

    /**
     * {@link SystemService} を設定する.
     * @param systemService {@link SystemService}
     */
    public void setSystemService(final SystemService systemService) {
        this.systemService = systemService;
    }

    /**
     * 社員番号を取得する.
     * @return linkageNumber 社員番号
     */
    public String getLinkageNumber() {
        return linkageNumber;
    }

    /**
     * 社員番号を設定する.
     * @param linkageNumber 社員番号
     */
    public void setLinkageNumber(final String linkageNumber) {
        this.linkageNumber = linkageNumber;
    }

    /**
     * システム名を取得する.
     * @return linkageName システム名
     */
    public String getLinkageName() {
        return linkageName;
    }

    /**
     * システム名を設定する.
     * @param linkageName システム名
     */
    public void setLinkageName(final String linkageName) {
        this.linkageName = linkageName;
    }

}
