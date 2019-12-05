package com.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;

//定义了线程专用的日志打印工具
public class PrintUtils {
	// 打印线程的运行日志，包括当前时间、当前线程名称、具体事件描述等信息
	public static void print(String threadName, String event) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		String dateTime = sdf.format(new Date());
		String desc = String.format("%s %s %s", dateTime, threadName, event);
		System.out.println(desc);
	}
}
