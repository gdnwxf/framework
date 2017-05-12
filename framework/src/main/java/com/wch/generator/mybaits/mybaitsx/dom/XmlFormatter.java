package com.wch.generator.mybaits.mybaitsx.dom;


import com.wch.generator.mybaits.mybaitsx.core.MyContext;

public interface XmlFormatter {
	void setContext(MyContext arg0);

	String getFormattedContent(Document arg0);
}