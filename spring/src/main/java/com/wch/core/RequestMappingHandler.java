package com.wch.core;

import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.RequestMapping;

public class RequestMappingHandler extends AnnotationHandler{

	public void processAnnotation(Context context, BeanClassCore bean) {
		Class<?> clazz = bean.getClazz();
		//类上的注解
		RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);
		String[] firstValues = annotation.value();
		 
		//方法上的注解
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			//封装方法
			MethodWraper ctrlMethodWraper = new  MethodWraper();
			ctrlMethodWraper.setMethod(method);
			ctrlMethodWraper.setObject(bean.getObject());
			RequestMapping annotation2 = method.getAnnotation(RequestMapping.class);
			String[] secondValues = annotation2.value();
			for (String second : secondValues) {
				for (String first : firstValues) {
					context.requestPut(first+second, ctrlMethodWraper);
				}
			}
		}
	}

	
	 
}
