package com.datetime.earlydate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//演示如何将日期时间按照指定格式输出
public class FormatDate {

	public static void main(String[] arg) throws ParseException {
		Date date = new Date();
		// 手工拼接指定格式的日期时间字符串
		String dateTimeDesc = String.format("%d-%d-%d %d:%d:%d",
				date.getYear() + 1900, date.getMonth() + 1, date.getDate(),
				date.getHours(), date.getMinutes(), date.getSeconds());
		System.out.println("dateTimeDesc=" + dateTimeDesc);
		// 获取当前的日期时间字符串
		String nowDateTime = getNowDateTime();
		System.out.println("nowDateTime=" + nowDateTime);
		// 获取当前的日期字符串
		String nowDate = getNowDate();
		System.out.println("nowDate=" + nowDate);
		// 获取当前的时间字符串
		String nowTime = getNowTime();
		System.out.println("nowTime=" + nowTime);
		// 获取当前的时间字符串（精确到毫秒）
		String nowTimeDetail = getNowTimeDetail();
		System.out.println("nowTimeDetail=" + nowTimeDetail);
		// 获取当前的日期时间字符串（纯数字，不包含其它标点符号）
		String simpleDateTime = getSimpleDateTime();
		System.out.println("simpleDateTime=" + simpleDateTime);

		String str = "2019-11-25 11:18:53";
		// 创建一个日期格式化的工具
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateFromStr = sdf.parse(str); // 从字符串中按照指定格式解析日期时间信息

		String format = "yyyy/MM/dd";
		// 获取指定格式的日期时间字符串
		String formatDateTime = getFormatDateTime(format);
		System.out.println("formatDateTime=" + formatDateTime);
		formatDateFromString(formatDateTime, format);
	}

	// 获取当前的日期时间字符串
	public static String getNowDateTime() {
		// 创建一个日期格式化的工具
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 将当前日期时间按照指定格式输出格式化后的日期时间字符串
		return sdf.format(new Date());
	}

	// 获取当前的日期字符串
	public static String getNowDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 创建一个日期格式化的工具
		return sdf.format(new Date()); // 将当前日期按照指定格式输出格式化后的日期字符串
	}

	// 获取当前的时间字符串
	public static String getNowTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); // 创建一个日期格式化的工具
		return sdf.format(new Date()); // 将当前时间按照指定格式输出格式化后的时间字符串
	}

	// 获取当前的时间字符串（精确到毫秒）
	public static String getNowTimeDetail() {
		// 创建一个日期格式化的工具
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		// 将当前时间按照指定格式输出格式化后的时间字符串（精确到毫秒）
		return sdf.format(new Date());
	}

	// 获取当前的日期时间字符串（纯数字，不包含其它标点符号）
	public static String getSimpleDateTime() {
		// 创建一个日期格式化的工具
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date()); // 将当前日期时间按照指定格式输出格式化后的日期时间字符串
	}

	// 获取指定格式的日期时间字符串
	public static String getFormatDateTime(String format) {
		String realFormat = (format.length() > 0) ? format : "yyyyMMddHHmmss";
		// 创建一个日期格式化的工具
		SimpleDateFormat sdf = new SimpleDateFormat(realFormat);
		return sdf.format(new Date()); // 将当前日期时间按照指定格式输出格式化后的日期时间字符串
	}

	// 把字符串变量转换为日期变量
	public static void formatDateFromString(String str, String format)
			throws ParseException {
		// 创建一个日期格式化的工具
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dateFromFormat = sdf.parse(str); // 从字符串中按照指定格式解析日期时间信息
		int year = dateFromFormat.getYear() + 1900;
		System.out.println("year=" + year);
		int month = dateFromFormat.getMonth() + 1;
		System.out.println("month=" + month);
		int dateInt = dateFromFormat.getDate();
		System.out.println("dateInt=" + dateInt);
	}

}
