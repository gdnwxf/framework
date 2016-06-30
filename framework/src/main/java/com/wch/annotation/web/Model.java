package com.wch.annotation.web;

import java.util.Collection;
import java.util.Map;

public abstract interface Model
{
  public abstract Model addAttribute(String paramString, Object paramObject);

  public abstract Model addAttribute(Object paramObject);

  public abstract Model addAllAttributes(Collection<?> paramCollection);

  public abstract Model addAllAttributes(Map<String, ?> paramMap);

  public abstract Model mergeAttributes(Map<String, ?> paramMap);

  public abstract boolean containsAttribute(String paramString);

  public abstract Map<String, Object> asMap();
}
