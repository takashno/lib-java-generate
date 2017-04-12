package com.zomu.t.lib.java.generate.common.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.zomu.t.lib.java.generate.common.context.ConvertContext;
import com.zomu.t.lib.java.generate.common.context.ConvertFailureTarget;
import com.zomu.t.lib.java.generate.common.context.ConvertTarget;
import com.zomu.t.lib.java.generate.common.exception.JavaConverterException;

/**
 * Java変換処理. <br>
 * TODO:きちんと調べきるまでは、テンプレートのキャッシュとかしない.
 * 
 * @author takashimanozomu
 *
 */
@Slf4j
public abstract class JavaConverter {

	/**
	 * 前処理
	 * 
	 * @param context
	 *            {@link ConvertContext}
	 */
	protected abstract void before(ConvertContext context);

	/**
	 * 個別変換対象 - 前処理
	 * 
	 * @param context
	 *            {@link ConvertContext}
	 */
	protected abstract void beforeTarget(ConvertContext context,
			ConvertTarget convertTarget) throws Exception;

	/**
	 * 後処理
	 * 
	 * @param context
	 *            {@link ConvertContext}
	 */
	protected abstract void after(ConvertContext context);

	/**
	 * 個別変換対象 - 後処理
	 * 
	 * @param context
	 *            {@link ConvertContext}
	 */
	protected abstract void afterTarget(ConvertContext context,
			ConvertTarget convertTarget) throws Exception;

	/**
	 * 変換処理
	 * 
	 * @param context
	 */
	public void convert(ConvertContext context) {

		log.debug("start java convert.");

		// 前処理
		before(context);

		if (CollectionUtils.isEmpty(context.getTargets())) {
			log.debug("convert total target : 0");
			return;
		} else {
			log.debug("convert total target : {}", context.getTargets().size());
		}

		if (context.isOnErrorResume()) {
			context.setFailureTargets(new ArrayList<ConvertFailureTarget>());
		}

		// ファクトリの生成
		MustacheFactory mf = new DefaultMustacheFactory();

		for (int i = 0; i < context.getTargets().size(); i++) {

			ConvertTarget target = context.getTargets().get(i);

			if (target.getClazz() == null) {
				log.debug("skip convert. because clazz is null.");
				continue;
			}

			try {

				// 個別変換対象 - 前処理
				beforeTarget(context, target);

				log.debug("--- convert #{} ---", (i + 1));
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
				m.execute(target.getOutputWriter(), scopes.toArray()).flush();

				// 個別変換対象 - 後処理
				afterTarget(context, target);

			} catch (Exception e) {
				if (context.isOnErrorResume()) {
					// エラーが出ても続ける場合は、そのまま続行.
					ConvertFailureTarget cft = new ConvertFailureTarget(target,
							e);
					context.getFailureTargets().add(cft);
					continue;
				} else {
					// エラーで中断.
					throw new JavaConverterException("fail write file.", e);
				}
			}
		}

		if (context.isOnErrorResume()
				&& CollectionUtils.isNotEmpty(context.getFailureTargets())) {
			log.debug("convert failure target : {}", context
					.getFailureTargets().size());
		}

		// 後処理
		after(context);

		log.debug("end java convert.");
	}
}
