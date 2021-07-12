/* Copyright (c) 2017-2021 Nozomu Takashima. */
package com.zomu_t.lib.java.generate.java8.generator;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.zomu_t.lib.java.generate.common.context.GenerateContext;
import com.zomu_t.lib.java.generate.common.context.GenerateTarget;
import com.zomu_t.lib.java.generate.common.converter.JavaGenerator;
import com.zomu_t.lib.java.generate.common.type.DefaultLogicTemplate;
import com.zomu_t.lib.java.generate.java8.model.*;
import com.zomu_t.lib.java.generate.java8.type.ArgModifier;
import com.zomu_t.lib.java.generate.java8.type.ClassKind;
import com.zomu_t.lib.java.generate.java8.type.MethodModifier;
import com.zomu_t.lib.java.generate.java8.util.FieldUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Java8用の変換処理.
 *
 * @author takashno
 */
public class Java8Generator extends JavaGenerator {

    /**
     * ロガー.
     */
    private static final Logger log = LoggerFactory.getLogger(Java8Generator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void beforeTarget(GenerateContext context, GenerateTarget generateTarget) throws Exception {

        if (generateTarget.getClazz() != null && generateTarget.getClazz() instanceof ClassModel) {

            ClassModel clazz = ClassModel.class.cast(generateTarget.getClazz());

            // アクセスメソッドの生成（自動生成機能）
            generateAccessMethod(context, generateTarget, clazz);

            // コンストラクタロジックの生成
            generateConstructorLogic(context, generateTarget, clazz);

            // メソッドロジックの生成
            generateMethodLogic(context, generateTarget, clazz);

            // クラスモデルの最終フラグ調整
            adjustLastFlg(clazz);

            // インポート構成の調整
            adjustImport(clazz);

            // メソッドブロックの調整
            adjustDefaultMethod(clazz);

        }

    }

    /**
     * アクセスメソッドの生成.
     *
     * @param context 生成コンテキスト
     * @param generateTarget 生成対象
     * @param classModel クラスモデル
     * @throws Exception エラー
     */
    private void generateAccessMethod(GenerateContext context, GenerateTarget generateTarget, ClassModel classModel)
            throws Exception {

        if (CollectionUtils.isNotEmpty(classModel.getFields())) {
            for (FieldModel field : classModel.getFields()) {
                if (field.isGetterAutoCreate()) {
                    classModel.getMethods().add(FieldUtils.createGetterMethod(field));
                }
                if (field.isSetterAutoCreate()) {
                    classModel.getMethods().add(FieldUtils.createSetterMethod(field));
                }
            }
        }

    }

    /**
     * 生成対象にメソッドのロジックがあるのであれば変換してロジックのコンテンツに加える.
     *
     * @param context 生成コンテキスト
     * @param generateTarget 生成対象
     * @param classModel クラスモデル
     * @throws Exception エラー
     */
    private void generateConstructorLogic(GenerateContext context, GenerateTarget generateTarget, ClassModel classModel)
            throws Exception {

        if (CollectionUtils.isNotEmpty(classModel.getConstructors())) {

            for (ConstructorModel cm : classModel.getConstructors()) {

                if (cm.getLogic() != null && CollectionUtils.isNotEmpty(cm.getLogic().getDetails())) {

                    for (int i = 0; i < cm.getLogic().getDetails().size(); i++) {

                        ConstructorLogicDetailModel ldm = cm.getLogic().getDetails().get(i);

                        log.debug("--- constructor logic generate #{} ---", (i + 1));
                        log.debug("template     : {}", ldm.getTemplatePath());
                        log.debug("scope size   : {}", ldm.getScopes().size());

                        // ファクトリの生成
                        MustacheFactory mf = new DefaultMustacheFactory();

                        // テンプレートのコンパイル
                        Mustache m = mf.compile(ldm.getTemplatePath());

                        try (StringWriter sw = new StringWriter()) {

                            // 変換処理
                            m.execute(sw, ldm.getScopes().toArray()).flush();

                            // 変換したロジックを追記
                            cm.getLogic().getContent().append(sw.toString());

                        }
                    }
                } else {

                    if (cm.isConstructorAutoCreate()) {

                        if (CollectionUtils.isNotEmpty(classModel.getFields())) {

                            // コンストラクタで初期化対象のフィールドについて引数を作成する
                            for (FieldModel fm : classModel.getFields()) {
                                if (fm.isInitConstructor()) {
                                    if (cm.getArgs() == null) {
                                        cm.setArgs(new ArrayList<>());
                                    }
                                    cm.getArgs()
                                            .add(ArgModel.builder()
                                                    .argModifier(ArgModifier.FINAL)
                                                    .type(fm.getType())
                                                    .name(fm.getName())
                                                    .build());
                                }
                            }

                            // ファクトリの生成
                            MustacheFactory mf = new DefaultMustacheFactory();

                            // テンプレートのコンパイル
                            Mustache m = mf.compile(DefaultLogicTemplate.CONSTRUCTOR.getPath());

                            try (StringWriter sw = new StringWriter()) {

                                // 変換処理
                                m.execute(sw, new Object[] { classModel }).flush();

                                // 変換したロジックを追記
                                cm.setLogic(ConstructorLogicModel.builder()
                                        .content(new StringBuilder(sw.toString()))
                                        .build());
                            }

                        }
                    }

                }
            }
        }
    }

    /**
     * 生成対象にメソッドのロジックがあるのであれば変換してロジックのコンテンツに加える.
     *
     * @param context 生成コンテキスト
     * @param generateTarget 生成対象
     * @param classModel クラスモデル
     * @throws Exception エラー
     */
    private void generateMethodLogic(GenerateContext context, GenerateTarget generateTarget, ClassModel classModel)
            throws Exception {

        if (CollectionUtils.isNotEmpty(classModel.getMethods())) {

            for (MethodModel mm : classModel.getMethods()) {

                if (mm.getLogic() != null && CollectionUtils.isNotEmpty(mm.getLogic().getDetails())) {

                    for (int i = 0; i < mm.getLogic().getDetails().size(); i++) {

                        LogicDetailModel ldm = mm.getLogic().getDetails().get(i);

                        log.debug("--- method logic generate #{} ---", (i + 1));
                        log.debug("template     : {}", ldm.getTemplatePath());
                        log.debug("scope size   : {}", ldm.getScopes().size());

                        // ファクトリの生成
                        MustacheFactory mf = new DefaultMustacheFactory();

                        // テンプレートのコンパイル
                        Mustache m = mf.compile(ldm.getTemplatePath());

                        try (StringWriter sw = new StringWriter()) {

                            // 変換処理
                            m.execute(sw, ldm.getScopes().toArray()).flush();

                            // 変換したロジックを追記
                            mm.getLogic().getContent().append(sw.toString());

                        }
                    }
                }
            }
        }
    }

    /**
     * 最終フラグの調整処理. この処理は、様々な要素が最終である場合にlastフラグを立てる処理.
     * いわゆるカンマで区切るような、引数など複数を指定が可能な要素の最後をmustacheで判定するためのもの.
     *
     * @param classModel クラスモデル
     */
    private void adjustLastFlg(ClassModel classModel) {
        // 総称型
        if (classModel.getGenericTypes() != null) {
            for (int i = 0; i < classModel.getGenericTypes().size(); i++) {
                if (i + 1 == classModel.getGenericTypes().size()) {
                    classModel.getGenericTypes().get(i).setLast(true);
                    if (CollectionUtils.isNotEmpty(classModel.getGenericTypes().get(i).getGenericTypes())) {
                        for (ClassModel clazz : classModel.getGenericTypes().get(i).getGenericTypes()) {
                            // 再帰処理
                            adjustLastFlg(clazz);
                        }
                    }
                } else {
                    classModel.getGenericTypes().get(i).setLast(false);
                    if (CollectionUtils.isNotEmpty(classModel.getGenericTypes().get(i).getGenericTypes())) {
                        for (ClassModel clazz : classModel.getGenericTypes().get(i).getGenericTypes()) {
                            // 再帰処理
                            adjustLastFlg(clazz);
                        }
                    }
                }
            }
        }
        // 親クラス
        if (classModel.getSuperClass() != null) {
            // 再帰処理
            adjustLastFlg(classModel.getSuperClass());
        }
        // 実装インタフェース
        if (classModel.getImplementsClasses() != null) {
            for (int i = 0; i < classModel.getImplementsClasses().size(); i++) {
                if (i + 1 == classModel.getImplementsClasses().size()) {
                    classModel.getImplementsClasses().get(i).setLast(true);
                } else {
                    classModel.getImplementsClasses().get(i).setLast(false);
                }
                // 再帰処理
                adjustLastFlg(classModel.getImplementsClasses().get(i));
            }
        }
        // アノテーション
        if (classModel.getAnnotations() != null) {
            for (AnnotationModel am : classModel.getAnnotations()) {
                if (am.getAttributes() != null) {
                    for (int i = 0; i < am.getAttributes().size(); i++) {
                        if (i + 1 == am.getAttributes().size()) {
                            am.getAttributes().get(i).setLast(true);
                        } else {
                            am.getAttributes().get(i).setLast(false);
                        }
                    }
                }
            }
        }
        // フィールド
        if (classModel.getFields() != null) {
            for (FieldModel fm : classModel.getFields()) {
                // 再帰処理
                adjustLastFlg(fm.getType());
                // アノテーション
                if (fm.getAnnotations() != null) {
                    for (AnnotationModel am : fm.getAnnotations()) {
                        if (am.getAttributes() != null) {
                            for (int i = 0; i < am.getAttributes().size(); i++) {
                                if (i + 1 == am.getAttributes().size()) {
                                    am.getAttributes().get(i).setLast(true);
                                } else {
                                    am.getAttributes().get(i).setLast(false);
                                }
                            }
                        }
                    }
                }
            }
        }
        // 列挙子
        if (classModel.getEnumerators() != null) {
            for (int i = 0; i < classModel.getEnumerators().size(); i++) {
                EnumeratorModel em = classModel.getEnumerators().get(i);
                if (i + 1 == classModel.getEnumerators().size()) {
                    em.setLast(true);
                } else {
                    em.setLast(false);
                }
                if (em.getValues() != null && !em.getValues().isEmpty()) {
                    // 値
                    for (int j = 0; j < em.getValues().size(); j++) {
                        EnumeratorValueModel evm = em.getValues().get(j);
                        if (j + 1 == em.getValues().size()) {
                            evm.setLast(true);
                        } else {
                            evm.setLast(false);
                        }
                    }
                }
            }
        }
        // コンストラクタ
        if (classModel.getConstructors() != null) {
            for (ConstructorModel cm : classModel.getConstructors()) {
                if (cm.getArgs() != null) {
                    for (int i = 0; i < cm.getArgs().size(); i++) {
                        // 再帰処理
                        adjustLastFlg(cm.getArgs().get(i).getType());
                        if (i + 1 == cm.getArgs().size()) {
                            cm.getArgs().get(i).setLast(true);
                        } else {
                            cm.getArgs().get(i).setLast(false);
                        }
                        // アノテーション
                        if (cm.getArgs().get(i).getAnnotations() != null) {
                            for (AnnotationModel am : cm.getArgs().get(i).getAnnotations()) {
                                if (am.getAttributes() != null) {
                                    for (int j = 0; j < am.getAttributes().size(); j++) {
                                        if (j + 1 == am.getAttributes().size()) {
                                            am.getAttributes().get(j).setLast(true);
                                        } else {
                                            am.getAttributes().get(j).setLast(false);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // メソッド
        if (classModel.getMethods() != null) {
            for (MethodModel mm : classModel.getMethods()) {
                if (mm.getArgs() != null) {
                    for (int i = 0; i < mm.getArgs().size(); i++) {
                        // 再帰処理
                        adjustLastFlg(mm.getArgs().get(i).getType());
                        if (i + 1 == mm.getArgs().size()) {
                            mm.getArgs().get(i).setLast(true);
                        } else {
                            mm.getArgs().get(i).setLast(false);
                        }
                        // アノテーション
                        if (mm.getArgs().get(i).getAnnotations() != null) {
                            for (AnnotationModel am : mm.getArgs().get(i).getAnnotations()) {
                                if (am.getAttributes() != null) {
                                    for (int j = 0; j < am.getAttributes().size(); j++) {
                                        if (j + 1 == am.getAttributes().size()) {
                                            am.getAttributes().get(j).setLast(true);
                                        } else {
                                            am.getAttributes().get(j).setLast(false);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    /**
     * インポート構成の調整処理.
     *
     * @param classModel クラスモデル
     */
    private void adjustImport(ClassModel classModel) {

        Map<String, ImportModel> imports = new HashMap<>();

        // インポート対象候補の収集
        importCollect(classModel, imports);

        imports.forEach((k, v) -> {

            // プリミティブ型とそのラッパーの場合は追加不要
            if (v.getClassName()
                    .matches(
                            "(int|long|short|double|float|char|byte|boolean|String|Integer|Long|Short|Double|Float|Character|Byte|Boolean)"))
                return;

            // java.langのパッケージはインポート不要
            if (v.getPackageName().startsWith("java.lang"))
                return;

            // インポートが存在しなければリストを生成しておく
            if (classModel.getImports() == null)
                classModel.setImports(new ArrayList<>());

            // 対象クラスのインポートと比較し、追加するかを決定する
            for (ImportModel im : classModel.getImports()) {

                // FQCN
                String fqcn = StringUtils.join(im.getPackageName(), im.getClassName());

                // 追加対象がないのであればSKIP
                if (StringUtils.isEmpty(fqcn))
                    return;

                // FQCNの一致、ワイルドカードが一致、staticインポートが一致であれば追加不要
                // kは収集処理にて各モデルから収集しているためクラス名までを含んだものでメソッド名は含まない
                if (StringUtils.equals(fqcn, k) && im.isWildcard() == v.isWildcard()
                        && im.isStaticImport() == v.isStaticImport())
                    return;
            }

            // 既に同一のものが含まれていないのであれば追加する
            if (!classModel.getImports().contains(v)) {
                classModel.getImports().add(v);
            }
        });

    }

    /**
     * インポートの編成.
     *
     * @param classModel クラスモデル
     * @param imports インポート編成
     */
    private void importCollect(ClassModel classModel, Map<String, ImportModel> imports) {

        // アノテーションに対してインポートの処理を行う
        if (classModel.getAnnotations() != null) {
            classModel.getAnnotations().forEach(x -> {
                String fqcn = StringUtils.join(x.getPackageName(), ".", x.getClassName());
                imports.putIfAbsent(fqcn,
                        ImportModel.builder()
                                .packageName(ClassUtils.getPackageName(fqcn))
                                .className(ClassUtils.getShortClassName(fqcn))
                                .build());
            });
        }

        // 実装インタフェースに対してインポートの収集を行う
        if (classModel.getImplementsClasses() != null) {
            classModel.getImplementsClasses().forEach(x -> {
                String fqcn = StringUtils.join(x.getPackageName(), ".", x.getClassName());
                imports.putIfAbsent(fqcn,
                        ImportModel.builder()
                                .packageName(ClassUtils.getPackageName(fqcn))
                                .className(ClassUtils.getShortClassName(fqcn))
                                .build());
                importCollect(x, imports);
            });
        }

        // 総称型に対してインポートの収集を行う
        if (classModel.getGenericTypes() != null) {
            classModel.getGenericTypes().forEach(x -> {
                String fqcn = StringUtils.join(x.getPackageName(), ".", x.getClassName());
                imports.putIfAbsent(fqcn,
                        ImportModel.builder()
                                .packageName(ClassUtils.getPackageName(fqcn))
                                .className(ClassUtils.getShortClassName(fqcn))
                                .build());
                importCollect(x, imports);
            });
        }

        // 親クラスに対してインポートの収集を行う
        if (classModel.getSuperClass() != null) {
            String fqcn = StringUtils.join(classModel.getSuperClass().getPackageName(), ".",
                    classModel.getSuperClass().getClassName());
            imports.putIfAbsent(fqcn,
                    ImportModel.builder()
                            .packageName(ClassUtils.getPackageName(fqcn))
                            .className(ClassUtils.getShortClassName(fqcn))
                            .build());
            importCollect(classModel.getSuperClass(), imports);
        }

        // フィールドに対してインポートの収集を行う
        if (classModel.getFields() != null && CollectionUtils.isNotEmpty(classModel.getFields())) {
            classModel.getFields().forEach(x -> {
                String fqcn = StringUtils.join(x.getType().getPackageName(), ".", x.getType().getClassName());
                imports.putIfAbsent(fqcn,
                        ImportModel.builder()
                                .packageName(ClassUtils.getPackageName(fqcn))
                                .className(ClassUtils.getShortClassName(fqcn))
                                .build());
            });
        }

        // メソッドに対してインポートの収集を行う
        if (classModel.getMethods() != null && CollectionUtils.isNotEmpty(classModel.getMethods())) {
            classModel.getMethods()
                    .forEach(x -> {

                        // 引数に対してインポートの収集を行う
                        x.getArgs().forEach(y -> {
                            String fqcn = y.getType().getPackageName() + "." + y.getType().getClassName();
                            imports.putIfAbsent(fqcn,
                                    ImportModel.builder()
                                            .packageName(ClassUtils.getPackageName(fqcn))
                                            .className(ClassUtils.getShortClassName(fqcn))
                                            .build());
                        });

                        // アノテーションに対してインポートの収集を行う
                        x.getAnnotations().forEach(y -> {
                            String fqcn = y.getPackageName() + "." + y.getClassName();
                            imports.putIfAbsent(fqcn,
                                    ImportModel.builder()
                                            .packageName(ClassUtils.getPackageName(fqcn))
                                            .className(ClassUtils.getShortClassName(fqcn))
                                            .build());
                        });

                        // throwsに対してインポートの収集を行う
                        x.getThrowsTypes().forEach(y -> {
                            String fqcn = y.getPackageName() + "." + y.getClassName();
                            imports.putIfAbsent(fqcn,
                                    ImportModel.builder()
                                            .packageName(ClassUtils.getPackageName(fqcn))
                                            .className(ClassUtils.getShortClassName(fqcn))
                                            .build());
                        });

                        // 返却値に対してインポートの収集を行う
                        if (x.getReturnType() != null) {
                            String fqcn = x.getReturnType().getType().getPackageName() + "."
                                    + x.getReturnType().getType().getClassName();
                            imports.putIfAbsent(fqcn,
                                    ImportModel.builder()
                                            .packageName(ClassUtils.getPackageName(fqcn))
                                            .className(ClassUtils.getShortClassName(fqcn))
                                            .build());
                            // 返却値の総称型に対してインポートの収集を行う
                            if (x.getReturnType().getType().getGenericTypes() != null) {
                                x.getReturnType().getType().getGenericTypes().forEach(y -> {
                                    importCollect(y, imports);
                                });
                            }
                        }

                    });

        }

    }

    /**
     * デフォルトメソッドの調整処理.
     *
     * @param classModel クラスモデル
     */
    private void adjustDefaultMethod(ClassModel classModel) {

        // インタフェースで、デフォルトメソッドを持たないものはブロックの出力は不要
        if (classModel.getClassKind() == ClassKind.INTERFACE) {
            for (MethodModel mm : classModel.getMethods()) {
                if (mm.getMethodModifiers().stream().noneMatch(x -> x == MethodModifier.DEFAULT)) {
                    mm.setNoneBlockMethod(true);
                }
            }
        }

        // インタフェース以外で抽象メソッドの場合はブロックの出力は不要
        if (classModel.getClassKind() != ClassKind.INTERFACE) {
            for (MethodModel mm : classModel.getMethods()) {
                if (CollectionUtils.isNotEmpty(mm.getMethodModifiers())
                        && mm.getMethodModifiers().stream().anyMatch(x -> x == MethodModifier.ABSTRACT)) {
                    mm.setNoneBlockMethod(true);
                }
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void before(GenerateContext context) {
        // Do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void after(GenerateContext context) {
        // Do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void afterTarget(GenerateContext context, GenerateTarget generateTarget) throws Exception {
        // Do nothing
    }

}
