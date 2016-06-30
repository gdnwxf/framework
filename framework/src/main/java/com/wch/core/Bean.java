
package com.wch.core;
import java.lang.reflect.Type;

/**
 * 实体bean类
 * @author GDNWXF
 * @date 2014年7月1日  下午10:29:51
 */
public class Bean {
	/**
	 * bean的类型  如  re
	 */
	private String beanType;
	/**
	 * 声明的实体类的名称  默认是以名称来进行注入的
	 */
	private String name; 
	/**
	 * 声明的实体类的类型
	 */
	private Type type;
	/**
	 * bean的clazz的属性
	 */
	private Class<?> clazz;
	
	
	
	
}
