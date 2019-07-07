package com.zomu_t.lib.java.generate.java8.converter;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.zomu_t.lib.java.generate.common.context.GenerateContext;
import com.zomu_t.lib.java.generate.common.context.GenerateTarget;
import com.zomu_t.lib.java.generate.common.converter.JavaGenerator;
import com.zomu_t.lib.java.generate.java8.model.*;
import com.zomu_t.lib.java.generate.java8.type.ClassKind;
import com.zomu_t.lib.java.generate.java8.type.MethodModifier;
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
     * <hr/>
     * scopesに設定したオブジェクトに対して、何かしらの前処理を行いたい場合は本メソッドをオーバーライドして実装してください.
     */
    @Override
    protected void before(GenerateContext context) {

        // 変換対象に対して前処理を行う.
        for (GenerateTarget generateTarget : context.getTargets()) {

            if (generateTarget.getClazz() != null
                    && generateTarget.getClazz() instanceof ClassModel) {

                ClassModel clazz = ClassModel.class.cast(generateTarget
                        .getClazz());

                // クラスモデルの最終フラグ調整
                adjustLastFlg(clazz);

                // インポート構成の調整
                adjustImport(clazz);

                // メソッドブロックの調整
                adjustDefaultMethod(clazz);

            }

        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void beforeTarget(GenerateContext context,
                                GenerateTarget generateTarget) throws Exception {

        if (generateTarget.getClazz() != null
                && generateTarget.getClazz() instanceof ClassModel) {

            ClassModel clazz = ClassModel.class.cast(generateTarget.getClazz());

            // ロジックの生成
            generateLogic(context, generateTarget, clazz);

        }

    }

    /**
     * 変換対象のロジックがあるのであれば変換してロジックのコンテンツに加える.
     *
     * @param context
     * @param generateTarget
     * @param classModel
     * @throws Exception
     */
    private void generateLogic(GenerateContext context,
                               GenerateTarget generateTarget, ClassModel classModel)
            throws Exception {

        if (CollectionUtils.isNotEmpty(classModel.getMethods())) {

            for (MethodModel mm : classModel.getMethods()) {

                if (mm.getLogic() != null
                        && CollectionUtils.isNotEmpty(mm.getLogic()
                        .getDetails())) {

                    for (int i = 0; i < mm.getLogic().getDetails().size(); i++) {

                        LogicDetailModel ldm = mm.getLogic().getDetails()
                                .get(i);

                        log.debug("--- logic generate #{} ---", (i + 1));
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
     * 総称型の最終フラグの調整処理.
     *
     * @param classModel
     */
    private void adjustLastFlg(ClassModel classModel) {
        // 総称型
        if (classModel.getGenericTypes() != null) {
            for (int i = 0; i < classModel.getGenericTypes().size(); i++) {
                if (i + 1 == classModel.getGenericTypes().size()) {
                    classModel.getGenericTypes().get(i).setLast(true);
                    if (CollectionUtils.isNotEmpty(classModel.getGenericTypes()
                            .get(i).getGenericTypes())) {
                        for (ClassModel clazz : classModel.getGenericTypes()
                                .get(i).getGenericTypes()) {
                            // 再帰処理
                            adjustLastFlg(clazz);
                        }
                    }
                } else {
                    classModel.getGenericTypes().get(i).setLast(false);
                    if (CollectionUtils.isNotEmpty(classModel.getGenericTypes()
                            .get(i).getGenericTypes())) {
                        for (ClassModel clazz : classModel.getGenericTypes()
                                .get(i).getGenericTypes()) {
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
                            for (AnnotationModel am : mm.getArgs().get(i)
                                    .getAnnotations()) {
                                if (am.getAttributes() != null) {
                                    for (int j = 0; j < am.getAttributes()
                                            .size(); j++) {
                                        if (j + 1 == am.getAttributes().size()) {
                                            am.getAttributes().get(j)
                                                    .setLast(true);
                                        } else {
                                            am.getAttributes().get(j)
                                                    .setLast(false);
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
     * @param classModel
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
                String fqcn = StringUtils.join(im.getPackageName(),
                        im.getClassName());

                // 追加対象がないのであればSKIP
                if (StringUtils.isEmpty(fqcn))
                    return;

                // FQCNの一致、ワイルドカードが一致、staticインポートが一致であれば追加不要
                // kは収集処理にて各モデルから収集しているためクラス名までを含んだものでメソッド名は含まない
                if (StringUtils.equals(fqcn, k)
                        && im.isWildcard() == v.isWildcard()
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
     * @param classModel
     * @param imports
     */
    private void importCollect(ClassModel classModel,
                               Map<String, ImportModel> imports) {

        // アノテーションに対してインポートの処理を行う
        if (classModel.getAnnotations() != null) {
            classModel.getAnnotations().forEach(
                    x -> {
                        String fqcn = StringUtils.join(x.getPackageName(), ".",
                                x.getClassName());
                        imports.putIfAbsent(fqcn, ImportModel.builder()
                                .packageName(ClassUtils.getPackageName(fqcn))
                                .className(ClassUtils.getShortClassName(fqcn))
                                .build());
                    });
        }

        // 実装インタフェースに対してインポートの収集を行う
        if (classModel.getImplementsClasses() != null) {
            classModel.getImplementsClasses().forEach(
                    x -> {
                        String fqcn = StringUtils.join(x.getPackageName(), ".",
                                x.getClassName());
                        imports.putIfAbsent(fqcn, ImportModel.builder()
                                .packageName(ClassUtils.getPackageName(fqcn))
                                .className(ClassUtils.getShortClassName(fqcn))
                                .build());
                        importCollect(x, imports);
                    });
        }

        // 総称型に対してインポートの収集を行う
        if (classModel.getGenericTypes() != null) {
            classModel.getGenericTypes().forEach(
                    x -> {
                        String fqcn = StringUtils.join(x.getPackageName(), ".",
                                x.getClassName());
                        imports.putIfAbsent(fqcn, ImportModel.builder()
                                .packageName(ClassUtils.getPackageName(fqcn))
                                .className(ClassUtils.getShortClassName(fqcn))
                                .build());
                        importCollect(x, imports);
                    });
        }

        // 親クラスに対してインポートの収集を行う
        if (classModel.getSuperClass() != null) {
            String fqcn = StringUtils.join(classModel.getSuperClass()
                    .getPackageName(), ".", classModel.getSuperClass()
                    .getClassName());
            imports.putIfAbsent(
                    fqcn,
                    ImportModel.builder()
                            .packageName(ClassUtils.getPackageName(fqcn))
                            .className(ClassUtils.getShortClassName(fqcn))
                            .build());
            importCollect(classModel.getSuperClass(), imports);
        }

        // フィールドに対してインポートの収集を行う
        if (classModel.getFields() != null
                && CollectionUtils.isNotEmpty(classModel.getFields())) {
            classModel.getFields().forEach(
                    x -> {
                        String fqcn = StringUtils.join(x.getType()
                                .getPackageName(), ".", x.getType()
                                .getClassName());
                        imports.putIfAbsent(fqcn, ImportModel.builder()
                                .packageName(ClassUtils.getPackageName(fqcn))
                                .className(ClassUtils.getShortClassName(fqcn))
                                .build());
                    });
        }

        // メソッドに対してインポートの収集を行う
        if (classModel.getMethods() != null
                && CollectionUtils.isNotEmpty(classModel.getMethods())) {
            classModel
                    .getMethods()
                    .forEach(x -> {

                        // 引数に対してインポートの収集を行う
                        x.getArgs()
                                .forEach(
                                        y -> {
                                            String fqcn = y.getType()
                                                    .getPackageName()
                                                    + "."
                                                    + y.getType()
                                                    .getClassName();
                                            imports.putIfAbsent(
                                                    fqcn,
                                                    ImportModel
                                                            .builder()
                                                            .packageName(
                                                                    ClassUtils
                                                                            .getPackageName(fqcn))
                                                            .className(
                                                                    ClassUtils
                                                                            .getShortClassName(fqcn))
                                                            .build());
                                        });

                        // アノテーションに対してインポートの収集を行う
                        x.getAnnotations()
                                .forEach(
                                        y -> {
                                            String fqcn = y
                                                    .getPackageName()
                                                    + "."
                                                    + y.getClassName();
                                            imports.putIfAbsent(
                                                    fqcn,
                                                    ImportModel
                                                            .builder()
                                                            .packageName(
                                                                    ClassUtils
                                                                            .getPackageName(fqcn))
                                                            .className(
                                                                    ClassUtils
                                                                            .getShortClassName(fqcn))
                                                            .build());
                                        });

                        // throwsに対してインポートの収集を行う
                        x.getThrowsTypes()
                                .forEach(
                                        y -> {
                                            String fqcn = y
                                                    .getPackageName()
                                                    + "."
                                                    + y.getClassName();
                                            imports.putIfAbsent(
                                                    fqcn,
                                                    ImportModel
                                                            .builder()
                                                            .packageName(
                                                                    ClassUtils
                                                                            .getPackageName(fqcn))
                                                            .className(
                                                                    ClassUtils
                                                                            .getShortClassName(fqcn))
                                                            .build());
                                        });

                        // 返却値に対してインポートの収集を行う
                        if (x.getReturnType() != null) {
                            String fqcn = x.getReturnType().getType()
                                    .getPackageName()
                                    + "."
                                    + x.getReturnType().getType()
                                    .getClassName();
                            imports.putIfAbsent(
                                    fqcn,
                                    ImportModel
                                            .builder()
                                            .packageName(
                                                    ClassUtils
                                                            .getPackageName(fqcn))
                                            .className(
                                                    ClassUtils
                                                            .getShortClassName(fqcn))
                                            .build());
                            // 返却値の総称型に対してインポートの収集を行う
                            if (x.getReturnType().getType()
                                    .getGenericTypes() != null) {
                                x.getReturnType().getType()
                                        .getGenericTypes().forEach(y -> {
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
     * @param classModel
     */
    private void adjustDefaultMethod(ClassModel classModel) {

        // インタフェースで、デフォルトメソッドを持たないものはブロックの出力は不要
        if (classModel.getClassKind() == ClassKind.INTERFACE) {
            for (MethodModel mm : classModel.getMethods()) {
                if (mm.getMethodModifiers().stream()
                        .noneMatch(x -> x == MethodModifier.DEFAULT)) {
                    mm.setNoneBlockMethod(true);
                }
            }
        }

        // インタフェース以外で抽象メソッドの場合はブロックの出力は不要
        if (classModel.getClassKind() != ClassKind.INTERFACE) {
            for (MethodModel mm : classModel.getMethods()) {
                if (mm.getMethodModifiers().stream()
                        .noneMatch(x -> x == MethodModifier.ABSTRACT)) {
                    mm.setNoneBlockMethod(true);
                }
            }
        }

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
    protected void afterTarget(GenerateContext context,
                               GenerateTarget generateTarget) throws Exception {
        // Do nothing
    }

}
