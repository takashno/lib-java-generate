/* Copyright (c) 2017-2021 Nozomu Takashima. */
package com.zomu_t.lib.java.generate.java8.model;

import java.io.Serializable;
import java.util.List;

import com.zomu_t.lib.java.generate.java8.type.AccessModifier;
import com.zomu_t.lib.java.generate.java8.type.FieldModifier;
import lombok.*;

/**
 * フィールドを表すモデル.
 *
 * @author takashno
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldModel implements Serializable {

    /**
     * デフォルトシリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * JavaDoc
     */
    private JavaDocModel javaDoc;

    /**
     * アノテーション
     */
    @Singular
    private List<AnnotationModel> annotations;

    /**
     * アクセス修飾子
     */
    @Builder.Default
    private AccessModifier accessModifier = AccessModifier.PRIVATE;

    /**
     * フィールド修飾子
     */
    @Singular
    private List<FieldModifier> fieldModifiers;

    /**
     * 型
     */
    @NonNull
    private ClassModel type;

    /**
     * 名称
     */
    @NonNull
    private String name;

    /**
     * 初期値
     */
    private String initializationValue;

    /**
     * 配列フラグ
     */
    @Builder.Default
    private boolean array = false;

    /**
     * コンストラクタで初期化するかどうかのフラグ
     */
    @Builder.Default
    private boolean initConstructor = false;

    /**
     * Getter作成フラグ
     */
    @Builder.Default
    private boolean getterAutoCreate = false;

    /**
     * Setter作成フラグ
     */
    @Builder.Default
    private boolean setterAutoCreate = false;
}
