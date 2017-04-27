package com.zomu.t.lib.java.generate.java8.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Singular;

import com.zomu.t.lib.java.generate.java8.type.AccessModifier;
import com.zomu.t.lib.java.generate.java8.type.FieldModifier;

/**
 * フィールドを表すモデル.
 * 
 * @author takashimanozomu
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldModel implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** JavaDoc */
	private JavaDocModel javaDoc;

	/** アノテーション */
	@Singular
	private List<AnnotationModel> annotations;

	/** アクセス修飾子 */
	@Builder.Default
	private AccessModifier accessModifier = AccessModifier.PRIVATE;

	/** フィールド修飾子 */
	@Singular
	private List<FieldModifier> fieldModifiers;

	/** 型 */
	@NonNull
	private ClassModel type;

	/** 名称 */
	@NonNull
	private String name;

	/** 初期値 */
	private String initializationValue;

	/** 配列フラグ */
	private boolean array = false;

}
