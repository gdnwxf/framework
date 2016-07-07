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
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.BaseColumnListElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.BlobColumnListElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.CountByExampleElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.DeleteByExampleElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.DeleteByPrimaryKeyElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.ExampleWhereClauseElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.InsertElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.InsertSelectiveElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.ResultMapWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.ResultMapWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SelectByExampleWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SelectByExampleWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SelectByPrimaryKeyElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByExampleSelectiveElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByExampleWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByExampleWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByPrimaryKeySelectiveElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByPrimaryKeyWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByPrimaryKeyWithoutBLOBsElementGenerator;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.messages.Messages;

public class XMLMapperGenerator extends AbstractXmlGenerator
{
  protected XmlElement getSqlMapElement()
  {
    FullyQualifiedTable table = this.introspectedTable.getFullyQualifiedTable();
    this.progressCallback.startTask(Messages.getString("Progress.12", table.toString()));

    XmlElement answer = new XmlElement("mapper");
    String namespace = this.introspectedTable.getMyBatis3SqlMapNamespace();
    answer.addAttribute(new Attribute("namespace", namespace));

    this.context.getCommentGenerator().addRootComment(answer);

    addResultMapWithoutBLOBsElement(answer);
    addResultMapWithBLOBsElement(answer);
    addExampleWhereClauseElement(answer);
    addMyBatis3UpdateByExampleWhereClauseElement(answer);
    addBaseColumnListElement(answer);
    addBlobColumnListElement(answer);
    addSelectByExampleWithBLOBsElement(answer);
    addSelectByExampleWithoutBLOBsElement(answer);
    addSelectByPrimaryKeyElement(answer);
    addDeleteByPrimaryKeyElement(answer);
    addDeleteByExampleElement(answer);
    addInsertElement(answer);
    addInsertSelectiveElement(answer);
    addCountByExampleElement(answer);
    addUpdateByExampleSelectiveElement(answer);
    addUpdateByExampleWithBLOBsElement(answer);
    addUpdateByExampleWithoutBLOBsElement(answer);
    addUpdateByPrimaryKeySelectiveElement(answer);
    addUpdateByPrimaryKeyWithBLOBsElement(answer);
    addUpdateByPrimaryKeyWithoutBLOBsElement(answer);

    return answer;
  }

  protected void addResultMapWithoutBLOBsElement(XmlElement parentElement) {
    if (this.introspectedTable.getRules().generateBaseResultMap()) {
      AbstractXmlElementGenerator elementGenerator = new ResultMapWithoutBLOBsElementGenerator(false);
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addResultMapWithBLOBsElement(XmlElement parentElement) {
    if (this.introspectedTable.getRules().generateResultMapWithBLOBs()) {
      AbstractXmlElementGenerator elementGenerator = new ResultMapWithBLOBsElementGenerator();
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addExampleWhereClauseElement(XmlElement parentElement) {
    if (this.introspectedTable.getRules().generateSQLExampleWhereClause()) {
      AbstractXmlElementGenerator elementGenerator = new ExampleWhereClauseElementGenerator(false);

      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addMyBatis3UpdateByExampleWhereClauseElement(XmlElement parentElement)
  {
    if (!(this.introspectedTable.getRules().generateMyBatis3UpdateByExampleWhereClause()))
      return;
    AbstractXmlElementGenerator elementGenerator = new ExampleWhereClauseElementGenerator(true);

    initializeAndExecuteGenerator(elementGenerator, parentElement);
  }

  protected void addBaseColumnListElement(XmlElement parentElement)
  {
    if (this.introspectedTable.getRules().generateBaseColumnList()) {
      AbstractXmlElementGenerator elementGenerator = new BaseColumnListElementGenerator();
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addBlobColumnListElement(XmlElement parentElement) {
    if (this.introspectedTable.getRules().generateBlobColumnList()) {
      AbstractXmlElementGenerator elementGenerator = new BlobColumnListElementGenerator();
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addSelectByExampleWithoutBLOBsElement(XmlElement parentElement)
  {
    if (this.introspectedTable.getRules().generateSelectByExampleWithoutBLOBs()) {
      AbstractXmlElementGenerator elementGenerator = new SelectByExampleWithoutBLOBsElementGenerator();
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addSelectByExampleWithBLOBsElement(XmlElement parentElement) {
    if (this.introspectedTable.getRules().generateSelectByExampleWithBLOBs()) {
      AbstractXmlElementGenerator elementGenerator = new SelectByExampleWithBLOBsElementGenerator();
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addSelectByPrimaryKeyElement(XmlElement parentElement) {
    if (this.introspectedTable.getRules().generateSelectByPrimaryKey()) {
      AbstractXmlElementGenerator elementGenerator = new SelectByPrimaryKeyElementGenerator();
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addDeleteByExampleElement(XmlElement parentElement) {
    if (this.introspectedTable.getRules().generateDeleteByExample()) {
      AbstractXmlElementGenerator elementGenerator = new DeleteByExampleElementGenerator();
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addDeleteByPrimaryKeyElement(XmlElement parentElement) {
    if (this.introspectedTable.getRules().generateDeleteByPrimaryKey()) {
      AbstractXmlElementGenerator elementGenerator = new DeleteByPrimaryKeyElementGenerator(false);
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addInsertElement(XmlElement parentElement) {
    if (this.introspectedTable.getRules().generateInsert()) {
      AbstractXmlElementGenerator elementGenerator = new InsertElementGenerator(false);
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addInsertSelectiveElement(XmlElement parentElement) {
    if (this.introspectedTable.getRules().generateInsertSelective()) {
      AbstractXmlElementGenerator elementGenerator = new InsertSelectiveElementGenerator();
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addCountByExampleElement(XmlElement parentElement) {
    if (this.introspectedTable.getRules().generateCountByExample()) {
      AbstractXmlElementGenerator elementGenerator = new CountByExampleElementGenerator();
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addUpdateByExampleSelectiveElement(XmlElement parentElement) {
    if (this.introspectedTable.getRules().generateUpdateByExampleSelective()) {
      AbstractXmlElementGenerator elementGenerator = new UpdateByExampleSelectiveElementGenerator();
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addUpdateByExampleWithBLOBsElement(XmlElement parentElement) {
    if (this.introspectedTable.getRules().generateUpdateByExampleWithBLOBs()) {
      AbstractXmlElementGenerator elementGenerator = new UpdateByExampleWithBLOBsElementGenerator();
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addUpdateByExampleWithoutBLOBsElement(XmlElement parentElement)
  {
    if (this.introspectedTable.getRules().generateUpdateByExampleWithoutBLOBs()) {
      AbstractXmlElementGenerator elementGenerator = new UpdateByExampleWithoutBLOBsElementGenerator();
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addUpdateByPrimaryKeySelectiveElement(XmlElement parentElement)
  {
    if (this.introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
      AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeySelectiveElementGenerator();
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addUpdateByPrimaryKeyWithBLOBsElement(XmlElement parentElement)
  {
    if (this.introspectedTable.getRules().generateUpdateByPrimaryKeyWithBLOBs()) {
      AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeyWithBLOBsElementGenerator();
      initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
  }

  protected void addUpdateByPrimaryKeyWithoutBLOBsElement(XmlElement parentElement)
  {
    if (!(this.introspectedTable.getRules().generateUpdateByPrimaryKeyWithoutBLOBs()))
      return;
    AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeyWithoutBLOBsElementGenerator(false);
    initializeAndExecuteGenerator(elementGenerator, parentElement);
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