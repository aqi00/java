package com.object.account;

// 定一个定期存款账户类
public class DepositAccount extends Account {
	private int depositTerm; // 存款期限，单位月

	// 存款账户的构造方法
	public DepositAccount(int depositTerm, String depositName) {
		super(depositName);
		this.depositTerm = depositTerm;
		setUnit("元"); // 设置余额的单位
	}

	// 获取存款的期限
	public int getDepositTerm() {
		return this.depositTerm;
	}

	// 设置存款的期限
	public void setDepositTerm(int depositTerm) {
		this.depositTerm = depositTerm;
	}

	// 这里的定期存款只能一次性全部取出，不能分批取出，适合整存整取、零存整取。
	public boolean takeOut(long amount) {
		if (amount == getBalance()) { // 全部取出
			return super.takeOut(amount);
		} else {
			return false;
		}
	}

	// 输出存款现金账户信息
	public String toString() {
		String desc = String.format("存款期限为%d个月，%s", this.depositTerm, super.toString());
		return desc;
	}
}
