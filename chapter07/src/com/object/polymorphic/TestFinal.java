package com.object.polymorphic;

//演示终类、终属性、终方法的用法
public class TestFinal {

	public static void main(String[] args) {
		Chick chick = new Chick(); // 小鸡类是个终类，它不能被其它类继承
		chick.call();
		ChickenFinal chicken = new ChickenFinal(); // 创建一个鸡类的实例
		// ChickenFinal类的MALE属性是个终态属性，首次初始化后就不能再做修改
		System.out.println("MALE=" + chicken.MALE);
		// ChickenFinal类的FEMALE属性是个终态属性，首次初始化后就不能再做修改
		System.out.println("FEMALE=" + chicken.FEMALE);
		// ChickenFinal类的canSwim方法是个终态方法，子类不能重写该方法
		System.out.println("Chicken canSwim=" + chicken.canSwim());
	}

}
