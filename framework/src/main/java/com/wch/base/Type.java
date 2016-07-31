/**
 * 
 */
package com.wch.base;

import com.wch.utils.Assert;

/**
 * 类型检测工具
 * @author wch
 *
 */
public class Type {
	
	public static boolean isBaseType(Object object) {

		Assert.notNull(object);
		
		if (object instanceof Byte) { return true; }
		if (object instanceof Short) { return true; }
		if (object instanceof Character) { return true; }
		if (object instanceof Integer) { return true; }
		if (object instanceof Long) { return true; }
		if (object instanceof Double) { return true; }
		if (object instanceof Float) { return true; }
		if (object instanceof Boolean) { return true; }
		
		Class<? extends Object> class1 = object.getClass();
		String name = class1.getSimpleName();
		if("int".equals(name)) { return true; }
		if("byte".equals(name)) { return true; }
		if("short".equals(name)) { return true; }
		if("long".equals(name)) { return true; }
		if("double".equals(name)) { return true; }
		if("float".equals(name)) { return true; }
		if("char".equals(name)) { return true; }
		if("boolean".equals(name)) { return true; }
		
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
		if (object instanceof short[]) { return true; }
		if (object instanceof char[]) { return true; }
		if (object instanceof int[]) { return true; }
		if (object instanceof long[]) { return true; }
		if (object instanceof double[]) { return true; }
		if (object instanceof float[]) { return true; }
		if (object instanceof boolean[]) { return true; }

		if (object instanceof Byte[]) { return true; }
		if (object instanceof Short[]) { return true; }
		if (object instanceof Character[]) { return true; }
		if (object instanceof Integer[]) { return true; }
		if (object instanceof Long[]) { return true; }
		if (object instanceof Double[]) { return true; }
		if (object instanceof Float[]) { return true; }
		if (object instanceof Boolean[]) { return true; }
		
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(Integer.class.getTypeName());
		int a = 0;
		System.out.println(isBaseArray(new int[10]));
	}

}
