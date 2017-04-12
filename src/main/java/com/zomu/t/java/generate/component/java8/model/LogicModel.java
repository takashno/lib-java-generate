package com.zomu.t.java.generate.component.java8.model;

import java.io.Serializable;

import lombok.Data;

/**
 * ロジックを表すモデル.
 * 
 * @author takashimanozomu
 */
@Data
public class LogicModel implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** ロジック（もはやここは自由） */
	private final StringBuilder logic = new StringBuilder();

}
