package jp.co.cyberagent.infosys.ams.dao;

import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Employee;

/**
 * 社員情報へのアクセス機能を提供する.
 * @author shimizu.kenji
 *
 */
public interface EmployeeDao {
    /**
     * 全社員情報を返す.
     * @return 全社員情報
     * @throws AMSException 社員情報の取得に失敗した場合
     */
    List<Employee> findAll() throws AMSException;
    /**
     * 社員IDから社員情報を検索する.
     * @param id 社員ID
     * @return 社員情報、存在しないIDの場合はnull
     * @throws AMSException 社員情報の検索に失敗した場合
     */
    Employee findById(long id) throws AMSException;
    /**
     * 指定の所属の社員情報を検索する.
     * @param id 所属ID
     * @return 社員一覧、存在しない所属IDの場合は空の一覧
     * @throws AMSException 社員情報の検索に失敗した場合
     */
    List<Employee> findByBelongsId(long id) throws AMSException;
    /**
     * 社員情報を更新する.
     * @param employee 社員情報
     * @throws AMSException 社員情報の更新に失敗した場合
     */
    void update(Employee employee) throws AMSException;
}
