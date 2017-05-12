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

public class UpdateByExampleSelectiveElementGenerator extends AbstractXmlElementGenerator
{
  public void addElements(XmlElement parentElement)
  {
    XmlElement answer = new XmlElement("update");

    answer.addAttribute(new Attribute("id", this.introspectedTable.getUpdateByExampleSelectiveStatementId()));

    answer.addAttribute(new Attribute("parameterType", "map"));

    this.context.getCommentGenerator().addComment(answer);

    StringBuilder sb = new StringBuilder();
    sb.append("update ");
    sb.append(this.introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());

    answer.addElement(new TextElement(sb.toString()));

    XmlElement dynamicElement = new XmlElement("set");
    answer.addElement(dynamicElement);

    for (IntrospectedColumn introspectedColumn : this.introspectedTable.getAllColumns())
    {
      XmlElement isNotNullElement = new XmlElement("if");
      sb.setLength(0);
      sb.append(introspectedColumn.getJavaProperty("record."));
      sb.append(" != null");
      isNotNullElement.addAttribute(new Attribute("test", sb.toString()));
      dynamicElement.addElement(isNotNullElement);

      sb.setLength(0);
      sb.append(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(introspectedColumn));

      sb.append(" = ");
      sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn, "record."));

      sb.append(',');

      isNotNullElement.addElement(new TextElement(sb.toString()));
    }

    answer.addElement(getUpdateByExampleIncludeElement());

    if (!(this.context.getPlugins().sqlMapUpdateByExampleSelectiveElementGenerated(answer, this.introspectedTable))) {
      return;
    }
    parentElement.addElement(answer);
  }
}