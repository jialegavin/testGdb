package com.njyb.gbdbas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库库基本连接信息
 * 
 * @author jiahp
 * 
 */
public class JdbcConnectionUtil {
	// mysql连接数据库基本属性
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://192.168.1.103:3306/njyp";
	private static String uid = "honghao";
	private static String pwd = "honghao";
	// oracle连接数据库基本属性
	private static Connection con;

	private JdbcConnectionUtil() {
	}

	public static Connection getConnection() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, uid, pwd);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return con;
	}

//	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
//
//	static {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static Connection createConnection() {
//
//		Connection conn = null;
//		try {
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@192.168.1.112:1521:orcl", "iep", "iep");
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return conn;
//	}
//
//	public static Connection getConnection() {
//		try {
//			Connection conn = tl.get();
//			if (conn == null) {
//				conn = createConnection();
//				tl.set(conn);
//			}
//			return conn;
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	public static void closeConnection() {
//		try {
//			Connection conn = tl.get();
//			if (conn != null) {
//				conn.close();
//			}
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		} finally {
//			tl.remove();
//		}
//	}
	public static void main(String[] args)throws Exception {
	}
	
}
