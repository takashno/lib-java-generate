package com.zomu_t.lib.java.generate.common.exception;

/**
 * Java生成例外.
 *
 * @author takashno
 */
public class JavaGenerateException extends RuntimeException {

    /**
     * デフォルトシリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ.
     *
     * @param message
     * @param cause
     */
    public JavaGenerateException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタ.
     *
     * @param message
     */
    public JavaGenerateException(String message) {
        super(message);
    }

}
