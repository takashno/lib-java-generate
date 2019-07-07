package com.zomu_t.lib.java.generate.java8;

import com.zomu_t.lib.java.generate.common.context.GenerateContext;
import com.zomu_t.lib.java.generate.common.context.GenerateTarget;
import com.zomu_t.lib.java.generate.common.type.DefaultTemplate;
import com.zomu_t.lib.java.generate.java8.converter.Java8Generator;
import com.zomu_t.lib.java.generate.java8.model.*;
import com.zomu_t.lib.java.generate.java8.type.AccessModifier;
import com.zomu_t.lib.java.generate.java8.type.ClassKind;
import com.zomu_t.lib.java.generate.java8.util.TypeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;

/**
 * ENUM出力サンプル.
 *
 * @author takashno
 */
public class Java8EnumGenerateTest {

    /**
     * ロガー
     */
    private static final Logger log = LoggerFactory.getLogger(Java8EnumGenerateTest.class);

    /**
     * サンプル出力処理.
     *
     * @param args 引数
     */
    public static void main(String[] args) {

        GenerateContext context = new GenerateContext();
        GenerateTarget target = context.newTarget(DefaultTemplate.JAVA8);

        target.setClazz(ClassModel.builder()
                // ファイルヘッダコメント
                .commentHeader("/* Java8 Enum Generate Test. */")
                // パッケージ
                .packageName("com.zomu_t.lib.java.generate.java8")
                // JavaDoc
                .javaDoc(JavaDocModel.builder()
                        .mainContent("Enum出力サンプル.")
                        .annotation(JavaDocAnnotationModel.builder()
                                .name("author")
                                .content("takashno").build())
                        .build())
                // アクセス修飾子
                .accessModifier(AccessModifier.PUBLIC)
                // enum
                .classKind(ClassKind.ENUM)
                .className("EnumSample")
                // フィールド
                .field(FieldModel.builder()
                        .javaDoc(JavaDocModel.builder()
                                .mainContent("値")
                                .build())
                        .accessModifier(AccessModifier.PRIVATE)
                        .type(TypeUtils.getStringClassModel())
                        .name("value")
                        .getterAutoCreate(true)
                        .build())
                // 列挙子
                .enumerator(EnumeratorModel.builder()
                        .javaDoc(JavaDocModel.builder()
                                .mainContent("ほげ").build())
                        .name("HOGE")
                        .value(EnumeratorValueModel.builder()
                                .stringAttr(true)
                                .value("ほげ")
                                .last(true)
                                .build())
                        .build())
                // コンストラクタ（まだ未完成・・・コンストラクタは別にするか・・・）
                .method(MethodModel.builder()
                        .accessModifier(AccessModifier.PRIVATE)
                        .name("EnumSample")
                        .arg(ArgModel.builder()
                                .type(TypeUtils.getStringClassModel())
                                .name("value")
                                .build())
                        .build())
                .build());

        // 出力
        Java8Generator converter = new Java8Generator();
        StringWriter sw = new StringWriter();
        target.setOutputWriter(sw);
        converter.convert(context);
        log.debug(sw.toString());
    }
}
