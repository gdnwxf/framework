/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.javamapper.elements.annotated;

import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectAllMethodGenerator;
import org.mybatis.generator.internal.util.StringUtility;

public class AnnotatedSelectAllMethodGenerator extends SelectAllMethodGenerator
{
  public void addMapperAnnotations(Interface interfaze, Method method)
  {
    interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Select"));

    StringBuilder sb = new StringBuilder();
    method.addAnnotation("@Select({");
    OutputUtilities.javaIndent(sb, 1);
    sb.append("\"select\",");
    method.addAnnotation(sb.toString());

    Iterator iter = this.introspectedTable.getAllColumns().iterator();

    sb.setLength(0);
    OutputUtilities.javaIndent(sb, 1);
    sb.append('"');
    boolean hasColumns = false;
    while (iter.hasNext()) {
      sb.append(StringUtility.escapeStringForJava(MyBatis3FormattingUtilities.getSelectListPhrase((IntrospectedColumn)iter.next())));
      hasColumns = true;

      if (iter.hasNext()) {
        sb.append(", ");
      }

      if (sb.length() > 80);
      sb.append("\",");
      method.addAnnotation(sb.toString());

      sb.setLength(0);
      OutputUtilities.javaIndent(sb, 1);
      sb.append('"');
      hasColumns = false;
    }

    if (hasColumns) {
      sb.append("\",");
      method.addAnnotation(sb.toString());
    }

    String orderByClause = this.introspectedTable.getTableConfigurationProperty("selectAllOrderByClause");
    boolean hasOrderBy = StringUtility.stringHasValue(orderByClause);

    sb.setLength(0);
    OutputUtilities.javaIndent(sb, 1);
    sb.append("\"from ");
    sb.append(StringUtility.escapeStringForJava(this.introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()));

    sb.append('"');
    if (hasOrderBy) {
      sb.append(',');
    }
    method.addAnnotation(sb.toString());

    if (hasOrderBy) {
      sb.setLength(0);
      OutputUtilities.javaIndent(sb, 1);
      sb.append("\"order by ");
      sb.append(orderByClause);
      sb.append('"');
      method.addAnnotation(sb.toString());
    }

    method.addAnnotation("})");

    addAnnotatedResults(interfaze, method);
  }

  private void addAnnotatedResults(Interface interfaze, Method method) {
    interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.type.JdbcType"));

    if (this.introspectedTable.isConstructorBased()) {
      interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Arg"));
      interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.ConstructorArgs"));
      method.addAnnotation("@ConstructorArgs({");
    } else {
      interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Result"));
      interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Results"));
      method.addAnnotation("@Results({");
    }

    StringBuilder sb = new StringBuilder();

    Iterator iterPk = this.introspectedTable.getPrimaryKeyColumns().iterator();
    Iterator iterNonPk = this.introspectedTable.getNonPrimaryKeyColumns().iterator();
    while (iterPk.hasNext()) {
      IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterPk.next();
      sb.setLength(0);
      OutputUtilities.javaIndent(sb, 1);
      sb.append(getResultAnnotation(interfaze, introspectedColumn, true, this.introspectedTable.isConstructorBased()));

      if ((iterPk.hasNext()) || (iterNonPk.hasNext())) {
        sb.append(',');
      }

      method.addAnnotation(sb.toString());
    }

    while (iterNonPk.hasNext()) {
      IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterNonPk.next();
      sb.setLength(0);
      OutputUtilities.javaIndent(sb, 1);
      sb.append(getResultAnnotation(interfaze, introspectedColumn, false, this.introspectedTable.isConstructorBased()));

      if (iterNonPk.hasNext()) {
        sb.append(',');
      }

      method.addAnnotation(sb.toString());
    }

    method.addAnnotation("})");
  }
}