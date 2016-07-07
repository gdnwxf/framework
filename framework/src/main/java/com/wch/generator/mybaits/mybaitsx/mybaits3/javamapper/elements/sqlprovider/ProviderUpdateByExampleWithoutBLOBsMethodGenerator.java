/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.javamapper.elements.sqlprovider;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.StringUtility;

public class ProviderUpdateByExampleWithoutBLOBsMethodGenerator extends AbstractJavaProviderMethodGenerator
{
  public void addClassElements(TopLevelClass topLevelClass)
  {
    Set staticImports = new TreeSet();
    Set importedTypes = new TreeSet();

    staticImports.add("org.apache.ibatis.jdbc.SqlBuilder.BEGIN");
    staticImports.add("org.apache.ibatis.jdbc.SqlBuilder.UPDATE");
    staticImports.add("org.apache.ibatis.jdbc.SqlBuilder.SET");
    staticImports.add("org.apache.ibatis.jdbc.SqlBuilder.SQL");

    importedTypes.add(new FullyQualifiedJavaType("java.util.Map"));

    Method method = new Method(getMethodName());
    method.setReturnType(FullyQualifiedJavaType.getStringInstance());
    method.setVisibility(JavaVisibility.PUBLIC);
    method.addParameter(new Parameter(new FullyQualifiedJavaType("java.util.Map<java.lang.String, java.lang.Object>"), "parameter"));

    this.context.getCommentGenerator().addGeneralMethodComment(method, this.introspectedTable);

    method.addBodyLine("BEGIN();");

    method.addBodyLine(String.format("UPDATE(\"%s\");", new Object[] { StringUtility.escapeStringForJava(this.introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()) }));

    method.addBodyLine("");

    for (IntrospectedColumn introspectedColumn : getColumns()) {
      StringBuilder sb = new StringBuilder();
      sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
      sb.insert(2, "record.");

      method.addBodyLine(String.format("SET(\"%s = %s\");", new Object[] { StringUtility.escapeStringForJava(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(introspectedColumn)), sb.toString() }));
    }

    method.addBodyLine("");

    FullyQualifiedJavaType example = new FullyQualifiedJavaType(this.introspectedTable.getExampleType());

    importedTypes.add(example);
    method.addBodyLine(String.format("%s example = (%s) parameter.get(\"example\");", new Object[] { example.getShortName(), example.getShortName() }));

    method.addBodyLine("applyWhere(example, true);");
    method.addBodyLine("return SQL();");

    if (callPlugins(method, topLevelClass)) {
      topLevelClass.addStaticImports(staticImports);
      topLevelClass.addImportedTypes(importedTypes);
      topLevelClass.addMethod(method);
    }
  }

  public String getMethodName() {
    return this.introspectedTable.getUpdateByExampleStatementId();
  }

  public List<IntrospectedColumn> getColumns() {
    return this.introspectedTable.getNonBLOBColumns();
  }

  public boolean callPlugins(Method method, TopLevelClass topLevelClass) {
    return this.context.getPlugins().providerUpdateByExampleWithoutBLOBsMethodGenerated(method, topLevelClass, this.introspectedTable);
  }
}