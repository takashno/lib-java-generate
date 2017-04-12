package com.zomu.t.lib.java.generate.java8.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private final List<String> mainContents = new ArrayList<>();

	/** inheritDocフラグ */
	private boolean inheritDoc;

	/** アノテーション */
	private final List<JavaDocAnnotationModel> annotations = new ArrayList<>();

}
