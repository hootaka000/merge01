package jp.co.cyberagent.infosys.ams.service;

import jp.co.cyberagent.infosys.ams.AMSException;

/**
 * アイテム読み込み時の処理インタフェース.
 * @author shimizu.kenji
 * @param <T> アイテムの型
 */
public interface ItemLoadOperator<T> {
    /**
     * アイテムを処理する.
     * @param item アイテム
     * @throws AMSException 処理に失敗した場合
     */
    void execute(T item) throws AMSException;
}
