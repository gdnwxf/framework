/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.xmlmapper.elements;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.internal.rules.Rules;

public class InsertSelectiveElementGenerator extends AbstractXmlElementGenerator
{
  public void addElements(XmlElement parentElement)
  {
    XmlElement answer = new XmlElement("insert");

    answer.addAttribute(new Attribute("id", this.introspectedTable.getInsertSelectiveStatementId()));

    FullyQualifiedJavaType parameterType = this.introspectedTable.getRules().calculateAllFieldsClass();

    answer.addAttribute(new Attribute("parameterType", parameterType.getFullyQualifiedName()));

    this.context.getCommentGenerator().addComment(answer);

    GeneratedKey gk = this.introspectedTable.getGeneratedKey();
    if (gk != null) {
      IntrospectedColumn introspectedColumn = this.introspectedTable.getColumn(gk.getColumn());

      if (introspectedColumn != null) {
        if (gk.isJdbcStandard()) {
          answer.addAttribute(new Attribute("useGeneratedKeys", "true"));
          answer.addAttribute(new Attribute("keyProperty", introspectedColumn.getJavaProperty()));
        } else {
          answer.addElement(getSelectKey(introspectedColumn, gk));
        }
      }
    }

    StringBuilder sb = new StringBuilder();

    sb.append("insert into ");
    sb.append(this.introspectedTable.getFullyQualifiedTableNameAtRuntime());
    answer.addElement(new TextElement(sb.toString()));

    XmlElement insertTrimElement = new XmlElement("trim");
    insertTrimElement.addAttribute(new Attribute("prefix", "("));
    insertTrimElement.addAttribute(new Attribute("suffix", ")"));
    insertTrimElement.addAttribute(new Attribute("suffixOverrides", ","));
    answer.addElement(insertTrimElement);

    XmlElement valuesTrimElement = new XmlElement("trim");
    valuesTrimElement.addAttribute(new Attribute("prefix", "values ("));
    valuesTrimElement.addAttribute(new Attribute("suffix", ")"));
    valuesTrimElement.addAttribute(new Attribute("suffixOverrides", ","));
    answer.addElement(valuesTrimElement);

    for (IntrospectedColumn introspectedColumn : this.introspectedTable.getAllColumns())
    {
      if (introspectedColumn.isIdentity())
      {
        continue;
      }

      if ((introspectedColumn.isSequenceColumn()) || (introspectedColumn.getFullyQualifiedJavaType().isPrimitive()))
      {
        sb.setLength(0);
        sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));

        sb.append(',');
        insertTrimElement.addElement(new TextElement(sb.toString()));

        sb.setLength(0);
        sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));

        sb.append(',');
        valuesTrimElement.addElement(new TextElement(sb.toString()));
      }

      XmlElement insertNotNullElement = new XmlElement("if");
      sb.setLength(0);
      sb.append(introspectedColumn.getJavaProperty());
      sb.append(" != null");
      insertNotNullElement.addAttribute(new Attribute("test", sb.toString()));

      sb.setLength(0);
      sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));

      sb.append(',');
      insertNotNullElement.addElement(new TextElement(sb.toString()));
      insertTrimElement.addElement(insertNotNullElement);

      XmlElement valuesNotNullElement = new XmlElement("if");
      sb.setLength(0);
      sb.append(introspectedColumn.getJavaProperty());
      sb.append(" != null");
      valuesNotNullElement.addAttribute(new Attribute("test", sb.toString()));

      sb.setLength(0);
      sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));

      sb.append(',');
      valuesNotNullElement.addElement(new TextElement(sb.toString()));
      valuesTrimElement.addElement(valuesNotNullElement);
    }

    if (!(this.context.getPlugins().sqlMapInsertSelectiveElementGenerated(answer, this.introspectedTable)))
      return;
    parentElement.addElement(answer);
  }
}