package com.zomu_t.lib.java.generate.java8;

import com.zomu_t.lib.java.generate.common.context.GenerateContext;
import com.zomu_t.lib.java.generate.common.context.GenerateTarget;
import com.zomu_t.lib.java.generate.common.type.DefaultTemplate;
import com.zomu_t.lib.java.generate.java8.generator.Java8Generator;
import com.zomu_t.lib.java.generate.java8.model.*;
import com.zomu_t.lib.java.generate.java8.type.AccessModifier;
import com.zomu_t.lib.java.generate.java8.type.MethodModifier;
import com.zomu_t.lib.java.generate.java8.util.FieldUtils;
import com.zomu_t.lib.java.generate.java8.util.JavaDocUtils;
import com.zomu_t.lib.java.generate.java8.util.TypeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.ArrayList;

public class Java8ConvertTest {

    /**
     * ロガー.
     */
    private static final Logger log = LoggerFactory.getLogger(Java8ConvertTest.class);

    /**
     * @param args
     */
    public static void main(String[] args) {

        GenerateContext context = new GenerateContext();

        GenerateTarget target = new GenerateTarget();

        ClassModel clazz = new ClassModel();

        // ヘッダーコメント
        clazz.setCommentHeader("/*\n * test \n */");

        // パッケージ
        clazz.setPackageName("hoge.fuga.piyo");

        // インポート定義
        ImportModel im1 = ImportModel.builder().packageName("hoge.fuga.piyo")
                .className("ClassName").methodName("methodName").build();

        ImportModel im2 = ImportModel.builder().packageName("ahoge.fuga.piyo")
                .className("ClassName").methodName("methodName").build();

        ImportModel im3 = ImportModel.builder().packageName("java.util")
                .className("List").build();

        // im1.setStaticImport(true);
        clazz.setImports(new ArrayList<ImportModel>());
        clazz.getImports().add(im1);
        clazz.getImports().add(im2);
        clazz.getImports().add(im3);

        JavaDocAnnotationModel jdam1 = JavaDocAnnotationModel.builder()
                .name("author").content("takashno").build();

        JavaDocAnnotationModel jdam2 = JavaDocAnnotationModel.builder()
                .name("since").content("v0.0.1").build();

        // JavaDoc
        JavaDocModel typeJavaDoc = JavaDocModel.builder().mainContent("1行目")
                .mainContent("2行目").mainContent("3行目").annotation(jdam1)
                .annotation(jdam2).build();

        clazz.setJavaDoc(typeJavaDoc);

        // クラスアノテーション
        AnnotationModel typeAnnotation1 = AnnotationModel
                .builder()
                .packageName("lombok")
                .className("Data")
                .attribute(AnnotationAttributeModel.builder()
                        .stringAttr(true)
                        .name("value")
                        .value("値")
                        .build())
                .last(true).build();

        clazz.setAnnotations(new ArrayList<AnnotationModel>());
        clazz.getAnnotations().add(typeAnnotation1);

        // アクセス修飾子
        clazz.setAccessModifier(AccessModifier.PUBLIC);

        // クラス修飾子
        // clazz.setTypeModifier();

        // クラス名
        clazz.setClassName("ConvertSample");

        clazz.setGenericTypes(new ArrayList<ClassModel>());
        clazz.getGenericTypes().add(
                TypeUtils.getGenericClassModel("com.example", "Hoge"));
        clazz.getGenericTypes().add(
                TypeUtils.getGenericClassModel("com.example", "Fuga"));

        // ----------------------------------
        // 親クラス
        // ----------------------------------
        ClassModel superClass = new ClassModel();
        superClass.setPackageName("hoge.fuga.piyo");
        superClass.setClassName("AbstractSuperClass");
        superClass.setGenericTypes(new ArrayList<ClassModel>());
        superClass.getGenericTypes().add(
                TypeUtils.getGenericClassModel("com.example", "Hoge"));
        superClass.getGenericTypes().add(
                TypeUtils.getGenericClassModel("com.example", "Fuga"));
        clazz.setSuperClass(superClass);

        // ----------------------------------
        // 実装クラス
        // ----------------------------------
        ClassModel implementsClass1 = new ClassModel();
        implementsClass1.setPackageName("hoge.fuga.piyo");
        implementsClass1.setClassName("ImplementsClass1");
        implementsClass1.setGenericTypes(new ArrayList<ClassModel>());
        implementsClass1.getGenericTypes().add(
                TypeUtils.getGenericClassModel("com.example", "Hoge"));
        implementsClass1.getGenericTypes().add(
                TypeUtils.getGenericClassModel("com.example", "Fuga"));

        clazz.setImplementsClasses(new ArrayList<ClassModel>());
        clazz.getImplementsClasses().add(implementsClass1);

        ClassModel implementsClass2 = new ClassModel();
        implementsClass2.setPackageName("hoge.fuga.piyo");
        implementsClass2.setClassName("ImplementsClass2");
        implementsClass2.setGenericTypes(new ArrayList<ClassModel>());
        implementsClass2.getGenericTypes().add(
                TypeUtils.getGenericClassModel("com.example", "Hoge"));
        implementsClass2.getGenericTypes().add(
                TypeUtils.getGenericClassModel("com.example", "Fuga"));
        clazz.getImplementsClasses().add(implementsClass2);

        // ----------------------------------
        // フィールド
        // ----------------------------------
        // フィールド1
        FieldModel fm1 = FieldUtils.getStringFieldModel("strProp1",
                "Stringプロパティ1");
        fm1.setInitializationValue("\"aaa\"");
        fm1.setAccessModifier(AccessModifier.PRIVATE);
        AnnotationModel fieldAnnotation1 = new AnnotationModel();
        fieldAnnotation1.setPackageName("lombok");
        fieldAnnotation1.setClassName("NotEmpty");

        fieldAnnotation1
                .setAttributes(new ArrayList<AnnotationAttributeModel>());
        fieldAnnotation1.getAttributes().add(
                new AnnotationAttributeModel(true, "value", "値", true));
        fieldAnnotation1.getAttributes().add(
                new AnnotationAttributeModel(true, "name", "名前", true));
        fieldAnnotation1.setLast(true);

        fm1.setAnnotations(new ArrayList<AnnotationModel>());
        fm1.getAnnotations().add(fieldAnnotation1);
        AnnotationModel fieldAnnotation2 = new AnnotationModel();
        fieldAnnotation2.setPackageName("lombok");
        fieldAnnotation2.setClassName("Pattern");

        fieldAnnotation2
                .setAttributes(new ArrayList<AnnotationAttributeModel>());
        fieldAnnotation2.getAttributes().add(
                new AnnotationAttributeModel(true, "regexp", "値", true));
        fieldAnnotation2.setLast(true);
        fm1.getAnnotations().add(fieldAnnotation2);

        clazz.setFields(new ArrayList<FieldModel>());
        clazz.getFields().add(fm1);

        // フィールド2
        FieldModel fm2 = FieldUtils.getStringFieldModel("strProp2",
                "Stringプロパティ2");
        clazz.getFields().add(fm2);

        target.setClazz(clazz);
        target.setTemplatePath(DefaultTemplate.JAVA8.getPath());
        StringWriter sw = new StringWriter();
        target.setOutputWriter(sw);
        context.getTargets().add(target);

        // ----------------------------------
        // メソッド
        // ----------------------------------
        // メソッド1
        MethodModel mm1 = new MethodModel();
        mm1.setAccessModifier(AccessModifier.PUBLIC);
        ReturnModel rm1 = ReturnModel.builder()
                .type(TypeUtils.getStringClassModel()).build();
        mm1.setReturnType(rm1);
        mm1.setName("method1");

        mm1.setAnnotations(new ArrayList<AnnotationModel>());
        mm1.getAnnotations().add(
                AnnotationModel.builder().packageName("com.example")
                        .className("GetMapping").last(false).build());
        JavaDocAnnotationModel jdam3 = JavaDocAnnotationModel.builder()
                .name("param").content("test").content("テスト値").build();
        mm1.setJavaDoc(JavaDocUtils.getMethodJavaDocModel("メソッド1です.", jdam3));
        ArgModel am1 = ArgModel.builder().type(TypeUtils.getStringClassModel())
                .name("arg1").build();
        mm1.setArgs(new ArrayList<ArgModel>());
        mm1.getArgs().add(am1);
        ArgModel am2 = ArgModel.builder().type(TypeUtils.getStringClassModel())
                .name("arg2").build();
        mm1.getArgs().add(am2);
        mm1.setThrowsTypes(new ArrayList<ClassModel>());
        mm1.getThrowsTypes().add(
                TypeUtils.getGenericClassModel("com.example",
                        "OriginalException"));
        mm1.setMethodModifiers(new ArrayList<MethodModifier>());
        mm1.getMethodModifiers().add(MethodModifier.ABSTRACT);

        // mm1.setLogic(LogicModel.builder().name("hoge").build());

        clazz.setMethods(new ArrayList<MethodModel>());
        clazz.getMethods().add(mm1);

        // ----------------------------------
        // メソッド
        // ----------------------------------
        // メソッド2
        MethodModel mm2 = new MethodModel();
        mm2.setAccessModifier(AccessModifier.PUBLIC);
        ReturnModel rm2 = ReturnModel.builder()
                .type(TypeUtils.getStringClassModel()).build();
        mm2.setReturnType(rm2);
        mm2.setName("method2");

        mm2.setAnnotations(new ArrayList<AnnotationModel>());
        mm2.getAnnotations().add(
                AnnotationModel.builder().packageName("com.example")
                        .className("GetMapping").last(false).build());
        JavaDocAnnotationModel jdam4 = JavaDocAnnotationModel.builder()
                .name("param").content("test").content("テスト値").build();
        mm2.setJavaDoc(JavaDocUtils.getMethodJavaDocModel("メソッド1です.", jdam4));
        ArgModel am3 = ArgModel.builder().type(TypeUtils.getStringClassModel())
                .name("arg1").build();
        mm2.setArgs(new ArrayList<ArgModel>());
        mm2.getArgs().add(am3);
        ArgModel am4 = ArgModel.builder().type(TypeUtils.getStringClassModel())
                .name("arg2").build();
        mm2.getArgs().add(am4);
        mm2.setThrowsTypes(new ArrayList<ClassModel>());
        mm2.getThrowsTypes().add(
                TypeUtils.getGenericClassModel("com.example",
                        "OriginalException"));
        mm2.setMethodModifiers(new ArrayList<MethodModifier>());

        // mm1.setLogic(LogicModel.builder().name("hoge").build());

        clazz.getMethods().add(mm2);

        Java8Generator converter = new Java8Generator();
        converter.generate(context);

        log.info("------------------------------");
        log.info(sw.toString());

    }

}
