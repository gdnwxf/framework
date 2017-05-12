package com.wch.guice.test.field;

import com.google.inject.Guice;
import com.google.inject.Inject;

public class FieldInjectDemo {
     @Inject
     private Service servcie;
     public Service getServcie() {
         return servcie;
     }
     public static void main(String[] args) {
         FieldInjectDemo demo = Guice.createInjector().getInstance(FieldInjectDemo.class);
         demo.getServcie().execute();
     }
 }