package jp.co.cyberagent.infosys.ams.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.dao.UserDao;
import jp.co.cyberagent.infosys.ams.model.User;
import jp.co.cyberagent.infosys.ams.model.User.Role;

/**
 * ユーザー情報へのアクセス機能を提供する.
 * 
 * @author b03883
 * 
 */
@Repository("userDao")
public class UserDaoJpa implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoJpa.class.getName());

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByName(String name) throws AMSException {
        TypedQuery<User> query
            = entityManager.createNamedQuery("user.findByName", User.class);
        query.setParameter("name", name);

        try {
            return query.getSingleResult();
        } catch (NoResultException no) {
            return null;
        } catch (Throwable t) {
            String message = "ユーザー情報の検索に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public List<User> findAllAdmin() throws AMSException {
        TypedQuery<User> query
            = entityManager.createNamedQuery("user.findAllAdmin", User.class);
        query.setParameter("role", Role.ADMIN);

        try {
            return query.getResultList();
        } catch (Throwable t) {
            String message = "ユーザー情報の検索に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public void save(User user) throws AMSException {
        try {
            entityManager.persist(user);
        } catch (Throwable t) {
            String message = "ユーザー情報の追加に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public void delete(long id) throws AMSException {
        try {
            User user = entityManager.find(User.class, id);
            entityManager.remove(entityManager.merge(user));
        } catch (Throwable t) {
            String message = "ユーザー情報の削除に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

}
