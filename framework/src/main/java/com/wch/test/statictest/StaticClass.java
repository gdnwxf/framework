package com.wch.test.statictest;

public class StaticClass{
	
	public static void main(String[] args) {
		
		StaticA aStaticA = new StaticA();
		
		
		System.out.println(aStaticA.getA(1, "321321").hashCode());
		
		
		StaticC aStaticC = new StaticC();
		
		
		System.out.println(aStaticC.getA(2, "dsadas").hashCode());
		System.out.println(aStaticC.getC());
		
		
	}
}

class StaticC extends StaticA{ 
	
	private int c ;
	

	public  A getA (int aInt , String bStr) {
		if(aInt != 0) {
			 a = new A(aInt,bStr);
			 c = aInt+ 1;
			return  a ;
		}
		c = aInt+ 1;
		return a;
 
	} 
	
	public int getC() {
		return c;
	}
}

class StaticB { 
	
	
	private static A a ;
	

	public  A getA (int aInt , String bStr) {
		if(aInt != 0) {
			 a = new A(aInt,bStr);
			return  a ;
		}
		return a;
 
	} 
}


class StaticA { 
	
	
	protected static A a ;
	

	public  A getA (int aInt , String bStr) {
		if(aInt != 0) {
			 a = new A(aInt,bStr);
			return  a ;
		}
		return a;
 
	} 
}

class A {
	private int a ;
	private String b  ;
	
	public A(int a, String b) {
		super();
		this.a = a;
		this.b = b;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	@Override
	public String toString() {
		return "A [a=" + a + ", b=" + b + "]";
	}
	
	
	
	
	
	
}
