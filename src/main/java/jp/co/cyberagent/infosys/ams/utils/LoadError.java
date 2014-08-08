package jp.co.cyberagent.infosys.ams.utils;

/**
 * ファイル読み込み時のエラー情報を管理する.
 * @param <L> エラーの位置情報の型
 * @param <D> エラーの詳細情報の型
 * @author shimizu.kenji
 */
public final class LoadError<L, D> {
    /** エラー位置. */
    private L location;
    /** エラー詳細情報. */
    private D detail;
    /**
     * コンストラクタ.
     * @param location エラー位置
     * @param detail エラー詳細情報
     */
    public LoadError(final L location, final D detail) {
        this.location = location;
        this.detail = detail;
    }
    /**
     * エラー位置を返す.
     * @return エラー位置
     */
    public L getLocation() {
        return location;
    }
    /**
     * 詳細情報を返す.
     * @return エラー詳細情報
     */
    public D getDetail() {
        return detail;
    }
}
