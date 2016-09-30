package com.rawind.queqiao.mysql.dbsource;

import java.sql.SQLException;

import javax.sql.DataSource;

import net.paoding.rose.jade.dataaccess.DataSourceFactory;

import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ShopDataSourceTransaction {
	private DataSourceTransactionManager transactionManager;
	private ThreadLocal<TransactionStatus> transactionStatus = new ThreadLocal<TransactionStatus>();

	public ShopDataSourceTransaction(DataSourceFactory dataSourceFactory) {
		transactionManager = new DataSourceTransactionManager(dataSourceFactory.getHolder(null, null).getDataSource());
	}

	public ShopDataSourceTransaction(DataSource dataSource) {
		transactionManager = new DataSourceTransactionManager(dataSource);
	}

	/**
	 * 创建Connection并获取一个事务
	 * 
	 * @throws SQLException
	 *             如创建连接出错，就抛出异常
	 */
	public void begin() throws SQLException {
		ConnectionHolder conHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(transactionManager.getDataSource());

		if (conHolder == null) {
			conHolder = new ConnectionHolder(transactionManager.getDataSource().getConnection());

			// 这个bindResource的步骤非常关键。ConnectionHolder会在DAO的每个方法执行时，从这里获取Connection的
			TransactionSynchronizationManager.bindResource(transactionManager.getDataSource(), conHolder);
		}
		transactionStatus.set(transactionManager.getTransaction(null));
	}

	/** 提交事务 */
	public void commit() {
		if (transactionStatus.get() != null) {
			// 这里会在spring的内部实现中调用上面给定的connection的commit方法
			transactionManager.commit(transactionStatus.get());
		} else {
			throw new RuntimeException("");
		}
	}

	/** 回滚事务 */
	public void rollback() {
		if (transactionStatus.get() != null) {
			// 这里会在spring的内部实现中调用上面给定的connection的rollback方法
			try {
				transactionManager.rollback(transactionStatus.get());
			} catch (Exception e) {

			}
		}
	}

	/** 释放连接资源，否则资源会浪费 （在finally内部一定要调用该方法） */
	public void release() {
		// 说明：以下没有重用ConnectionHolder对象并关闭了Connection，实现虽然有些粗暴，但很有必要

		ConnectionHolder conHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(transactionManager.getDataSource());
		if (conHolder != null) {
			conHolder.released();
			try {
				if (conHolder.getConnection() != null) {
					conHolder.getConnection().close(); // 释放连接，还给connection pool
				}
			} catch (SQLException e) {
			}
			conHolder.clear(); // 清除不再重用了
		}
		TransactionSynchronizationManager.unbindResource(transactionManager.getDataSource());
	}
}
