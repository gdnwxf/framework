package com.wch.guice.test.field;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.wch.guice.test.HelloWorld;

public class ConstructorInjectDemo {
  
          private Service service;
          private HelloWorld helloWorld;
          @Inject
          public ConstructorInjectDemo(Service service,HelloWorld helloWorld) {
              this.service=service;
              this.helloWorld=helloWorld;
          }
          public Service getService() {
              return service;
          }
          public HelloWorld getHelloWorld() {
              return helloWorld;
          }
          public static void main(String[] args) {
              ConstructorInjectDemo cid = Guice.createInjector().getInstance(ConstructorInjectDemo.class);
              cid.getService().execute();
              System.out.println(cid.getHelloWorld().sayHello());
          }
     }