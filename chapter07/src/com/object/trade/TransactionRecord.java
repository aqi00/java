package com.object.trade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// 定义交易记录类
public class TransactionRecord {

	public final static int IN = 0; // 存入、买入
	public final static int OUT = 1; // 取出、卖出

	private int dealType; // 交易类型
	private String dealName; // 交易名称
	private long dealAmonut; // 交易数额
	private LocalDateTime dealTime; // 交易时间

	// 交易记录类的构造方法
	public TransactionRecord(int dealType, String dealName, long dealAmonut,
			LocalDateTime dealTime) {
		this.dealType = dealType;
		this.dealName = dealName;
		this.dealAmonut = dealAmonut;
		this.dealTime = dealTime;
	}

	// 输出交易详情
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String desc = String.format("%s 于 %s %s %d", this.dealName, this.dealTime.format(formatter), 
				(this.dealType==IN) ? "存入/买入" : "取出/卖出", this.dealAmonut);
		return desc;
	}

}
