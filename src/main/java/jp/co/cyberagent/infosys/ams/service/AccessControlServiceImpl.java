package jp.co.cyberagent.infosys.ams.service;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.dao.CustomerDao;
import jp.co.cyberagent.infosys.ams.dao.EmployeeDao;
import jp.co.cyberagent.infosys.ams.dao.GroupDao;
import jp.co.cyberagent.infosys.ams.model.Customer;
import jp.co.cyberagent.infosys.ams.model.Employee;
import jp.co.cyberagent.infosys.ams.model.Group;
import jp.co.cyberagent.infosys.ams.utils.LoadError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link AccessControlService} の実装クラス.
 * @author A13413
 *
 */
@Service("accessControlService")
public final class AccessControlServiceImpl implements AccessControlService {

    /** Employee DAO. */
    @Autowired
    private EmployeeDao employeeDao;
    /** Customer DAO. */
    @Autowired
    private CustomerDao customerDao;
    /** Group DAO. */
    @Autowired
    private GroupDao groupDao;
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void setAccessControl(final long employeeId, final List<Long> customerIds)
            throws AMSException {
        // TODO テスト
        Employee employee = employeeDao.findById(employeeId);
        List<Customer> customers = employee.getCustomers();
        for (Customer customer : new LinkedList<>(customers)) {
            if (!customerIds.contains(customer.getId())) {
                customers.remove(customer);
            }
        }
        for (Long id : customerIds) {
            if (!containsCustomer(customers, id)) {
                Customer customer = customerDao.findById(id);
                if (customer == null) {
                    continue;
                }
                customers.add(customer);
            }
        }
        employeeDao.update(employee);
    }

    /**
     * 顧客情報一覧に指定の顧客IDの顧客情報が含まれているか判定する.
     * @param customers 顧客情報一覧
     * @param id 顧客ID
     * @return 含まれている場合true,含まれていない場合false
     */
    private boolean containsCustomer(final List<Customer> customers, final Long id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void setAccessControlByCustomer(final long customerId,
            final List<Long> employeeIds) throws AMSException {
        // TODO テスト
        Customer customer = customerDao.findById(customerId);
        List<Employee> employees = customer.getEmployees();
        for (Employee employee : new LinkedList<>(employees)) {
            if (!employeeIds.contains(employee.getId())) {
                employees.remove(employee);
            }
        }
        for (Long id : employeeIds) {
            if (!containsEmployee(employees, id)) {
                Employee employee = employeeDao.findById(id);
                if (employee == null) {
                    continue;
                }
                employees.add(employee);
            }
        }
        customerDao.update(customer);
    }

    /**
     * 社員一覧が指定の社員IDを含んでいるか判定する.
     * @param employees 社員一覧
     * @param id 社員ID
     * @return 含んでいる場合true, 含んでいない場合false
     */
    private boolean containsEmployee(final List<Employee> employees, final Long id) {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void setAccessControl(final List<Long> employeeIds, final List<Long> addCustomerIds)
            throws AMSException {
        // TODO テスト
        for (long employeeId : employeeIds) {
            Employee employee = employeeDao.findById(employeeId);
            if (employee == null) {
                continue;
            }
            for (long addCustomerId : addCustomerIds) {
                if (containsCustomer(employee.getCustomers(), addCustomerId)) {
                    continue;
                }
                Customer customer = customerDao.findById(addCustomerId);
                if (customer == null) {
                    continue;
                }
                employee.getCustomers().add(customer);
            }
            employeeDao.update(employee);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream createExcelFile(final List<Employee> employees)
            throws AMSException {
        // TODO テスト
        // TODO 未実装
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<LoadError<String, String>> loadExcelFile(final InputStream input,
            final ItemLoadOperator<AccessControlSet> operater) throws AMSException {
        // TODO テスト
        // TODO 未実装
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void setGroupAccessControl(final long groupId, final List<Long> customerIds)
            throws AMSException {
        // TODO テスト
        Group group = groupDao.findById(groupId);
        List<Customer> customers = group.getCustomers();
        for (Customer customer : new LinkedList<>(customers)) {
            if (!customerIds.contains(customer.getId())) {
                customers.remove(customer);
            }
        }
        for (Long id : customerIds) {
            if (!containsCustomer(customers, id)) {
                Customer customer = customerDao.findById(id);
                if (customer == null) {
                    continue;
                }
                customers.add(customer);
            }
        }
        groupDao.update(group);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void setGroupAccessControlByCustomer(final long customerId,
            final List<Long> groupIds) throws AMSException {
        // TODO テスト
        Customer customer = customerDao.findById(customerId);
        List<Group> groups = customer.getGroups();
        for (Group group : new LinkedList<>(groups)) {
            if (!groupIds.contains(group.getId())) {
                groups.remove(group);
            }
        }
        for (Long id : groupIds) {
            if (!containsGroup(groups, id)) {
                Group group = groupDao.findById(id);
                if (group == null) {
                    continue;
                }
                groups.add(group);
            }
        }
        customerDao.update(customer);
    }
    /**
     * グループ一覧が指定のグループIDを含んでいるか判定する.
     * @param groups グループ一覧
     * @param id グループID
     * @return 含んでいる場合true, 含んでいない場合false
     */
    private boolean containsGroup(final List<Group> groups, final Long id) {
        for (Group group : groups) {
            if (group.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
