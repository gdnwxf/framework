package com.wch.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.wch.utils.BeanUtils;

public class AnnotaionUtils {

	public static <T extends Annotation> T getAnnotation(AnnotatedElement ae, Class<T> annotationType) {
		T ann = ae.getAnnotation(annotationType);
		if (ann == null) {
			for (Annotation metaAnn : ae.getAnnotations()) {
				ann = metaAnn.annotationType().getAnnotation(annotationType);
				if (ann != null) {
					break;
				}
			}
		}
		return ann;
	}
	
	/** 
	 *获取属性上的所有的注解
	 */
	public static Annotation [] getClassAnnotations(Field field) { 
		return  field.getAnnotations();
	}
	
	/**
	 * 获取类上的注解
	 */
	public static Annotation[] getClassAnnotations(Class<?> bridgeMethod) { 
		return bridgeMethod.getAnnotations();
	}
	
	/**
	 * 获取方法上的所有的注解
	 * @param bridgeMethod
	 * @return
	 */
	public static Annotation[] getMethodAnnotations(Method bridgeMethod) {
		return bridgeMethod.getAnnotations();
	}
	
	/**
	 * 获取指定类型的注解
	 * @param bridgeMethod
	 * @param annotationType
	 * @return
	 */
	public static <T> Annotation getAnnotation(Method bridgeMethod,Class<T> annotationType){
	    return null;
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		   Class<?> class1  = Class.forName("com.wch.pojo.UserController");
		   Method [] method = class1.getDeclaredMethods();
		   for (int i = 0; i < method.length; i++) {
			   BeanUtils.printObj(method[i]);
			   Annotation[] annotations  = getMethodAnnotations(method[i]);
			   for (int j = 0; j < annotations.length; j++) {
				   BeanUtils.printObj(annotations[j]);
			   }
		   }	
		   
 	}
	/**
	 * 判断两个方发是否相等
	 * @param bridgeMethod
	 * @param bridgedMethod
	 * @return
	 */
	public static boolean isVisibilityBridgeMethodPair(Method bridgeMethod, Method bridgedMethod) {
		if (bridgeMethod == bridgedMethod) {
			return true;
		}
		return Arrays.equals(bridgeMethod.getParameterTypes(), bridgedMethod.getParameterTypes()) &&
				bridgeMethod.getReturnType().equals(bridgedMethod.getReturnType());
	}
	
}
