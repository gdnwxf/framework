package com.wch.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public abstract class AnnotationUtils {
	static final String VALUE = "value";
	private static final Map<Class<?>, Boolean> annotatedInterfaceCache = new WeakHashMap<Class<?>, Boolean>();

	public AnnotationUtils() {
	}

	public static <T extends Annotation> T getAnnotation(AnnotatedElement ae, Class<T> annotationType) {
		T ann = ae.getAnnotation(annotationType);
		if (ann == null) {
			for (Annotation metaAnn : ae.getAnnotations()) {
				ann = metaAnn.annotationType().getAnnotation(annotationType);
				if (ann != null) {
					break;
				}
			}
		}
		return ann;
	}

	/**
	 * 找到method方法上所有的注解
	 * 
	 * @param method
	 * @return
	 */
	public static Annotation[] getAnnotations(Method method) {
		return method.getAnnotations();
	}

	public static <A extends Annotation> A getAnnotation(Method method, Class<A> annotationType) {
		Method resolvedMethod = null;//  .findBridgedMethod(method);
		@SuppressWarnings("null")
		A ann = resolvedMethod.getAnnotation(annotationType);
		if (ann == null) {
			for (Annotation metaAnn : resolvedMethod.getAnnotations()) {
				ann = metaAnn.annotationType().getAnnotation(annotationType);
				if (ann != null) {
					break;
				}
			}
		}
		return ann;
	}

	public static <A extends Annotation> A findAnnotation(Method method, Class<A> annotationType) {
		A annotation = getAnnotation(method, annotationType);
		Class<?> clazz = method.getDeclaringClass();
		if (annotation == null) {
			annotation = searchOnInterfaces(method, annotationType, clazz.getInterfaces());
		}
		while (annotation == null) {
			clazz = clazz.getSuperclass();
			if ((clazz == null) || (clazz.equals(Object.class))) {
				break;
			}
			try {
				Method equivalentMethod = clazz.getDeclaredMethod(method.getName(), method.getParameterTypes());
				annotation = getAnnotation(equivalentMethod, annotationType);
			} catch (NoSuchMethodException ex) {
			}

			if (annotation == null) {
				annotation = searchOnInterfaces(method, annotationType, clazz.getInterfaces());
			}
		}
		return annotation;
	}

	private static <A extends Annotation> A searchOnInterfaces(Method method, Class<A> annotationType, Class<?>[] ifcs) {
		A annotation = null;
		for (Class<?> iface : ifcs) {
			if (isInterfaceWithAnnotatedMethods(iface)) {
				try {
					Method equivalentMethod = iface.getMethod(method.getName(), method.getParameterTypes());
					annotation = getAnnotation(equivalentMethod, annotationType);
				} catch (NoSuchMethodException ex) {
				}

				if (annotation != null) {
					break;
				}
			}
		}
		return annotation;
	}

	private static boolean isInterfaceWithAnnotatedMethods(Class<?> iface) {
		synchronized (annotatedInterfaceCache) {
			Boolean flag = (Boolean) annotatedInterfaceCache.get(iface);
			if (flag != null) {
				return flag.booleanValue();
			}
			boolean found = false;
			for (Method ifcMethod : iface.getMethods()) {
				if (ifcMethod.getAnnotations().length > 0) {
					found = true;
					break;
				}
			}
			annotatedInterfaceCache.put(iface, Boolean.valueOf(found));
			return found;
		}
	}

	public static <A extends Annotation> A findAnnotation(Class<?> clazz, Class<A> annotationType) {
		A annotation = clazz.getAnnotation(annotationType);
		if (annotation != null) {
			return annotation;
		}
		for (Class<?> ifc : clazz.getInterfaces()) {
			annotation = findAnnotation(ifc, annotationType);
			if (annotation != null) {
				return annotation;
			}
		}
		if (!Annotation.class.isAssignableFrom(clazz)) {
			for (Annotation ann : clazz.getAnnotations()) {
				annotation = findAnnotation(ann.annotationType(), annotationType);
				if (annotation != null) {
					return annotation;
				}
			}
		}
		Class<?> superClass = clazz.getSuperclass();
		if ((superClass == null) || (superClass.equals(Object.class))) {
			return null;
		}
		return findAnnotation(superClass, annotationType);
	}

	public static Class<?> findAnnotationDeclaringClass(Class<? extends Annotation> annotationType, Class<?> clazz) {
		if ((clazz == null) || (clazz.equals(Object.class))) {
			return null;
		}
		return isAnnotationDeclaredLocally(annotationType, clazz) ? clazz : findAnnotationDeclaringClass(annotationType, clazz.getSuperclass());
	}

	public static Class<?> findAnnotationDeclaringClassForTypes(List<Class<? extends Annotation>> annotationTypes, Class<?> clazz) {
		if ((clazz == null) || (clazz.equals(Object.class))) {
			return null;
		}
		for (Class<? extends Annotation> annotationType : annotationTypes) {
			if (isAnnotationDeclaredLocally(annotationType, clazz)) {
				return clazz;
			}
		}
		return findAnnotationDeclaringClassForTypes(annotationTypes, clazz.getSuperclass());
	}

	public static boolean isAnnotationDeclaredLocally(Class<? extends Annotation> annotationType, Class<?> clazz) {
		boolean declaredLocally = false;
		for (Annotation annotation : clazz.getDeclaredAnnotations()) {
			if (annotation.annotationType().equals(annotationType)) {
				declaredLocally = true;
				break;
			}
		}
		return declaredLocally;
	}

	public static boolean isAnnotationInherited(Class<? extends Annotation> annotationType, Class<?> clazz) {
		return (clazz.isAnnotationPresent(annotationType)) && (!isAnnotationDeclaredLocally(annotationType, clazz));
	}
/*
	public static Map<String, Object> getAnnotationAttributes(Annotation annotation) {
		return getAnnotationAttributes(annotation, false, false);
	}

	public static Map<String, Object> getAnnotationAttributes(Annotation annotation, boolean classValuesAsString) {
		return getAnnotationAttributes(annotation, classValuesAsString, false);
	}*/
/*
	public static AnnotationAttributes getAnnotationAttributes(Annotation annotation, boolean classValuesAsString, boolean nestedAnnotationsAsMap) {
		AnnotationAttributes attrs = new AnnotationAttributes();
		Method[] methods = annotation.annotationType().getDeclaredMethods();
		for (Method method : methods) {
			if ((method.getParameterTypes().length == 0) && (method.getReturnType() != Void.TYPE)) {
				try {
					Object value = method.invoke(annotation, new Object[0]);
					if (classValuesAsString) {
						if ((value instanceof Class)) {
							value = ((Class<?>) value).getName();
						} else if ((value instanceof Class[])) {
							Class<?>[] clazzArray = (Class[]) value;
							String[] newValue = new String[clazzArray.length];
							for (int i = 0; i < clazzArray.length; i++) {
								newValue[i] = clazzArray[i].getName();
							}
							value = newValue;
						}
					}
					if ((nestedAnnotationsAsMap) && ((value instanceof Annotation))) {
						attrs.put(method.getName(), getAnnotationAttributes((Annotation) value, classValuesAsString, nestedAnnotationsAsMap));
					} else if ((nestedAnnotationsAsMap) && ((value instanceof Annotation[]))) {
						Annotation[] realAnnotations = (Annotation[]) value;
						AnnotationAttributes[] mappedAnnotations = new AnnotationAttributes[realAnnotations.length];
						for (int i = 0; i < realAnnotations.length; i++) {
							mappedAnnotations[i] = getAnnotationAttributes(realAnnotations[i], classValuesAsString, nestedAnnotationsAsMap);
						}

						attrs.put(method.getName(), mappedAnnotations);
					} else {
						attrs.put(method.getName(), value);
					}
				} catch (Exception ex) {
					throw new IllegalStateException("Could not obtain annotation attribute values", ex);
				}
			}
		}
		return attrs;
	}*/

	public static Object getValue(Annotation annotation) {
		return getValue(annotation, "value");
	}

	public static Object getValue(Annotation annotation, String attributeName) {
		try {
			Method method = annotation.annotationType().getDeclaredMethod(attributeName, new Class[0]);
			return method.invoke(annotation, new Object[0]);
		} catch (Exception ex) {
		}
		return null;
	}

	public static Object getDefaultValue(Annotation annotation) {
		return getDefaultValue(annotation, "value");
	}

	public static Object getDefaultValue(Annotation annotation, String attributeName) {
		return getDefaultValue(annotation.annotationType(), attributeName);
	}

	public static Object getDefaultValue(Class<? extends Annotation> annotationType) {
		return getDefaultValue(annotationType, "value");
	}

	public static Object getDefaultValue(Class<? extends Annotation> annotationType, String attributeName) {
		try {
			Method method = annotationType.getDeclaredMethod(attributeName, new Class[0]);
			return method.getDefaultValue();
		} catch (Exception ex) {
		}
		return null;
	}
}
