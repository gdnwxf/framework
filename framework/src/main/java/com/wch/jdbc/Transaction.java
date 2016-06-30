package com.wch.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 基于事务的Transaction
 * @author gdnwxf
 * @version 1.0
 * @since 2014
 * @email gdnwxf@qq.com
 * @date 2015-1-22 18:44:32
 */
public class Transaction {
	
	/** The factory. */
	private SessionFactory factory = null;

	/** The connection. */
	private Connection connection = null;
	
	/** 是否开启了事务. */
	private boolean begin = false;
	
	/** The transaction. */
	private static Transaction transaction= null;
	
	/**
	 * Instantiates a new transaction.
	 *
	 * @param connection the connection
	 * @throws SQLException the SQL exception
	 */
	public Transaction(Connection connection) throws SQLException {
		this.connection = connection;
	}
	
	/**
	 * Gets the transaction.
	 *
	 * @param conn the conn
	 * @return the transaction
	 * @throws SQLException the SQL exception
	 */
	public static Transaction getTransaction(Connection conn) throws SQLException {
		if (transaction == null) {
			return new Transaction(conn);
		}
		return transaction;
	}

	/**
	 * Instantiates a new transaction.
	 *
	 * @param factory the factory
	 * @throws SQLException the SQL exception
	 */
	public Transaction(SessionFactory factory) throws SQLException {
		this.factory = factory;
		this.connection = factory.getConnection();
	}
	
	/**
	 * 开启一个新的事务.
	 *
	 * @throws SQLException the SQL exception
	 */
	public void begin() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = factory.getConnection();
		}
		connection.setAutoCommit(false);
		begin = true;
	}

	/**
	 * 关闭事务.
	 *
	 * @throws SQLException the SQL exception
	 */
	public void close() throws SQLException {
		connection.close();
		begin = false;
	}

	/**
	 * 回滚事务.
	 *
	 * @throws SQLException the SQL exception
	 */
	public void rollback() throws SQLException {
		connection.rollback();
	}

	/**
	 * 提交事务.
	 *
	 * @throws SQLException the SQL exception
	 */
	public void commit() throws SQLException {
		connection.commit();
	}
	
	/**
	 * 返回该事务中的链接.
	 *
	 * @return the connection
	 * @throws SQLException the SQL exception
	 */
	public Connection getConnection() throws SQLException {
		return connection;
	}
	
	/**
	 * 返回该事务中的链接的工厂.
	 *
	 * @return the factory
	 */
	public SessionFactory getFactory() {
		return factory;
	}
	
	/**
	 * 判断是否开启事务.
	 *
	 * @return true, if is begin
	 */
	public boolean isBegin() {
		return begin;
	}
}
