package com.special.function;

//演示动物类的定义，其中midnight方法的输入参数为Behavior类型
public class Animal {
	private String name; // 动物名称

	public Animal(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	// 定义一个半夜行动的方法。具体的动作由输入行为的act方法执行
	public void midnight(Behavior behavior) {
		behavior.act();
	}

}
