/* Copyright (c) 2017-2021 Nozomu Takashima. */
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
    private String name;

    /**
     * 値
     */
    private List<EnumeratorValueModel> values = new ArrayList<>();

    /**
     * 最終フラグ
     */
    private boolean last = false;

    /**
     * Builder.<br>
     * Lombokと混在しているが、Listのプロパティを後々どうしても追加・変更・削除をしたかったため.
     * Lombokで出力すると、UnmodifiableListとして扱われるため.
     */
    public static class EnumeratorModelBuilder {
        private JavaDocModel javaDoc;
        private String name;
        private List<EnumeratorValueModel> values = new ArrayList<>();
        private boolean last = false;

        public EnumeratorModelBuilder javaDoc(JavaDocModel javaDoc) {
            this.javaDoc = javaDoc;
            return this;
        }

        public EnumeratorModelBuilder name(String name) {
            this.name = name;
            return this;
        }

        public EnumeratorModelBuilder value(EnumeratorValueModel value) {
            this.values.add(value);
            return this;
        }

        public EnumeratorModelBuilder values(List<EnumeratorValueModel> values) {
            this.values = values;
            return this;
        }

        public EnumeratorModelBuilder last(boolean last) {
            this.last = last;
            return this;
        }

        public EnumeratorModel build() {
            return new EnumeratorModel(javaDoc, name, values, last);
        }
    }

    public static EnumeratorModelBuilder builder() {
        return new EnumeratorModelBuilder();
    }
}
