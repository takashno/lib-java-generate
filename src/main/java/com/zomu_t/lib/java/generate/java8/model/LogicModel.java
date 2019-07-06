package com.zomu_t.lib.java.generate.java8.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

/**
 * ロジックを表すモデル.
 *
 * @author takashimanozomu
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogicModel implements Serializable {

    /**
     * デフォルトシリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ロジック本文
     */
    @Builder.Default
    private final StringBuilder content = new StringBuilder();

    /**
     * ロジック詳細リスト
     */
    @Singular
    private List<LogicDetailModel> details = new ArrayList<>();

}
