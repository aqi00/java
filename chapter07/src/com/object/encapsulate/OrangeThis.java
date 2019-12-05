package com.object.encapsulate;

//演示如何使用this关键字来操作成员属性、成员方法和构造方法
public class OrangeThis {
	private String name; // 定义了橘子的名称
	private double weight; // 定义了橘子的重量
	private boolean isRipe; // 定义了橘子是否成熟。true表示成熟，false表示未成熟
	private String place; // 定义了橘子的产地

	// 设置橘子的产地
	public void setPlace(String place) {
		// this.place表示这个place是该类的属性变量，没加this前缀的place是该方法的输入参数
		this.place = place;
		this.name = (place.equals("淮北")) ? "枳子" : "橘子";
	}

	// 获取橘子的产地
	public String getPlace() {
		return this.place;
	}

	// 设置橘子的名称
	public void setName(String name) {
		this.name = name;
	}

	// 获取橘子的名称
	public String getName() {
		return this.name;
	}

	// 设置橘子的重量
	public void setWeight(double weight) {
		this.weight = weight;
	}

	// 获取橘子的重量
	public double getWeight() {
		return this.weight;
	}

	// 设置橘子是否成熟
	public void setRipe(boolean isRipe) {
		this.isRipe = isRipe;
	}

	// 获取橘子是否成熟
	public boolean getRipe() {
		return this.isRipe;
	}

	// 不带参数的构造方法是默认的构造方法
	// 如果当前类未定义其它构造方法，则无需显式定义不带参数的构造方法。
	// 如果当前类已定义其它构造方法，则
	public OrangeThis() {
	}

	// 只有一个输入参数的构造方法
	public OrangeThis(String place) {
		setPlace(place); // 调用该类的成员方法
		// 此时成员方法前面的this可加可不加，即使不加也不会产生歧义
		// this.setPlace(place);
	}

	// 拥有三个输入参数的构造方法
	public OrangeThis(String place, double weight, boolean isRipe) {
		// 在一个构造方法中调用另一个构造方法，不能直接写类的名称，而要使用this指代构造方法
		this(place);
		this.weight = weight;
		this.isRipe = isRipe;
	}

	// 输出各属性字段的取值
	public String toString() {
		String desc = String.format("这个%s的重量是%f克，%s成熟，产地是%s。", name, weight,
				isRipe ? "已" : "未", place);
		return desc;
	}

}
