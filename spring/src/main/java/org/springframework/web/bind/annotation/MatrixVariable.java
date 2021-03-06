package org.springframework.web.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MatrixVariable
{
  public abstract String value();

  public abstract String pathVar();

  public abstract boolean required();

  public abstract String defaultValue();
}
