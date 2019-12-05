package com.datetime.earlydate;

import java.util.Date;

//演示日期工具类Date的常用方法
public class TestDate {

	public static void main(String[] arg) {
		getDateInfo(); // 获取日期实例中的各时间单位数值
		setDateInfo(); // 设置日期实例中的各时间单位数值
		compareDate(); // 比较两个日期时间的先后关系
	}

	// 获取日期实例中的各时间单位数值
	private static void getDateInfo() {
		Date date = new Date(); // 创建一个新的日期实例，默认保存的是系统时间
		int year = date.getYear() + 1900; // 获取日期实例中的年份
		System.out.println("year=" + year);
		int month = date.getMonth() + 1; // 获取日期实例中的月份
		System.out.println("month=" + month);
		int day = date.getDate(); // 获取日期实例中的日子
		System.out.println("day=" + day);
		int dayWeek = date.getDay(); // 获取日期实例中的星期几
		dayWeek = (dayWeek == 0) ? 7 : dayWeek; // 将星期日对应的数值改为7
		System.out.println("dayWeek=" + dayWeek);
		int hour = date.getHours(); // 获取日期实例中的时钟
		System.out.println("hour=" + hour);
		int minute = date.getMinutes(); // 获取日期实例中的分钟
		System.out.println("minute=" + minute);
		int second = date.getSeconds(); // 获取日期实例中的秒钟
		System.out.println("second=" + second);
		long time = date.getTime(); // 获取日期实例中的时间总数（单位毫秒）
		System.out.println("time=" + time);
	}

	// 设置日期实例中的各时间单位数值
	private static void setDateInfo() {
		Date date = new Date(); // 创建一个新的日期实例，默认保存的是系统时间
		date.setYear(100); // 设置日期实例中的年份
		int year = date.getYear() + 1900; // 获取日期实例中的年份
		System.out.println("year=" + year);
		date.setMonth(10); // 设置日期实例中的月份
		int month = date.getMonth() + 1; // 获取日期实例中的月份
		System.out.println("month=" + month);
		date.setDate(20); // 设置日期实例中的日子
		int dateInt = date.getDate(); // 获取日期实例中的日子
		System.out.println("dateInt=" + dateInt);
		date.setHours(12); // 设置日期实例中的时钟
		int hour = date.getHours(); // 获取日期实例中的时钟
		System.out.println("hour=" + hour);
		date.setMinutes(30); // 设置日期实例中的分钟
		int minute = date.getMinutes(); // 获取日期实例中的分钟
		System.out.println("minute=" + minute);
		date.setSeconds(59); // 设置日期实例中的秒钟
		int second = date.getSeconds(); // 获取日期实例中的秒钟
		System.out.println("second=" + second);
	}

	// 比较两个日期时间的先后关系
	private static void compareDate() {
		Date dateOld = new Date(); // 创建一个日期实例
		Date dateNew = new Date(); // 创建一个日期实例
		// 设置dateNew的时间总数（单位毫秒）。此处表示给当前时间增加一毫秒
		dateNew.setTime(dateNew.getTime() + 1);
		boolean equals = dateOld.equals(dateNew); // 比较两个时间是否相等
		System.out.println("equals=" + equals);
		boolean before = dateOld.before(dateNew); // 比较A时间是否在B时间之前
		System.out.println("before=" + before);
		boolean after = dateOld.after(dateNew); // 比较A时间是否在B时间之后
		System.out.println("after=" + after);
		// 比较A时间与B时间的先后关系。
		// 返回-1表示A时间较早，返回0表示两个时间相等，返回1表示B时间较早
		int compareResult = dateOld.compareTo(dateNew);
		System.out.println("compareResult=" + compareResult);
	}

}
