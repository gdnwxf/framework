package com.wch.annotation.pojo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Table
{
  public abstract String name();

  public abstract String catalog();

  public abstract String schema();

  public abstract UniqueConstraint[] uniqueConstraints();
}
