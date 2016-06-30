/**
 * 
 */
package com.wch.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wch.utils.date.DateUtils;
import com.wch.utils.string.StringUtils;

 
/**
 * The Class BeanUtils.
 *
 * @author gdnwxf
 * @version 1.0
 * @since 2014
 * @email gdnwxf@qq.com
 * @date 2015-1-22 17:40:18
 */
public class BeanUtils {

	/** The hash queue. */
	private static Set<Class<?>> hashQueue = new HashSet<Class<?>>();

	/**
	 * 将map 里面的值copy 到对象中去.
	 * 
	 * @param map a Map<?,?>
	 * @param target a Object
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	public static void copyNotNullProperties(Map<?, ?> map, Object target) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = target.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			String fieldName = fields[i].getName();
			if (map.get(fieldName) != null) {
				fields[i].set(target, map.get(fieldName));
			}
		}
	}

	/**
	 * 将对象的值转成map.
	 * 
	 * @param target a Object
	 * @return the map
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	public static Map<String, Object> beans2Map(Object target) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String, Object> map = new HashMap<String, Object>();
		Class<?> clazz = target.getClass();
		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			String methodName = methods[i].getName();
			if (methodName.startsWith("get")) {
				Object object = methods[i].invoke(target);
				if (object != null) {
					String fieldName = methodName.replace("get", "");
					map.put(StringUtils.char2Lower(fieldName, new Integer[] { 0 }), object);
				}
			}
		}
		return map;
	}

	/**
	 * 讲Map的值 转成对应的对象.
	 * 
	 * @param <T> the generic type
	 * @param map a Map<String,Object>
	 * @param t a Class<T>
	 * @return the t
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	public static <T> T mapToBean(Map<String, Object> map, Class<T> t) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		T object = t.newInstance();
		Method[] methods = object.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			String methodName = methods[i].getName();
			if (methodName.indexOf("set") == 0) {
				String fieldName = methodName.substring(3);// 下标从0开始的
				methods[i].invoke(object, map.get(StringUtils.char2Lower(fieldName, 0)));
			}
		}
		return object;
	}

	/**
	 * Instance base type.
	 * 
	 * @param clazz a Class<?>
	 * @param obj a String[]
	 * @return the object
	 */
	public static Object instanceBaseType(Class<?> clazz, String... obj) {
		if (obj == null) {
			return null;
		}
		if (clazz == byte.class) {
			return Byte.valueOf(obj[0]);
		} else if (clazz == Byte.class) {
			return Byte.valueOf(obj[0]);
		} else if (clazz == char.class) {
			return obj[0].charAt(0);
		} else if (clazz == Character.class) {
			return obj[0].charAt(0);
		} else if (clazz == short.class) {
			return Short.valueOf(obj[0]);
		} else if (clazz == Short.class) {
			return Short.valueOf(obj[0]);
		} else if (clazz == int.class) {
			return Integer.valueOf(obj[0]);
		} else if (clazz == Integer.class) {
			return Integer.valueOf(obj[0]);
		} else if (clazz == long.class) {
			return Long.valueOf(obj[0]);
		} else if (clazz == Long.class) {
			return Long.valueOf(obj[0]);
		} else if (clazz == float.class) {
			return Float.valueOf(obj[0]);
		} else if (clazz == Float.class) {
			return Float.valueOf(obj[0]);
		} else if (clazz == double.class) {
			return Double.valueOf(obj[0]);
		} else if (clazz == Double.class) {
			return Double.valueOf(obj[0]);
		} else {
			return obj[0];
		}
	}

