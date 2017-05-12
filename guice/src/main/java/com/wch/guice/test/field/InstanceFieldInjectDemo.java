package com.wch.guice.test.field;

import com.google.inject.Guice;
import com.google.inject.Inject;

public class InstanceFieldInjectDemo {
 
       @Inject
       private Service service;
       public static void main(String[] args) {
           InstanceFieldInjectDemo ifid = new InstanceFieldInjectDemo();
           Guice.createInjector().injectMembers(ifid);
           ifid.service.execute();
       }
   }
 