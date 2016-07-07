package com.wch.generator.mybaits.mybaitsx.beanutils;

import org.mybatis.generator.config.Context;

import com.wch.generator.mybaits.mybaitsx.core.MyContext;
import com.wch.generator.mybaits.mybaitsx.dom.Document;
import com.wch.generator.mybaits.mybaitsx.dom.XmlFormatter;

public class DefaultXmlFormatter implements XmlFormatter {
	protected MyContext context;

	public String getFormattedContent(Document document) {
		return document.getFormattedContent();
	}

	public void setContext(MyContext context) {
		this.context = context;
	}
}