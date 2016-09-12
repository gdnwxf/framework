package com.wch.test.advance;

import java.util.Arrays;

public class Test {

		public static void main(String[] args) {
		  System.out.println(210);
		  System.out.println(Arrays.toString("210".getBytes()));
		  
		  int i = 5;
		  System.out.println(Integer.toBinaryString(i));
		  System.out.println( i >>= 1);//向右移一位
		}
}
