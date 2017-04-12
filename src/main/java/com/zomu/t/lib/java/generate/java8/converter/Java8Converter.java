package com.zomu.t.lib.java.generate.java8.converter;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;

import com.zomu.t.lib.java.generate.common.context.ConvertContext;
import com.zomu.t.lib.java.generate.common.context.ConvertTarget;
import com.zomu.t.lib.java.generate.common.converter.JavaConverter;
import com.zomu.t.lib.java.generate.java8.model.AnnotationModel;
import com.zomu.t.lib.java.generate.java8.model.ClassModel;
import com.zomu.t.lib.java.generate.java8.model.FieldModel;
import com.zomu.t.lib.java.generate.java8.model.MethodModel;

/**
 * 
 * @author takashimanozomu
 *
 */
@Slf4j
public class Java8Converter extends JavaConverter {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void before(ConvertContext context) {

		for (ConvertTarget target : context.getTargets()) {

			ClassModel clazz = ClassModel.class.cast(target.getClazz());

			adjustLastFlg(clazz);

		}

	}

	/**
	 * 総称型の最終フラグの調整処理
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
	 * {@inheritDoc}
	 */
	@Override
	protected void after(ConvertContext context) {
		// TODO Auto-generated method stub

	}

}
