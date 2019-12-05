package com.object.trade;

import java.time.LocalDateTime;
import java.util.Arrays;

//定义一个账户类
public class Account {
	private String name; // 账户名称
	private long balance; // 账户余额
	private String unit; // 余额单位
	// 交易记录数组
	private TransactionRecord[] records = new TransactionRecord[] {};

	// 账户的构造方法
	public Account(String name) {
		this.name = name;
		this.balance = 0;
	}

	// 获取账户的名称
	public String getName() {
		return this.name;
	}
	
	// 设置账户的名称
	public void setName(String name) {
		this.name = name;
	}

	// 获取账户的余额
	public long getBalance() {
		return this.balance;
	}

	// 设置账户的余额
	public void setBalance(long balance) {
		this.balance = balance;
	}

	// 获取余额的单位
	public String getUnit() {
		return this.unit;
	}

	// 设置余额的单位
	public void setUnit(String unit) {
		this.unit = unit;
	}

	// 获取交易记录数组
	public TransactionRecord[] getRecords() {
		return this.records;
	}

	// 存入操作。返回true表示买入成功，false表示买入失败
	public boolean saveIn(long amount) {
		if (amount > 0) {
			this.balance += amount; // 余额增加
			// 数组大小扩容
			records = Arrays.copyOf(records, records.length + 1);
			// 插入一条存入操作的交易记录
			records[records.length - 1] = new TransactionRecord(
					TransactionRecord.IN, name, amount, LocalDateTime.now());
			return true;
		} else {
			return false;
		}
	}

	// 取出操作。返回true表示取出成功，false表示取出失败
	public boolean takeOut(long amount) {
		if (amount > 0) {
			this.balance -= amount; // 余额减少
			// 数组大小扩容
			records = Arrays.copyOf(records, records.length + 1);
			// 插入一条取出操作的交易记录
			records[records.length - 1] = new TransactionRecord(
					TransactionRecord.OUT, name, amount, LocalDateTime.now());
			return true;
		} else {
			return false;
		}
	}

	// 输出账户信息
	public String toString() {
		String desc = String.format("名称为%s，余额为%d%s，交易数为%d笔",
				this.name, this.balance, this.unit, this.records.length);
		if (this.records.length > 0) {
			desc += "。其中：";
			for (TransactionRecord record : records) {
				desc = String.format("%s\n%s", desc, record.toString());
			}
		}
		return desc;
	}

}
