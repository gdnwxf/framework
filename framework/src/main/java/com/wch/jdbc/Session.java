package com.wch.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author GDNWXF
 * @email gdnwxf@qq.com
 * @2014年5月25日 下午6:39:43
 */
public interface Session {

	public static final String ALIAS_TO_ENTITY_MAP = "map";

	public static final String ALIAS_TO_LIST = "list";

	/**
	 * 获取链接
	 */
	public Connection getConnection() throws SQLException;

	/**
	 * 开启事务
	 * 
	 * @throws SQLException
	 */
	public Transaction beginTransaction() throws SQLException;

	/**
	 * 获取事务
	 */
	public Transaction getTransaction() throws SQLException;

	/**
	 * 关闭session
	 * 
	 * @throws SQLException
	 */
	public void close() throws SQLException;

	/**
	 * 更新数据库
	 * 
	 * @param sql
	 * @param params 以Object可变参数的形式传值
	 * @throws SQLException
	 */
	public void executeUpdate(String sql, Object... params) throws SQLException;

	/**
	 * 更新数据库
	 * 
	 * @param sql
	 * @param map 以map的形式传值
	 * @throws SQLException
	 */
	public void executeUpdate(String sql, Map<String, Object> params) throws SQLException;

	/**
	 * 将结果集转换成实体
	 * 
	 * @param clazz
	 */
	public Session addEntity(Class<?> clazz);

	/**
	 * 获取结果集
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ResultSet getResultSet() throws SQLException;

	/**
	 * 获取结果集中的字段的大写
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String[] getAliasUpper() throws SQLException;

	/**
	 * 获取结果集中的字段的小写
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String[] getAlias() throws SQLException;

	/**
	 * 以Object数组的形式查询的结果集的集合
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Object[]> getValues() throws SQLException;

	/**
	 * 以Object数组的形式返回单行的记录
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Object[] getUniqueRow() throws SQLException;

	/**
	 * 以Map的形式返回查询的结果集
	 * 
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List list() throws SQLException;

	/**
	 * 以Map的形式返回查询的单行结果集
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> getUniqueMapRow() throws SQLException;

	/**
	 * 返回唯一的结果集
	 * 
	 * @return
	 * @throws SQLException
	 */
	public <T> T getUniqueObject() throws SQLException;

	/**
	 * 获取查询的结果集以List<?>形式返回
	 * 
	 * @param <T>
	 * 
	 * @param clazz 要映射的实体类的Class类
	 * @param sql
	 * @param params
	 * @return List<?> 查询结果集
	 * @throws SQLException
	 */
	public <T> List<T> queryList(Class<T> t, String sql, Object... params) throws SQLException;

	/**
	 * 获取查询的结果集以List<?>形式返回
	 * 
	 * @param clazz 要映射的实体类的Class类
	 * @param sql
	 * @param paramMap 以map的形式传值
	 * @return List<?> 查询结果集
	 * @throws SQLException
	 */
	public List<?> queryList(Class<?> clazz, String sql, Map<String, Object> params) throws SQLException;

	/**
	 * 获取查询的结果集以List<?>形式返回
	 * 
	 * @param sql
	 * @param translate 通过自定义的赋值方式
	 * @param params
	 * @return List<?> 查询结果集
	 * @throws SQLException
	 */
	public List<?> queryList(String sql, ResultTranslate translate, Object... params) throws SQLException;

	/**
	 * 获取查询的结果集以List<?>形式返回
	 * 
	 * @param sql
	 * @param translate 通过自定义的赋值方式
	 * @param paramMap 以map的形式传值
	 * @return List<?> 查询结果集
	 * @throws SQLException
	 */
	public List<?> queryList(String sql, ResultTranslate translate, Map<String, Object> params) throws SQLException;

	/**
	 * 获取查询的结果集以List<Map<String,Object>>形式返回
	 * 
	 * @param sql
	 * @param params
	 * @return List<Map<String,Object>> 查询结果集
	 * @throws SQLException
	 */
	public List<Map<String, Object>> queryList(String sql, Object... params) throws SQLException;

	/**
	 * 获取查询的结果集以List<Map<String,Object>>形式返回
	 * 
	 * @param sql
	 * @param paramMap 以map的形式传值
	 * @return List<Map<String,Object>> 查询结果集
	 * @throws SQLException
	 */
	public List<Map<String, Object>> queryList(String sql, Map<String, Object> params) throws SQLException;

	/**
	 * 通过反射技术获取单个对象的数据记录以对象的形式返回
	 * 
	 * @param clazz
	 * @param sql
	 * @param params
	 * @return 单个对象
	 * @throws SQLException
	 */
	public Object querySingal(Class<?> clazz, String sql, Object... params) throws SQLException;

	/**
	 * 通过反射技术获取单个对象的数据记录以对象的形式返回
	 * 
	 * @param clazz
	 * @param sql
	 * @param paramMap 以map的形式传值
	 * @return 单个对象
	 * @throws SQLException
	 */
	public Object querySingal(Class<?> clazz, String sql, Map<String, Object> params) throws SQLException;

	/**
	 * 获取单个对象的数据记录以对象的形式返回
	 * 
	 * @param sql
	 * @param translate 自定义赋值
	 * @param params
	 * @return 单个对象
	 * @throws SQLException
	 */
	public Object querySingal(String sql, ResultTranslate translate, Object... params) throws SQLException;

	/**
	 * 获取单个对象的数据记录以对象的形式返回
	 * 
	 * @param sql
	 * @param translate 自定义赋值
	 * @param paramMap 以map的形式传值
	 * @return 单个对象
	 * @throws SQLException
	 */
	public Object querySingal(String sql, ResultTranslate translate, Map<String, Object> params) throws SQLException;

	/**
	 * 获取单条数据记录一Map的形式返回
	 * 
	 * @param sql
	 * @param params
	 * @return 单条数据记录
	 * @throws SQLException
	 */
	public Map<String, Object> querySingal(String sql, Object... params) throws SQLException;

	/**
	 * 获取单条数据记录一Map的形式返回
	 * 
	 * @param sql
	 * @param params
	 * @return 单条数据记录
	 * @throws SQLException
	 */
	public Map<String, Object> querySingal(String sql, Map<String, Object> params) throws SQLException;

	/**
	 * 创建预编译的环境
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public Session createSessionQuery(String sql, Object... params) throws SQLException;

	public Query createQuery(String sql, Object... params) throws SQLException;

	/**
	 * 结果集转换接口
	 * 
	 * @author GDNWXF
	 * 
	 */
	public interface ResultTranslate {

		public Object getObject(ResultSet rs);

	}
}
