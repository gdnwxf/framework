/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.model;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.RootClassInfo;
import org.mybatis.generator.internal.util.messages.Messages;

public class BaseRecordGenerator extends AbstractJavaGenerator
{
  public List<CompilationUnit> getCompilationUnits()
  {
    FullyQualifiedTable table = this.introspectedTable.getFullyQualifiedTable();
    this.progressCallback.startTask(Messages.getString("Progress.8", table.toString()));

    Plugin plugins = this.context.getPlugins();
    CommentGenerator commentGenerator = this.context.getCommentGenerator();

    FullyQualifiedJavaType type = new FullyQualifiedJavaType(this.introspectedTable.getBaseRecordType());

    TopLevelClass topLevelClass = new TopLevelClass(type);
    topLevelClass.setVisibility(JavaVisibility.PUBLIC);
    commentGenerator.addJavaFileComment(topLevelClass);

    FullyQualifiedJavaType superClass = getSuperClass();
    if (superClass != null) {
      topLevelClass.setSuperClass(superClass);
      topLevelClass.addImportedType(superClass);
    }

    List<IntrospectedColumn> introspectedColumns = getColumnsInThisClass();

    if (this.introspectedTable.isConstructorBased()) {
      addParameterizedConstructor(topLevelClass);

      if (!(this.introspectedTable.isImmutable())) {
        addDefaultConstructor(topLevelClass);
      }
    }

    String rootClass = getRootClass();
    for (IntrospectedColumn introspectedColumn : introspectedColumns) {
      if (RootClassInfo.getInstance(rootClass, this.warnings).containsProperty(introspectedColumn))
      {
        continue;
      }

      Field field = getJavaBeansField(introspectedColumn);
      if (plugins.modelFieldGenerated(field, topLevelClass, introspectedColumn, this.introspectedTable, Plugin.ModelClassType.BASE_RECORD))
      {
        topLevelClass.addField(field);
        topLevelClass.addImportedType(field.getType());
      }

      Method method = getJavaBeansGetter(introspectedColumn);
      if (plugins.modelGetterMethodGenerated(method, topLevelClass, introspectedColumn, this.introspectedTable, Plugin.ModelClassType.BASE_RECORD))
      {
        topLevelClass.addMethod(method);
      }

      if (!(this.introspectedTable.isImmutable())) {
        method = getJavaBeansSetter(introspectedColumn);
        if (plugins.modelSetterMethodGenerated(method, topLevelClass, introspectedColumn, this.introspectedTable, Plugin.ModelClassType.BASE_RECORD))
        {
          topLevelClass.addMethod(method);
        }
      }
    }

    List answer = new ArrayList();
    if (this.context.getPlugins().modelBaseRecordClassGenerated(topLevelClass, this.introspectedTable))
    {
      answer.add(topLevelClass);
    }
    return answer;
  }

  private FullyQualifiedJavaType getSuperClass()
  {
    FullyQualifiedJavaType superClass;
    if (this.introspectedTable.getRules().generatePrimaryKeyClass()) {
      superClass = new FullyQualifiedJavaType(this.introspectedTable.getPrimaryKeyType());
    }
    else {
      String rootClass = getRootClass();
      if (rootClass != null)
        superClass = new FullyQualifiedJavaType(rootClass);
      else {
        superClass = null;
      }
    }

    return superClass;
  }

  private boolean includePrimaryKeyColumns() {
    return ((!(this.introspectedTable.getRules().generatePrimaryKeyClass())) && (this.introspectedTable.hasPrimaryKeyColumns()));
  }

  private boolean includeBLOBColumns()
  {
    return ((!(this.introspectedTable.getRules().generateRecordWithBLOBsClass())) && (this.introspectedTable.hasBLOBColumns()));
  }

  private void addParameterizedConstructor(TopLevelClass topLevelClass)
  {
    Method method = new Method();
    method.setVisibility(JavaVisibility.PUBLIC);
    method.setConstructor(true);
    method.setName(topLevelClass.getType().getShortName());
    this.context.getCommentGenerator().addGeneralMethodComment(method, this.introspectedTable);

    List<IntrospectedColumn> constructorColumns = (includeBLOBColumns()) ? this.introspectedTable.getAllColumns() : this.introspectedTable.getNonBLOBColumns();

    for (IntrospectedColumn introspectedColumn : constructorColumns) {
      method.addParameter(new Parameter(introspectedColumn.getFullyQualifiedJavaType(), introspectedColumn.getJavaProperty()));
    }

    StringBuilder sb = new StringBuilder();
    if (this.introspectedTable.getRules().generatePrimaryKeyClass()) {
      boolean comma = false;
      sb.append("super(");
      for (IntrospectedColumn introspectedColumn : this.introspectedTable.getPrimaryKeyColumns())
      {
        if (comma)
          sb.append(", ");
        else {
          comma = true;
        }
        sb.append(introspectedColumn.getJavaProperty());
      }
      sb.append(");");
      method.addBodyLine(sb.toString());
    }

    List<IntrospectedColumn> introspectedColumns = getColumnsInThisClass();

    for (IntrospectedColumn introspectedColumn : introspectedColumns) {
      sb.setLength(0);
      sb.append("this.");
      sb.append(introspectedColumn.getJavaProperty());
      sb.append(" = ");
      sb.append(introspectedColumn.getJavaProperty());
      sb.append(';');
      method.addBodyLine(sb.toString());
    }

    topLevelClass.addMethod(method);
  }

  private List<IntrospectedColumn> getColumnsInThisClass()
  {
    List introspectedColumns;
    if (includePrimaryKeyColumns())
    {
      if (includeBLOBColumns())
        introspectedColumns = this.introspectedTable.getAllColumns();
      else
        introspectedColumns = this.introspectedTable.getNonBLOBColumns();
    }
    else
    {
      if (includeBLOBColumns()) {
        introspectedColumns = this.introspectedTable.getNonPrimaryKeyColumns();
      }
      else {
        introspectedColumns = this.introspectedTable.getBaseColumns();
      }
    }

    return introspectedColumns;
  }
}