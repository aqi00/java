package com.database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//演示JDBC的连接操作
public class TestConnect {

	public static void main(String[] args) {
		String driver_class = "com.mysql.cj.jdbc.Driver"; // 数据库的驱动类
		// 数据库的连接地址。MySQL需要在地址后面添加时区，否则会报错：
		// SQLException: The server time zone value '*** ' is unrecognized or represents more than one time zone.
		String dbUrl = "jdbc:mysql://localhost:3306/study?serverTimezone=GMT%2B8";
		//String dbUrl = "jdbc:mysql://localhost:3306/study";
		String dbUserName = "root"; // 数据库的用户名
		String dbPassword = "222@@@wwwWWW"; // 数据库的密码
		try {
			Class.forName(driver_class); // 加载数据库的驱动（包含初始化动作）
			// 根据连接地址、用户名、密码来获取数据库的连接
			try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword)) {
				String desc = String.format("数据库%s的连接状态为“%s”，已%s自动提交。", 
						conn.getCatalog(), // 获取该连接的数据库实例名称
						conn.isClosed() ? "关闭" : "连上", // 获取数据库的连接状态
						conn.getAutoCommit() ? "开启" : "关闭" // 获取数据库的自动提交标志
				);
				System.out.println(desc);
//				conn.close(); // 关闭数据库的连接
//				conn.commit(); // 提交数据库的修改
//				conn.setAutoCommit(false); // 设置自动提交的标志，默认为true表示自动提交
//				// 要先关闭自动提交，才能通过rollback方法回滚事务。否则报错“Can't call rollback when autocommit=true”
//				conn.rollback(); // 回滚数据库的修改。
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
