package jp.co.cyberagent.infosys.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.dao.BelongsDao;
import jp.co.cyberagent.infosys.ams.dao.EmployeeDao;
import jp.co.cyberagent.infosys.ams.model.Belongs;
import jp.co.cyberagent.infosys.ams.model.Employee;

/**
 * {@link BelongsService} の実装クラス.
 * @author shimizu.kenji
 *
 */
@Service("belongsService")
public final class BelongsServiceImpl implements BelongsService {

    /** Belongs DAO. */
    @Autowired
    private transient BelongsDao belongsDao;
    /** Employee DAO. */
    @Autowired
    private transient EmployeeDao employeeDao;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Belongs> getAllBelongs() throws AMSException {
        return belongsDao.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> getAllEmployees() throws AMSException {
        return employeeDao.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee getEmployee(final long employeeId) throws AMSException {
        return employeeDao.findById(employeeId);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> findEmployee(final long belongsId) throws AMSException {
        return employeeDao.findByBelongsId(belongsId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addEmployee(final long belongsId, final List<Long> employeeIds)
            throws AMSException {
        Belongs belongs = belongsDao.findById(belongsId);
        for (long employeeId : employeeIds) {
            belongs.getEmployees().add(employeeDao.findById(employeeId));
        }
        belongsDao.update(belongs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void removeEmployee(final long belongsId, final long employeeId)
            throws AMSException {
        Belongs belongs = belongsDao.findById(belongsId);
        belongs.getEmployees().remove(employeeDao.findById(employeeId));
        belongsDao.update(belongs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void rename(final long belongsId, final String name) throws AMSException {
        Belongs belongs = belongsDao.findById(belongsId);
        belongs.setName(name);
        belongsDao.update(belongs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addBelongs(final String name, final long chief) throws AMSException {
        Belongs belongs = new Belongs();
        belongs.setName(name);
        belongs.setBelongsId(belongsDao.getNextBelongsId());
        belongs.setChief(employeeDao.findById(chief));
        belongsDao.save(belongs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void removeBelongs(final long belongsId) throws AMSException {
        belongsDao.delete(belongsId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void integrate(final long base, final List<Long> belongs) throws AMSException {
        // TODO 未実装
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void separate(final long belongs, final String name, final long chief, final List<Long> employeeIds)
            throws AMSException {
        // TODO 未実装
    }

    /**
     * {@link BelongsDao} を設定する.
     * @param belongsDao {@link BelongsDao}
     */
    public void setBelongsDao(final BelongsDao belongsDao) {
        this.belongsDao = belongsDao;
    }

    /**
     * {@link EmployeeDao} を設定する.
     * @param employeeDao {@link EmployeeDao}
     */
    public void setEmployeeDao(final EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

}
