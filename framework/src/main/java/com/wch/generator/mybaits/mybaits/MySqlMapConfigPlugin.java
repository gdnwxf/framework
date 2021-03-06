package com.wch.generator.mybaits.mybaits;

import java.util.List;

import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;



import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

public class MySqlMapConfigPlugin extends PluginAdapter {

	private List<String> sqlMapFiles = new ArrayList();
	@Override
	public boolean validate(List<String> warnings) {
		boolean valid = true;
		if (!StringUtility.stringHasValue(this.properties.getProperty("targetProject"))) {
			warnings.add(Messages.getString("ValidationError.18", "SqlMapConfigPlugin", "targetProject"));
			valid = false;
		}

		if (!StringUtility.stringHasValue(this.properties.getProperty("targetPackage"))) {
			warnings.add(Messages.getString("ValidationError.18", "SqlMapConfigPlugin", "targetPackage"));
			valid = false;
		}

		return valid;
	}

	public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles() {
		Document document = new Document("-//ibatis.apache.org//DTD SQL Map Config 2.0//EN", "http://ibatis.apache.org/dtd/sql-map-config-2.dtd");
		XmlElement root = new XmlElement("sqlMapConfig");
		document.setRootElement(root);
		root.addElement(new TextElement("<!--"));
		root.addElement(new TextElement("  This file is generated by MyBatis Generator."));
		root.addElement(new TextElement("  This file is the shell of an SqlMapConfig file - in many cases you will need to add"));
		root.addElement(new TextElement("    to this file before it is usable by iBATIS."));
		StringBuilder sb = new StringBuilder();
		sb.append("  This file was generated on ");
		sb.append(new Date());
		sb.append('.');
		root.addElement(new TextElement(sb.toString()));
		root.addElement(new TextElement("-->"));
		XmlElement settings = new XmlElement("settings");
		settings.addAttribute(new Attribute("useStatementNamespaces", "true"));
		root.addElement(settings);
		Iterator gxf = this.sqlMapFiles.iterator();

		while (gxf.hasNext()) {
			String answer = (String) gxf.next();
			XmlElement sqlMap = new XmlElement("sqlMap");
			sqlMap.addAttribute(new Attribute("resource", answer));
			root.addElement(sqlMap);
		}

		GeneratedXmlFile gxf1 = new GeneratedXmlFile(document, this.properties.getProperty("fileName", "SqlMapConfig.xml"), this.properties.getProperty("targetPackage"), this.properties.getProperty("targetProject"), false, this.context.getXmlFormatter());
		ArrayList answer1 = new ArrayList(1);
		answer1.add(gxf1);
		return answer1;
	}

	public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
		StringBuilder sb = new StringBuilder();
		sb.append(sqlMap.getTargetPackage());
		sb.append('.');
		String temp = sb.toString();
		sb.setLength(0);
		sb.append(temp.replace('.', '/'));
		sb.append(sqlMap.getFileName());
		this.sqlMapFiles.add(sb.toString());
		return true;
	}
	
	

}
