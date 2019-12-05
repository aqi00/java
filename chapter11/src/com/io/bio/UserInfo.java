package com.io.bio;

import java.io.Serializable;

//定义一个可序列化的用户信息类。实现Serializable接口表示当前类支持序列化
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L; // 该类的实例在序列化时的版本编码
	private String name; // 用户名
	private String phone; // 手机号码
	// 关键字transient可让它所修饰的字段无法序列化，也就是说，序列化无法保存该字段的数值
	private transient String password; // 密码

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

}
