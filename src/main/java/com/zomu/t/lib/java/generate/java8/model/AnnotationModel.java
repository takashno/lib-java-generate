package com.zomu.t.lib.java.generate.java8.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * アノテーションを表すモデル.
 * 
 * @author takashimanozomu
 * 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnotationModel implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** パッケージ名 */
	@NonNull
	private String packageName;

	/** クラス名 */
	@NonNull
	private String className;

	/** 属性 */
	private final List<AnnotationAttributeModel> attributes = new ArrayList<>();

	/** 最終フラグ */
	private boolean last;

	/**
	 * 属性を保持しているかどうか判定します.
	 * 
	 * @return
	 */
	public boolean hasAttributes() {
		return attributes.size() > 0;
	}
}
