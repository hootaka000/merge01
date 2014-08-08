package jp.co.cyberagent.infosys.ams.action.admin.belongs;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Belongs;
import jp.co.cyberagent.infosys.ams.service.BelongsService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 所属名変更用アクションクラス.
 * @author ootaka
 */
@Validations(
        requiredStrings = {
                @RequiredStringValidator(
                        fieldName = "belong.name",
                        message = "${getText('errors.required.belong.name')}")
    }
)
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class BelongsUpdateAction extends ActionSupport {

    /** 所属. */
    private Belongs belong;

    /** 所属管理サービス. */
    @Autowired
    private BelongsService service;

    /**
     * バリデーション.
     * <p>登録済所属名の重複チェックをする.
     */
    @Override
    public void validate() {
        /*try {

            // 登録済所属名の重複チェックをする.
            if (belong != null) {
                Belong g = service.getBelong(belong.getName());
                if (g != null) {
                    addActionError(getText("errors.validation.belong.duplicate.name"));
                    return;
                }
            }

        } catch (Exception e) {
            addActionError(getText("errors.common.system"));
        }*/
    }

    /**
     * 更新処理.
     * @return 成功:null(HTTPステータス:200), 失敗:input
     * @throws AMSException 所属名の変更に失敗した場合
     */
    @Action("/admin/belongs/belongs-update")
    public String execute() throws AMSException {
        service.rename(belong.getId(), belong.getName());
        return null;
    }

    /**
     * 所属を取得する.
     * @return belong 所属
     */
    public Belongs getBelong() {
        return belong;
    }

    /**
     * 所属を設定する.
     * @param belong 所属
     */
    public void setBelong(final Belongs belong) {
        this.belong = belong;
    }

}
