package com.object.inherit;

//定义了一个继承自鸟类的老鹰类
public class Eagle extends Bird {

	// 老鹰类重写了带三个参数的构造方法，则不使用没有输入参数的构造方法
	public Eagle(String name, int sexType, String voice) {
		super(name, sexType, voice); // 利用super指代父类的构造方法名称
	}
}
