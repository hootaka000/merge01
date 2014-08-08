package jp.co.cyberagent.infosys.ams.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 連携システム モデルクラス.
 *
 * @author shimizu.kenji
 */
@Entity
@Table(name = "linkage_system")
@NamedQuery(name="linkage_system.findAll", query="from LinkageSystem")
public final class LinkageSystem implements Serializable, Comparable<LinkageSystem> {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** アクセスキーの文字数. */
    public static final int ACCESS_KEY_LENGTH = 32;

    /** ID. */
    @Id
    @GeneratedValue
    private Long id;

    /** システム名. */
    private String name;

    /** アクセスキー. */
    @Column(name="access_key")
    private String accessKey;

    /** 管理者. */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /** 操作ログ 一覧. */
    @OneToMany(mappedBy = "linkageSystem")
    private List<OperationLog> operationLogs;

    /**
     * ID を設定します.
     *
     * @param id ID
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * ID を取得します.
     * @return ID
     */
    public Long getId() {
        return this.id;
    }

    /**
     * システム名 を設定します.
     * @param name システム名
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * システム名 を取得します.
     * @return システム名
     */
    public String getName() {
        return this.name;
    }

    /**
     * アクセスキー を設定します.
     * @param accessKey アクセスキー
     */
    public void setAccessKey(final String accessKey) {
        this.accessKey = accessKey;
    }

    /**
     * アクセスキー を取得します.
     * @return アクセスキー
     */
    public String getAccessKey() {
        return this.accessKey;
    }

    /**
     * 管理者 を設定します.
     * @param user 管理者
     */
    public void setUser(final User user) {
        this.user = user;
    }

    /**
     * 管理者 を取得します.
     * @return 管理者
     */
    public User getUser() {
        return this.user;
    }

    /**
     * 操作ログ 一覧を設定します.
     * @param operationLogs 操作ログ 一覧
     */
    public void setOperationLogSet(final List<OperationLog> operationLogs) {
        this.operationLogs = operationLogs;
    }

    /**
     * 操作ログ を追加します.
     * @param operationLog 操作ログ
     */
    public void addOperationLog(final OperationLog operationLog) {
        this.operationLogs.add(operationLog);
    }

    /**
     * 操作ログ 一覧を取得します.
     * @return 操作ログ 一覧
     */
    public List<OperationLog> getOperationLogs() {
        return this.operationLogs;
    }
    /**
     * 連携システムID順で比較する.
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final LinkageSystem o) {
        // TODO 未実装
        return 0;
    }
}
