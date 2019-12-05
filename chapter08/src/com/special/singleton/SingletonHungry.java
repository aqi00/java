package com.special.singleton;

//饿汉方式实现单例模式。在程序启动时就创建实例
public class SingletonHungry {
	private static int count = 0; // 创建实例的次数
	 // 声明一个当前类的实例。在程序启动之后，会自动给类的静态属性赋初始值
	private static final SingletonHungry instance = new SingletonHungry();

	// 构造方法。每调用一次构造方法，实例的创建次数就加一
	private SingletonHungry() {
		count++;
	}

	// 获取当前类的实例
	public static SingletonHungry getInstance() {
		return instance;
	}

	// 获取实例的创建次数
	public int getCount() {
		return count;
	}
}
