package com.wch.generator.mybaits.mybaitsx.utils;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mybatis.generator.codegen.ibatis2.IntrospectedTableIbatis2Java2Impl;
import org.mybatis.generator.codegen.ibatis2.IntrospectedTableIbatis2Java5Impl;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3SimpleImpl;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;

import com.wch.generator.mybaits.mybaitsx.bean.IntrospectedColumn;
import com.wch.generator.mybaits.mybaitsx.bean.IntrospectedTable;
import com.wch.generator.mybaits.mybaitsx.bean.TableConfiguration;
import com.wch.generator.mybaits.mybaitsx.beanutils.DefaultJavaFormatter;
import com.wch.generator.mybaits.mybaitsx.beanutils.DefaultXmlFormatter;
import com.wch.generator.mybaits.mybaitsx.beanutils.FullyQualifiedTable;
import com.wch.generator.mybaits.mybaitsx.beanutils.JavaFormatter;
import com.wch.generator.mybaits.mybaitsx.beanutils.JavaTypeResolver;
import com.wch.generator.mybaits.mybaitsx.core.MyContext;
import com.wch.generator.mybaits.mybaitsx.dom.XmlFormatter;
import com.wch.generator.mybaits.mybaitsx.type.JavaTypeResolverDefaultImpl;

public class ObjectFactory {
	private static List<ClassLoader> externalClassLoaders = new ArrayList();
	private static List<ClassLoader> resourceClassLoaders = new ArrayList();

	public static synchronized void addResourceClassLoader(ClassLoader classLoader) {
		resourceClassLoaders.add(classLoader);
	}

	public static synchronized void addExternalClassLoader(ClassLoader classLoader) {
		externalClassLoaders.add(classLoader);
	}

	public static Class<?> externalClassForName(String type) throws ClassNotFoundException {
		Iterator i$ = externalClassLoaders.iterator();

		while (i$.hasNext()) {
			ClassLoader classLoader = (ClassLoader) i$.next();

			try {
				Class clazz = Class.forName(type, true, classLoader);
				return clazz;
			} catch (Throwable arg4) {
				;
			}
		}

		return internalClassForName(type);
	}

	public static Object createExternalObject(String type) {
		try {
			Class e = externalClassForName(type);
			Object answer = e.newInstance();
			return answer;
		} catch (Exception arg2) {
			throw new RuntimeException(Messages.getString("RuntimeError.6", type), arg2);
		}
	}

	public static Class<?> internalClassForName(String type) throws ClassNotFoundException {
		Class clazz = null;

		try {
			ClassLoader e = Thread.currentThread().getContextClassLoader();
			clazz = Class.forName(type, true, e);
		} catch (Exception arg2) {
			;
		}

		if (clazz == null) {
			clazz = Class.forName(type, true, ObjectFactory.class.getClassLoader());
		}

		return clazz;
	}

	public static URL getResource(String resource) {
		Iterator cl = resourceClassLoaders.iterator();

		URL url;
		do {
			if (!cl.hasNext()) {
				ClassLoader cl1 = Thread.currentThread().getContextClassLoader();
				url = cl1.getResource(resource);
				if (url == null) {
					url = ObjectFactory.class.getClassLoader().getResource(resource);
				}

				return url;
			}

			ClassLoader classLoader = (ClassLoader) cl.next();
			url = classLoader.getResource(resource);
		} while (url == null);

		return url;
	}

	public static Object createInternalObject(String type) {
		try {
			Class e = internalClassForName(type);
			Object answer = e.newInstance();
			return answer;
		} catch (Exception arg2) {
			throw new RuntimeException(Messages.getString("RuntimeError.6", type), arg2);
		}
	}

	public static JavaTypeResolver createJavaTypeResolver(MyContext context, List<String> warnings) {
		JavaTypeResolverConfiguration config = context.getJavaTypeResolverConfiguration();
		String type;
		if (config != null && config.getConfigurationType() != null) {
			type = config.getConfigurationType();
			if ("DEFAULT".equalsIgnoreCase(type)) {
				type = JavaTypeResolverDefaultImpl.class.getName();
			}
		} else {
			type = JavaTypeResolverDefaultImpl.class.getName();
		}

		JavaTypeResolver answer = (JavaTypeResolver) createInternalObject(type);
		answer.setWarnings(warnings);
		if (config != null) {
			answer.addConfigurationProperties(config.getProperties());
		}

		answer.setContext(context);
		return answer;
	}
 
	public static JavaFormatter createJavaFormatter(MyContext context) {
		String type = context.getProperty("javaFormatter");
		if (!StringUtility.stringHasValue(type)) {
			type = DefaultJavaFormatter.class.getName();
		}

		JavaFormatter answer = (JavaFormatter) createInternalObject(type);
		answer.setContext(context);
		return answer;
	}

	public static XmlFormatter createXmlFormatter(MyContext context) {
		String type = context.getProperty("xmlFormatter");
		if (!StringUtility.stringHasValue(type)) {
			type = DefaultXmlFormatter.class.getName();
		}

		XmlFormatter answer = (XmlFormatter) createInternalObject(type);
		answer.setContext(context);
		return answer;
	}

	public static IntrospectedTable createIntrospectedTable(TableConfiguration tableConfiguration, FullyQualifiedTable table, MyContext context) {
		IntrospectedTable answer = createIntrospectedTableForValidation(context);
		answer.setFullyQualifiedTable(table);
		answer.setTableConfiguration(tableConfiguration);
		return answer;
	}

	public static IntrospectedTable createIntrospectedTableForValidation(MyContext context) {
		String type = context.getTargetRuntime();
		if (!StringUtility.stringHasValue(type)) {
			type = IntrospectedTableMyBatis3Impl.class.getName();
		} else if ("Ibatis2Java2".equalsIgnoreCase(type)) {
			type = IntrospectedTableIbatis2Java2Impl.class.getName();
		} else if ("Ibatis2Java5".equalsIgnoreCase(type)) {
			type = IntrospectedTableIbatis2Java5Impl.class.getName();
		} else if ("Ibatis3".equalsIgnoreCase(type)) {
			type = IntrospectedTableMyBatis3Impl.class.getName();
		} else if ("MyBatis3".equalsIgnoreCase(type)) {
			type = IntrospectedTableMyBatis3Impl.class.getName();
		} else if ("MyBatis3Simple".equalsIgnoreCase(type)) {
			type = IntrospectedTableMyBatis3SimpleImpl.class.getName();
		} 
			 
		type = com.wch.generator.mybaits.mybaitsx.mybaits3.IntrospectedTableMyBatis3SimpleImpl.class.getName();

		IntrospectedTable answer = (IntrospectedTable) createInternalObject(type);
		answer.setContext(context);
		return answer;
	}

	public static IntrospectedColumn createIntrospectedColumn(MyContext context) {
		String type = context.getIntrospectedColumnImpl();
		if (!StringUtility.stringHasValue(type)) {
			type = IntrospectedColumn.class.getName();
		}

		IntrospectedColumn answer = (IntrospectedColumn) createInternalObject(type);
		answer.setContext(context);
		return answer;
	}
}