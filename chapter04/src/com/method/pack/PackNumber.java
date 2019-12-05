package com.method.pack;

//以Integer为例，演示数值包装类型的赋值
public class PackNumber {

	public static void main(String[] args) {
		// 数值包装类型下面以Integer举例，其它包装类型的用法同Integer，包括Byte、Short、Long、Float、Double
		// 初始化包装变量的第一种方式：直接用等号赋值
		Integer oneInteger = 1;
		// 初始化包装变量的第二种方式：调用包装类型的valueOf方法
		//Integer oneInteger = Integer.valueOf(1);
		// 初始化包装变量的第三种方式：使用关键字new创建新变量
		//Integer oneInteger = new Integer(1);
		System.out.println("oneInteger="+oneInteger);
		byte oneByte = oneInteger.byteValue(); // 把包装变量转换成字节变量
		System.out.println("oneByte="+oneByte);
		short oneShort = oneInteger.shortValue(); // 把包装变量转换成短整变量
		System.out.println("oneShort="+oneShort);
		int oneInt = oneInteger.intValue(); // 把包装变量转换成整型变量
		System.out.println("oneInt="+oneInt);
		long oneLong = oneInteger.longValue(); // 把包装变量转换成长整变量
		System.out.println("oneLong="+oneLong);
		float oneFloat = oneInteger.floatValue(); // 把包装变量转换成浮点变量
		System.out.println("oneFloat="+oneFloat);
		double oneDouble = oneInteger.doubleValue(); // 把包装变量转换成双精度变量
		System.out.println("oneDouble="+oneDouble);
	}
}
