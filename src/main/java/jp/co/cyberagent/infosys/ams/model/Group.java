package jp.co.cyberagent.infosys.ams.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
 * グループ モデルクラス.
 * @author shimizu.kenji
 */
@Entity
@Table(name = "`group`")
@NamedQueries({
    @NamedQuery(name="group.findById", query="from Group where id = :id"),
    @NamedQuery(name="group.findAll", query="from Group")
})
public final class Group implements Serializable, Comparable<Group> {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** グループID接頭辞. */
    public static final String GROUP_ID_PREFIX = "G";

    /** ID. */
    @Id
    @GeneratedValue
    private Long id;

    /** グループID. */
    @Column(name="group_id")
    private String groupId;

    /** グループ名. */
    private String name;

    /** グループメンバ一覧. */
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name="employee_group",
                joinColumns={@JoinColumn(name="group_id")},
                inverseJoinColumns={@JoinColumn(name="employee_id")})
    private List<Employee> employees;

    /** アクセス権を設定した顧客一覧. */
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name="customer_group",
                joinColumns={@JoinColumn(name="group_id")},
                inverseJoinColumns={@JoinColumn(name="customer_id")})
    private List<Customer> customers;

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
     * グループID を設定します.
     * @param name グループID
     */
    public void setGroupId(final String groupId) {
        this.groupId = groupId;
    }

    /**
     * グループID を取得します.
     * @return グループID
     */
    public String getGroupId() {
        return this.groupId;
    }

    /**
     * グループ名 を設定します.
     * @param name グループ名
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * グループ名 を取得します.
     * @return グループ名
     */
    public String getName() {
        return this.name;
    }

    /**
     * グループに属する社員一覧を返す.
     * @return 社員一覧
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * グループに属する社員一覧を設定する.
     * @param employees 社員一覧
     */
    public void setEmployees(final List<Employee> employees) {
        this.employees = employees;
    }
    /**
     * グループに社員を追加する.
     * @param employee 社員情報
     */
    public void addEmployee(final Employee employee) {
        if (employees == null) {
            employees = new LinkedList<>();
        }
        employees.add(employee);
    }
    /**
     * グループの社員を取り除く.
     * @param employee 社員情報
     */
    public void removeEmployee(final Employee employee) {
        if (employees == null) {
            throw new NullPointerException("employees is null.");
        }
        employees.remove(employee);
    }

    /**
     * アクセス権を設定した顧客一覧を返す.
     * @return 顧客一覧
     */
    public List<Customer> getCustomers() {
        return customers;
    }
    /**
     * アクセス権を設定した顧客一覧を設定する.
     * @param customers 顧客一覧
     */
    public void setCustomers(final List<Customer> customers) {
        this.customers = customers;
    }
    /**
     * 顧客を一覧に追加する.
     * @param customer 顧客情報
     */
    public void addCustomer(final Customer customer) {
        if (customers == null) {
            customers = new LinkedList<>();
        }
        customers.add(customer);
    }
    /**
     * 顧客を一覧から取り除く.
     * @param customer 顧客情報
     */
    public void removeCustomer(final Customer customer) {
        if (customers == null) {
            throw new NullPointerException("customers is null.");
        }
        customers.remove(customer);
    }
    /**
     * グループID順で比較する.
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final Group o) {
        // TODO 未実装
        return 0;
    }
}
