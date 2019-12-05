package com.addition.annotation;

//定义一个苹果类
public class Apple {
	@NotNull // 通过注解声明该字段不可为空
	private String name; // 名称
	@NotNull // 通过注解声明该字段不可为空
	private String color; // 颜色
	@NotNull // 通过注解声明该字段不可为空
	private Double weight; // 重量
	@NotNull // 通过注解声明该字段不可为空
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
		return this.color.toLowerCase().equals("red");
	}

}
