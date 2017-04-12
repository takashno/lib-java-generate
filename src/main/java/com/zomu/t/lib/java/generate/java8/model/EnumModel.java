package com.zomu.t.lib.java.generate.java8.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author takashimanozomu
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnumModel implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	private String aaa;

}
