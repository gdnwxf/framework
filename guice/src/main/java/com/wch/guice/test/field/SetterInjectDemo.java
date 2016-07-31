package com.wch.guice.test.field;

import com.google.inject.Guice;
import com.google.inject.Inject;

public class SetterInjectDemo {
 
         private Service service;
 
         @Inject
         public void setService(Service service) {
             this.service = service;
         }
 
         public Service getService() {
             return service;
         }
 
         public static void main(String[] args) {
             SetterInjectDemo sid = Guice.createInjector().getInstance(SetterInjectDemo.class);
             sid.getService().execute();
         }
 
     }
 