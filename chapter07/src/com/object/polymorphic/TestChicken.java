package com.object.polymorphic;

//演示类的多态性
public class TestChicken {

	public static void main(String[] args) {
		Chicken chicken = new Cock(); // 鸡类的实例变成了一只公鸡
		chicken.call(); // 此时鸡类的叫声就变为公鸡的叫声
		chicken = new Hen(); // 鸡类的实例变成了一只母鸡
		chicken.call(); // 此时鸡类的叫声就变为母鸡的叫声

		call(new Cock()); // 公鸡叫
		call(new Hen()); // 母鸡叫
	}

	// 定义一个叫唤方法，传入什么鸡，就让什么鸡叫
	private static void call(Chicken chicken) {
		chicken.call();
	}

}
