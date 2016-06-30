package com.wch.utils;

public class Assert {
	 
	public static void notNull(Object object){
		 notNull(object, null);
	}
	
	public static void notNull(Object object , String msg){
	 
		if (object == null) {
			throw new NullPointerException(new StringBuilder("").append(object).append(" : ").append(msg == null ? "": null).append(" the Object is null").toString());
		}
	}
	
	public static void isNotNull(Object  object ,String msg) {
		if (object != null) {
			throw new NullPointerException(new StringBuilder("").append(object).append(" : ").append(msg == null ? "": null).append(" the Object is not null").toString());
		}
	}
	
	public static void isNotNull(Object  object) {
		isNotNull(object);
	}
 
}
