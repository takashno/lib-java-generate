package com.zomu_t.lib.java.generate.java8.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * アクセス修飾子を表す列挙型.
 * 
 * @author takashno
 *
 */
@Getter
@AllArgsConstructor
public enum AccessModifier {

	/** private */
	PRIVATE,

	/** protected */
	PROTECTED,

	/** public */
	PUBLIC;

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
