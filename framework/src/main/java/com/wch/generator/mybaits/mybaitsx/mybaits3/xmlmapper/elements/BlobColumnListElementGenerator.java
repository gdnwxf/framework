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

public class BlobColumnListElementGenerator extends AbstractXmlElementGenerator
{
  public void addElements(XmlElement parentElement)
  {
    XmlElement answer = new XmlElement("sql");

    answer.addAttribute(new Attribute("id", this.introspectedTable.getBlobColumnListId()));

    this.context.getCommentGenerator().addComment(answer);

    StringBuilder sb = new StringBuilder();

    Iterator iter = this.introspectedTable.getBLOBColumns().iterator();

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

    if (!(this.context.getPlugins().sqlMapBlobColumnListElementGenerated(answer, this.introspectedTable)))
      return;
    parentElement.addElement(answer);
  }
}