	/**
	 * 实例化基本的数组类型.
	 * 
	 * @param clazz a Class<?>
	 * @param obj a String[]
	 * @return the object
	 */
	public static Object instanceBaseArrayType(Class<?> clazz, String[] obj) {
		if (obj == null) {
			return null;
		} else {
			if (clazz == byte[].class) {
				byte[] byteObj = new byte[obj.length];
				for (int i = 0; i < obj.length; i++) {
					byteObj[i] = Byte.valueOf(obj[i]);
				}
				return byteObj;
			} else if (clazz == Byte[].class) {
				Byte[] byteObj = new Byte[obj.length];
				for (int i = 0; i < obj.length; i++) {
					byteObj[i] = Byte.valueOf(obj[i]);
				}
				return byteObj;
			} else if (clazz == short[].class) {
				short[] byteObj = new short[obj.length];
				for (int i = 0; i < obj.length; i++) {
					byteObj[i] = Short.valueOf(obj[i]);
				}
				return byteObj;
			} else if (clazz == Short[].class) {
				Short[] byteObj = new Short[obj.length];
				for (int i = 0; i < obj.length; i++) {
					byteObj[i] = Short.valueOf(obj[i]);
				}
				return byteObj;
			} else if (clazz == int[].class) {
				int[] byteObj = new int[obj.length];
				for (int i = 0; i < obj.length; i++) {
					byteObj[i] = Integer.valueOf(obj[i]);
				}
				return byteObj;
			} else if (clazz == Integer[].class) {
				Integer[] byteObj = new Integer[obj.length];
				for (int i = 0; i < obj.length; i++) {
					byteObj[i] = Integer.valueOf(obj[i]);
				}
				return byteObj;
			} else if (clazz == char[].class) {
				char[] byteObj = new char[obj.length];
				for (int i = 0; i < obj.length; i++) {
					byteObj[i] = obj[i].charAt(0);
				}
				return byteObj;
			} else if (clazz == Character[].class) {
				Character[] byteObj = new Character[obj.length];
				for (int i = 0; i < obj.length; i++) {
					byteObj[i] = obj[i].charAt(0);
				}
				return byteObj;
			} else if (clazz == long[].class) {
				long[] byteObj = new long[obj.length];
				for (int i = 0; i < obj.length; i++) {
					byteObj[i] = Long.valueOf(obj[i]);
				}
				return byteObj;
			} else if (clazz == Long[].class) {
				Long[] byteObj = new Long[obj.length];
				for (int i = 0; i < obj.length; i++) {
					byteObj[i] = Long.valueOf(obj[i]);
				}
				return byteObj;
			} else if (clazz == float[].class) {
				float[] byteObj = new float[obj.length];
				for (int i = 0; i < obj.length; i++) {
					byteObj[i] = Float.valueOf(obj[i]);
				}
				return byteObj;
			} else if (clazz == Float[].class) {
				Float[] byteObj = new Float[obj.length];
				for (int i = 0; i < obj.length; i++) {
					byteObj[i] = Float.valueOf(obj[i]);
				}
				return byteObj;
			} else if (clazz == double[].class) {
				double[] byteObj = new double[obj.length];
				for (int i = 0; i < obj.length; i++) {
					byteObj[i] = Double.valueOf(obj[i]);
				}
				return byteObj;
			} else if (clazz == Double[].class) {
				Double[] byteObj = new Double[obj.length];
				for (int i = 0; i < obj.length; i++) {
					byteObj[i] = Double.valueOf(obj[i]);
				}
				return byteObj;
			} else {

				// 用Array反射实现数组的实例化一维数组
				// int [] dims = {1,2,3}; //3维数组
				// Array array = (Array) Array.newInstance(clazz, dims);
				// Object array = Array.newInstance(clazz, new int[]{obj.length}); // 一维数组
				// for (int i = 0; i < obj.length; i++) {
				//
				// }

				// 其他的数组类型暂时不考虑
				return obj;
			}
		}
	}

	/**
	 * 判断对象是不是基本数据类型. (包含基本的数据类型和基本的数据类型的数组)
	 * 
	 * @param object a Object
	 * @return true, if successful
	 */
	public static boolean isBaseType(Object object) {
		// null 也表示基本的数据类型
		if (object == null) {
			return true;
		}
		Class<?> clazz = object.getClass();
		if (clazz == Class.class) {
			return true;
		}
		if (clazz == byte.class || clazz == Byte.class || clazz == short.class || clazz == Short.class || clazz == int.class || clazz == Integer.class || clazz == char.class || clazz == Character.class || clazz == long.class || clazz == Long.class || clazz == double.class || clazz == Double.class || clazz == float.class || clazz == Float.class || clazz == String.class) {
			return true;
		}

		return false;
	}

