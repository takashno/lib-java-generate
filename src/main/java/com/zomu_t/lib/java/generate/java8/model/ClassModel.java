package com.zomu_t.lib.java.generate.java8.model;

import java.io.Serializable;
import java.util.List;

import com.zomu_t.lib.java.generate.common.model.Clazz;
import com.zomu_t.lib.java.generate.java8.type.AccessModifier;
import com.zomu_t.lib.java.generate.java8.type.ClassKind;
import com.zomu_t.lib.java.generate.java8.type.TypeModifier;
import lombok.*;

/**
 * Javaクラスを表すモデル.
 *
 * @author takashimanozomu
 */
@Getter
@Setter
@Builder
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
    @Singular
    private List<ImportModel> imports;

    /**
     * クラスに対するJavaDoc
     */
    private JavaDocModel javaDoc;

    /**
     * クラスに対するアノテーション
     */
    @Singular
    private List<AnnotationModel> annotations;

    /**
     * アクセス修飾子
     */
    @Builder.Default
    private AccessModifier accessModifier = AccessModifier.PUBLIC;

    /**
     * クラス修飾子
     */
    private TypeModifier typeModifier;

    /**
     * クラス種別
     */
    @Builder.Default
    private ClassKind classKind = ClassKind.CLASS;

    /**
     * クラス名
     */
    @NonNull
    private String className;

    /**
     * スーパークラス
     */
    private ClassModel superClass;

    /**
     * 実装インタフェース
     */
    @Singular
    private List<ClassModel> implementsClasses;

    /**
     * 総称型
     */
    @Singular
    private List<ClassModel> genericTypes;

    /**
     * 列挙子
     */
    @Singular
    private List<EnumeratorModel> enumerators;

    /**
     * フィールド
     */
    @Singular
    private List<FieldModel> fields;

    /**
     * メソッド
     */
    @Singular
    private List<MethodModel> methods;

    /**
     * プリミティブ型フラグ
     */
    @Builder.Default
    private boolean primitive = false;

    /**
     * 最終フラグ
     */
    @Builder.Default
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

}
