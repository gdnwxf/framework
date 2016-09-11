package com.wch.test.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyMain {
	
	public static void main(String[] args) {
		
		ProxyTarget proxyTarget1 = new ProxyTargetImpl();
		
		
		InvocationHandler h = new ProxyInvocationHandler(proxyTarget1);
		
		ProxyTarget proxyTarget =  (ProxyTarget) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				new Class<?> [] {ProxyTarget.class }, h);
//		
		 
		proxyTarget.doBusiness();
		
		
	}
}
