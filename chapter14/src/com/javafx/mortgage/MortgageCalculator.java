package com.javafx.mortgage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import com.javafx.NumberField;
import com.javafx.ToastUtil;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//房贷计算器的程序入口（纯代码布局控件）
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MortgageCalculator extends Application {
	private int mMainWidth = 400;
	private int mMainHeight = 600;
	private int mItemHeight = 40;

	public static void main(String[] args) {
		launch(args); // 启动JavaFX应用，接下来会跳到start方法
	}

	private NumberField fieldPrice;
	private NumberField fieldLoan;
	private Button btnLoan;
	private Label labelLoanResult;
	private RadioButton rbInterest;
	private RadioButton rbPrincipal;
	private CheckBox cbBusi;
	private NumberField fieldBusi;
	private CheckBox cbAccu;
	private NumberField fieldAccu;
	private ComboBox dbYear;
	private ComboBox dbRatio;
	private Button btnRepay;
	private Label labelRepayResult;

	// 初始化程序界面
	private void initUI(Stage stage) {
		FlowPane fpMain = new FlowPane();
		fpMain.setPadding(new Insets(20, 5, 20, 5));
		fpMain.setPrefSize(mMainWidth, mMainHeight);
		// 添加购房总价行
		HBox hbPrice = new HBox();
		hbPrice.setPadding(new Insets(5, 0, 5, 0));
		hbPrice.setPrefSize(mMainWidth, mItemHeight);
		Label labelPrice = new Label("购房总价：");
		labelPrice.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		labelPrice.setPrefSize(100, mItemHeight);
		hbPrice.getChildren().add(labelPrice);
		fieldPrice = new NumberField();
		fieldPrice.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		fieldPrice.setPrefSize(250, mItemHeight);
		hbPrice.getChildren().add(fieldPrice);
		Label labelPriceUnit = new Label("　万");
		labelPriceUnit.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		labelPriceUnit.setPrefSize(50, mItemHeight);
		hbPrice.getChildren().add(labelPriceUnit);
		fpMain.getChildren().add(hbPrice);
		// 添加按揭部分行
		HBox hbLoan = new HBox();
		hbLoan.setPadding(new Insets(5, 0, 5, 0));
		hbLoan.setPrefSize(mMainWidth, mItemHeight);
		Label labelLoan = new Label("按揭部分：");
		labelLoan.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		labelLoan.setPrefSize(100, mItemHeight);
		hbLoan.getChildren().add(labelLoan);
		fieldLoan = new NumberField();
		fieldLoan.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		fieldLoan.setPrefSize(250, mItemHeight);
		hbLoan.getChildren().add(fieldLoan);
		Label labelLoanUnit = new Label("　%");
		labelLoanUnit.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		labelLoanUnit.setPrefSize(50, mItemHeight);
		hbLoan.getChildren().add(labelLoanUnit);
		fpMain.getChildren().add(hbLoan);
		// 添加计算贷款总额按钮
		btnLoan = new Button("计算贷款总额");
		btnLoan.setFont(Font.font("NSimSun", 20)); // 设置按钮文本的字体及大小
		btnLoan.setPadding(new Insets(5, 0, 5, 0));
		btnLoan.setPrefSize(mMainWidth, mItemHeight);
		fpMain.getChildren().add(btnLoan);
		// 添加贷款总额标签
		labelLoanResult = new Label("这里显示贷款结果");
		labelLoanResult.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		labelLoanResult.setPadding(new Insets(5, 0, 5, 0));
		labelLoanResult.setPrefSize(mMainWidth, mItemHeight);
		fpMain.getChildren().add(labelLoanResult);
		// 添加还款方式行
		HBox hbMethod = new HBox();
		hbMethod.setPadding(new Insets(5, 0, 5, 0));
		hbMethod.setPrefSize(mMainWidth, mItemHeight);
		Label labelMethod = new Label("还款方式：");
		labelMethod.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		labelMethod.setPrefSize(140, mItemHeight);
		hbMethod.getChildren().add(labelMethod);
		ToggleGroup tgMethod = new ToggleGroup();
		rbInterest = new RadioButton("等额本息");
		rbInterest.setFont(Font.font("NSimSun", 20)); // 设置单选按钮的字体及大小
		rbInterest.setPrefSize(130, mItemHeight);
		rbInterest.setToggleGroup(tgMethod);
		rbInterest.setSelected(true);
		hbMethod.getChildren().add(rbInterest);
		rbPrincipal = new RadioButton("等额本金");
		rbPrincipal.setFont(Font.font("NSimSun", 20)); // 设置单选按钮的字体及大小
		rbPrincipal.setPrefSize(130, mItemHeight);
		rbPrincipal.setToggleGroup(tgMethod);
		hbMethod.getChildren().add(rbPrincipal);
		fpMain.getChildren().add(hbMethod);
		// 添加商业贷款行
		HBox hbBusi = new HBox();
		hbBusi.setPadding(new Insets(5, 0, 5, 0));
		hbBusi.setPrefSize(mMainWidth, mItemHeight);
		cbBusi = new CheckBox("商业贷款：　");
		cbBusi.setFont(Font.font("NSimSun", 20)); // 设置复选框的字体及大小
		cbBusi.setPrefSize(170, mItemHeight);
		cbBusi.setSelected(true);
		hbBusi.getChildren().add(cbBusi);
		fieldBusi = new NumberField();
		fieldBusi.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		fieldBusi.setPrefSize(180, mItemHeight);
		hbBusi.getChildren().add(fieldBusi);
		Label labelBusiUnit = new Label("　万");
		labelBusiUnit.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		labelBusiUnit.setPrefSize(50, mItemHeight);
		hbBusi.getChildren().add(labelBusiUnit);
		fpMain.getChildren().add(hbBusi);
		// 添加公积金贷款行
		HBox hbAccu = new HBox();
		hbAccu.setPadding(new Insets(5, 0, 5, 0));
		hbAccu.setPrefSize(mMainWidth, mItemHeight);
		cbAccu = new CheckBox("公积金贷款：");
		cbAccu.setFont(Font.font("NSimSun", 20)); // 设置复选框的字体及大小
		cbAccu.setPrefSize(170, mItemHeight);
		hbAccu.getChildren().add(cbAccu);
		fieldAccu = new NumberField();
		fieldAccu.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		fieldAccu.setPrefSize(180, mItemHeight);
		hbAccu.getChildren().add(fieldAccu);
		Label labelAccuUnit = new Label("　万");
		labelAccuUnit.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		labelAccuUnit.setPrefSize(50, mItemHeight);
		hbAccu.getChildren().add(labelAccuUnit);
		fpMain.getChildren().add(hbAccu);
		// 添加贷款年限行
		HBox hbYear = new HBox();
		hbYear.setPadding(new Insets(5, 0, 5, 0));
		hbYear.setPrefSize(mMainWidth, mItemHeight);
		Label labelYear = new Label("贷款年限：");
		labelYear.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		labelYear.setPrefSize(100, mItemHeight);
		hbYear.getChildren().add(labelYear);
		dbYear = new ComboBox(FXCollections.observableArrayList(yearDescList));
		dbYear.setStyle(String.format("-fx-font: %d \"%s\";", 20, "NSimSun")); // 设置下拉框的字体
		dbYear.setPrefSize(300, mItemHeight);
		dbYear.getSelectionModel().select(4);
		hbYear.getChildren().add(dbYear);
		fpMain.getChildren().add(hbYear);
		// 添加贷款利率行
		HBox hbRatio = new HBox();
		hbRatio.setPadding(new Insets(5, 0, 5, 0));
		hbRatio.setPrefSize(mMainWidth, mItemHeight);
		Label labelRatio = new Label("贷款利率：");
		labelRatio.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		labelRatio.setPrefSize(100, mItemHeight);
		hbRatio.getChildren().add(labelRatio);
		dbRatio = new ComboBox(FXCollections.observableArrayList(ratioDescList));
		dbRatio.setStyle(String.format("-fx-font: %d \"%s\";", 20, "NSimSun")); // 设置下拉框的字体
		dbRatio.setPrefSize(300, mItemHeight);
		dbRatio.getSelectionModel().select(0);
		hbRatio.getChildren().add(dbRatio);
		fpMain.getChildren().add(hbRatio);
		// 添加计算还款明细按钮
		btnRepay = new Button("计算还款明细");
		btnRepay.setFont(Font.font("NSimSun", 20)); // 设置按钮文本的字体及大小
		btnRepay.setPadding(new Insets(5, 0, 5, 0));
		btnRepay.setPrefSize(mMainWidth, mItemHeight);
		fpMain.getChildren().add(btnRepay);
		// 添加还款明细标签
		labelRepayResult = new Label("这里显示还款结果");
		labelRepayResult.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		labelRepayResult.setPadding(new Insets(5, 0, 5, 0));
		labelRepayResult.setPrefSize(mMainWidth, mItemHeight * 4);
		labelRepayResult.setWrapText(true);
		fpMain.getChildren().add(labelRepayResult);
		// 下面显示程序界面
		Scene scene = new Scene(fpMain); // 创建一个场景
		stage.setTitle("房贷计算器"); // 设置舞台的标题
		stage.setScene(scene); // 设置舞台的场景
		stage.setResizable(false); // 设置舞台的尺寸是否允许变化
		stage.show(); // 显示舞台
	}

	@Override
	public void start(Stage stage) throws Exception { // 应用程序开始运行
		initUI(stage); // 初始化程序界面
		// 点击了按钮“计算贷款总额”，显示计算好的贷款总额
		btnLoan.setOnAction(e -> showLoan());
		rbInterest.setOnAction(e -> isInterest = true);
		rbPrincipal.setOnAction(e -> isInterest = false);
		cbBusi.setOnAction(e -> hasBusi = cbBusi.isSelected());
		cbAccu.setOnAction(e -> hasAccu = cbAccu.isSelected());
		dbYear.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
							int pos = yearDescList.indexOf(newValue);
							mYear = yearArray[pos];
						});
		dbRatio.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
							int pos = ratioDescList.indexOf(newValue);
							mBusiRatio = busiArray[pos];
							mAccuRatio = accuArray[pos];
						});
		btnRepay.setOnAction(e -> { // 点击了按钮“计算还款明细”
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

	private List<String> yearDescList = Arrays.asList("5年", "10年", "15年", "20年", "30年");
	private int[] yearArray = { 5, 10, 15, 20, 30 }; // 还款年限数组
	private List<String> ratioDescList = Arrays.asList(
			"2015年10月24日 五年期商贷利率 4.90%　公积金利率 3.25%",
			"2015年08月26日 五年期商贷利率 5.15%　公积金利率 3.25%",
			"2015年06月28日 五年期商贷利率 5.40%　公积金利率 3.50%",
			"2015年05月11日 五年期商贷利率 5.65%　公积金利率 3.75%",
			"2015年03月01日 五年期商贷利率 5.90%　公积金利率 4.00%",
			"2014年11月22日 五年期商贷利率 6.15%　公积金利率 4.25%",
			"2012年07月06日 五年期商贷利率 6.15%　公积金利率 4.50%");
	private double[] busiArray = { 4.90, 5.15, 5.40, 5.65, 5.90, 6.15, 6.55 }; // 商贷利率数组
	private double[] accuArray = { 3.25, 3.25, 3.50, 3.75, 4.00, 4.25, 4.50 }; // 公积金利率数组
	private boolean isInterest = true; // 是否为等额本息
	private boolean hasBusi = true; // 是否存在商业贷款
	private boolean hasAccu = false; // 是否存在公积金贷款
	private int mYear = yearArray[4]; // 还款年限
	private double mBusiRatio = busiArray[0]; // 商业贷款的利率
	private double mAccuRatio = accuArray[0]; // 公积金贷款的利率

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
