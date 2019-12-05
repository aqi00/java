package com.object.encapsulate;

//演示如何定义类的构造方法
public class OrangeConstruct {

	// 定义了橘子的名称
	private String name;
	// 定义了橘子的重量
	private double weight;
	// 定义了橘子是否成熟。true表示成熟，false表示未成熟
	private boolean isRipe;
	// 定义了橘子的产地
	private String place;

	// 设置橘子的产地
	public void setPlace(String inputPlace) {
		place = inputPlace;
		name = (place.equals("淮北")) ? "枳子" : "橘子";
	}

	// 获取橘子的产地
	public String getPlace() {
		return place;
	}

	// 设置橘子的名称
	public void setName(String inputName) {
		name = inputName;
	}

	// 获取橘子的名称
	public String getName() {
		return name;
	}

	// 设置橘子的重量
	public void setWeight(double inputWeight) {
		weight = inputWeight;
	}

	// 获取橘子的重量
	public double getWeight() {
		return weight;
	}

	// 设置橘子是否成熟
	public void setRipe(boolean inputRipe) {
		isRipe = inputRipe;
	}

	// 获取橘子是否成熟
	public boolean getRipe() {
		return isRipe;
	}

	// 不带参数的构造方法是默认的构造方法
	// 如果当前类未定义其它构造方法，则无需显式定义不带参数的构造方法。
	// 如果当前类已定义其它构造方法，则若想继续使用默认的构造方法，就得显式定义不带参数的构造方法。
	public OrangeConstruct() {
	}

	// 只有一个输入参数的构造方法
	public OrangeConstruct(String inputPlace) {
		place = inputPlace;
		name = (place.equals("淮北")) ? "枳子" : "橘子";
	}

	// 拥有三个输入参数的构造方法
	public OrangeConstruct(String inputPlace, double inputWeight, boolean inputRipe) {
		place = inputPlace;
		name = (place.equals("淮北")) ? "枳子" : "橘子";
		weight = inputWeight;
		isRipe = inputRipe;
	}

	// 输出各属性字段的取值
	public String toString() {
		String desc = String.format("这个%s的重量是%f克，%s成熟，产地是%s。", name, weight,
				isRipe ? "已" : "未", place);
		return desc;
	}

}
