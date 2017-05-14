package com.wch.generator.mybaits.dbcore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wch.generator.mybaits.CamelCaseUtils;
import com.wch.generator.mybaits.UserInfo;
/**
 * 
 * @author wch
 * @date 2017年5月6日 上午10:59:29
 */
public class JdbcTemplate {
	
	private static Logger logger = LoggerFactory.getLogger(JdbcTemplate.class);

	/** The Constant pattern. :[\\w\\W]+?\\b */
	private final Pattern pattern = Pattern.compile(":[\\w\\W]+?\\b");

	private DataSource dataSource;

	private boolean isPreperedStatement = true;
	
	private static Transaction transaction = null;
	
	private volatile ThreadLocal<Connection> connHolder = new ThreadLocal<>();
	
	private volatile int status = 0;
	
	public JdbcTemplate(DataSource dataSource, Boolean isPreperedStatement) {
		this.dataSource = dataSource;
		transaction = new MyTransaction();
		if (isPreperedStatement != null) {
			this.isPreperedStatement = isPreperedStatement;
		}
	}

	public JdbcTemplate(DataSource dataSource) {
		 this(dataSource, true);
	}
	
	public static JdbcTemplate defaultTemplate() 
	{
		return new JdbcTemplate(new DataSource());
	}
	
	private List<?> selectList(String sql, Translate<?> translate, Class<?> clazz,
			boolean isSingle , Object ... params) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Object> list = null;
		try {
			connection = getConnection();
			if (isPreperedStatement) {
				PreparedStatement statement = (PreparedStatement) getStatement(connection, sql, params);
				rs = statement.executeQuery();
			} else {
				Statement statement = getStatement(connection, sql, params);
				rs = statement.executeQuery(sql);
			}

			ResultSetMetaData metaData = rs.getMetaData();
			int columns = metaData.getColumnCount();

			while (rs.next()) {
				if (list == null)
					list = new ArrayList<>();

				Map<String, Object> mapObject = new HashMap<>(columns);
				
				for (int i = 1; i <= columns; i++) {
					mapObject.put(CamelCaseUtils.toCamelCase(metaData.getColumnLabel(i)), rs.getObject(i));
				}

				if (clazz != null) {
					list.add(Translate.translate(mapObject, clazz));
				} else if (translate != null) {
					list.add(translate.translate(mapObject));
				} else {
					list.add(mapObject);
				}
				if (isSingle) {
					break;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("  查询出现异常 : " + e.getMessage());
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (connection != null && (status != 1))
					dataSource.close(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @param sql
	 * @param params
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	private Statement getStatement(Connection connection, String sourceSql, Object... params) throws SQLException {
		Statement statement = null;
		String sql = sourceSql;

		if (!isPreperedStatement) {
			statement = connection.createStatement();
		}
		
		
		if (params.length == 0) {
			
			if (isPreperedStatement) {
				statement = connection.prepareStatement(sql);
			}

			if(dataSource.showSql())
			{
				logger.debug("sql: {}", sql);
				
			}
			
		} else if (params[0] instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) params[0];
			Matcher matcher = pattern.matcher(sql);
			List<Object> list = new ArrayList<Object>();
			while (matcher.find()) {
				String temp = matcher.group();
				Object value = map.get(temp.substring(1));
				if (value == null) {
					throw new SQLException("你还没有设置  " + temp.substring(1) + " 的值!");
				}
				if (isPreperedStatement) {
					sql = sql.replace(temp, "?");
					list.add(value);
				} else {
					if (value instanceof Number) {
						sql = sql.replace(temp, value.toString());
					} else if (value instanceof CharSequence) {
						sql = sql.replace(temp, "'" + value.toString() + "'");
					} else {
						sql = sql.replace(temp, "'" + value.toString() + "'");
					}
				}
			}

			if(dataSource.showSql())
			{
				logger.debug("sql: {}", sql);
				
			}
			
			if (list != null && list.size() > 0) {
				statement = connection.prepareStatement(sql);
				Object[] mapObjects = list.toArray(new Object[list.size()]);
				if(dataSource.showSql())
				{
					logger.debug("params: {}", Arrays.toString(mapObjects));
					
				}
				for (int i = 0; i < mapObjects.length; i++) {
					((PreparedStatement) statement).setObject(i + 1, mapObjects[i]);
				}
			}
			
		} else if (params instanceof Object[]) {
			if (!isPreperedStatement) {
				throw new SQLException("不支持此类型的参数!");
			}
			statement = connection.prepareStatement(sql);
			Object[] objects = (Object[]) params;
			if(dataSource.showSql())
			{
				logger.debug("params: {}",Arrays.toString(objects));
				
			}
			for (int i = 0; i < objects.length; i++) {
				((PreparedStatement) statement).setObject(i + 1, objects[i]);
			}
		} else {
			throw new SQLException("不支持此类型的参数!");
		}
		
		
		
		return statement;
	}

	public List<Map<String, Object>> selectList(String sql, Map<String, Object> params, Translate<?> translate) {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> selectList(String sql,  Class<T> t , Object ... params) {
		return (List<T>) selectList(sql, null, t, false , params);
	}
	@SuppressWarnings("unchecked")
	public <T> T selectSignal(String sql,  Class<T> t , Object ... params) {
		List<?> selectList = selectList(sql, null, t, true , params);
		if(selectList ==null || selectList.size() ==0 )
		{
			return null;
		}
		return (T) selectList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public  List<Map<String, Object>> selectList(String sql, Object ... params) {
		return (List<Map<String, Object>>) selectList(sql,null,null,false, params);
	}
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectSignal(String sql, Object ... params) {
		List<?> selectList = selectList(sql,null,null,true, params);
		if(selectList ==null || selectList.size() ==0 )
		{
			return null;
		}
		return  (Map<String, Object>) selectList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> selectList(String sql, Translate<T> translate,Object ... params) {
		return (List<T>) selectList(sql,translate,null,false, params);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T selectSignal(String sql, Translate<T> translate, Object ... params) {
		List<?> selectList = selectList(sql,translate,null,true, params);
		if(selectList ==null || selectList.size() ==0 )
		{
			return null;
		}
		return  (T) selectList.get(0);
	}
	
	private Connection getConnection() throws SQLException{
		//此时表示开启事务
		Connection connection = connHolder.get();
		
		if(connection != null)
		{
			if(status == 1) {
				logger.debug(" 缓存中的connection信息 {} " , connection);
				return connection;
			} 
			
			if(status == 0)
			{
				logger.debug(" 事务未开启或已提交 ");
				
				connHolder.set(null);
				dataSource.close(connection);
			}
		}
		
		return dataSource.getConnection();
	}
	
	/**
	 * 自定义的事务
	 * @author qinghao
	 *
	 */
	class MyTransaction implements Transaction
	{
		
		/**
		 * 对象的锁
		 */
		private final Object mutex = new Object();
		 
		/**
		 * 开始事务
		 */
		public void begin() {
				if (status == 1)
					return;
			synchronized (mutex) {
				status = 1;
				Connection connection = dataSource.getConnection();
				logger.debug(" 获取事务信息 {}", connection);
				connHolder.set(connection);
			}
		}

		/**
		 * 开始事务
		 */
		public void start() {
			if (status == 1)
				return;
			synchronized (mutex) {
				status = 1;
				Connection connection = dataSource.getConnection();
				logger.debug(" 获取事务信息 {}", connection);
				connHolder.set(connection);
			}
		}

		/**
		 * 提交事务
		 */
		public void commit() throws IllegalStateException {
			if(status == 2 
					|| status == 3) {
				throw new IllegalStateException(" 事务的状态非法 ");
			}
			
			synchronized (mutex) {
				status = 2;
				Connection tempConn = connHolder.get();
				connHolder.set(null);
				dataSource.close(tempConn);
			}
		}

		/**
		 * 回滚事务
		 */
		public void rollback() {
			if(status == 2
					|| status == 3) {
				throw new IllegalStateException(" 事务的状态非法 ");
			}
			
			synchronized (mutex) {
				status = 3;
				Connection tempConn = connHolder.get();
				connHolder.set(null);
				
				dataSource.close(tempConn);
			}
		}
	}
	
	/**
	 * 获取事务信息
	 * @return
	 * @throws SQLException
	 */
	public Transaction getTransaction()throws SQLException {
		if(transaction == null) {
			throw new SQLException("事务实例化失败 !!!");
		}
		return transaction;
	}

	public static void main(String[] args) throws IllegalStateException, SQLException {
		JdbcTemplate jdbcTemplate =  JdbcTemplate.defaultTemplate();
		Transaction transaction = jdbcTemplate.getTransaction();
		transaction.start();
		Map<String, Object> params = new HashMap<>();
		params.put("id", 1);	
		List<UserInfo> selectList = jdbcTemplate.selectList(" select * from  user_info where id =:id " , UserInfo.class , params);
		selectList = jdbcTemplate.selectList(" select * from  user_info where id =:id " , UserInfo.class , params);
//		List<Map<String, Object>> selectList2 = jdbcTemplate.selectList(" show index from goods_purchase  " );
		System.out.println(selectList);
		
		transaction.commit();
		selectList = jdbcTemplate.selectList(" select * from  user_info where id =:id " , UserInfo.class , params);
		transaction.commit();
	}

	
	
}



