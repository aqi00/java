package com.special.builder;

//定义一个不能修改属性的用户类
public class UserCommon {
	private final String name; // 姓名
	private final String password; // 密码
	
	public UserCommon(String name, String password) {
		this.name = name;
		this.password = password;
	}

	// 获取用户的姓名
	public String getName() {
		return this.name;
	}

	// 获取用户的密码
	public String getPassword() {
		return this.password;
	}
	
}
