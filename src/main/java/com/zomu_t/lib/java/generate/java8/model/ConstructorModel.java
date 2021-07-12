/* Copyright (c) 2017-2021 Nozomu Takashima. */
package com.zomu_t.lib.java.generate.java8.model;

import com.zomu_t.lib.java.generate.java8.type.AccessModifier;
import com.zomu_t.lib.java.generate.java8.type.MethodModifier;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * コンストラクタを表すモデル.
 *
 * @author takashno
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConstructorModel implements Serializable {

    /**
     * デフォルトシリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * JavaDoc
     */
    private JavaDocModel javaDoc;

    /**
     * アノテーション
     */
    private List<AnnotationModel> annotations = new ArrayList<>();

    /**
     * アクセス修飾子
     */
    private AccessModifier accessModifier;

    /**
     * 引数
     */
    private List<ArgModel> args = new ArrayList<>();

    /**
     * Throws
     */
    private List<ClassModel> throwsTypes = new ArrayList<>();

    /**
     * コンストラクタロジックを自動生成するかのフラグ
     */
    private boolean constructorAutoCreate = false;

    /**
     * ロジック
     */
    private ConstructorLogicModel logic;

    /**
     * throwする可能性のある例外を保持しているかどうか判定します.
     *
     * @return
     */
    public boolean hasThrowTypes() {
        return throwsTypes == null ? false : throwsTypes.size() > 0;
    }

    /**
     * Builder.<br>
     * Lombokと混在しているが、Listのプロパティを後々どうしても追加・変更・削除をしたかったため.
     * Lombokで出力すると、UnmodifiableListとして扱われるため.
     */
    public static class ConstructorModelBuilder {
        private JavaDocModel javaDoc;
        private List<AnnotationModel> annotations = new ArrayList<>();
        private AccessModifier accessModifier;
        private List<ArgModel> args = new ArrayList<>();
        private List<ClassModel> throwsTypes = new ArrayList<>();
        private boolean constructorAutoCreate = false;
        private ConstructorLogicModel logic;

        public ConstructorModelBuilder javaDoc(final JavaDocModel javaDoc) {
            this.javaDoc = javaDoc;
            return this;
        }

        public ConstructorModelBuilder annotations(final List<AnnotationModel> annotations) {
            if (this.annotations == null) {
                this.annotations = new ArrayList<>();
            }
            this.annotations.addAll(annotations);
            return this;
        }

        public ConstructorModelBuilder annotation(final AnnotationModel annotation) {
            if (this.annotations == null) {
                this.annotations = new ArrayList<>();
            }
            this.annotations.add(annotation);
            return this;
        }

        public ConstructorModelBuilder accessModifier(final AccessModifier accessModifier) {
            this.accessModifier = accessModifier;
            return this;
        }

        public ConstructorModelBuilder args(final List<ArgModel> args) {
            if (this.args == null) {
                this.args = new ArrayList<>();
            }
            this.args.addAll(args);
            return this;
        }

        public ConstructorModelBuilder arg(final ArgModel arg) {
            if (this.args == null) {
                this.args = new ArrayList<>();
            }
            this.args.add(arg);
            return this;
        }

        public ConstructorModelBuilder throwsTypes(final List<ClassModel> throwsTypes) {
            if (this.throwsTypes == null) {
                this.throwsTypes = new ArrayList<>();
            }
            this.throwsTypes.addAll(throwsTypes);
            return this;
        }

        public ConstructorModelBuilder throwsType(final ClassModel throwsType) {
            if (this.throwsTypes == null) {
                this.throwsTypes = new ArrayList<>();
            }
            this.throwsTypes.add(throwsType);
            return this;
        }

        public ConstructorModelBuilder constructorAutoCreate(final boolean constructorAutoCreate) {
            this.constructorAutoCreate = constructorAutoCreate;
            return this;
        }

        public ConstructorModelBuilder logic(final ConstructorLogicModel logic) {
            this.logic = logic;
            return this;
        }

        public ConstructorModel build() {
            return new ConstructorModel(this.javaDoc, this.annotations, this.accessModifier, this.args,
                    this.throwsTypes, this.constructorAutoCreate, this.logic);
        }

    }

    public static ConstructorModelBuilder builder() {
        return new ConstructorModelBuilder();
    }
}
