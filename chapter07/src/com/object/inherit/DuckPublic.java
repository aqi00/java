package com.object.inherit;

//演示同名的父类属性、子类属性、输入参数三者的优先级顺序
public class DuckPublic extends Bird {

	public DuckPublic(String name, int sex) {
		super(name, sex, "嘎嘎");
	}

	public void setSexType(int sexType) {
		super.setSexType(sexType);
		// 若想对父类的属性直接赋值，则考虑把父类的属性从private改为public
		super.sexName = (sexType == 0) ? "公" : "母";
		// 父类和子类拥有同名属性，则不带前缀的属性字段默认为子类属性
		// sexName = (sexType==0) ? "公" : "母";
		// this.sexName = (sexType==0) ? "公" : "母";
	}

	private String sexName; // 性别名称

	public void setSexName(String sexName) {
		// 输入参数与类的属性同名，则不带前缀的参数字段默认为输入参数
		this.sexName = sexName;
	}

}
