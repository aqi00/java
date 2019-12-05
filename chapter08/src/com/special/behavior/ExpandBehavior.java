package com.special.behavior;

//定义一个增加了Java8新特性的接口
public interface ExpandBehavior {
	public void fly(); // 声明了一个抽象的飞翔方法
	public void swim(); // 声明了一个抽象的游泳方法
	public void run(); // 声明了一个抽象的奔跑方法

	// 默认方法，以前缀default标识。默认方法不支持重写，但可以被继承。
	public default String getOrigin(String place, String name, String ancestor) {
		return String.format("%s%s的祖先是%s。", place, name, ancestor);
	}

	// 接口内部的静态属性也默认为终态属性，所以final前缀可加可不加
	public static int MALE = 0; // 雄性
	public static int FEMALE = 1; // 雌性
	// public final static int MALE = 0;
	// public final static int FEMALE = 1;
	
	// 静态方法，以关键字static标识。静态方法支持重写，但不能被继承。
	public static String getNameByLeg(int leg_count) {
		if (leg_count == 2) {
			return "二足动物";
		} else if (leg_count == 4) {
			return "四足动物";
		} else if (leg_count >= 6) {
			return "多足动物";
		} else {
			return "奇异动物";
		}
	}

}
