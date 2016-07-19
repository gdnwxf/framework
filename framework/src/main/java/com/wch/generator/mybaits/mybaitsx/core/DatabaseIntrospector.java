/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.core;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mybatis.generator.internal.db.ActualTableName;
import org.mybatis.generator.logging.Log;
import org.mybatis.generator.logging.LogFactory;

import com.wch.generator.mybaits.mybaitsx.bean.GeneratedKey;
import com.wch.generator.mybaits.mybaitsx.bean.IntrospectedColumn;
import com.wch.generator.mybaits.mybaitsx.bean.IntrospectedTable;
import com.wch.generator.mybaits.mybaitsx.bean.TableConfiguration;
import com.wch.generator.mybaits.mybaitsx.beanutils.FullyQualifiedJavaType;
import com.wch.generator.mybaits.mybaitsx.beanutils.FullyQualifiedTable;
import com.wch.generator.mybaits.mybaitsx.beanutils.JavaTypeResolver;
import com.wch.generator.mybaits.mybaitsx.beanutils.SqlReservedWords;
import com.wch.generator.mybaits.mybaitsx.utils.JavaBeansUtil;
import com.wch.generator.mybaits.mybaitsx.utils.Messages;
import com.wch.generator.mybaits.mybaitsx.utils.ObjectFactory;
import com.wch.generator.mybaits.mybaitsx.utils.StringUtility;

public class DatabaseIntrospector {
	private DatabaseMetaData databaseMetaData;
	private JavaTypeResolver javaTypeResolver;
	private List<String> warnings;
	private MyContext context;
	private Log logger;

	public DatabaseIntrospector(MyContext context, DatabaseMetaData databaseMetaData, JavaTypeResolver javaTypeResolver, List<String> warnings) {
		this.context = context;
		this.databaseMetaData = databaseMetaData;
		this.javaTypeResolver = javaTypeResolver;
		this.warnings = warnings;
		this.logger = LogFactory.getLog(super.getClass());
	}

	private void calculatePrimaryKey(FullyQualifiedTable table, IntrospectedTable introspectedTable) {
		ResultSet rs = null;
		try {
			rs = this.databaseMetaData.getPrimaryKeys(table.getIntrospectedCatalog(), table.getIntrospectedSchema(), table.getIntrospectedTableName());
		} catch (SQLException e) {
			closeResultSet(rs);
			this.warnings.add(Messages.getString("Warning.15"));
			return;
		}

		try {
			Map<Short , String> keyColumns = new TreeMap();
			while (rs.next()) {
				String columnName = rs.getString("COLUMN_NAME");
				short keySeq = rs.getShort("KEY_SEQ");
				keyColumns.put(Short.valueOf(keySeq), columnName);
			}

			for (String columnName : keyColumns.values())
				introspectedTable.addPrimaryKeyColumn(columnName);
		} catch (SQLException e) {
		} finally {
			closeResultSet(rs);
		}
	}

	private void closeResultSet(ResultSet rs) {
		if (rs == null)
			return;
		try {
			rs.close();
		} catch (SQLException e) {
		}
	}

	private void reportIntrospectionWarnings(IntrospectedTable introspectedTable, TableConfiguration tableConfiguration, FullyQualifiedTable table) {
	 

	 

		GeneratedKey generatedKey = tableConfiguration.getGeneratedKey();
		if ((generatedKey == null) || (introspectedTable.getColumn(generatedKey.getColumn()) != null))
			return;
		if (generatedKey.isIdentity()) {
			this.warnings.add(Messages.getString("Warning.5", generatedKey.getColumn(), table.toString()));
		} else
			this.warnings.add(Messages.getString("Warning.6", generatedKey.getColumn(), table.toString()));
	}

