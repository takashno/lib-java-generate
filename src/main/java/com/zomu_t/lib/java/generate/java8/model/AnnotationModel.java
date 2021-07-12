/* Copyright (c) 2017-2021 Nozomu Takashima. */
package com.zomu_t.lib.java.generate.java8.model;

import java.io.Serializable;
import java.util.List;

import lombok.*;

/**
 * アノテーションを表すモデル.
 * 
 * @author takashno
 * 
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnotationModel implements Serializable {

    /** デフォルトシリアルバージョンUID */
    private static final long serialVersionUID = 1L;

    /** パッケージ名 */
    @NonNull
    private String packageName;

    /** クラス名 */
    @NonNull
    private String className;

    /** 属性 */
    @Singular
    private List<AnnotationAttributeModel> attributes;

    /** 最終フラグ */
    @Builder.Default
    private boolean last = false;

    /**
     * 属性を保持しているかどうか判定します.
     * 
     * @return
     */
    public boolean hasAttributes() {
        return attributes == null ? false : attributes.size() > 0;
    }
}
