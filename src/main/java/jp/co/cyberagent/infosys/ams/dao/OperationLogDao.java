package jp.co.cyberagent.infosys.ams.dao;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.OperationLog;


/**
 * 操作ログ情報にアクセスする機能を提供する.
 * @author shimizu.kenji
 *
 */
public interface OperationLogDao {
    /**
     * 操作ログを追加する.
     * @param log 操作ログ
     * @throws AMSException 操作ログの追加に失敗した場合
     */
    void save(OperationLog log) throws AMSException;
}
