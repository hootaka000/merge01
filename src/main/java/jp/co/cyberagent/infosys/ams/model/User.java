package jp.co.cyberagent.infosys.ams.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User Model.
 * @author shimizu.kenji
 */
@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name="user.findByName", query="from User where name = :name"),
    @NamedQuery(name="user.findAllAdmin", query="from User where role = :role")
})
public final class User implements Serializable, Comparable<User> {
    /** Serial Version UID. */
    private static final long serialVersionUID = 5051037717099733509L;
    /** ユーザー権限. */
    public enum Role {
        /** 管理者. */
        ADMIN,
        /** 一般ユーザー. */
        USER;
    }
    /** ID. */
    private Long id;
    /** ユーザー名. */
    private String name;
    /** Role. */
    private Role role;
    /** 連携システム情報. */
    private List<LinkageSystem> linkageSystems;
    /** 操作履歴一覧. */
    private List<OperationLog> operationLogs;

    /**
     * IDを返す.
     * @return ID
     */
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    /**
     * IDを設定する.
     * @param id ID
     */
    public void setId(final Long id) {
        this.id = id;
    }
    /**
     * ユーザー名を返す.
     * @return ユーザー名
     */
    public String getName() {
        return name;
    }
    /**
     * ユーザー名を設定する.
     * @param name ユーザー名
     */
    public void setName(final String name) {
        this.name = name;
    }
    /**
     * ユーザー権限を返す.
     * @return ユーザー権限
     */
    @Enumerated(EnumType.ORDINAL)
    public Role getRole() {
        return role;
    }
    /**
     * ユーザー権限を設定する.
     * @param role ユーザー権限
     */
    public void setRole(final Role role) {
        this.role = role;
    }
    /**
     * 連携システム一覧を返す.
     * @return 連携システム一覧
     */
    @OneToMany(mappedBy = "user")
    public List<LinkageSystem> getLinkageSystems() {
        return linkageSystems;
    }
    /**
     * 連携システム一覧を設定する.
     * @param linkageSystems 連携システム一覧
     */
    public void setLinkageSystems(final List<LinkageSystem> linkageSystems) {
        this.linkageSystems = linkageSystems;
    }
    /**
     * 操作履歴一覧を返す.
     * @return 操作履歴一覧
     */
    @OneToMany(mappedBy = "user")
    public List<OperationLog> getOperationLogs() {
        return operationLogs;
    }
    /**
     * 操作履歴一覧を設定する.
     * @param operationLogs 操作履歴一覧
     */
    public void setOperationLogs(final List<OperationLog> operationLogs) {
        this.operationLogs = operationLogs;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%d,%s,%s", id, role.toString(), name);
    }
    /**
     * ユーザー名順で比較する.
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final User o) {
        // TODO 未実装
        return 0;
    }
}
