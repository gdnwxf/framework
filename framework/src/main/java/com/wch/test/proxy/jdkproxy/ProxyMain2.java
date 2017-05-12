package com.wch.test.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * jdk 的动态代理只依赖于接口
 * @author wch
 *
 */
public class ProxyMain2 {
	
	public static void main(String[] args) {
			
		InvocationHandler h = new ProxyInvocationHandler();
		
		ProxyTarget proxyTarget =  (ProxyTarget) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				new Class<?> [] {ProxyTarget.class }, h);
//		
		 
		proxyTarget.doBusiness();
		
		
	}
}
