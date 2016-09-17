package com.wch.test.abstracttest;

public class AbstractTest {   


	public static void main(String[] args) {
		System.out.println(AbstractA.ABS_STATIC);
		AbstractA.ABS_STATIC = "hh12h321";
		AbstractA aa1 = new AbstractA() {};
		aa1.ABS_STATIC = "---";
		AbstractA aa2 = new AbstractA() {} ;
		
		System.out.println(aa2.hashCode() + " || " + aa1.hashCode());
	}
}

 abstract class AbstractA {
	 
	 public static String ABS_STATIC = "ABS_STATIC" ;

	 public AbstractA() {
		  
	 }
}

