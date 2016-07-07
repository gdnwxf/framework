/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.xmlmapper.elements;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;

public class UpdateByPrimaryKeySelectiveElementGenerator extends AbstractXmlElementGenerator
{
  public void addElements(XmlElement parentElement)
  {
    XmlElement answer = new XmlElement("update");

    answer.addAttribute(new Attribute("id", this.introspectedTable.getUpdateByPrimaryKeySelectiveStatementId()));
    String parameterType;
    if (this.introspectedTable.getRules().generateRecordWithBLOBsClass())
      parameterType = this.introspectedTable.getRecordWithBLOBsType();
    else {
      parameterType = this.introspectedTable.getBaseRecordType();
    }

    answer.addAttribute(new Attribute("parameterType", parameterType));

    this.context.getCommentGenerator().addComment(answer);

    StringBuilder sb = new StringBuilder();

    sb.append("update ");
    sb.append(this.introspectedTable.getFullyQualifiedTableNameAtRuntime());
    answer.addElement(new TextElement(sb.toString()));

    XmlElement dynamicElement = new XmlElement("set");
    answer.addElement(dynamicElement);

    for (IntrospectedColumn introspectedColumn : this.introspectedTable.getNonPrimaryKeyColumns())
    {
      XmlElement isNotNullElement = new XmlElement("if");
      sb.setLength(0);
      sb.append(introspectedColumn.getJavaProperty());
      sb.append(" != null");
      isNotNullElement.addAttribute(new Attribute("test", sb.toString()));
      dynamicElement.addElement(isNotNullElement);

      sb.setLength(0);
      sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));

      sb.append(" = ");
      sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));

      sb.append(',');

      isNotNullElement.addElement(new TextElement(sb.toString()));
    }

    boolean and = false;
    for (IntrospectedColumn introspectedColumn : this.introspectedTable.getPrimaryKeyColumns())
    {
      sb.setLength(0);
      if (and) {
        sb.append("  and ");
      } else {
        sb.append("where ");
        and = true;
      }

      sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));

      sb.append(" = ");
      sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));

      answer.addElement(new TextElement(sb.toString()));
    }

    if (!(this.context.getPlugins().sqlMapUpdateByPrimaryKeySelectiveElementGenerated(answer, this.introspectedTable))) {
      return;
    }
    parentElement.addElement(answer);
  }
}