package com.special.singleton;

//懒汉方式实现单例模式。在调用getInstance方法时才创建实例
public class SingletonLazy {
	private static int count = 0; // 创建实例的次数
	private static SingletonLazy instance; // 声明一个当前类的实例

	// 构造方法。每调用一次构造方法，实例的创建次数就加一
	private SingletonLazy() {
		count++;
	}

	// 获取当前类的实例
	public static SingletonLazy getInstance() {
		// 第一次调用getInstance方式时，instance变量是空的，需要给它创建新实例
		if (instance == null) {
			instance = new SingletonLazy(); // 创建当前类的实例
		}
		return instance;
	}

	// 获取实例的创建次数
	public int getCount() {
		return count;
	}
}
