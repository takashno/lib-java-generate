package com.zomu_t.lib.java.generate.common.context;

import com.zomu_t.lib.java.generate.common.model.Clazz;
import lombok.Data;

import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成対象.
 *
 * @author takashno
 */
@Data
public class GenerateTarget implements Serializable {

    /**
     * デフォルトシリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 出力先
     */
    private Writer outputWriter;

    /**
     * クラス
     */
    private Clazz clazz;

    /**
     * スコープ
     */
    private final List<Object> scopes = new ArrayList<>();

    /**
     * テンプレートパス
     */
    private String templatePath;

}
