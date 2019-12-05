package com.database.properties;

//演示属性文件工具类的用法
public class TestProperties {

	public static void main(String[] args) {
		// 以下拼接数据库配置文件db.properties的完整路径
		String class_path = PropertiesUtil.class.getResource("/").getFile();
		String config_path = String.format("%s/%s", class_path, "db.properties");
		//System.out.println("config_path="+config_path);
		//write(config_path); // 写入属性文件
		read(config_path); // 读取属性文件
	}

	// 写入属性文件
	private static void write(String config_path) {
		PropertiesUtil prop = new PropertiesUtil(config_path); // 创建一个属性工具
		prop.writeString("jdbc.connection.username", "root"); // 写入数据库的用户名
		prop.writeString("jdbc.connection.password", "111111"); // 写入数据库的密码
		prop.commit(); // 提交属性文件的修改
	}

	// 读取属性文件
	private static void read(String config_path) {
		PropertiesUtil prop = new PropertiesUtil(config_path); // 创建一个属性工具
		String username = prop.readString("jdbc.connection.username", ""); // 读取数据库的用户名
		String password = prop.readString("jdbc.connection.password", ""); // 读取数据库的密码
		System.out.println("username="+username+", password="+password);
	}

}
