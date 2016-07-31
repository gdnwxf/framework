package com.wch.guice.test.field;

import com.google.inject.ImplementedBy;

@ImplementedBy(ServiceImpl.class)
 public interface Service {
     void execute();
 }