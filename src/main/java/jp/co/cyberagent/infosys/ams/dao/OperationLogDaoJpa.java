package jp.co.cyberagent.infosys.ams.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.OperationLog;

/**
 * 操作ログ情報にアクセスする機能を提供する.
 * @author b03883
 *
 */
@Repository("operationLogDao")
public class OperationLogDaoJpa implements OperationLogDao {

    private static final Logger logger = Logger.getLogger(OperationLogDaoJpa.class.getName());

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(OperationLog log) throws AMSException {
        try {
            entityManager.persist(log);
        } catch (Throwable t) {
            String message = "操作ログの追加に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

}
