package com.special.behavior;

//演示抽象类派生而来的子类用法
public class TestAbstract {

	public static void main(String[] args) {
		// 不能创建抽象类的实例，因为抽象类是个尚未完工的类
		// Chicken chicken = new Chicken();
		Cock cock = new Cock(); // 创建一个公鸡实例，公鸡类继承自抽象类Chicken
		cock.call(); // 调用公鸡实例的叫唤方法
		Hen hen = new Hen(); // 创建一个母鸡实例，母鸡类继承自抽象类Chicken
		hen.call(); // 调用母鸡实例的叫唤方法
	}

}
