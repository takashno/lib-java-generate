package com.zomu.t.java.generate.component.java8.util;

import lombok.val;
import lombok.experimental.UtilityClass;

import com.zomu.t.java.generate.component.java8.model.ClassModel;
import com.zomu.t.java.generate.component.java8.model.FieldModel;

/**
 * フィールドモデルを提供するユーティリティ.
 * 
 * @author takashimanozomu
 *
 */
@UtilityClass
public class FieldUtils {

	/**
	 * java.lang.String用のフィールドモデルを取得します.
	 * 
	 * @param fieldName
	 *            フィールド名
	 * @param javaDocContents
	 *            JavaDocコンテンツ
	 * @param type
	 *            型
	 * @return
	 */
	public FieldModel getFieldModel(String fieldName, String javaDocContents,
			ClassModel type) {
		val fm = FieldModel.builder().type(type).name(fieldName)
				.javaDoc(JavaDocUtils.getFieldJavaDocModel(javaDocContents))
				.build();
		return fm;
	}

	/**
	 * java.lang.String用のフィールドモデルを取得します.
	 * 
	 * @param fieldName
	 *            フィールド名
	 * @param javaDocContents
	 *            JavaDocコンテンツ
	 * @return
	 */
	public FieldModel getStringFieldModel(String fieldName,
			String javaDocContents) {
		return getFieldModel(fieldName, javaDocContents,
				TypeUtils.getStringClassModel());
	}

	/**
	 * java.lang.Short用のフィールドモデルを取得します.
	 * 
	 * @param fieldName
	 *            フィールド名
	 * @param javaDocContents
	 *            JavaDocコンテンツ
	 * @return
	 */
	public static FieldModel getShortFieldModel(String fieldName,
			String javaDocContents) {
		return getFieldModel(fieldName, javaDocContents,
				TypeUtils.getShortClassModel());
	}

	/**
	 * java.lang.Integer用のフィールドモデルを取得します.
	 * 
	 * @param fieldName
	 *            フィールド名
	 * @param javaDocContents
	 *            JavaDocコンテンツ
	 * @return
	 */
	public static FieldModel getIntegerFieldModel(String fieldName,
			String javaDocContents) {
		return getFieldModel(fieldName, javaDocContents,
				TypeUtils.getIntegerClassModel());
	}

	/**
	 * java.lang.Long用のフィールドモデルを取得します.
	 * 
	 * @param fieldName
	 *            フィールド名
	 * @param javaDocContents
	 *            JavaDocコンテンツ
	 * @return
	 */
	public static FieldModel getLongFieldModel(String fieldName,
			String javaDocContents) {
		return getFieldModel(fieldName, javaDocContents,
				TypeUtils.getLongClassModel());
	}

	/**
	 * java.lang.Long用のフィールドモデルを取得します.
	 * 
	 * @param fieldName
	 *            フィールド名
	 * @param javaDocContents
	 *            JavaDocコンテンツ
	 * @return
	 */
	public static FieldModel getDoubleFieldModel(String fieldName,
			String javaDocContents) {
		return getFieldModel(fieldName, javaDocContents,
				TypeUtils.getDoubleClassModel());
	}

	/**
	 * java.lang.Float用のフィールドモデルを取得します.
	 * 
	 * @param fieldName
	 *            フィールド名
	 * @param javaDocContents
	 *            JavaDocコンテンツ
	 * @return
	 */
	public static FieldModel getFloatFieldModel(String fieldName,
			String javaDocContents) {
		return getFieldModel(fieldName, javaDocContents,
				TypeUtils.getFloatClassModel());
	}

	/**
	 * java.lang.Boolean用のフィールドモデルを取得します.
	 * 
	 * @param fieldName
	 *            フィールド名
	 * @param javaDocContents
	 *            JavaDocコンテンツ
	 * @return
	 */
	public static FieldModel getBooleanFieldModel(String fieldName,
			String javaDocContents) {
		return getFieldModel(fieldName, javaDocContents,
				TypeUtils.getBooleanClassModel());
	}

	/**
	 * java.lang.Boolean用のフィールドモデルを取得します.
	 * 
	 * @param fieldName
	 *            フィールド名
	 * @param javaDocContents
	 *            JavaDocコンテンツ
	 * @return
	 */
	public static FieldModel getByteFieldModel(String fieldName,
			String javaDocContents) {
		return getFieldModel(fieldName, javaDocContents,
				TypeUtils.getByteClassModel());
	}

	/**
	 * java.lang.Character用のフィールドモデルを取得します.
	 * 
	 * @param fieldName
	 *            フィールド名
	 * @param javaDocContents
	 *            JavaDocコンテンツ
	 * @return
	 */
	public static FieldModel getCharacterFieldModel(String fieldName,
			String javaDocContents) {
		return getFieldModel(fieldName, javaDocContents,
				TypeUtils.getCharacterClassModel());
	}

}
