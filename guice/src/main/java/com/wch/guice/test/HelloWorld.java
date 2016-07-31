package com.wch.guice.test;

import com.google.inject.ImplementedBy;

@ImplementedBy(HelloWorldSubImpl.class)
public interface HelloWorld {

	String sayHello();
}
