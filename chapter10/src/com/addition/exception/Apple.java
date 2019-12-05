package com.addition.exception;

import java.util.Optional;

//定义一个苹果类
public class Apple {
	private String name; // 名称
	private String color; // 颜色
	private Double weight; // 重量
	private Double price; // 价格

	public Apple(String name, String color, Double weight, Double price) {
		this.name = name;
		this.color = color;
		this.weight = weight;
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return this.color;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return this.price;
	}

	// 获取该苹果的详细描述文字
	public String toString() {
		return String.format("\n(name=%s,color=%s,weight=%f,price=%f)", name,
				color, weight, price);
	}

	// 判断是否红苹果
	public boolean isRedApple() {
		// 不严谨的写法。一旦color字段为空，就会发生空指针异常
		// return this.color.toLowerCase().equals("red");
		// 常规的写法，判断color字段是否为空，再做分支处理
		// boolean isRed = (this.color==null) ? false : this.color.toLowerCase().equals("red");
		// 利用Optional处理可空对象，可空对象指的是该对象可能不存在（空指针）
		boolean isRed = Optional.ofNullable(this.color) // 构造一个可空对象
				.map(color -> color.toLowerCase()) // map指定了非空时候的取值
				.orElse("null") // orElse设置了空指针时候的取值
				.equals("red"); // 再判断是否红苹果
		return isRed;
	}

}
