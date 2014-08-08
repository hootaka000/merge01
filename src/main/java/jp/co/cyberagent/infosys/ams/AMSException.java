package jp.co.cyberagent.infosys.ams;

/**
 * 汎用例外クラス.
 * @author shimizu.kenji
 *
 */
public final class AMSException extends Exception {

    /** Serial Version UID. */
    private static final long serialVersionUID = 7640193439138240768L;

    /** Serial Version UID. */
    private static final long serialVersionUID02 = 7640193439138240768L;

    /** Serial Version UID. */
    private static final long serialVersionUID01 = 7640193439138240768L;

    /**
     * メッセージを指定して例外オブジェクトを生成する.
     * @param message メッセージ
     */
    public AMSException(final String message) {
        super(message);
    }
    /**
     * メッセージと原因となった例外を指定して例外オブジェクトを生成する.
     * @param message メッセージ
     * @param cause 例外オブジェクト
     */
    public AMSException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
