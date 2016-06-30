package com.wch.annotation.pojo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueConstraint
{
  public abstract String name();

  public abstract String[] columnNames();
}
