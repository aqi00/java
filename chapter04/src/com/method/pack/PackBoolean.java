package com.method.pack;

//演示布尔包装类型的用法
public class PackBoolean {

	public static void main(String[] args) {
		// 初始化包装变量的第一种方式：直接用等号赋值
		Boolean boolPack = true;
		// 初始化包装变量的第二种方式：调用包装类型的valueOf方法
		//Boolean boolPack = Boolean.valueOf(true);
		// 初始化包装变量的第三种方式：使用关键字new创建新变量
		//Boolean boolPack = new Boolean(true);
		System.out.println("boolPack="+boolPack);
		// 把包装变量转换成布尔变量，需要调用包装变量的booleanValue方法
		boolean bool = boolPack.booleanValue();
		System.out.println("bool="+bool);

		// 布尔的包装变量允许直接使用逻辑运算符“!”“&”“|”“^”
		Boolean boolZhen = true;
		Boolean boolJia = true;
		Boolean not = !boolZhen;
		System.out.println("not="+not);
		Boolean and = boolZhen & boolJia;
		System.out.println("and="+and);
		Boolean or = boolZhen | boolJia;
		System.out.println("or="+or);
		Boolean xor = boolZhen ^ boolJia;
		System.out.println("xor="+xor);
		boolean equalResult = boolPack.equals(false); // 包装变量的equals方法相当于关系运算符“==”
		System.out.println("equalResult="+equalResult);
		
		boolean a = true, b = false;
		// 布尔包装类型的logicalAnd方法相当于逻辑“与”运算符“&”
		boolean andResult = Boolean.logicalAnd(a, b);
		System.out.println("andResult="+andResult);
		// 布尔包装类型的logicalOr方法相当于逻辑“或”运算符“|”
		boolean orResult = Boolean.logicalOr(a, b);
		System.out.println("orResult="+orResult);
		// 布尔包装类型的logicalXor方法相当于逻辑“异或”运算符“^”
		boolean xorResult = Boolean.logicalXor(a, b);
		System.out.println("xorResult="+xorResult);
	}
}
