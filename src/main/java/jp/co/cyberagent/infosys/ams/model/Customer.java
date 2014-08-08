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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 顧客 モデルクラス.
 * @author shimizu.kenji
 *
 */
@Entity
@Table(name = "customer")
@NamedQueries({
        @NamedQuery(name = "customer.findAll", query = "from Customer"),
        @NamedQuery(name = "customer.findById", query = "from Customer where id = :id"),
        @NamedQuery(name = "customer.findByCaonId", query = "from Customer where caon_id = :caon_id"),
        @NamedQuery(name = "customer.findByCompanyName", query = "from Customer where name = :name"),
        @NamedQuery(name = "customer.findByDivisionName", query = "from Customer where name = :name and devision = :devision"),
        @NamedQuery(name = "customer.findByServiceName", query = "from Customer where name = :name and devision = :devision and service = :service")
})
public final class Customer implements Serializable, Comparable<Customer> {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** ID. */
    @Id
    @GeneratedValue
    private Long id;

    /** 顧客ID. */
    @Column(name = "customer_id")
    private String customerId;

    /** CAON ID. */
    @Column(name = "caon_id")
    private String caonId = "";

    /** 企業名. */
    private String name;

    /** 事業部ID. */
    @Column(name = "devision_id")
    private String devisionId;

    /** 事業部名. */
    private String devision;

    /** サービスID. */
    @Column(name = "service_id")
    private String serviceId;

    /** サービス名. */
    private String service;

    /** 上位の顧客情報. */
    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Customer parent;

    /** 下位の顧客 一覧. */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private List<Customer> customers;

    /** 社員 一覧. */
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "customer_employee",
            joinColumns = { @JoinColumn(name = "customer_id") },
            inverseJoinColumns = { @JoinColumn(name = "employee_id") })
    private List<Employee> employees;

    /**
     * この顧客情報にアクセス可能なグループ一覧.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "customer_group",
            joinColumns = { @JoinColumn(name = "customer_id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id") })
    private List<Group> groups;

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
     * 顧客ID を設定します.
     * @param customerId 顧客ID
     */
    public void setCustomerId(final String customerId) {
        this.customerId = customerId;
    }

    /**
     * 顧客ID を取得します.
     * @return 顧客ID
     */
    public String getCustomerId() {
        return this.customerId;
    }

    /**
     * CAON ID を設定します.
     * @param caonId CAON ID
     */
    public void setCaonId(final String caonId) {
        this.caonId = caonId;
    }

    /**
     * CAON ID を取得します.
     * @return CAON ID
     */
    public String getCaonId() {
        if (caonId == null || caonId.isEmpty()) {
            return null;
        }
        return this.caonId;
    }

    /**
     * 企業名 を設定します.
     * @param name 企業名
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 企業名 を取得します.
     * @return 企業名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 事業部ID を設定します.
     * @param devisionId 事業部ID
     */
    public void setDevisionId(final String devisionId) {
        this.devisionId = devisionId;
    }

    /**
     * 事業部ID を取得します.
     * @return 事業部ID
     */
    public String getDevisionId() {
        if (devisionId == null || devisionId.isEmpty()) {
            return null;
        }
        return this.devisionId;
    }

    /**
     * 事業部名 を設定します.
     * @param devision 事業部名
     */
    public void setDevision(final String devision) {
        this.devision = devision;
    }

    /**
     * 事業部名 を取得します.
     * @return 事業部名
     */
    public String getDevision() {
        if (devision == null || devision.isEmpty()) {
            return null;
        }
        return this.devision;
    }

    /**
     * サービスID を設定します.
     * @param serviceId サービスID
     */
    public void setServiceId(final String serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * サービスID を取得します.
     * @return サービスID
     */
    public String getServiceId() {
        if (serviceId == null || serviceId.isEmpty()) {
            return null;
        }
        return this.serviceId;
    }

    /**
     * サービス名 を設定します.
     * @param service サービス名
     */
    public void setService(final String service) {
        this.service = service;
    }

    /**
     * サービス名 を取得します.
     * @return サービス名
     */
    public String getService() {
        if (service == null || service.isEmpty()) {
            return null;
        }
        return this.service;
    }

    /**
     * 上位の顧客情報 を設定します.
     * @param parent 顧客情報
     */
    public void setParent(final Customer parent) {
        this.parent = parent;
    }

    /**
     * 上位の顧客情報 を取得します.
     * @return 顧客情報
     */
    public Customer getParent() {
        return this.parent;
    }

    /**
     * 顧客 一覧を設定します.
     * @param customers 顧客 一覧
     */
    public void setCustomerSet(final List<Customer> customers) {
        this.customers = customers;
    }

    /**
     * 顧客 を追加します.
     * @param customer 顧客
     */
    public void addCustomer(final Customer customer) {
        if (customers == null) {
            customers = new LinkedList<>();
        }
        this.customers.add(customer);
    }

    /**
     * 顧客 一覧を取得します.
     * @return 顧客 一覧
     */
    public List<Customer> getCustomerSet() {
        return this.customers;
    }

    /**
     * この顧客情報にアクセス可能なグループ一覧を返す.
     * @return グループ一覧
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * この顧客情報にアクセス可能なグループ一覧を設定する.
     * @param groups グループ一覧
     */
    public void setGroups(final List<Group> groups) {
        this.groups = groups;
    }

    /**
     * アクセス可能なグループを追加する.
     * @param group グループ情報
     */
    public void addGroup(final Group group) {
        if (groups == null) {
            groups = new LinkedList<>();
        }
        groups.add(group);
    }

    /**
     * アクセス可能なグループを取り除く.
     * @param group グループ情報
     */
    public void removeGroup(final Group group) {
        if (groups == null) {
            throw new NullPointerException("groups is null.");
        }
        groups.remove(group);
    }

    /**
     * この顧客に紐付いた社員一覧を返す.
     * @return 社員一覧
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * この顧客に紐付いた社員一覧を設定する.
     * @param employees 社員一覧
     */
    public void setEmployees(final List<Employee> employees) {
        this.employees = employees;
    }

    /**
     * 顧客ID順で比較する.
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final Customer o) {
        // TODO 未実装
        return 0;
    }
}
