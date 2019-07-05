package com.zomu_t.lib.java.generate.java8.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 列挙子を表すモデル.
 *
 * @author takashno
 */
@Getter
@Setter
public class EnumeratorModel implements Serializable {

    /**
     * デフォルトシリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * JavaDoc
     */
    private JavaDocModel javaDoc;

    /**
     * 名称
     */
    private String name;

    /**
     * 値
     */
    private List<EnumeratorValueModel> values;
}
