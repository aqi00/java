package com.object.polymorphic;

//定义一个鸡类，它内部定义了终态属性和终态方法
public class ChickenFinal {

	// 定义一个名称属性
	public String name;
	// 定义一个性别属性
	public int sex;

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

}
