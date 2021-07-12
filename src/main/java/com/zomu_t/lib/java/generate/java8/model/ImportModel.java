/* Copyright (c) 2017-2021 Nozomu Takashima. */
package com.zomu_t.lib.java.generate.java8.model;

import java.io.Serializable;

import lombok.*;

/**
 * インポートを表すモデル.
 * 
 * @author takashno
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImportModel implements Serializable {

    /** デフォルトシリアルバージョンUID */
    private static final long serialVersionUID = 1L;

    /** パッケージ名 */
    @NonNull
    private String packageName;

    /** staticインポートフラグ */
    private boolean staticImport;

    /** クラス名 */
    private String className;

    /** メソッド名 */
    private String methodName;

    /** ワイルドカードフラグ */
    @Builder.Default
    private boolean wildcard = false;

}
