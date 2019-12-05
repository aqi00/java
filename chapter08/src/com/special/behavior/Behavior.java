package com.special.behavior;

//定义一个接口。接口主要声明一些特定的行为方法
public interface Behavior {
	// 注意，接口内部的方法默认为抽象方法，所以不必添加abstract前缀
	// abstract public void fly(); // 这里的abstract可加可不加
	public void fly(); // 声明了一个抽象的飞翔方法
	public void swim(); // 声明了一个抽象的游泳方法
	public void run(); // 声明了一个抽象的奔跑方法

	// 接口内部的属性默认都是终态属性，所以不必添加final前缀
	public String TAG = "动物世界";
	// public final String TAG = "动物世界"; // 这里的final可加可不加

	// 接口不允许定义构造方法。在Java8以前，接口内部的所有方法都必须是抽象方法

}
