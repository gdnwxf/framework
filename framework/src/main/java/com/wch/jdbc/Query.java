package com.wch.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * The Interface Query.
 *
 * @author gdnwxf
 * @version 1.0
 * @since 2014
 * @email gdnwxf@qq.com
 * @date 2015-1-22 18:44:16
 */
public interface Query {

	/**
	 * 返回查询到的结果集.
	 *
	 * @return the result set
	 * @throws SQLException the SQL exception
	 */
	public abstract ResultSet getResultSet() throws SQLException;

	/**
	 * 查出的列的String 数组转成大写.
	 *
	 * @return the alias upper
	 * @throws SQLException the SQL exception
	 */
	public abstract String[] getAliasUpper() throws SQLException;

	/**
	 * 返回结果集中查出的列的String 数组.
	 *
	 * @return the alias
	 * @throws SQLException the SQL exception
	 */
	public abstract String[] getAlias() throws SQLException;

	/**
	 * 以对象数组的形式返回.
	 *
	 * @return the values
	 * @throws SQLException the SQL exception
	 */
	@SuppressWarnings("rawtypes")
	public abstract List getValues() throws SQLException;

	/**
	 * 以list<Map<String,Object>> 返回从数据库中查出的东西.
	 *
	 * @return the list
	 * @throws SQLException the SQL exception
	 */
	@SuppressWarnings("rawtypes")
	public abstract List list() throws SQLException;

	/**
	 * 以Object[]返回唯一的行值.
	 *
	 * @return the unique row
	 * @throws SQLException the SQL exception
	 */
	public abstract Object[] getUniqueRow() throws SQLException;

	/**
	 * Map<String,Object> 的形式返回一行值.
	 *
	 * @return the unique map row
	 * @throws SQLException the SQL exception
	 */
	public abstract Map<String, Object> getUniqueMapRow() throws SQLException;
	
	/**
	 * 加入实体转换.
	 *
	 * @param clazz the clazz
	 * @return the query
	 */
	public abstract Query addEntity(Class<?> clazz);
	
	/**
	 * 用preparedment的方式获取ResultSet .
	 *
	 * @return the prepared result set
	 * @throws SQLException the SQL exception
	 */
	public ResultSet getPreparedResultSet() throws SQLException ;

	public abstract Object uniqueResult() throws SQLException;

	public abstract Object uniqueResult(int colIndex) throws SQLException;

	public abstract Object uniqueResult(String columnName) throws SQLException;

	public abstract Integer count() throws SQLException;

	public abstract Query setResultTransformer(Alias alias);

}
