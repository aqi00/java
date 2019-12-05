package com.special.inner;

//演示枚举类型的扩展定义
public enum SeasonCn {
	// 在定义枚举变量的同时，调用该枚举变量的构造方法
	SPRING(1, "春天"), SUMMER(2, "夏天"), AUTUMN(3, "秋天"), WINTER(4, "冬天");

	private int value; // 季节的数字序号
	private String name; // 季节的中文名称

	// 在构造方法中传入该季节的阿拉伯数字和中文名称
	private SeasonCn(int value, String name) {
		this.value = value;
		this.name = name;
	}

	// 获取季节的数字序号
	public int getValue() {
		return this.value;
	}

	// 获取季节的中文名称
	public String getName() {
		return this.name;
	}
}
