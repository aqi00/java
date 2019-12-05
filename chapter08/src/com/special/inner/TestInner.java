package com.special.inner;

//演示外部如何调用内部类和嵌套类
public class TestInner {

	public static void main(String[] args) {
		testTree(); // 演示外部类的调用方法
		testInner(); // 演示内部类的调用方法
		testNest(); // 演示嵌套类的调用方法
	}

	// 演示外部类的调用方法
	private static void testTree() {
		// 先创建外部类的实例，再基于该实例去创建内部类的实例
		Tree tree = new Tree("杨树"); // 创建一个树木实例
		tree.sprout(); // 调用树木实例的sprout方法
		Tree.Flower flower = tree.new Flower("杨花"); // 通过树木实例创建内部类的花朵实例
		flower.bloom(); // 调用花朵实例的bloom方法
	}

	// 演示内部类的调用方法
	private static void testInner() {
		// 先创建外部类的实例，再基于该实例去创建内部类的实例
		TreeInner inner = new TreeInner("桃树");
		// 创建一个内部类的实例，需要在new之前添加“外层类的实例名.”
		TreeInner.Flower flower = inner.new Flower("桃花");
		flower.bloom(); // 调用内部类实例的bloom方法
		// 重新创建Flower类的实例，它的构造方法指定了另一个树木名称tree_name
		TreeInner.Flower flower2 = inner.new Flower("碧桃", "桃花");
		// bloomInnerTree方法里面访问了内部类自身的tree_name字段
		flower2.bloomInnerTree();
		// bloomOuterTree方法里面访问了外部类TreeInner的tree_name字段
		flower2.bloomOuterTree();
	}

	// 演示嵌套类的调用方法
	private static void testNest() {
		// 创建一个嵌套类的实例，格式为“new 外层类的名称.嵌套类的名称(...)”
		TreeNest.Flower flower = new TreeNest.Flower("茉莉花");
		flower.bloom(); // 调用嵌套类实例的bloom方法
	}

}
