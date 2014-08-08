package jp.co.cyberagent.infosys.ams.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.LinkageSystem;

/**
 * 連携システム情報にアクセスする機能を提供する.
 * @author b03883
 *
 */
@Repository("linkageSystemDao")
public class LinkageSystemDaoJpa implements LinkageSystemDao {

    private static final Logger logger = Logger.getLogger(LinkageSystemDaoJpa.class.getName());

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<LinkageSystem> findAll() throws AMSException {
        TypedQuery<LinkageSystem> query
            = entityManager.createNamedQuery("linkage_system.findAll", LinkageSystem.class);

        try {
            return query.getResultList();
        } catch (Throwable t) {
            String message = "連携システム情報の検索に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public void save(LinkageSystem linkageSystem) throws AMSException {
        try {
            entityManager.persist(linkageSystem);
        } catch (Throwable t) {
            String message = "連携システム情報の追加に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public void delete(long id) throws AMSException {
        try {
            LinkageSystem linkageSystem = entityManager.find(LinkageSystem.class, id);
            entityManager.remove(entityManager.merge(linkageSystem));
        } catch (Throwable t) {
            String message = "連携システム情報の削除に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

}
