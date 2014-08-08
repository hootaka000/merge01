package jp.co.cyberagent.infosys.ams.dao;

import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Customer;


/**
 * 顧客情報にアクセスする機能を提供する.
 * @author shimizu.kenji
 *
 */
public interface CustomerDao {
    /**
     * 顧客数を返す.
     * @return 顧客数
     * @throws AMSException 顧客数の取得に失敗した場合
     */
    int getCount() throws AMSException;
    /**
     * 全顧客情報を検索する.
     * @return 全顧客情報
     * @throws AMSException 顧客情報の取得に失敗した場合
     */
    List<Customer> findAll() throws AMSException;
    /**
     * 顧客情報を検索する.
     * @param filter 検索条件（CAON IDまたは企業名に部分一致）
     * @param offset 検索開始位置のオフセット
     * @param limit 検索上限数
     * @return 顧客情報一覧
     * @throws AMSException 顧客の検索に失敗した場合
     */
    List<Customer> find(String filter, int offset, int limit) throws AMSException;
    /**
     * 指定の顧客情報を検索する.
     * @param id 顧客ID
     * @return 顧客情報、存在しない顧客IDの場合はnull
     * @throws AMSException 顧客情報の検索に失敗した場合
     */
    Customer findById(long id) throws AMSException;
    /**
     * CAON IDから顧客情報を検索する.
     * @param caonId CAON ID
     * @return 顧客情報
     * @throws AMSException 顧客情報の検索に失敗した場合
     */
    Customer findByCaonId(String caonId) throws AMSException;
    /**
     * 企業名から顧客情報を検索する.
     * @param companyName 企業名
     * @return 顧客情報
     * @throws AMSException 顧客情報の検索に失敗した場合
     */
    Customer findByCompanyName(String companyName) throws AMSException;
    /**
     * 企業名から顧客情報を検索する(複数).
     * @param companyName 企業名
     * @return 顧客情報
     * @throws AMSException 顧客情報の検索に失敗した場合
     */
    List<Customer> findByCompanyNameAll(String companyName) throws AMSException;
    /**
     * 企業名と事業部名から顧客情報を検索する.
     * @param companyName 企業名
     * @param divisionName 事業部名
     * @return 顧客情報
     * @throws AMSException 顧客情報の検索に失敗した場合
     */
    Customer findByDivisionName(String companyName, String divisionName) throws AMSException;
    /**
     * 企業名と事業部名とサービス名から顧客情報を検索する.
     * @param companyName 企業名
     * @param divisionName 事業部名
     * @param serviceName サービス名
     * @return 顧客情報
     * @throws AMSException 顧客情報の検索に失敗した場合
     */
    Customer findByServiceName(String companyName, String divisionName,
            String serviceName) throws AMSException;
    /**
     * 指定企業の次の事業部IDを返す.
     * @param customer 企業の顧客情報
     * @return 次の事業部ID（3桁の文字列 001～999）
     * @throws AMSException 顧客情報の検索に失敗した場合
     */
    String getNextDivisionId(Customer customer) throws AMSException;
    /**
     * 指定事業部の次のサービス部IDを返す.
     * @param customer 事業部の顧客情報
     * @return 次のサービス部ID（3桁の文字列 001～999）
     * @throws AMSException 顧客情報の検索に失敗した場合
     */
    String getNextServiceId(Customer customer) throws AMSException;
    /**
     * 顧客情報を追加する.
     * @param customer 顧客情報
     * @throws AMSException 顧客情報の追加に失敗した場合
     */
    void save(Customer customer) throws AMSException;
    /**
     * 顧客情報を削除する.
     * @param id 顧客ID
     * @throws AMSException 顧客情報の削除に失敗した場合
     */
    void delete(long id) throws AMSException;
    /**
     * 顧客情報を更新する.
     * @param customer 顧客情報
     * @throws AMSException 顧客情報の更新に失敗した場合
     */
    void update(Customer customer) throws AMSException;
}
