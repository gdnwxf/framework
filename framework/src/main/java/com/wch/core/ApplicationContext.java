package com.wch.core;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

public class ApplicationContext  {
	/**
	 * 配置前缀
	 */
	public String prefix = "/";
	/**
	 * 配置后缀
	 */
	public String suffix = ".jsp";
	
	//以名称形式存放的bean
	private Map<String, Object> nameBeans = new HashMap<String, Object>();
	//以类型形式存放的bean
	private Map<String, Object> typeBeans = new HashMap<String, Object>();
	

	public static String basePath = "";
	
	private ServletContext servletContext ;
	
	/**
	 */
	public ApplicationContext() {
	}
	/**
	 */
	public ApplicationContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public static String getBasePath() {
		return basePath;
	}

	public static void setBasePath(String basePath) {
		ApplicationContext.basePath = basePath;
	}

}
