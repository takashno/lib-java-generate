package com.zomu.t.lib.java.generate.java8.converter;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ClassUtils;

import org.apache.commons.lang3.StringUtils;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.zomu.t.lib.java.generate.common.context.ConvertContext;
import com.zomu.t.lib.java.generate.common.context.ConvertTarget;
import com.zomu.t.lib.java.generate.common.converter.JavaConverter;
import com.zomu.t.lib.java.generate.java8.model.AnnotationModel;
import com.zomu.t.lib.java.generate.java8.model.ClassModel;
import com.zomu.t.lib.java.generate.java8.model.FieldModel;
import com.zomu.t.lib.java.generate.java8.model.ImportModel;
import com.zomu.t.lib.java.generate.java8.model.LogicDetailModel;
import com.zomu.t.lib.java.generate.java8.model.MethodModel;

/**
 * Java8用の変換処理.
 * 
 * @author takashimanozomu
 */
@Slf4j
public class Java8Converter extends JavaConverter {

	/**
	 * {@inheritDoc}
	 * <hr/>
	 * scopesに設定したオブジェクトに対して、何かしらの前処理を行いたい場合は本メソッドをオーバーライドして実装してください.
	 */
	@Override
	protected void before(ConvertContext context) {

		// 変換対象に対して前処理を行う.
		for (ConvertTarget convertTarget : context.getTargets()) {

			if (convertTarget.getClazz() != null
					&& convertTarget.getClazz() instanceof ClassModel) {

				ClassModel clazz = ClassModel.class.cast(convertTarget
						.getClazz());

				// クラスモデルの最終フラグ調整
				adjustLastFlg(clazz);

				// インポート構成の調整
				adjustImport(clazz);

			}

		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void beforeTarget(ConvertContext context,
			ConvertTarget convertTarget) throws Exception {

		if (convertTarget.getClazz() != null
				&& convertTarget.getClazz() instanceof ClassModel) {

			ClassModel clazz = ClassModel.class.cast(convertTarget.getClazz());

			// ロジックの生成
			generateLogic(context, convertTarget, clazz);

		}

	}

	/**
	 * 変換対象のロジックがあるのであれば変換してロジックのコンテンツに加える.
	 * 
	 * @param context
	 * @param convertTarget
	 * @param classModel
	 * @throws Exception
	 */
	private void generateLogic(ConvertContext context,
			ConvertTarget convertTarget, ClassModel classModel)
			throws Exception {

		if (CollectionUtils.isNotEmpty(classModel.getMethods())) {

			for (MethodModel mm : classModel.getMethods()) {

				if (mm.getLogic() != null
						&& CollectionUtils.isNotEmpty(mm.getLogic()
								.getDetails())) {

					for (int i = 0; i < mm.getLogic().getDetails().size(); i++) {

						LogicDetailModel ldm = mm.getLogic().getDetails()
								.get(i);

						log.debug("--- logic convert #{} ---", (i + 1));
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
		if (classModel.getAnnoatations() != null) {
			for (AnnotationModel am : classModel.getAnnoatations()) {
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

		importCollect(classModel, imports);

		imports.forEach((k, v) -> {

			for (ImportModel im : classModel.getImports()) {

				String fqcn = StringUtils.join(im.getPackageName(),
						im.getClassName());

				if (StringUtils.isEmpty(fqcn)) {
					// 追加対象がないのであればSKIP
					continue;
				}

				// 第一判定：FQCNの一致、ワイルドカードが一致、staticインポートが一致であれば追加不要
				// kは収集処理にて各モデルから収集しているためクラス名までを含んだものでメソッド名は含まない
				if (StringUtils.equals(fqcn, k)
						&& im.isWildcard() == v.isWildcard()
						&& im.isStaticImport() == v.isStaticImport()) {
					continue;
				}

				// 第二判定：FQCNが途中まで一緒で、ワイルドカードが指定されているものが既存にあれば追加不要
				String[] add = k.split("\\.");
				String[] exists = fqcn.split("\\.");
				if (add.length > exists.length) {

				}

			}

			if (!classModel.getImports().contains(v)) {
				classModel.getImports().add(v);
			}
		});

	}

	/**
	 * 
	 * @param classModel
	 * @param imports
	 */
	private void importCollect(ClassModel classModel,
			Map<String, ImportModel> imports) {

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

						});

		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void after(ConvertContext context) {
		// Do nothing
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void afterTarget(ConvertContext context,
			ConvertTarget convertTarget) throws Exception {
		// Do nothing
	}

}
