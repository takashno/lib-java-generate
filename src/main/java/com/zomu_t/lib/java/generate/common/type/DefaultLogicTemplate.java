package com.zomu_t.lib.java.generate.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * デフォルトロジックテンプレート.
 *
 * @author takashno
 */
@Getter
@AllArgsConstructor
public enum DefaultLogicTemplate {

    /**
     * Getterロジックテンプレートパス
     */
    GETTER("com/zomu_t/lib/java/generate/java8/templates/GetterLogic.mustache"),

    /**
     * Setterロジックテンプレートパス
     */
    SETTER("com/zomu_t/lib/java/generate/java8/templates/SetterLogic.mustache"),

    /**
     * コンストラクタロジックテンプレートパス
     */
    CONSTRUCTOR("com/zomu_t/lib/java/generate/java8/templates/ConstructorLogic.mustache");

    /**
     * パス
     */
    private String path;

}
