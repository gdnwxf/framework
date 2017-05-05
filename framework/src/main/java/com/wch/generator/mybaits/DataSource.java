package com.wch.generator.mybaits;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSource implements javax.sql.DataSource {

	private static Logger logger = LoggerFactory.getLogger(DataSource.class);

	/** The Constant DRIVER_NAME. */
	private static final String DRIVER_NAME = "driver";

	/** The Constant URL. */
	private static final String URL = "url";

	/** The Constant JDBC_CONFIG_PROPERTIES. */
	private static final String JDBC_CONFIG_PROPERTIES = "jdbcConfig.properties";

	/** The properties. */
	private static Properties properties = null;

	private static final String MAX_CONNECTION = "maxConnection";
	
	/** show_sql */
	private static final String SHOW_SQL = "show_sql";

	/**
	 * 超时时间 ms
	 */
	private static final String TIME_OUT = "timeOut";

	private static BlockingQueue<Connection> connections;

	private static Long timeOut;
	
	private static boolean showSql = false;

	static {
		try {
			if (properties == null) {
				properties = System.getProperties();
			}
			properties.load(ClassLoader.getSystemResourceAsStream(JDBC_CONFIG_PROPERTIES));
			Class.forName(properties.getProperty(DRIVER_NAME));
			int maxConnections = 0;
			try {
				String maxProperty = properties.getProperty(MAX_CONNECTION);
				maxConnections = Integer.parseInt(maxProperty);
			} catch (Exception e) {
				logger.debug(" 最大连接设置无效 将使用默认连接最大值! ");
				maxConnections = 10;
			}

			connections = new ArrayBlockingQueue<Connection>(maxConnections);

			showSql = properties.getProperty(SHOW_SQL) == null ? null : Boolean.parseBoolean(properties.getProperty(SHOW_SQL));
			
			for (int i = 0; i < maxConnections; i++) {
				java.sql.Connection connection = DriverManager.getConnection(properties.getProperty(URL), properties);
				connections.put(connection);
			}

			if (properties.get(TIME_OUT) != null) {
				timeOut = (Long) properties.get(TIME_OUT);
			} else {
				timeOut = 10 * 1000l;
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("找不到对应的驱动" + properties.getProperty(DRIVER_NAME));
		} catch (SQLException e) {
			throw new RuntimeException("找不到对应的配置文件" + JDBC_CONFIG_PROPERTIES);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				public void run() {
					if(connections != null) {
						for (Connection connection : connections) {
							try {
								connection.close();
							} catch (SQLException e) {
								logger.debug(" shutdown connection with mistake {} ", e.getMessage());
							}
						}
					}
				}
			}));
		}
		
	}

	/**
	 * 获取连接
	 * 
	 * @return
	 */
	public Connection getConnection() {
		try {
			return connections.poll(timeOut, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			return null;
		}
	}

	/**
	 * 回收连接
	 * 
	 * @param connection
	 */
	public void close(Connection connection) {
		try {
			connections.put(connection);
		} catch (InterruptedException e) {
		}
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		 return DriverManager.getLogWriter();
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		DriverManager.setLogWriter(out);
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		DriverManager.setLoginTimeout(seconds);
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return DriverManager.getLoginTimeout();
	}

	@Override
	public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		throw new UnsupportedOperationException("获取连接的方式不对!");
//		return DriverManager.getConnection(properties.getProperty(URL), username, password);
	}
	
	public boolean showSql()
	{
		return showSql;
	}

}
