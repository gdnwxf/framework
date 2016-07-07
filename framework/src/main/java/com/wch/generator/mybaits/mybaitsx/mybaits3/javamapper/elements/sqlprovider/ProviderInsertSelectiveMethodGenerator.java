/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.javamapper.elements.sqlprovider;

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
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.JavaBeansUtil;
import org.mybatis.generator.internal.util.StringUtility;

public class ProviderInsertSelectiveMethodGenerator extends AbstractJavaProviderMethodGenerator
{
  public void addClassElements(TopLevelClass topLevelClass)
  {
    Set staticImports = new TreeSet();
    Set importedTypes = new TreeSet();

    staticImports.add("org.apache.ibatis.jdbc.SqlBuilder.BEGIN");
    staticImports.add("org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO");
    staticImports.add("org.apache.ibatis.jdbc.SqlBuilder.SQL");
    staticImports.add("org.apache.ibatis.jdbc.SqlBuilder.VALUES");

    FullyQualifiedJavaType fqjt = this.introspectedTable.getRules().calculateAllFieldsClass();

    importedTypes.add(fqjt);

    Method method = new Method(this.introspectedTable.getInsertSelectiveStatementId());

    method.setVisibility(JavaVisibility.PUBLIC);
    method.setReturnType(FullyQualifiedJavaType.getStringInstance());
    method.addParameter(new Parameter(fqjt, "record"));

    this.context.getCommentGenerator().addGeneralMethodComment(method, this.introspectedTable);

    method.addBodyLine("BEGIN();");
    method.addBodyLine(String.format("INSERT_INTO(\"%s\");", new Object[] { StringUtility.escapeStringForJava(this.introspectedTable.getFullyQualifiedTableNameAtRuntime()) }));

    for (IntrospectedColumn introspectedColumn : this.introspectedTable.getAllColumns()) {
      if (introspectedColumn.isIdentity())
      {
        continue;
      }

      method.addBodyLine("");
      if ((!(introspectedColumn.getFullyQualifiedJavaType().isPrimitive())) && (!(introspectedColumn.isSequenceColumn())))
      {
        method.addBodyLine(String.format("if (record.%s() != null) {", new Object[] { JavaBeansUtil.getGetterMethodName(introspectedColumn.getJavaProperty(), introspectedColumn.getFullyQualifiedJavaType()) }));
      }

      method.addBodyLine(String.format("VALUES(\"%s\", \"%s\");", new Object[] { StringUtility.escapeStringForJava(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn)), MyBatis3FormattingUtilities.getParameterClause(introspectedColumn) }));

      if ((!(introspectedColumn.getFullyQualifiedJavaType().isPrimitive())) && (!(introspectedColumn.isSequenceColumn())))
      {
        method.addBodyLine("}");
      }
    }

    method.addBodyLine("");
    method.addBodyLine("return SQL();");

    if (!(this.context.getPlugins().providerInsertSelectiveMethodGenerated(method, topLevelClass, this.introspectedTable)))
      return;
    topLevelClass.addStaticImports(staticImports);
    topLevelClass.addImportedTypes(importedTypes);
    topLevelClass.addMethod(method);
  }
}