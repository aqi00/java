package com.object.trade;

import com.object.account.BankAccount;

// 演示各种账户类的使用
public class TestAccount {

	public static void main(String[] args) {
		testBankAccount(); // 演示银行账户的操作
		testSecurityAccount(); // 演示证券账户的操作
	}

	// 演示银行账户的操作
	private static void testBankAccount() {
		BankAccount bank = new BankAccount(); // 创建一个银行账户
		bank.saveCurrent(5000); // 活期账户存入5000元
		bank.saveDeposit(6, 5000); // 存入6个月定期的5000元
		System.out.println("第一次存款操作之后，"+bank.toString());
		bank.takeCurrent(2000); // 活期账户取出2000元
		bank.takeDeposit(6); // 取出6个月定期存款
		bank.saveDeposit(12, 5000); // 存入12个月定期的5000元
		System.out.println("第二次存款操作之后，"+bank.toString());
	}

	// 演示证券账户的操作
	private static void testSecurityAccount() {
		SecurityAccount security = new SecurityAccount(); // 创建一个证券账户
		security.saveFund(10000); // 可用资金存入10000元
		security.buyStock("601398", "工商银行", 500); // 买入500股工商银行
		System.out.println("第一次股票交易之后，"+security.toString());
		security.takeFund(3000); // 可用资金取出3000元
		security.sellStock("601398", 100); // 卖出100股工商银行
		security.buyStock("601857", "中国石油", 300); // 买入300股中国石油
		System.out.println("第二次股票交易之后，"+security.toString());
	}
}
