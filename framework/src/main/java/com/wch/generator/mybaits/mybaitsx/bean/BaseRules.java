package com.wch.generator.mybaits.mybaitsx.bean;

import com.wch.generator.mybaits.mybaitsx.bean.IntrospectedTable.TargetRuntime;
import com.wch.generator.mybaits.mybaitsx.beanutils.FullyQualifiedJavaType;
import com.wch.generator.mybaits.mybaitsx.utils.StringUtility;

public abstract class BaseRules implements Rules {
	protected TableConfiguration tableConfiguration;
	protected IntrospectedTable introspectedTable;
	protected final boolean isModelOnly;

	public BaseRules(IntrospectedTable introspectedTable) {
		this.introspectedTable = introspectedTable;
		this.tableConfiguration = introspectedTable.getTableConfiguration();
		String modelOnly = this.tableConfiguration.getProperty("modelOnly");
		this.isModelOnly = StringUtility.isTrue(modelOnly);
	}

	public boolean generateInsert() {
		return this.isModelOnly ? false : this.tableConfiguration.isInsertStatementEnabled();
	}

	public boolean generateInsertSelective() {
		return this.isModelOnly ? false : this.tableConfiguration.isInsertStatementEnabled();
	}

	public FullyQualifiedJavaType calculateAllFieldsClass() {
		String answer;
		if (this.generateRecordWithBLOBsClass()) {
			answer = this.introspectedTable.getRecordWithBLOBsType();
		} else if (this.generateBaseRecordClass()) {
			answer = this.introspectedTable.getBaseRecordType();
		} else {
			answer = this.introspectedTable.getPrimaryKeyType();
		}

		return new FullyQualifiedJavaType(answer);
	}

	public boolean generateUpdateByPrimaryKeyWithoutBLOBs() {
		if (this.isModelOnly) {
			return false;
		} else {
			boolean rc = this.tableConfiguration.isUpdateByPrimaryKeyStatementEnabled() && this.introspectedTable.hasPrimaryKeyColumns() && this.introspectedTable.hasBaseColumns();
			return rc;
		}
	}

	public boolean generateUpdateByPrimaryKeyWithBLOBs() {
		if (this.isModelOnly) {
			return false;
		} else {
			boolean rc = this.tableConfiguration.isUpdateByPrimaryKeyStatementEnabled() && this.introspectedTable.hasPrimaryKeyColumns() && this.introspectedTable.hasBLOBColumns();
			return rc;
		}
	}

	public boolean generateUpdateByPrimaryKeySelective() {
		if (this.isModelOnly) {
			return false;
		} else {
			boolean rc = this.tableConfiguration.isUpdateByPrimaryKeyStatementEnabled() && this.introspectedTable.hasPrimaryKeyColumns() && (this.introspectedTable.hasBLOBColumns() || this.introspectedTable.hasBaseColumns());
			return rc;
		}
	}

	public boolean generateDeleteByPrimaryKey() {
		if (this.isModelOnly) {
			return false;
		} else {
			boolean rc = this.tableConfiguration.isDeleteByPrimaryKeyStatementEnabled() && this.introspectedTable.hasPrimaryKeyColumns();
			return rc;
		}
	}

	public boolean generateDeleteByExample() {
		if (this.isModelOnly) {
			return false;
		} else {
			boolean rc = this.tableConfiguration.isDeleteByExampleStatementEnabled();
			return rc;
		}
	}

	public boolean generateBaseResultMap() {
		if (this.isModelOnly) {
			return true;
		} else {
			boolean rc = this.tableConfiguration.isSelectByExampleStatementEnabled() || this.tableConfiguration.isSelectByPrimaryKeyStatementEnabled();
			return rc;
		}
	}

	public boolean generateResultMapWithBLOBs() {
		boolean rc;
		if (this.introspectedTable.hasBLOBColumns()) {
			if (this.isModelOnly) {
				rc = true;
			} else {
				rc = this.tableConfiguration.isSelectByExampleStatementEnabled() || this.tableConfiguration.isSelectByPrimaryKeyStatementEnabled();
			}
		} else {
			rc = false;
		}

		return rc;
	}

