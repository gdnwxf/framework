/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.xmlmapper.elements;

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

public class ResultMapWithBLOBsElementGenerator extends AbstractXmlElementGenerator
{
  public void addElements(XmlElement parentElement)
  {
    XmlElement answer = new XmlElement("resultMap");

    answer.addAttribute(new Attribute("id", this.introspectedTable.getResultMapWithBLOBsId()));
    String returnType;
    if (this.introspectedTable.getRules().generateRecordWithBLOBsClass()) {
      returnType = this.introspectedTable.getRecordWithBLOBsType();
    }
    else
    {
      returnType = this.introspectedTable.getBaseRecordType();
    }

    answer.addAttribute(new Attribute("type", returnType));

    if (!(this.introspectedTable.isConstructorBased())) {
      answer.addAttribute(new Attribute("extends", this.introspectedTable.getBaseResultMapId()));
    }

    this.context.getCommentGenerator().addComment(answer);

    if (this.introspectedTable.isConstructorBased())
      addResultMapConstructorElements(answer);
    else {
      addResultMapElements(answer);
    }

    if (!(this.context.getPlugins().sqlMapResultMapWithBLOBsElementGenerated(answer, this.introspectedTable))) {
      return;
    }
    parentElement.addElement(answer);
  }

  private void addResultMapElements(XmlElement answer)
  {
    for (IntrospectedColumn introspectedColumn : this.introspectedTable.getBLOBColumns())
    {
      XmlElement resultElement = new XmlElement("result");

      resultElement.addAttribute(new Attribute("column", MyBatis3FormattingUtilities.getRenamedColumnNameForResultMap(introspectedColumn)));

      resultElement.addAttribute(new Attribute("property", introspectedColumn.getJavaProperty()));

      resultElement.addAttribute(new Attribute("jdbcType", introspectedColumn.getJdbcTypeName()));

      if (StringUtility.stringHasValue(introspectedColumn.getTypeHandler()))
      {
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

      if (StringUtility.stringHasValue(introspectedColumn.getTypeHandler()))
      {
        resultElement.addAttribute(new Attribute("typeHandler", introspectedColumn.getTypeHandler()));
      }

      constructor.addElement(resultElement);
    }

    for (IntrospectedColumn introspectedColumn : this.introspectedTable.getNonPrimaryKeyColumns())
    {
      XmlElement resultElement = new XmlElement("arg");

      resultElement.addAttribute(new Attribute("column", MyBatis3FormattingUtilities.getRenamedColumnNameForResultMap(introspectedColumn)));

      resultElement.addAttribute(new Attribute("jdbcType", introspectedColumn.getJdbcTypeName()));

      if (introspectedColumn.getFullyQualifiedJavaType().isPrimitive())
      {
        StringBuilder sb = new StringBuilder();
        sb.append('_');
        sb.append(introspectedColumn.getFullyQualifiedJavaType().getShortName());
        resultElement.addAttribute(new Attribute("javaType", sb.toString()));
      }
      else if ("byte[]".equals(introspectedColumn.getFullyQualifiedJavaType().getFullyQualifiedName()))
      {
        resultElement.addAttribute(new Attribute("javaType", "_byte[]"));
      }
      else {
        resultElement.addAttribute(new Attribute("javaType", introspectedColumn.getFullyQualifiedJavaType().getFullyQualifiedName()));
      }

      if (StringUtility.stringHasValue(introspectedColumn.getTypeHandler()))
      {
        resultElement.addAttribute(new Attribute("typeHandler", introspectedColumn.getTypeHandler()));
      }

      constructor.addElement(resultElement);
    }

    answer.addElement(constructor);
  }
}