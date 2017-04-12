package com.zomu.t.java.generate.component.java8.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * アノテーション属性モデル.
 * 
 * @author takashimanozomu
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnotationAttributeModel implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** 文字列属性フラグ */
	private boolean stringAttr = true;

	/** 名称 */
	@NonNull
	private String name;

	/** 値 */
	@NonNull
	private String value;

	/** 最終フラグ */
	private boolean last;
}
