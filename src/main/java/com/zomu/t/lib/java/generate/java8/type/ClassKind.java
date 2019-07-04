package com.zomu.t.lib.java.generate.java8.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * クラスの種類.
 * 
 * @author takashimanozomu
 *
 */
@Getter
@AllArgsConstructor
public enum ClassKind {

	/** class */
	CLASS,

	/** enum */
	ENUM,

	/** interface */
	INTERFACE;

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
