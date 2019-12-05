package com.object.polymorphic;

//定义一个继承自鸡类的公鸡类
public class Cock extends Chicken {

	public Cock() {
		// 公鸡的性别固定为雄性
		sex = 0;
		//sex = MALE;
	}

	// 重写了公鸡的叫唤方法
	public void call() {
		super.call(); // 先调用鸡类的叫唤方法
		System.out.println("喔喔喔");
	}

}
