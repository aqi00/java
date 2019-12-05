package com.addition.annotation;

//定义一个继承自鸡类的公鸡类
public class Cock extends Chicken {

	public Cock() {
		// 公鸡的性别固定为雄性
		// sex = 0;
		sex = MALE;
	}

	// 该注解表示以下重写了父类的方法。
	// 如果父类找不到参数列表一致的同名方法，则编译器在编码阶段就会报错。
	@Override
	// 重写了公鸡的叫唤方法
	public void call() {
		System.out.println("喔喔喔");
	}

}
