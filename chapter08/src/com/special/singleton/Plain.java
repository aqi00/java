package com.special.singleton;

//普通类，未实现单例模式
public class Plain {
	private static int count = 0; // 创建实例的次数

	// 构造方法。每调用一次构造方法，实例的创建次数就加一
	public Plain() {
		count++;
	}

	// 获取实例的创建次数
	public int getCount() {
		return count;
	}
}
