package com.wch.test.reflectstatic;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 
 * @author wch
 *
 */
public class ReflectStaticTest {

	private static final String DE = "12321";

	private static final String BDE =  reflectGet("DE");
	
	public static void main(String[] args)throws Exception  {

		setStaticValue("DE", "dsjdjsa");
		System.out.println(reflectGet("DE"));
	}
	
	 public static void setStaticValue(String field, String value) throws Exception {

		Field declaredField = ReflectStaticTest.class.getDeclaredField(field);
			
		declaredField.setAccessible(true);
		
		Field modifiers = Field.class.getDeclaredField("modifiers");
		modifiers.setAccessible(true);
		// 此处的作用就是把field的final修饰给干掉
		modifiers.setInt(declaredField,declaredField.getModifiers() &~Modifier.FINAL);
		
		declaredField.set(null, value);
		 
	}
	
	public static String reflectGet(String de) {
		try {
			Field reflectStaticTest = ReflectStaticTest.class.getDeclaredField(de);
			reflectStaticTest.setAccessible(true);
			
			Field modifiers = Field.class.getDeclaredField("modifiers");
			modifiers.setAccessible(true);
			// 此处的作用就是把field的final修饰给干掉
			modifiers.setInt(reflectStaticTest,reflectStaticTest.getModifiers() &~Modifier.FINAL);
			
			return   (String) reflectStaticTest.get(null);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}


