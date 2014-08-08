package jp.co.cyberagent.infosys.ams.action.admin.assign;

import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Belongs;
import jp.co.cyberagent.infosys.ams.service.BelongsService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ActionSupport;

/**
 * アクセス権設定ページのアクションクラス.
 * @author shimizu.kenji
 *
 */
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class AssignViewAction extends ActionSupport {
    /** Serial Version UID. */
    private static final long serialVersionUID = -6024830040684277610L;

    /** 表示する所属ID. */
    private Long belongsId;
    /** 表示する所属情報. */
    private Belongs belongs;
    /** 全所属情報. */
    private List<Belongs> belongsList;
    /** Belongs Service. */
    private transient BelongsService belongsService;
    /**
     * アクセス権設定ページを表示する.
     * @return アクセス権設定ページ
     * @throws AMSException アクセス権設定ページの表示情報の取得に失敗した場合
     */
    @Override
    @Action("/admin/assign/setting")
    public String execute() throws AMSException {
        belongsList = belongsService.getAllBelongs();
        if (belongsList.isEmpty()) {
            return SUCCESS;
        }
        if (belongsId == null) {
            belongs = belongsList.get(0);
            belongsId = belongs.getId();
        } else {
            belongs = find(belongsList, belongsId);
            if (belongs == null) {
                // 所属ID指定なしにリダイレクト
                return "redirect_assign_setting";
            }
        }
        return SUCCESS;
    }
    /**
     * 所属情報一覧から指定の所属IDの所属情報を検索する.
     * <p>指定の所属IDの所属情報が一覧にない場合は<code>null</code>を返す.
     * @param belongsList 所属情報一覧
     * @param belongsId 所属ID
     * @return 所属情報またはnull
     */
    private Belongs find(final List<Belongs> belongsList, final Long belongsId) {
        for (Belongs belongs : belongsList) {
            if (belongs.getId().equals(belongsId)) {
                return belongs;
            }
        }
        return null;
    }
    /**
     * 表示する所属IDを返す.
     * @return 所属ID
     */
    public long getBelongsId() {
        return belongsId;
    }
    /**
     * 表示する所属IDを設定する.
     * @param belongsId 所属ID
     */
    public void setBelongsId(final long belongsId) {
        this.belongsId = belongsId;
    }
    /**
     * {@link BelongsService} を設定する.
     * @param belongsService {@link BelongsService}
     */
    public void setBelongsService(final BelongsService belongsService) {
        this.belongsService = belongsService;
    }
    /**
     * 表示する所属情報を返す.
     * @return 所属情報
     */
    public Belongs getBelongs() {
        return belongs;
    }
    /**
     * 全所属情報を返す.
     * @return 全所属情報
     */
    public List<Belongs> getBelongsList() {
        return belongsList;
    }
}
