package com.javafx.calculator;

import com.javafx.ToastUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

//简单计算器的程序入口
public class MathCalculator extends Application {
	private Label textResult; // 声明一个标签
	private ScrollPane scrollPane; // 声明一个滚动窗格
	
	// 获得指定符号以及宽高的按钮
	private Button getButton(String symbol, int width, int height) {
		Button button = new Button(symbol); // 创建一个按钮
		button.setPrefSize(width, height); // 设置按钮的推荐宽高
		button.setFont(Font.font("Times New Roman", 25)); // 设置按钮的字体
		return button;
	}

	@Override
	public void start(Stage stage) { // 应用程序开始运行
		// 创建计算器的各按钮
		Button btnDelete = getButton("←", 100, 70);
		Button btnCancel = getButton("CE", 100, 70);
		Button btnClear = getButton("C", 100, 70);
		Button btnNegate = getButton("+/-", 100, 70);
		Button btnSqrt = getButton("√", 100, 70);
		Button btnDiv = getButton("÷", 100, 70);
		Button btnRem = getButton("％", 100, 70);
		Button btnMul = getButton("╳", 100, 70);
		Button btnRec = getButton("1/x", 100, 70);
		Button btnSub = getButton("－", 100, 70);
		Button btnAdd = getButton("＋", 100, 70);
		Button btnEqual = getButton("＝", 100, 70 * 2);
		Button btnDot = getButton(".", 100, 70);
		Button btn9 = getButton("9", 100, 70);
		Button btn8 = getButton("8", 100, 70);
		Button btn7 = getButton("7", 100, 70);
		Button btn6 = getButton("6", 100, 70);
		Button btn5 = getButton("5", 100, 70);
		Button btn4 = getButton("4", 100, 70);
		Button btn3 = getButton("3", 100, 70);
		Button btn2 = getButton("2", 100, 70);
		Button btn1 = getButton("1", 100, 70);
		Button btn0 = getButton("0", 100 * 2, 70);

		BorderPane mainPane = new BorderPane(); // 创建一个边界窗格作为主界面
		mainPane.setPadding(new Insets(5, 5, 5, 5)); // 设置边界窗格的四周空白
		mainPane.setPrefSize(100 * 5, 70 * 5 + 90); // 设置边界窗格的推荐宽高
		// 下面是界面上方的结果标签
		textResult = new Label("0"); // 创建一个标签
		textResult.setPrefWidth(100 * 5 - 20); // 设置标签的推荐宽度
		textResult.setAlignment(Pos.BOTTOM_RIGHT); // 设置标签的对齐方式
		textResult.setWrapText(true); // 设置标签文本是否允许自动换行
		textResult.setFont(Font.font("Times New Roman", 25)); // 设置标签的字体
		scrollPane = new ScrollPane(); // 创建一个滚动窗格
		scrollPane.setPrefSize(100 * 5, 90); // 设置滚动窗格的推荐宽高
		scrollPane.setContent(textResult); // 设置滚动窗格的内容
		mainPane.setTop(scrollPane); // 把滚动窗格添加到主界面的上方
		// 界面中央采用网格窗格
		GridPane gridPane = new GridPane(); // 创建一个网格窗格
		gridPane.add(btnDelete, 0, 1); // 在网格窗格的第1行第0列添加按钮
		gridPane.add(btnCancel, 1, 1); // 在网格窗格的第1行第1列添加按钮
		gridPane.add(btnClear, 2, 1); // 在网格窗格的第1行第2列添加按钮
		gridPane.add(btnNegate, 3, 1); // 在网格窗格的第1行第3列添加按钮
		gridPane.add(btnSqrt, 4, 1); // 在网格窗格的第1行第4列添加按钮
		gridPane.add(btn7, 0, 2); // 在网格窗格的第2行第0列添加按钮
		gridPane.add(btn8, 1, 2); // 在网格窗格的第2行第1列添加按钮
		gridPane.add(btn9, 2, 2); // 在网格窗格的第2行第2列添加按钮
		gridPane.add(btnDiv, 3, 2); // 在网格窗格的第2行第3列添加按钮
		gridPane.add(btnRem, 4, 2); // 在网格窗格的第2行第4列添加按钮
		gridPane.add(btn4, 0, 3); // 在网格窗格的第3行第0列添加按钮
		gridPane.add(btn5, 1, 3); // 在网格窗格的第3行第1列添加按钮
		gridPane.add(btn6, 2, 3); // 在网格窗格的第3行第2列添加按钮
		gridPane.add(btnMul, 3, 3); // 在网格窗格的第3行第3列添加按钮
		gridPane.add(btnRec, 4, 3); // 在网格窗格的第3行第4列添加按钮
		mainPane.setCenter(gridPane); // 把网格窗格添加到主界面的中央
		// 界面下方再分为左右两边
		HBox bottomBox = new HBox(); // 创建一个水平箱子
		// 界面左下角部分
		BorderPane leftPane = new BorderPane(); // 创建一个边界窗格
		// 左边上半部分
		HBox leftTopBox = new HBox(); // 创建一个水平箱子
		leftTopBox.getChildren().add(btn1); // 给水平箱子添加按钮
		leftTopBox.getChildren().add(btn2); // 给水平箱子添加按钮
		leftTopBox.getChildren().add(btn3); // 给水平箱子添加按钮
		leftTopBox.getChildren().add(btnSub); // 给水平箱子添加按钮
		leftPane.setTop(leftTopBox); // 把水平箱子添加到边界窗格的上方
		// 左边下半部分
		HBox leftBottomBox = new HBox(); // 创建一个水平箱子
		leftBottomBox.getChildren().add(btn0); // 给水平箱子添加按钮
		leftBottomBox.getChildren().add(btnDot); // 给水平箱子添加按钮
		leftBottomBox.getChildren().add(btnAdd); // 给水平箱子添加按钮
		leftPane.setCenter(leftBottomBox); // 把水平箱子添加到边界窗格的中央
		// 界面下方开始填充
		bottomBox.getChildren().add(leftPane); // 给底部的水平箱子添加边界窗格
		bottomBox.getChildren().add(btnEqual); // 给底部的水平箱子添加按钮
		mainPane.setBottom(bottomBox); // 把底部的水平箱子添加添加到主界面下方
		// 下面显示程序界面
		Scene scene = new Scene(mainPane); // 创建一个场景
		stage.setTitle("算术计算器"); // 设置舞台的标题
		stage.setScene(scene); // 设置舞台的场景
		stage.setResizable(false); // 设置舞台的尺寸是否允许变化
		stage.show(); // 显示舞台

		// 设置按钮的点击动作
		btnDelete.setOnAction(e -> delete());
		btnCancel.setOnAction(e -> cancel());
		btnClear.setOnAction(e -> clear());
		btnEqual.setOnAction(e -> equal());
		btnAdd.setOnAction(e -> simpleCalculate("+"));
		btnSub.setOnAction(e -> simpleCalculate("-"));
		btnMul.setOnAction(e -> simpleCalculate("*"));
		btnDiv.setOnAction(e -> simpleCalculate("/"));
		btnRem.setOnAction(e -> simpleCalculate("%"));
		btnRec.setOnAction(e -> complexCalculate("/="));
		btnNegate.setOnAction(e -> complexCalculate("-="));
		btnSqrt.setOnAction(e -> complexCalculate("√="));
		btnDot.setOnAction(e -> inputNumber("."));
		btn9.setOnAction(e -> inputNumber("9"));
		btn8.setOnAction(e -> inputNumber("8"));
		btn7.setOnAction(e -> inputNumber("7"));
		btn6.setOnAction(e -> inputNumber("6"));
		btn5.setOnAction(e -> inputNumber("5"));
		btn4.setOnAction(e -> inputNumber("4"));
		btn3.setOnAction(e -> inputNumber("3"));
		btn2.setOnAction(e -> inputNumber("2"));
		btn1.setOnAction(e -> inputNumber("1"));
		btn0.setOnAction(e -> inputNumber("0"));
	}

