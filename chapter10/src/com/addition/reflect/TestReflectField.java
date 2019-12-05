package com.addition.reflect;

import java.lang.reflect.Field;

//演示如何利用反射来访问某个类的私有属性
public class TestReflectField {

	public static void main(String[] arg) {
		Cock cock = new Cock(); // 创建一个公鸡实例
		System.out.println("准备修理公鸡，性别取值 = "+getReflectSex(cock));
		setReflectSex(cock, cock.FEMALE); // 把公鸡实例的性别篡改为“雌性”
		System.out.println("结束修理公鸡，性别取值 = "+getReflectSex(cock));
		Hen hen = new Hen(); // 创建一个母鸡实例
		System.out.println("准备修理母鸡，性别取值 = "+getReflectSex(hen));
		setReflectSex(hen, hen.MALE); // 把母鸡实例的性别篡改为“雄性”
		System.out.println("结束修理母鸡，性别取值 = "+getReflectSex(hen));
	}

	// 通过反射来获得某个实例的私有属性
	private static int getReflectSex(Chicken chicken) {
		int sex = -1;
//		try {
//			Class cls = Chicken.class; // 获得Chicken类的基因类型
//			
//			Field sexField = cls.getDeclaredField("sex"); // 通过字段名称获取该类的字段对象
//			if (sexField != null) {
//				sexField.setAccessible(true); // 将该字段设置为允许访问
//				try {
//					sex = sexField.getInt(chicken); // 获取某实例的字段值
//				} catch (IllegalArgumentException e) { // 捕捉到了非法参数异常
//					e.printStackTrace();
//				} catch (IllegalAccessException e) { // 捕捉到了非法入口异常
//					e.printStackTrace();
//				}
//			}
//		} catch (NoSuchFieldException e) { // 捕捉到了无此字段异常
//			e.printStackTrace();
//		} catch (SecurityException e) { // 捕捉到了安全异常
//			e.printStackTrace();
//		}
		// 上面的异常捕获代码太杂了，不如统一捕获这些异常的基类Exception
		try {
			Class cls = Chicken.class; // 获得Chicken类的基因类型
			Field sexField = cls.getDeclaredField("sex"); // 通过字段名称获取该类的字段对象
			if (sexField != null) {
				sexField.setAccessible(true); // 将该字段设置为允许访问
				sex = sexField.getInt(chicken); // 获取某实例的字段值
			}
		} catch (Exception e) { // 捕捉到了任何一种异常（错误除外）
			e.printStackTrace();
		}
		return sex;
	}
	
	// 通过反射来修改某个实例的私有属性
	private static void setReflectSex(Chicken chicken, int sex) {
		try {
			Class cls = Chicken.class; // 获得Chicken类的基因类型
			Field sexField = cls.getDeclaredField("sex"); // 通过字段名称获取该类的字段对象
			if (sexField != null) { 
				sexField.setAccessible(true); // 将该字段设置为允许访问
				sexField.setInt(chicken, sex); // 将某实例的该字段修改为指定数值
			}
		} catch (Exception e) { // 捕捉到了任何一种异常（错误除外）
			e.printStackTrace();
		}
	}

}
