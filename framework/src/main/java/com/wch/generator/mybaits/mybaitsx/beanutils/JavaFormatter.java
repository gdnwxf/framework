package com.wch.generator.mybaits.mybaitsx.beanutils;

import com.wch.generator.mybaits.mybaitsx.core.MyContext;

public interface JavaFormatter {
	void setContext(MyContext arg0);

	String getFormattedContent(CompilationUnit arg0);
}