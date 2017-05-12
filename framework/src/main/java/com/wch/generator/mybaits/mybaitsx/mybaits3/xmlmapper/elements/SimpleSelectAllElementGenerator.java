/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.xmlmapper.elements;

import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.StringUtility;

public class SimpleSelectAllElementGenerator extends AbstractXmlElementGenerator
{
  public void addElements(XmlElement parentElement)
  {
    XmlElement answer = new XmlElement("select");

    answer.addAttribute(new Attribute("id", this.introspectedTable.getSelectAllStatementId()));

    answer.addAttribute(new Attribute("resultMap", this.introspectedTable.getBaseResultMapId()));

    this.context.getCommentGenerator().addComment(answer);

    StringBuilder sb = new StringBuilder();
    sb.append("select ");
    Iterator iter = this.introspectedTable.getAllColumns().iterator();

    while (iter.hasNext()) {
      sb.append(MyBatis3FormattingUtilities.getSelectListPhrase((IntrospectedColumn)iter.next()));

      if (iter.hasNext()) {
        sb.append(", ");
      }

      if (sb.length() > 80);
      answer.addElement(new TextElement(sb.toString()));
      sb.setLength(0);
    }

    if (sb.length() > 0) {
      answer.addElement(new TextElement(sb.toString()));
    }

    sb.setLength(0);
    sb.append("from ");
    sb.append(this.introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());

    answer.addElement(new TextElement(sb.toString()));

    String orderByClause = this.introspectedTable.getTableConfigurationProperty("selectAllOrderByClause");
    boolean hasOrderBy = StringUtility.stringHasValue(orderByClause);
    if (hasOrderBy) {
      sb.setLength(0);
      sb.append("order by ");
      sb.append(orderByClause);
      answer.addElement(new TextElement(sb.toString()));
    }

    if (!(this.context.getPlugins().sqlMapSelectAllElementGenerated(answer, this.introspectedTable)))
      return;
    parentElement.addElement(answer);
  }
}