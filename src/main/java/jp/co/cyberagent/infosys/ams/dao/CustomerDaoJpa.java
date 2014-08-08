package jp.co.cyberagent.infosys.ams.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Customer;

/**
 * 顧客情報にアクセスする機能を提供する.
 * @author b03883
 *
 */
@Repository("customerDao")
public class CustomerDaoJpa implements CustomerDao {

    private static final Logger logger = Logger.getLogger(CustomerDaoJpa.class.getName());

    private EntityManager entityManager;
    private static final String OPE_ADD = "追加";
    private static final String OPE_UPD = "更新";

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public int getCount() throws AMSException {
        Query query = entityManager.createQuery("select count(id) from Customer");

        try {
            return ((Long)query.getSingleResult()).intValue();
        } catch (Throwable t) {
            String message = "顧客数の取得に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public List<Customer> findAll() throws AMSException {
        TypedQuery<Customer> query
            = entityManager.createNamedQuery("customer.findAll", Customer.class);

        try {
            return query.getResultList();
        } catch (Throwable t) {
            String message = "顧客情報の取得に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> find(String filter, int offset, int limit)
            throws AMSException {
        Query query = entityManager.createQuery(
                "from Customer where caonId like :filter or name like :filter")
                    .setFirstResult(offset).setMaxResults(limit);
        query.setParameter("filter", "%" + filter + "%");

        try {
            return query.getResultList();
        } catch (Throwable t) {
            String message = "顧客情報の検索に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public Customer findById(long id) throws AMSException {
        TypedQuery<Customer> query
            = entityManager.createNamedQuery("customer.findById", Customer.class);
        query.setParameter("id", id);

        try {
            return query.getSingleResult();
        } catch (NoResultException no) {
            return null;
        } catch (Throwable t) {
            String message = "顧客情報の検索に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public Customer findByCaonId(String caonId) throws AMSException {
        TypedQuery<Customer> query
            = entityManager.createNamedQuery("customer.findByCaonId", Customer.class);
        query.setParameter("caon_id", caonId);

        try {
            List<Customer> data = query.getResultList();
            if(data.size() > 0){
                return data.get(0);
            }
            return null;
        } catch (NoResultException no) {
            return null;
        } catch (Throwable t) {
            String message = "顧客情報の検索に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public Customer findByCompanyName(String companyName) throws AMSException {
        List<Customer> customers = findByCompanyNameAll(companyName);
        for (Customer customer : customers) {
            if (customer.getDevision() == null) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public List<Customer> findByCompanyNameAll(String companyName) throws AMSException {
        TypedQuery<Customer> query
            = entityManager.createNamedQuery("customer.findByCompanyName", Customer.class);
        query.setParameter("name", companyName);

        try {
            return query.getResultList();
        } catch (NoResultException no) {
            return null;
        } catch (Throwable t) {
            String message = "顧客情報の検索に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public Customer findByDivisionName(String companyName, String divisionName)
            throws AMSException {
        TypedQuery<Customer> query
            = entityManager.createNamedQuery("customer.findByDivisionName", Customer.class);
        query.setParameter("name", companyName);
        query.setParameter("devision", divisionName);

        try {
            for (Customer customer : query.getResultList()) {
                if (customer.getService() == null) {
                    return customer;
                }
            }
            return null;
        } catch (NoResultException no) {
            return null;
        } catch (Throwable t) {
            String message = "顧客情報の検索に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public Customer findByServiceName(String companyName, String divisionName,
            String serviceName) throws AMSException {
        TypedQuery<Customer> query
            = entityManager.createNamedQuery("customer.findByServiceName", Customer.class);
        query.setParameter("name", companyName);
        query.setParameter("devision", divisionName);
        query.setParameter("service", serviceName);

        try {
            return query.getSingleResult();
        } catch (NoResultException no) {
            return null;
        } catch (Throwable t) {
            String message = "顧客情報の検索に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public String getNextDivisionId(Customer customer) throws AMSException {
        Query query = entityManager.createQuery(
                "select max(devision_id) + 1 from Customer where name = :name");
        query.setParameter("name", customer.getName());
        Object ret = query.getSingleResult();
        Integer nextDivisionId = (ret == null) ? 1 : (Integer)ret;
        return String.format("%03d", nextDivisionId);
    }

    @Override
    public String getNextServiceId(Customer customer) throws AMSException {
        Query query = entityManager.createQuery(
                "select max(service_id) + 1 from Customer where name = :name and devision = :division");
        query.setParameter("name", customer.getName());
        query.setParameter("division", customer.getDevision());
        Object ret = query.getSingleResult();
        Integer nextServiceId = (ret == null) ? 1 : (Integer)ret;
        return String.format("%03d", nextServiceId);
    }

    @Override
    public void save(Customer customer) throws AMSException {
        update(customer, OPE_ADD);
    }

    @Override
    public void delete(long id) throws AMSException {
        try {
            Customer customer = entityManager.find(Customer.class, id);
            entityManager.remove(entityManager.merge(customer));
        } catch (Throwable t) {
            String message = "顧客情報の削除に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public void update(Customer customer) throws AMSException {
        update(customer, OPE_UPD);
    }

    private void update(Customer customer, String ope) throws AMSException {
        try {
            if (OPE_ADD.equals(ope)) {
                entityManager.persist(customer);
            } else if (OPE_UPD.equals(ope)) {
                entityManager.merge(customer);
            }
        } catch (Throwable t) {
            String message = new StringBuilder("顧客情報の")
                .append(ope)
                .append("に失敗しました")
                .toString();
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

}
