package jp.co.cyberagent.infosys.ams.dao;

import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.LinkageSystem;


/**
 * 連携システム情報にアクセスする機能を提供する.
 * @author shimizu.kenji
 *
 */
public interface LinkageSystemDao {
    /**
     * 全ての連携システム情報を返す.
     * @return 連携システム情報一覧
     * @throws AMSException 連携システム情報の取得に失敗した場合
     */
    List<LinkageSystem> findAll() throws AMSException;
    /**
     * 連携システム情報を追加する.
     * @param linkageSystem 連携システム情報
     * @throws AMSException 連携システム情報の追加に失敗した場合
     */
    void save(LinkageSystem linkageSystem) throws AMSException;
    /**
     * 連携システム情報を削除する.
     * @param id 連携システムID
     * @throws AMSException 連携システム情報の削除に失敗した場合
     */
    void delete(long id) throws AMSException;
}
