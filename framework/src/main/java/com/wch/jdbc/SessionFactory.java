package com.wch.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库链接工厂.
 * 
 * @author Gdnwxf
 * @时间2013-8-3 下午5:44:16
 */
public class SessionFactory {

	/** 配置文件. */
	private final static String JDBC_CONFIG = "jdbcConfig.properties";

	/** 驱动. */
	private final static String DRIVER = "driver";

	/** url. */
	private final static String URL = "url";

	/** 用户名. */
	private final static String USER = "user";

	/** 密码. */
	private final static String PASSWORD = "password";

	/** 打印sql. */
	private final static String SHOW_SQL = "show_sql";

	/** The factory. */
//	private static SessionFactory factory;

	/** The p. */
	private Properties p = null;

	/** The pop. */
	private Properties pop = null;
	
	/**
	 * Gets the single instance of SessionFactory.
	 * 
	 * @return single instance of SessionFactory
	 */
	public static SessionFactory getInstance() {
		return  new SessionFactory(JDBC_CONFIG);
	}
	
	/**
	 * Gets the single instance of SessionFactory.
	 * 
	 * @return single instance of SessionFactory
	 */
	public static SessionFactory getInstance(String jdbcConfigPath) {
		return new SessionFactory(jdbcConfigPath);
	}
	
	/**
	 * Instantiates a new session factory.
	 */
	private SessionFactory(String jdbcConfigPath) {
		try {
			p = new Properties();
			p.load(this.getClass().getClassLoader().getResourceAsStream(jdbcConfigPath));
			pop = new Properties();
			pop.setProperty(USER, p.getProperty(USER));
			pop.setProperty(PASSWORD, p.getProperty(PASSWORD));
			Class.forName(p.getProperty(DRIVER));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得数据库的链接.
	 * 
	 * @return the connection
	 * @throws SQLException the SQL exception
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(p.getProperty(URL), pop);
	}

	/**
	 * 返回的是不带事务的提交.
	 * 
	 * @return the session
	 * @throws SQLException the SQL exception
	 */
	public Session openSession() throws SQLException {
		return new SessionImpl(getConnection());
	}

	/**
	 * 返回的是带有事务的Session.
	 * 
	 * @return the current session
	 * @throws SQLException the SQL exception
	 */
	public static Session getCurrentSession() throws SQLException {
		Transaction transaction = new Transaction(getInstance());
		return new SessionImpl(transaction);
	}

	/**
	 * 打印sql语句的标识.
	 * 
	 * @return true, if is show sql
	 */
	public boolean isShowSql() {
		boolean show = false;
		String isShowSql = p.getProperty(SHOW_SQL);
		if (isShowSql != null || !"".equals(isShowSql)) {
			show = Boolean.parseBoolean(isShowSql);
		}
		return show;
	}

	/**
	 * Close.
	 * 
	 * @param connection the connection
	 */
	public void close(Connection connection) {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
