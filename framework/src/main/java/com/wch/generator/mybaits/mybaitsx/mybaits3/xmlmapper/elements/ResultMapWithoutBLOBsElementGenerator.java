/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.xmlmapper.elements;

import java.util.List;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.StringUtility;

public class ResultMapWithoutBLOBsElementGenerator extends AbstractXmlElementGenerator
{
  private boolean isSimple;

  public ResultMapWithoutBLOBsElementGenerator(boolean isSimple)
  {
    this.isSimple = isSimple;
  }

  public void addElements(XmlElement parentElement)
  {
    XmlElement answer = new XmlElement("resultMap");
    answer.addAttribute(new Attribute("id", this.introspectedTable.getBaseResultMapId()));
    String returnType;
    if (this.isSimple) {
      returnType = this.introspectedTable.getBaseRecordType();
    }
    else
    {
      if (this.introspectedTable.getRules().generateBaseRecordClass())
        returnType = this.introspectedTable.getBaseRecordType();
      else {
        returnType = this.introspectedTable.getPrimaryKeyType();
      }
    }

    answer.addAttribute(new Attribute("type", returnType));

    this.context.getCommentGenerator().addComment(answer);

    if (this.introspectedTable.isConstructorBased())
      addResultMapConstructorElements(answer);
    else {
      addResultMapElements(answer);
    }

    if (!(this.context.getPlugins().sqlMapResultMapWithoutBLOBsElementGenerated(answer, this.introspectedTable)))
      return;
    parentElement.addElement(answer);
  }

  private void addResultMapElements(XmlElement answer)
  {
    for (IntrospectedColumn introspectedColumn : this.introspectedTable.getPrimaryKeyColumns())
    {
      XmlElement resultElement = new XmlElement("id");

      resultElement.addAttribute(new Attribute("column", MyBatis3FormattingUtilities.getRenamedColumnNameForResultMap(introspectedColumn)));

      resultElement.addAttribute(new Attribute("property", introspectedColumn.getJavaProperty()));

      resultElement.addAttribute(new Attribute("jdbcType", introspectedColumn.getJdbcTypeName()));

      if (StringUtility.stringHasValue(introspectedColumn.getTypeHandler())) {
        resultElement.addAttribute(new Attribute("typeHandler", introspectedColumn.getTypeHandler()));
      }

      answer.addElement(resultElement);
    }
    List<IntrospectedColumn> columns;
    if (this.isSimple)
      columns = this.introspectedTable.getNonPrimaryKeyColumns();
    else {
      columns = this.introspectedTable.getBaseColumns();
    }
    for (IntrospectedColumn introspectedColumn : columns) {
      XmlElement resultElement = new XmlElement("result");

      resultElement.addAttribute(new Attribute("column", MyBatis3FormattingUtilities.getRenamedColumnNameForResultMap(introspectedColumn)));

      resultElement.addAttribute(new Attribute("property", introspectedColumn.getJavaProperty()));

      resultElement.addAttribute(new Attribute("jdbcType", introspectedColumn.getJdbcTypeName()));

      if (StringUtility.stringHasValue(introspectedColumn.getTypeHandler())) {
        resultElement.addAttribute(new Attribute("typeHandler", introspectedColumn.getTypeHandler()));
      }

      answer.addElement(resultElement);
    }
  }

  private void addResultMapConstructorElements(XmlElement answer) {
    XmlElement constructor = new XmlElement("constructor");

    for (IntrospectedColumn introspectedColumn : this.introspectedTable.getPrimaryKeyColumns())
    {
      XmlElement resultElement = new XmlElement("idArg");

      resultElement.addAttribute(new Attribute("column", MyBatis3FormattingUtilities.getRenamedColumnNameForResultMap(introspectedColumn)));

      resultElement.addAttribute(new Attribute("jdbcType", introspectedColumn.getJdbcTypeName()));

      resultElement.addAttribute(new Attribute("javaType", introspectedColumn.getFullyQualifiedJavaType().getFullyQualifiedName()));

      if (StringUtility.stringHasValue(introspectedColumn.getTypeHandler())) {
        resultElement.addAttribute(new Attribute("typeHandler", introspectedColumn.getTypeHandler()));
      }

      constructor.addElement(resultElement);
    }
    List<IntrospectedColumn> columns;
    if (this.isSimple)
      columns = this.introspectedTable.getNonPrimaryKeyColumns();
    else {
      columns = this.introspectedTable.getBaseColumns();
    }
    for (IntrospectedColumn introspectedColumn : columns) {
      XmlElement resultElement = new XmlElement("arg");

      resultElement.addAttribute(new Attribute("column", MyBatis3FormattingUtilities.getRenamedColumnNameForResultMap(introspectedColumn)));

      resultElement.addAttribute(new Attribute("jdbcType", introspectedColumn.getJdbcTypeName()));

      resultElement.addAttribute(new Attribute("javaType", introspectedColumn.getFullyQualifiedJavaType().getFullyQualifiedName()));

      if (StringUtility.stringHasValue(introspectedColumn.getTypeHandler())) {
        resultElement.addAttribute(new Attribute("typeHandler", introspectedColumn.getTypeHandler()));
      }

      constructor.addElement(resultElement);
    }

    answer.addElement(constructor);
  }
}