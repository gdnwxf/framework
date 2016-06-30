package com.wch.core;

import java.util.HashMap;
import java.util.Map;

public class WebContext extends BeanContext  {
	
	public static Map<String, MethodWraper> requestMapping = new HashMap<String, MethodWraper>();
	public static Map<String, MethodWraper> getRequestMapping = new HashMap<String, MethodWraper>();
	public static Map<String, MethodWraper> postRequestMapping = new HashMap<String, MethodWraper>();
	
	public WebContext( Map<Class<?>, Object> autoWiredMapping , Map<String, Object> resourceMapping ) {
		super(autoWiredMapping, resourceMapping);
	}
	
	public void requestPut(String mapingURL, MethodWraper methodWraper ) {
		requestMapping.put(mapingURL, methodWraper);
	}
	public void getPut(String mapingURL, MethodWraper methodWraper) {
		getRequestMapping.put(mapingURL, methodWraper);
	}
	public void postPut(String mapingURL, MethodWraper methodWraper) {
		postRequestMapping.put(mapingURL, methodWraper);
	}
	 
	public MethodWraper getRequestMapping(int scope,String mapingURL ) {
		switch (scope) {
		case GET_SCOPE:
			return  getRequestMapping.get(mapingURL);
		case POST_SCOPE:
			return  postRequestMapping.get(mapingURL);
		case REQUEST_SCOPE:
			return  requestMapping.get(mapingURL);
		default:
			return null;
		}
	}
	
}
