/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.xmlmapper.elements;

import java.util.List;
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
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.StringUtility;

public class SelectByPrimaryKeyElementGenerator extends AbstractXmlElementGenerator
{
  public void addElements(XmlElement parentElement)
  {
    XmlElement answer = new XmlElement("select");

    answer.addAttribute(new Attribute("id", this.introspectedTable.getSelectByPrimaryKeyStatementId()));

    if (this.introspectedTable.getRules().generateResultMapWithBLOBs()) {
      answer.addAttribute(new Attribute("resultMap", this.introspectedTable.getResultMapWithBLOBsId()));
    }
    else
      answer.addAttribute(new Attribute("resultMap", this.introspectedTable.getBaseResultMapId()));
    String parameterType;
    if (this.introspectedTable.getRules().generatePrimaryKeyClass()) {
      parameterType = this.introspectedTable.getPrimaryKeyType();
    }
    else
    {
      if (this.introspectedTable.getPrimaryKeyColumns().size() > 1)
        parameterType = "map";
      else {
        parameterType = ((IntrospectedColumn)this.introspectedTable.getPrimaryKeyColumns().get(0)).getFullyQualifiedJavaType().toString();
      }

    }

    answer.addAttribute(new Attribute("parameterType", parameterType));

    this.context.getCommentGenerator().addComment(answer);

    StringBuilder sb = new StringBuilder();
    sb.append("select ");

    if (StringUtility.stringHasValue(this.introspectedTable.getSelectByPrimaryKeyQueryId()))
    {
      sb.append('\'');
      sb.append(this.introspectedTable.getSelectByPrimaryKeyQueryId());
      sb.append("' as QUERYID,");
    }
    answer.addElement(new TextElement(sb.toString()));
    answer.addElement(getBaseColumnListElement());
    if (this.introspectedTable.hasBLOBColumns()) {
      answer.addElement(new TextElement(","));
      answer.addElement(getBlobColumnListElement());
    }

    sb.setLength(0);
    sb.append("from ");
    sb.append(this.introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());

    answer.addElement(new TextElement(sb.toString()));

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

      sb.append(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(introspectedColumn));

      sb.append(" = ");
      sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));

      answer.addElement(new TextElement(sb.toString()));
    }

    if (!(this.context.getPlugins().sqlMapSelectByPrimaryKeyElementGenerated(answer, this.introspectedTable))) {
      return;
    }
    parentElement.addElement(answer);
  }
}