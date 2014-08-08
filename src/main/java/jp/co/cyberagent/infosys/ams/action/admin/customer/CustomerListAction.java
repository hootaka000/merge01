package jp.co.cyberagent.infosys.ams.action.admin.customer;

import jp.co.cyberagent.infosys.ams.AMSException;
import jp.co.cyberagent.infosys.ams.model.Customer;
import jp.co.cyberagent.infosys.ams.service.CustomerService;
import jp.co.cyberagent.infosys.ams.utils.Paging;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 顧客一覧用アクションクラス.
 * @author ootaka
 */
@ParentPackage("admin")
@InterceptorRef("defaultInterceptor")
public final class CustomerListAction extends ActionSupport {

    /** 顧客一覧ページ情報. */
    private Paging<Customer> paging;

    /** 現在ページ番号. */
    private int currentNumber = 1;

    /** フィルター文字列. */
    private String filter;

    /** 顧客管理サービス. */
    @Autowired
    private CustomerService service;

    /**
     * 顧客一覧ページ情報表示処理.
     * @return 成功:success
     * @throws AMSException 顧客情報の取得に失敗した場合
     */
    @Action("/admin/customer/customer-list")
    public String execute() throws AMSException {
        paging = service.getCustomers(filter, currentNumber);

        return SUCCESS;
    }

    /**
     * 顧客一覧ページ情報を取得する.
     * @return paging 顧客一覧ページ情報
     */
    public Paging<Customer> getPaging() {
        return paging;
    }

    /**
     * 顧客一覧ページ情報を設定する.
     * @param paging 顧客一覧ページ情報
     */
    public void setPaging(final Paging<Customer> paging) {
        this.paging = paging;
    }

    /**
     * 現在ページ番号を取得する.
     * @return currentNumber　現在ページ番号
     */
    public int getCurrentNumber() {
        return currentNumber;
    }

    /**
     * 現在ページ番号を設定する.
     * @param currentNumber 現在ページ番号
     */
    public void setCurrentNumber(final int currentNumber) {
        this.currentNumber = currentNumber;
    }

    /**
     * フィルター文字列を取得する.
     * @return filter フィルター文字列
     */
    public String getFilter() {
        return filter;
    }

    /**
     * フィルター文字列を設定する.
     * @param filter フィルター文字列
     */
    public void setFilter(final String filter) {
        this.filter = filter;
    }

}
