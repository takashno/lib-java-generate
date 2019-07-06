package com.zomu_t.lib.java.generate.java8.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.zomu_t.lib.java.generate.java8.type.AccessModifier;
import com.zomu_t.lib.java.generate.java8.type.MethodModifier;
import lombok.*;

/**
 * メソッドを表すモデル.
 *
 * @author takashno
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MethodModel implements Serializable {

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
    @Singular
    private List<AnnotationModel> annotations = new ArrayList<>();

    /**
     * アクセス修飾子
     */
    private AccessModifier accessModifier;

    /**
     * メソッド修飾子
     */
    @Singular
    private List<MethodModifier> methodModifiers = new ArrayList<>();

    /**
     * 型
     */
    private ReturnModel returnType;

    /**
     * 名称
     */
    @NonNull
    private String name;

    /**
     * 引数
     */
    @Singular
    private List<ArgModel> args = new ArrayList<>();

    /**
     * Throws
     */
    @Singular
    private List<ClassModel> throwsTypes = new ArrayList<>();

    /**
     * ロジック
     */
    private LogicModel logic;

    /**
     * ブロックが不要なメソッドフラグ（このプロパティは内部処理での利用する）
     */
    @Builder.Default
    private boolean noneBlockMethod = false;

    /**
     * throwする可能性のある例外を保持しているかどうか判定します.
     *
     * @return
     */
    public boolean hasThrowTypes() {
        return throwsTypes == null ? false : throwsTypes.size() > 0;
    }

    /**
     * 抽象メソッドであるかどうか判定します.
     *
     * @return
     */
    public boolean isAbstractMethod() {
        return methodModifiers == null ? false : methodModifiers.stream()
                .anyMatch(x -> x == MethodModifier.ABSTRACT);
    }

    public void addMethodModifiers(MethodModifier methodModifier) {
        this.methodModifiers.add(methodModifier);
    }
}