	/**
	 * Checks if is base array.
	 * 
	 * @param object a Object
	 * @return true, if is base array
	 */
	public static boolean isBaseArray(Object object) {
		Class<?> clazz = object.getClass();
		// 判断是否是数组的类型
		if (clazz.isArray()) {
			clazz = clazz.getComponentType(); // 数组的内部的类型
			// 判断其内部是否是基本的数据类型
			if (clazz == byte.class || clazz == Byte.class || clazz == short.class || clazz == Short.class || clazz == int.class || clazz == Integer.class || clazz == char.class || clazz == Character.class || clazz == long.class || clazz == Long.class || clazz == double.class || clazz == Double.class || clazz == float.class || clazz == Float.class || clazz == String.class) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Fileter interface.
	 * 
	 * @param object the object
	 * @param clazz the clazz
	 * @return true, if successful
	 */
	public static boolean fileterInterface(Object object, Class<?> clazz) {
		List<Class<?>> interfaces = Arrays.asList(object.getClass().getInterfaces());
		if (interfaces.contains(clazz)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是不是基本的数据类型 包含基本数据类型组成的数组.
	 * 
	 * @param object a Object
	 */
	public static void printBaseType(Object object) {
		if (object == null) {
			sysPrintln(" [ null ] ");
			return;
		}
		// 如果是基本的class的类型就直接打印
	    sysPrint(" [ "+object.getClass().getSimpleName() + " : ");
		if (object instanceof byte[]) {
			sysPrintln(Arrays.toString((byte[]) object));
		} else if (object instanceof Byte[]) {
			sysPrintln(Arrays.toString((Byte[]) object));
		} else if (object instanceof short[]) {
			sysPrintln(Arrays.toString((short[]) object));
		} else if (object instanceof Short[]) {
			sysPrintln(Arrays.toString((Short[]) object));
		} else if (object instanceof int[]) {
			sysPrintln(Arrays.toString((int[]) object));
		} else if (object instanceof Integer[]) {
			sysPrintln(Arrays.toString((Integer[]) object));
		} else if (object instanceof char[]) {
			sysPrintln(Arrays.toString((char[]) object));
		} else if (object instanceof Character[]) {
			sysPrintln(Arrays.toString((Character[]) object));
		} else if (object instanceof long[]) {
			sysPrintln(Arrays.toString((long[]) object));
		} else if (object instanceof Long[]) {
			sysPrintln(Arrays.toString((Long[]) object));
		} else if (object instanceof float[]) {
			sysPrintln(Arrays.toString((float[]) object));
		} else if (object instanceof Float[]) {
			sysPrintln(Arrays.toString((Float[]) object));
		} else if (object instanceof double[]) {
			sysPrintln(Arrays.toString((double[]) object));
		} else if (object instanceof Double[]) {
			sysPrintln(Arrays.toString((Double[]) object));
		} else if (object instanceof String[]) {
			sysPrintln(Arrays.toString((String[]) object));
		} else {
			sysPrintln(object + " ] ");
		}

	}

	/**
	 * 判断基本的反射类型.
	 * 
	 * @param object a Object
	 * @return true, if successful
	 */
	@SuppressWarnings("unused")
	private static boolean jugleBaseReflectType(Object object) {
		// jdk 1.5 不能使用parameter
		// if(object instanceof Method|| object instanceof Field || object instanceof Class|| object instanceof Annotation ||object instanceof Type || object instanceof Parameter){
		if (object instanceof Method || object instanceof Field || object instanceof Class || object instanceof Annotation || object instanceof Type) {
			return true;
		}
		return false;
	}

	/**
	 * 只对对象的属性进行打印(基本的 不循环 declaredField 和 declaredMethod ).
	 *
	 * @param object the object
	 */
	public static void printMin(Object object) {
		if (isBaseType(object) || isBaseArray(object)) {
			printBaseType(object);
			return;
		}

		// 如果返回的类型是Enumeration.class的则遍历整个Enumeration
		if (fileterInterface(object, Enumeration.class)) {
			Enumeration<?> enumeration = (Enumeration<?>) object;
			sysPrint(" enumeration : \n {  ");
			while (enumeration.hasMoreElements()) {
				Object enumObject = enumeration.nextElement();
				printBaseType(enumObject);
			}
			sysPrintln(" } ");
		}

		printFields(object);

		printDeclaredMethod(object, "get");

	}

	/**
	 * 只对对象的属性进行打印(基本的循环 declaredField 和 declaredMethod ).
	 *
	 * @param object the object
	 */
	public static void print_Min(Object object) {
		if (isBaseType(object) || isBaseArray(object)) {
			printBaseType(object);
			return;
		}
		
		if (hashQueue.contains(object.getClass())) {
			return;
		}
		
		hashQueue.add(object.getClass());
		
		// 如果返回的类型是Enumeration.class的则遍历整个Enumeration
		if (fileterInterface(object, Enumeration.class)) {
			Enumeration<?> enumeration = (Enumeration<?>) object;
			sysPrint(" enumeration : \n {  ");
			while (enumeration.hasMoreElements()) {
				Object enumObject = enumeration.nextElement();
				printMax(enumObject);
			}
			sysPrintln(" } ");
		}

		Object[] returnObject = printFields(object);
		
		for (int i = 0; i < returnObject.length; i++) {
			print_Min(returnObject[i]);
		}
		
		Object[] returnObject2 = printDeclaredMethod(object, "get");
		
		for (int i = 0; i < returnObject2.length; i++) {
			print_Min(returnObject2[i]);
		}

	}

	/**
	 * 对对象的属性和方法进行打印 (不循环 element 和  method ).
	 *
	 * @param object the object
	 */
	public static void printMiddle(Object object) {

		if (isBaseType(object) || isBaseArray(object)) {
			printBaseType(object);
			return;
		}
		
		printDeclaredFields(object);

		// 如果返回的类型是Enumeration.class的则遍历整个Enumeration
		if (fileterInterface(object, Enumeration.class)) {
			Enumeration<?> enumeration = (Enumeration<?>) object;
			sysPrint(" enumeration : \n {  ");
			while (enumeration.hasMoreElements()) {
				Object enumObject = enumeration.nextElement();
				printMax(enumObject);
			}
			sysPrintln(" } ");
		}
		
		printMethod(object, "get");

	}

	/**
	 * 最大限度地对对象进行打印.
	 *
	 * @param object a Object
	 */
	public static void print_Max(Object object) {
		if (isBaseType(object) || isBaseArray(object)) {
			printBaseType(object);
			return;
		}

		if (hashQueue.contains(object.getClass())) {
			return;
		}

		hashQueue.add(object.getClass());

		Object [] fieldObjects = printDeclaredFields(object);
		for (int i = 0; i < fieldObjects.length; i++) {
			printMax(fieldObjects[i]);
		}

		// 如果返回的类型是Enumeration.class的则遍历整个Enumeration
		if (fileterInterface(object, Enumeration.class)) {
			Enumeration<?> enumeration = (Enumeration<?>) object;
			sysPrint(" enumeration : \n {  ");
			while (enumeration.hasMoreElements()) {
				Object enumObject = enumeration.nextElement();
				printMax(enumObject);
			}
			sysPrintln(" } ");
		}

		// 打印无参的get方法所获得的值
		Object[] returnObject = printMethod(object, "get");
		for (int i = 0; i < returnObject.length; i++) {
			printMax(returnObject[i]);
		}

	}

	/**
	 * 最大限度地对对象进行打印.
	 *
	 * @param object a Object
	 */
	public static void printMax(Object object) {
		
		if (isBaseType(object) || isBaseArray(object)) {
			printBaseType(object);
			return;
		}

		if (hashQueue.contains(object.getClass())) {
			return;
		}

		hashQueue.add(object.getClass());

		printDeclaredFields(object);

		// 如果返回的类型是Enumeration.class的则遍历整个Enumeration
		if (fileterInterface(object, Enumeration.class)) {
			Enumeration<?> enumeration = (Enumeration<?>) object;
			sysPrint(" enumeration : \n {  ");
			while (enumeration.hasMoreElements()) {
				Object enumObject = enumeration.nextElement();
				printMax(enumObject);
			}
			sysPrintln(" } ");
		}

		// 打印无参的get方法所获得的值
		Object[] returnObject = printMethod(object, "get");
		for (int i = 0; i < returnObject.length; i++) {
			printMax(returnObject[i]);
		}

	}

	/**
	 * 递归打印对象的值.
	 * 
	 * @param object a Object
	 */
	public static void printObj(Object object) {
		String beginTime = "";
		sysPrintln("*****BeanInfo( " + (beginTime = DateUtils.formartDate(new Date(), "MM-dd HH:mm:ss:SSS")) + " )**************");
		printMiddle(object);
		sysPrintln("*****BeanInfo( " + beginTime + " )**************");
	}

	/**
	 * 打印对象的公共属性的值 .
	 * 
	 * @param object a Object
	 */
	public static void printObjParams(Object object) {
		try {
			Class<?> clazz = object.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				sysPrintln(fields[i].getName() + "\t" + fields[i].get(object));
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 打印对象的所有属性的值.
	 * 
	 * @param object a Object
	 * @return the object
	 */
	public static Object[] printFields(Object object) {
		List<Object> tempList = new ArrayList<Object>();
		try {
			Class<?> clazz = object.getClass();
			// 获取的是
			Field[] fields = clazz.getFields();
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				Object tempObject = fields[i].get(object);
				tempList.add(tempObject);
				sysPrint(" FIELD "+fields[i].getName() + " : ");
				printBaseType(tempObject);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return tempList.toArray(new Object[1]);
	}

	/**
	 * 打印对象的所有属性的值.
	 * 
	 * @param object a Object
	 * @return the object
	 */
	public static Object[] printDeclaredFields(Object object) {
		List<Object> tempList = new ArrayList<Object>();
		try {
			Class<?> clazz = object.getClass();
			// 获取的是
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				Object tempObject = fields[i].get(object);
				tempList.add(tempObject);
				sysPrint(" FIELD "+fields[i].getName() + " : ");
				printBaseType(tempObject);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return tempList.toArray(new Object[1]);
	}

	/**
	 * Gets the mehtod with name.
	 * 
	 * @param clazz a Class<?>
	 * @param name a String
	 * @param paramType a Class<?>[]
	 * @return the mehtod with name
	 * @throws NoSuchMethodException the no such method exception
	 * @throws SecurityException the security exception
	 */
	public static Method getMehtodWithName(Class<?> clazz, String name, Class<?>... paramType) throws NoSuchMethodException, SecurityException {
		return clazz.getMethod(name, paramType);
	}

	/**
	 * Prints the method execute result.
	 * 
	 * @param object a Object
	 * @param methodNameFilter 方法名称的过滤
	 * @param parameters the parameters
	 * @return the object[]
	 */
	public static Object[] printDeclaredMethod(Object object, String methodNameFilter, Object... parameters) {
		List<Object> resultList = new ArrayList<Object>();
		try {
			Object objectTemp = null;
			Method[] methods = object.getClass().getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				String methodName = methods[i].getName();
				if (methodName.indexOf(methodNameFilter) >= 0) {
					methods[i].setAccessible(true);
					// 方法参数为零时
					if (methods[i].getParameterTypes().length == 0) {
						if (parameters != null && parameters.length > 0) {
							objectTemp = methods[i].invoke(object, parameters);
						} else {
							objectTemp = methods[i].invoke(object);
						}
						// 当返回值不是基本的数据类型的时候才返回 当其返回的是自身的时候避免死循环
						if (!isBaseType(objectTemp) && !isBaseArray(objectTemp)) {
							resultList.add(objectTemp);
						}
						sysPrint( " METHOD "+methodName);
						printBaseType(objectTemp);
					}
				}
				// 为boolean类型的时候生成的方法是is开头的
				if ("get".equals(methodNameFilter) && methods[i].getName().indexOf("is") == 0) {
					methods[i].setAccessible(true);
					// 当其返回的类型为boolean的类型时
					if (methods[i].getReturnType() == (Boolean.class)) {
						if (parameters != null && parameters.length > 0) {
							objectTemp = methods[i].invoke(object, parameters);
						} else {
							objectTemp = methods[i].invoke(object);
						}
						// 当返回值不是基本的数据类型的时候才返回
						if (!isBaseType(objectTemp) && !isBaseArray(objectTemp) && !object.getClass().equals(objectTemp.getClass())) {
							resultList.add(objectTemp);
						}		
						sysPrint(" METHOD "+methodName);
						printBaseType(objectTemp);
					}
				}
			}
		} catch (Exception e) {
			sysPrintln(e.getMessage());
		}

		return resultList.toArray(new Object[1]);
	}

	/**
	 * Prints the method execute result.
	 * 
	 * @param object a Object
	 * @param methodNameFilter 方法名称的过滤
	 * @param parameters the parameters
	 * @return the object[]
	 */
	public static Object[] printMethod(Object object, String methodNameFilter, Object... parameters) {
		List<Object> resultList = new ArrayList<Object>();
		try {
			Object objectTemp = null;
			Method[] methods = object.getClass().getMethods();
			for (int i = 0; i < methods.length; i++) {
				String methodName = methods[i].getName();
				if (methodName.indexOf(methodNameFilter) >= 0) {
					methods[i].setAccessible(true);
					// 方法参数为零时
					if (methods[i].getParameterTypes().length == 0) {
						if (parameters != null && parameters.length > 0) {
							objectTemp = methods[i].invoke(object, parameters);
						} else {
							objectTemp = methods[i].invoke(object);
						}

						// 当返回值不是基本的数据类型的时候才返回 当其返回的是自身的时候避免死循环
						if (!isBaseType(objectTemp) && !isBaseArray(objectTemp)) {
							resultList.add(objectTemp);
						}
						sysPrint(" METHOD "+methodName);
						printBaseType(objectTemp);
					}
				}
				// 为boolean类型的时候生成的方法是is开头的
				if ("get".equals(methodNameFilter) && methods[i].getName().indexOf("is") == 0) {
					methods[i].setAccessible(true);
					// 当其返回的类型为boolean的类型时
					if (methods[i].getReturnType() == (Boolean.class)) {
						if (parameters != null && parameters.length > 0) {
							objectTemp = methods[i].invoke(object, parameters);
						} else {
							objectTemp = methods[i].invoke(object);
						}
						// 当返回值不是基本的数据类型的时候才返回
						if (!isBaseType(objectTemp) && !isBaseArray(objectTemp) && !object.getClass().equals(objectTemp.getClass())) {
							resultList.add(objectTemp);
						}
						sysPrint(" METHOD "+methodName);
						printBaseType(objectTemp);
					}
				}
			}
		} catch (Exception e) {
			sysPrintln(e.getMessage());
		}

		return resultList.toArray(new Object[1]);
	}
	
	/**
	 * Sys print.
	 *
	 * @param object the object
	 */
	private static void sysPrint(Object object){
		System.out.print( object );
	}
	
	/**
	 * Sys println.
	 *
	 * @param object the object
	 */
	private static void sysPrintln(Object object){
		System.out.println( object );
	}


	/**
	 * _print element like value.
	 * 
	 * @param object a Object
	 * @param methodName a String
	 * @param methodNameFilter a String
	 * @param objectTemp a Object
	 * @return true, if successful
	 */
	@Deprecated
	public static boolean _printFieldLikeValue(Object object, String methodName, String methodNameFilter, Object objectTemp) {
		try {
			String _fieldName = StringUtils.char2Lower(methodName.replace(methodNameFilter, ""), 0);
			Field _field = object.getClass().getDeclaredField(_fieldName);
			if (_field != null) {
				sysPrintln(_fieldName + " : " + objectTemp);
				return true;
			}
		} catch (Exception e) {
			sysPrintln(e.getMessage());
		}
		return false;
	}

}
