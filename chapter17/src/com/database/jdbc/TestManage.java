package com.database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

//演示JDBC的变更类操作（含建表、改表结构、删表、增加记录、修改记录、删除记录）
public class TestManage {
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
		// 1、根据连接地址、用户名、密码来获取数据库的连接
		// 2、创建连接的执行报告
		// 3、命令报告执行SQL语句
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				Statement stmt = conn.createStatement()) {
//			dropTable(stmt); // 删除表格
			createTable(stmt); // 创建表格
//			alterTable(stmt); // 修改表结构
//			insertRecord(stmt); // 插入记录
//			updateRecord(stmt); // 更新记录
//			deleteRecord(stmt); // 删除记录
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 创建表格
	private static void createTable(Statement stmt) throws SQLException {
		String sql = "create table teacher (" // 建表语句
				+ "	gonghao INT NOT NULL," // 工号字段
				+ "	name VARCHAR(32) NOT NULL," // 姓名字段
				+ "	birthday DATE NULL," // 出生日期字段
				+ "	sex INT NOT NULL," // 性别字段
				+ "	course VARCHAR(32) NOT NULL," // 课程字段
				+ "	PRIMARY KEY (gonghao))" // 以工号为主键
				+ "comment = '教师信息表';";
		int count = stmt.executeUpdate(sql); // 执行处理语句
		System.out.println("建表语句的返回结果为"+count);
	}

	// 修改表结构
	private static void alterTable(Statement stmt) throws SQLException {
//		List<String> sqlList = Arrays.asList(
//				"alter table student modify column xuehao int comment '学号'",
//				"alter table student modify column name varchar(32) comment '姓名'",
//				"alter table student modify column birthday date comment '生日'");
		List<String> sqlList = Arrays.asList( // 以下语句给每列添加说明
				"alter table teacher modify column gonghao int comment '工号'",
				"alter table teacher modify column name varchar(32) comment '姓名'",
				"alter table teacher modify column birthday date comment '生日'",
				"alter table teacher modify column sex int comment '性别。0 男性；1 女性'",
				"alter table teacher modify column course varchar(32) comment '任教课程'");
		for (String sql : sqlList) {
			int count = stmt.executeUpdate(sql); // 执行处理语句
			System.out.println("表结构修改语句的返回结果为"+count);
		}
	}

	// 删除表格
	private static void dropTable(Statement stmt) throws SQLException {
		String sql = "drop table teacher"; // 删表语句
		int count = stmt.executeUpdate(sql); // 执行处理语句
		System.out.println("删表语句的返回结果为"+count);
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

	// 更新记录
	private static void updateRecord(Statement stmt) throws SQLException {
		String sql = "update teacher set course='英语' where sex='1'"; // 记录更新语句
		int count = stmt.executeUpdate(sql); // 执行处理语句。返回被更新的记录数量
		System.out.println("更新记录语句的返回结果为"+count);
	}

	// 删除记录
	private static void deleteRecord(Statement stmt) throws SQLException {
		String sql = "delete from teacher where sex='0'"; // 记录删除语句
		int count = stmt.executeUpdate(sql); // 执行处理语句。返回被删除的记录数量
		System.out.println("删除记录语句的返回结果为"+count);
	}

}
