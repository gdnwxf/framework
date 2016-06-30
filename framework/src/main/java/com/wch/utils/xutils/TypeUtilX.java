package com.wch.utils.xutils;

import com.wch.utils.Assert;

/**
 * 类型检测工具
 * @author wch
 *
 */
public class TypeUtilX {
	
	public static boolean isBaseType(Object object) {

		Assert.notNull(object);
		
		if (object instanceof Byte) { return true; }
		else if (object instanceof Short) { return true; }
		else if (object instanceof Character) { return true; }
		else if (object instanceof Integer) { return true; }
		else if (object instanceof Long) { return true; }
		else if (object instanceof Double) { return true; }
		else if (object instanceof Float) { return true; }
		else if (object instanceof Boolean) { return true; }
		else if (object instanceof String) { return true; }
		
		Class<? extends Object> class1 = object.getClass();
		
		String name = class1.getSimpleName();
		if("int".equals(name)) { return true; }
		else if("byte".equals(name)) { return true; }
		else if("short".equals(name)) { return true; }
		else if("long".equals(name)) { return true; }
		else if("double".equals(name)) { return true; }
		else if("float".equals(name)) { return true; }
		else if("char".equals(name)) { return true; }
		else if("boolean".equals(name)) { return true; }
		else if("String".equals(name)) { return true; }
		
		return false;
	} 
	
	public static boolean isArray( Object object) {
		Assert.notNull(object);
		Class<? extends Object> class1 = object.getClass();
		return class1.isArray() ? true:false;
	}
	
	public static boolean isBaseArray( Object object) {
		Assert.notNull(object);
		
		if (object instanceof byte[]) { return true; }
		else if (object instanceof short[]) { return true; }
		else if (object instanceof char[]) { return true; }
		else if (object instanceof int[]) { return true; }
		else if (object instanceof long[]) { return true; }
		else if (object instanceof double[]) { return true; }
		else if (object instanceof float[]) { return true; }
		else if (object instanceof boolean[]) { return true; }

		if (object instanceof Byte[]) { return true; }
		else if (object instanceof Short[]) { return true; }
		else if (object instanceof Character[]) { return true; }
		else if (object instanceof Integer[]) { return true; }
		else if (object instanceof Long[]) { return true; }
		else if (object instanceof Double[]) { return true; }
		else if (object instanceof Float[]) { return true; }
		else if (object instanceof Boolean[]) { return true; }
		else if (object instanceof String[]) { return true; }
		
		return false;
	}
	
//	public static Class<?> mysql2Java() {
//		
//	}
	
	public static void main(String[] args) {
		System.out.println(Integer.class.getTypeName());
		int a = 0;
		System.out.println(isBaseArray(new int[10]));
		boolean flag = false;
		System.out.println(isBaseType(flag));
	}

}
