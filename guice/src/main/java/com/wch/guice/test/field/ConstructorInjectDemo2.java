package com.wch.guice.test.field;

import com.google.inject.Guice;
import com.google.inject.Inject;

public class ConstructorInjectDemo2 {
  
          private Service service;
          @Inject
          public ConstructorInjectDemo2(Service service) {
              this.service=service;
          }
          public Service getService() {
              return service;
          }
          public static void main(String[] args) {
              ConstructorInjectDemo2 cid = Guice.createInjector().getInstance(ConstructorInjectDemo2.class);
              cid.getService().execute();
          }
  
      }