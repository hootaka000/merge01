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
import jp.co.cyberagent.infosys.ams.model.Belongs;

/**
 * 所属情報にアクセスする機能を提供する.
 * @author b03883
 *
 */
@Repository("belongsDao")
public class BelongsDaoJpa implements BelongsDao {

    private static final Logger logger = Logger.getLogger(BelongsDaoJpa.class.getName());

    private EntityManager entityManager;
    private static final String OPE_ADD = "追加";
    private static final String OPE_UPD = "更新";

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Belongs findById(long id) throws AMSException {
        TypedQuery<Belongs> query
            = entityManager.createNamedQuery("belongs.findById", Belongs.class);
        query.setParameter("id", id);

        try {
            return query.getSingleResult();
        } catch (NoResultException no) {
            return null;
        } catch (Throwable t) {
            String message = "所属情報の検索に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public List<Belongs> findAll() throws AMSException {
        TypedQuery<Belongs> query
            = entityManager.createNamedQuery("belongs.findAll", Belongs.class);

        try {
            return query.getResultList();
        } catch (Throwable t) {
            String message = "所属情報の取得に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public void update(Belongs belongs) throws AMSException {
        update(belongs, OPE_UPD);
    }

    @Override
    public void save(Belongs belongs) throws AMSException {
        update(belongs, OPE_ADD);
    }

    @Override
    public void delete(long id) throws AMSException {
        try {
            Belongs belongs = entityManager.find(Belongs.class, id);
            entityManager.remove(entityManager.merge(belongs));
        } catch (Throwable t) {
            String message = "所属情報の削除に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public String getNextBelongsId() {
        Query query = entityManager.createQuery(
                "select max(substring(belongs_id,2,5)) + 1 from Belongs");
        Object ret = query.getSingleResult();
        Integer nextBelongsId = (ret == null) ? 1 : (Integer)ret;
        return new StringBuilder(Belongs.BELONGS_ID_PREFIX)
            .append(String.format("%05d", nextBelongsId)).toString();
    }

    private void update(Belongs belongs, String ope) throws AMSException {
        try {
            if (OPE_ADD.equals(ope)) {
                entityManager.persist(belongs);
            } else if (OPE_UPD.equals(ope)) {
                entityManager.merge(belongs);
            }
        } catch (Throwable t) {
            String message = new StringBuilder("所属情報の")
                .append(ope)
                .append("に失敗しました")
                .toString();
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

}
