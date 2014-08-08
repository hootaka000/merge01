package jp.co.cyberagent.infosys.ams.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Customer;
import jp.co.cyberagent.infosys.ams.utils.LoadError;
import jp.co.cyberagent.infosys.ams.utils.Paging;


/**
 * 顧客管理サービス.
 * @author shimizu.kenji
 *
 */
public interface CustomerService {
    /**
     * 全顧客情報を返す.
     * @return 全顧客情報
     * @throws AMSException 顧客情報の取得に失敗した場合
     */
    List<Customer> getAllCustomers() throws AMSException;
    /**
     * 指定ページに表示する顧客情報の一覧をかえす.
     * @param filter CAON IDまたは企業名のフィルター文字列（部分一致でフィルターする）
     * @param pageNumber ページ番号
     * @return 顧客情報一覧を含むページング情報
     * @throws AMSException 顧客情報の取得に失敗した場合
     */
    Paging<Customer> getCustomers(String filter, int pageNumber) throws AMSException;
    /**
     * 指定の顧客情報を返す.
     * @param id 顧客ID
     * @return 顧客情報、存在しない顧客IDの場合はnull
     * @throws AMSException 顧客情報の取得に失敗した場合
     */
    Customer getCustomer(long id) throws AMSException;
    /**
     * 顧客情報を追加する.
     * <p>CAON IDが指定されていない場合、顧客情報を新規に登録する.
     * <p>CAON IDに対応する企業が登録されていない場合、顧客情報を新規に登録する.ただし、同名の企業名がある場合はエラーとする.
     * <p>CAON IDに対応する企業が登録されている場合、義業部またはサービスを新規に登録する.
     * @param customer 顧客情報
     * @throws AMSException 顧客情報の追加に失敗した場合
     */
    void addCustomer(Customer customer) throws AMSException;
    /**
     * CAON IDを更新する.
     * @param id 顧客ID
     * @param caonId CAON ID
     * @throws AMSException CAON IDの更新に失敗した場合
     */
    void updateCaonId(long id, String caonId) throws AMSException;
    /**
     * CAON IDから顧客情報を検索する.
     * @param caonId CAON ID
     * @return 顧客情報、存在しないCAON IDの場合はnull
     * @throws AMSException 顧客情報の取得に失敗した場合
     */
    Customer getCustomer(String caonId) throws AMSException;
    /**
     * 企業名、事業部名、サービス名から顧客情報を検索する.
     * <p>事業部名、サービス名がない場合はnullを指定する
     * @param companyName 企業名
     * @param divisionName 事業部名 or null
     * @param serviceName サービス名 or null
     * @return 顧客情報、指定の名前の顧客情報が存在しない場合はnull
     * @throws AMSException 顧客情報の取得に失敗した場合
     */
    Customer getCustomer(String companyName, String divisionName, String serviceName) throws AMSException;
    /**
     * 顧客情報を削除する.
     * @param id 顧客ID
     * @throws AMSException 顧客情報の削除に失敗した場合
     */
    void removeCustomer(long id) throws AMSException;
    /**
     * 顧客情報一覧CSVファイルを読み込み１顧客情報ごとに処理する.
     * @param input CSVファイルのInputStream
     * @param operator 操作オブジェクト
     * @return エラー情報（エラー行数、エラーメッセージ）一覧、エラーがない場合は空のリスト
     * @throws AMSException CSVファイルの読み込みに失敗した場合
     */
    List<LoadError<Integer, String>> loadCsv(InputStream input, ItemLoadOperator<Customer> operator)
            throws AMSException;
    /**
     * 顧客情報をCSVファイルに出力する.
     * @param customers 顧客情報一覧
     * @return CSVファイルのContent-Length,CSVファイルのInputStream
     * @throws AMSException CSVファイルの出力に失敗した場合
     */
    Map<Long, InputStream> createCsv(List<Customer> customers) throws AMSException;
}
