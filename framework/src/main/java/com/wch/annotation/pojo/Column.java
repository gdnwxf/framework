package com.wch.annotation.pojo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Column
{
  public abstract String name();

  public abstract boolean unique();

  public abstract boolean nullable();

  public abstract boolean insertable();

  public abstract boolean updatable();

  public abstract String columnDefinition();

  public abstract String table();

  public abstract int length();

  public abstract int precision();

  public abstract int scale();
}
