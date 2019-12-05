package com.datetime.localdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//演示LocalDate、LocalTime、LocalDateTime与字符串格式互相转换
public class FormatLocalDate {

	public static void main(String[] args) {
		convertStringToLocal(); // 把字符串转换为日期时间
		convertLocalToString(); // 把日期时间转换为字符串
	}

	// 把字符串转换为日期时间
	private static void convertStringToLocal() {
		String strDateSimple = "20190729";
		// 把日期字符串转换为LocalDate实例。BASIC_ISO_DATE定义的日期格式为yyyyMMdd
		LocalDate dateSimple = LocalDate.parse(strDateSimple, DateTimeFormatter.BASIC_ISO_DATE);
		System.out.println("dateSimple=" + dateSimple.toString());
		String strDateWithLine = "2019-07-29";
		// 把日期字符串转换为LocalDate实例。ISO_LOCAL_DATE定义的日期格式为yyyy-MM-dd
		LocalDate dateWithLine = LocalDate.parse(strDateWithLine, DateTimeFormatter.ISO_LOCAL_DATE);
		System.out.println("dateWithLine=" + dateWithLine.toString());
		String strTimeWithColon = "12:44:50";
		// 把时间字符串转换为LocalTime实例。ISO_LOCAL_TIME定义的时间格式为HH:mm:ss
		LocalTime timeWithColon = LocalTime.parse(strTimeWithColon, DateTimeFormatter.ISO_LOCAL_TIME);
		System.out.println("timeWithColon=" + timeWithColon.toString());
		String strDateTimeISO = "2019-11-23T14:46:30";
		// 把日期时间字符串转换为LocalDateTime实例。ISO_LOCAL_DATE_TIME定义的日期时间格式为yyyy-MM-ddTHH:mm:ss
		LocalDateTime datetimeISO = LocalDateTime.parse(strDateTimeISO, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		System.out.println("datetimeISO=" + datetimeISO.toString());

		String strDateWithSway = "2019/07/29";
		// 自己定义了一个形如“yyyy/MM/dd”的日期格式
		DateTimeFormatter dateFormatWithSway = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		// 把日期字符串按照格式“yyyy/MM/dd”转换为LocalDate实例
		LocalDate dateWithSway = LocalDate.parse(strDateWithSway, dateFormatWithSway);
		System.out.println("dateWithSway=" + dateWithSway.toString());
		String strTimeSimple = "125809";
		// 自己定义了一个形如“HHmmss”的时间格式
		DateTimeFormatter timeFormatSimple = DateTimeFormatter.ofPattern("HHmmss");
		// 把时间字符串按照格式“HHmmss”转换为LocalTime实例
		LocalTime timeSimple = LocalTime.parse(strTimeSimple, timeFormatSimple);
		System.out.println("timeSimple=" + timeSimple.toString());
		String strWithCn = "2019年07月29日12时58分09秒";
		// 自己定义了一个形如“yyyy年MM月dd日HH时mm分ss秒”的日期时间格式
		DateTimeFormatter formatCn = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒");
		// 把日期时间字符串按照格式“yyyy年MM月dd日HH时mm分ss秒”转换为LocalDateTime实例
		LocalDateTime datetimeWithCn = LocalDateTime.parse(strWithCn, formatCn);
		System.out.println("datetimeWithCn=" + datetimeWithCn.toString());
	}

	// 把日期时间转换为字符串
	private static void convertLocalToString() {
		LocalDate date = LocalDate.now(); // 获得当前日期的实例
		// 把LocalDate实例转换为日期字符串。BASIC_ISO_DATE定义的日期格式为yyyyMMdd
		String strDateSimple = date.format(DateTimeFormatter.BASIC_ISO_DATE);
		System.out.println("strDateSimple=" + strDateSimple);
		// 把LocalDate实例转换为日期字符串。ISO_LOCAL_DATE定义的日期格式为yyyy-MM-dd
		String strDateWithLine = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
		System.out.println("strDateWithLine=" + strDateWithLine);
		// 自己定义了一个形如“yyyy/MM/dd”的日期格式
		DateTimeFormatter dateFormatWithSway = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		// 把LocalDate实例按照格式“yyyy/MM/dd”转换为日期字符串
		String strDateWithSway = date.format(dateFormatWithSway);
		System.out.println("strDateWithSway=" + strDateWithSway);
		LocalTime time = LocalTime.now(); // 获得当前时间的实例
		// 把LocalTime实例转换为时间字符串。ISO_LOCAL_TIME定义的时间格式为HH:mm:ss
		String strTimeWithColon = time.format(DateTimeFormatter.ISO_LOCAL_TIME);
		System.out.println("strTimeWithColon=" + strTimeWithColon);
		// 自己定义了一个形如“HHmmss”的时间格式
		DateTimeFormatter timeFormatSimple = DateTimeFormatter.ofPattern("HHmmss");
		// 把LocalTime实例按照格式“HHmmss”转换为时间字符串
		String strTimeSimple = time.format(timeFormatSimple);
		System.out.println("strTimeSimple=" + strTimeSimple);
		LocalDateTime datetime = LocalDateTime.now(); // 获得当前日期时间的实例
		// 自己定义了一个形如“yyyy年MM月dd日HH时mm分ss秒”的日期时间格式
		DateTimeFormatter formatCn = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒");
		// 把LocalDateTime实例按照格式“yyyy年MM月dd日HH时mm分ss秒”转换为日期时间字符串
		String strWithCn = datetime.format(formatCn);
		System.out.println("strWithCn=" + strWithCn);
	}

}
