package com.rawind.queqiao.mysql.dbsource;

import java.util.Map;

import javax.sql.DataSource; 	

import net.paoding.rose.jade.dataaccess.DataSourceFactory;
import net.paoding.rose.jade.dataaccess.DataSourceHolder;
import net.paoding.rose.jade.statement.StatementMetaData;

public class ShopDataSourceFactory implements DataSourceFactory {
	
	private DataSource dataSource;
	
	@Override
	public DataSourceHolder getHolder(StatementMetaData metaData, Map<String, Object> runtime) {
		return new DataSourceHolder(dataSource);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
