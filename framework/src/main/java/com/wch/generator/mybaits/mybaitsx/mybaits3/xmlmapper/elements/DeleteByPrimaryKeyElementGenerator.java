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

public class DeleteByPrimaryKeyElementGenerator extends AbstractXmlElementGenerator
{
  private boolean isSimple;

  public DeleteByPrimaryKeyElementGenerator(boolean isSimple)
  {
    this.isSimple = isSimple;
  }

  public void addElements(XmlElement parentElement)
  {
    XmlElement answer = new XmlElement("delete");

    answer.addAttribute(new Attribute("id", this.introspectedTable.getDeleteByPrimaryKeyStatementId()));
    String parameterClass;
    if ((!(this.isSimple)) && (this.introspectedTable.getRules().generatePrimaryKeyClass())) {
      parameterClass = this.introspectedTable.getPrimaryKeyType();
    }
    else
    {
      if (this.introspectedTable.getPrimaryKeyColumns().size() > 1)
        parameterClass = "map";
      else {
        parameterClass = ((IntrospectedColumn)this.introspectedTable.getPrimaryKeyColumns().get(0)).getFullyQualifiedJavaType().toString();
      }
    }

    answer.addAttribute(new Attribute("parameterType", parameterClass));

    this.context.getCommentGenerator().addComment(answer);

    StringBuilder sb = new StringBuilder();
    sb.append("delete from ");
    sb.append(this.introspectedTable.getFullyQualifiedTableNameAtRuntime());
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

      sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));

      sb.append(" = ");
      sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));

      answer.addElement(new TextElement(sb.toString()));
    }

    if (!(this.context.getPlugins().sqlMapDeleteByPrimaryKeyElementGenerated(answer, this.introspectedTable))) {
      return;
    }
    parentElement.addElement(answer);
  }
}