package com.zomu_t.lib.java.generate.common.context;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 変換失敗対象.
 * 
 * @author takashimanozomu
 */
@Data
@AllArgsConstructor
public class ConvertFailureTarget implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** 変換対象 */
	private ConvertTarget convertTarget;

	/** 例外事象 */
	private Throwable cause;

}
