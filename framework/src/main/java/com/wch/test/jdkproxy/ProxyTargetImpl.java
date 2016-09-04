package com.wch.test.jdkproxy;

public class ProxyTargetImpl implements ProxyTarget {

	@Override
	public void doBusiness() { 
		System.out.println("this is the business thing ");
	}

	
}
