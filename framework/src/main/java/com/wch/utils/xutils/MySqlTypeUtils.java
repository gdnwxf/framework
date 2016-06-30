package com.wch.utils.xutils;

import java.sql.SQLException;

import com.mysql.jdbc.Field;

public class MySqlTypeUtils {

	
	
	
		
		public String getColumnTypeName(int column) throws SQLException {
			Field field = null ;//; getField(column);

			int mysqlType = field.getMysqlType();
			int jdbcType = field.getSQLType();

			switch (mysqlType) {
			case 16:
				return "BIT";
			case 0:
			case 246:
				return ((field.isUnsigned()) ? "DECIMAL UNSIGNED" : "DECIMAL");
			case 1:
				return ((field.isUnsigned()) ? "TINYINT UNSIGNED" : "TINYINT");
			case 2:
				return ((field.isUnsigned()) ? "SMALLINT UNSIGNED" : "SMALLINT");
			case 3:
				return ((field.isUnsigned()) ? "INT UNSIGNED" : "INT");
			case 4:
				return ((field.isUnsigned()) ? "FLOAT UNSIGNED" : "FLOAT");
			case 5:
				return ((field.isUnsigned()) ? "DOUBLE UNSIGNED" : "DOUBLE");
			case 6:
				return "NULL";
			case 7:
				return "TIMESTAMP";
			case 8:
				return ((field.isUnsigned()) ? "BIGINT UNSIGNED" : "BIGINT");
			case 9:
				return ((field.isUnsigned()) ? "MEDIUMINT UNSIGNED" : "MEDIUMINT");
			case 10:
				return "DATE";
			case 11:
				return "TIME";
			case 12:
				return "DATETIME";
			case 249:
				return "TINYBLOB";
			case 250:
				return "MEDIUMBLOB";
			case 251:
				return "LONGBLOB";
			case 252:
//				if (getField(column).isBinary()) {
//					return "BLOB";
//				}

				return "TEXT";
			case 15:
				return "VARCHAR";
			case 253:
				if (jdbcType == -3) {
					return "VARBINARY";
				}

				return "VARCHAR";
			case 254:
				if (jdbcType == -2) {
					return "BINARY";
				}

				return "CHAR";
			case 247:
				return "ENUM";
			case 13:
				return "YEAR";
			case 248:
				return "SET";
			case 255:
				return "GEOMETRY";
			case 245:
				return "JSON";
			}

			return "UNKNOWN";
		}
		
		
	  public static String getClassNameForJavaType(int javaType, boolean isUnsigned, int mysqlTypeIfKnown, boolean isBinaryOrBlob, boolean isOpaqueBinary, boolean treatYearAsDate) {
			switch (javaType) {
			case -7:
			case 16:
				return "java.lang.Boolean";
			case -6:
				if (isUnsigned) {
					return "java.lang.Integer";
				}

				return "java.lang.Integer";
			case 5:
				if (isUnsigned) {
					return "java.lang.Integer";
				}

				return "java.lang.Integer";
			case 4:
				if ((!(isUnsigned)) || (mysqlTypeIfKnown == 9)) {
					return "java.lang.Integer";
				}

				return "java.lang.Long";
			case -5:
				if (!(isUnsigned)) {
					return "java.lang.Long";
				}

				return "java.math.BigInteger";
			case 2:
			case 3:
				return "java.math.BigDecimal";
			case 7:
				return "java.lang.Float";
			case 6:
			case 8:
				return "java.lang.Double";
			case -1:
			case 1:
			case 12:
				if (!(isOpaqueBinary)) {
					return "java.lang.String";
				}

				return "[B";
			case -4:
			case -3:
			case -2:
				if (mysqlTypeIfKnown == 255)
					return "[B";
				if (isBinaryOrBlob) {
					return "[B";
				}
				return "java.lang.String";
			case 91:
				return (((treatYearAsDate) || (mysqlTypeIfKnown != 13)) ? "java.sql.Date" : "java.lang.Short");
			case 92:
				return "java.sql.Time";
			case 93:
				return "java.sql.Timestamp";
			}

			return "java.lang.Object";
		}
}
