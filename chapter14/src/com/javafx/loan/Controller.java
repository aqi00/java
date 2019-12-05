package com.javafx.loan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

import com.javafx.NumberField;
import com.javafx.ToastUtil;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

//房贷计算器的界面控制器
public class Controller implements Initializable {
	@FXML private Label labelPrice;
	@FXML private NumberField fieldPrice; // 购房总价输入框
	@FXML private Label labelPriceUnit;
	@FXML private Label labelLoan;
	@FXML private NumberField fieldLoan; // 按揭部分输入框
	@FXML private Label labelLoanUnit;
	@FXML private Button btnLoan; // 计算房贷的按钮
	@FXML private Label labelLoanResult; // 房贷计算结果的标签

	@Override
	public void initialize(URL location, ResourceBundle resources) { // 界面打开后的初始化操作
		labelPrice.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		fieldPrice.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		labelPriceUnit.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		labelLoan.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		fieldLoan.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		labelLoanUnit.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		btnLoan.setFont(Font.font("NSimSun", 20)); // 设置按钮文本的字体及大小
		labelLoanResult.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		// 单击了按钮“计算贷款总额”，显示计算好的贷款总额
		btnLoan.setOnAction(e -> showLoan());
	}

	// 根据购房总价和按揭比例，计算贷款总额
	private void showLoan() {
		if (fieldPrice.getText() == null || fieldPrice.getText().length() <= 0) {
			ToastUtil.show("购房总价不能为空");
			return;
		} else if (fieldLoan.getText() == null || fieldLoan.getText().length() <= 0) {
			ToastUtil.show("按揭部分不能为空");
			return;
		}
		double total = Double.parseDouble(fieldPrice.getText());
		double rate = Double.parseDouble(fieldLoan.getText()) / 100;
		// 拼接房贷计算结果，金额保留小数点后面两位
		String desc = String.format("您的贷款总额为%s万元", formatDecimal(total * rate, 2));
		labelLoanResult.setText(desc);
	}

	// 精确到小数点后第几位
	private String formatDecimal(double value, int digit) {
		BigDecimal decimal = new BigDecimal(value);
		decimal = decimal.setScale(digit, RoundingMode.HALF_UP);
		return decimal.toString();
	}

}
