package com.wch.pojo;

import com.wch.utils.clazz.ClassUtils;

public class Test {
	public static void main(String[] args) throws Exception {
		Class<?> loadClass = ClassUtils.loadClass("com.wch.pojo.User");
		System.out.println(loadClass.getSuperclass().getMethod("eat").invoke(loadClass.getSuperclass()));
		
		Test test = new Test();


	}





}
