package com.wch.generator.mybaits.mybaitsx.beanutils;

import java.util.List;
import java.util.Properties;

import com.wch.generator.mybaits.mybaitsx.bean.IntrospectedColumn;
import com.wch.generator.mybaits.mybaitsx.core.MyContext;

public interface JavaTypeResolver {
	void addConfigurationProperties(Properties arg0);

	void setContext(MyContext arg0);

	void setWarnings(List<String> arg0);

	FullyQualifiedJavaType calculateJavaType(IntrospectedColumn arg0);

	String calculateJdbcTypeName(IntrospectedColumn arg0);
}