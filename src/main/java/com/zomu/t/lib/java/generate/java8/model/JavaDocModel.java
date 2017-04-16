package com.zomu.t.lib.java.generate.java8.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

/**
 * JavaDocを表すモデル.
 * 
 * 
 * @author takashimanozomu
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JavaDocModel implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** JavaDocのメイン文章.基本的にリストの1要素が1行の扱いとする. */
	@Singular("mainContents")
	private final List<String> mainContents = new ArrayList<>();

	/** inheritDocフラグ */
	@Builder.Default
	private boolean inheritDoc = false;

	/** アノテーション */
	@Singular("annotations")
	private final List<JavaDocAnnotationModel> annotations = new ArrayList<>();

}
