package com.wch.generator.mybaits.mybaits;

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
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SimpleSelectAllElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SimpleSelectByPrimaryKeyElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByExampleSelectiveElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByExampleWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByExampleWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByPrimaryKeySelectiveElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByPrimaryKeyWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByPrimaryKeyWithoutBLOBsElementGenerator;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.messages.Messages;


public class MyXMLGenerator extends AbstractXmlGenerator {
	protected XmlElement getSqlMapElement() {
		FullyQualifiedTable table = this.introspectedTable.getFullyQualifiedTable();
		this.progressCallback.startTask(Messages.getString("Progress.12", table.toString()));
		XmlElement answer = new XmlElement("mapper");
		String namespace = this.introspectedTable.getMyBatis3SqlMapNamespace();
		answer.addAttribute(new Attribute("namespace", namespace));
		this.context.getCommentGenerator().addRootComment(answer);
		this.addResultMapElement(answer);
		this.addDeleteByPrimaryKeyElement(answer);
		this.addInsertElement(answer);
		this.addUpdateByPrimaryKeyElement(answer);
		this.addSelectByPrimaryKeyElement(answer);
		this.addSelectAllElement(answer);
		return answer;
	}

	protected void addResultMapElement(XmlElement parentElement) {
		if (this.introspectedTable.getRules().generateBaseResultMap()) {
			ResultMapWithoutBLOBsElementGenerator elementGenerator = new ResultMapWithoutBLOBsElementGenerator(true);
			this.initializeAndExecuteGenerator(elementGenerator, parentElement);
		}

	}

	protected void addSelectByPrimaryKeyElement(XmlElement parentElement) {
		if (this.introspectedTable.getRules().generateSelectByPrimaryKey()) {
			SimpleSelectByPrimaryKeyElementGenerator elementGenerator = new SimpleSelectByPrimaryKeyElementGenerator();
			this.initializeAndExecuteGenerator(elementGenerator, parentElement);
		}

	}

	protected void addSelectAllElement(XmlElement parentElement) {
		SimpleSelectAllElementGenerator elementGenerator = new SimpleSelectAllElementGenerator();
		this.initializeAndExecuteGenerator(elementGenerator, parentElement);
	}

	protected void addDeleteByPrimaryKeyElement(XmlElement parentElement) {
		if (this.introspectedTable.getRules().generateDeleteByPrimaryKey()) {
			DeleteByPrimaryKeyElementGenerator elementGenerator = new DeleteByPrimaryKeyElementGenerator(true);
			this.initializeAndExecuteGenerator(elementGenerator, parentElement);
		}

	}

	protected void addInsertElement(XmlElement parentElement) {
		if (this.introspectedTable.getRules().generateInsert()) {
			InsertElementGenerator elementGenerator = new InsertElementGenerator(true);
			this.initializeAndExecuteGenerator(elementGenerator, parentElement);
		}

	}

	protected void addUpdateByPrimaryKeyElement(XmlElement parentElement) {
		if (this.introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
			UpdateByPrimaryKeyWithoutBLOBsElementGenerator elementGenerator = new UpdateByPrimaryKeyWithoutBLOBsElementGenerator(true);
			this.initializeAndExecuteGenerator(elementGenerator, parentElement);
		}

	}

	protected void initializeAndExecuteGenerator(AbstractXmlElementGenerator elementGenerator, XmlElement parentElement) {
		elementGenerator.setContext(this.context);
		elementGenerator.setIntrospectedTable(this.introspectedTable);
		elementGenerator.setProgressCallback(this.progressCallback);
		elementGenerator.setWarnings(this.warnings);
		elementGenerator.addElements(parentElement);
	}

	public Document getDocument() {
		Document document = new Document("-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd");
		document.setRootElement(this.getSqlMapElement());
		if (!this.context.getPlugins().sqlMapDocumentGenerated(document, this.introspectedTable)) {
			document = null;
		}

		return document;
	}
}
