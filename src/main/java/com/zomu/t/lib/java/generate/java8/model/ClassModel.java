package com.zomu.t.lib.java.generate.java8.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Singular;

import com.zomu.t.lib.java.generate.common.model.Clazz;
import com.zomu.t.lib.java.generate.java8.type.AccessModifier;
import com.zomu.t.lib.java.generate.java8.type.ClassKind;
import com.zomu.t.lib.java.generate.java8.type.TypeModifier;

/**
 * Javaクラスを表すモデル.
 * 
 * @author takashimanozomu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassModel implements Clazz, Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** ヘッダー部コメント */
	private String commentHeader;

	/** パッケージ名 */
	private String packageName;

	/** インポート */
	@Singular("imports")
	private final List<ImportModel> imports = new ArrayList<>();

	/** クラスに対するJavaDoc */
	private JavaDocModel javaDoc;

	/** クラスに対するアノテーション */
	@Singular("annotations")
	private final List<AnnotationModel> annotations = new ArrayList<>();

	/** アクセス修飾子 */
	@Builder.Default
	private AccessModifier accessModifier = AccessModifier.PUBLIC;

	/** クラス修飾子 */
	private TypeModifier typeModifier;

	/** クラス種別 */
	@Builder.Default
	private ClassKind classKind = ClassKind.CLASS;

	/** クラス名 */
	@NonNull
	private String className;

	/** スーパークラス */
	private ClassModel superClass;

	/** 実装インタフェース */
	@Singular("implementsClasses")
	private final List<ClassModel> implementsClasses = new ArrayList<>();

	/** 総称型 */
	@Singular("genericTypes")
	private final List<ClassModel> genericTypes = new ArrayList<>();

	/** フィールド */
	@Singular("fields")
	private final List<FieldModel> fields = new ArrayList<>();

	/** メソッド */
	@Singular("methods")
	private final List<MethodModel> methods = new ArrayList<>();

	/** プリミティブ型フラグ */
	@Builder.Default
	private boolean primitive = false;

	/** 最終フラグ */
	@Builder.Default
	private boolean last = false;

	/**
	 * 総称型を保持しているかどうか判定します.
	 * 
	 * @return
	 */
	public boolean hasGenericTypes() {
		return genericTypes.size() > 0;
	}

	/**
	 * 実装インタフェースを保持しているかどうか判定します.
	 * 
	 * @return
	 */
	public boolean hasImplementsClasses() {
		return implementsClasses.size() > 0;
	}

}
