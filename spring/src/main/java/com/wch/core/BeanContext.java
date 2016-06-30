package com.wch.core;

import java.util.Map;

public abstract class BeanContext implements Context{

	private final Map<Class<?>, Object> autoWiredMapping;
	private final Map<String, Object> resourceMapping ;
	
	public  BeanContext(final Map<Class<?>, Object> autoWiredMapping ,final Map<String, Object> resourceMapping ) {
		this.autoWiredMapping  = autoWiredMapping;
		this.resourceMapping = resourceMapping;
	}
	
	public void requestPut(String mapingURL, MethodWraper methodWraper ) {
		throw  new UnsupportedOperationException("不支持操作 ");
	}
	public void getPut(String mapingURL, MethodWraper methodWraper) {
		throw  new UnsupportedOperationException("不支持操作 ");
	}
	public void postPut(String mapingURL, MethodWraper methodWraper) {
		throw  new UnsupportedOperationException("不支持操作 ");
	}
	public MethodWraper getRequestMapping(int scope,String mapingURL) {
		throw  new UnsupportedOperationException("不支持操作 ");
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<T> clazz) {
		return (T) autoWiredMapping.get(clazz);
	}
	
	public Object getBean(String beanName) {
		return resourceMapping.get(beanName);
	}
	
	
}
