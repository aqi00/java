package com.object.account;

import java.util.Arrays;

//定义一个银行账户类
public class BankAccount {
	private CashAccount current; // 活期存款账户
	private DepositAccount[] deposits; // 定期存款账户数组

	// 银行账户的构造方法
	public BankAccount() {
		current = new CashAccount(CashAccount.RMB, "活期存款");
		deposits = new DepositAccount[] {};
	}

	// 往活期账户存入
	public boolean saveCurrent(long amount) {
		return current.saveIn(amount);
	}

	// 从活期账户取出
	public boolean takeCurrent(long amount) {
		return current.takeOut(amount);
	}

	// 往定期账户存入
	public boolean saveDeposit(int depositTerm, long amount) {
		boolean result = false;
		int pos = getDepositPos(depositTerm); // 查找指定期限的定期账户
		if (pos >= 0) { // 已找到
			DepositAccount depositAccount = deposits[pos]; // 获得已有的定期账户
			result = depositAccount.saveIn(amount); // 存入已有的定期账户
			deposits[pos] = depositAccount; // 更新已有的定期账户
		} else { // 未找到
			// 创建新的定期账户
			DepositAccount depositAccount = new DepositAccount(
					depositTerm, depositTerm + "个月定期存款");
			result = depositAccount.saveIn(amount); // 存入新的定期账户
			deposits = Arrays.copyOf(deposits, deposits.length + 1); // 数组大小扩容
			deposits[deposits.length - 1] = depositAccount; // 插入新的定期账户
		}
		return result;
	}

	// 从定期账户取出
	public boolean takeDeposit(int depositTerm) {
		boolean result = false;
		int pos = getDepositPos(depositTerm); // 查找指定期限的定期账户
		if (pos >= 0) { // 已找到
			DepositAccount depositAccount = deposits[pos]; // 获得已有的定期账户
			// 取出已有的定期账户
			result = depositAccount.takeOut(depositAccount.getBalance());
			deposits[pos] = depositAccount; // 更新已有的定期账户
		}
		return result;
	}

	// 查找指定期限的定期账户
	private int getDepositPos(int depositTerm) {
		int pos = -1;
		for (int i = 0; i < deposits.length; i++) { // 遍历定期账户数组
			if (deposits[i].getDepositTerm() == depositTerm) { // 找到指定期限的定期账户
				pos = i;
				break;
			}
		}
		return pos;
	}

	// 输出银行账户的详细信息
	public String toString() {
		String desc = "银行账户信息如下：\n";
		desc = String.format("%s\t%s\n", desc, current.toString());
		for (DepositAccount item : deposits) { // 遍历定期账户数组
			desc = String.format("%s\t%s\n", desc, item.toString());
		}
		return desc;
	}

}
