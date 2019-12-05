package com.database.pool;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//演示C3P0连接池的用法。C3P0依赖mchange-commons-java-0.2.16.jar
public class TestC3P0 {
	private static String driver_class = "com.mysql.cj.jdbc.Driver";
	private static String dbUrl = "jdbc:mysql://localhost:3306/study?serverTimezone=GMT%2B8";
	private static String dbUserName = "root";
	private static String dbPassword = "222@@@wwwWWW";
	
	public static void main(String[] args) {
		initDataSource(); // 初始化连接池
		showRecordGroupBySex(); // 显示性别分组
		dataSource.close(); // 关闭连接池
	}

	private static ComboPooledDataSource dataSource; // 声明C3P0连接池的对象
	// 初始化连接池
	private static void initDataSource() {
		dataSource = new ComboPooledDataSource(); // 创建C3P0连接池
		try {
			dataSource.setDriverClass(driver_class); // 设置连接池的数据库驱动
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		dataSource.setJdbcUrl(dbUrl); // 设置数据库的连接地址
		dataSource.setUser(dbUserName); // 设置数据库的用户名
		dataSource.setPassword(dbPassword); // 设置数据库的密码
		dataSource.setMaxPoolSize(10); // 设置连接池大小的上限
		dataSource.setMinPoolSize(1); // 设置连接池大小的下限
		dataSource.setInitialPoolSize(3); // 设置连接池的初始大小
		dataSource.setMaxStatements(5); // 设置报告的最大个数
		// 设置获取连接的等待时间，单位毫秒。当连接池中的所有连接都被占用的时候，新请求想获取连接就必须等待，等待现有连接被释放后才能获取空闲连接。默认为0表示一直等待下去
		dataSource.setCheckoutTimeout(1000);
		// 设置最大空闲时间，单位秒。如果某个连接超过该时间仍未使用，则会被自动回收。默认为0表示不判断是否超时，也就是永不回收
		dataSource.setMaxIdleTime(30*60);
	}

	// 显示性别分组
	private static void showRecordGroupBySex() {
		String sql = "select sex,count(1) count from teacher group by sex order by sex asc";
		// 1、从连接池中获取连接
		// 2、创建连接的报告
		// 3、命令报告执行指定的查询语句，并返回查询记录的结果集
		try (Connection conn = dataSource.getConnection();
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

}
