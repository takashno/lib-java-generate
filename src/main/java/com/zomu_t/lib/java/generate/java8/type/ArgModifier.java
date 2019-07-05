package com.zomu_t.lib.java.generate.java8.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 引数に付与する修飾子を表す列挙型.
 * 
 * @author takashimanozomu
 *
 */
@Getter
@AllArgsConstructor
public enum ArgModifier {

	/** final */
	FINAL;

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
