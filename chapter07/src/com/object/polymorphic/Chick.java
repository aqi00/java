package com.object.polymorphic;

//定义一个小鸡类。因为小鸡的性别难以辨别，所以不再定义性别属性，小鸡类也不允许被继承
final public class Chick {

	// 定义一个名称属性
	public String name;

	// 定义一个叫唤方法
	public void call() {
		System.out.println("叽叽喳喳");
	}

}
