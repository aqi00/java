package com.special.behavior;

//演示抽象类的定义。这是一个抽象鸡类
abstract public class Chicken {
	public String name; // 鸡的名称
	public int sex; // 鸡的性别

	// 定义一个抽象的叫唤方法。注意后面没有花括号，并且以分号结尾
	abstract public void call();

	// 即使抽象类定义了构造方法，外部也无法创建它的实例
	public Chicken() {
	}

	// Java只有抽象类和抽象方法，没有抽象属性的说法
	// abstract public String cry;

}
