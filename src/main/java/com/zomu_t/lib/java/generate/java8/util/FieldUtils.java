/* Copyright (c) 2017-2021 Nozomu Takashima. */
package com.zomu_t.lib.java.generate.java8.util;

import com.zomu_t.lib.java.generate.common.type.DefaultLogicTemplate;
import com.zomu_t.lib.java.generate.java8.model.*;
import com.zomu_t.lib.java.generate.java8.type.AccessModifier;
import com.zomu_t.lib.java.generate.java8.type.MethodModifier;
import lombok.val;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.text.WordUtils;

/**
 * フィールドモデルを提供するユーティリティ.
 *
 * @author takashno
 */
@UtilityClass
public class FieldUtils {

    /**
     * フィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @param type 型
     * @return
     */
    public FieldModel getFieldModel(String fieldName, String javaDocContents, ClassModel type,
            boolean isSetterAutoCreate, boolean isGetterAutoCreate) {
        val fm = FieldModel.builder()
                .type(type)
                .name(fieldName)
                .javaDoc(JavaDocUtils.getFieldJavaDocModel(javaDocContents))
                .setterAutoCreate(isSetterAutoCreate)
                .getterAutoCreate(isGetterAutoCreate)
                .build();
        return fm;
    }

    /**
     * フィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @param type 型
     * @return
     */
    public FieldModel getFieldModel(String fieldName, String javaDocContents, ClassModel type) {
        val fm = FieldModel.builder()
                .type(type)
                .name(fieldName)
                .javaDoc(JavaDocUtils.getFieldJavaDocModel(javaDocContents))
                .build();
        return fm;
    }

    /**
     * java.lang.String用のフィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @return
     */
    public FieldModel getStringFieldModel(String fieldName, String javaDocContents, boolean isSetterAutoCreate,
            boolean isGetterAutoCreate) {
        return getFieldModel(fieldName, javaDocContents, TypeUtils.getStringClassModel(), isSetterAutoCreate,
                isGetterAutoCreate);
    }

    /**
     * java.lang.String用のフィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @return
     */
    public FieldModel getStringFieldModel(String fieldName, String javaDocContents) {
        return getFieldModel(fieldName, javaDocContents, TypeUtils.getStringClassModel());
    }

    /**
     * java.lang.Short用のフィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @return
     */
    public static FieldModel getShortFieldModel(String fieldName, String javaDocContents, boolean isSetterAutoCreate,
            boolean isGetterAutoCreate) {
        return getFieldModel(fieldName, javaDocContents, TypeUtils.getShortClassModel(), isSetterAutoCreate,
                isGetterAutoCreate);
    }

    /**
     * java.lang.Short用のフィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @return
     */
    public static FieldModel getShortFieldModel(String fieldName, String javaDocContents) {
        return getFieldModel(fieldName, javaDocContents, TypeUtils.getShortClassModel());
    }

    /**
     * java.lang.Integer用のフィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @return
     */
    public static FieldModel getIntegerFieldModel(String fieldName, String javaDocContents, boolean isSetterAutoCreate,
            boolean isGetterAutoCreate) {
        return getFieldModel(fieldName, javaDocContents, TypeUtils.getIntegerClassModel(), isSetterAutoCreate,
                isGetterAutoCreate);
    }

    /**
     * java.lang.Integer用のフィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @return
     */
    public static FieldModel getIntegerFieldModel(String fieldName, String javaDocContents) {
        return getFieldModel(fieldName, javaDocContents, TypeUtils.getIntegerClassModel());
    }

    /**
     * java.lang.Long用のフィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @return
     */
    public static FieldModel getLongFieldModel(String fieldName, String javaDocContents, boolean isSetterAutoCreate,
            boolean isGetterAutoCreate) {
        return getFieldModel(fieldName, javaDocContents, TypeUtils.getLongClassModel(), isSetterAutoCreate,
                isGetterAutoCreate);
    }

    /**
     * java.lang.Long用のフィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @return
     */
    public static FieldModel getLongFieldModel(String fieldName, String javaDocContents) {
        return getFieldModel(fieldName, javaDocContents, TypeUtils.getLongClassModel());
    }

    /**
     * java.lang.Double用のフィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @return
     */
    public static FieldModel getDoubleFieldModel(String fieldName, String javaDocContents, boolean isSetterAutoCreate,
            boolean isGetterAutoCreate) {
        return getFieldModel(fieldName, javaDocContents, TypeUtils.getDoubleClassModel(), isSetterAutoCreate,
                isGetterAutoCreate);
    }

    /**
     * java.lang.Double用のフィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @return
     */
    public static FieldModel getDoubleFieldModel(String fieldName, String javaDocContents) {
        return getFieldModel(fieldName, javaDocContents, TypeUtils.getDoubleClassModel());
    }

