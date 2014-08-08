package jp.co.cyberagent.infosys.ams.dao;

import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Belongs;


/**
 * 所属情報にアクセスする機能を提供する.
 * @author shimizu.kenji
 *
 */
public interface BelongsDao {
    /**
     * 所属IDから所属情報を検索する.
     * @param id 所属ID
     * @return 所属情報、存在しない所属IDの場合はnull
     * @throws AMSException 所属情報の検索に失敗した場合
     */
    Belongs findById(long id) throws AMSException;
    /**
     * 全所属情報を返す.
     * @return 所属情報一覧
     * @throws AMSException 所属情報の取得に失敗した場合
     */
    List<Belongs> findAll() throws AMSException;
    /**
     * 所属情報を更新する.
     * @param belongs 所属情報
     * @throws AMSException 所属情報の更新に失敗した場合
     */
    void update(Belongs belongs) throws AMSException;
    /**
     * 所属情報を追加する.
     * @param belongs 所属情報
     * @throws AMSException 所属情報の追加に失敗した場合
     */
    void save(Belongs belongs) throws AMSException;
    /**
     * 所属情報を削除する.
     * @param id 所属ID
     * @throws AMSException 所属情報の削除に失敗した場合
     */
    void delete(long id) throws AMSException;
    /**
     * 次の所属ID（6桁の文字列 B00000～B99999）を返す.
     * @return 所属ID
     */
    String getNextBelongsId();
}
