package com.object.inherit;

//演示鸟类及其各个子类的调用过程
public class TestBird {

	public static void main(String[] args) {
		setProperty(); // 通过成员方法设置属性值
		setConstruct(); // 通过构造方法设置属性值
		showDuck(); // 通过鸭子类演示开放性修饰符的用法
	}

	// 通过成员方法设置属性值
	private static void setProperty() {
		Bird pigeon = new Bird(); // 创建一个鸟类的实例
		pigeon.setName("鸽子");
		pigeon.setSexType(1);
		pigeon.setVoice("咕咕");
		System.out.println(pigeon.toString());
		Swallow swallow = new Swallow(); // Swallow类使用不带任何参数的默认构造方法
		swallow.setName("燕子");
		swallow.setSexType(0);
		swallow.setVoice("啾啾");
		System.out.println(swallow.toString());
	}

	// 通过构造方法设置属性值
	private static void setConstruct() {
		Bird cuckoo = new Bird("杜鹃", 1, "布谷"); // 调用Bird类带三个参数的构造方法
		System.out.println(cuckoo.toString());
		Eagle eagle = new Eagle("鹰", 0, "啁啁"); // Eagle类重写了带三个参数的构造方法
		System.out.println(eagle.toString());
	}

	// 通过鸭子类演示开放性修饰符的用法
	private static void showDuck() {
		// Duck类不修改父类的属性，改为自己定义一个同名属性sexName
		Duck duck = new Duck("鸭", 0);
		System.out.println(duck.toString());
		// DuckPublic类要求把父类的sexName改为public
		DuckPublic duckPublic = new DuckPublic("鸭", 1);
		System.out.println(duckPublic.toString());
		// DuckProtected类要求把父类的sexName改为protected
		DuckProtected duckProtected = new DuckProtected("鸭", 0);
		System.out.println(duckProtected.toString());
	}

}
