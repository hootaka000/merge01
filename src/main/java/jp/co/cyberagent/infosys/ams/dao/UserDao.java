package jp.co.cyberagent.infosys.ams.dao;

import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.User;


/**
 * ユーザー情報へのアクセス機能を提供する.
 * @author shimizu.kenji
 *
 */
public interface UserDao {
    /**
     * ユーザー名からユーザー情報を検索する.
     * @param name ユーザー名
     * @return ユーザー情報、存在しないユーザー名の場合はnull
     * @throws AMSException ユーザー情報の検索に失敗した場合
     */
    User findByName(String name) throws AMSException;
    /**
     * 管理者権限を持つすべてのユーザー情報を検索する.
     * @return ユーザー情報一覧
     * @throws AMSException ユーザー情報の検索に失敗した場合
     */
    List<User> findAllAdmin() throws AMSException;
    /**
     * ユーザー情報を追加する.
     * @param user ユーザー情報
     * @throws AMSException ユーザー情報の追加に失敗した場合
     */
    void save(User user) throws AMSException;
    /**
     * ユーザー情報を削除する.
     * @param id ユーザーID
     * @throws AMSException ユーザー情報の削除に失敗した場合
     */
    void delete(long id) throws AMSException;
}
