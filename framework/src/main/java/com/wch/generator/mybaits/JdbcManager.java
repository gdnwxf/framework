
package com.wch.generator.mybaits;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

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
	public static void main1(String[] args) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo");
		ResultSet resultSet = preparedStatement.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i < metaData.getColumnCount(); i++) {
			System.out.println( metaData.getColumnName(i));
		}
		
		while (resultSet.next() ) {
			Byte object =( (Integer) resultSet.getObject("age")).byteValue();
			System.out.println(object);
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

		ResultSet importedKeys = dataBaseMetaData.getImportedKeys(null, null, "user_info");
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

	/**
	 * The main method. 主方法
	 * @param args the arguments
	 * @throws SQLException the SQL exception
	 */
	public static void main(String[] args) throws SQLException {
		Connection connection = getConnection();
//		PreparedStatement preparedStatement = connection.prepareStatement(" show create table goods_purchase ");
		PreparedStatement preparedStatement = connection.prepareStatement(" show index from goods_purchase ");
//		PreparedStatement preparedStatement = connection.prepareStatement(" desc goods_purchase ");
//		PreparedStatement preparedStatement = connection.prepareStatement("select * from  information_schema.columns ");
		ResultSet resultSet = preparedStatement.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int columnCount = metaData.getColumnCount();
		for (int i = 1; i < columnCount; i++) {
			System.out.println( metaData.getColumnName(i));
			System.out.println( metaData.getColumnType(i));
		}
		
		
		while (resultSet.next() ) {
			for (int i = 1; i <= columnCount; i++) {
				System.out.print(resultSet.getObject(i) + " ");
			}
			System.out.println();
		}
	
		DatabaseMetaData dataBaseMetaData = connection.getMetaData();
		ResultSet primaryKeys = dataBaseMetaData.getPrimaryKeys(null, null, "userinfo");
		ResultSetMetaData metaData2 = primaryKeys.getMetaData();
		  columnCount = metaData2.getColumnCount();
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

	
	public List<Column> getColumns(String schema, String... tableName)
	{
		List<Column> columns = null ;
		
		String sql = "select * from columns where " ;
		if(StringUtils.isNoneBlank(schema))
		{
			sql += " TABLE_SCHEMA = " + schema;
		}
		
		String tableNameSql = this.join(Arrays.asList(tableName), ",", "'");
		if(StringUtils.isNoneBlank(tableNameSql))
		{
			sql += " table_name in ( " + tableNameSql + ")";
		}
		
		
		 
		
		return columns;
	}
	
	
	private String join(List<?> list, String spilt , String separator ) {
   	 // handle null, zero and one elements before building a buffer
	   if (list == null) {
         return null;
      }
   	Iterator<?> iterator = list.iterator();
       if (iterator == null) {
           return null;
       }
       if (!iterator.hasNext()) {
           return StringUtils.EMPTY;
       }
       Object first = iterator.next();
       if (!iterator.hasNext()) {
           return first == null ? "" :  spilt+first.toString()+ spilt;
       }

       // two or more elements
       StringBuilder buf = new StringBuilder(256); // Java default is 16, probably too small
       if (first != null) {
           buf.append(spilt);
           buf.append(first);
           buf.append(spilt);
       }

       while (iterator.hasNext()) {
           buf.append(separator);
           Object obj = iterator.next();
           if (obj != null) {
               buf.append(spilt);
               buf.append(obj);
               buf.append(spilt);
           }
       }

   	return buf.toString();
   }
}
