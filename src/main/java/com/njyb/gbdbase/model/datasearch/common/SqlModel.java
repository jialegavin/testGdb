package com.njyb.gbdbase.model.datasearch.common;

/**
 * 封装一些sql语句用于mybatis的解析
 * @author 洪皓
 * @date 2015-04-16
 * @version 标准版
 */
public class SqlModel {
	private String sql;
	public SqlModel() 
	{
		
	}
	public SqlModel(String sql) {
		this.sql = sql;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
}
