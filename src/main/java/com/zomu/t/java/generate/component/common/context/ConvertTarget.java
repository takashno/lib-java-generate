package com.zomu.t.java.generate.component.common.context;

import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import com.zomu.t.java.generate.component.common.model.Clazz;

/**
 * 変換対象.
 * 
 * @author takashimanozomu
 */
@Data
public class ConvertTarget implements Serializable {

	/** デフォルトシリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** 出力先 */
	private Writer outputWriter;

	/** クラス */
	private Clazz clazz;

	/** スコープ */
	private final List<Object> scopes = new ArrayList<>();

	/** テンプレートパス */
	private String templatePath;

}
