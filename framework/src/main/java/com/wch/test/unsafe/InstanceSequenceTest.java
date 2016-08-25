package com.wch.test.unsafe;

public class InstanceSequenceTest {
	
	private static A a = new A();
	
	static {
//		System.out.println(a);
		System.out.println("InstanceSequenceTest before instance");
	}
	
	public InstanceSequenceTest() {
		// TODO Auto-generated constructor stub
		System.out.println("InstanceSequenceTest instance ");
	}
	
	public static void main(String[] args) {
		 InstanceSequenceTest test= new InstanceSequenceTest();
	}
}


class A {
	public A() {
		System.out.println("a instance");
		// TODO Auto-generated constructor stub
	}
}
