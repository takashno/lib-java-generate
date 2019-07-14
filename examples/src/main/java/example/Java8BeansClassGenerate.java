/* Java8 Beans Generate Test. */
package example;

import com.zomu_t.lib.java.generate.common.context.GenerateContext;
import com.zomu_t.lib.java.generate.common.context.GenerateTarget;
import com.zomu_t.lib.java.generate.common.type.DefaultTemplate;
import com.zomu_t.lib.java.generate.java8.generator.Java8Generator;
import com.zomu_t.lib.java.generate.java8.model.ClassModel;
import com.zomu_t.lib.java.generate.java8.model.FieldModel;
import com.zomu_t.lib.java.generate.java8.model.JavaDocAnnotationModel;
import com.zomu_t.lib.java.generate.java8.model.JavaDocModel;
import com.zomu_t.lib.java.generate.java8.type.AccessModifier;
import com.zomu_t.lib.java.generate.java8.util.TypeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;

/**
 * Java8のBeansClass出力テスト.<br>
 *
 * @author takashno
 * @since v0.0.2
 */
public class Java8BeansClassGenerate {

    /**
     * ロガー
     */
    private static final Logger log = LoggerFactory.getLogger(Java8BeansClassGenerate.class);

    /**
     * サンプル出力処理.
     *
     * @param args 引数
     */
    public static void main(String[] args) {

        GenerateContext context = new GenerateContext();
        GenerateTarget target = context.newTarget(DefaultTemplate.JAVA8);

        ClassModel classModel =
                ClassModel.builder()
                        // ファイルヘッダコメント
                        .commentHeader("/* Java8 Beans Class Generate Test. */")
                        // パッケージ
                        .packageName("example")
                        // JavaDoc
                        .javaDoc(JavaDocModel.builder()
                                .mainContent("Beansクラス出力サンプル.")
                                .annotation(JavaDocAnnotationModel.builder()
                                        .name("author")
                                        .content("takashno").build())
                                .build())
                        // アクセス修飾子
                        .accessModifier(AccessModifier.PUBLIC)
                        .className("BeansSample")
                        // フィールド
                        .field(FieldModel.builder()
                                .javaDoc(JavaDocModel.builder()
                                        .mainContent("値1")
                                        .build())
                                .accessModifier(AccessModifier.PRIVATE)
                                .type(TypeUtils.getStringClassModel())
                                .name("value1")
                                .getterAutoCreate(true)
                                .setterAutoCreate(true)
                                .build())
                        .field(FieldModel.builder()
                                .javaDoc(JavaDocModel.builder()
                                        .mainContent("値2")
                                        .build())
                                .accessModifier(AccessModifier.PRIVATE)
                                .type(TypeUtils.getIntegerClassModel())
                                .name("value2")
                                .getterAutoCreate(true)
                                .setterAutoCreate(true)
                                .build())
                        .build();

        // Serializableを実装
        TypeUtils.setImplementsSerializable(classModel);

        // 対象に設定
        target.setClazz(classModel);

        // 出力
        Java8Generator converter = new Java8Generator();
        StringWriter sw = new StringWriter();
        target.setOutputWriter(sw);
        converter.generate(context);
        log.debug(sw.toString());
    }

}
