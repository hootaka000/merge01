package jp.co.cyberagent.infosys.ams.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 操作ログ モデルクラス.
 *
 * @author shimizu.kenji
 */
@Entity
@Table(name = "operation_log")
public final class OperationLog implements Serializable, Comparable<OperationLog> {
    /** 操作種別. */
    public enum OperationType {
        /** 追加. */
        ADD,
        /** 更新. */
        UPDATE,
        /** 削除. */
        REMOVE
    }
    /** 操作結果. */
    public enum OperationResult {
        /** 成功. */
        SUCCESS,
        /** エラー. */
        ERROR,
        /** 中断. */
        ABORT
    }

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** ID. */
    @Id
    @GeneratedValue
    private Long id;

    /** 操作種別. */
    @Enumerated(EnumType.ORDINAL)
    private OperationType type;

    /** 操作日時. */
    @Column(name="date_time")
    private Date dateTime;

    /** 操作者. */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /** 連携システム. */
    @ManyToOne
    @JoinColumn(name = "linkage_system_id")
    private LinkageSystem linkageSystem;

    /** 操作内容. */
    private String detail;

    /** 操作結果. */
    @Enumerated(EnumType.ORDINAL)
    private OperationResult result;

    /**
     * ID を設定します.
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
     * 操作種別 を設定します.
     * @param type 操作種別
     */
    public void setType(final OperationType type) {
        this.type = type;
    }

    /**
     * 操作種別 を取得します.
     * @return 操作種別
     */
    public OperationType getType() {
        return this.type;
    }

    /**
     * 操作日時 を設定します.
     * @param dateTime 操作日時
     */
    public void setDateTime(final Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * 操作日時 を取得します.
     * @return 操作日時
     */
    public Date getDateTime() {
        return this.dateTime;
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
     * 操作内容 を設定します.
     * @param detail 操作内容
     */
    public void setDetail(final String detail) {
        this.detail = detail;
    }

    /**
     * 操作内容 を取得します.
     * @return 操作内容
     */
    public String getDetail() {
        return this.detail;
    }

    /**
     * 操作結果 を設定します.
     * @param result 操作結果
     */
    public void setResult(final OperationResult result) {
        this.result = result;
    }

    /**
     * 操作結果 を取得します.
     * @return 操作結果
     */
    public OperationResult getResult() {
        return this.result;
    }
    /**
     * 操作ログID順で比較する.
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final OperationLog o) {
        // TODO 未実装
        return 0;
    }
}
