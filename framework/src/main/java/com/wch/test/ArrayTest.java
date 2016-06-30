package com.wch.test;


import com.wch.utils.BeanUtils;


public class ArrayTest {
	public static void main(String[] args) { 
		String [] aStrings = new String[321321];
		int [] a = new int[231];
		int b = 0;
		int cObject= b;
		BeanUtils.printObj(a);
		System.out.println(a instanceof int []);
		System.out.println();
	}
}
