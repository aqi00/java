package com.special.behavior;

//定义一个名叫Bird的鸟类（作为可被其它类继承的基类）
public class Bird {
	// 定义鸟的名称
	private String name;
	// 定义鸟的性别类型。0表示雄性，1表示雌性
	private int sexType;
	// 定义鸟的性别名称
	private String sexName; // 与Duck搭配使用

	// 鸟类的构造方法
	public Bird(String name, int sexType) {
		this.name = name;
		setSexType(sexType); // 该方法内部同时修改性别类型和性别名称
	}

	// 设置鸟的名称
	public void setName(String name) {
		this.name = name;
	}

	// 获取鸟的名称
	public String getName() {
		return this.name;
	}

	// 设置鸟的性别类型，并自动调整性别名称
	public void setSexType(int sexType) {
		this.sexType = sexType;
		this.sexName = (sexType == 0) ? "雄" : "雌";
	}

	// 获取鸟的性别类型
	public int getSexType() {
		return this.sexType;
	}

	// 获取鸟的性别名称
	public String getSexName() {
		return this.sexName;
	}

}
