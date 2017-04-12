package com.zomu.t.lib.java.generate.java8.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
	private List<AnnotationModel> annotations = new ArrayList<>();

	/** アクセス修飾子 */
	private AccessModifier accessModifier;

	/** メソッド修飾子 */
	private List<MethodModifier> methodModifier = new ArrayList<>();

	/** 型 */
	private ClassModel resultType;

	/** 名称 */
	@NonNull
	private String name;

	/** 引数 */
	private List<ArgModel> args = new ArrayList<>();

	/** Throws */
	private List<ClassModel> throwsTypes = new ArrayList<>();

	/** ロジック */
	private LogicModel logic;

	/**
	 * throwする可能性のある例外を保持しているかどうか判定します.
	 * 
	 * @return
	 */
	public boolean hasThrowTypes() {
		return throwsTypes.size() > 0;
	}

}