	public List<IntrospectedTable> introspectTables(TableConfiguration tc) throws SQLException {
		Map columns = getColumns(tc);

		if (columns.isEmpty()) {
			this.warnings.add(Messages.getString("Warning.19", tc.getCatalog(), tc.getSchema(), tc.getTableName()));

			return null;
		}

		calculateExtraColumnInformation(tc, columns);
		applyColumnOverrides(tc, columns);
		calculateIdentityColumns(tc, columns);

		List introspectedTables = calculateIntrospectedTables(tc, columns);

		Iterator iter = introspectedTables.iterator();
		while (iter.hasNext()) {
			IntrospectedTable introspectedTable = (IntrospectedTable) iter.next();

			if (!(introspectedTable.hasAnyColumns())) {
				String warning = Messages.getString("Warning.1", introspectedTable.getFullyQualifiedTable().toString());

				this.warnings.add(warning);
				iter.remove();
			} else if ((!(introspectedTable.hasPrimaryKeyColumns())) && (!(introspectedTable.hasBaseColumns()))) {
				String warning = Messages.getString("Warning.18", introspectedTable.getFullyQualifiedTable().toString());

				this.warnings.add(warning);
				iter.remove();
			} else {
				reportIntrospectionWarnings(introspectedTable, tc, introspectedTable.getFullyQualifiedTable());
			}

		}

		return introspectedTables;
	}
 

	private void calculateExtraColumnInformation(TableConfiguration tc, Map<ActualTableName, List<IntrospectedColumn>> columns) {
		StringBuilder sb = new StringBuilder();
		Pattern pattern = null;
		String replaceString = null;
		if (tc.getColumnRenamingRule() != null) {
			pattern = Pattern.compile(tc.getColumnRenamingRule().getSearchString());

			replaceString = tc.getColumnRenamingRule().getReplaceString();
			replaceString = (replaceString == null) ? "" : replaceString;
		}
		
		for (Iterator i$ = columns.entrySet().iterator(); i$.hasNext();) {
			Map.Entry<ActualTableName, List<IntrospectedColumn>> entry = (Map.Entry) i$.next();

			for (IntrospectedColumn introspectedColumn : (List<IntrospectedColumn>) entry.getValue()) {
				String calculatedColumnName;
				if (pattern == null) {
					calculatedColumnName = introspectedColumn.getActualColumnName();
				} else {
					Matcher matcher = pattern.matcher(introspectedColumn.getActualColumnName());

					calculatedColumnName = matcher.replaceAll(replaceString);
				}

				if (StringUtility.isTrue(tc.getProperty("useActualColumnNames"))) {
					introspectedColumn.setJavaProperty(JavaBeansUtil.getValidPropertyName(calculatedColumnName));
				} else if (StringUtility.isTrue(tc.getProperty("useCompoundPropertyNames"))) {
					sb.setLength(0);
					sb.append(calculatedColumnName);
					sb.append('_');
					sb.append(JavaBeansUtil.getCamelCaseString(introspectedColumn.getRemarks(), true));

					introspectedColumn.setJavaProperty(JavaBeansUtil.getValidPropertyName(sb.toString()));
				} else {
					introspectedColumn.setJavaProperty(JavaBeansUtil.getCamelCaseString(calculatedColumnName, false));
				}

				FullyQualifiedJavaType fullyQualifiedJavaType = this.javaTypeResolver.calculateJavaType(introspectedColumn);

				if (fullyQualifiedJavaType != null) {
					introspectedColumn.setFullyQualifiedJavaType(fullyQualifiedJavaType);

					introspectedColumn.setJdbcTypeName(this.javaTypeResolver.calculateJdbcTypeName(introspectedColumn));
				} else {
					boolean warn = true;
				 

					if (warn) {
						introspectedColumn.setFullyQualifiedJavaType(FullyQualifiedJavaType.getObjectInstance());

						introspectedColumn.setJdbcTypeName("OTHER");

						String warning = Messages.getString("Warning.14", Integer.toString(introspectedColumn.getJdbcType()), ((ActualTableName) entry.getKey()).toString(), introspectedColumn.getActualColumnName());

						this.warnings.add(warning);
					}
				}

				if ((this.context.autoDelimitKeywords()) && (SqlReservedWords.containsWord(introspectedColumn.getActualColumnName()))) {
					introspectedColumn.setColumnNameDelimited(true);
				}

				if (tc.isAllColumnDelimitingEnabled())
					introspectedColumn.setColumnNameDelimited(true);
			}
		}
		Map.Entry entry;
	}

