package com.wch.test.reflectstatic;
import java.lang.reflect.*; 
 
public class EverythingIsTrue { 

	private static final String DE_STRING  = "21321";
	
   static void setFinalStatic(Field field, Object newValue) throws Exception { 
      field.setAccessible(true); 
 
      Field modifiersField = Field.class.getDeclaredField("modifiers"); 
      modifiersField.setAccessible(true); 
      modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL); 
 
      field.set(null, newValue); 
   } 
   public static void main(String args[]) throws Exception {       
      Field declaredField = EverythingIsTrue.class.getDeclaredField("DE_STRING");
	  setFinalStatic(declaredField, "好的杀毒阿斯"); 
	  
	  System.out.println(declaredField.get(null));
	
      System.out.format("Everything is %s", false); // "Everything is true" 
   } 
} 


class  Test {
	
	private static final String DE_STRING  = "21321";
}