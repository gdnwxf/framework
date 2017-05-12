package com.wch.guice.test;

import com.google.inject.Singleton;

@Singleton
public class HelloWorldSubImpl extends HelloWorldImpl {

	@Override
	public String sayHello() {
		return "@HelloWorldSubImpl";
	}
}