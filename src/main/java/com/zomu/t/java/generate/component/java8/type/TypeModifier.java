package com.zomu.t.java.generate.component.java8.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * クラスに付与する修飾子を表す列挙型.
 * 
 * @author takashimanozomu
 *
 */
@Getter
@AllArgsConstructor
public enum TypeModifier {

	/** final */
	FINAL,

	/** abstract */
	ABSTRACT;

	/**
	 * 文字列表現を返却します.
	 */
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
