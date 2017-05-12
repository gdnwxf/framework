package com.wch.generator.mybaits.mybaitsx.bean;

import com.wch.generator.mybaits.mybaitsx.beanutils.FullyQualifiedJavaType;

public interface Rules {
	boolean generateInsert();

	boolean generateInsertSelective();

	FullyQualifiedJavaType calculateAllFieldsClass();

	boolean generateUpdateByPrimaryKeyWithoutBLOBs();

	boolean generateUpdateByPrimaryKeyWithBLOBs();

	boolean generateUpdateByPrimaryKeySelective();

	boolean generateDeleteByPrimaryKey();

	boolean generateDeleteByExample();

	boolean generateBaseResultMap();

	boolean generateResultMapWithBLOBs();

	boolean generateSQLExampleWhereClause();

	boolean generateMyBatis3UpdateByExampleWhereClause();

	boolean generateBaseColumnList();

	boolean generateBlobColumnList();

	boolean generateSelectByPrimaryKey();

	boolean generateSelectByExampleWithoutBLOBs();

	boolean generateSelectByExampleWithBLOBs();

	boolean generateExampleClass();

	boolean generateCountByExample();

	boolean generateUpdateByExampleSelective();

	boolean generateUpdateByExampleWithoutBLOBs();

	boolean generateUpdateByExampleWithBLOBs();

	boolean generatePrimaryKeyClass();

	boolean generateBaseRecordClass();

	boolean generateRecordWithBLOBsClass();

	boolean generateJavaClient();

	IntrospectedTable getIntrospectedTable();
}