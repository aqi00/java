package com.special.behavior;

//定义一个继承自抽象鸡类的公鸡类
public class Cock extends Chicken {
	public Cock() {
		sex = 0; // 公鸡的性别固定为雄性
	}

	// 重写了公鸡的叫唤方法。如果不重写父类的抽象方法，那么该子类仍旧为抽象类
	public void call() {
		System.out.println("喔喔喔");
	}

}
