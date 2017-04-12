package com.zomu.t.lib.java.generate.common.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 変換コンテキスト.
 * 
 * @author takashimanozomu
 */
@Data
public class ConvertContext implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** 変換対象 */
	private final List<ConvertTarget> targets = new ArrayList<ConvertTarget>();

	/** エラーを無視して続ける */
	private boolean onErrorResume = false;

	/** 変換失敗対象リスト */
	private List<ConvertFailureTarget> failureTargets;

}
