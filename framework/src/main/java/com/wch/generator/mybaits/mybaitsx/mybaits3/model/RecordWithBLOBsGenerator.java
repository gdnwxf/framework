/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.model;

import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.Plugin.ModelClassType;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.RootClassInfo;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.messages.Messages;

public class RecordWithBLOBsGenerator extends AbstractJavaGenerator
{
  public List<CompilationUnit> getCompilationUnits()
  {
    FullyQualifiedTable table = this.introspectedTable.getFullyQualifiedTable();
    this.progressCallback.startTask(Messages.getString("Progress.9", table.toString()));

    Plugin plugins = this.context.getPlugins();
    CommentGenerator commentGenerator = this.context.getCommentGenerator();

    TopLevelClass topLevelClass = new TopLevelClass(this.introspectedTable.getRecordWithBLOBsType());

    topLevelClass.setVisibility(JavaVisibility.PUBLIC);
    commentGenerator.addJavaFileComment(topLevelClass);

    String rootClass = getRootClass();
    if (this.introspectedTable.getRules().generateBaseRecordClass())
      topLevelClass.setSuperClass(this.introspectedTable.getBaseRecordType());
    else {
      topLevelClass.setSuperClass(this.introspectedTable.getPrimaryKeyType());
    }

    if (this.introspectedTable.isConstructorBased()) {
      addParameterizedConstructor(topLevelClass);

      if (!(this.introspectedTable.isImmutable())) {
        addDefaultConstructor(topLevelClass);
      }
    }

    for (IntrospectedColumn introspectedColumn : this.introspectedTable.getBLOBColumns())
    {
      if (RootClassInfo.getInstance(rootClass, this.warnings).containsProperty(introspectedColumn))
      {
        continue;
      }

      Field field = getJavaBeansField(introspectedColumn);
      if (plugins.modelFieldGenerated(field, topLevelClass, introspectedColumn, this.introspectedTable, Plugin.ModelClassType.RECORD_WITH_BLOBS))
      {
        topLevelClass.addField(field);
        topLevelClass.addImportedType(field.getType());
      }

      Method method = getJavaBeansGetter(introspectedColumn);
      if (plugins.modelGetterMethodGenerated(method, topLevelClass, introspectedColumn, this.introspectedTable, Plugin.ModelClassType.RECORD_WITH_BLOBS))
      {
        topLevelClass.addMethod(method);
      }

      if (!(this.introspectedTable.isImmutable())) {
        method = getJavaBeansSetter(introspectedColumn);
        if (plugins.modelSetterMethodGenerated(method, topLevelClass, introspectedColumn, this.introspectedTable, Plugin.ModelClassType.RECORD_WITH_BLOBS))
        {
          topLevelClass.addMethod(method);
        }
      }
    }

    List answer = new ArrayList();
    if (this.context.getPlugins().modelRecordWithBLOBsClassGenerated(topLevelClass, this.introspectedTable))
    {
      answer.add(topLevelClass);
    }
    return answer;
  }

  private void addParameterizedConstructor(TopLevelClass topLevelClass) {
    Method method = new Method();
    method.setVisibility(JavaVisibility.PUBLIC);
    method.setConstructor(true);
    method.setName(topLevelClass.getType().getShortName());
    this.context.getCommentGenerator().addGeneralMethodComment(method, this.introspectedTable);

    for (IntrospectedColumn introspectedColumn : this.introspectedTable.getAllColumns())
    {
      method.addParameter(new Parameter(introspectedColumn.getFullyQualifiedJavaType(), introspectedColumn.getJavaProperty()));
    }

    boolean comma = false;
    StringBuilder sb = new StringBuilder();
    sb.append("super(");
    for (IntrospectedColumn introspectedColumn : this.introspectedTable.getNonBLOBColumns())
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

    for (IntrospectedColumn introspectedColumn : this.introspectedTable.getBLOBColumns())
    {
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
}