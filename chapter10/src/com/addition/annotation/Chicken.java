package com.addition.annotation;

//该注解表示屏蔽“未使用”这种警告
@SuppressWarnings("unused")
//定义一个抽象的鸡类
abstract public class Chicken {

	// 定义一个名称属性
	public String name;
	// 定义一个性别属性
	public int sex;

	// 定义一个抽象的叫唤方法。注意后面没有花括号，并且以分号结尾
	abstract public void call();

	// 以下利用final修饰成员属性和成员方法
	public final int MALE = 0; // 雄性
	public final int FEMALE = 1; // 雌性

	// 定义一个能否游泳的方法
	public final boolean canSwim() {
		return false;
	}

	private void setName(String name) { // 设置名称
		this.name = name;
	}

	private String getName() { // 获取名称
		return this.name;
	}

	private void setSex(int sex) { // 设置性别
		this.sex = sex;
	}

	private int getSex() { // 获取性别
		return this.sex;
	}

}
