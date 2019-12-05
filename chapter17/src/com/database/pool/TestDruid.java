package com.database.pool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

//演示Druid连接池的用法
public class TestDruid {
	private static String driver_class = "com.mysql.cj.jdbc.Driver";
	private static String dbUrl = "jdbc:mysql://localhost:3306/study?serverTimezone=GMT%2B8";
	private static String dbUserName = "root";
	private static String dbPassword = "222@@@wwwWWW";

	public static void main(String[] args) {
		initDataSource(); // 初始化连接池
		for (int i=0; i<3; i++) { // 多次操作数据库
			showRecordGroupBySex(); // 显示性别分组
			//showPoolStatus(); // 显示连接池的当前状态
		}
		dataSource.close(); // 关闭连接池
	}

	private static DruidDataSource dataSource; // 声明Druid连接池的对象
	// 初始化连接池
	private static void initDataSource() {
		dataSource = new DruidDataSource(); // 创建Druid连接池
		dataSource.setDriverClassName(driver_class); // 设置连接池的数据库驱动
		dataSource.setUrl(dbUrl); // 设置数据库的连接地址
		dataSource.setUsername(dbUserName); // 设置数据库的用户名
		dataSource.setPassword(dbPassword); // 设置数据库的密码
		dataSource.setInitialSize(1); // 设置连接池的初始大小
		dataSource.setMinIdle(1); // 设置连接池大小的下限
		dataSource.setMaxActive(20); // 设置连接池大小的上限
		dataSource.setRemoveAbandoned(true); // 设置是否抛弃已超时的连接
		// 设置超时的时间间隔，单位秒。如果某连接超过该时间仍未释放，则会被自动回收
		dataSource.setRemoveAbandonedTimeout(30);
		// 设置获取连接所允许的等待时间，单位毫秒。超过该时间将不再获取连接
		dataSource.setMaxWait(20000);
		// 设置间隔多久才检测需要关闭的空闲连接，单位毫秒
		dataSource.setTimeBetweenEvictionRunsMillis(20000);
		// 设置检测连接是否有效的SQL语句
		dataSource.setValidationQuery("SELECT 'x'");
		// 当空闲时是否需要测试有效性。建议设置为true，保证安全性
		dataSource.setTestWhileIdle(true);
		// 设置为true，表示申请连接时将调用validationQuery方法来检测连接是否有效
		dataSource.setTestOnBorrow(true);
	}

	// 显示连接池的当前状态
	private static void showPoolStatus() {
		System.out.println("getDbType="+dataSource.getDbType()); // 获取数据库的名称
		System.out.println("getActiveCount="+dataSource.getActiveCount()); // 获取活跃连接的数量
		System.out.println("getCloseCount="+dataSource.getCloseCount()); // 获取已关闭连接的数量
		System.out.println("getConnectCount="+dataSource.getConnectCount()); // 获取已连接的数量
		System.out.println("getCreateCount="+dataSource.getCreateCount()); // 获取已创建连接的数量
		System.out.println("getPoolingCount="+dataSource.getPoolingCount()); // 获取空闲连接的数量
		System.out.println("getRecycleCount="+dataSource.getRecycleCount()); // 获取已回收连接的数量
	}
	
	// 显示性别分组
	private static void showRecordGroupBySex() {
		String sql = "select sex,count(1) count from teacher group by sex order by sex asc";
		// 1、从连接池中获取连接
		// 2、创建连接的报告
		// 3、命令报告执行指定的查询语句，并返回查询记录的结果集
		try (DruidPooledConnection conn = dataSource.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) { // 循环遍历结果集里面的所有记录
				int sex = rs.getInt("sex"); // 获取指定字段的整型值
				int count = rs.getInt("count"); // 获取指定字段的整型值
				String desc = String.format("%s老师有%d位；", sex==0 ? "男" : "女", count);
				System.out.print(desc);
			}
			System.out.println("\ngetActiveCount="+dataSource.getActiveCount()); // 获取活跃连接的数量
			System.out.println("getConnectCount="+dataSource.getConnectCount()); // 获取已连上连接的数量
			System.out.println("getPoolingCount="+dataSource.getPoolingCount()); // 获取空闲连接的数量
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
