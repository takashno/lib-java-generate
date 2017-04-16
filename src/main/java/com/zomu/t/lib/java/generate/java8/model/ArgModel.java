package com.zomu.t.lib.java.generate.java8.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import lombok.experimental.Delegate;

import com.zomu.t.lib.java.generate.java8.type.ArgModifier;

/**
 * 
 * @author takashimanozomu
 *
 */
@Data
@Builder
@AllArgsConstructor
public class ArgModel implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** 修飾子 */
	private ArgModifier argModifier;

	/** アノテーション */
	@Delegate
	@Singular("annotations")
	private final List<AnnotationModel> annotations = new ArrayList<>();

	/** 型 */
	@NonNull
	private ClassModel type;

	/** 名称 */
	@NonNull
	private String name;

	/** 配列フラグ */
	private boolean array = false;

	/** 最終フラグ */
	private boolean last = false;

}
