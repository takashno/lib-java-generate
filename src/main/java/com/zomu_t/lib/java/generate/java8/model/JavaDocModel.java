package com.zomu_t.lib.java.generate.java8.model;

import java.io.Serializable;
import java.util.List;

import lombok.*;

/**
 * JavaDocを表すモデル.
 * 
 * 
 * @author takashimanozomu
 *
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JavaDocModel implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** JavaDocのメイン文章.基本的にリストの1要素が1行の扱いとする. */
	@Singular
	private List<String> mainContents;

	/** inheritDocフラグ */
	@Builder.Default
	private boolean inheritDoc = false;

	/** アノテーション */
	@Singular
	private List<JavaDocAnnotationModel> annotations;

}
