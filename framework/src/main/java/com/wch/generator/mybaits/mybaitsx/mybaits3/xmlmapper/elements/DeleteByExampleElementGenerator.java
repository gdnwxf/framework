/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.xmlmapper.elements;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Context;

public class DeleteByExampleElementGenerator extends AbstractXmlElementGenerator
{
  public void addElements(XmlElement parentElement)
  {
    XmlElement answer = new XmlElement("delete");

    String fqjt = this.introspectedTable.getExampleType();

    answer.addAttribute(new Attribute("id", this.introspectedTable.getDeleteByExampleStatementId()));

    answer.addAttribute(new Attribute("parameterType", fqjt));

    this.context.getCommentGenerator().addComment(answer);

    StringBuilder sb = new StringBuilder();
    sb.append("delete from ");
    sb.append(this.introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());

    answer.addElement(new TextElement(sb.toString()));
    answer.addElement(getExampleIncludeElement());

    if (!(this.context.getPlugins().sqlMapDeleteByExampleElementGenerated(answer, this.introspectedTable)))
      return;
    parentElement.addElement(answer);
  }
}