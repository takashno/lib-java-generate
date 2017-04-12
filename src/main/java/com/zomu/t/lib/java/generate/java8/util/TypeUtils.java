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
	 * プリミティブ型のshortのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getPrimitiveShortClassModel() {
		val cm = ClassModel.builder().className("short").primitive(true)
				.build();
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
	 * プリミティブ型のintのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getPrimitiveIntClassModel() {
		val cm = ClassModel.builder().className("int").primitive(true).build();
		return cm;
	}

	/**
	 * java.lang.Longのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getLongClassModel() {
		val cm = ClassModel.builder().packageName("java.lang")
				.className("Long").build();
		return cm;
	}

	/**
	 * プリミティブ型のlongのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getPrimitiveLongClassModel() {
		val cm = ClassModel.builder().className("long").primitive(true).build();
		return cm;
	}

	/**
	 * java.lang.Doubleのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getDoubleClassModel() {
		val cm = ClassModel.builder().packageName("java.lang")
				.className("Double").build();
		return cm;
	}

	/**
	 * プリミティブ型のdoubleのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getPrimitiveDoubleClassModel() {
		val cm = ClassModel.builder().className("double").primitive(true)
				.build();
		return cm;
	}

	/**
	 * java.lang.Floatのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getFloatClassModel() {
		val cm = ClassModel.builder().packageName("java.lang")
				.className("Float").build();
		return cm;
	}

	/**
	 * プリミティブ型のfloatのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getPrimitiveFloatClassModel() {
		val cm = ClassModel.builder().className("float").primitive(true)
				.build();
		return cm;
	}

	/**
	 * java.lang.Booleanのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getBooleanClassModel() {
		val cm = ClassModel.builder().packageName("java.lang")
				.className("Boolean").build();
		return cm;
	}

	/**
	 * プリミティブ型のbooleanのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getPrimitiveBooleanClassModel() {
		val cm = ClassModel.builder().className("boolean").primitive(true)
				.build();
		return cm;
	}

	/**
	 * java.lang.Byteのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getByteClassModel() {
		val cm = ClassModel.builder().packageName("java.lang")
				.className("Byte").build();
		return cm;
	}

	/**
	 * プリミティブ型のbyteのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getPrimitiveByteClassModel() {
		val cm = ClassModel.builder().className("byte").primitive(true).build();
		return cm;
	}

	/**
	 * java.lang.Characterのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getCharacterClassModel() {
		val cm = ClassModel.builder().packageName("java.lang")
				.className("Character").build();
		return cm;
	}

	/**
	 * プリミティブ型のcharのクラスモデルを取得します.
	 * 
	 * @return
	 */
	public ClassModel getPrimitiveCharClassModel() {
		val cm = ClassModel.builder().className("char").primitive(true).build();
		return cm;
	}

	/**
	 * 指定したパッケージと型と総称型を持つクラスモデルを取得します.
	 * 
	 * @param packageName
	 * @param className
	 * @param genericTypes
	 * @return
	 */
	public ClassModel getGenericClassModel(String packageName,
			String className, ClassModel... genericTypes) {
		val cm = ClassModel.builder().packageName(packageName)
				.className(className).build();
		cm.getGenericTypes().addAll(Arrays.asList(genericTypes));
		return cm;
	}

}
