package com.wch.test.statictest;

public class StaticTest12 {
	
	private static  String abc = System.getenv("PATH");
	
	
	/**
	 * 既可以在static的块中赋值  
	 * 还可以在普通代码块里面赋值 (但是普通的代码快中是无法赋值给静态区域里面的值的)
	 */
	
	{
		abc = "32132"; 
	}
	
	static {
		abc = "321321" ;
	}
	
	public static void main(String[] args) {
		
		
		System.out.println(System.getenv());
		System.out.println(" --------------- ");
		System.out.println(abc);
	}
}
