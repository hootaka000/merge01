package jp.co.cyberagent.infosys.ams.service;

import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.dao.EmployeeDao;
import jp.co.cyberagent.infosys.ams.dao.GroupDao;
import jp.co.cyberagent.infosys.ams.model.Group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link GroupService} の実装クラス.
 * @author shimizu.kenji
 *
 */
@Service("groupService")
public final class GroupServiceImpl implements GroupService {

    /** Group DAO. */
    @Autowired
    private GroupDao groupDao;
    /** Employee DAO. */
    @Autowired
    private EmployeeDao employeeDao;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Group> getAll() throws AMSException {
        return groupDao.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Group getGroup(final long id) throws AMSException {
        return groupDao.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addEmployee(final long groupId, final long employeeId) throws AMSException {
        Group group = groupDao.findById(groupId);
        group.addEmployee(employeeDao.findById(employeeId));
        groupDao.update(group);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void removeEmployee(final long groupId, final long employeeId)
            throws AMSException {
        Group group = groupDao.findById(groupId);
        group.removeEmployee(employeeDao.findById(employeeId));
        groupDao.update(group);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void rename(final long groupId, final String name) throws AMSException {
        Group group = groupDao.findById(groupId);
        group.setName(name);
        groupDao.update(group);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
   public void addGroup(final String name) throws AMSException {
        Group group = new Group();
        group.setName(name);
        groupDao.save(group);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void removeGroup(final long id) throws AMSException {
        groupDao.delete(id);
    }

}
