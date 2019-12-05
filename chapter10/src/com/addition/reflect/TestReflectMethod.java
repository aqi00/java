package com.addition.reflect;

import java.lang.reflect.Method;

//演示如何利用反射来访问某个类的私有方法
public class TestReflectMethod {

	public static void main(String[] arg) {
		Cock cock = new Cock(); // 创建一个公鸡实例
		System.out.println("准备修理公鸡, 名称 = "+getReflectName(cock)+", 性别 = "+getReflectSex(cock));
		setReflectName(cock, "母鸭"); // 把公鸡实例的名称篡改为“母鸭”
		setReflectSex(cock, cock.FEMALE); // 把公鸡实例的性别篡改为“雌性”
		System.out.println("结束修理公鸡, 名称 = "+getReflectName(cock)+", 性别 = "+getReflectSex(cock));
	}

	// 通过反射来调用某个实例的私有方法（getName方法）
	private static String getReflectName(Chicken chicken) {
		String name = "";
		try {
			Class cls = Chicken.class; // 获得Chicken类的基因类型
			// 通过方法名称及参数列表获取该方法的Method对象
			Method method = cls.getDeclaredMethod("getName");
			method.setAccessible(true); // 将该方法设置为允许访问
			name = (String) method.invoke(chicken); // 调用某实例的方法并获得输出参数
		} catch (Exception e) { // 捕捉到了任何一种异常（错误除外）
			e.printStackTrace();
		}
		return name;
	}

	// 通过反射来调用某个实例的私有方法（setName方法）
	private static void setReflectName(Chicken chicken, String name) {
		try {
			Class cls = Chicken.class; // 获得Chicken类的基因类型
			// 通过方法名称及参数列表获取该方法的Method对象
			// 之所以需要参数类型列表，是因为同名方法可能会被多次重载，重载后的方法通过参数个数与参数类型加以区分
			Method method = cls.getDeclaredMethod("setName", String.class);
			method.setAccessible(true); // 将该方法设置为允许访问
			method.invoke(chicken, name); // 携带输入参数调用某实例的方法
		} catch (Exception e) { // 捕捉到了任何一种异常（错误除外）
			e.printStackTrace();
		}
	}

	// 通过反射来调用某个实例的私有方法（getSex方法）
	private static int getReflectSex(Chicken chicken) {
		int sex = -1;
		try {
			Class cls = Chicken.class; // 获得Chicken类的基因类型
			// 通过方法名称及参数列表获取该方法的Method对象
			Method method = cls.getDeclaredMethod("getSex");
			method.setAccessible(true); // 将该方法设置为允许访问
			sex = (int) method.invoke(chicken); // 调用某实例的方法并获得输出参数
		} catch (Exception e) { // 捕捉到了任何一种异常（错误除外）
			e.printStackTrace();
		}
		return sex;
	}

	// 通过反射来调用某个实例的私有方法（setSex方法）
	private static void setReflectSex(Chicken chicken, int sex) {
		try {
			Class cls = Chicken.class; // 获得Chicken类的基因类型
			// 通过方法名称及参数列表获取该方法的Method对象
			// 之所以需要参数类型列表，是因为同名方法可能会被多次重载，重载后的方法通过参数个数与参数类型加以区分
			Method method = cls.getDeclaredMethod("setSex", int.class);
			method.setAccessible(true); // 将该方法设置为允许访问
			method.invoke(chicken, sex); // 携带输入参数调用某实例的方法
		} catch (Exception e) { // 捕捉到了任何一种异常（错误除外）
			e.printStackTrace();
		}
	}

}
