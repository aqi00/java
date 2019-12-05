package com.addition.annotation;

//该注解表示以下定义的是函数式接口，有且仅有一个抽象方法声明。
//如果同时声明了多个抽象方法，则编译器在编码阶段就会报错。
@FunctionalInterface
//定义一个计算器接口，给算术类使用
public interface Calculator {

	// 声明一个名叫运算的抽象方法
	public double operate(double x, double y);
}
