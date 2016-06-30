package com.wch.mybatis.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;

/**
 * The Class XMLMapperLoader.
 * 
 * @version 2013年11月10日 下午1:43:45
 */
public class XMLMapperLoader implements DisposableBean, InitializingBean, ApplicationContextAware {

	/** The context. */
	private ConfigurableApplicationContext context = null;


	private transient Resource[] resources = null;

	/** The file mapping. */
	private static Map<String, String> fileMapping = new HashMap<String, String>();

	/** The scanner. */
	private static Scanner scanner = null;
	

	/** The service. */
	private  ScheduledExecutorService service = null;

	/**
	 * Sets the application context.
	 * 
	 * @param applicationContext the new application context
	 * @throws BeansException the beans exception
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = (ConfigurableApplicationContext) applicationContext;

	}

	/**
	 * @throws Exception
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		try {		
			service = Executors.newScheduledThreadPool(1);
			// 获取xml所在包
			SqlSessionFactoryBean config = context.getBean(SqlSessionFactoryBean.class);
			Field field = config.getClass().getDeclaredField("mapperLocations");
			field.setAccessible(true);
			resources = (Resource[]) field.get(config);
			// 触发文件监听事件
			scanner = new Scanner();
			scanner.scan();

			service.scheduleAtFixedRate(new Task(), 5, 5, TimeUnit.SECONDS);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * The Class Task.
	 */
	class Task implements Runnable {

		/**
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			try {
				if (scanner.isChanged()) {
					System.out.println("*Mapper.xml文件改变,重新加载.");
					scanner.reloadXML();
					System.out.println("加载完毕.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * The Class Scanner.
	 */
	@SuppressWarnings({ "rawtypes" })
	class Scanner {
 
		/**
		 * Gets the resource.
		 * 
		 * @param basePackage the base package
		 * @param pattern the pattern
		 * @return the resource
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public Resource[] getResource() throws IOException {
			Resource[] resources = XMLMapperLoader.this.resources;
			return resources;
		}

		/**
		 * Reload xml.
		 * 
		 * @throws Exception the exception
		 */
		public void reloadXML() throws Exception {
			SqlSessionFactory factory = context.getBean(SqlSessionFactory.class);
			Configuration configuration = factory.getConfiguration();
			// 移除加载项
			removeConfig(configuration);
			// 重新扫描加载
			Resource[] resources = XMLMapperLoader.this.resources;
			if (resources != null) {
				for (int i = 0; i < resources.length; i++) {
					if (resources[i] == null) {
						continue;
					}
					try {
						XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(resources[i].getInputStream(), configuration, resources[i].toString(), configuration.getSqlFragments());
						xmlMapperBuilder.parse();
					} catch (Exception e) {
						throw new NestedIOException("Failed to parse mapping resource: '" + resources[i] + "'", e);
					} finally {
						ErrorContext.instance().reset();
					}
				}
			}

		}

		/**
		 * Remove config.
		 * 
		 * @param configuration the configuration
		 * @throws Exception the exception
		 */
		private void removeConfig(Configuration configuration) throws Exception {
			Class<?> classConfig = configuration.getClass();
			clearMap(classConfig, configuration, "mappedStatements");
			clearMap(classConfig, configuration, "caches");
			clearMap(classConfig, configuration, "resultMaps");
			clearMap(classConfig, configuration, "parameterMaps");
			clearMap(classConfig, configuration, "keyGenerators");
			clearMap(classConfig, configuration, "sqlFragments");

			clearSet(classConfig, configuration, "loadedResources");

		}

		/**
		 * Clear map.
		 * 
		 * @param classConfig the class config
		 * @param configuration the configuration
		 * @param fieldName the element name
		 * @throws Exception the exception
		 */
		private void clearMap(Class<?> classConfig, Configuration configuration, String fieldName) throws Exception {
			Field field = classConfig.getDeclaredField(fieldName);
			field.setAccessible(true);
			Map mapConfig = (Map) field.get(configuration);
			mapConfig.clear();
		}

		/**
		 * Clear set.
		 * 
		 * @param classConfig the class config
		 * @param configuration the configuration
		 * @param fieldName the element name
		 * @throws Exception the exception
		 */
		private void clearSet(Class<?> classConfig, Configuration configuration, String fieldName) throws Exception {
			Field field = classConfig.getDeclaredField(fieldName);
			field.setAccessible(true);
			Set setConfig = (Set) field.get(configuration);
			setConfig.clear();
		}

		/**
		 * Scan.
		 * 
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public void scan() throws IOException { 
			if (!fileMapping.isEmpty()) {
				return;
			}
			Resource[] resources = XMLMapperLoader.this.resources;
			if (resources != null) {
				for (int i = 0; i < resources.length; i++) {
					String multi_key = getValue(resources[i]);
					fileMapping.put(resources[i].getFilename(), multi_key);
				}
			}
		}

		/**
		 * Gets the value.
		 * 
		 * @param resource the resource
		 * @return the value
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		private String getValue(Resource resource) throws IOException {
			String contentLength = String.valueOf((resource.contentLength()));
			String lastModified = String.valueOf((resource.lastModified()));
			return new StringBuilder(contentLength).append(lastModified).toString();
		}

		/**
		 * Checks if is changed.
		 * 
		 * @return true, if is changed
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public boolean isChanged() throws IOException {
			boolean isChanged = false;
			Resource[] resources = XMLMapperLoader.this.resources;
			if (resources != null) {
				for (int i = 0; i < resources.length; i++) {
					String name = resources[i].getFile().getPath();
					String value = fileMapping.get(name);
					String multi_key = getValue(resources[i]);
					if (!multi_key.equals(value)) {
						isChanged = true;
						System.out.println(name+ " 已改变  ");
						fileMapping.put(name, multi_key);
					}
				}
			}
			return isChanged;
		}
	}

	/**
	 * @throws Exception
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	@Override
	public void destroy() throws Exception {
		if (service != null) {
			service.shutdownNow();
		}
	}

}
