package com.database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

//演示JDBC的预处理操作
public class TestPrepare {
	private static String driver_class = "com.mysql.cj.jdbc.Driver"; // 数据库的驱动类
	private static String dbUrl = "jdbc:mysql://localhost:3306/study?serverTimezone=GMT%2B8"; // 数据库的连接地址
	private static String dbUserName = "root"; // 数据库的用户名
	private static String dbPassword = "222@@@wwwWWW"; // 数据库的密码
	static {
		try {
			Class.forName(driver_class); // 加载数据库的驱动（包含初始化动作）
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		testStatement(); // 测试普通报告的处理
		testPreparedStatement(); // 测试预先报告的处理
	}
	
	// 测试普通报告的处理
	private static void testStatement() {
		// 1、根据连接地址、用户名、密码来获取数据库的连接
		// 2、创建连接的报告
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				Statement stmt = conn.createStatement()) {
			insertRecord(stmt); // 插入记录
			//deleteRecord(stmt, "化学"); // 删除记录，正常情况
			//deleteRecord(stmt, "' or 1='1"); // 删除记录，异常情况
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 测试预报告的处理
	private static void testPreparedStatement() {
		String sql = "delete from teacher where course=?"; // 使用问号给查询条件占位
		// 1、根据连接地址、用户名、密码来获取数据库的连接
		// 2、创建连接的预报告
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			//stmt.setString(1, "化学"); // 设置对应序号的参数值，正常情况
			stmt.setString(1, "'' or 1=1"); // 设置对应序号的参数值，异常情况
			int count = stmt.executeUpdate(); // 执行处理语句
			System.out.println("预先准备的SQL语句："+stmt.toString());
			System.out.println("删除记录语句的返回结果为"+count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 插入记录
	private static void insertRecord(Statement stmt) throws SQLException {
		List<String> sqlList = Arrays.asList( // 以下每个语句插入一条记录
				"insert into teacher (gonghao, name, birthday, sex, course) VALUES ('1', '张老师', '1983-03-03', 1, '语文')",
				"insert into teacher (gonghao, name, birthday, sex, course) VALUES ('2', '李老师', '1984-04-04', 0, '数学')",
				"insert into teacher (gonghao, name, birthday, sex, course) VALUES ('3', '王老师', '1985-05-05', 1, '英语')",
				"insert into teacher (gonghao, name, birthday, sex, course) VALUES ('4', '赵老师', '1986-06-06', 0, '物理')",
				"insert into teacher (gonghao, name, birthday, sex, course) VALUES ('5', '刘老师', '1987-07-07', 1, '化学')");
		for (String sql : sqlList) {
			int count = stmt.executeUpdate(sql); // 执行处理语句
			System.out.println("添加记录语句的返回结果为"+count);
		}
	}

	// 删除记录
	private static void deleteRecord(Statement stmt, String course) throws SQLException {
		String sql = String.format("delete from teacher where course='%s'", course);
		int count = stmt.executeUpdate(sql); // 执行处理语句
		System.out.println("待执行的SQL语句："+sql);
		System.out.println("删除记录语句的返回结果为"+count);
	}

}
