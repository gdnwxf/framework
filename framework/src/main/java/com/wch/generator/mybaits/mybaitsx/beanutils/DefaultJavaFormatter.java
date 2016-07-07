package com.wch.generator.mybaits.mybaitsx.beanutils;

import com.wch.generator.mybaits.mybaitsx.core.MyContext;

public class DefaultJavaFormatter implements JavaFormatter {
	protected MyContext context;

	public String getFormattedContent(CompilationUnit compilationUnit) {
		return compilationUnit.getFormattedContent();
	}

	public void setContext(MyContext context) {
		this.context = context;
	}
}