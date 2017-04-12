package com.zomu.t.java.generate.component.java8;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

import com.zomu.t.java.generate.component.common.context.ConvertContext;
import com.zomu.t.java.generate.component.common.context.ConvertTarget;
import com.zomu.t.java.generate.component.common.converter.JavaConverter;
import com.zomu.t.java.generate.component.common.type.DefaultTemplate;
import com.zomu.t.java.generate.component.java8.converter.Java8Converter;
import com.zomu.t.java.generate.component.java8.model.AnnotationAttributeModel;
import com.zomu.t.java.generate.component.java8.model.AnnotationModel;
import com.zomu.t.java.generate.component.java8.model.ArgModel;
import com.zomu.t.java.generate.component.java8.model.ClassModel;
import com.zomu.t.java.generate.component.java8.model.FieldModel;
import com.zomu.t.java.generate.component.java8.model.ImportModel;
import com.zomu.t.java.generate.component.java8.model.JavaDocAnnotationModel;
import com.zomu.t.java.generate.component.java8.model.JavaDocModel;
import com.zomu.t.java.generate.component.java8.model.MethodModel;
import com.zomu.t.java.generate.component.java8.type.AccessModifier;
import com.zomu.t.java.generate.component.java8.type.ArgModifier;
import com.zomu.t.java.generate.component.java8.util.FieldUtils;
import com.zomu.t.java.generate.component.java8.util.JavaDocUtils;
import com.zomu.t.java.generate.component.java8.util.TypeUtils;

@Slf4j
public class Test {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		ConvertContext context = new ConvertContext();

		ConvertTarget target = new ConvertTarget();

		ClassModel clazz = new ClassModel();

		// ヘッダーコメント
		clazz.setCommentHeader("/* test */");

		// パッケージ
		clazz.setPackageName("hoge.fuga.piyo");

		// インポート定義
		ImportModel im1 = ImportModel.builder().packageName("hoge.fuga.piyo")
				.className("ClassName").methodName("methodName").build();

		// im1.setStaticImport(true);
		clazz.getImports().add(im1);

		// JavaDoc
		JavaDocModel typeJavaDoc = new JavaDocModel();
		typeJavaDoc.getMainContents().add("1行目");
		typeJavaDoc.getMainContents().add("2行目");
		typeJavaDoc.getMainContents().add("3行目");
		JavaDocAnnotationModel jdam1 = JavaDocAnnotationModel.builder()
				.name("author").build();
		jdam1.add("Nozomu Takashima");
		typeJavaDoc.getAnnotations().add(jdam1);

		JavaDocAnnotationModel jdam2 = JavaDocAnnotationModel.builder()
				.name("since").build();
		jdam2.add("V0.0.1");
		typeJavaDoc.getAnnotations().add(jdam2);
		clazz.setJavaDoc(typeJavaDoc);

		// クラスアノテーション
		AnnotationModel typeAnnotation1 = new AnnotationModel();
		typeAnnotation1.setPackageName("lombok");
		typeAnnotation1.setClassName("Data");
		typeAnnotation1.getAttributes().add(
				new AnnotationAttributeModel(true, "value", "値", true));
		typeAnnotation1.setLast(true);
		clazz.getAnnoatations().add(typeAnnotation1);

		// アクセス修飾子
		clazz.setAccessModifier(AccessModifier.PUBLIC);

		// クラス修飾子
		// clazz.setTypeModifier();

		// クラス名
		clazz.setClassName("ConvertSample");
		clazz.getGenericTypes().add(
				TypeUtils.getGenericClassModel("com.example", "Hoge"));
		clazz.getGenericTypes().add(
				TypeUtils.getGenericClassModel("com.example", "Fuga"));

		// ----------------------------------
		// 親クラス
		// ----------------------------------
		ClassModel superClass = new ClassModel();
		superClass.setClassName("AbstractSuperClass");
		superClass.getGenericTypes().add(
				TypeUtils.getGenericClassModel("com.example", "Hoge"));
		superClass.getGenericTypes().add(
				TypeUtils.getGenericClassModel("com.example", "Fuga"));
		clazz.setSuperClass(superClass);

		// ----------------------------------
		// 実装クラス
		// ----------------------------------
		ClassModel implementsClass1 = new ClassModel();
		implementsClass1.setClassName("ImplementsClass1");
		implementsClass1.getGenericTypes().add(
				TypeUtils.getGenericClassModel("com.example", "Hoge"));
		implementsClass1.getGenericTypes().add(
				TypeUtils.getGenericClassModel("com.example", "Fuga"));
		clazz.getImplementsClasses().add(implementsClass1);

		ClassModel implementsClass2 = new ClassModel();
		implementsClass2.setClassName("ImplementsClass2");
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
		fieldAnnotation1.getAttributes().add(
				new AnnotationAttributeModel(true, "value", "値", true));
		fieldAnnotation1.getAttributes().add(
				new AnnotationAttributeModel(true, "name", "名前", true));
		fieldAnnotation1.setLast(true);
		fm1.getAnnotations().add(fieldAnnotation1);
		AnnotationModel fieldAnnotation2 = new AnnotationModel();
		fieldAnnotation2.setPackageName("lombok");
		fieldAnnotation2.setClassName("Pattern");
		fieldAnnotation2.getAttributes().add(
				new AnnotationAttributeModel(true, "regexp", "値", true));
		fieldAnnotation2.setLast(true);
		fm1.getAnnotations().add(fieldAnnotation2);
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
		mm1.setResultType(TypeUtils.getStringClassModel());
		mm1.setName("method1");
		mm1.getAnnotations().add(
				new AnnotationModel("com.example", "GetMapping", false));
		JavaDocAnnotationModel jdam3 = JavaDocAnnotationModel.builder()
				.name("param").build();
		jdam3.add("test");
		jdam3.add("テスト値");
		mm1.setJavaDoc(JavaDocUtils.getMethodJavaDocModel("メソッド1です.", jdam3));
		mm1.getArgs().add(
				new ArgModel(null, null, TypeUtils.getStringClassModel(),
						"arg1", false));
		mm1.getArgs().add(
				new ArgModel(null, null, TypeUtils.getStringClassModel(),
						"arg2", true));
		mm1.getThrowsTypes().add(
				TypeUtils.getGenericClassModel("com.example",
						"OriginalException"));

		clazz.getMethods().add(mm1);

		Java8Converter converter = new Java8Converter();
		converter.convert(context);

		log.info("------------------------------");
		log.info(sw.toString());

	}

}
