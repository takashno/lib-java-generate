package com.zomu_t.lib.java.generate.java8.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * フィールドに付与する修飾子を表す列挙型.
 * 
 * @author takashimanozomu
 *
 */
@Getter
@AllArgsConstructor
public enum FieldModifier {

	/** static */
	STATIC,

	/** final */
	FINAL,

	/** transient */
	TRANSIENT,

	/** volatile */
	VOLATILE;

	/**
	 * 文字列表現を返却します.
	 * 
	 * @return 文字列表現
	 */
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
