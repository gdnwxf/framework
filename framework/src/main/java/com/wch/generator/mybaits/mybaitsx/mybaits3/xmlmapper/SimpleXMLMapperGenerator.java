/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.xmlmapper;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.DeleteByPrimaryKeyElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.InsertElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.ResultMapWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SimpleSelectAllElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SimpleSelectByPrimaryKeyElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByPrimaryKeyWithoutBLOBsElementGenerator;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.messages.Messages;

public class SimpleXMLMapperGenerator extends AbstractXmlGenerator
{
  protected XmlElement getSqlMapElement()
  {
    FullyQualifiedTable table = this.introspectedTable.getFullyQualifiedTable();
    this.progressCallback.startTask(Messages.getString("Progress.12", table.toString()));
    XmlElement answer = new XmlElement("mapper");
    String namespace = this.introspectedTable.getMyBatis3SqlMapNamespace();
    answer.addAttribute(new Attribute("namespace", namespace));

    this.context.getCommentGenerator().addRootComment(answer);

    addResultMapElement(answer);
    addDeleteByPrimaryKeyElement(answer);
    addInsertElement(answer);
    addUpdateByPrimaryKeyElement(answer);
    addSelectByPrimaryKeyElement(answer);
    addSelectAllElement(answer);

    return answer;
  }

  protected void addResultMapElement(XmlElement parentElement) {
    if (this.introspectedTable.getRules().generateBaseResultMap()) {
      AbstractXmlElementGenerator elementGenerator = new ResultMapWithoutBLOBsElementGenerator(true);

      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addSelectByPrimaryKeyElement(XmlElement parentElement) {
    if (this.introspectedTable.getRules().generateSelectByPrimaryKey()) {
      AbstractXmlElementGenerator elementGenerator = new SimpleSelectByPrimaryKeyElementGenerator();
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addSelectAllElement(XmlElement parentElement) {
    AbstractXmlElementGenerator elementGenerator = new SimpleSelectAllElementGenerator();
    initializeAndExecuteGenerator(elementGenerator, parentElement);
  }

  protected void addDeleteByPrimaryKeyElement(XmlElement parentElement) {
    if (this.introspectedTable.getRules().generateDeleteByPrimaryKey()) {
      AbstractXmlElementGenerator elementGenerator = new DeleteByPrimaryKeyElementGenerator(true);
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addInsertElement(XmlElement parentElement) {
    if (this.introspectedTable.getRules().generateInsert()) {
      AbstractXmlElementGenerator elementGenerator = new InsertElementGenerator(true);
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addUpdateByPrimaryKeyElement(XmlElement parentElement) {
    if (this.introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
      AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeyWithoutBLOBsElementGenerator(true);

      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void initializeAndExecuteGenerator(AbstractXmlElementGenerator elementGenerator, XmlElement parentElement)
  {
    elementGenerator.setContext(this.context);
    elementGenerator.setIntrospectedTable(this.introspectedTable);
    elementGenerator.setProgressCallback(this.progressCallback);
    elementGenerator.setWarnings(this.warnings);
    elementGenerator.addElements(parentElement);
  }

  public Document getDocument()
  {
    Document document = new Document("-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd");

    document.setRootElement(getSqlMapElement());

    if (!(this.context.getPlugins().sqlMapDocumentGenerated(document, this.introspectedTable)))
    {
      document = null;
    }

    return document;
  }
}