    /**
     * java.lang.Float用のフィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @return
     */
    public static FieldModel getFloatFieldModel(String fieldName, String javaDocContents, boolean isSetterAutoCreate,
            boolean isGetterAutoCreate) {
        return getFieldModel(fieldName, javaDocContents, TypeUtils.getFloatClassModel(), isSetterAutoCreate,
                isGetterAutoCreate);
    }

    /**
     * java.lang.Float用のフィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @return
     */
    public static FieldModel getFloatFieldModel(String fieldName, String javaDocContents) {
        return getFieldModel(fieldName, javaDocContents, TypeUtils.getFloatClassModel());
    }

    /**
     * java.lang.Boolean用のフィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @return
     */
    public static FieldModel getBooleanFieldModel(String fieldName, String javaDocContents, boolean isSetterAutoCreate,
            boolean isGetterAutoCreate) {
        return getFieldModel(fieldName, javaDocContents, TypeUtils.getBooleanClassModel(), isSetterAutoCreate,
                isGetterAutoCreate);
    }

    /**
     * java.lang.Boolean用のフィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @return
     */
    public static FieldModel getBooleanFieldModel(String fieldName, String javaDocContents) {
        return getFieldModel(fieldName, javaDocContents, TypeUtils.getBooleanClassModel());
    }

    /**
     * java.lang.Byte用のフィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @return
     */
    public static FieldModel getByteFieldModel(String fieldName, String javaDocContents, boolean isSetterAutoCreate,
            boolean isGetterAutoCreate) {
        return getFieldModel(fieldName, javaDocContents, TypeUtils.getByteClassModel(), isSetterAutoCreate,
                isGetterAutoCreate);
    }

    /**
     * java.lang.Byte用のフィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @return
     */
    public static FieldModel getByteFieldModel(String fieldName, String javaDocContents) {
        return getFieldModel(fieldName, javaDocContents, TypeUtils.getByteClassModel());
    }

    /**
     * java.lang.Character用のフィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @return
     */
    public static FieldModel getCharacterFieldModel(String fieldName, String javaDocContents,
            boolean isSetterAutoCreate, boolean isGetterAutoCreate) {
        return getFieldModel(fieldName, javaDocContents, TypeUtils.getCharacterClassModel(), isSetterAutoCreate,
                isGetterAutoCreate);
    }

    /**
     * java.lang.Character用のフィールドモデルを取得します.
     *
     * @param fieldName フィールド名
     * @param javaDocContents JavaDocコンテンツ
     * @return
     */
    public static FieldModel getCharacterFieldModel(String fieldName, String javaDocContents) {
        return getFieldModel(fieldName, javaDocContents, TypeUtils.getCharacterClassModel());
    }

    /**
     * 指定されたFieldのGetterメソッドを作成.
     *
     * @param fieldModel Fieldを表すモデル
     * @return Methodを表すモデル
     */
    public static MethodModel createGetterMethod(FieldModel fieldModel) {

        LogicModel logicModel = LogicModel.builder()
                .detail(LogicDetailModel.builder()
                        .templatePath(DefaultLogicTemplate.GETTER.getPath())
                        .scope(GetterLogicModel.builder().name(fieldModel.getName()).build())
                        .build())
                .build();

        return MethodModel.builder()
                .javaDoc(JavaDocModel.builder()
                        .mainContent(fieldModel.getName() + "を取得します.")
                        .annotation(
                                JavaDocAnnotationModel.builder().name("return").content(fieldModel.getName()).build())
                        .build())
                .accessModifier(AccessModifier.PUBLIC)
                .returnType(ReturnModel.builder().type(fieldModel.getType()).array(fieldModel.isArray()).build())
                .name("get" + WordUtils.capitalize(fieldModel.getName()))
                .logic(logicModel)
                .build();
    }

    /**
     * 指定されたFieldのSetterメソッドを作成.
     *
     * @param fieldModel Fieldを表すモデル
     * @return Methodを表すモデル
     */
    public static MethodModel createSetterMethod(FieldModel fieldModel) {

        LogicModel logicModel = LogicModel.builder()
                .detail(LogicDetailModel.builder()
                        .templatePath(DefaultLogicTemplate.SETTER.getPath())
                        .scope(SetterLogicModel.builder().name(fieldModel.getName()).build())
                        .build())
                .build();

        return MethodModel.builder()
                .javaDoc(JavaDocModel.builder()
                        .mainContent(fieldModel.getName() + "を設定します.")
                        .annotation(JavaDocAnnotationModel.builder()
                                .name("param")
                                .content(fieldModel.getName())
                                .content("設定する値")
                                .build())
                        .build())
                .accessModifier(AccessModifier.PUBLIC)
                .name("set" + WordUtils.capitalize(fieldModel.getName()))
                .arg(ArgModel.builder().type(fieldModel.getType()).name(fieldModel.getName()).build())
                .logic(logicModel)
                .build();
    }
}
