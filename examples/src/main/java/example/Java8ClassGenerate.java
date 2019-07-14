/* Java8 Class Generate Test. */
package example;

import com.zomu_t.lib.java.generate.common.context.GenerateContext;
import com.zomu_t.lib.java.generate.common.context.GenerateTarget;
import com.zomu_t.lib.java.generate.common.type.DefaultTemplate;
import com.zomu_t.lib.java.generate.java8.generator.Java8Generator;
import com.zomu_t.lib.java.generate.java8.model.*;
import com.zomu_t.lib.java.generate.java8.type.AccessModifier;
import com.zomu_t.lib.java.generate.java8.type.MethodModifier;
import com.zomu_t.lib.java.generate.java8.util.JavaDocUtils;
import com.zomu_t.lib.java.generate.java8.util.TypeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;

/**
 * Java8のClass出力テスト.<br>
 *
 * @author takashno
 * @since v0.0.2
 */
public class Java8ClassGenerate {

    /**
     * ロガー.
     */
    private static final Logger log = LoggerFactory.getLogger(Java8ClassGenerate.class);

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
                        .commentHeader("/* Java8 Class Generate Test. */")
                        // パッケージ
                        .packageName("example")
                        // インポート定義
                        .imports(ImportModel.builder()
                                .packageName("com.zomu_t.lib.java.generate.common.context")
                                .className("GenerateContext").build())
                        .imports(ImportModel.builder()
                                .packageName("com.zomu_t.lib.java.generate.common.context")
                                .className("GenerateTarget").build())
                        .imports(ImportModel.builder()
                                .packageName("com.zomu_t.lib.java.generate.common.type")
                                .className("DefaultTemplate").build())
                        .imports(ImportModel.builder()
                                .packageName("com.zomu_t.lib.java.generate.java8.generator")
                                .className("Java8Generator").build())
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


        // Generaet
        StringWriter sw = new StringWriter();
        target.setOutputWriter(sw);
        Java8Generator converter = new Java8Generator();
        converter.generate(context);

        log.info(sw.toString());

    }


}