	private void calculateIdentityColumns(TableConfiguration tc, Map<ActualTableName, List<IntrospectedColumn>> columns) {
		GeneratedKey gk = tc.getGeneratedKey();
		if (gk == null) {
			return;
		}

		for (Map.Entry entry : columns.entrySet()) {
			for (IntrospectedColumn introspectedColumn : (List<IntrospectedColumn>) entry.getValue())
				if (isMatchedColumn(introspectedColumn, gk))
					if ((gk.isIdentity()) || (gk.isJdbcStandard())) {
						introspectedColumn.setIdentity(true);
						introspectedColumn.setSequenceColumn(false);
					} else {
						introspectedColumn.setIdentity(false);
						introspectedColumn.setSequenceColumn(true);
					}
		}
	}

	private boolean isMatchedColumn(IntrospectedColumn introspectedColumn, GeneratedKey gk) {
		if (introspectedColumn.isColumnNameDelimited()) {
			return introspectedColumn.getActualColumnName().equals(gk.getColumn());
		}
		return introspectedColumn.getActualColumnName().equalsIgnoreCase(gk.getColumn());
	}

	private void applyColumnOverrides(TableConfiguration tc, Map<ActualTableName, List<IntrospectedColumn>> columns) {
		for (Iterator i$ = columns.entrySet().iterator(); i$.hasNext();) {
			Map.Entry entry = (Map.Entry) i$.next();

			for (IntrospectedColumn introspectedColumn : (List<IntrospectedColumn>) entry.getValue()) {

				 
			}
		}
		Map.Entry entry;
	}

