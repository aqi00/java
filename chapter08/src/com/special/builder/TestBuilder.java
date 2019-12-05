package com.special.builder;

//演示建造者模式的用户
public class TestBuilder {

	public static void main(String[] args) {
		testCommon(); // 测试普通方式的用户实例
		testBuilder(); // 测试建造者模式的用户实例
	}
	
	// 测试普通方式的用户实例
	private static void testCommon() {
		// 创建用户实例，在构造方法中传入各属性
		UserCommon user = new UserCommon("张三", "111111");
		System.out.println("普通方式，用户名为"+user.getName()+", 密码为"+user.getPassword());
	}

	// 测试建造者模式的用户实例
	private static void testBuilder() {
		User.Builder builder = new User.Builder(); // 创建用户类的建造者实例
		builder.setName("李四"); // 设置用户的姓名
		builder.setPassword("888888"); // 设置用户的密码
		User user = builder.build(); // 通过建造者实例获取用户的实例
		System.out.println("建造者模式，用户名为"+user.getName()+", 密码为"+user.getPassword());
	}

}
