package com.addition.annotation;

//定义一个继承自鸡类的母鸡类
public class Hen extends Chicken {

	public Hen() {
		// 母鸡的性别固定为雌性
		// sex = 1;
		sex = FEMALE;
	}

	// 该注解表示以下重写了父类的方法。
	// 如果父类找不到参数列表一致的同名方法，则编译器在编码阶段就会报错。
	@Override
	// 重写了母鸡的叫唤方法
	public void call() {
		System.out.println("咯咯咯");
	}

}
