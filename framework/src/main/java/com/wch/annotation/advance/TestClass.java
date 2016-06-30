package com.wch.annotation.advance;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Properties;

import org.junit.Test;

import com.wch.annotation.advance.test.UserInfo;
import com.wch.annotation.web.PathVariable;

import cn.org.rapid_framework.generator.provider.java.model.JavaClass;
import cn.org.rapid_framework.generator.provider.java.model.JavaMethod;
import cn.org.rapid_framework.generator.provider.java.model.MethodParameter;
import cn.org.rapid_framework.generator.util.PropertyPlaceholderHelper.PlaceholderResolver;
import cn.org.rapid_framework.generator.util.PropertyPlaceholderHelper.PropertyPlaceholderConfigurerResolver;
import cn.org.rapid_framework.generator.util.paranamer.AdaptiveParanamer;
import cn.org.rapid_framework.generator.util.paranamer.Paranamer;

public class TestClass {

	public static void main(String[] args) {
		Method[] methods = UserInfo.class.getMethods();
		for (Method method : methods) {
			Parameter[] parameters = method.getParameters();
			if (parameters != null && parameters.length != 0) {
				for (Parameter parameter : parameters) {
					Annotation[] annotations = parameter.getAnnotations();
					if (annotations != null && annotations.length > 0) {
						for (Annotation annotation : annotations) {
							if (annotation.annotationType() == PathVariable.class) {
								PathVariable a = (PathVariable) annotation;
								System.out.println(a.value());
							}
						}
					}
				}
			}
		}
	}

	@Test
	public void testmethodname2() throws Exception {

		Method[] methods = UserInfo.class.getMethods();
		for (Method method : methods) {

			if (method.getName().equals("setAgent")) {
				Paranamer paranamer = new AdaptiveParanamer();
				String[] params = paranamer.lookupParameterNames(method);
				System.out.println(Arrays.toString(params));
			}
		}

	}

	@Test
	public void testmethodname1() throws Exception {

		Method[] methods = UserInfo.class.getMethods();
		for (Method method : methods) {
			JavaClass javaClass = new JavaClass(UserInfo.class);
			MethodParameter methodParameter = new MethodParameter(0, new JavaMethod(method, javaClass), javaClass);
			String packageName = methodParameter.getPackageName();
			Parameter[] parameters = method.getParameters();
			if (parameters != null)
				System.out.println(methodParameter.getName());
		}

	}
	@Test
	public void testName3() throws Exception {
		Properties props = new Properties();
		props.setProperty("3213", "3213");
		PlaceholderResolver a = new PropertyPlaceholderConfigurerResolver(props);
		System.out.println(a.resolvePlaceholder("3213"));
		
	}
}