	// 按下退格键，要删除上次输入的字符
	private void delete() {
		if (operator.equals("")) { // 无操作符，则表示逐位取消前一个操作数
			if (firstNum.length() == 1) {
				firstNum = "0";
			} else if (firstNum.length() > 0) {
				firstNum = firstNum.substring(0, firstNum.length() - 1);
			} else {
				ToastUtil.show("没有可取消的数字了");
				return;
			}
			showResult(firstNum);
		} else { // 有操作符，则表示逐位取消后一个操作数
			if (nextNum.length() == 1) {
				nextNum = "";
			} else if (nextNum.length() > 0) {
				nextNum = nextNum.substring(0, nextNum.length() - 1);
			} else {
				ToastUtil.show("没有可取消的数字了");
				return;
			}
			showResult(showText.substring(0, showText.length() - 1));
		}
	}

	// 按下取消键，要删除刚才输入的整个数字
	private void cancel() {
		result = "0";
		firstNum = result;
		nextNum = "";
		showResult(showText + "CE" + result);
	}

	// 按下清除键，要清空结果标签
	private void clear() {
		showResult(""); // 清空结果标签
		operator = "";
		firstNum = "";
		nextNum = "";
		result = "";
	}

	// 按下了等号键，执行运算操作
	private void equal() {
		inputText = "=";
		if (operator.length() == 0 || operator.contains("=")) {
			ToastUtil.show("请输入运算符");
			return;
		} else if (nextNum.length() <= 0) {
			ToastUtil.show("请输入数字");
			return;
		}
		if (caculate()) { // 计算成功，则显示计算结果
			operator = inputText;
			showResult(showText + operator + result);
			System.out.println("caculate succ. operator is " + operator);
		}
	}

