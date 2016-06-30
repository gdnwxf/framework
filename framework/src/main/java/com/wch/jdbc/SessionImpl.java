package com.wch.jdbc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wch.utils.BeanUtils;
import com.wch.utils.string.StringUtils;

 
/**
 * The Class SessionImpl.
 *
 * @author gdnwxf
 * @version 1.0
 * @since 2014
 * @email gdnwxf@qq.com
 * @date 2015-1-22 18:42:39
 */
public class SessionImpl implements Session {
	
	/** 数据库链接. */
	private Connection connection = null;
	
	/** 数据库的预编译. */
	private PreparedStatement ps = null;
	
	/** 查询数据库的结果集. */
	private ResultSet rs = null;
	
	/** 数据库中的BIGDECIMAL数据类型. */
	private final Class<?> BIGDECIMAL_CLASS_NAME = java.math.BigDecimal.class;

	/** The transaction. */
	private Transaction transaction = null;

	/** The is show sql. */
	private boolean isShowSql = false;

	/** The factory. */
	private SessionFactory factory = null;

	/** The Constant pattern. */
	private final static Pattern pattern = Pattern.compile(":[\\w\\W]+?\\b");

	/** The transaction flag. */
	private boolean transactionFlag = true;

	/** 确认返回的值是list map Entity. */
	private String returnType = Session.ALIAS_TO_ENTITY_MAP;

	/** 当查询出来的东西给实体赋值. */
	private Class<?> entity = null;

	/**
	 * 单次事务.
	 *
	 * @throws SQLException the SQL exception
	 */
	public SessionImpl() throws SQLException {
		connection = SessionFactory.getInstance().getConnection();
		connection.setAutoCommit(false);// 让其事务手动提交
		isShowSql = SessionFactory.getInstance().isShowSql();
		transactionFlag = false;
	}

	/**
	 * 多次事务.
	 *
	 * @param transaction the transaction
	 * @throws SQLException the SQL exception
	 */
	public SessionImpl(Transaction transaction) throws SQLException {
		this.transaction = transaction;
		this.factory = transaction == null ? SessionFactory.getInstance() : transaction.getFactory();
		this.isShowSql = factory.isShowSql();
	}

	/**
	 * 由sessionFactory 创建session.
	 *
	 * @param connection the connection
	 * @throws SQLException the SQL exception
	 */
	public SessionImpl(Connection connection) throws SQLException {
		this.connection = connection;
		this.connection.setAutoCommit(false);// 让其事务手动提交
		isShowSql = SessionFactory.getInstance().isShowSql();
		transactionFlag = false; // 表示不进行事务的处理
	}

	/**
	 * 获取链接.
	 *
	 * @return the connection
	 * @throws SQLException the SQL exception
	 */
	public Connection getConnection() throws SQLException {
		if (!transactionFlag) {
			return connection;
		}
		if (transaction != null && transaction.isBegin()) {
			return transaction.getConnection();
		} else {
			return factory.getConnection();
		}
	}

	/**
	 * 开启事务.
	 *
	 * @return the transaction
	 * @throws SQLException the SQL exception
	 */
	public Transaction beginTransaction() throws SQLException {
		if (getConnection().getAutoCommit()) {
			getConnection().setAutoCommit(false);
		}
		// 如果不是事务类型的时候
		if (!transactionFlag) {
			transaction = Transaction.getTransaction(getConnection());
		}
		return transaction;
	}

	/**
	 * 获取事务.
	 *
	 * @return the transaction
	 * @throws SQLException the SQL exception
	 */
	public Transaction getTransaction() throws SQLException {
		if (transaction == null) {
			return Transaction.getTransaction(getConnection());
		} else {
			return transaction;
		}
	}

	/**
	 * 打印sql语句.
	 *
	 * @param sql the sql
	 */
	private void printSql(String sql) {
		if (isShowSql) {
			System.out.println(sql);
		}
	}

	/**
	 * 关闭session.
	 *
	 * @throws SQLException the SQL exception
	 */
	public void close() throws SQLException {
		if (getConnection() != null && !getConnection().isClosed()) {
			getConnection().close();
		}
	}

