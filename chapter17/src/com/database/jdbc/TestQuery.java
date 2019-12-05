package com.database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

//演示JDBC的查询类操作
public class TestQuery {
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
		showAllRecord(); // 查询所有记录（默认排序）
		showAllRecordByBirthday(); // 查询所有记录（按照生日倒序）
		showRecordGroupBySex(); // 查询性别分组
	}
	
	// 查询所有记录（默认排序）
	private static void showAllRecord() {
		String sql = "select * from teacher"; // 查询teacher的所有记录
		// 1、根据连接地址、用户名、密码来获取数据库的连接
		// 2、创建连接的报告
		// 3、命令报告执行指定的查询语句，并返回查询记录的结果集
		// 4、循环遍历结果集里面的所有记录
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) { // 循环遍历结果集里面的所有记录
				int gonghao = rs.getInt("gonghao"); // 获取指定字段的整型值
				String name = rs.getString("name"); // 获取指定字段的字符串值
				Date birthday = rs.getDate("birthday"); // 获取指定字段的日期值
				int sex = rs.getInt("sex"); // 获取指定字段的整型值
				String course = rs.getString("course"); // 获取指定字段的字符串值
				String desc = String.format("工号为%d，姓名为%s，出生日期为%s，性别为%s，课程为%s。", 
						gonghao, name, getFormatDate(birthday), sex==0 ? "男性" : "女性", course);
				System.out.println("当前教师信息为："+desc);
			}
//			rs.absolute(1); // 游标移到绝对位置的第几条，负数表示倒数的第几条
//			rs.first(); // 游标移到第一条
//			rs.last(); // 游标移到最后一条
//			rs.previous(); // 游标移到前一条
//			rs.next(); // 游标移到后一条
//			rs.beforeFirst(); // 游标移到第一条之前
//			rs.afterLast(); // 游标移到最后一条之后
//			rs.isFirst(); // 是否为第一条
//			rs.isLast(); // 是否为最后一条
//			rs.isBeforeFirst(); // 是否在第一条之前
//			rs.isAfterLast(); // 是否在最后一条之后
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 查询所有记录（按照生日倒序）
	private static void showAllRecordByBirthday() {
		String sql = "select * from teacher order by birthday desc"; // 所有记录按照生日字段降序排列
		// 1、根据连接地址、用户名、密码来获取数据库的连接
		// 2、创建连接的报告
		// 3、命令报告执行指定的查询语句，并返回查询记录的结果集
		// 4、循环遍历结果集里面的所有记录
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) { // 循环遍历结果集里面的所有记录
				int gonghao = rs.getInt("gonghao"); // 获取指定字段的整型值
				String name = rs.getString("name"); // 获取指定字段的字符串值
				Date birthday = rs.getDate("birthday"); // 获取指定字段的日期值
				int sex = rs.getInt("sex"); // 获取指定字段的整型值
				String course = rs.getString("course"); // 获取指定字段的字符串值
				String desc = String.format("工号为%d，姓名为%s，出生日期为%s，性别为%s，课程为%s。", 
						gonghao, name, getFormatDate(birthday), sex==0 ? "男性" : "女性", course);
				System.out.println("当前记录的教师信息如下："+desc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 查询性别分组。注意要给count之类的函数结果分配别名
	private static void showRecordGroupBySex() {
		String sql = "select sex,count(sex) count from teacher group by sex order by sex asc";
		// 1、根据连接地址、用户名、密码来获取数据库的连接
		// 2、创建连接的报告
		// 3、命令报告执行指定的查询语句，并返回查询记录的结果集
		// 4、循环遍历结果集里面的所有记录
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) { // 循环遍历结果集里面的所有记录
				int sex = rs.getInt("sex"); // 获取指定字段的整型值
				int count = rs.getInt("count"); // 获取指定字段的整型值
				String desc = String.format("%s老师有%d位；", sex==0 ? "男" : "女", count);
				System.out.print(desc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 获取指定格式的日期字符串
	public static String getFormatDate(Date date) {
		// 创建一个日期格式化的工具
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 将当前日期时间按照指定格式输出格式化后的日期时间字符串
		return sdf.format(date);
	}

}
