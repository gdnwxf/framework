package com.wch.guice.test.field;

import com.google.inject.Inject;

public class FieldInjectDemo2 {
     @Inject
     private Service servcie;
     public Service getServcie() {
         return servcie;
     }
     public static void main(String[] args) {
         FieldInjectDemo2 fd = new FieldInjectDemo2();
         fd.getServcie().execute();
     }
 }