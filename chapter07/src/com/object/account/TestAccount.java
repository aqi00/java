package com.object.account;

// 演示各种账户类的使用
public class TestAccount {

	public static void main(String[] args) {
		testBankAccount(); // 演示银行账户的操作
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

}
