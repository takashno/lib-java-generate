package com.zomu_t.lib.java.generate.common.exception;

/**
 * Java変換例外.
 * 
 * @author takashimanozomu
 *
 */
public class JavaConverterException extends RuntimeException {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 * 
	 * @param message
	 * @param cause
	 */
	public JavaConverterException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * コンストラクタ.
	 * 
	 * @param message
	 */
	public JavaConverterException(String message) {
		super(message);
	}

}
