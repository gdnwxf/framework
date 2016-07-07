/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.javamapper.elements.sqlprovider;

import java.util.Set;
import java.util.TreeSet;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.StringUtility;

public class ProviderDeleteByExampleMethodGenerator extends AbstractJavaProviderMethodGenerator
{
  public void addClassElements(TopLevelClass topLevelClass)
  {
    Set staticImports = new TreeSet();
    Set importedTypes = new TreeSet();

    staticImports.add("org.apache.ibatis.jdbc.SqlBuilder.BEGIN");
    staticImports.add("org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM");
    staticImports.add("org.apache.ibatis.jdbc.SqlBuilder.SQL");

    FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(this.introspectedTable.getExampleType());
    importedTypes.add(fqjt);

    Method method = new Method(this.introspectedTable.getDeleteByExampleStatementId());

    method.setVisibility(JavaVisibility.PUBLIC);
    method.setReturnType(FullyQualifiedJavaType.getStringInstance());
    method.addParameter(new Parameter(fqjt, "example"));

    this.context.getCommentGenerator().addGeneralMethodComment(method, this.introspectedTable);

    method.addBodyLine("BEGIN();");
    method.addBodyLine(String.format("DELETE_FROM(\"%s\");", new Object[] { StringUtility.escapeStringForJava(this.introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()) }));

    method.addBodyLine("applyWhere(example, false);");
    method.addBodyLine("return SQL();");

    if (!(this.context.getPlugins().providerDeleteByExampleMethodGenerated(method, topLevelClass, this.introspectedTable)))
      return;
    topLevelClass.addStaticImports(staticImports);
    topLevelClass.addImportedTypes(importedTypes);
    topLevelClass.addMethod(method);
  }
}