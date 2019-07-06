package com.zomu_t.lib.java.generate.java8.model;

import java.io.Serializable;

import lombok.*;

/**
 * 返却値を表すモデル.
 * 
 * @author takashno
 *
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReturnModel implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** 型 */
	@NonNull
	private ClassModel type;

	/** 配列フラグ */
	@Builder.Default
	private boolean array = false;

}
