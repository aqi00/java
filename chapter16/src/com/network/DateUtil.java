package com.network;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	// 获取当前时间的编号
	public static String getTimeId() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		return sdf.format(date);
	}

	// 获取当前的日期时间
	public static String getNowTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}

}
