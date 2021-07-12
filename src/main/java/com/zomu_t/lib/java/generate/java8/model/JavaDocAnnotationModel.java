/* Copyright (c) 2017-2021 Nozomu Takashima. */
package com.zomu_t.lib.java.generate.java8.model;

import java.io.Serializable;
import java.util.List;

import lombok.*;

/**
 * JavaDocのアノテーションを表すモデル.
 * 
 * @author takashno
 *
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JavaDocAnnotationModel implements Serializable {

    /** デフォルトシリアルバージョンUID */
    private static final long serialVersionUID = 1L;

    /** アノテーション名 */
    @NonNull
    private String name;

    /** コンテンツ */
    @Singular
    private List<String> contents;

}
