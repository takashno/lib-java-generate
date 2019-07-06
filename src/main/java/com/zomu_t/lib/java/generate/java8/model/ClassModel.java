package com.zomu_t.lib.java.generate.java8.model;

import com.zomu_t.lib.java.generate.common.model.Clazz;
import com.zomu_t.lib.java.generate.java8.type.AccessModifier;
import com.zomu_t.lib.java.generate.java8.type.ClassKind;
import com.zomu_t.lib.java.generate.java8.type.TypeModifier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Javaクラスを表すモデル.
 *
 * @author takashno
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassModel implements Clazz, Serializable {

    /**
     * デフォルトシリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ヘッダー部コメント
     */
    private String commentHeader;

    /**
     * パッケージ名
     */
    private String packageName;

    /**
     * インポート
     */
    private List<ImportModel> imports = new ArrayList<>();

    /**
     * クラスに対するJavaDoc
     */
    private JavaDocModel javaDoc;

    /**
     * クラスに対するアノテーション
     */
    private List<AnnotationModel> annotations = new ArrayList<>();

    /**
     * アクセス修飾子
     */
    private AccessModifier accessModifier = AccessModifier.PUBLIC;

    /**
     * クラス修飾子
     */
    private TypeModifier typeModifier;

    /**
     * クラス種別
     */
    private ClassKind classKind = ClassKind.CLASS;

    /**
     * クラス名
     */
    private String className;

    /**
     * スーパークラス
     */
    private ClassModel superClass;

    /**
     * 実装インタフェース
     */
    private List<ClassModel> implementsClasses = new ArrayList<>();

    /**
     * 総称型
     */
    private List<ClassModel> genericTypes = new ArrayList<>();

    /**
     * 列挙子
     */
    private List<EnumeratorModel> enumerators = new ArrayList<>();

    /**
     * フィールド
     */
    private List<FieldModel> fields = new ArrayList<>();

    /**
     * メソッド
     */
    private List<MethodModel> methods = new ArrayList<>();

    /**
     * プリミティブ型フラグ
     */
    private boolean primitive = false;

    /**
     * 最終フラグ
     */
    private boolean last = false;

    /**
     * 総称型を保持しているかどうか判定します.
     *
     * @return
     */
    public boolean hasGenericTypes() {
        return genericTypes == null ? false : genericTypes.size() > 0;
    }

    /**
     * 実装インタフェースを保持しているかどうか判定します.
     *
     * @return
     */
    public boolean hasImplementsClasses() {
        return implementsClasses == null ? false : implementsClasses.size() > 0;
    }


    /**
     * Builder.<br>
     * Lombokと混在しているが、Listのプロパティを後々どうしても追加・変更・削除をしたかったため.
     * Lombokで出力すると、UnmodifiableListとして扱われるため.
     */
    public static class ClassModelBuilder {
        private String commentHeader;
        private String packageName;
        private List<ImportModel> imports = new ArrayList<>();
        private JavaDocModel javaDoc;
        private List<AnnotationModel> annotations = new ArrayList<>();
        private AccessModifier accessModifier = AccessModifier.PUBLIC;
        private TypeModifier typeModifier;
        private ClassKind classKind = ClassKind.CLASS;
        private String className;
        private ClassModel superClass;
        private List<ClassModel> implementsClasses = new ArrayList<>();
        private List<ClassModel> genericTypes = new ArrayList<>();
        private List<EnumeratorModel> enumerators = new ArrayList<>();
        private List<FieldModel> fields = new ArrayList<>();
        private List<MethodModel> methods = new ArrayList<>();
        private boolean primitive = false;
        private boolean last = false;

        public ClassModelBuilder commentHeader(final String commentHeader) {
            this.commentHeader = commentHeader;
            return this;
        }

        public ClassModelBuilder packageName(final String packageName) {
            this.packageName = packageName;
            return this;
        }

        public ClassModelBuilder imports(final List<ImportModel> imports) {
            if (this.imports == null) {
                this.imports = new ArrayList<>();
            }
            this.imports.addAll(imports);
            return this;
        }

        public ClassModelBuilder imports(final ImportModel imports) {
            if (this.imports == null) {
                this.imports = new ArrayList<>();
            }
            this.imports.add(imports);
            return this;
        }

        public ClassModelBuilder javaDoc(final JavaDocModel javaDoc) {
            this.javaDoc = javaDoc;
            return this;
        }

        public ClassModelBuilder annotations(final List<AnnotationModel> annotations) {
            if (this.annotations == null) {
                this.annotations = new ArrayList<>();
            }
            this.annotations.addAll(annotations);
            return this;
        }

        public ClassModelBuilder annotation(final AnnotationModel annotation) {
            if (this.annotations == null) {
                this.annotations = new ArrayList<>();
            }
            this.annotations.add(annotation);
            return this;
        }

        public ClassModelBuilder accessModifier(final AccessModifier accessModifier) {
            this.accessModifier = accessModifier;
            return this;
        }

        public ClassModelBuilder typeModifier(final TypeModifier typeModifier) {
            this.typeModifier = typeModifier;
            return this;
        }

        public ClassModelBuilder classKind(final ClassKind classKind) {
            this.classKind = classKind;
            return this;
        }

        public ClassModelBuilder className(final String className) {
            this.className = className;
            return this;
        }

        public ClassModelBuilder superClass(final ClassModel superClass) {
            this.superClass = superClass;
            return this;
        }

        public ClassModelBuilder implementsClasses(final List<ClassModel> implementsClasses) {
            if (this.implementsClasses == null) {
                this.implementsClasses = new ArrayList<>();
            }
            this.implementsClasses.addAll(implementsClasses);
            return this;
        }

        public ClassModelBuilder implementsClass(final ClassModel implementsClass) {
            if (this.implementsClasses == null) {
                this.implementsClasses = new ArrayList<>();
            }
            this.implementsClasses.add(implementsClass);
            return this;
        }

        public ClassModelBuilder genericTypes(final List<ClassModel> genericTypes) {
            if (this.genericTypes == null) {
                this.genericTypes = new ArrayList<>();
            }
            this.genericTypes.addAll(genericTypes);
            return this;
        }

        public ClassModelBuilder genericType(final ClassModel genericType) {
            if (this.genericTypes == null) {
                this.genericTypes = new ArrayList<>();
            }
            this.genericTypes.add(genericType);
            return this;
        }

        public ClassModelBuilder enumerators(final List<EnumeratorModel> enumerators) {
            if (this.enumerators == null) {
                this.enumerators = new ArrayList<>();
            }
            this.enumerators.addAll(enumerators);
            return this;
        }

        public ClassModelBuilder enumerator(final EnumeratorModel enumerator) {
            if (this.enumerators == null) {
                this.enumerators = new ArrayList<>();
            }
            this.enumerators.add(enumerator);
            return this;
        }

        public ClassModelBuilder fields(final List<FieldModel> fields) {
            if (this.fields == null) {
                this.fields = new ArrayList<>();
            }
            this.fields.addAll(fields);
            return this;
        }

        public ClassModelBuilder field(final FieldModel field) {
            if (this.fields == null) {
                this.fields = new ArrayList<>();
            }
            this.fields.add(field);
            return this;
        }

        public ClassModelBuilder methods(final List<MethodModel> methods) {
            if (this.methods == null) {
                this.methods = new ArrayList<>();
            }
            this.methods.addAll(methods);
            return this;
        }

        public ClassModelBuilder method(final MethodModel method) {
            if (this.methods == null) {
                this.methods = new ArrayList<>();
            }
            this.methods.add(method);
            return this;
        }

        public ClassModelBuilder primitive(final boolean primitive) {
            this.primitive = primitive;
            return this;
        }

        public ClassModelBuilder last(final boolean last) {
            this.last = last;
            return this;
        }

        public ClassModel build() {
            return new ClassModel(
                    this.commentHeader,
                    this.packageName,
                    this.imports,
                    this.javaDoc,
                    this.annotations,
                    this.accessModifier,
                    this.typeModifier,
                    this.classKind,
                    this.className,
                    this.superClass,
                    this.implementsClasses,
                    this.genericTypes,
                    this.enumerators,
                    this.fields,
                    this.methods,
                    this.primitive,
                    this.last);
        }

    }

    public static ClassModelBuilder builder() {
        return new ClassModelBuilder();
    }

}
