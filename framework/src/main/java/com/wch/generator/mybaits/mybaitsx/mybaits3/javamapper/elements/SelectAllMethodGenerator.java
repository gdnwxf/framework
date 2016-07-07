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
import org.mybatis.generator.config.Context;

public class SelectAllMethodGenerator extends AbstractJavaMapperMethodGenerator
{
  public void addInterfaceElements(Interface interfaze)
  {
    Set importedTypes = new TreeSet();
    importedTypes.add(FullyQualifiedJavaType.getNewListInstance());

    Method method = new Method();
    method.setVisibility(JavaVisibility.PUBLIC);

    FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();

    FullyQualifiedJavaType listType = new FullyQualifiedJavaType(this.introspectedTable.getBaseRecordType());

    importedTypes.add(listType);
    returnType.addTypeArgument(listType);
    method.setReturnType(returnType);
    method.setName(this.introspectedTable.getSelectAllStatementId());

    this.context.getCommentGenerator().addGeneralMethodComment(method, this.introspectedTable);

    addMapperAnnotations(interfaze, method);

    if (!(this.context.getPlugins().clientSelectAllMethodGenerated(method, interfaze, this.introspectedTable)))
      return;
    interfaze.addImportedTypes(importedTypes);
    interfaze.addMethod(method);
  }

  public void addMapperAnnotations(Interface interfaze, Method method)
  {
  }
}