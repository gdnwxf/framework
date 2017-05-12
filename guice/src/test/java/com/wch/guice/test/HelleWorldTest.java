package com.wch.guice.test;
import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provider;

public class HelleWorldTest {

	@Test
	public void testSayHello() {
		Injector inj = Guice.createInjector(new Module() {
			public void configure(Binder binder) {
				binder.bind(HelloWorld.class).to(HelloWorldImpl.class);
			}
		});
		Injector inj2 = Guice.createInjector(new Module() {
			public void configure(Binder binder) {
				binder.bind(HelloWorld.class).to(HelloWorldImplAgain.class);
			}
		});
		HelloWorld hw = inj.getInstance(HelloWorld.class);
		Assert.assertEquals(hw.sayHello(), "Hello, world!");
		Assert.assertEquals(inj2.getInstance(HelloWorld.class).sayHello(), "Hello, world!");
	}
	
	/**
	 *  绑定子类到子类
	 * @throws Exception
	 */
	@Test
	public void testOnlyOneSubClass() throws Exception {
		Injector inj=  Guice.createInjector(new Module() {
		       @Override
		       public void configure(Binder binder) {
		           binder.bind(HelloWorldImpl.class).to(HelloWorldImpl.class);
		       }
		   });
		 HelloWorld hw = inj.getInstance(HelloWorldImpl.class);
		 System.out.println(hw.sayHello());
	}
	
	
	/**
	 * 测试绑定到实现类  <不嫩绑定到实现类>
	 * @throws Exception
	 */
	@Test
	public void testBingToImpl() throws Exception {
		Injector inj=  Guice.createInjector(new Module() {
			        @Override
			        public void configure(Binder binder) {
			            binder.bind(HelloWorldImpl.class).to(HelloWorldImpl.class);
			        }
			    });
			  HelloWorld hw = inj.getInstance(HelloWorldImpl.class);
			  System.out.println(hw.sayHello());
			 
	}
	
	/**
	 * 可以绑定到子实现类
	 * @throws Exception
	 */
	@Test
	public void testBingToSubClass() throws Exception {
		 Injector inj=  Guice.createInjector(new Module() {
			             @Override
			             public void configure(Binder binder) {
			                 binder.bind(HelloWorldImpl.class).to(HelloWorldSubImpl.class);
			             }
			         });
			       HelloWorldImpl hw = inj.getInstance(HelloWorldImpl.class);
			       System.out.println(hw.sayHello());
	}
	
	/**
	 * provide 的实现
	 * @throws Exception
	 */
	@Test
	public void testProvider() throws Exception {
		Injector inj=  Guice.createInjector(new Module() {
		       @Override
		       public void configure(Binder binder) {
		           binder.bind(HelloWorld.class).toProvider(new Provider<HelloWorld>() {
		               @Override
		               public HelloWorld get() {
		                   return new HelloWorldImpl();
		               }
		           });
		       }
		   });
		 HelloWorld hw = inj.getInstance(HelloWorld.class);
		 System.out.println(hw.sayHello());
	}    
		
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAnnotation() throws Exception {
		Injector inj=  Guice.createInjector();
		HelloWorld hw = inj.getInstance(HelloWorld.class);
		System.out.println(hw.sayHello());
	}
}