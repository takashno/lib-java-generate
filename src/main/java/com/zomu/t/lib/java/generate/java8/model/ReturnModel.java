package com.zomu.t.lib.java.generate.java8.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * 返却値を表すモデル.
 * 
 * @author takashimanozomu
 *
 */
@Data
@Builder
@AllArgsConstructor
public class ReturnModel implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** 型 */
	@NonNull
	private ClassModel type;

	/** 配列フラグ */
	private boolean array = false;

}