	// 简单的四则运算，包括：加、减、乘、除、取余数
	private void simpleCalculate(String input) {
		inputText = input;
		System.out.println("inputText=" + inputText + ", firstNum=" + firstNum + ", operator=" + operator);
		if (firstNum.length() <= 0 || (operator.length() > 0 && !operator.contains("="))) {
			ToastUtil.show("请输入数字");
			return;
		}
		if (operator.length() == 0 || operator.contains("=")) {
			operator = inputText; // 操作符
			showResult(showText + operator);
		}
	}

	// 复杂的运算，包括：取倒数、取反、开方
	private void complexCalculate(String input) {
		inputText = input;
		if (firstNum.length() <= 0) {
			ToastUtil.show("请输入数字");
			return;
		}
		if (inputText.equals("/=")) { // 求倒数运算
			if ("0".equals(firstNum)) { // 发现被除数是0
				ToastUtil.show("被除数不能为零");
				return;
			}
			result = String.valueOf(Arithmetic.rec(firstNum));
		} else if (inputText.equals("-=")) { // 取反运算
			result = String.valueOf(Arithmetic.neg(firstNum));
		} else if (inputText.equals("√=")) { // 开方运算
			if (Double.parseDouble(firstNum) < 0) {
				ToastUtil.show("开根号的数值不能小于0");
				return;
			}
			result = String.valueOf(Arithmetic.sqrt(firstNum));
		}
		firstNum = result;
		nextNum = "";
		operator = inputText;
		showResult(showText + operator + result);
		System.out.println("result=" + result + ",firstNum=" + firstNum + ",operator=" + operator);
	}

	// 按下了数字键
	private void inputNumber(String input) {
		inputText = input;
		if (operator.contains("=")) { // 上一次单击了等号按钮，则清空操作符
			operator = "";
			firstNum = "";
			showText = "";
		}
		if (operator.equals("")) { // 无操作符，则继续拼接前一个操作数
			firstNum = firstNum + inputText;
		} else { // 有操作符，则继续拼接后一个操作数
			nextNum = nextNum + inputText;
		}
		showResult(showText + inputText);
	}

	private String inputText; // 输入的字符
	private String operator = ""; // 操作符
	private String firstNum = ""; // 前一个操作数
	private String nextNum = ""; // 后一个操作数
	private String result = ""; // 当前的计算结果
	private String showText = ""; // 显示的文本内容

	// 开始加减乘除四则运算，计算成功则返回true，计算失败则返回false
	private boolean caculate() {
		System.out.println("operator=" + operator + ", firstNum=" + firstNum + ", nextNum=" + nextNum);
		if (operator.equals("+")) { // 当前是相加运算
			result = String.valueOf(Arithmetic.add(firstNum, nextNum));
		} else if (operator.equals("-")) { // 当前是相减运算
			result = String.valueOf(Arithmetic.sub(firstNum, nextNum));
		} else if (operator.equals("*")) { // 当前是相乘运算
			result = String.valueOf(Arithmetic.mul(firstNum, nextNum));
		} else if (operator.equals("/")) { // 当前是相除运算
			if ("0".equals(nextNum)) { // 发现被除数是0
				ToastUtil.show("被除数不能为零"); // 被除数为0，要弹窗提示用户
				return false; // 返回false表示运算失败
			} else { // 被除数非0，则进行正常的除法运算
				result = String.valueOf(Arithmetic.div(firstNum, nextNum));
			}
		} else if (operator.equals("%")) { // 当前是取余数运算
			if ("0".equals(nextNum)) { // 发现被除数是0
				ToastUtil.show("被除数不能为零"); // 被除数为0，要弹窗提示用户
				return false; // 返回false表示运算失败
			} else { // 被除数非0，则进行正常的除法运算
				result = String.valueOf(Arithmetic.rem(firstNum, nextNum));
			}
		}
		System.out.println("result=" + result); // 把运算结果打印到日志中
		firstNum = result;
		nextNum = "";
		return true; // 返回true表示运算成功
	}

	// 显示计算结果
	private void showResult(String text) {
		showText = text;
		textResult.setText(showText);
		scrollBottom(); // 滚动到最底部
	}

	// 滚动到最底部
	private void scrollBottom() {
		// 总高度
		double totalHeight = scrollPane.getContent().getBoundsInLocal().getHeight();
		// 可见高度
		double visibleHeight = scrollPane.getViewportBounds().getHeight();
		// 待滚动高度
		double scrollHeight = totalHeight - visibleHeight;
		if (scrollHeight > 0) {
			scrollPane.setVvalue(scrollHeight);
		}
	}

	public static void main(String[] args) {
		launch(args); // 启动JavaFX应用，接下来会跳到start方法
	}
}
