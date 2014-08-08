package jp.co.cyberagent.infosys.ams.service;

import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Belongs;
import jp.co.cyberagent.infosys.ams.model.Employee;

/**
 * 所属管理サービス.
 * @author shimizu.kenji
 *
 */
public interface BelongsService {
    /**
     * 全所属情報を返す.
     * @return 所属情報一覧
     * @throws AMSException 所属情報の取得に失敗した場合
     */
    List<Belongs> getAllBelongs() throws AMSException;
    /**
     * 全所属の全社員情報を返す.
     * @return 社員情報一覧
     * @throws AMSException 社員情報の取得に失敗した場合
     */
    List<Employee> getAllEmployees() throws AMSException;
    /**
     * 指定社員IDの社員情報を返す.
     * @param employeeId 社員ID
     * @return 社員情報
     * @throws AMSException 社員情報の取得に失敗した場合
     */
    Employee getEmployee(long employeeId) throws AMSException;
    /**
     * 指定の所属の社員一覧を返す.
     * @param belongsId 所属ID
     * @return 社員一覧
     * @throws AMSException 社員情報の取得に失敗した場合
     */
    List<Employee> findEmployee(long belongsId) throws AMSException;
    /**
     * 指定の所属に社員を追加する.
     * @param belongsId 所属ID
     * @param employeeIds 社員ID一覧
     * @throws AMSException 社員の追加に失敗した場合
     */
    void addEmployee(long belongsId, List<Long> employeeIds) throws AMSException;

    /**
     * 指定の所属から社員を削除する.
     * @param belongsId 所属ID
     * @param employeeId 社員ID
     * @throws AMSException 社員の削除に失敗した場合
     */
    void removeEmployee(long belongsId, long employeeId) throws AMSException;
    /**
     * 所属名を変更する.
     * @param belongsId 所属ID
     * @param name 新しい所属名
     * @throws AMSException 所属名の変更に失敗した場合
     */
    void rename(long belongsId, String name) throws AMSException;
    /**
     * 所属を追加する.
     * @param name 所属名
     * @param chief 所属長の社員ID
     * @throws AMSException 所属の追加に失敗した場合
     */
    void addBelongs(String name, long chief) throws AMSException;
    /**
     * 所属を削除する.
     * @param belongsId 所属ID
     * @throws AMSException 所属の削除に失敗した場合
     */
    void removeBelongs(long belongsId) throws AMSException;
    /**
     * 所属を統合する.
     * <p>ベースとなる所属にbelongsで指定した所属を統合し１つの所属とする.
     * @param base ベースとなる所属の所属ID
     * @param belongs 取り込む所属の所属ID一覧
     * @throws AMSException 所属の統合に失敗した場合
     */
    void integrate(long base, List<Long> belongs) throws AMSException;
    /**
     * 所属を分離する.
     * @param belongs 分離する所属の所属ID
     * @param name 新しい所属の名前
     * @param chief 新しい所属の所属長
     * @param employeeIds 新しい所属に追加する社員ID一覧
     * @throws AMSException 所属の分離に失敗した場合
     */
    void separate(long belongs, String name, long chief, List<Long> employeeIds) throws AMSException;
}