	private Map<ActualTableName, List<IntrospectedColumn>> getColumns(TableConfiguration tc) throws SQLException {
		boolean delimitIdentifiers = (tc.isDelimitIdentifiers()) || (StringUtility.stringContainsSpace(tc.getCatalog())) || (StringUtility.stringContainsSpace(tc.getSchema())) || (StringUtility.stringContainsSpace(tc.getTableName()));
		String localTableName;
		String localCatalog;
		String localSchema;
		if (delimitIdentifiers) {
			  localCatalog = tc.getCatalog();
			  localSchema = tc.getSchema();
			localTableName = tc.getTableName();
		} else {
			if (this.databaseMetaData.storesLowerCaseIdentifiers()) {
				  localCatalog = (tc.getCatalog() == null) ? null : tc.getCatalog().toLowerCase();

				  localSchema = (tc.getSchema() == null) ? null : tc.getSchema().toLowerCase();

				localTableName = (tc.getTableName() == null) ? null : tc.getTableName().toLowerCase();
			} else {
				if (this.databaseMetaData.storesUpperCaseIdentifiers()) {
					  localCatalog = (tc.getCatalog() == null) ? null : tc.getCatalog().toUpperCase();

					  localSchema = (tc.getSchema() == null) ? null : tc.getSchema().toUpperCase();

					localTableName = (tc.getTableName() == null) ? null : tc.getTableName().toUpperCase();
				} else {
					localCatalog = tc.getCatalog();
					localSchema = tc.getSchema();
					localTableName = tc.getTableName();
				}
			}
		}
		if (tc.isWildcardEscapingEnabled()) {
			String escapeString = this.databaseMetaData.getSearchStringEscape();

			StringBuilder sb = new StringBuilder();

			if (localSchema != null) {
				StringTokenizer st = new StringTokenizer(localSchema, "_%", true);
				while (st.hasMoreTokens()) {
					String token = st.nextToken();
					if ((token.equals("_")) || (token.equals("%"))) {
						sb.append(escapeString);
					}
					sb.append(token);
				}
				localSchema = sb.toString();
			}

			sb.setLength(0);
			StringTokenizer st = new StringTokenizer(localTableName, "_%", true);
			while (st.hasMoreTokens()) {
				String token = st.nextToken();
				if ((token.equals("_")) || (token.equals("%"))) {
					sb.append(escapeString);
				}
				sb.append(token);
			}
			localTableName = sb.toString();
		}

		Map<ActualTableName, List<IntrospectedColumn>> answer = new HashMap();

		if (this.logger.isDebugEnabled()) {
			String fullTableName = StringUtility.composeFullyQualifiedTableName(localCatalog, localSchema, localTableName, '.');

			this.logger.debug(Messages.getString("Tracing.1", fullTableName));
		}

		ResultSet rs = this.databaseMetaData.getColumns(localCatalog, localSchema, localTableName, null);

		while (rs.next()) {
			IntrospectedColumn introspectedColumn = ObjectFactory.createIntrospectedColumn(this.context);

			introspectedColumn.setTableAlias(tc.getAlias());
			introspectedColumn.setJdbcType(rs.getInt("DATA_TYPE"));
			introspectedColumn.setLength(rs.getInt("COLUMN_SIZE"));
			introspectedColumn.setActualColumnName(rs.getString("COLUMN_NAME"));
			introspectedColumn.setNullable(rs.getInt("NULLABLE") == 1);

			introspectedColumn.setScale(rs.getInt("DECIMAL_DIGITS"));
			introspectedColumn.setRemarks(rs.getString("REMARKS"));
			introspectedColumn.setDefaultValue(rs.getString("COLUMN_DEF"));

			ActualTableName atn = new ActualTableName(rs.getString("TABLE_CAT"), rs.getString("TABLE_SCHEM"), rs.getString("TABLE_NAME"));

			List columns = (List) answer.get(atn);
			if (columns == null) {
				columns = new ArrayList();
				answer.put(atn, columns);
			}

			columns.add(introspectedColumn);

			if (this.logger.isDebugEnabled()) {
				this.logger.debug(Messages.getString("Tracing.2", introspectedColumn.getActualColumnName(), Integer.toString(introspectedColumn.getJdbcType()), atn.toString()));
			}

		}

		closeResultSet(rs);

		if ((answer.size() > 1) && (!(StringUtility.stringContainsSQLWildcard(localSchema))) && (!(StringUtility.stringContainsSQLWildcard(localTableName)))) {
			ActualTableName inputAtn = new ActualTableName(tc.getCatalog(), tc.getSchema(), tc.getTableName());

			StringBuilder sb = new StringBuilder();
			boolean comma = false;
			for (ActualTableName atn : answer.keySet()) {
				if (comma)
					sb.append(',');
				else {
					comma = true;
				}
				sb.append(atn.toString());
			}

			this.warnings.add(Messages.getString("Warning.25", inputAtn.toString(), sb.toString()));
		}

		return answer;
	}

	private List<IntrospectedTable> calculateIntrospectedTables(TableConfiguration tc, Map<ActualTableName, List<IntrospectedColumn>> columns) {
		boolean delimitIdentifiers = (tc.isDelimitIdentifiers()) || (StringUtility.stringContainsSpace(tc.getCatalog())) || (StringUtility.stringContainsSpace(tc.getSchema())) || (StringUtility.stringContainsSpace(tc.getTableName()));

		List answer = new ArrayList();

		for (Map.Entry entry : columns.entrySet()) {
			ActualTableName atn = (ActualTableName) entry.getKey();

			FullyQualifiedTable table = new FullyQualifiedTable((StringUtility.stringHasValue(tc.getCatalog())) ? atn.getCatalog() : null, (StringUtility.stringHasValue(tc.getSchema())) ? atn.getSchema() : null, atn.getTableName(), tc.getDomainObjectName(), tc.getAlias(), StringUtility.isTrue(tc.getProperty("ignoreQualifiersAtRuntime")), tc.getProperty("runtimeCatalog"), tc.getProperty("runtimeSchema"), tc.getProperty("runtimeTableName"), delimitIdentifiers, this.context);

			IntrospectedTable introspectedTable = ObjectFactory.createIntrospectedTable(tc, table, this.context);

			for (IntrospectedColumn introspectedColumn : (List<IntrospectedColumn>) entry.getValue()) {
				introspectedTable.addColumn(introspectedColumn);
			}

			calculatePrimaryKey(table, introspectedTable);

			answer.add(introspectedTable);
		}

		return answer;
	}
}