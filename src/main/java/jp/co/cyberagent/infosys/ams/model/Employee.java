package jp.co.cyberagent.infosys.ams.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Employee Model.
 * @author shimizu.kenji
 */
@Entity
@Table(name = "employee")
@NamedQueries({
    @NamedQuery(name="employee.findAll", query="from Employee"),
    @NamedQuery(name="employee.findById", query="from Employee where id = :id"),
    @NamedQuery(name="employee.findByBelongsId", query="select emp from Employee emp inner join emp.belongs b where b.id = :id")
})
public final class Employee implements Serializable, Comparable<Employee> {
    /** 役職. */
    public enum Grade {
        /** G5. */
        G5("統括"),
        /** G4. */
        G4("局長"),
        /** G3. */
        G3("マネージャー"),
        /** G2. */
        G2("リーダー");
        /** 役職名. */
        private String name;
        /**
         * コンストラクタ.
         * @param name 役職名
         */
        private Grade(final String name) {
            this.name = name;
        }
        /**
         * 役職名を返す.
         * @return 役職名
         */
        public String getName() {
            return name;
        }
    }
    /** Serial Version UID. */
    private static final long serialVersionUID = -2061731778611970746L;
    /** ID. */
    @Id
    @GeneratedValue
    private Long id;
    /** 社員番号. */
    private String number;
    /** 役職. */
    @Enumerated(EnumType.STRING)
    private Grade grade;
    /** 社員名. */
    private String name;
    /** E-Mail. */
    private String email;
    /** 所属局一覧. */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="belongs_employee",
                joinColumns={@JoinColumn(name="employee_id")},
                inverseJoinColumns={@JoinColumn(name="belongs_id")})
    private List<Belongs> belongs;
    /** 企業一覧. */
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name="customer_employee",
                joinColumns={@JoinColumn(name="employee_id")},
                inverseJoinColumns={@JoinColumn(name="customer_id")})
    private List<Customer> customers;
    /** グループ一覧. */
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name="employee_group",
                joinColumns={@JoinColumn(name="employee_id")},
                inverseJoinColumns={@JoinColumn(name="group_id")})
    private List<Group> groups;
    /**
     * IDを返す.
     * @return ID
     */
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
     * 社員番号を返す.
     * @return 社員番号
     */
    public String getNumber() {
        return number;
    }
    /**
     * 社員番号を設定する.
     * @param number 社員番号
     */
    public void setNumber(final String number) {
        this.number = number;
    }
    /**
     * 役職を返す.
     * @return 役職
     */
    public Grade getGrade() {
        return grade;
    }
    /**
     * 役職を設定する.
     * @param grade 役職
     */
    public void setGrade(final Grade grade) {
        this.grade = grade;
    }
    /**
     * 社員名を返す.
     * @return 社員名
     */
    public String getName() {
        return name;
    }
    /**
     * 社員名を設定する.
     * @param name 社員名
     */
    public void setName(final String name) {
        this.name = name;
    }
    /**
     * E-Mailを返す.
     * @return E-Mail
     */
    public String getEmail() {
        return email;
    }
    /**
     * E-Mailを設定する.
     * @param email E-Mail
     */
    public void setEmail(final String email) {
        this.email = email;
    }
    /**
     * 所属局一覧を返す.
     * @return 所属局一覧
     */
    public List<Belongs> getBelongs() {
        return belongs;
    }
    /**
     * 所属局一覧を設定する.
     * @param belongs 所属局一覧
     */
    public void setBelongs(final List<Belongs> belongs) {
        this.belongs = belongs;
    }
    /**
     * 顧客一覧を返す.
     * @return 顧客一覧
     */
    public List<Customer> getCustomers() {
        return customers;
    }
    /**
     * 顧客一覧を設定する.
     * @param customers 顧客一覧
     */
    public void setCustomers(final List<Customer> customers) {
        this.customers = customers;
    }
    /**
     * この社員が属するグループ一覧を返す.
     * @return groups グループ一覧
     */
    public List<Group> getGroups() {
        return groups;
    }
    /**
     * この社員が属するグループ一覧を設定する.
     * @param groups グループ一覧
     */
    public void setGroups(final List<Group> groups) {
        this.groups = groups;
    }
    /**
     * 文字列表現を返す.
     * @return 文字列表現
     */
    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s",
                id, number, grade.toString(), name, email);
    }
    /**
     * 社員番号順で比較する.
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final Employee o) {
        // TODO 未実装
        return 0;
    }
}
