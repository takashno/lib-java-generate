package com.zomu.t.lib.java.generate.java8.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * 
 * 
 * @author takashimanozomu
 *
 */
@Getter
@AllArgsConstructor
public enum MethodModifier {

	/** abstract */
	ABSTRACT,

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
