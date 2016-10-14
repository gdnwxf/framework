package com.wch.test;

public class FinalField {
	//final 可以赋值为str 为null
    final public static String str = null ;
    
	public static void main(String[] args) {
		
	}
}

class A {
	
	  private String id;
	  private String name;
	public final String getId() {
		return id;
	}
	public final void setId(String id) {
		this.id = id;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	  
}

class B extends A {
	  private String id;
	  private String name;
 
	  
}