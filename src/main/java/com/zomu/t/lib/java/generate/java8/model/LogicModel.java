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
 * ロジックを表すモデル.
 * 
 * @author takashimanozomu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogicModel implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** ロジック本文 */
	@Builder.Default
	private final StringBuilder content = new StringBuilder();

	/** ロジック詳細リスト */
	@Singular("details")
	private List<LogicDetailModel> details = new ArrayList<>();

}
