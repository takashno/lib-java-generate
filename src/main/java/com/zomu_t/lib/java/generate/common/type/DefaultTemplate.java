package com.zomu_t.lib.java.generate.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * デフォルトテンプレート定義.
 *
 * @author takashno
 */
@Getter
@AllArgsConstructor
public enum DefaultTemplate {

    /**
     * Java8用デフォルトテンプレートパス
     */
    JAVA8("com/zomu_t/lib/java/generate/java8/templates/JavaClass.mustache");

    /**
     * パス
     */
    private String path;

}
