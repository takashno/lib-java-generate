package com.zomu_t.lib.java.generate.common.converter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.zomu_t.lib.java.generate.common.context.GenerateTarget;
import com.zomu_t.lib.java.generate.common.context.GenerateContext;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.googlejavaformat.java.Formatter;
import com.zomu_t.lib.java.generate.common.context.GenerateFailureTarget;
import com.zomu_t.lib.java.generate.common.exception.JavaGenerateException;

/**
 * Java生成処理. <br>
 * TODO:きちんと調べきるまでは、テンプレートのキャッシュとかしない.
 *
 * @author takashno
 */
@Slf4j
public abstract class JavaGenerator {

    /**
     * 前処理
     *
     * @param context {@link GenerateContext}
     */
    protected abstract void before(GenerateContext context);

    /**
     * 個別変換対象 - 前処理
     *
     * @param context {@link GenerateContext}
     */
    protected abstract void beforeTarget(GenerateContext context,
                                         GenerateTarget generateTarget) throws Exception;

    /**
     * 後処理
     *
     * @param context {@link GenerateContext}
     */
    protected abstract void after(GenerateContext context);

    /**
     * 個別変換対象 - 後処理
     *
     * @param context {@link GenerateContext}
     */
    protected abstract void afterTarget(GenerateContext context,
                                        GenerateTarget generateTarget) throws Exception;

    /**
     * 変換処理
     *
     * @param context
     */
    public void generate(GenerateContext context) {

        log.debug("start java generate.");

        // 前処理
        before(context);

        if (CollectionUtils.isEmpty(context.getTargets())) {
            log.debug("generate total target : 0");
            return;
        } else {
            log.debug("generate total target : {}", context.getTargets().size());
        }

        if (context.isOnErrorResume()) {
            context.setFailureTargets(new ArrayList<GenerateFailureTarget>());
        }

        // ファクトリの生成
        MustacheFactory mf = new DefaultMustacheFactory();

        for (int i = 0; i < context.getTargets().size(); i++) {

            GenerateTarget target = context.getTargets().get(i);

            if (target.getClazz() == null) {
                log.debug("skip convert. because clazz is null.");
                continue;
            }

            try {

                // 個別変換対象 - 前処理
                beforeTarget(context, target);

                log.debug("--- generate #{} ---", (i + 1));
                log.debug("template     : {}", target.getTemplatePath());
                log.debug("clazz        : {}", target.getClazz().toString());
                log.debug("scope size   : {}", target.getScopes().size());

                // テンプレートのコンパイル
                Mustache m = mf.compile(target.getTemplatePath());

                // スコープの変換
                List<Object> scopes = new ArrayList<>();
                scopes.add(target.getClazz());
                scopes.addAll(target.getScopes());

                // 変換処理
                if (context.isDoFormat()) {

                    // 一旦文字列に書き起こす
                    StringWriter sw = new StringWriter();
                    m.execute(sw, scopes.toArray()).flush();

                    System.out.println(sw.toString());

                    // フォーマット処理
                    String formattedSource = new Formatter().formatSource(sw
                            .toString());

                    // 指定されたWriterへ書き込む
                    target.getOutputWriter().write(formattedSource);
                    target.getOutputWriter().flush();

                } else {
                    m.execute(target.getOutputWriter(), scopes.toArray())
                            .flush();
                }

                // 個別変換対象 - 後処理
                afterTarget(context, target);

            } catch (Exception e) {
                if (context.isOnErrorResume()) {
                    // エラーが出ても続ける場合は、そのまま続行.
                    GenerateFailureTarget cft = new GenerateFailureTarget(target,
                            e);
                    context.getFailureTargets().add(cft);
                    continue;
                } else {
                    // エラーで中断.
                    throw new JavaGenerateException("fail write file.", e);
                }
            }
        }

        if (context.isOnErrorResume()
                && CollectionUtils.isNotEmpty(context.getFailureTargets())) {
            log.debug("generate failure target : {}", context
                    .getFailureTargets().size());
        }

        // 後処理
        after(context);

        log.debug("end java generate.");
    }
}
