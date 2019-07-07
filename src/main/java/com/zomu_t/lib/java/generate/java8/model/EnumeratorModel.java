package com.zomu_t.lib.java.generate.java8.model;


import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 列挙子を表すモデル.
 *
 * @author takashno
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @NonNull
    private String name;

    /**
     * 値
     */
    @Singular
    private List<EnumeratorValueModel> values = new ArrayList<>();
}
