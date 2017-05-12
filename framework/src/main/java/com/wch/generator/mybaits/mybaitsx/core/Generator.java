package com.wch.generator.mybaits.mybaitsx.core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wch.generator.mybaits.mybaitsx.bean.TableConfiguration;
import com.wch.generator.mybaits.mybaitsx.domain.GeneratedJavaFile;
import com.wch.generator.mybaits.mybaitsx.domain.GeneratedXmlFile;


public class Generator {

	public static void main(String[] args) throws InterruptedException, SQLException {

		MyContext mContext = new MyContext(null);
		List<GeneratedJavaFile> generatedJavaFiles = null;
		List<GeneratedXmlFile> generatedXmlFiles = null;
		List<String> warnings = new  ArrayList<String>();
		Set<String> fullyQualifiedTableNames = new HashSet<String>();
		fullyQualifiedTableNames.add("account_info");
		TableConfiguration tc = new TableConfiguration(mContext);
		tc.setTableName("account_info");
		mContext.addTableConfiguration(tc);
		mContext.introspectTables(warnings, fullyQualifiedTableNames);
		mContext.generateFiles(generatedJavaFiles, generatedXmlFiles, warnings);
	}

}
