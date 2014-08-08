package jp.co.cyberagent.infosys.ams.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 所属 モデルクラス.
 *
 * @author shimizu.kenji
 */
@Entity
@Table(name = "belongs")
@NamedQueries({
    @NamedQuery(name="belongs.findById", query="from Belongs where id = :id"),
    @NamedQuery(name="belongs.findAll", query="from Belongs")
})
public final class Belongs implements Serializable, Comparable<Belongs> {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** 所属ID接頭辞. */
    public static final String BELONGS_ID_PREFIX = "B";

    /** ID. */
    @Id
    @GeneratedValue
    private Long id;

    /** 所属ID. */
    @Column(name="belongs_id")
    private String belongsId;

    /** 所属名. */
    private String name;

    /** 局長. */
    @ManyToOne
    @JoinColumn(name = "chief")
    private Employee chief;

    /** 所属メンバ一覧. */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="belongs_employee",
                joinColumns={@JoinColumn(name="belongs_id")},
                inverseJoinColumns={@JoinColumn(name="employee_id")})
    private List<Employee> employees;

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
     * 所属ID を設定します.
     * @param belongsId 所属ID
     */
    public void setBelongsId(final String belongsId) {
        this.belongsId = belongsId;
    }

    /**
     * 所属ID を取得します.
     * @return 所属ID
     */
    public String getBelongsId() {
        return this.belongsId;
    }

    /**
     * 所属名 を設定します.
     * @param name 所属名
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 所属名 を取得します.
     * @return 所属名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 局長を設定します.
     * @param chief 局長
     */
    public void setChief(final Employee chief) {
        this.chief = chief;
    }

    /**
     * 局長を取得します.
     * @return 局長
     */
    public Employee getChief() {
        return this.chief;
    }

    /**
     * @return employees
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * @param employees セットする employees
     */
    public void setEmployees(final List<Employee> employees) {
        this.employees = employees;
    }
    /**
     * 所属ID順で比較する.
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final Belongs o) {
        // TODO 未実装
        return 0;
    }
}
