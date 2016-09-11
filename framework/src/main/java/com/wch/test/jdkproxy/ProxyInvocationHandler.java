package com.wch.test.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class ProxyInvocationHandler implements  InvocationHandler {
	
	private Object target ;
	
	
	public ProxyInvocationHandler() {
		// TODO Auto-generated constructor stub
	}
	
	

	public ProxyInvocationHandler(Object target) {
		super();
		this.target = target;
	}



	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(proxy.getClass());
		//proxy 是提调用这个方法的本身
		//proxy 这个proxy 是jvm内置的
		//proxy 这个proxy 是jdk内置的代理对象class com.sun.proxy.$Proxy0 
		//proxy 这个proxy 是对当前InvocationHandler的一个封装
		
		if(method.getName().equals("doBusiness")) {
			System.out.println("方法调用前");
		}
		Object object = method.invoke(target, args);
		if(method.getName().equals("doBusiness")) {
			System.out.println("方法调用后");
		}
		return object;
	}

 

}
