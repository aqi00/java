package com.special.inner;

//演示外部如何调用静态属性和静态方法
public class TestStatic {

	public static void main(String[] args) {
		testStaticMember(); // 演示静态成员的调用方式
		testStaticBlock(); // 演示静态代码块与构造方法的执行顺序
		testStaticProperty(); // 演示静态属性的持久性
	}

	// 演示静态成员的调用方式
	private static void testStaticMember() {
		// 使用静态属性无需创建该类的实例，只要通过“类名.静态属性名”即可访问静态属性
		System.out.println("类型TYPE_ARBOR的取值为" + TreeStatic.TYPE_ARBOR);
		System.out.println("类型TYPE_BUSH的取值为" + TreeStatic.TYPE_BUSH);
		// 使用静态方法无需创建该类的实例，只要通过“类名.静态方法名”即可访问静态方法
		String arbor_name = TreeStatic.getTypeName(TreeStatic.TYPE_ARBOR);
		System.out.println("类型TYPE_ARBOR对应的名称是" + arbor_name);
		String bush_name = TreeStatic.getTypeName(TreeStatic.TYPE_BUSH);
		System.out.println("类型TYPE_BUSH对应的名称是" + bush_name);
	}

	// 演示静态代码块与构造方法的执行顺序
	private static void testStaticBlock() {
		System.out.println("开始创建树木类的实例");
		TreeStatic tree = new TreeStatic("月桂");
		System.out.println("结束创建树木类的实例");
	}

	// 演示静态属性的持久性
	private static void testStaticProperty() {
		TreeStatic bigTree = new TreeStatic("大树"); // 创建一个大树实例
		bigTree.grow(); // 大树在生长
		TreeStatic littleTree = new TreeStatic("小树"); // 创建一个小树实例
		littleTree.grow(); // 小树在生长
	}

}
