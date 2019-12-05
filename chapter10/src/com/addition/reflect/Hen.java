package com.addition.reflect;

//定义一个继承自鸡类的母鸡类
public class Hen extends Chicken {

	public Hen() {
		name = "母鸡";
		// 母鸡的性别固定为雌性
		// sex = 1;
		sex = FEMALE;
	}

	// 重写了母鸡的叫唤方法
	public void call() {
		System.out.println("咯咯咯");
	}

}
