package jp.co.cyberagent.infosys.ams.service;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.dao.OperationLogDao;
import jp.co.cyberagent.infosys.ams.model.OperationLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link OperationLogService} の実装クラス.
 * @author shimizu.kenji
 *
 */
@Service("operationLogService")
public final class OperationLogServiceImpl implements OperationLogService {

    /** Operation Log DAO. */
    @Autowired
    private OperationLogDao operationLogDao;
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(final OperationLog log) throws AMSException {
        operationLogDao.save(log);
    }

}
