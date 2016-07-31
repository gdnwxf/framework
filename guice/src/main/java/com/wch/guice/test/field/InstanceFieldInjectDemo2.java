package com.wch.guice.test.field;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Module;

public class InstanceFieldInjectDemo2 {

       @Inject
       private Service service;
       public static void main(String[] args) {
          final InstanceFieldInjectDemo2 ifid = new InstanceFieldInjectDemo2();
           Guice.createInjector(new Module() {
               @Override
               public void configure(Binder binder) {
                   binder.requestInjection(ifid);
               }
           });
           System.out.println(ifid.getClass().getClassLoader());
           ifid.service.execute();
       }
   }