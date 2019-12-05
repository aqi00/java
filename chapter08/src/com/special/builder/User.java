package com.special.builder;

//定义一个采用建造者模式的用户类
public class User {
	private final String name; // 姓名
	private final String password; // 密码
	
	// 获取用户的姓名
	public String getName() {
		return this.name;
	}

	// 获取用户的密码
	public String getPassword() {
		return this.password;
	}
	
	// 用户类的构造方法。通过该类的建造者来传递具体参数。注意它是私有方法，外部不能直接调用。
	private User(Builder builder) {
		this.name = builder.name;
		this.password = builder.password;
	}
	
	// 定义用户类的建造者
	public static class Builder {
		private String name; // 姓名
		private String password; // 密码

		// 设置用户的姓名
		public void setName(String name) {
			this.name = name;
		}

		// 设置用户的密码
		public void setPassword(String password) {
			this.password = password;
		}
		
		// 建造方法，返回用户类的实例
		public User build() {
			return new User(this);
		}
	}

}
