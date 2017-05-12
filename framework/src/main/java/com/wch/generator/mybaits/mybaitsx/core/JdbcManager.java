
package com.wch.generator.mybaits.mybaitsx.core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * The Class JdbcManager.
 */
public class JdbcManager {

	/** The Constant DRIVER_NAME. */
	private static final String	DRIVER_NAME				= "driver";

	/** The Constant URL. */
	private static final String	URL						= "url";

	/** The Constant JDBC_CONFIG_PROPERTIES. */
	private static final String	JDBC_CONFIG_PROPERTIES	= "jdbcConfig.properties";

	/** The properties. */
	private static Properties	properties				= null;

	static {
		try {
			if (properties == null) {
				properties = System.getProperties();
			}
			properties.load(ClassLoader.getSystemResourceAsStream(JDBC_CONFIG_PROPERTIES));
			Class.forName(properties.getProperty(DRIVER_NAME));
		}
		catch (ClassNotFoundException e) {
			throw new RuntimeException("找不到对应的驱动" + properties.getProperty(DRIVER_NAME));
		}
		catch (IOException e) {
			throw new RuntimeException("找不到对应的配置文件" + JDBC_CONFIG_PROPERTIES);
		}
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws SQLException the SQL exception
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(properties.getProperty(URL), properties);
	}

	/**
	 * Close.
	 *
	 * @param obj the obj
	 * @throws SQLException the SQL exception
	 */
	public static void close(Object obj) throws SQLException {
		if (obj != null) { throw new SQLException("对象为空 " + obj); }

		if (obj instanceof PreparedStatement) {
			PreparedStatement pStatement = (PreparedStatement) obj;
			pStatement.close();
		}
		else if (obj instanceof Connection) {
			Connection connection = (Connection) obj;
			connection.close();
		}
		else if (obj instanceof ResultSet) {
			ResultSet resultSet = (ResultSet) obj;
			resultSet.close();
		}
		
	}

	/**
	 * The main method. 主方法
	 * @param args the arguments
	 * @throws SQLException the SQL exception
	 */
	public static void main(String[] args) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo");
		ResultSet resultSet = preparedStatement.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i < metaData.getColumnCount(); i++) {
			System.out.println( metaData.getColumnName(i));
			System.out.println( metaData.getColumnType(i));
		}
		
		
		while (resultSet.next() ) {
//			System.out.println(resultSet.getObject(0));
		}
	
		DatabaseMetaData dataBaseMetaData = connection.getMetaData();
		ResultSet primaryKeys = dataBaseMetaData.getPrimaryKeys(null, null, "userinfo");
		ResultSetMetaData metaData2 = primaryKeys.getMetaData();
		int columnCount = metaData2.getColumnCount();
		for (int i = 1; i < columnCount + 1; i++) {
			System.out.println(metaData2.getColumnName(i));
		}
		while (primaryKeys.next()) {
			System.out.println(primaryKeys.getString("COLUMN_NAME"));
			System.out.println(primaryKeys.getString("PK_NAME"));
		}
	
		ResultSet importedKeys = dataBaseMetaData.getImportedKeys(null, null, "userinfo");
		for (; importedKeys.next();) {
			System.out.println(importedKeys.getString("FK_NAME"));
			System.out.println(importedKeys.getString("FKCOLUMN_NAME"));
			System.out.println(importedKeys.getString("PKCOLUMN_NAME"));
		}
	
		System.out.println("-------------------");
	
		ResultSetMetaData metaData3 = importedKeys.getMetaData();
		int columnCount2 = metaData3.getColumnCount();
		for (int i = 1; i < columnCount2; i++) {
			System.out.println(metaData3.getColumnName(i));
		}
		
		
		resultSet.close();
		preparedStatement.close();
		connection.close();
	}

}
