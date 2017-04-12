package com.zomu.t.java.generate.component.java8.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * 
 * @author takashimanozomu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImportModel implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** パッケージ名 */
	@NonNull
	private String packageName;

	/** staticインポートフラグ */
	private boolean staticImport;

	/** クラス名 */
	private String className;

	/** メソッド名 */
	private String methodName;

	/** ワイルドカードフラグ */
	private boolean wildcard;

}
