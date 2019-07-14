/* Java8 Abstract Class Generate Test. */
package example;

import com.zomu_t.lib.java.generate.common.context.GenerateContext;
import com.zomu_t.lib.java.generate.common.context.GenerateTarget;
import com.zomu_t.lib.java.generate.common.type.DefaultTemplate;
import com.zomu_t.lib.java.generate.java8.generator.Java8Generator;
import com.zomu_t.lib.java.generate.java8.model.*;
import com.zomu_t.lib.java.generate.java8.type.AccessModifier;
import com.zomu_t.lib.java.generate.java8.type.ClassKind;
import com.zomu_t.lib.java.generate.java8.type.MethodModifier;
import com.zomu_t.lib.java.generate.java8.type.TypeModifier;
import com.zomu_t.lib.java.generate.java8.util.JavaDocUtils;
import com.zomu_t.lib.java.generate.java8.util.TypeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;

/**
 * Java8のAbstractClass出力テスト.<br>
 *
 * @author takashno
 * @since v0.0.2
 */
public class Java8AbstractClassGenerate {

    /**
     * ロガー.
     */
    private static final Logger log = LoggerFactory.getLogger(Java8AbstractClassGenerate.class);

    /**
     * 出力テスト.
     *
     * @param args 引数
     */
    public static void main(String[] args) {

        GenerateContext context = new GenerateContext();
        GenerateTarget target = context.newTarget(DefaultTemplate.JAVA8);

        // Setting Class
        ClassModel clazz =
                ClassModel.builder()
                        // ヘッダーコメント
                        .commentHeader("/* Java8 Abstract Class Generate Test. */")
                        // パッケージ
                        .packageName("example")
                        // インポート定義
                        .imports(ImportModel.builder()
                                .packageName("hoge.fuga.piyo")
                                .className("Hoge").build())
                        // アクセス修飾子
                        .accessModifier(AccessModifier.PUBLIC)
                        // 型修飾子
                        .typeModifier(TypeModifier.ABSTRACT)
                        // クラス
                        .classKind(ClassKind.CLASS)
                        // JavaDoc
                        .javaDoc(JavaDocModel.builder()
                                .mainContent("Java8のAbstractClass出力テスト.")
                                .annotation(JavaDocAnnotationModel.builder()
                                        .name("author").content("takashno").build())
                                .annotation(JavaDocAnnotationModel.builder()
                                        .name("since").content("v0.0.2").build())
                                .build())
                        // クラス名
                        .className("AbstractClassGenerateTesterImpl")
                        .implementsClass(ClassModel.builder()
                                .packageName("example")
                                .className("InterfaceGenerateTester")
                                .build())
                        .build();

        target.setClazz(clazz);

        // Setting Method
        MethodModel defaultMethod =
                MethodModel.builder()
                        .accessModifier(AccessModifier.PROTECTED)
                        .methodModifier(MethodModifier.ABSTRACT)
                        .name("abstractMethodSample")
                        .javaDoc(JavaDocUtils.getMethodJavaDocModel("抽象メソッドのサンプル.",
                                JavaDocAnnotationModel.builder()
                                        .name("param")
                                        .content("args")
                                        .content("引数").build()))
                        .arg(ArgModel.builder()
                                .type(TypeUtils.getStringClassModel())
                                .array(true)
                                .name("args").build())
                        .build();
        clazz.getMethods().add(defaultMethod);

        MethodModel method =
                MethodModel.builder()
                        .annotation(AnnotationModel.builder()
                                .packageName("java.lang")
                                .className("Override")
                                .build())
                        .accessModifier(AccessModifier.PUBLIC)
                        .name("methodSample")
                        .javaDoc(JavaDocUtils.getMethodJavaDocModel("メソッドのサンプル.",
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
        Java8Generator converter = new Java8Generator();
        converter.generate(context);

        log.info(sw.toString());

    }


}
