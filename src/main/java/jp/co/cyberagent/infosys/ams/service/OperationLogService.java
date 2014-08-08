package jp.co.cyberagent.infosys.ams.service;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.OperationLog;


/**
 * 操作ログ管理サービス.
 * @author shimizu.kenji
 *
 */
public interface OperationLogService {
    /**
     * 操作ログを追加する.
     * @param log 操作ログ情報
     * @throws AMSException 操作ログの追加に失敗した場合
     */
    void add(OperationLog log) throws AMSException;
}
