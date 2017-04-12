package com.zomu.t.lib.java.generate.java8.util;

import java.util.Arrays;
import java.util.List;

import lombok.val;

import com.zomu.t.lib.java.generate.java8.model.JavaDocAnnotationModel;
import com.zomu.t.lib.java.generate.java8.model.JavaDocModel;

/**
 * 
 * @author takashimanozomu
 *
 */
public final class JavaDocUtils {

	/**
	 * フィールド用のJavaDocモデルを取得します.
	 * 
	 * @param javaDocContents
	 * @return
	 */
	public static JavaDocModel getFieldJavaDocModel(String javaDocContents) {
		val jdm = new JavaDocModel();
		jdm.getMainContents().add(javaDocContents);
		return jdm;
	}

	/**
	 * メソッド用のJavaDocモデルを取得します.
	 * 
	 * @param javaDocContents
	 * @return
	 */
	public static JavaDocModel getMethodJavaDocModel(String javaDocContents,
			JavaDocAnnotationModel... annotationModels) {
		return getMethodJavaDocModel(
				Arrays.asList(new String[] { javaDocContents }),
				annotationModels);
	}

	/**
	 * メソッド用のJavaDocモデルを取得します.
	 * 
	 * @param javaDocContents
	 * @return
	 */
	public static JavaDocModel getMethodJavaDocModel(
			List<String> javaDocContents,
			JavaDocAnnotationModel... annotationModels) {
		val jdm = new JavaDocModel();
		jdm.getMainContents().addAll(javaDocContents);
		if (annotationModels != null) {
			jdm.getAnnotations().addAll(Arrays.asList(annotationModels));
		}
		return jdm;
	}

}
