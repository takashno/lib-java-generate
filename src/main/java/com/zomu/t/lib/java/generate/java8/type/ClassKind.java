package com.zomu.t.lib.java.generate.java8.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClassKind {

	CLASS,

	INTERFACE;

	/**
	 * 文字列表現を返却します.
	 */
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
