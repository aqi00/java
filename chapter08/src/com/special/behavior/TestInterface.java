package com.special.behavior;

//演示如何使用接口对应的实现类
public class TestInterface {

	public static void main(String[] args) {
		testSimple(); // 演示简单接口的实现类用法
		testMultiple(); // 演示某个类同时实现了多个接口
		testExpand(); // 演示扩展接口的实现类用法
	}

	// 演示简单接口的实现类用法
	private static void testSimple() {
		Goose goose = new Goose("家鹅", 0); // 创建一个家鹅实例
		goose.fly(); // 实现了接口的fly方法
		goose.swim(); // 实现了接口的swim方法
		goose.run(); // 实现了接口的run方法
	}

	// 演示某个类同时实现了多个接口
	private static void testMultiple() {
		Frog frog = new Frog(); // 创建一个青蛙实例
		frog.swim(); // 实现了Behavior接口的swim方法
		frog.jump(); // 实现了Behavior2接口的run方法
	}

	// 演示扩展接口的实现类用法
	private static void testExpand() {
		// 实现类可以继承接口的静态属性
		ExpandGoose goose = new ExpandGoose("鹅", ExpandGoose.FEMALE);
		// goose.fly();
		// goose.swim();
		// goose.run();
		goose.show("中国", "鸿雁");
		goose.show("欧洲", "灰雁");
		// 接口中的静态方法没有被实现类所继承，因而只能通过扩展接口自身访问
		String typeName = ExpandBehavior.getNameByLeg(2);
		System.out.println("鹅是" + typeName);
	}

}
