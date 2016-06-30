package com.wch.core;

import java.io.Serializable;

public class BeanClass implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4959763878078218364L;
	private Object object ;
	private Class<?> clazz;
	
	public BeanClass(Class<?> t) {
		this.clazz = t;
	}
	public BeanClass(Object  object, Class<?> t) { 
		this.object = object;
		this.clazz = t;
	}
	public BeanClass(Object  object) { 
		this.object = object;
		this.clazz = object.getClass();
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
	public Class<?> getClazz() {
		return this.clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
 
	
}
