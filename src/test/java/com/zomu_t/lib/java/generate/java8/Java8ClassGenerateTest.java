/* Java8 Class Generate Test. */
package com.zomu_t.lib.java.generate.java8;

import com.zomu_t.lib.java.generate.common.context.ConvertContext;
import com.zomu_t.lib.java.generate.common.context.ConvertTarget;
import com.zomu_t.lib.java.generate.common.type.DefaultTemplate;
import com.zomu_t.lib.java.generate.java8.converter.Java8Converter;
import com.zomu_t.lib.java.generate.java8.model.*;
import com.zomu_t.lib.java.generate.java8.type.AccessModifier;
import com.zomu_t.lib.java.generate.java8.type.MethodModifier;
import com.zomu_t.lib.java.generate.java8.util.JavaDocUtils;
import com.zomu_t.lib.java.generate.java8.util.TypeUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.StringWriter;

/**
 * Java8のClass出力テスト.<br>
 *
 * @author takashno
 * @since v0.0.2
 */
@Slf4j
public class Java8ClassGenerateTest {

    /**
     * 出力テスト.
     *
     * @param args 引数
     */
    public static void main(String[] args) {

        ConvertContext context = new ConvertContext();
        ConvertTarget target = context.newTarget(DefaultTemplate.JAVA8);

        // Setting Class
        ClassModel clazz =
                ClassModel.builder()
                        // ヘッダーコメント
                        .commentHeader("/* Java8 Class Generate Test. */")
                        // パッケージ
                        .packageName("com.zomu_t.lib.java.generate.java8")
                        // インポート定義
                        .imports(ImportModel.builder()
                                .packageName("com.zomu_t.lib.java.generate.common.context")
                                .className("ConvertContext").build())
                        .imports(ImportModel.builder()
                                .packageName("com.zomu_t.lib.java.generate.common.context")
                                .className("ConvertTarget").build())
                        .imports(ImportModel.builder()
                                .packageName("com.zomu_t.lib.java.generate.common.type")
                                .className("DefaultTemplate").build())
                        .imports(ImportModel.builder()
                                .packageName("com.zomu_t.lib.java.generate.java8.converter")
                                .className("Java8Converter").build())
                        .imports(ImportModel.builder()
                                .packageName("com.zomu_t.lib.java.generate.java8.model")
                                .wildcard(true).build())
                        .imports(ImportModel.builder()
                                .packageName("com.zomu_t.lib.java.generate.java8.type")
                                .className("AccessModifier").build())
                        .imports(ImportModel.builder()
                                .packageName("com.zomu_t.lib.java.generate.java8.type")
                                .className("MethodModifier").build())
                        .imports(ImportModel.builder()
                                .packageName("com.zomu_t.lib.java.generate.java8.util")
                                .className("JavaDocUtils").build())
                        .imports(ImportModel.builder()
                                .packageName("com.zomu_t.lib.java.generate.java8.util")
                                .className("TypeUtils").build())
                        // アクセス修飾子
                        .accessModifier(AccessModifier.PUBLIC)
                        // クラスアノテーション
                        .annotation(AnnotationModel
                                .builder()
                                .packageName("lombok.extern.slf4j")
                                .className("Slf4j")
                                .last(true).build())
                        // JavaDoc
                        .javaDoc(JavaDocModel.builder()
                                .mainContent("Java8のClass出力テスト.")
                                .annotation(JavaDocAnnotationModel.builder()
                                        .name("author").content("takashno").build())
                                .annotation(JavaDocAnnotationModel.builder()
                                        .name("since").content("v0.0.2").build())
                                .build())
                        // クラス名
                        .className("Java8ClassGenerateTest")
                        .build();

        target.setClazz(clazz);

        // Setting Method
        MethodModel method =
                MethodModel.builder()
                        .accessModifier(AccessModifier.PUBLIC)
                        .methodModifier(MethodModifier.STATIC)
                        .name("main")
                        .javaDoc(JavaDocUtils.getMethodJavaDocModel("出力テスト.",
                                JavaDocAnnotationModel.builder()
                                        .name("param")
                                        .content("args")
                                        .content("引数").build()))
                        .arg(ArgModel.builder()
                                .type(TypeUtils.getStringClassModel())
                                .array(true)
                                .name("args").build())
                        .build();

        clazz.getMethods().add(method);


        // Convert
        StringWriter sw = new StringWriter();
        target.setOutputWriter(sw);
        Java8Converter converter = new Java8Converter();
        converter.convert(context);

        log.info(sw.toString());

    }


}
