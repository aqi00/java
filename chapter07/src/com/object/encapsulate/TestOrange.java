package com.object.encapsulate;

//演示几个简单类的调用过程
public class TestOrange {

	public static void main(String[] args) {
		testSimple(); // 演示OrangeSimple类的调用
		testMember(); // 演示OrangeMember类的调用
		testConstruct(); // 演示OrangeConstruct类的调用
		testThis(); // 演示OrangeThis类的调用
	}

	// 演示OrangeSimple类的调用
	private static void testSimple() {
		OrangeSimple orange = new OrangeSimple(); // 创建OrangeSimple的一个实例
		orange.name = "橘子"; // 设置名称属性
		orange.place = "淮南"; // 设置产地属性
		orange.isRipe = true; // 设置是否成熟的属性
		orange.weight = 200; // 设置重量属性
	}

	// 演示OrangeMember类的调用
	private static void testMember() {
		OrangeMember orange = new OrangeMember(); // 创建OrangeMember的一个实例
		orange.setName("橘子"); // 调用名称设置方法
		orange.setPlace("淮南"); // 调用产地设置方法
		orange.setRipe(true); // 调用是否成熟的设置方法
		orange.setWeight(200); // 调用重量设置方法
		System.out.println(orange.toString()); // 打印该实例的详细信息
	}

	// 演示OrangeConstruct类的调用
	private static void testConstruct() {
		// 创建OrangeConstruct的一个实例
		OrangeConstruct orange = new OrangeConstruct("淮北", 100, false);
		System.out.println(orange.toString()); // 打印该实例的详细信息
	}

	// 演示OrangeThis类的调用
	private static void testThis() {
		// 创建OrangeThis的一个实例
		OrangeThis orange = new OrangeThis("江南", 300, true);
		// 打印该实例的详细信息
		System.out.println(orange.toString());
	}

}
