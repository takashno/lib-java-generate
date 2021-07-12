/* Copyright (c) 2017-2021 Nozomu Takashima. */
package com.zomu_t.lib.java.generate.common.context;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 生成失敗対象.
 * 
 * @author takashno
 */
@Data
@AllArgsConstructor
public class GenerateFailureTarget implements Serializable {

    /** デフォルトシリアルバージョンUID */
    private static final long serialVersionUID = 1L;

    /** 変換対象 */
    private GenerateTarget generateTarget;

    /** 例外事象 */
    private Throwable cause;

}
