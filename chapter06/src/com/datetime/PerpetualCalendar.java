package com.datetime;

import java.time.LocalDate;

// 实现一个简单的万年历
public class PerpetualCalendar {

	public static void main(String[] args) {
		showCalendar(2020, 2); // 根据指定年月显示当月的月历
	}

	// 根据指定年月显示当月的月历
	public static void showCalendar(int year, int month) {
		// 行分隔标记
		String line = "\n ————————————————————————————————\n";
		String slit = " | "; // 列分隔标记
		String[] weekTitles = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" };
		LocalDate date = LocalDate.of(year, month, 1); // 获取当月1日的本地日期实例
		// 拼接当前日历的年月标题（包含年份、月份、是否闰年、当月天数等信息）
		String calendar = String.format("\n%20s%d年（%s）%d月（共%d天）%s",
				"", date.getYear(), date.isLeapYear() ? "闰年" : "平年",
				date.getMonthValue(), date.lengthOfMonth(), line);
		String weekTitle = slit; // 月份的星期标题
		// 拼接星期一到星期日的周标题
		for (int i = 0; i < weekTitles.length; i++) {
			weekTitle = String.format("%s%3s%s", weekTitle, weekTitles[i], slit);
		}
		int firstWeekNum = date.getDayOfWeek().getValue(); // 当月第一天是星期几
		int[][] weekdays = new int[5][7]; // 星期的二维数组，每月横跨五个星期
		loop : for (int i = 0; i < 5; i++) { // 遍历组装各个星期
			for (int j = 0; j < 7; j++) { // 通过循环填满星期数组
				if (i==0 && j < firstWeekNum-1) { // 当前日期还是上个月份的日子
					continue; // 不处理，继续下个日期
				} else { // 当前日期位于本月份
					weekdays[i][j] = date.getDayOfMonth(); // 给星期数组填写本月的日期
					if (date.getDayOfMonth() == date.lengthOfMonth()) { // 如果是本月的最后一天
						break loop; // 跳出外层的遍历循环
					} else {
						date = date.plusDays(1); // 日期加一
					}
				}
			}
		}
		String weekDetail = ""; // 月份的星期详情
		for (int i = 0; i < 5; i++) { // 遍历星期的二维数组，把各星期以及分隔串拼接起来
			weekDetail = String.format("%s%s%s", weekDetail, line, slit);
			for (int j = 0; j < 7; j++) { // 拼接本星期的内容，每个日子占据六位空间，且靠右对齐
				if (weekdays[i][j] > 0) { // 属于本月的日期，则填写具体日期
					weekDetail = String.format("%s%6d%s", weekDetail, weekdays[i][j], slit);
				} else { // 不属本月的日期，则留空
					weekDetail = String.format("%s%6s%s", weekDetail, "", slit);
				}
			}
		}
		calendar = String.format("%s%s%s%s", calendar, weekTitle, weekDetail, line);
		System.out.println(calendar); // 打印拼接好的万年历日期
	}

}
