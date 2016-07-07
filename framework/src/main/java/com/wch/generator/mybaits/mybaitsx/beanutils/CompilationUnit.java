package com.wch.generator.mybaits.mybaitsx.beanutils;

import java.util.List;
import java.util.Set;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

public interface CompilationUnit {
	String getFormattedContent();

	Set<FullyQualifiedJavaType> getImportedTypes();

	Set<String> getStaticImports();

	FullyQualifiedJavaType getSuperClass();

	boolean isJavaInterface();

	boolean isJavaEnumeration();

	Set<FullyQualifiedJavaType> getSuperInterfaceTypes();

	FullyQualifiedJavaType getType();

	void addImportedType(FullyQualifiedJavaType arg0);

	void addImportedTypes(Set<FullyQualifiedJavaType> arg0);

	void addStaticImport(String arg0);

	void addStaticImports(Set<String> arg0);

	void addFileCommentLine(String arg0);

	List<String> getFileCommentLines();
}