	public boolean generateSQLExampleWhereClause() {
		if (this.isModelOnly) {
			return false;
		} else {
			boolean rc = this.tableConfiguration.isSelectByExampleStatementEnabled() || this.tableConfiguration.isDeleteByExampleStatementEnabled() || this.tableConfiguration.isCountByExampleStatementEnabled();
			if (this.introspectedTable.getTargetRuntime() == TargetRuntime.IBATIS2) {
				rc |= this.tableConfiguration.isUpdateByExampleStatementEnabled();
			}

			return rc;
		}
	}

	public boolean generateMyBatis3UpdateByExampleWhereClause() {
		return this.isModelOnly ? false : this.introspectedTable.getTargetRuntime() == TargetRuntime.MYBATIS3 && this.tableConfiguration.isUpdateByExampleStatementEnabled();
	}

	public boolean generateSelectByPrimaryKey() {
		if (this.isModelOnly) {
			return false;
		} else {
			boolean rc = this.tableConfiguration.isSelectByPrimaryKeyStatementEnabled() && this.introspectedTable.hasPrimaryKeyColumns() && (this.introspectedTable.hasBaseColumns() || this.introspectedTable.hasBLOBColumns());
			return rc;
		}
	}

	public boolean generateSelectByExampleWithoutBLOBs() {
		return this.isModelOnly ? false : this.tableConfiguration.isSelectByExampleStatementEnabled();
	}

	public boolean generateSelectByExampleWithBLOBs() {
		if (this.isModelOnly) {
			return false;
		} else {
			boolean rc = this.tableConfiguration.isSelectByExampleStatementEnabled() && this.introspectedTable.hasBLOBColumns();
			return rc;
		}
	}

	public boolean generateExampleClass() {
		if (this.introspectedTable.getContext().getSqlMapGeneratorConfiguration() == null && this.introspectedTable.getContext().getJavaClientGeneratorConfiguration() == null) {
			return false;
		} else if (this.isModelOnly) {
			return false;
		} else {
			boolean rc = this.tableConfiguration.isSelectByExampleStatementEnabled() || this.tableConfiguration.isDeleteByExampleStatementEnabled() || this.tableConfiguration.isCountByExampleStatementEnabled() || this.tableConfiguration.isUpdateByExampleStatementEnabled();
			return rc;
		}
	}

	public boolean generateCountByExample() {
		if (this.isModelOnly) {
			return false;
		} else {
			boolean rc = this.tableConfiguration.isCountByExampleStatementEnabled();
			return rc;
		}
	}

	public boolean generateUpdateByExampleSelective() {
		if (this.isModelOnly) {
			return false;
		} else {
			boolean rc = this.tableConfiguration.isUpdateByExampleStatementEnabled();
			return rc;
		}
	}

	public boolean generateUpdateByExampleWithoutBLOBs() {
		if (this.isModelOnly) {
			return false;
		} else {
			boolean rc = this.tableConfiguration.isUpdateByExampleStatementEnabled() && (this.introspectedTable.hasPrimaryKeyColumns() || this.introspectedTable.hasBaseColumns());
			return rc;
		}
	}

	public boolean generateUpdateByExampleWithBLOBs() {
		if (this.isModelOnly) {
			return false;
		} else {
			boolean rc = this.tableConfiguration.isUpdateByExampleStatementEnabled() && this.introspectedTable.hasBLOBColumns();
			return rc;
		}
	}

	public IntrospectedTable getIntrospectedTable() {
		return this.introspectedTable;
	}

	public boolean generateBaseColumnList() {
		return this.isModelOnly ? false : this.generateSelectByPrimaryKey() || this.generateSelectByExampleWithoutBLOBs();
	}

	public boolean generateBlobColumnList() {
		return this.isModelOnly ? false : this.introspectedTable.hasBLOBColumns() && (this.tableConfiguration.isSelectByExampleStatementEnabled() || this.tableConfiguration.isSelectByPrimaryKeyStatementEnabled());
	}

	public boolean generateJavaClient() {
		return !this.isModelOnly;
	}
}