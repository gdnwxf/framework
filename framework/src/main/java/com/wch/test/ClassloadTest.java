package com.wch.test;

public class ClassloadTest {
	
	

		public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
			
			
			StaticTest  forName1 = new StaticTest();
			
			System.out.println(forName1.getAbc()  + " ==== " +forName1.hashCode());
			forName1.setPrefix("----");
			MyClassloader myClassloader = new MyClassloader();
			StaticTest forName2 =   (StaticTest) myClassloader.loadClass("com.wch.test.StaticTest").newInstance() ;
			StaticTest forName3 =   (StaticTest) myClassloader.loadClass("com.wch.test.StaticTest").newInstance() ;
//		
//			forName2.load();
			forName1 =  forName2;
			System.out.println(forName2.getAbc() + "  ---- " +  forName2 .hashCode()) ;
			
//			Class<StaticTest> forName2 =  (Class<StaticTest>) Class.forName("com.wch.test.StaticTest") ;
			
			System.out.println(forName1.hashCode() + " "  + forName2.hashCode());
			 
			forName1.getAbc().replace("wch", "231231") ;
			System.out.println(StaticTest.getAbc());
		}
}
