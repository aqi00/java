package com.object.trade;

// 定义一个股票账户类
public class StockAccount extends Account {

	// 股票代码
	private String stockId;

	// 股票账户的构造方法
	public StockAccount(String stockId, String stockName) {
		super(stockName);
		this.stockId = stockId;
		setUnit("股");
	}

	// 获取股票代码
	public String getStockId() {
		return this.stockId;
	}

	// 买入股票，买入数量必须是100的整数倍，至少买入100股
	public boolean saveIn(long amount) {
		if (amount % 100 == 0 && amount >= 100) { // 整百买入
			return super.saveIn(amount);
		} else {
			return false;
		}
	}

	// 卖出股票，卖出数量必须是100的整数倍，至少卖出100股
	public boolean takeOut(long amount) {
		if (amount % 100 == 0 && amount >= 100) { // 整百卖出
			return super.takeOut(amount);
		} else {
			return false;
		}
	}

	// 输出股票现金账户信息
	public String toString() {
		String desc = String.format("股票代码为%s，%s", this.stockId,
				super.toString());
		return desc;
	}
}
