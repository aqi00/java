package com.donghan.test;

import java.util.Date; // 引入系统库中的Date工具

public class Year {

	public static void main(String[] args) {
		Date date = new Date(); // 创建一个当前日期的实例
		int year = date.getYear(); // 从当前日期中获取当前年份
		System.out.println("year="+year);
	}
}
