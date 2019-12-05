package com.datetime.calendar;

import java.util.Calendar;
import java.util.Date;

// 演示Calendar的几个实际应用，包括Calendar和Date类型互相转换、计算两个日历时间的天数、打印当前月份的月历
public class ConvertCalendar {

	public static void main(String[] arg) {
		convertCalendarToDate(); // 把Calendar类型的数据转换为Date类型
		convertDateToCalendar(); // 把Date类型的数据转换为Calendar类型
		countDays(); // 计算两个日历实例之间的间隔天数
		printMonthCalendar(); // 计算两个日历实例之间的间隔天数
	}

	// 把Calendar类型的数据转换为Date类型
	private static void convertCalendarToDate() {
		Calendar calendar = Calendar.getInstance(); // 创建一个日历实例
		Date date = calendar.getTime(); // 调用日历实例的getTime方法，获得日期信息
		System.out.println("日历转日期 date=" + date.toString() + ", calendar=" + calendar.toString());
	}

	// 把Date类型的数据转换为Calendar类型
	private static void convertDateToCalendar() {
		Calendar calendar = Calendar.getInstance(); // 创建一个日历实例
		Date date = new Date(); // 创建一个日期实例
		calendar.setTime(date); // 调用日历实例的setTime方法，设置日期信息
		System.out.println("日期转日历 date=" + date.toString() + ", calendar=" + calendar.toString());
	}

	// 计算两个日历实例间隔的天数
	private static void countDays() {
		Calendar calendarA = Calendar.getInstance(); // 创建一个日历实例
		calendarA.set(2019, 3, 15); // 设置第一个日历实例的年月日
		Calendar calendarB = Calendar.getInstance(); // 创建一个日历实例
		calendarB.set(2019, 9, 15); // 设置第二个日历实例的年月日
		long timeOfA = calendarA.getTimeInMillis(); // 获得第一个日历实例包含的时间总数（单位毫秒）
		long timeOfB = calendarB.getTimeInMillis(); // 获得第二个日历实例包含的时间总数（单位毫秒）
		// 先计算二者的差额，再把毫秒计量的差额转换为天数
		long dayCount = (timeOfB - timeOfA) / (1000 * 60 * 60 * 24);
		System.out.println("dayCount=" + dayCount);
	}

	// 打印当前月份的月历
	private static void printMonthCalendar() {
		Calendar calendar = Calendar.getInstance(); // 创建一个日历实例
		calendar.set(Calendar.DATE, 1); // 设置日期为当月1号
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 获得该日期对应的星期几
		dayOfWeek = dayOfWeek == 1 ? 7 : dayOfWeek - 1;
		int lastDay = calendar.getActualMaximum(Calendar.DATE); // 获取当月的最后一天
		// 拼接月历开头的年月
		String yearAndMonth = String.format("\n%21s%d年%d月", "",
				calendar.get(calendar.YEAR), calendar.get(calendar.MONTH) + 1);
		System.out.println(yearAndMonth);
		System.out.println(" 星期一 星期二 星期三 星期四 星期五 星期六 星期日");
		for (int i = 1; i < dayOfWeek; i++) { // 先补齐1号前面的空白
			System.out.printf("%7s", "");
		}
		for (int i = 1; i <= lastDay; i++) { // 循环打印从一号到本月最后一天的日子
			String today = String.format("%7d", i);
			System.out.print(today);
			if ((dayOfWeek + i - 1) % 7 == 0) { // 如果当天是星期日，末尾就另起一行
				System.out.println();
			}
		}
	}

}
