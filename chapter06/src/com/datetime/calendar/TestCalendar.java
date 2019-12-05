package com.datetime.calendar;

import java.util.Calendar;

//演示日历工具类Calendar的常见用法
public class TestCalendar {

	public static void main(String[] arg) {
		getCalendarInfo(); // 获取日历实例中的各时间单位数值
		setCalendarInfo(); // 设置日历实例中的各时间单位数值
		compareCalendar(); // 比较两个日历时间的先后关系
	}

	// 获取日历实例中的各时间单位数值
	private static void getCalendarInfo() {
		Calendar calendar = Calendar.getInstance(); // 创建一个日历实例
		int year = calendar.get(Calendar.YEAR); // 获取日历实例中的年份
		System.out.println("year=" + year);
		int month = calendar.get(Calendar.MONTH) + 1; // 获取日历实例中的月份
		System.out.println("month=" + month);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // 获取日历实例中的日子
		System.out.println("dayOfMonth=" + dayOfMonth);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 获取日历实例中的星期几
		dayOfWeek = dayOfWeek == 1 ? 7 : dayOfWeek - 1;
		System.out.println("dayOfWeek=" + dayOfWeek);
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR); // 获取日历实例中从年初开始数的日子
		System.out.println("dayOfYear=" + dayOfYear);
		int hour = calendar.get(Calendar.HOUR); // 获取日历实例中的时钟（12小时制）
		System.out.println("hour=" + hour);
		int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY); // 获取日历实例中的时钟（24小时制）
		System.out.println("hourOfDay=" + hourOfDay);
		int minute = calendar.get(Calendar.MINUTE); // 获取日历实例中的分钟
		System.out.println("minute=" + minute);
		int second = calendar.get(Calendar.SECOND); // 获取日历实例中的秒钟
		System.out.println("second=" + second);
		int milliSecond = calendar.get(Calendar.MILLISECOND); // 获取日历实例中的毫秒
		System.out.println("milliSecond=" + milliSecond);
	}

	// 设置日历实例中的各时间单位数值
	private static void setCalendarInfo() {
		Calendar calendar = Calendar.getInstance(); // 创建一个日历实例
		// 调用带三个参数的set方法同时设置日历实例的年、月、日
		calendar.set(2019, 11, 27);
		// 调用带六个参数的set方法同时设置日历实例的年、月、日、时、分、秒
		calendar.set(2019, 11, 27, 12, 30, 40);
		System.out.println("begin set dayOfMonth="
				+ calendar.get(Calendar.DAY_OF_MONTH));
		// 带两个参数的set方法允许把某个时间单位改为指定数值
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println("end set dayOfMonth=" + calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println("begin set hourOfDay=" + calendar.get(Calendar.HOUR_OF_DAY));
		// 联合使用get和set方法，可对某个时间单位增减
		int dayResult = calendar.get(Calendar.HOUR_OF_DAY) + 1; // 给当前日期加上一天
		calendar.set(Calendar.HOUR_OF_DAY, dayResult); // 设置日历实例中的日期
		System.out.println("end set hourOfDay="
				+ calendar.get(Calendar.HOUR_OF_DAY));
		System.out.println("begin set minute=" + calendar.get(Calendar.MINUTE));
		// 调用add方法，直接在当前时间的基础上增加若干数值
		calendar.add(Calendar.MINUTE, 10); // 给当前时间加上10分钟
		System.out.println("end add minute=" + calendar.get(Calendar.MINUTE));
	}

	// 比较两个日历时间的先后关系
	private static void compareCalendar() {
		Calendar calendarOld = Calendar.getInstance(); // 创建一个日历实例
		Calendar calendarNew = Calendar.getInstance(); // 创建一个日历实例
		calendarNew.add(Calendar.SECOND, 1); // 给calendarNew加上一秒
		boolean equals = calendarOld.equals(calendarNew); // 比较两个时间是否相等
		System.out.println("equals=" + equals);
		boolean before = calendarOld.before(calendarNew); // 比较A时间是否在B时间之前
		System.out.println("before=" + before);
		boolean after = calendarOld.after(calendarNew); // 比较A时间是否在B时间之后
		System.out.println("after=" + after);
		// 比较A时间与B时间的先后关系。
		// 返回-1表示A较早，返回0表示二者相等，返回1表示B较早
		int compareResult = calendarOld.compareTo(calendarNew);
		System.out.println("compareResult=" + compareResult);
	}

}
