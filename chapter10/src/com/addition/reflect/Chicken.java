package com.addition.reflect;

//定义一个鸡类
public class Chicken {

	// 定义一个名称属性
	protected String name;
	// 定义一个性别属性
	protected int sex;
	
	// 定义一个叫唤方法
	public void call() {
		System.out.println("半夜鸡叫");
	}

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
