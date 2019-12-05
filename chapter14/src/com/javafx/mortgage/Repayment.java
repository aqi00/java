package com.javafx.mortgage;

//还贷的数据结构
public class Repayment {
	public double totalLoan; // 贷款总额
	public double monthRepay; // 每月还款金额
	public double monthMinus; // 等额本金方式的每月递减金额
	public double totalInterest; // 贷款总利息

	public Repayment() {
		totalLoan = 0;
		monthRepay = 0;
		monthMinus = 0;
		totalInterest = 0;
	}
}
