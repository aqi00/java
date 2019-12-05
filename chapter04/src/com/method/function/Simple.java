package com.method.function;

//说明main方法的格式定义
public class Simple {

	// 方法的定义格式为：“访问权限 可选的static 返回值的数据类型 方法名称(参数类型 参数名称)”
	// 其中多个参数之间以逗号分隔，如“参数1类型 参数1名称, 参数2类型 参数2名称”
	// 如果该方法无需返回任何数值，则返回值的数据类型填void
	public static void main(String[] args) { // String[]表示字符串数组
		if (args.length == 0) {
			System.out.println("您没有输入任何参数");
		}
		for (int i=0; i<args.length; i++) { // 依次取出并打印该Java程序在命令行执行的输入参数
			int seq = i+1;
			System.out.println("您输入的第"+seq+"个参数是："+args[i]);
		}
	}
}
