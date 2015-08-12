package com.njyb.gbdbas.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * jdbc结果集封装
 * 
 * @author jiahp
 * 
 */
public class JdbcResultUtil {
	// 定义连接成员变量
	private Connection con = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet rs = null;
	public JdbcResultUtil() {
		con = JdbcConnectionUtil.getConnection();
	}

	/**
	 * 打开相关数据库连接资源
	 * 
	 * @throws Exception
	 */
	private void open() throws Exception {
	}

	/**
	 * 关闭相关连接资源 释放空间
	 * 
	 * @throws Exception
	 */
	private void close() throws Exception {
		if (rs != null)
			rs.close();
		if (con != null)
			con.close();
	}

	/**
	 * 获取查询结果集
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public ResultSet getResultSet(String sql) {

		try {
			preparedStatement = con.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	/**
	 * 根据查询语句生成对应的实体类
	 * 
	 * @param sql
	 * @return
	 */
	public Map<String, String> getMapModleBySql(String sql) {
		Map<String, String> map = null;
		try {
			open();
			map = new HashMap<String, String>();
			getJavaBeanProperties(sql, map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close();
			} catch (Exception e) {
				 
				e.printStackTrace();
			}
		}
		return map;
	}

	/**
	 * 根据多表查询的语句生成对应的实体类
	 * 
	 * @param sql
	 * @return
	 */
	public Map<String, String> getMapModleByMulitSql(String sql) {
		return null;
	}

	/**
	 * 根据表的名称生成对应的实体类
	 * 
	 * @param tablename
	 * @return
	 */
	public Map<String, String> getMapModle(String tablename) {
		return null;
	}
	/**
	 * 创建javabean属性
	 * @param sql
	 * @param map
	 * @throws SQLException
	 */
	private void getJavaBeanProperties(String sql, Map<String, String> map)
			throws SQLException {
		preparedStatement=con.prepareStatement(sql);
		rs=preparedStatement.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		int j = md.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= j; i++) {
				String key = md.getColumnClassName(i).substring(
						md.getColumnClassName(i).lastIndexOf(".") + 1);
				if (key.equals("BigDecimal"))
					key = "Integer";
				if (key.equals("Timestamp"))
					key = "Date";
				String value = md.getColumnName(i);
				map.put(key + "_" + i, value.toLowerCase());
			}
		}
	}
}
