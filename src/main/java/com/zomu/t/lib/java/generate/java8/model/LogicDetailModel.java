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
 * ロジック詳細を表すモデル.
 * 
 * @author takashimanozomu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogicDetailModel implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** テンプレートパス（こちらが指定されていれば優先する） */
	private String templatePath;

	/** スコープ（こちらが指定されていれば優先する） */
	@Singular("scopes")
	private final List<Object> scopes = new ArrayList<>();

}
