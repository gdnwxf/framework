package com.wch.guice.test.muiltinject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;

@Retention(RetentionPolicy.RUNTIME)
  @Target(value= {ElementType.FIELD, ElementType.PARAMETER})
  @BindingAnnotation
  public @interface Home {
  }