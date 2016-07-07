/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.type;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.mybatis.generator.config.Context;

import com.wch.generator.mybaits.mybaitsx.bean.IntrospectedColumn;
import com.wch.generator.mybaits.mybaitsx.beanutils.FullyQualifiedJavaType;
import com.wch.generator.mybaits.mybaitsx.beanutils.JavaTypeResolver;
import com.wch.generator.mybaits.mybaitsx.core.MyContext;
import com.wch.generator.mybaits.mybaitsx.utils.StringUtility;


public class JavaTypeResolverDefaultImpl implements JavaTypeResolver {
	protected List<String> warnings;
	protected Properties properties = new Properties();
	protected MyContext context;
	protected boolean forceBigDecimals;
	protected Map<Integer, JavaTypeResolverDefaultImpl.JdbcTypeInformation> typeMap = new HashMap();

	public JavaTypeResolverDefaultImpl() {
		this.typeMap.put(Integer.valueOf(2003), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("ARRAY", new FullyQualifiedJavaType(Object.class.getName())));
		this.typeMap.put(Integer.valueOf(-5), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("BIGINT", new FullyQualifiedJavaType(Long.class.getName())));
		this.typeMap.put(Integer.valueOf(-2), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("BINARY", new FullyQualifiedJavaType("byte[]")));
		this.typeMap.put(Integer.valueOf(-7), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("BIT", new FullyQualifiedJavaType(Boolean.class.getName())));
		this.typeMap.put(Integer.valueOf(2004), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("BLOB", new FullyQualifiedJavaType("byte[]")));
		this.typeMap.put(Integer.valueOf(16), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("BOOLEAN", new FullyQualifiedJavaType(Boolean.class.getName())));
		this.typeMap.put(Integer.valueOf(1), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("CHAR", new FullyQualifiedJavaType(String.class.getName())));
		this.typeMap.put(Integer.valueOf(2005), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("CLOB", new FullyQualifiedJavaType(String.class.getName())));
		this.typeMap.put(Integer.valueOf(70), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("DATALINK", new FullyQualifiedJavaType(Object.class.getName())));
		this.typeMap.put(Integer.valueOf(91), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("DATE", new FullyQualifiedJavaType(Date.class.getName())));
		this.typeMap.put(Integer.valueOf(2001), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("DISTINCT", new FullyQualifiedJavaType(Object.class.getName())));
		this.typeMap.put(Integer.valueOf(8), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("DOUBLE", new FullyQualifiedJavaType(Double.class.getName())));
		this.typeMap.put(Integer.valueOf(6), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("FLOAT", new FullyQualifiedJavaType(Double.class.getName())));
		this.typeMap.put(Integer.valueOf(4), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("INTEGER", new FullyQualifiedJavaType(Integer.class.getName())));
		this.typeMap.put(Integer.valueOf(2000), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("JAVA_OBJECT", new FullyQualifiedJavaType(Object.class.getName())));
		this.typeMap.put(Integer.valueOf(-16), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("LONGNVARCHAR", new FullyQualifiedJavaType(String.class.getName())));
		this.typeMap.put(Integer.valueOf(-4), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("LONGVARBINARY", new FullyQualifiedJavaType("byte[]")));
		this.typeMap.put(Integer.valueOf(-1), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("LONGVARCHAR", new FullyQualifiedJavaType(String.class.getName())));
		this.typeMap.put(Integer.valueOf(-15), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("NCHAR", new FullyQualifiedJavaType(String.class.getName())));
		this.typeMap.put(Integer.valueOf(2011), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("NCLOB", new FullyQualifiedJavaType(String.class.getName())));
		this.typeMap.put(Integer.valueOf(-9), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("NVARCHAR", new FullyQualifiedJavaType(String.class.getName())));
		this.typeMap.put(Integer.valueOf(0), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("NULL", new FullyQualifiedJavaType(Object.class.getName())));
		this.typeMap.put(Integer.valueOf(1111), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("OTHER", new FullyQualifiedJavaType(Object.class.getName())));
		this.typeMap.put(Integer.valueOf(7), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("REAL", new FullyQualifiedJavaType(Float.class.getName())));
		this.typeMap.put(Integer.valueOf(2006), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("REF", new FullyQualifiedJavaType(Object.class.getName())));
		this.typeMap.put(Integer.valueOf(5), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("SMALLINT", new FullyQualifiedJavaType(Short.class.getName())));
		this.typeMap.put(Integer.valueOf(2002), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("STRUCT", new FullyQualifiedJavaType(Object.class.getName())));
		this.typeMap.put(Integer.valueOf(92), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("TIME", new FullyQualifiedJavaType(Date.class.getName())));
		this.typeMap.put(Integer.valueOf(93), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("TIMESTAMP", new FullyQualifiedJavaType(Date.class.getName())));
		this.typeMap.put(Integer.valueOf(-6), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Byte.class.getName())));
		this.typeMap.put(Integer.valueOf(-3), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("VARBINARY", new FullyQualifiedJavaType("byte[]")));
		this.typeMap.put(Integer.valueOf(12), new JavaTypeResolverDefaultImpl.JdbcTypeInformation("VARCHAR", new FullyQualifiedJavaType(String.class.getName())));
	}

	public void addConfigurationProperties(Properties properties) {
		this.properties.putAll(properties);
		this.forceBigDecimals = StringUtility.isTrue(properties.getProperty("forceBigDecimals"));
	}

	public FullyQualifiedJavaType calculateJavaType(IntrospectedColumn introspectedColumn) {
		JavaTypeResolverDefaultImpl.JdbcTypeInformation jdbcTypeInformation = (JavaTypeResolverDefaultImpl.JdbcTypeInformation) this.typeMap.get(Integer.valueOf(introspectedColumn.getJdbcType()));
		FullyQualifiedJavaType answer;
		if (jdbcTypeInformation == null) {
			switch (introspectedColumn.getJdbcType()) {
			case 2:
			case 3:
				if (introspectedColumn.getScale() <= 0 && introspectedColumn.getLength() <= 18 && !this.forceBigDecimals) {
					if (introspectedColumn.getLength() > 9) {
						answer = new FullyQualifiedJavaType(Long.class.getName());
					} else if (introspectedColumn.getLength() > 4) {
						answer = new FullyQualifiedJavaType(Integer.class.getName());
					} else {
						answer = new FullyQualifiedJavaType(Short.class.getName());
					}
				} else {
					answer = new FullyQualifiedJavaType(BigDecimal.class.getName());
				}
				break;
			default:
				answer = null;
			}
		} else {
			answer = jdbcTypeInformation.getFullyQualifiedJavaType();
		}

		return answer;
	}

	public String calculateJdbcTypeName(IntrospectedColumn introspectedColumn) {
		JavaTypeResolverDefaultImpl.JdbcTypeInformation jdbcTypeInformation = (JavaTypeResolverDefaultImpl.JdbcTypeInformation) this.typeMap.get(Integer.valueOf(introspectedColumn.getJdbcType()));
		String answer;
		if (jdbcTypeInformation == null) {
			switch (introspectedColumn.getJdbcType()) {
			case 2:
				answer = "NUMERIC";
				break;
			case 3:
				answer = "DECIMAL";
				break;
			default:
				answer = null;
			}
		} else {
			answer = jdbcTypeInformation.getJdbcTypeName();
		}

		return answer;
	}

	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}

	public void setContext(MyContext context) {
		this.context = context;
	}

	public static class JdbcTypeInformation {
		private String jdbcTypeName;
		private FullyQualifiedJavaType fullyQualifiedJavaType;

		public JdbcTypeInformation(String jdbcTypeName, FullyQualifiedJavaType fullyQualifiedJavaType) {
			this.jdbcTypeName = jdbcTypeName;
			this.fullyQualifiedJavaType = fullyQualifiedJavaType;
		}

		public String getJdbcTypeName() {
			return this.jdbcTypeName;
		}

		public FullyQualifiedJavaType getFullyQualifiedJavaType() {
			return this.fullyQualifiedJavaType;
		}
	}
}