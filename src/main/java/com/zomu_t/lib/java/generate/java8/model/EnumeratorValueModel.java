package com.zomu_t.lib.java.generate.java8.model;

import lombok.*;

import java.io.Serializable;

/**
 * 列挙子の値を表すモデル.
 *
 * @author takashno
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnumeratorValueModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文字列属性フラグ
     */
    @Builder.Default
    private boolean stringAttr = true;

    /**
     * 値
     */
    private String value;

    /**
     * 最終フラグ
     */
    @Builder.Default
    private boolean last = false;

}
