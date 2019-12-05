package com.object.trade;

import java.util.Arrays;

//定义一个证券账户类
public class SecurityAccount {

	// 可用资金账户
	private CashAccount fund;
	// 股票账户数组
	private StockAccount[] stocks;

	// 证券账户的构造方法
	public SecurityAccount() {
		fund = new CashAccount(CashAccount.RMB, "可用资金");
		stocks = new StockAccount[] {};
	}

	// 存入可用资金
	public boolean saveFund(long amount) {
		return fund.saveIn(amount);
	}

	// 取出可用资金
	public boolean takeFund(long amount) {
		return fund.takeOut(amount);
	}

	// 买入指定股票
	public boolean buyStock(String stockId, String stockName, long amount) {
		boolean result = false;
		// 查找指定代号的股票账户
		int pos = getStockPos(stockId);
		if (pos >= 0) { // 已找到
			// 获得已有的股票账户
			StockAccount stockAccount = stocks[pos];
			// 存入已有的股票账户
			result = stockAccount.saveIn(amount);
			// 更新已有的股票账户
			stocks[pos] = stockAccount;
		} else { // 未找到
			// 创建新的股票账户
			StockAccount stockAccount = new StockAccount(stockId, stockName);
			// 存入新的股票账户
			result = stockAccount.saveIn(amount);
			// 数组大小扩容
			stocks = Arrays.copyOf(stocks, stocks.length + 1);
			// 插入新的股票账户
			stocks[stocks.length - 1] = stockAccount;
		}
		return result;
	}

	// 卖出指定股票
	public boolean sellStock(String stockId, long amount) {
		boolean result = false;
		// 查找指定代号的股票账户
		int pos = getStockPos(stockId);
		if (pos >= 0) { // 已找到
			// 获得已有的股票账户
			StockAccount stockAccount = stocks[pos];
			// 存入已有的股票账户
			result = stockAccount.takeOut(amount);
			// 更新已有的股票账户
			stocks[pos] = stockAccount;
		}
		return result;
	}

	// 查找指定代号的股票账户
	private int getStockPos(String stockId) {
		int pos = -1;
		for (int i = 0; i < stocks.length; i++) {
			if (stocks[i].getStockId().equals(stockId)) {
				pos = i;
				break;
			}
		}
		return pos;
	}

	// 输出证券账户的详细信息
	public String toString() {
		String desc = "证券账户信息如下：\n";
		desc = String.format("%s\t%s\n", desc, fund.toString());
		for (StockAccount item : stocks) {
			desc = String.format("%s\t%s\n", desc, item.toString());
		}
		return desc;
	}

}
