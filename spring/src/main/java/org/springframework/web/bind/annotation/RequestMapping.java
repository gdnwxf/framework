package org.springframework.web.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface RequestMapping
{
  public abstract String[] value() default {};

  public abstract RequestMethod[] method() default {};

  public abstract String[] params() default {};

  public abstract String[] headers() default {};

  public abstract String[] consumes() default {};

  public abstract String[] produces() default {};
}
