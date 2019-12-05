package com.special.singleton;

//演示几种单例模式的用法
public class TestSingleton {

	public static void main(String[] args) {
		testNotSingleton(); // 测试未采取单例模式的普通类
		testLazySingleton(); // 测试采取了懒汉方式的单例类
		testHungrySingleton(); // 测试采取了饿汉方式的单例类
		testNestSingleton(); // 测试采取了嵌套类方式的单例类
	}
	
	// 测试未采取单例模式的普通类
	private static void testNotSingleton() {
		Plain plain;
		for (int i = 0; i < 3; i++) { // 依次创建普通类的三个实例
			plain = new Plain();
			System.out.println("i="+i+", 普通类的实例创建次数=" + plain.getCount());
		}
	}
	
	// 测试采取了懒汉方式的单例类
	private static void testLazySingleton() {
		SingletonLazy lazy;
		for (int i = 0; i < 3; i++) { // 先后获取三次的单例实例
			lazy = SingletonLazy.getInstance();
			System.out.println("i="+i+", 懒汉方式单例类的实例创建次数=" + lazy.getCount());
		}
	}

	// 测试采取了饿汉方式的单例类
	private static void testHungrySingleton() {
		SingletonHungry hungry;
		for (int i = 0; i < 3; i++) { // 先后获取三次的单例实例
			hungry = SingletonHungry.getInstance();
			System.out.println("i="+i+", 饿汉方式单例类的实例创建次数=" + hungry.getCount());
		}
	}

	// 测试采取了嵌套方式的单例类
	private static void testNestSingleton() {
		SingletonNest inner;
		for (int i = 0; i < 3; i++) { // 先后获取三次的单例实例
			inner = SingletonNest.getInstance();
			System.out.println("i="+i+", 嵌套方式单例类的实例创建次数=" + inner.getCount());
		}
	}

}
