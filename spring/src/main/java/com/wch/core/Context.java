package com.wch.core;

public abstract interface Context {
	
	public final static int  REQUEST_SCOPE = 0 ;
	public final static int  GET_SCOPE = 1 ;
	public final static int  POST_SCOPE = 2 ;
	
	public void requestPut(String mapingURL, MethodWraper methodWraper );
	public void getPut(String mapingURL, MethodWraper methodWraper);
	public void postPut(String mapingURL, MethodWraper methodWraper);
	public MethodWraper getRequestMapping(int scope,String mapingURL);
	public <T>T getBean(Class<T> clazz);
	public Object getBean(String beanName);
}
