package com.special.behavior;

//定义一个继承自抽象鸡类的母鸡类
public class Hen extends Chicken {
	public Hen() {
		sex = 1; // 母鸡的性别固定为雌性
	}

	// 重写了母鸡的叫唤方法。如果不重写父类的抽象方法，那么该子类仍旧为抽象类
	public void call() {
		System.out.println("咯咯咯");
	}

}
