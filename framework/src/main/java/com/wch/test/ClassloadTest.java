package com.wch.test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassloadTest {
	
	

		public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
			
			
			
			Class<?> czzClass = StaticTest.class;
			System.out.println(czzClass.hashCode());
			System.out.println(" ++++++++++++++++++ ");
//			forName1.setPrefix("----");
			MyClassloader myClassloader = new MyClassloader("D:\\mygithub\\github\\project\\framework\\framework\\target\\classes" , new String [] {"com.wch.test.StaticTest" });
			Class<?> loadClass  = myClassloader.loadClass("com.wch.test.StaticTest");
			System.out.println(loadClass.getClassLoader());
			System.out.println(loadClass.hashCode());
			  Field method = loadClass.getDeclaredField("abc");  
		        System.out.println(method.get(loadClass.newInstance()));  
		        
		        StaticTest newInstance =   (StaticTest) loadClass.newInstance();
		         
		        
//		        
//			StaticTest forName2 =     newInstance ;
//			StaticTest forName3 =    newInstance ;
//		
//			forName2.load();
//			forName1 =  forName2;
//			System.out.println(forName2.getAbc() + "  ---- " +  forName2 .hashCode()) ;
			
//			Class<StaticTest> forName2 =  (Class<StaticTest>) Class.forName("com.wch.test.StaticTest") ;
			
//			System.out.println(forName1.hashCode() + " "  + forName2.hashCode());
			 
//			forName1.getAbc().replace("wch", "231231") ;
//			System.out.println(StaticTest.getAbc());
		}
}
