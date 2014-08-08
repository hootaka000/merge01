package jp.co.cyberagent.infosys.ams.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Employee;

/**
 * 社員情報へのアクセス機能を提供する.
 * @author b03883
 *
 */
@Repository("employeeDao")
public class EmployeeDaoJpa implements EmployeeDao {

    private static final Logger logger = Logger.getLogger(EmployeeDaoJpa.class.getName());

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() throws AMSException {
        TypedQuery<Employee> query
            = entityManager.createNamedQuery("employee.findAll", Employee.class);

        try {
            return query.getResultList();
        } catch (Throwable t) {
            String message = "社員情報情報の取得に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public Employee findById(long id) throws AMSException {
        TypedQuery<Employee> query
            = entityManager.createNamedQuery("employee.findById", Employee.class);
        query.setParameter("id", id);

        try {
            return query.getSingleResult();
        } catch (NoResultException no) {
            return null;
        } catch (Throwable t) {
            String message = "社員情報の検索に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public List<Employee> findByBelongsId(long id) throws AMSException {
        TypedQuery<Employee> query
            = entityManager.createNamedQuery("employee.findByBelongsId", Employee.class);
        query.setParameter("id", id);

        try {
            return query.getResultList();
        } catch (Throwable t) {
            String message = "社員情報の検索に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public void update(Employee employee) throws AMSException {
        try {
            entityManager.merge(employee);
        } catch (Throwable t) {
            String message = "社員情報の更新に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

}
