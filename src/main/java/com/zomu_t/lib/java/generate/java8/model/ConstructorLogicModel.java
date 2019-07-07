package com.zomu_t.lib.java.generate.java8.model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ConstructorLogicModel implements Serializable {

    /**
     * デフォルトシリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタロジック本文
     */
    @Builder.Default
    private final StringBuilder content = new StringBuilder();

    /**
     * コンストラクタロジック詳細リスト
     */
    @Singular
    private List<ConstructorLogicDetailModel> details = new ArrayList<>();
}
