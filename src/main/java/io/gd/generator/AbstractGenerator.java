package io.gd.generator;

import io.gd.generator.config.Config;
import io.gd.generator.context.Context;
import io.gd.generator.context.GenLog;
import io.gd.generator.handler.Handler;
import io.gd.generator.util.ClassHelper;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Version;

public abstract class AbstractGenerator<T extends Context> implements Generator {

	static final Logger logger = LoggerFactory.getLogger(AbstractGenerator.class);

	protected Configuration freemarkerConfiguration;

	protected Config config;

	protected List<Handler<T>> handlers;
	
	protected GenLog genLog;
	
	public AbstractGenerator(Config config) {
		this.config = config;
		this.handlers = new ArrayList<>();
	}

	protected void init() throws Exception {
		freemarkerConfiguration = new Configuration(new Version(config.getFreemakerVersion()));
		freemarkerConfiguration.setDefaultEncoding(config.getDefaultEncoding());
		URL url = getClass().getClassLoader().getResource(config.getTemplate());
		freemarkerConfiguration.setDirectoryForTemplateLoading(new File(url.getPath()));
		genLog = new GenLog(config.getGenLogFile());
	}

	protected void destroy() throws Exception {

	}

	@Override
	public void generate() {
		try {
			init();
			/* 获取所有 entity */
			Set<Class<?>> entityClasses = ClassHelper.getClasses(config.getEntityPackage());
			/* 获取所有 query model */
			Map<String, Class<?>> queryModelClasses = ClassHelper.getQuerysClasses(config.getQueryModelPackage());
			/* 遍历生成 */
			// entityClasses.parallelStream().forEach(entityClass -> {
			entityClasses.stream().forEach(entityClass -> {
				if (entityClass.getDeclaredAnnotation(Table.class) != null) {
					try {
						/* 生成mapper */
						generateOne(entityClass, queryModelClasses.get(entityClass.getSimpleName() + config.getQueryModelSuffix()));
						logger.info("generate " + entityClass.getName() + " success");
					} catch (Exception e) {
						logger.error("generate " + entityClass.getName() + " error", e);
					}
				} else {
					logger.info("generate " + entityClass.getName() + " skipped");
				}
			});
		} catch (Exception e) {
			logger.error("generate error", e);
		} finally {
			try {
				destroy();
			} catch (Exception e) {
				logger.error("destroy error", e);
			}
		}
	}

	protected void generateOne(Class<?> entityClass, Class<?> queryModelClass) throws Exception {
		T context = createContext(entityClass, queryModelClass);
		for (Handler<T> handler : handlers) {
			handler.handle(context);
		}
	}

	abstract T createContext(Class<?> entityClass, Class<?> queryModelClass);

}
