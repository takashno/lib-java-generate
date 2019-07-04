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
import com.zomu.t.lib.java.generate.java8.type.MethodModifier;

/**
 * メソッドを表すモデル.
 * 
 * @author takashimanozomu
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MethodModel implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** JavaDoc */
	private JavaDocModel javaDoc;

	/** アノテーション */
	@Singular
	private List<AnnotationModel> annotations;

	/** アクセス修飾子 */
	private AccessModifier accessModifier;

	/** メソッド修飾子 */
	@Singular
	private List<MethodModifier> methodModifiers;

	/** 型 */
	private ReturnModel returnType;

	/** 名称 */
	@NonNull
	private String name;

	/** 引数 */
	@Singular
	private List<ArgModel> args;

	/** Throws */
	@Singular
	private List<ClassModel> throwsTypes;

	/** ロジック */
	private LogicModel logic;

	/** ブロックが不要なメソッドフラグ（このプロパティは内部処理での利用する） */
	@Builder.Default
	private boolean noneBlockMethod = false;

	/**
	 * throwする可能性のある例外を保持しているかどうか判定します.
	 * 
	 * @return
	 */
	public boolean hasThrowTypes() {
		return throwsTypes == null ? false : throwsTypes.size() > 0;
	}

	/**
	 * 抽象メソッドであるかどうか判定します.
	 * 
	 * @return
	 */
	public boolean isAbstractMethod() {
		return methodModifiers == null ? false : methodModifiers.stream()
				.anyMatch(x -> x == MethodModifier.ABSTRACT);
	}
}
