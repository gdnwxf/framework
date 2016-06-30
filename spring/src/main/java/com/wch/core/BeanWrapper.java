package com.wch.core;

public class BeanWrapper {
	
	private Object object;
	private Class<?> clazz;
	private String resourceName;
	
	public BeanWrapper(Object object, Class<?> clazz) {
		super();
		this.object = object;
		this.clazz = clazz;
	}
	public BeanWrapper(Object object, String resourceName) {
		super();
		this.object = object;
		this.resourceName = resourceName;
	}
	public BeanWrapper(Object object, Class<?> clazz, String resourceName) {
		super();
		this.object = object;
		this.clazz = clazz;
		this.resourceName = resourceName;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
}
