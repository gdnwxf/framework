package com.wch.generator.mybaits.dbcore;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Translate {
	
	private static Logger logger = LoggerFactory.getLogger(DataSource.class);
	
	public Object translate(java.util.Map<String, Object> mapObject) {
		// 默认用jdk的beanWarpper来实现
		return null;
	}
	
	public static <T> T translate(java.util.Map<String, Object> mapObject, Class<T> t) {
			T t2 = null;
			try {
				t2 = t.newInstance();
				BeanInfo beanInfo = Introspector.getBeanInfo(t);
				PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor p : descriptors) {
					// 获得所有get方法
					Object object = mapObject.get(p.getName());
					if(object != null) {
						Class<?> propertyType = p.getPropertyType();
						Method readMethod = p.getWriteMethod(); // get方法
						try {
							if(object.getClass() == propertyType) {
								readMethod.invoke(t2, object);
							}
						} catch (IllegalAccessException e) {
							logger.error("can't set the value  field {} fieldType value {} value {}",p.getName(), propertyType,object,object.getClass());
						} catch (IllegalArgumentException e) {
							logger.error("can't set the value  field {} fieldType value {} value {}",p.getName(), propertyType,object,object.getClass());
						} catch (InvocationTargetException e) {
							logger.error("can't set the value  field {} fieldType value {} value {}",p.getName(), propertyType,object,object.getClass());
						}
					}
				}
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) {
			} catch (IntrospectionException e) {
			}
			return t2;
	}
}