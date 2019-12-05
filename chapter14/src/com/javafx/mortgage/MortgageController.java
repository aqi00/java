package com.javafx.mortgage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.javafx.NumberField;
import com.javafx.ToastUtil;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Font;

//房贷计算器的界面控制器
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MortgageController implements Initializable {
	@FXML private Label labelPrice;
	@FXML private NumberField fieldPrice; // 购房总价的输入框
	@FXML private Label labelPriceUnit;
	@FXML private Label labelLoan;
	@FXML private NumberField fieldLoan; // 按揭部分的输入框
	@FXML private Label labelLoanUnit;
	@FXML private Button btnLoan; // 计算贷款总额的按钮
	@FXML private Label labelLoanResult;
	@FXML private Label labelMethod;
	@FXML private RadioButton rbInterest; // 等额本息的单选按钮
	@FXML private RadioButton rbPrincipal; // 等额本金的单选按钮
	@FXML private CheckBox cbBusi; // 商业贷款的复选框
	@FXML private NumberField fieldBusi; // 商业贷款的输入框
	@FXML private Label labelBusiUnit;
	@FXML private CheckBox cbAccu; // 公积金贷款的复选框
	@FXML private NumberField fieldAccu; // 公积金贷款的输入框
	@FXML private Label labelAccuUnit;
	@FXML private Label labelYear;
	@FXML private ComboBox dbYear; // 贷款年限的下拉框
	@FXML private Label labelRatio;
	@FXML private ComboBox dbRatio; // 贷款利率的下拉框
	@FXML private Button btnRepay; // 计算还款明细的按钮
	@FXML private Label labelRepayResult; // 还款明细的结果标签

	@Override
	public void initialize(URL location, ResourceBundle resources) { // 界面打开后的初始化操作
		labelPrice.setFont(Font.font("NSimSun", 20)); // 设置标签的字体及大小
		fieldPrice.setFont(Font.font("NSimSun", 20)); // 设置输入框的字体及大小
		labelPriceUnit.setFont(Font.font("NSimSun", 20)); // 设置标签的字体及大小
		labelLoan.setFont(Font.font("NSimSun", 20)); // 设置标签的字体及大小
		fieldLoan.setFont(Font.font("NSimSun", 20)); // 设置输入框的字体及大小
		labelLoanUnit.setFont(Font.font("NSimSun", 20)); // 设置标签的字体及大小
		btnLoan.setFont(Font.font("NSimSun", 20)); // 设置按钮的字体及大小
		labelLoanResult.setFont(Font.font("NSimSun", 20)); // 设置标签的字体及大小
		labelMethod.setFont(Font.font("NSimSun", 20)); // 设置标签的字体及大小
		rbInterest.setFont(Font.font("NSimSun", 20)); // 设置单选按钮的字体及大小
		rbPrincipal.setFont(Font.font("NSimSun", 20)); // 设置单选按钮的字体及大小
		cbBusi.setFont(Font.font("NSimSun", 20)); // 设置复选框的字体及大小
		fieldBusi.setFont(Font.font("NSimSun", 20)); // 设置输入框的字体及大小
		labelBusiUnit.setFont(Font.font("NSimSun", 20)); // 设置标签的字体及大小
		cbAccu.setFont(Font.font("NSimSun", 20)); // 设置复选框的字体及大小
		fieldAccu.setFont(Font.font("NSimSun", 20)); // 设置输入框的字体及大小
		labelAccuUnit.setFont(Font.font("NSimSun", 20)); // 设置标签的字体及大小
		labelYear.setFont(Font.font("NSimSun", 20)); // 设置标签的字体及大小
		dbYear.setStyle(String.format("-fx-font: %d \"%s\";", 20, "NSimSun")); // 设置下拉框的字体
		labelRatio.setFont(Font.font("NSimSun", 20)); // 设置标签的字体及大小
		dbRatio.setStyle(String.format("-fx-font: %d \"%s\";", 20, "NSimSun")); // 设置下拉框的字体
		btnRepay.setFont(Font.font("NSimSun", 20)); // 设置按钮的字体及大小
		labelRepayResult.setFont(Font.font("NSimSun", 20)); // 设置标签的字体及大小
		// 单击了按钮“计算贷款总额”，显示计算好的贷款总额
		btnLoan.setOnAction(e -> showLoan());
		rbInterest.setOnAction(e -> isInterest = true); // 设置等额本息单选按钮的单击事件
		rbPrincipal.setOnAction(e -> isInterest = false); // 设置等额本金单选按钮的单击事件
		cbBusi.setOnAction(e -> hasBusi = cbBusi.isSelected()); // 设置商业贷款复选框的单击事件
		cbAccu.setOnAction(e -> hasAccu = cbAccu.isSelected()); // 设置公积金贷款复选框的单击事件
		// 添加贷款年限下拉框的选中监听器
		dbYear.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
							int pos = yearDescList.indexOf(newValue); // 定位当前选项所处的位置序号
							mYear = yearArray[pos];
						});
		dbYear.setItems(FXCollections.observableArrayList(yearDescList)); // 设置贷款年限下拉框的数据项
		dbYear.getSelectionModel().select(4); // 贷款年限下拉框默认选中第五项
		// 添加贷款利率下拉框的选中监听器
		dbRatio.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
							int pos = ratioDescList.indexOf(newValue); // 定位当前选项所处的位置序号
							mBusiRatio = busiArray[pos];
							mAccuRatio = accuArray[pos];
						});
		dbRatio.setItems(FXCollections.observableArrayList(ratioDescList)); // 设置贷款利率下拉框的数据项
		dbRatio.getSelectionModel().select(0); // 贷款利率下拉框默认选中第一项
		btnRepay.setOnAction(e -> { // 单击了按钮“计算还款明细”
					if (!hasBusi && !hasAccu) {
						ToastUtil.show("请选择商业贷款或者公积金贷款");
						return;
					} else if (hasBusi && (fieldBusi.getText()==null || fieldBusi.getText().length()<=0)) {
						ToastUtil.show("商业贷款总额不能为空");
						return;
					} else if (hasAccu && (fieldAccu.getText()==null || fieldAccu.getText().length()<=0)) {
						ToastUtil.show("公积金贷款总额不能为空");
						return;
					}
					showRepay(); // 显示计算好的还款明细
				});
	}

	private boolean isInterest = true; // 是否为等额本息
	private boolean hasBusi = true; // 是否存在商业贷款
	private boolean hasAccu = false; // 是否存在公积金贷款
	private int mYear; // 贷款年限
	private double mBusiRatio; // 商业贷款的利率
	private double mAccuRatio; // 公积金贷款的利率
	private List<String> yearDescList = Arrays.asList("5年", "10年", "15年", "20年", "30年");
	private int[] yearArray = { 5, 10, 15, 20, 30 }; // 贷款年限数组
	private List<String> ratioDescList = Arrays.asList(
			"2015年10月24日 五年期商贷利率 4.90%　公积金利率 3.25%",
			"2015年08月26日 五年期商贷利率 5.15%　公积金利率 3.25%",
			"2015年06月28日 五年期商贷利率 5.40%　公积金利率 3.50%",
			"2015年05月11日 五年期商贷利率 5.65%　公积金利率 3.75%",
			"2015年03月01日 五年期商贷利率 5.90%　公积金利率 4.00%",
			"2014年11月22日 五年期商贷利率 6.15%　公积金利率 4.25%",
			"2012年07月06日 五年期商贷利率 6.55%　公积金利率 4.50%");
	private double[] busiArray = { 4.90, 5.15, 5.40, 5.65, 5.90, 6.15, 6.55 }; // 商贷利率数组
	private double[] accuArray = { 3.25, 3.25, 3.50, 3.75, 4.00, 4.25, 4.50 }; // 公积金利率数组

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
		String desc = String.format("您的贷款总额为%s万元", formatDecimal(total * rate, 2));
		labelLoanResult.setText(desc);
	}

	// 根据贷款的相关条件，计算还款总额、利息总额，以及月供
	private void showRepay() {
		Repayment busiResult = new Repayment();
		Repayment accuResult = new Repayment();
		if (hasBusi) { // 申请了商业贷款
			double busiLoad = Double.parseDouble(fieldBusi.getText()) * 10000;
			double busiTime = mYear * 12;
			double busiRate = mBusiRatio / 100;
			// 计算商业贷款部分的还款明细
			busiResult = calMortgage(busiLoad, busiTime, busiRate, isInterest);
		}
		if (hasAccu) { // 申请了公积金贷款
			double accuLoad = Double.parseDouble(fieldAccu.getText()) * 10000;
			double accuTime = mYear * 12;
			double accuRate = mAccuRatio / 100;
			// 计算公积金贷款部分的还款明细
			accuResult = calMortgage(accuLoad, accuTime, accuRate, isInterest);
		}
		String desc = String.format("您的贷款总额为%s万元\n　　还款总额为%s万元\n其中利息总额为%s万元\n　　还款总时间为%d月",
				formatDecimal((busiResult.totalLoan + accuResult.totalLoan)/10000, 2),
				formatDecimal((busiResult.totalLoan + busiResult.totalInterest
						+ accuResult.totalLoan + accuResult.totalInterest)/ 10000, 2),
				formatDecimal((busiResult.totalInterest + accuResult.totalInterest)/10000, 2),
				mYear * 12);
		if (isInterest) { // 如果是等额本息方式
			desc = String.format("%s\n每月还款金额为%s元", desc,
					formatDecimal(busiResult.monthRepay + accuResult.monthRepay, 2));
		} else { // 如果是等额本金方式
			desc = String.format("%s\n首月还款金额为%s元，其后每月递减%s元", desc,
					formatDecimal(busiResult.monthRepay + accuResult.monthRepay, 2),
					formatDecimal(busiResult.monthMinus + accuResult.monthMinus, 2));
		}
		labelRepayResult.setText(desc);
	}

	// 精确到小数点后第几位
	private String formatDecimal(double value, int digit) {
		BigDecimal decimal = new BigDecimal(value);
		decimal = decimal.setScale(digit, RoundingMode.HALF_UP);
		return decimal.toString();
	}

	// 根据贷款金额、还款年限、基准利率，计算还款信息
	private Repayment calMortgage(double ze, double nx, double rate, boolean bInterest) {
		double zem = (ze * rate / 12 * Math.pow((1 + rate / 12), nx))
				/ (Math.pow((1 + rate / 12), nx) - 1);
		double amount = zem * nx;
		double rateAmount = amount - ze;
		double benjinm = ze / nx;
		double lixim = ze * (rate / 12);
		double diff = benjinm * (rate / 12);
		double huankuanm = benjinm + lixim;
		double zuihoukuan = diff + benjinm;
		double av = (huankuanm + zuihoukuan) / 2;
		double zong = av * nx;
		double zongli = zong - ze;

		Repayment result = new Repayment();
		result.totalLoan = ze;
		if (bInterest) {
			result.monthRepay = zem;
			result.totalInterest = rateAmount;
		} else {
			result.monthRepay = huankuanm;
			result.monthMinus = diff;
			result.totalInterest = zongli;
		}
		return result;
	}

}
