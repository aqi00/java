package com.datetime.localdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

//演示LocalDate、LocalTime、LocalDateTime的基本用法
public class TestLocalDate {

	public static void main(String[] args) {
		showLocalDate(); // 演示LocalDate的各种方法
		showLocalTime(); // 演示LocalTime的各种方法
		showLocalDateTime(); // 演示LocalDateTime的各种方法
	}

	// 演示LocalDate的各种方法
	private static void showLocalDate() {
		LocalDate date = LocalDate.now(); // 获得本地日期的实例
		System.out.println("date=" + date.toString());
		int year = date.getYear(); // 获得该日期所在的年份
		System.out.println("year=" + year);
		// 获得该日期所在的月份。注意getMonthValue返回的是数字月份，getMonth返回的是英文月份
		int month = date.getMonthValue();
		System.out.println("month=" + month + ", english month=" + date.getMonth());
		int dayOfMonth = date.getDayOfMonth(); // 获得该日期所在的日子
		System.out.println("dayOfMonth=" + dayOfMonth);
		int dayOfYear = date.getDayOfYear(); // 获得该日期在一年当中的序号
		System.out.println("dayOfYear=" + dayOfYear);
		// 获得该日期是星期几。注意getDayOfWeek返回的是英文，后面的getValue才返回数字星期几
		int dayOfWeek = date.getDayOfWeek().getValue();
		System.out.println("dayOfWeek=" + dayOfWeek + ", english weekday=" + date.getDayOfWeek());
		int lengthOfYear = date.lengthOfYear(); // 获得该日期所在的年份一共有多少天
		System.out.println("lengthOfYear=" + lengthOfYear);
		int lengthOfMonth = date.lengthOfMonth(); // 获得该日期所在的月份一共有多少天
		System.out.println("lengthOfMonth=" + lengthOfMonth);
		boolean isLeapYear = date.isLeapYear(); // 判断该日期所在的年份是否为闰年
		System.out.println("isLeapYear=" + isLeapYear);
		
		LocalDate dateManual = LocalDate.of(2019, 11, 22); // 构造一个指定年月日的日期实例
		System.out.println("dateManual=" + dateManual.toString());
		dateManual = dateManual.plusYears(0); // 增加若干年份
		dateManual = dateManual.plusMonths(0); // 增加若干月份
		dateManual = dateManual.plusDays(0); // 增加若干日子
		dateManual = dateManual.plusWeeks(0); // 增加若干星期
		dateManual = dateManual.minusYears(0); // 减少若干年份
		dateManual = dateManual.minusMonths(0); // 减少若干月份
		dateManual = dateManual.minusDays(0); // 减少若干日子
		dateManual = dateManual.minusWeeks(0); // 减少若干星期
		dateManual = dateManual.withYear(2000); // 设置指定的年份
		dateManual = dateManual.withMonth(12); // 设置指定的月份
		dateManual = dateManual.withDayOfYear(1); // 设置当年的日子
		dateManual = dateManual.withDayOfMonth(1); // 设置当月的日子

		boolean equalsDate = date.equals(dateManual); // 判断两个日期是否相等
		System.out.println("equalsDate=" + equalsDate);
		boolean isBeforeDate = date.isBefore(dateManual); // 判断A日期是否在B日期之前
		System.out.println("isBeforeDate=" + isBeforeDate);
		boolean isAfterDate = date.isAfter(dateManual); // 判断A日期是否在B日期之后
		System.out.println("isAfterDate=" + isAfterDate);
		boolean isEqualDate = date.isEqual(dateManual); // 判断A日期是否与B日期相等
		System.out.println("isEqualDate=" + isEqualDate);
	}

	// 演示LocalTime的各种方法
	private static void showLocalTime() {
		LocalTime time = LocalTime.now(); // 获得本地时间的实例
		System.out.println("time=" + time.toString());
		int hour = time.getHour(); // 获得该时间所在的时钟
		System.out.println("hour=" + hour);
		int minute = time.getMinute(); // 获得该时间所在的分钟
		System.out.println("minute=" + minute);
		int second = time.getSecond(); // 获得该时间所在的秒钟
		System.out.println("second=" + second);
		// 一秒等于一千毫秒，一毫秒等于一千微秒，一微秒等于一千纳秒，算下来一秒等于十亿纳秒
		int nano = time.getNano(); // 获得该时间秒钟后面的纳秒单位
		System.out.println("nano=" + nano);

		LocalTime timeManual = LocalTime.of(14, 30, 25); // 构造一个指定时分秒的时间实例
		System.out.println("timeManual=" + timeManual.toString());
		timeManual = timeManual.plusHours(0); // 增加若干时钟
		timeManual = timeManual.plusMinutes(0); // 增加若干分钟
		timeManual = timeManual.plusSeconds(0); // 增加若干秒钟
		timeManual = timeManual.plusNanos(0); // 增加若干纳秒
		timeManual = timeManual.minusHours(0); // 减少若干时钟
		timeManual = timeManual.minusMinutes(0); // 减少若干分钟
		timeManual = timeManual.minusSeconds(0); // 减少若干秒钟
		timeManual = timeManual.minusNanos(0); // 减少若干纳秒
		timeManual = timeManual.withHour(0); // 设置指定的时钟
		timeManual = timeManual.withMinute(0); // 设置指定的分钟
		timeManual = timeManual.withSecond(0); // 设置指定的秒钟
		timeManual = timeManual.withNano(0); // 设置指定的纳秒

		boolean equalsTime = time.equals(timeManual); // 判断两个时间是否相等
		System.out.println("equalsTime=" + equalsTime);
		boolean isBeforeTime = time.isBefore(timeManual); // 判断A时间是否在B时间之前
		System.out.println("isBeforeTime=" + isBeforeTime);
		boolean isAfterTime = time.isAfter(timeManual); // 判断A时间是否在B时间之后
		System.out.println("isAfterTime=" + isAfterTime);
	}

	// 演示LocalDateTime的各种方法
	private static void showLocalDateTime() {
		// 获得本地日期时间的实例
		LocalDateTime datetime = LocalDateTime.now();
		System.out.println("datetime=" + datetime.toString());
		// LocalDateTime的方法是LocalDate与LocalTime的合集，
		// 也就是说LocalDate与LocalTime的大部分方法可以直接拿来给LocalDateTime使用，
		// 因而下面不再演示LocalDateTime的详细方法如何调用了。
		// 注意LocalDateTime不提供lengthOfYear、lengthOfMonth、isLeapYear这三个方法。
	}

}
