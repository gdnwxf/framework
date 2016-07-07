/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.xmlmapper.elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.internal.rules.Rules;

public class InsertElementGenerator extends AbstractXmlElementGenerator
{
  private boolean isSimple;

  public InsertElementGenerator(boolean isSimple)
  {
    this.isSimple = isSimple;
  }

  public void addElements(XmlElement parentElement)
  {
    XmlElement answer = new XmlElement("insert");

    answer.addAttribute(new Attribute("id", this.introspectedTable.getInsertStatementId()));
    FullyQualifiedJavaType parameterType;
    if (this.isSimple) {
      parameterType = new FullyQualifiedJavaType(this.introspectedTable.getBaseRecordType());
    }
    else {
      parameterType = this.introspectedTable.getRules().calculateAllFieldsClass();
    }

    answer.addAttribute(new Attribute("parameterType", parameterType.getFullyQualifiedName()));

    this.context.getCommentGenerator().addComment(answer);

    GeneratedKey gk = this.introspectedTable.getGeneratedKey();
    if (gk != null) {
      IntrospectedColumn introspectedColumn = this.introspectedTable.getColumn(gk.getColumn());

      if (introspectedColumn != null) {
        if (gk.isJdbcStandard()) {
          answer.addAttribute(new Attribute("useGeneratedKeys", "true"));

          answer.addAttribute(new Attribute("keyProperty", introspectedColumn.getJavaProperty()));
        }
        else {
          answer.addElement(getSelectKey(introspectedColumn, gk));
        }
      }
    }

    StringBuilder insertClause = new StringBuilder();
    StringBuilder valuesClause = new StringBuilder();

    insertClause.append("insert into ");
    insertClause.append(this.introspectedTable.getFullyQualifiedTableNameAtRuntime());

    insertClause.append(" (");

    valuesClause.append("values (");

    List<String> valuesClauses = new ArrayList();
    Iterator iter = this.introspectedTable.getAllColumns().iterator();

    while (iter.hasNext()) {
      IntrospectedColumn introspectedColumn = (IntrospectedColumn)iter.next();
      if (introspectedColumn.isIdentity())
      {
        continue;
      }

      insertClause.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));

      valuesClause.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));

      if (iter.hasNext()) {
        insertClause.append(", ");
        valuesClause.append(", ");
      }

      if (valuesClause.length() > 80) {
        answer.addElement(new TextElement(insertClause.toString()));
        insertClause.setLength(0);
        OutputUtilities.xmlIndent(insertClause, 1);

        valuesClauses.add(valuesClause.toString());
        valuesClause.setLength(0);
        OutputUtilities.xmlIndent(valuesClause, 1);
      }
    }

    insertClause.append(')');
    answer.addElement(new TextElement(insertClause.toString()));

    valuesClause.append(')');
    valuesClauses.add(valuesClause.toString());

    for (String clause : valuesClauses) {
      answer.addElement(new TextElement(clause));
    }

    if (!(this.context.getPlugins().sqlMapInsertElementGenerated(answer, this.introspectedTable)))
      return;
    parentElement.addElement(answer);
  }
}