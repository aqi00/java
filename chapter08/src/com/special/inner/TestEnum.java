package com.special.inner;

import java.time.LocalDate;
import java.time.Month;

//演示枚举类型的用法
public class TestEnum {

	public static void main(String[] args) {
		testMonth(); // 演示Month类型的调用方式
		testEnum(); // 演示简单枚举类型的调用方式
		testEnumCn(); // 演示扩展枚举类型的调用方式
	}

	// 演示Month类型的调用方式。注意，Month类型是Java自带的一种枚举类型
	private static void testMonth() {
		LocalDate date = LocalDate.now(); // 获得本地日期的实例
		Month month = date.getMonth(); // 获得该日期所在的英文月份
		System.out.println("month 序号=" + month.getValue() + ", 名称=" + month.name());
	}

	// 演示简单枚举类型的调用方式
	private static void testEnum() {
		Season spring = Season.SPRING; // 声明一个春天的季节实例
		Season summer = Season.SUMMER; // 声明一个夏天的季节实例
		Season autumn = Season.AUTUMN; // 声明一个秋天的季节实例
		Season winter = Season.WINTER; // 声明一个冬天的季节实例
		// 枚举类型提供的通用方法主要有两个，
		// 其中ordinal方法可获得该枚举的序号，toString可获得该枚举的字段名称
		System.out.println("spring 序号=" + spring.ordinal() + ", 名称=" + spring.toString());
		System.out.println("summer 序号=" + summer.ordinal() + ", 名称=" + summer.toString());
		System.out.println("autumn 序号=" + autumn.ordinal() + ", 名称=" + autumn.toString());
		System.out.println("winter 序号=" + winter.ordinal() + ", 名称=" + winter.toString());
	}

	// 演示扩展枚举类型的调用方式
	private static void testEnumCn() {
		SeasonCn spring = SeasonCn.SPRING; // 声明一个春天的季节实例
		SeasonCn summer = SeasonCn.SUMMER; // 声明一个夏天的季节实例
		SeasonCn autumn = SeasonCn.AUTUMN; // 声明一个秋天的季节实例
		SeasonCn winter = SeasonCn.WINTER; // 声明一个冬天的季节实例
		// 通过扩展而来的getName方法，可获得该枚举预先设定的中文名称
		System.out.println("spring 序号=" + spring.getValue() + ", 名称=" + spring.getName());
		System.out.println("summer 序号=" + summer.getValue() + ", 名称=" + summer.getName());
		System.out.println("autumn 序号=" + autumn.getValue() + ", 名称=" + autumn.getName());
		System.out.println("winter 序号=" + winter.getValue() + ", 名称=" + winter.getName());
	}

}
