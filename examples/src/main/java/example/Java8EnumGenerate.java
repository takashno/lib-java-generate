/* Java8 Enum Generate Test. */
package example;

import com.zomu_t.lib.java.generate.common.context.GenerateContext;
import com.zomu_t.lib.java.generate.common.context.GenerateTarget;
import com.zomu_t.lib.java.generate.common.type.DefaultTemplate;
import com.zomu_t.lib.java.generate.java8.generator.Java8Generator;
import com.zomu_t.lib.java.generate.java8.model.*;
import com.zomu_t.lib.java.generate.java8.type.AccessModifier;
import com.zomu_t.lib.java.generate.java8.type.ClassKind;
import com.zomu_t.lib.java.generate.java8.util.TypeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;

/**
 * Java8のENUM出力サンプル.
 *
 * @author takashno
 */
public class Java8EnumGenerate {

    /**
     * ロガー
     */
    private static final Logger log = LoggerFactory.getLogger(Java8EnumGenerate.class);

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
                .packageName("example")
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
                        .initConstructor(true)
                        .build())
                // 列挙子
                .enumerator(EnumeratorModel.builder()
                        .javaDoc(JavaDocModel.builder()
                                .mainContent("ほげ").build())
                        .name("HOGE")
                        .value(EnumeratorValueModel.builder()
                                .stringAttr(true)
                                .value("ほげ")
                                .build())
                        .build())
                .enumerator(EnumeratorModel.builder()
                        .javaDoc(JavaDocModel.builder()
                                .mainContent("ふが").build())
                        .name("FUGA")
                        .value(EnumeratorValueModel.builder()
                                .stringAttr(true)
                                .value("ふが")
                                .build())
                        .build())
                // コンストラクタ
                .constructor(ConstructorModel.builder()
                        .javaDoc(JavaDocModel.builder()
                                .mainContent("プライベートコンストラクタ")
                                .build())
                        .accessModifier(AccessModifier.PRIVATE)
                        .constructorAutoCreate(true)
                        .build())
                .build());

        // 出力
        Java8Generator converter = new Java8Generator();
        StringWriter sw = new StringWriter();
        target.setOutputWriter(sw);
        converter.generate(context);
        log.debug(sw.toString());
    }
}
