package jp.co.cyberagent.infosys.ams.service;

import java.io.InputStream;
import java.util.List;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Employee;
import jp.co.cyberagent.infosys.ams.utils.LoadError;


/**
 * アクセス権設定サービス.
 * @author shimizu.kenji
 *
 */
public interface AccessControlService {
    /**
     * アクセス権情報.
     * @author A13413
     *
     */
    class AccessControlSet {
        /** 社員ID. */
        private long employeeId;
        /** 顧客ID一覧. */
        private List<Long> customerIds;
        /**
         * コンストラクタ.
         * @param employeeId 社員ID
         * @param customerIds 顧客ID一覧
         */
        public AccessControlSet(final long employeeId, final List<Long> customerIds) {
            this.employeeId = employeeId;
            this.customerIds = customerIds;
        }
        /**
         * 社員IDを返す.
         * @return 社員ID
         */
        public long getEmployeeId() {
            return employeeId;
        }
        /**
         * 顧客ID一覧を返す.
         * @return 顧客ID一覧
         */
        public List<Long> getCustomerIds() {
            return customerIds;
        }
    }
    /**
     * 社員の顧客へのアクセス権を設定する.
     * <p>customerIdsに含まれていない顧客へのアクセス件は削除する.
     * @param employeeId 社員ID
     * @param customerIds 顧客ID一覧
     * @throws AMSException アクセス権の設定に失敗した場合
     */
    void setAccessControl(long employeeId, List<Long> customerIds) throws AMSException;
    /**
     * 社員の顧客へのアクセス権を設定する.
     * @param customerId 顧客ID
     * @param employeeIds 社員ID一覧
     * @throws AMSException アクセス権の設定に失敗した場合
     */
    void setAccessControlByCustomer(long customerId, List<Long> employeeIds) throws AMSException;
    /**
     * 複数の社員の顧客へのアクセス権を設定する.
     * <p>追加する顧客が、既に該当の社員に設定されている場合は、処理をスキップする.
     * @param employeeIds 社員ID一覧
     * @param addCustomerIds アクセス権を追加する顧客ID一覧
     * @throws AMSException アクセス権の設定に失敗した場合
     */
    void setAccessControl(List<Long> employeeIds, List<Long> addCustomerIds) throws AMSException;
    /**
     * アクセス権情報のExcelファイルを生成する.
     * @param employees 社員情報一覧
     * @return ExcelファイルのInputStream
     * @throws AMSException Excelファイルの生成に失敗した場合
     */
    InputStream createExcelFile(List<Employee> employees) throws AMSException;
    /**
     * アクセス権一覧のExcelファイルを読み込む.
     * <p>読み込んだアクセス権情報を、operatorで処理する.
     * @param input アクセス権一覧ExcelファイルのInputStream
     * @param operater アクセス権情報を処理するoperator
     * @return エラー情報（セル名、エラーメッセージ）一覧、エラーがない場合は空のリスト
     * @throws AMSException Excelファイルの読み込みに失敗した場合
     */
    List<LoadError<String, String>> loadExcelFile(InputStream input, ItemLoadOperator<AccessControlSet> operater)
            throws AMSException;
    /**
     * グループの顧客へのアクセス権を設定する.
     * <p>customerIdsに含まれていない顧客へのアクセス件は削除する.
     * @param groupId グループID
     * @param customerIds 顧客ID一覧
     * @throws AMSException アクセス権の設定に失敗した場合
     */
    void setGroupAccessControl(long groupId, List<Long> customerIds) throws AMSException;
    /**
     * グループの顧客へのアクセス権を設定する.
     * @param customerId 顧客ID
     * @param groupIds グループID一覧
     * @throws AMSException アクセス権の設定に失敗した場合
     */
    void setGroupAccessControlByCustomer(long customerId, List<Long> groupIds) throws AMSException;
}
