package com.zomu_t.lib.java.generate.java8.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * メソッドに付与する修飾子を表す列挙型.
 *
 * @author takashimanozomu
 */
@Getter
@AllArgsConstructor
public enum MethodModifier {

    /**
     * default
     */
    DEFAULT,

    /**
     * abstract
     */
    ABSTRACT,

    /**
     * static
     */
    STATIC,

    /**
     * final
     */
    FINAL;

    /**
     * 文字列表現を返却します.
     *
     * @return 文字列表現
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

}
