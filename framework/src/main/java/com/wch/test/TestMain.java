package com.wch.test;

public class TestMain {

	public static void main(String[] args) {
		test01("wccccch");
	}
	
	public static void test01(String shopId) {
		String microShopId = shopId; 
		shopId =  new String("231231") ;
		System.out.println(microShopId + shopId);
	}
}
