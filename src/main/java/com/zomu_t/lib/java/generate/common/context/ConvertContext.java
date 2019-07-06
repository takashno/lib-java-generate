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
public class ConvertContext implements Serializable {

    /**
     * デフォルトシリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 変換対象
     */
    private final List<ConvertTarget> targets = new ArrayList<ConvertTarget>();

    /**
     * エラーを無視して続ける
     */
    private boolean onErrorResume = false;

    /**
     * ソースコードのフォーマットを行うかどうか
     */
    private boolean doFormat = true;

    /**
     * 変換失敗対象リスト
     */
    private List<ConvertFailureTarget> failureTargets;

    /**
     * 変換対象を追加する.
     *
     * @param target 変換対象
     */
    public void addTarget(final ConvertTarget target) {
        this.targets.add(target);
    }

    public ConvertTarget newTarget() {
        ConvertTarget newTarget = new ConvertTarget();
        this.targets.add(newTarget);
        return newTarget;
    }

    public ConvertTarget newTarget(DefaultTemplate template) {
        ConvertTarget newTarget = new ConvertTarget();
        newTarget.setTemplatePath(template.getPath());
        this.targets.add(newTarget);
        return newTarget;
    }

}
