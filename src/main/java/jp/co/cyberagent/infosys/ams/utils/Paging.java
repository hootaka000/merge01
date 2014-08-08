package jp.co.cyberagent.infosys.ams.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * ページングを制御する.
 * @author shimizu.kenji
 * @param <T> アイテムの型
 */
public final class Paging<T> {
    /** １ページのアイテム数の規定値. */
    public static final int DEFAULT_NUMBER_IN_PAGE = 50;
    /** 現在のページ番号. */
    private int currentNumber;
    /** 全アイテム数. */
    private int totalItemSize;
    /** １ページのアイテム数. */
    private int numberInPage = DEFAULT_NUMBER_IN_PAGE;
    /** 現在のページのアイテム一覧. */
    private List<T> items;

    /**
     * コンストラクタ.
     * @param currentPageNumber 現在のページ番号
     * @param totalItemSize 全アイテム数
     */
    public Paging(final int currentPageNumber, final int totalItemSize) {
        this.currentNumber = currentPageNumber;
        this.totalItemSize = totalItemSize;
    }
    /**
     * １ページのアイテム数を返す.
     * @return １ページのアイテム数
     */
    public int getNumberInPage() {
        return numberInPage;
    }
    /**
     * １ページのアイテム数を設定する.
     * @param numberInPage １ページのアイテム数
     */
    public void setNumberInPage(final int numberInPage) {
        this.numberInPage = numberInPage;
    }
    /**
     * 次のページが存在するか判定する.
     * @return 次のページが存在する場合true, 存在しない場合false
     */
    public boolean getNextPage() {
        int totalPages = (int) Math.ceil(totalItemSize / numberInPage);
        return (currentNumber < totalPages);
    }
    /**
     * 前のページが存在するか判定する.
     * @return 前のページが存在する場合true, 存在しない場合false
     */
    public boolean getPrevPage() {
        return (currentNumber > 1);
    }
    /**
     * 現在のページ番号を返す.
     * @return 現在のページ番号
     */
    public int getCurrentPageNumber() {
        return currentNumber;
    }
    /**
     * アイテム一覧を返す.
     * @return アイテム一覧
     */
    public List<T> getItems() {
        return items;
    }
    /**
     * アイテムを追加する.
     * @param item アイテム
     */
    public void addItem(final T item) {
        if (items == null) {
            items = new LinkedList<>();
        }
        items.add(item);
    }
}