	/**
	 * 关闭结果集和预编译块.
	 */
	public void simpleClose() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("未能成功关闭的原因" + e);
		}
	}

	/**
	 * Gets the result transformer.
	 *
	 * @return the result transformer
	 */
	private String getResultTransformer() {
		return returnType;
	}

	/**
	 * Sets the result transformer.
	 *
	 * @param returnType the new result transformer
	 */
	public void setResultTransformer(String returnType) {
		this.returnType = returnType;
	}

	/* (non-Javadoc)
	 * @see com.wch.jdbc.Session#addEntity(java.lang.Class)
	 */
	public Session addEntity(Class<?> clazz) {
		this.entity = clazz;
		return this;
	}

	/**
	 * Gets the entity.
	 *
	 * @return the entity
	 */
	private Class<?> getEntity() {
		Class<?> temp = entity;
		entity = null;
		return temp;
	}

	/**
	 * 更新数据库.
	 *
	 * @param sql the sql
	 * @param params 以Object可变参数的形式传值
	 * @throws SQLException the SQL exception
	 */
	public void executeUpdate(String sql, Object... params) throws SQLException {
		try {

			if (getConnection().getAutoCommit()) {
				getConnection().isClosed();
				throw new SQLException("请手动开启事务");
			}
			createSessionQuery(sql, params);
			ps.executeUpdate();
		} finally {
			simpleClose();
		}
	}

	/**
	 * 更新数据库.
	 *
	 * @param sql the sql
	 * @param params the params
	 * @throws SQLException the SQL exception
	 */
	public void executeUpdate(String sql, Map<String, Object> params) throws SQLException {
		try {
			if (getConnection().getAutoCommit()) {
				getConnection().isClosed();
				throw new SQLException("请手动开启事务");
			}
			createSessionQuery(sql, params);
			ps.executeUpdate();
		} finally {
			simpleClose();
		}
	}

	/**
	 * 获取该实体类的有参或无参的构造方法.
	 *
	 * @param clazz the clazz
	 * @param fields the fields
	 * @return the constructor
	 * @throws SQLException the SQL exception
	 */
	@SuppressWarnings("unused")
	private Constructor<?> getConstructor(Class<?> clazz, Field... fields) throws SQLException {
		Constructor<?> construtor = null;
		try {
			Class<?>[] cla = null;
			if (fields != null) {
				Integer fieldLen = fields.length;
				cla = new Class[fieldLen];
				for (int i = 0; i < fieldLen; i++) {
					cla[i] = fields[i].getType();
				}
				// 得到的有参的构造函数
				construtor = clazz.getConstructor(cla);
			} else {
				construtor = clazz.getConstructor();
			}
			return construtor;
		} catch (NoSuchMethodException e) {
			throw new SQLException("无法通过反射技术获取该实体类的构造函数" + e);
		}
	}

	/**
	 * 返回查询到的结果集.
	 *
	 * @return the result set
	 * @throws SQLException the SQL exception
	 */
	public ResultSet getResultSet() throws SQLException {
		if (ps == null) {
			throw new SQLException("没有预编译sql");
		} else if (rs == null || rs.isClosed()) {
			rs = ps.executeQuery();
		}
		return rs;
	}

	/**
	 * 查出的列的String 数组转成大写.
	 *
	 * @return the alias upper
	 * @throws SQLException the SQL exception
	 */
	public String[] getAliasUpper() throws SQLException {
		getResultSet();
		int rs_col = getColsNo();
		String[] paramName = new String[rs_col];
		for (int i = 0; i < rs_col; i++) {
			// 是选择的字段另取得名称
			paramName[i] = rs.getMetaData().getColumnLabel(i + 1).toUpperCase();
		}
		return paramName;
	}

	/**
	 * 返回结果集中查出的列的String 数组.
	 *
	 * @return the alias
	 * @throws SQLException the SQL exception
	 */
	public String[] getAlias() throws SQLException {
		getResultSet();
		int rs_col = getColsNo();
		String[] paramName = new String[rs_col];
		for (int i = 0; i < rs_col; i++) {
			// 是选择的字段另取得名称
			paramName[i] = rs.getMetaData().getColumnLabel(i + 1);
		}
		return paramName;
	}

	/**
	 * Gets the cols no.
	 *
	 * @return 返回查询出的结果列的数量
	 * @throws SQLException the SQL exception
	 */
	private int getColsNo() throws SQLException {
		ResultSetMetaData rsmd = getResultSet().getMetaData();
		return rsmd.getColumnCount();
	}

	/**
	 * 以对象数组的形式返回.
	 *
	 * @return the values
	 * @throws SQLException the SQL exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getValues() throws SQLException {
		getResultSet();
		List valueList = new ArrayList();
		while (rs.next()) {
			Object[] objects = new Object[getColsNo()];
			for (int j = 0; j < getColsNo(); j++) {
				objects[j] = rs.getObject(j + 1);
			}
			if (getColsNo() == 1) {
				valueList.add(objects[0]);
			} else {
				valueList.add(objects);
			}
		}
		simpleClose();
		return valueList;
	}

	/**
	 * 以list<Map<String,Object>> 返回从数据库中查出的东西.
	 *
	 * @return the list
	 * @throws SQLException the SQL exception
	 */
	public List<?> list() throws SQLException {
		getResultSet();
		String sReturnType = getResultTransformer();
		if (sReturnType.equals(Session.ALIAS_TO_LIST)) {
			return getValues();
		}
		List<Map<String, Object>> valueList = new ArrayList<Map<String, Object>>();
		List<Object> entityList = new ArrayList<Object>();
		int iColNo = getColsNo();
		String[] alias = getAlias();
		Class<?> entityClass = getEntity();
		while (rs.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int j = 0; j < iColNo; j++) {
				map.put(alias[j], rs.getObject(alias[j]));
			}
			if (entityClass != null) {
				try {
					entityList.add(BeanUtils.mapToBean(map, entityClass));
				} catch (InstantiationException e) {
					throw new SQLException(e.getMessage());
				} catch (IllegalAccessException e) {
					throw new SQLException(e.getMessage());
				} catch (IllegalArgumentException e) {
					throw new SQLException(e.getMessage());
				} catch (InvocationTargetException e1) {
					throw new SQLException(e1.getMessage());
				}
			} else if (entityClass == null) {
				if (sReturnType.equals(Session.ALIAS_TO_ENTITY_MAP)) {
					valueList.add(map);
				}
			}

		}

		// simpleClose();
		rs.close();
		if (entityClass != null) {
			return entityList;
		} else if (sReturnType.equals(Session.ALIAS_TO_ENTITY_MAP)) {
			return valueList;
		}
		return null;
	}

	/**
	 * 以Object[]返回唯一的行值.
	 *
	 * @return the unique row
	 * @throws SQLException the SQL exception
	 */
	public Object[] getUniqueRow() throws SQLException {
		getResultSet();
		Object[] objects = new Object[getColsNo()];
		if (rs.next()) {
			for (int j = 0; j < getColsNo(); j++) {
				objects[j] = rs.getObject(j + 1);
			}
		}
		simpleClose();
		return objects;
	}

	/**
	 * Map<String,Object> 的形式返回一行值.
	 *
	 * @return the unique map row
	 * @throws SQLException the SQL exception
	 */
	public Map<String, Object> getUniqueMapRow() throws SQLException {
		getResultSet();
		Map<String, Object> map = new HashMap<String, Object>();
		if (rs.next()) {
			for (int j = 0; j < getColsNo(); j++) {
				map.put(getAlias()[j], rs.getObject(getAlias()[j]));
			}
		}
		simpleClose();
		return map;
	}

	/**
	 * 返回唯一的单值.
	 *
	 * @param <T> the generic type
	 * @return the unique object
	 * @throws SQLException the SQL exception
	 */
	public <T> T getUniqueObject() throws SQLException {
		getResultSet();
		@SuppressWarnings("unchecked")
		T t = (T) rs.getObject(1);
		simpleClose();
		return t;
	}

	/**
	 * 当从数据库获取的值为BIGDECIMAL时进行转换.
	 *
	 * @param value the value
	 * @return the obj
	 */
	private Object getObj(Object value) {
		if (value != null && BIGDECIMAL_CLASS_NAME.equals(value.getClass())) {
			BigDecimal bigDecimal = (BigDecimal) value;
			long longValue = bigDecimal.longValue();
			value = longValue;
		}
		return value;
	}

	/**
	 * 将Map 实例化成 bean.
	 *
	 * @param <T> the generic type
	 * @param map the map
	 * @param t the t
	 * @return the t
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	public static <T> T mapToBean(Map<String, Object> map, Class<T> t) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		T object = t.newInstance();
		Method[] methods = object.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			String methodName = methods[i].getName();
			if (methodName.indexOf("set") == 0) {
				String fieldName = methodName.substring(3);// 下标从0开始的
				System.out.println(fieldName);
				methods[i].invoke(object, map.get(StringUtils.char2Lower(fieldName, 0)));
			}
		}
		return object;
	}

	/**
	 * Instance object.
	 *
	 * @param <T> the generic type
	 * @param num 为0时为 无参构造方法 为1时是有参构造方法
	 * @param t the t
	 * @param map the map
	 * @return the t
	 * @throws SQLException the SQL exception
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private <T> T instanceObject(Integer num, Class<T> t, Map<String, Object> map) throws SQLException {
		T object = null;
		Field[] fields = null;
		// Constructor<?> cons = null;
		// 无参构造函数的赋值
		try {
			// cons = getConstructor(t, fields);
			fields = t.getDeclaredFields();
			if (num == 0) {
				object = t.newInstance();
				for (int i = 0; i < fields.length; i++) {
					String fieldName = fields[i].getName();// .toUpperCase();
					fields[i].setAccessible(true);
					fields[i].set(object, getObj(map.get(fieldName)));
				}
			} else if (num == 1) {
				// 有参构造函数的赋值
				// object = cons.newInstance(params);
			}
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("为对象赋值时发生错误");
		}
	}

	/**
	 * Instance object.
	 *
	 * @param <T> the generic type
	 * @param t the t
	 * @param map the map
	 * @return the t
	 * @throws SQLException the SQL exception
	 */
	private <T> T instanceObject(Class<T> t, Map<String, Object> map) throws SQLException {
		T object = null;
		Field[] fields = null;
		try {
			fields = t.getDeclaredFields();
			object = t.newInstance();
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				fields[i].setAccessible(true);
				fields[i].set(object, getObj(map.get(fieldName), fields[i].getType()));
			}
		} catch (Exception e) {
			throw new SQLException("为对象赋值时发生错误" + e.getMessage());
		}
		return object;
	}

	/**
	 * Gets the obj.
	 *
	 * @param value the value
	 * @param type the type
	 * @return the obj
	 */
	private Object getObj(Object value, Class<?> type) {
		if (value != null && BIGDECIMAL_CLASS_NAME.equals(value.getClass())) {
			BigDecimal bigDecimal = (BigDecimal) value;
			if (type == byte.class) {
				return bigDecimal.byteValue();
			} else if (type == Byte.class) {
				return bigDecimal.byteValue();
			} else if (type == short.class) {
				return bigDecimal.shortValue();
			} else if (type == Short.class) {
				return bigDecimal.shortValue();
			} else if (type == int.class) {
				return bigDecimal.intValue();
			} else if (type == Integer.class) {
				return bigDecimal.intValue();
			} else if (type == long.class) {
				return bigDecimal.longValue();
			} else if (type == Long.class) {
				return bigDecimal.longValue();
			} else if (type == float.class) {
				return bigDecimal.floatValue();
			} else if (type == Float.class) {
				return bigDecimal.floatValue();
			} else if (type == double.class) {
				return bigDecimal.doubleValue();
			} else if (type == Double.class) {
				return bigDecimal.doubleValue();
			}
		}
		return value;
	}

	/**
	 * 获取查询的结果集以List<?>形式返回.
	 *
	 * @param <T> the generic type
	 * @param t the t
	 * @param sql the sql
	 * @param params the params
	 * @return List<?> 查询结果集
	 * @throws SQLException the SQL exception
	 */
	public <T> List<T> queryList(Class<T> t, String sql, Object... params) throws SQLException {
		// new 一个List
		List<T> list = new ArrayList<T>();
		// 查询数据库中的数据然后封装成
		try {
			createSessionQuery(sql, params);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> list2 = (List<Map<String, Object>>) list();
			for (Map<String, Object> map : list2) {
				list.add(this.instanceObject(t, map));
			}

		} finally {
			this.simpleClose();
		}
		return list;
	}

	/**
	 * 获取查询的结果集以List<?>形式返回.
	 *
	 * @param clazz 要映射的实体类的Class类
	 * @param sql the sql
	 * @param params the params
	 * @return List<?> 查询结果集
	 * @throws SQLException the SQL exception
	 */
	public List<?> queryList(Class<?> clazz, String sql, Map<String, Object> params) throws SQLException {
		// new 一个List
		List<Object> list = new ArrayList<Object>();
		// 查询数据库中的数据然后封装成
		try {

			createSessionQuery(sql, params);

			Integer rs_col = rs.getMetaData().getColumnCount();
			String[] paramName = new String[rs_col];
			for (int i = 0; i < rs_col; i++) {
				// 是选择的字段另取得名称
				paramName[i] = rs.getMetaData().getColumnLabel(i + 1);
			}
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < paramName.length; i++) {
					map.put(paramName[i].toUpperCase().replace("_", ""), rs.getObject(paramName[i]));
				}
				Object objentity = this.instanceObject(clazz, map);
				list.add(objentity);
			}

		} finally {
			this.simpleClose();
		}
		return list;
	}

	/**
	 * 获取查询的结果集以List<?>形式返回.
	 *
	 * @param sql the sql
	 * @param translate 通过自定义的赋值方式
	 * @param params the params
	 * @return List<?> 查询结果集
	 * @throws SQLException the SQL exception
	 */
	public List<?> queryList(String sql, ResultTranslate translate, Object... params) throws SQLException {
		// new 一个List
		List<Object> list = new ArrayList<Object>();
		// 查询数据库中的数据然后封装成
		try {

			createSessionQuery(sql, params);

			while (rs.next()) {
				list.add(translate.getObject(rs));
			}
		} finally {
			this.simpleClose();
		}
		return list;
	}

	/**
	 * 获取查询的结果集以List<?>形式返回.
	 *
	 * @param sql the sql
	 * @param translate 通过自定义的赋值方式
	 * @param params the params
	 * @return List<?> 查询结果集
	 * @throws SQLException the SQL exception
	 */
	public List<?> queryList(String sql, ResultTranslate translate, Map<String, Object> params) throws SQLException {
		// new 一个List
		List<Object> list = new ArrayList<Object>();
		// 查询数据库中的数据然后封装成
		try {

			createSessionQuery(sql, params);

			while (rs.next()) {
				list.add(translate.getObject(rs));
			}
		} finally {
			this.simpleClose();
		}
		return list;
	}

	/**
	 * 获取查询的结果集以List<Map<String,Object>>形式返回.
	 *
	 * @param sql the sql
	 * @param params the params
	 * @return List<Map<String,Object>> 查询结果集
	 * @throws SQLException the SQL exception
	 */
	public List<Map<String, Object>> queryList(String sql, Object... params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {

			createSessionQuery(sql, params);
			String[] paramName = new String[getColsNo()];
			for (int i = 0; i < getColsNo(); i++) {
				// 是选择的字段另取得名称
				paramName[i] = rs.getMetaData().getColumnLabel(i + 1);
			}
			/**
			 * String [] fieldsName = new String[rs_col]; for (int i = 0; i < rs_col; i++) { //是 字段的名称 fieldsName[i] = rs.getMetaData().getColumnName(i+1); }
			 */
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < getColsNo(); i++) {
					map.put(paramName[i], rs.getObject(paramName[i]));
				}
				list.add(map);
			}

		} finally {
			this.simpleClose();
		}
		return list;
	}

	/**
	 * 获取查询的结果集以List<Map<String,Object>>形式返回.
	 *
	 * @param sql the sql
	 * @param params the params
	 * @return List<Map<String,Object>> 查询结果集
	 * @throws SQLException the SQL exception
	 */
	public List<Map<String, Object>> queryList(String sql, Map<String, Object> params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			createSessionQuery(sql, params);
			getResultSet();
			Integer rs_col = rs.getMetaData().getColumnCount();
			String[] paramName = new String[rs_col];
			for (int i = 0; i < rs_col; i++) {
				// 是选择的字段另取得名称
				paramName[i] = rs.getMetaData().getColumnLabel(i + 1);
			}
			/**
			 * String [] fieldsName = new String[rs_col]; for (int i = 0; i < rs_col; i++) { //是 字段的名称 fieldsName[i] = rs.getMetaData().getColumnName(i+1); }
			 */
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < rs_col; i++) {
					map.put(paramName[i], rs.getObject(paramName[i]));
				}
				list.add(map);
			}

		} finally {
			this.simpleClose();
		}
		return list;
	}

	/**
	 * 通过反射技术获取单个对象的数据记录以对象的形式返回.
	 *
	 * @param clazz the clazz
	 * @param sql the sql
	 * @param params the params
	 * @return 单个对象
	 * @throws SQLException the SQL exception
	 */
	public Object querySingal(Class<?> clazz, String sql, Object... params) throws SQLException {
		Object object = null;
		try {
			createSessionQuery(sql, params);

			Integer rs_col = rs.getMetaData().getColumnCount();
			String[] paramName = new String[rs_col];
			for (int i = 0; i < rs_col; i++) {
				// 是选择的字段另取得名称
				paramName[i] = rs.getMetaData().getColumnLabel(i + 1);
			}
			if (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < rs_col; i++) {
					map.put(paramName[i].toUpperCase().replace("_", ""), rs.getObject(paramName[i]));
				}
				return this.instanceObject(clazz, map);
			}
		} finally {
			this.simpleClose();
		}
		return object;
	}

	/**
	 * 通过反射技术获取单个对象的数据记录以对象的形式返回.
	 *
	 * @param clazz the clazz
	 * @param sql the sql
	 * @param params the params
	 * @return 单个对象
	 * @throws SQLException the SQL exception
	 */
	public Object querySingal(Class<?> clazz, String sql, Map<String, Object> params) throws SQLException {
		Object object = null;
		try {

			createSessionQuery(sql, params);

			Integer rs_col = rs.getMetaData().getColumnCount();
			String[] paramName = new String[rs_col];
			for (int i = 0; i < rs_col; i++) {
				// 是选择的字段另取得名称
				paramName[i] = rs.getMetaData().getColumnLabel(i + 1);
			}
			if (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < rs_col; i++) {
					map.put(paramName[i].toUpperCase().replace("_", ""), rs.getObject(paramName[i]));
				}
				return this.instanceObject(clazz, map);
			}
		} finally {
			this.simpleClose();
		}
		return object;
	}

	/**
	 * 获取单个对象的数据记录以对象的形式返回.
	 *
	 * @param sql the sql
	 * @param translate 自定义赋值
	 * @param params the params
	 * @return 单个对象
	 * @throws SQLException the SQL exception
	 */
	public Object querySingal(String sql, ResultTranslate translate, Object... params) throws SQLException {
		Object object = null;
		try {

			createSessionQuery(sql, params);

			if (rs.next()) {
				return translate.getObject(rs);
			}
		} finally {
			this.simpleClose();
		}
		return object;
	}

	/**
	 * 获取单个对象的数据记录以对象的形式返回.
	 *
	 * @param sql the sql
	 * @param translate 自定义赋值
	 * @param params the params
	 * @return 单个对象
	 * @throws SQLException the SQL exception
	 */
	public Object querySingal(String sql, ResultTranslate translate, Map<String, Object> params) throws SQLException {
		Object object = null;
		try {

			createSessionQuery(sql, params);

			if (rs.next()) {
				return translate.getObject(rs);
			}
		} finally {
			this.simpleClose();
		}
		return object;
	}

	/**
	 * 获取单条数据记录一Map的形式返回.
	 *
	 * @param sql the sql
	 * @param params the params
	 * @return 单条数据记录
	 * @throws SQLException the SQL exception
	 */
	public Map<String, Object> querySingal(String sql, Object... params) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			createSessionQuery(sql, params);

			Integer rs_col = rs.getMetaData().getColumnCount();
			String[] paramName = new String[rs_col];
			for (int i = 0; i < rs_col; i++) {
				// 是选择的字段另取得名称
				paramName[i] = rs.getMetaData().getColumnLabel(i + 1);
			}
			if (rs.next()) {
				Object[] orm = new Object[rs_col];
				for (int i = 0; i < orm.length; i++) {
					map.put(paramName[i], rs.getObject(paramName[i]));
				}
				return map;
			}
		} finally {
			this.simpleClose();
		}
		return map;
	}

	/**
	 * 获取单条数据记录一Map的形式返回.
	 *
	 * @param sql the sql
	 * @param params the params
	 * @return 单条数据记录
	 * @throws SQLException the SQL exception
	 */
	public Map<String, Object> querySingal(String sql, Map<String, Object> params) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			createSessionQuery(sql, params);
			String[] paramName = getAlias();
			int rs_col = paramName.length;
			if (rs.next()) {
				Object[] orm = new Object[rs_col];
				for (int i = 0; i < orm.length; i++) {
					map.put(paramName[i], rs.getObject(paramName[i]));
				}
				return map;
			}
		} finally {
			if (rs != null)
				rs.close();
		}
		return map;
	}

	/* (non-Javadoc)
	 * @see com.wch.jdbc.Session#createSessionQuery(java.lang.String, java.lang.Object[])
	 */
	public Session createSessionQuery(String sql, Object... params) throws SQLException {
		ps = getStatement(sql, params);
		return this;
	}

	
	/**
	 * @param sql
	 * @param params
	 * @throws SQLException
	 */
	private PreparedStatement getStatement(String sql, Object... params) throws SQLException {
		PreparedStatement preparedStatement = null;
		if (getConnection().isClosed()) {
			throw new SQLException("connection is closed");
		}
		if (params.length == 0) {
			preparedStatement = getConnection().prepareStatement(sql);
		} else if (params[0] instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) params[0];
			Matcher matcher = pattern.matcher(sql);
			List<Object> list = new ArrayList<Object>();
			while (matcher.find()) {
				String temp = matcher.group();
				Object value = map.get(temp.substring(1));
				sql = sql.replace(temp, "?");
				if (value == null) {
					throw new SQLException("你还没有设置  " + temp.substring(1) + " 的值!");
				}
				list.add(value);
			}
			preparedStatement = getConnection().prepareStatement(sql);
			Object[] mapObjects = list.toArray(new Object[1]);
			for (int i = 0; i < mapObjects.length; i++) {
				preparedStatement.setObject(i + 1, mapObjects[i]);
			}
		} else if (params instanceof Object[]) {
			ps = getConnection().prepareStatement(sql);
			Object[] objects = (Object[]) params;
			for (int i = 0; i < objects.length; i++) {
				ps.setObject(i + 1, objects[i]);
			}
		} else {
			throw new SQLException("不支持此类型的参数!");
		}
		return preparedStatement;
	}

	/**
	 * 创建一个查询的Query.
	 *
	 * @param sql the sql
	 * @param params the params
	 * @return the query
	 * @throws SQLException the SQL exception
	 */
	public Query createQuery(String sql, Object... params) throws SQLException {
		printSql(sql);
		return new SQLQuery(getStatement(sql, params), sql);
	}
}
