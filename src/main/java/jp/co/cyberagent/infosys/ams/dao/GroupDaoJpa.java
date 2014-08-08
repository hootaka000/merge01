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
import jp.co.cyberagent.infosys.ams.model.Group;

/**
 * グループ情報にアクセス機能を提供する.
 * @author b03883
 *
 */
@Repository("groupDao")
public class GroupDaoJpa implements GroupDao {

    private static final Logger logger = Logger.getLogger(GroupDaoJpa.class.getName());

    private EntityManager entityManager;
    private static final String OPE_ADD = "追加";
    private static final String OPE_UPD = "更新";

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Group findById(long id) throws AMSException {
        TypedQuery<Group> query
            = entityManager.createNamedQuery("group.findById", Group.class);
        query.setParameter("id", id);

        try {
            return query.getSingleResult();
        } catch (NoResultException no) {
            return null;
        } catch (Throwable t) {
            String message = "グループ情報の検索に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public List<Group> findAll() throws AMSException {
        TypedQuery<Group> query
            = entityManager.createNamedQuery("group.findAll", Group.class);

        try {
            return query.getResultList();
        } catch (Throwable t) {
            String message = "グループ情報の取得に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public void save(Group group) throws AMSException {
        update(group, OPE_ADD);
    }

    @Override
    public void update(Group group) throws AMSException {
        update(group, OPE_UPD);
    }

    @Override
    public void delete(long id) throws AMSException {
        try {
            Group group = entityManager.find(Group.class, id);
            entityManager.remove(entityManager.merge(group));
        } catch (Throwable t) {
            String message = "グループ情報の削除に失敗しました";
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

    @Override
    public String getNextGroupId() {
        Query query = entityManager.createQuery(
                "select max(substring(group_id,2,5)) + 1 from Group");
        Object ret = query.getSingleResult();
        Integer nextBelongsId = (ret == null) ? 1 : (Integer)ret;
        return new StringBuilder(Group.GROUP_ID_PREFIX)
            .append(String.format("%05d", nextBelongsId)).toString();
    }

    private void update(Group group, String ope) throws AMSException {
        try {
            if (OPE_ADD.equals(ope)) {
                group.setGroupId(getNextGroupId());
                entityManager.persist(group);
            } else if (OPE_UPD.equals(ope)) {
                entityManager.merge(group);
            }
        } catch (Throwable t) {
            String message = new StringBuilder("グループ情報の")
                .append(ope)
                .append("に失敗しました")
                .toString();
            logger.error(message, t);
            throw new AMSException(message, t);
        }
    }

}
