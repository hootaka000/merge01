package jp.co.cyberagent.infosys.ams.service;

import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Group;


/**
 * グループ管理サービス.
 * @author shimizu.kenji
 *
 */
public interface GroupService {
    /**
     * 全グループ情報を返す.
     * @return 全グループ情報
     * @throws AMSException グループ情報の取得に失敗した場合
     */
    List<Group> getAll() throws AMSException;
    /**
     * グループ情報を返す.
     * @param id グループID
     * @return グループ情報
     * @throws AMSException グループ情報の取得に失敗した場合
     */
    Group getGroup(long id) throws AMSException;
    /**
     * グループに社員を追加する.
     * @param groupId グループID
     * @param employeeId 社員ID
     * @throws AMSException 社員の追加に失敗した場合
     */
    void addEmployee(long groupId, long employeeId) throws AMSException;
    /**
     * グループから社員を削除する.
     * @param groupId グループID
     * @param employeeId 社員ID
     * @throws AMSException 社員の削除に失敗した場合
     */
    void removeEmployee(long groupId, long employeeId) throws AMSException;
    /**
     * グループ名を変更する.
     * @param groupId グループID
     * @param name 新しいグループ名
     * @throws AMSException グループ名の変更に失敗した場合
     */
    void rename(long groupId, String name) throws AMSException;
    /**
     * グループを追加する.
     * @param name グループ名
     * @throws AMSException グループの追加に失敗した場合
     */
    void addGroup(String name) throws AMSException;
    /**
     * グループを削除する.
     * @param id グループID
     * @throws AMSException グループの削除に失敗した場合
     */
    void removeGroup(long id) throws AMSException;
}
