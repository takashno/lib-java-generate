package com.zomu.t.java.generate.component.java8.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Delegate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JavaDocAnnotationModel implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** アノテーション名 */
	@NonNull
	private String name;

	/** コンテンツ */
	@Delegate
	private final List<String> contents = new ArrayList<>();

}
