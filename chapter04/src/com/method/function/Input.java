package com.method.function;

import java.util.Date;

//演示如何定义方法的输入参数，包括普通参数、可变参数和数组参数
public class Input {

	public static void main(String[] args) {
		showTime(); // 显示当前时间
		// 下面两个setAlarm方法调用的是参数个数不变的方法
		setAlarm(1); // 设置一小时之后的闹钟
		setAlarm(1, -10); // 设置五十分钟之后的闹钟（一小时减十分钟）
		// 下面两个setAlarm方法调用的是参数个数变化的方法（即定义了可变参数的方法）
		setAlarm(); // 带可变参数的方法允许没有输入参数
		setAlarm(1, -10, 3); // 带可变参数的方法允许有多个输入参数
		int[] addedArray = {1, -10, 3};
		setAlarmByArray(addedArray); // setAlarmByArray方法的输入参数为数组类型
	}
	
	// 没有输入参数，则方法名称后面的圆括号内部留空。showTime方法的用途是显示当前时间
	private static void showTime() {
		Date date = new Date(); // 创建一个时间实例
		int hour = date.getHours(); // 获取当前时钟
		int minute = date.getMinutes(); // 获取当前分钟
		int second = date.getSeconds(); // 获取当前秒钟
		System.out.println("当前时间是"+hour+"时"+minute+"分"+second+"秒");
	}
	
	// 只有一个输入参数，参数格式为“参数类型 参数名称”
	// setAlarm方法的用途是设置指定时刻的闹钟，其中时钟为在当前时间上增加若干小时
	private static void setAlarm(int addedHour) {
		Date date = new Date(); // 创建一个时间实例
		int hour = date.getHours()+addedHour; // 给当前时钟加上若干小时
		int minute = date.getMinutes(); // 获取当前分钟
		int second = date.getSeconds(); // 获取当前秒钟
		System.out.println("设定的闹钟时间是"+hour+"时"+minute+"分"+second+"秒");
	}

	// 有两个输入参数，参数格式为“参数1类型 参数1名称, 参数2类型 参数2名称”
	// 下面的setAlarm方法与上面的setAlarm方法名称相同，但参数个数不同，该情况被称作方法重载。
	// 虽然两个方法的方法名称一样，但是编译器能够根据参数个数和参数类型来判断要调用哪个方法
	private static void setAlarm(int addedHour, int addedMinute) {
		Date date = new Date(); // 创建一个时间实例
		int hour = date.getHours()+addedHour; // 给当前时钟加上若干小时
		int minute = date.getMinutes()+addedMinute; // 给当前分钟加上若干分钟
		int second = date.getSeconds(); // 获取当前秒钟
		System.out.println("设定的闹钟时间是"+hour+"时"+minute+"分"+second+"秒");
	}
	
	// 参数类型后面添加三个点号“...”，表示这里的参数数量并不固定，可以有一个、两个，也可以有三个，也可以没有参数。
	// 故而此时的输入参数被称为可变参数，意思是参数的数量允许变化，“...”可以看作是方法参数的省略号。
	// 注意，如果已经存在同名且参数个数确定的方法，则编译器优先调用参数个数确定的方法；
	// 只有不存在参数个数确定的同名方法，编译器才会调用定义了可变参数的方法。
	private static void setAlarm(int... addedNumber) {
		Date date = new Date(); // 创建一个时间实例
		int hour = date.getHours(); // 获取当前时钟
		int minute = date.getMinutes(); // 获取当前分钟
		int second = date.getSeconds(); // 获取当前秒钟
		// 可变参数的数量也是通过“.length”获得
		if (addedNumber.length > 0) { // 至少有一个输入参数
			// 获取指定位置的可变参数，依然通过下标“[数字]”实现，就像是访问数组元素一般
			hour += addedNumber[0];
		}
		if (addedNumber.length > 1) { // 至少有两个输入参数
			minute += addedNumber[1];
		}
		if (addedNumber.length > 2) { // 至少有三个输入参数
			second += addedNumber[2];
		}
		System.out.println("可变参数设定的闹钟时间是"+hour+"时"+minute+"分"+second+"秒");
	}
	
	// 编译器认为“int...”与“int[] ”类型相同，所以不允许定义参数为“int...”和“int[] ”的同名方法
	private static void setAlarmByArray(int[] addedNumber) {
		Date date = new Date(); // 创建一个时间实例
		int hour = date.getHours(); // 获取当前时钟
		int minute = date.getMinutes(); // 获取当前分钟
		int second = date.getSeconds(); // 获取当前秒钟
		if (addedNumber.length > 0) { // 数组大小大于0
			hour += addedNumber[0];
		}
		if (addedNumber.length > 1) { // 数组大小大于1
			minute += addedNumber[1];
		}
		if (addedNumber.length > 2) { // 数组大小大于2
			second += addedNumber[2];
		}
		System.out.println("设定的闹钟时间是"+hour+"时"+minute+"分"+second+"秒");
	}
}
