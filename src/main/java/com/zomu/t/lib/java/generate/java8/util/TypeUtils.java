package com.zomu.t.lib.java.generate.java8.util;

import java.util.Arrays;

import lombok.val;
import lombok.experimental.UtilityClass;

import com.zomu.t.lib.java.generate.java8.model.ClassModel;

/**
 * 基本型ユーティリティ
 * 
 * @author takashimanozomu
 *
 */
@UtilityClass
public class TypeUtils {

	/**
	 * java.lang.Stringのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getStringClassModel() {
		val cm = new ClassModel();
		cm.setPackageName("java.lang");
		cm.setClassName("String");
		return cm;
	}

	/**
	 * java.lang.Shortのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getShortClassModel() {
		val cm = new ClassModel();
		cm.setPackageName("java.lang");
		cm.setClassName("Short");
		return cm;
	}

	/**
	 * java.lang.Integerのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getIntegerClassModel() {
		val cm = new ClassModel();
		cm.setPackageName("java.lang");
		cm.setClassName("Integer");
		return cm;
	}

	/**
	 * java.lang.Longのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getLongClassModel() {
		val cm = new ClassModel();
		cm.setPackageName("java.lang");
		cm.setClassName("Long");
		return cm;
	}

	/**
	 * java.lang.Doubleのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getDoubleClassModel() {
		val cm = new ClassModel();
		cm.setPackageName("java.lang");
		cm.setClassName("Double");
		return cm;
	}

	/**
	 * java.lang.Floatのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getFloatClassModel() {
		val cm = new ClassModel();
		cm.setPackageName("java.lang");
		cm.setClassName("Float");
		return cm;
	}

	/**
	 * java.lang.Booleanのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getBooleanClassModel() {
		val cm = new ClassModel();
		cm.setPackageName("java.lang");
		cm.setClassName("Boolean");
		return cm;
	}

	/**
	 * java.lang.Byteのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getByteClassModel() {
		val cm = new ClassModel();
		cm.setPackageName("java.lang");
		cm.setClassName("Byte");
		return cm;
	}

	/**
	 * java.lang.Characterのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getCharacterClassModel() {
		val cm = new ClassModel();
		cm.setPackageName("java.lang");
		cm.setClassName("Character");
		return cm;
	}

	/**
	 * 
	 * @param packageName
	 * @param className
	 * @param genericTypes
	 * @return
	 */
	public ClassModel getGenericClassModel(String packageName,
			String className, ClassModel... genericTypes) {

		val cm = new ClassModel();
		cm.setPackageName(packageName);
		cm.setClassName(className);
		cm.getGenericTypes().addAll(Arrays.asList(genericTypes));
		return cm;
	}

}
