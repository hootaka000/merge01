package jp.co.cyberagent.infosys.ams.dao;

import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Group;


/**
 * グループ情報にアクセス機能を提供する.
 * @author shimizu.kenji
 *
 */
public interface GroupDao {
    /**
     * グループIDからグループ情報を検索する.
     * @param id グループID
     * @return グループ情報、存在しないIDの場合はnull
     * @throws AMSException グループ情報の検索に失敗した場合
     */
    Group findById(long id) throws AMSException;
    /**
     * 全グループ情報を返す.
     * @return 全グループ情報
     * @throws AMSException グループ情報の取得に失敗した場合
     */
    List<Group> findAll() throws AMSException;
    /**
     * グループを追加する.
     * @param group グループ情報
     * @throws AMSException グループの追加に失敗した場合
     */
    void save(Group group) throws AMSException;
    /**
     * グループ情報を更新する.
     * @param group グループ情報
     * @throws AMSException グループ情報の更新に失敗した場合
     */
    void update(Group group) throws AMSException;
    /**
     * グループ情報を削除する.
     * @param id グループID
     * @throws AMSException グループ情報の削除に失敗した場合
     */
    void delete(long id) throws AMSException;
    /**
     * 次のグループID（6桁の文字列 G00000～G99999）を返す.
     * @return グループID
     */
    String getNextGroupId();
}
