package com.addition.reflect;

//演示如何利用反射获得某个类的类型
public class TestReflectClass {

	public static void main(String[] arg) {
		// 第一种方式：通过“类名.class”获取
		Class clsFromClass = Cock.class;
		System.out.println("clsFromClass name = " + clsFromClass.getName());

		// 第二种方式：通过“实例名.getClass()”获取
		Cock cock = new Cock();
		Class clsFromInstance = cock.getClass();
		System.out.println("clsFromInstance name = " + clsFromInstance.getName());

		// 第三种方式：通过该类的完整路径字符串获取
		try { // 开始小心翼翼地尝试，随时准备捕捉异常
			Class clsFromString = Class.forName("com.addition.reflect.Cock");
			System.out.println("clsFromString name = " + clsFromString.getName());
		} catch (ClassNotFoundException e) { // 捕捉到“类型未找到”异常
			e.printStackTrace();
		}
	}

}
