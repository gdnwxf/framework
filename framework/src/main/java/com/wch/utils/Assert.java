
 
package com.wch.utils;



 
public class Assert {

	/**
	 * 判断是否是空
	 * TODO 请在此处添加注释
	 * @param object
	 */
	public static void notNull(Object object){
		if (object == null) {
			throw new NullPointerException(object  + " the Object is null");
		}
	}
	
	/**
	 * 判断是否是空
	 * TODO 请在此处添加注释
	 * @param object
	 */
	public static void notNull(Object object ,String msg){
		if (object == null) {
			throw new NullPointerException(  object + ( msg == null? "": msg)  + " the Object is null");
		}
	}
	
	public static void isNull(Object  object) {
		if (object != null) {
			throw new NullPointerException(object  + " the Object is not null");
		}
	}
	
	/**
	 * 判断是否是空
	 * TODO 请在此处添加注释
	 * @param object
	 */
	public static void isNull(Object object ,String msg){
		if (object != null) {
			throw new NullPointerException(object  + (msg == null? "" : msg) + " the Object is null");
		}
	}
}
