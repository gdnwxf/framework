package com.wch.generator.mybaits.mybaitsx.bean;
 

public class FlatModelRules extends BaseRules {
	public FlatModelRules(IntrospectedTable introspectedTable) {
		super(introspectedTable);
	}

	public boolean generatePrimaryKeyClass() {
		return false;
	}

	public boolean generateBaseRecordClass() {
		return true;
	}

	public boolean generateRecordWithBLOBsClass() {
		return false;
	}
}