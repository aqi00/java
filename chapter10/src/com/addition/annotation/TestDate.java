package com.addition.annotation;

import java.util.Date;

//该注解表示屏蔽“已过时”这种警告
@SuppressWarnings("deprecation")
//演示日期工具类Date的常用方法
public class TestDate {

	public static void main(String[] arg) {
		getDateInfo(); // 获取日期实例中的各时间单位数值
		setDateInfo(); // 设置日期实例中的各时间单位数值
		compareDate(); // 比较两个日期时间的先后关系
	}

	// 获取日期实例中的各时间单位数值
	private static void getDateInfo() {
		// 创建一个新的日期实例，默认保存的是系统时间
		Date date = new Date();
		// 获取日期实例中的年份
		int year = date.getYear() + 1900;
		System.out.println("year=" + year);
		// 获取日期实例中的月份
		int month = date.getMonth() + 1;
		System.out.println("month=" + month);
		// 获取日期实例中的日子
		int dateInt = date.getDate();
		System.out.println("dateInt=" + dateInt);
		// 获取日期实例中的星期几
		int dayWeek = date.getDay();
		dayWeek = (dayWeek == 0) ? 7 : dayWeek; // 将星期日对应的数值改为7
		System.out.println("dayWeek=" + dayWeek);
		// 获取日期实例中的时钟
		int hour = date.getHours();
		System.out.println("hour=" + hour);
		// 获取日期实例中的分钟
		int minute = date.getMinutes();
		System.out.println("minute=" + minute);
		// 获取日期实例中的秒钟
		int second = date.getSeconds();
		System.out.println("second=" + second);
		// 获取日期实例中的时间总数（单位毫秒）
		long time = date.getTime();
		System.out.println("time=" + time);
	}

	// 设置日期实例中的各时间单位数值
	private static void setDateInfo() {
		// 创建一个新的日期实例，默认保存的是系统时间
		Date date = new Date();
		// 设置日期实例中的年份
		date.setYear(100);
		// 获取日期实例中的年份
		int year = date.getYear() + 1900;
		System.out.println("year=" + year);
		// 设置日期实例中的月份
		date.setMonth(10);
		// 获取日期实例中的月份
		int month = date.getMonth() + 1;
		System.out.println("month=" + month);
		// 设置日期实例中的日子
		date.setDate(20);
		// 获取日期实例中的日子
		int dateInt = date.getDate();
		System.out.println("dateInt=" + dateInt);
		// 设置日期实例中的时钟
		date.setHours(12);
		// 获取日期实例中的时钟
		int hour = date.getHours();
		System.out.println("hour=" + hour);
		// 设置日期实例中的分钟
		date.setMinutes(30);
		// 获取日期实例中的分钟
		int minute = date.getMinutes();
		System.out.println("minute=" + minute);
		// 设置日期实例中的秒钟
		date.setSeconds(59);
		// 获取日期实例中的秒钟
		int second = date.getSeconds();
		System.out.println("second=" + second);
	}

	// 比较两个日期时间的先后关系
	private static void compareDate() {
		Date dateOld = new Date();
		Date dateNew = new Date();
		// 设置dateNew的时间总数（单位毫秒）。此处表示给当前时间增加一毫秒
		dateNew.setTime(dateNew.getTime() + 1);
		// 比较两个时间是否相等
		boolean equals = dateOld.equals(dateNew);
		System.out.println("equals=" + equals);
		// 比较A时间是否在B时间之前
		boolean before = dateOld.before(dateNew);
		System.out.println("before=" + before);
		// 比较A时间是否在B时间之后
		boolean after = dateOld.after(dateNew);
		System.out.println("after=" + after);
		// 比较A时间与B时间的先后关系。返回-1表示A时间较早，返回0表示两个时间相等，返回1表示B时间较早
		int compareResult = dateOld.compareTo(dateNew);
		System.out.println("compareResult=" + compareResult);
	}

}
