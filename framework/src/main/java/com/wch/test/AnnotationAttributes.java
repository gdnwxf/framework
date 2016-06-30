/*package com.wch.test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.wch.util.StringUtils;

public class AnnotationAttributes extends LinkedHashMap<String, Object> {
	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;

	public AnnotationAttributes() {
	}

	public AnnotationAttributes(int initialCapacity) {
		super(initialCapacity);
	}

	public AnnotationAttributes(Map<String, Object> map) {
		super(map);
	}

	public static AnnotationAttributes fromMap(Map<String, Object> map) {
		if (map == null) {
			return null;
		}

		if ((map instanceof AnnotationAttributes)) {
			return (AnnotationAttributes) map;
		}

		return new AnnotationAttributes(map);
	}

	public String getString(String attributeName) {
		return (String) doGet(attributeName, String.class);
	}

	public String[] getStringArray(String attributeName) {
     return (String[])doGet(attributeName, [Ljava.lang.String.class);
   }

	public boolean getBoolean(String attributeName) {
		return ((Boolean) doGet(attributeName, Boolean.class)).booleanValue();
	}

	public <N extends Number> N getNumber(String attributeName) {
		return (Number) doGet(attributeName, Integer.class);
	}

	public <E extends Enum<?>> E getEnum(String attributeName) {
		return (Enum) doGet(attributeName, Enum.class);
	}

	public <T> Class<? extends T> getClass(String attributeName) {
		return (Class) doGet(attributeName, Class.class);
	}

	public Class<?>[] getClassArray(String attributeName) {
     return (Class[])doGet(attributeName, [Ljava.lang.Class.class);
   }

	public AnnotationAttributes getAnnotation(String attributeName) {
		return (AnnotationAttributes) doGet(attributeName, AnnotationAttributes.class);
	}

	public AnnotationAttributes[] getAnnotationArray(String attributeName) {
     return (AnnotationAttributes[])doGet(attributeName, [Lorg.springframework.core.annotation.AnnotationAttributes.class);
   }

	private <T> T doGet(String attributeName, Class<T> expectedType) {
		Object value = get(attributeName);
		Assert.isAssignable(expectedType, value.getClass(), String.format("Attribute '%s' is of type [%s], but [%s] was expected. Cause: ", new Object[] { attributeName, value.getClass().getSimpleName(), expectedType.getSimpleName() }));
		return value;
	}

	public String toString() {
		Iterator<Map.Entry<String, Object>> entries = entrySet().iterator();
		StringBuilder sb = new StringBuilder("{");
		while (entries.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry) entries.next();
			sb.append((String) entry.getKey());
			sb.append('=');
			sb.append(valueToString(entry.getValue()));
			sb.append(entries.hasNext() ? ", " : "");
		}
		sb.append("}");
		return sb.toString();
	}

	private String valueToString(Object value) {
		if (value == this) {
			return "(this Map)";
		}
		if ((value instanceof Object[])) {
			return "[" + StringUtils.arrayToCommaDelimitedString((Object[]) value) + "]";
		}
		return String.valueOf(value);
	}
}
*/
