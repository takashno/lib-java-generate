package com.zomu.t.lib.java.generate.java8.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Singular;
import lombok.experimental.Delegate;

import com.zomu.t.lib.java.generate.java8.type.ArgModifier;

/**
 * 引数を表すモデル.
 * 
 * @author takashimanozomu
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArgModel implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** 修飾子 */
	private ArgModifier argModifier;

	/** アノテーション */
	@Delegate
	@Singular
	private List<AnnotationModel> annotations;

	/** 型 */
	@NonNull
	private ClassModel type;

	/** 名称 */
	@NonNull
	private String name;

	/** 配列フラグ */
	@Builder.Default
	private boolean array = false;

	/** 最終フラグ */
	@Builder.Default
	private boolean last = false;

}
