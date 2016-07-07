package com.wch.generator.mybaits.mybaitsx.domain;

import org.mybatis.generator.api.GeneratedFile;
import org.mybatis.generator.api.XmlFormatter;
import org.mybatis.generator.api.dom.xml.Document;

public class GeneratedXmlFile extends GeneratedFile {
	private Document document;
	private String fileName;
	private String targetPackage;
	private boolean isMergeable;
	private XmlFormatter xmlFormatter;

	public GeneratedXmlFile(Document document, String fileName, String targetPackage, String targetProject, boolean isMergeable, XmlFormatter xmlFormatter) {
		super(targetProject);
		this.document = document;
		this.fileName = fileName;
		this.targetPackage = targetPackage;
		this.isMergeable = isMergeable;
		this.xmlFormatter = xmlFormatter;
	}

	public String getFormattedContent() {
		return this.xmlFormatter.getFormattedContent(this.document);
	}

	public String getFileName() {
		return this.fileName;
	}

	public String getTargetPackage() {
		return this.targetPackage;
	}

	public boolean isMergeable() {
		return this.isMergeable;
	}
}