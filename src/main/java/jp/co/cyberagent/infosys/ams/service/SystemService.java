package jp.co.cyberagent.infosys.ams.service;

import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.LinkageSystem;
import jp.co.cyberagent.infosys.ams.model.User;


/**
 * システム管理サービス.
 * @author shimizu.kenji
 *
 */
public interface SystemService {
    /**
     * ログイン認証を行う.
     * @param name ログイン名（社員番号）
     * @param password パスワード
     * @return ログインに成功した場合はユーザー情報を返し、失敗した場合はnullを返す
     * @throws AMSException ログイン認証に失敗した場合
     */
    User login(String name, String password) throws AMSException;
    /**
     * 管理者権限を持つ全ユーザ情報を返す.
     * @return 管理者権限を持つ全ユーザー情報
     * @throws AMSException ユーザー情報の取得に失敗した場合
     */
    List<User> getAdminUsers() throws AMSException;
    /**
     * 管理者権限のユーザーを追加する.
     * @param name ユーザー名
     * @throws AMSException ユーザーの追加に失敗した場合.
     */
    void addAdminUser(String name) throws AMSException;
    /**
     * ユーザーを削除する.
     * @param userId ユーザーID
     * @throws AMSException ユーザーの削除に失敗した場合.
     */
    void removeUser(long userId) throws AMSException;
    /**
     * 全ての連携システム情報を返す.
     * @return 全連携システム情報
     * @throws AMSException 連携システム情報の取得に失敗した場合
     */
    List<LinkageSystem> getLinkageSystems() throws AMSException;
    /**
     * 連携システムを追加する.
     * <p>アクセスキーを生成して、連携システムを追加する.
     * @param userName ユーザー名
     * @param systemName システム名
     * @throws AMSException ユーザーの追加に失敗した場合.
     */
    void addLinkageSystem(String userName, String systemName) throws AMSException;
    /**
     * 連携システムを削除する.
     * @param linkageSystemId 連携システムID
     * @throws AMSException 連携システム情報の削除に失敗した場合.
     */
    void removeLinkageSystem(long linkageSystemId) throws AMSException;
}
