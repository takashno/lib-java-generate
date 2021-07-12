/* Copyright (c) 2017-2021 Nozomu Takashima. */
package com.zomu_t.lib.java.generate.java8.model;

import java.io.Serializable;
import java.util.List;

import lombok.*;

/**
 * ロジック詳細を表すモデル.
 * 
 * @author takashno
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogicDetailModel implements Serializable {

    /** デフォルトシリアルバージョンUID */
    private static final long serialVersionUID = 1L;

    /** テンプレートパス（こちらが指定されていれば優先する） */
    private String templatePath;

    /** スコープ（こちらが指定されていれば優先する） */
    @Singular
    private List<Object> scopes;

}
