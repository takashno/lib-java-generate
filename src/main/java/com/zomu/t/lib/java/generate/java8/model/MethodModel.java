package com.zomu.t.lib.java.generate.java8.model;

import java.io.Serializable;
import java.util.ArrayList;
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
	@Singular("annotations")
	private List<AnnotationModel> annotations = new ArrayList<>();

	/** アクセス修飾子 */
	private AccessModifier accessModifier;

	/** メソッド修飾子 */
	@Singular("methodModifier")
	private final List<MethodModifier> methodModifier = new ArrayList<>();

	/** 型 */
	private ReturnModel returnType;

	/** 名称 */
	@NonNull
	private String name;

	/** 引数 */
	@Singular("args")
	private final List<ArgModel> args = new ArrayList<>();

	/** Throws */
	@Singular("throwsTypes")
	private final List<ClassModel> throwsTypes = new ArrayList<>();

	/** ロジック */
	private LogicModel logic;

	/** ブロックが不要なメソッドフラグ（このプロパティは内部処理での利用する） */
	private boolean noneBlockMethod = false;

	/**
	 * throwする可能性のある例外を保持しているかどうか判定します.
	 * 
	 * @return
	 */
	public boolean hasThrowTypes() {
		return throwsTypes.size() > 0;
	}

	/**
	 * 抽象メソッドであるかどうか判定します.
	 * 
	 * @return
	 */
	public boolean isAbstractMethod() {
		return methodModifier.stream().anyMatch(
				x -> x == MethodModifier.ABSTRACT);
	}
}
