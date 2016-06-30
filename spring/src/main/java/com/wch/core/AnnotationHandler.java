package com.wch.core;

abstract class AnnotationHandler {
	
	public abstract void processAnnotation(Context context, BeanClassCore bean);
	
	public void process(Context context, BeanClassCore bean) {
		this.processAnnotation(context, bean);
	}
	
}
