package com.zomu_t.lib.java.generate.common.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.zomu_t.lib.java.generate.common.type.DefaultTemplate;
import lombok.Data;

/**
 * 変換コンテキスト.
 *
 * @author takashimanozomu
 */
@Data
public class GenerateContext implements Serializable {

    /**
     * デフォルトシリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 変換対象
     */
    private final List<GenerateTarget> targets = new ArrayList<GenerateTarget>();

    /**
     * エラーを無視して続ける
     */
    private boolean onErrorResume = false;

    /**
     * ソースコードのフォーマットを行うかどうか
     */
    private boolean doFormat = true;

    /**
     * 生成失敗対象リスト
     */
    private List<GenerateFailureTarget> failureTargets;

    /**
     * 生成対象を追加する.
     *
     * @param target 変換対象
     */
    public void addTarget(final GenerateTarget target) {
        this.targets.add(target);
    }

    /**
     * 生成対象を作成します.
     *
     * @return 生成対象
     */
    public GenerateTarget newTarget() {
        GenerateTarget newTarget = new GenerateTarget();
        this.targets.add(newTarget);
        return newTarget;
    }

    /**
     * 生成対象を作成します.
     *
     * @param template テンプレート
     * @return 生成対象
     */
    public GenerateTarget newTarget(DefaultTemplate template) {
        GenerateTarget newTarget = new GenerateTarget();
        newTarget.setTemplatePath(template.getPath());
        this.targets.add(newTarget);
        return newTarget;
    }

}
