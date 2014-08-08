package jp.co.cyberagent.infosys.ams.action.admin.groups;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Group;
import jp.co.cyberagent.infosys.ams.service.GroupService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * グループ名変更用アクションクラス.
 * @author ootaka
 */
@Validations(
        requiredStrings = {
                @RequiredStringValidator(
                        fieldName = "group.name",
                        message = "${getText('errors.required.group.name')}")
    }
)
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class GroupsUpdateAction extends ActionSupport {

    /** グループ. */
    private Group group;

    /** グループ管理サービス. */
    @Autowired
    private GroupService service;

    /**
     * バリデーション.
     * <p>登録済グループ名の重複チェックをする.
     */
    @Override
    public void validate() {
        /*try {

            // 登録済グループ名の重複チェックをする.
            if (group != null) {
                Group g = service.getGroup(group.getName());
                if (g != null) {
                    addActionError(getText("errors.validation.group.duplicate.name"));
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
     * @throws AMSException グループ名の変更に失敗した場合
     */
    @Action("/admin/groups/groups-update")
    public String execute() throws AMSException {
        service.rename(group.getId(), group.getName());
        return null;
    }

    /**
     * グループを取得する.
     * @return group グループ
     */
    public Group getGroup() {
        return group;
    }

    /**
     * グループを設定する.
     * @param group グループ
     */
    public void setGroup(final Group group) {
        this.group = group;
    }

}
