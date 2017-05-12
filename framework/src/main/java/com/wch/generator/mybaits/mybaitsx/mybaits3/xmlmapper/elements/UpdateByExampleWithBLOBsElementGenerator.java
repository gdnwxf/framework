/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.xmlmapper.elements;

import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.Context;

public class UpdateByExampleWithBLOBsElementGenerator extends AbstractXmlElementGenerator
{
  public void addElements(XmlElement parentElement)
  {
    XmlElement answer = new XmlElement("update");

    answer.addAttribute(new Attribute("id", this.introspectedTable.getUpdateByExampleWithBLOBsStatementId()));

    answer.addAttribute(new Attribute("parameterType", "map"));
    this.context.getCommentGenerator().addComment(answer);

    StringBuilder sb = new StringBuilder();
    sb.append("update ");
    sb.append(this.introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());

    answer.addElement(new TextElement(sb.toString()));

    sb.setLength(0);
    sb.append("set ");

    Iterator iter = this.introspectedTable.getAllColumns().iterator();

    while (iter.hasNext()) {
      IntrospectedColumn introspectedColumn = (IntrospectedColumn)iter.next();

      sb.append(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(introspectedColumn));

      sb.append(" = ");
      sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn, "record."));

      if (iter.hasNext()) {
        sb.append(',');
      }

      answer.addElement(new TextElement(sb.toString()));

      if (iter.hasNext()) {
        sb.setLength(0);
        OutputUtilities.xmlIndent(sb, 1);
      }
    }

    answer.addElement(getUpdateByExampleIncludeElement());

    if (!(this.context.getPlugins().sqlMapUpdateByExampleWithBLOBsElementGenerated(answer, this.introspectedTable))) {
      return;
    }
    parentElement.addElement(answer);
  }
}