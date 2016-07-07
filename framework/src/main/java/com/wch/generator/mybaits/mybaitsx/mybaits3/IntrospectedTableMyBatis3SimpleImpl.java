/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3;

import java.util.List;

import org.mybatis.generator.api.ProgressCallback;

import com.wch.generator.mybaits.mybaitsx.bean.IntrospectedTable;
import com.wch.generator.mybaits.mybaitsx.domain.GeneratedJavaFile;
import com.wch.generator.mybaits.mybaitsx.domain.GeneratedXmlFile;
 
 
public  class IntrospectedTableMyBatis3SimpleImpl extends IntrospectedTable
{
	
	
	public IntrospectedTableMyBatis3SimpleImpl( ) {
		   super(IntrospectedTable.TargetRuntime.MYBATIS3);
		     
	}

	@Override
	public void calculateGenerators(List<String> paramList, ProgressCallback paramProgressCallback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<GeneratedJavaFile> getGeneratedJavaFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GeneratedXmlFile> getGeneratedXmlFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isJava5Targeted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getGenerationSteps() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean requiresXMLGenerator() {
		// TODO Auto-generated method stub
		return false;
	} }