package com.zomu_t.lib.java.generate.java8.util;

import java.util.Arrays;

import lombok.val;
import lombok.experimental.UtilityClass;

import com.zomu_t.lib.java.generate.java8.model.ClassModel;

/**
 * 基本型ユーティリティ
 *
 * @author takashno
 */
@UtilityClass
public class TypeUtils {

    /**
     * java.lang.Stringのクラスモデルを取得します.
     *
     * @return Stringのクラスモデル
     */
    public ClassModel getStringClassModel() {
        ClassModel cm = new ClassModel();
        cm.setPackageName("java.lang");
        cm.setClassName("String");
        return cm;
    }

    /**
     * java.lang.Shortのクラスモデルを取得します.
     *
     * @return Shortのクラスモデル
     */
    public ClassModel getShortClassModel() {
        ClassModel cm = new ClassModel();
        cm.setPackageName("java.lang");
        cm.setClassName("Short");
        return cm;
    }

    /**
     * プリミティブ型のshortのクラスモデルを取得します.
     *
     * @return shortのクラスモデル
     */
    public ClassModel getPrimitiveShortClassModel() {
        return ClassModel.builder().className("short").primitive(true)
                .build();
    }

    /**
     * java.lang.Integerのクラスモデルを取得します.
     *
     * @return
     */
    public ClassModel getIntegerClassModel() {
        ClassModel cm = new ClassModel();
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
        ClassModel cm = ClassModel.builder().className("int").primitive(true).build();
        return cm;
    }

    /**
     * java.lang.Longのクラスモデルを取得します.
     *
     * @return
     */
    public ClassModel getLongClassModel() {
        ClassModel cm = ClassModel.builder().packageName("java.lang")
                .className("Long").build();
        return cm;
    }

    /**
     * プリミティブ型のlongのクラスモデルを取得します.
     *
     * @return
     */
    public ClassModel getPrimitiveLongClassModel() {
        ClassModel cm = ClassModel.builder().className("long").primitive(true).build();
        return cm;
    }

    /**
     * java.lang.Doubleのクラスモデルを取得します.
     *
     * @return
     */
    public ClassModel getDoubleClassModel() {
        ClassModel cm = ClassModel.builder().packageName("java.lang")
                .className("Double").build();
        return cm;
    }

    /**
     * プリミティブ型のdoubleのクラスモデルを取得します.
     *
     * @return doubleのクラスモデル
     */
    public ClassModel getPrimitiveDoubleClassModel() {
        return ClassModel.builder().className("double").primitive(true)
                .build();
    }

    /**
     * java.lang.Floatのクラスモデルを取得します.
     *
     * @return Floatのクラスモデル
     */
    public ClassModel getFloatClassModel() {
        return ClassModel.builder().packageName("java.lang")
                .className("Float").build();
    }

    /**
     * プリミティブ型のfloatのクラスモデルを取得します.
     *
     * @return
     */
    public ClassModel getPrimitiveFloatClassModel() {
        ClassModel cm = ClassModel.builder().className("float").primitive(true)
                .build();
        return cm;
    }

    /**
     * java.lang.Booleanのクラスモデルを取得します.
     *
     * @return
     */
    public ClassModel getBooleanClassModel() {
        ClassModel cm = ClassModel.builder().packageName("java.lang")
                .className("Boolean").build();
        return cm;
    }

    /**
     * プリミティブ型のbooleanのクラスモデルを取得します.
     *
     * @return
     */
    public ClassModel getPrimitiveBooleanClassModel() {
        ClassModel cm = ClassModel.builder().className("boolean").primitive(true)
                .build();
        return cm;
    }

    /**
     * java.lang.Byteのクラスモデルを取得します.
     *
     * @return
     */
    public ClassModel getByteClassModel() {
        ClassModel cm = ClassModel.builder().packageName("java.lang")
                .className("Byte").build();
        return cm;
    }

    /**
     * プリミティブ型のbyteのクラスモデルを取得します.
     *
     * @return
     */
    public ClassModel getPrimitiveByteClassModel() {
        ClassModel cm = ClassModel.builder().className("byte").primitive(true).build();
        return cm;
    }

    /**
     * java.lang.Characterのクラスモデルを取得します.
     *
     * @return
     */
    public ClassModel getCharacterClassModel() {
        ClassModel cm = ClassModel.builder().packageName("java.lang")
                .className("Character").build();
        return cm;
    }

    /**
     * プリミティブ型のcharのクラスモデルを取得します.
     *
     * @return
     */
    public ClassModel getPrimitiveCharClassModel() {
        ClassModel cm = ClassModel.builder().className("char").primitive(true).build();
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
        ClassModel cm = ClassModel.builder().packageName(packageName)
                .className(className).genericTypes(Arrays.asList(genericTypes))
                .build();
        return cm;
    }

}
