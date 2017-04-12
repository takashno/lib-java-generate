package com.zomu.t.lib.java.generate.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * デフォルトテンプレート定義.
 * 
 * @author takashimanozomu
 */
@Getter
@AllArgsConstructor
public enum DefaultTemplate {

	/** Java8用デフォルトテンプレートパス */
	JAVA8("com/zomu/t/java/generate/component/java8/templates/JavaClass.mustache");

	/** パス */
	private String path;
}
