/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.javamapper.elements;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;

public class SelectByPrimaryKeyMethodGenerator extends AbstractJavaMapperMethodGenerator
{
  private boolean isSimple;

  public SelectByPrimaryKeyMethodGenerator(boolean isSimple)
  {
    this.isSimple = isSimple;
  }

  public void addInterfaceElements(Interface interfaze)
  {
    Set importedTypes = new TreeSet();
    Method method = new Method();
    method.setVisibility(JavaVisibility.PUBLIC);

    FullyQualifiedJavaType returnType = this.introspectedTable.getRules().calculateAllFieldsClass();

    method.setReturnType(returnType);
    importedTypes.add(returnType);

    method.setName(this.introspectedTable.getSelectByPrimaryKeyStatementId());
    boolean annotate;
    StringBuilder sb;
    if ((!(this.isSimple)) && (this.introspectedTable.getRules().generatePrimaryKeyClass())) {
      FullyQualifiedJavaType type = new FullyQualifiedJavaType(this.introspectedTable.getPrimaryKeyType());

      importedTypes.add(type);
      method.addParameter(new Parameter(type, "key"));
    }
    else
    {
      List<IntrospectedColumn> introspectedColumns = this.introspectedTable.getPrimaryKeyColumns();

      annotate = introspectedColumns.size() > 1;
      if (annotate) {
        importedTypes.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
      }

      sb = new StringBuilder();
      for (IntrospectedColumn introspectedColumn : introspectedColumns) {
        FullyQualifiedJavaType type = introspectedColumn.getFullyQualifiedJavaType();

        importedTypes.add(type);
        Parameter parameter = new Parameter(type, introspectedColumn.getJavaProperty());

        if (annotate) {
          sb.setLength(0);
          sb.append("@Param(\"");
          sb.append(introspectedColumn.getJavaProperty());
          sb.append("\")");
          parameter.addAnnotation(sb.toString());
        }
        method.addParameter(parameter);
      }
    }

    addMapperAnnotations(interfaze, method);

    this.context.getCommentGenerator().addGeneralMethodComment(method, this.introspectedTable);

    if (!(this.context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze, this.introspectedTable)))
      return;
    interfaze.addImportedTypes(importedTypes);
    interfaze.addMethod(method);
  }

  public void addMapperAnnotations(Interface interfaze, Method method)
  {
  }
}