/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.javamapper.elements;

import java.util.Set;
import java.util.TreeSet;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;

public class UpdateByExampleSelectiveMethodGenerator extends AbstractJavaMapperMethodGenerator
{
  public void addInterfaceElements(Interface interfaze)
  {
    Set importedTypes = new TreeSet();
    Method method = new Method();
    method.setVisibility(JavaVisibility.PUBLIC);
    method.setReturnType(FullyQualifiedJavaType.getIntInstance());
    method.setName(this.introspectedTable.getUpdateByExampleSelectiveStatementId());

    FullyQualifiedJavaType parameterType = this.introspectedTable.getRules().calculateAllFieldsClass();

    method.addParameter(new Parameter(parameterType, "record", "@Param(\"record\")"));

    importedTypes.add(parameterType);

    FullyQualifiedJavaType exampleType = new FullyQualifiedJavaType(this.introspectedTable.getExampleType());

    method.addParameter(new Parameter(exampleType, "example", "@Param(\"example\")"));

    importedTypes.add(exampleType);

    importedTypes.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));

    this.context.getCommentGenerator().addGeneralMethodComment(method, this.introspectedTable);

    addMapperAnnotations(interfaze, method);

    if (!(this.context.getPlugins().clientUpdateByExampleSelectiveMethodGenerated(method, interfaze, this.introspectedTable))) {
      return;
    }
    interfaze.addImportedTypes(importedTypes);
    interfaze.addMethod(method);
  }

  public void addMapperAnnotations(Interface interfaze, Method method)
  {
  }
}