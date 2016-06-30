package com.wch.core;

import java.lang.reflect.Method;

public class MethodWraper {
	
	private Method method;
	
	private Object object;
	
	private String [] argsName ;

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public String[] getArgsName() {
		return argsName;
	}

	public void setArgsName(String[] argsName) {
		this.argsName = argsName;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	

}
