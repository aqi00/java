package com.special.singleton;

//嵌套方式实现单例模式。在SingletonHolder类首次加载时创建实例
public class SingletonNest {
	private static int count = 0; // 创建实例的次数

	// 定义一个嵌套类，并在嵌套类的内部声明当前类的实例
	private static class SingletonHolder {
		private static SingletonNest instance = new SingletonNest(); // 创建一个外层类的实例
	}

	// 构造方法。每调用一次构造方法，实例的创建次数就加一
	private SingletonNest() {
		count++;
	}

	// 获取当前类的实例
	public static SingletonNest getInstance() {
		return SingletonHolder.instance;
	}

	// 获取实例的创建次数
	public int getCount() {
		return count;
	}
}
