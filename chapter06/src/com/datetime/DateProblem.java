package com.datetime;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

//演示西方历法的1582年问题
public class DateProblem {

	public static void main(String[] args) {
		calendar1582(); // 日历工具Calendar存在1582年问题
		date1582(); // 日期工具Date也存在1582年问题
		localDate1582(); // 本地日期不存在1582年问题
		dateToLocal1899(); // 本地日期转成Date日期会丢失一天
		localToDate1582(); // 本地日期转成Date日期时会遇到1582年问题
	}

	// 日历工具Calendar存在1582年问题
	private static void calendar1582() {
		Calendar calendar = Calendar.getInstance(); // 获取一个日历实例
		calendar.set(1582, 9, 4); // 设置日历实例为1582年10月4日
		String originDate = String.format("%d-%d-%d", calendar.get(Calendar.YEAR), 
				calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println("原始的日历实例=" + originDate);
		calendar.add(Calendar.DAY_OF_MONTH, 1); // 给日历实例加一天
		String newDate = String.format("%d-%d-%d", calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println("加了一天之后，新的日历实例=" + newDate);
	}

	// 日期工具Date也存在1582年问题
	private static void date1582() {
		Calendar calendar = Calendar.getInstance(); // 获取一个日历实例
		calendar.set(1582, 9, 4); // 设置日历实例为1582年10月4日
		Date date = new Date(); // 创建一个日期
		date.setTime(calendar.getTimeInMillis()); // 设置日期实例的具体时间
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("原始的日期实例=" + formatter.format(date));
		// 给日期实例加一天
		date.setTime(calendar.getTimeInMillis() + 1 * 24 * 60 * 60 * 1000);
		System.out.println("加了一天之后，新的日期实例=" + formatter.format(date));
	}

	// 本地日期不存在1582年问题
	private static void localDate1582() {
		LocalDate localDate = LocalDate.of(1582, 10, 4); // 设置本地日期实例为1582年10月4日
		System.out.println("原始的本地日期实例=" + localDate);
		localDate = localDate.plusDays(1); // 给本地日期实例加1天
		System.out.println("加了一天之后，新的本地日期实例=" + localDate);
	}

	// 本地日期转成Date日期会丢失一天
	private static void dateToLocal1899() {
		System.out.println("——1899年的本地日期转成Date日期——");
		Calendar calendar = Calendar.getInstance(); // 获取一个日历实例
		calendar.set(1899, 11, 31); // 设置日历实例为1899年12月31日
		Date date = new Date(); // 创建一个日期
		date.setTime(calendar.getTimeInMillis()); // 设置日期实例的具体时间
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("原始的日期实例=" + formatter.format(date));
		// 把日期实例转换为本地日期时间的实例
		LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		LocalDate localDate = localDateTime.toLocalDate(); // 把本地日期时间转为本地日期
		System.out.println("转换之后的本地日期实例=" + localDate);
		// 把本地日期转换为当前时区对应的日期时间
		ZonedDateTime zdt = localDate.atStartOfDay(ZoneId.systemDefault());
		Date convertDate = Date.from(zdt.toInstant()); // 把时区实例转换为日期实例
		System.out.println("再次转换之后的日期实例=" + formatter.format(convertDate));
	}

	// 本地日期转成Date日期时会遇到1582年问题
	private static void localToDate1582() {
		System.out.println("——1582年的本地日期转成Date日期——");
		LocalDate localDate = LocalDate.of(1582, 10, 15); // 设置本地日期实例为1582年10月15日
		System.out.println("原始的本地日期实例=" + localDate);
		// 把本地日期转换为当前时区对应的日期时间
		ZonedDateTime zdt = localDate.atStartOfDay(ZoneId.systemDefault());
		Date date = Date.from(zdt.toInstant()); // 把时区实例转换为日期实例
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("转换之后的日期实例=" + formatter.format(date));
	}

}
