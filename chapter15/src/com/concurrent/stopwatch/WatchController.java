package com.concurrent.stopwatch;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Font;

//秒表计时器的界面控制器
public class WatchController implements Initializable {
	@FXML private RadioButton rbSecond; // 秒级计时的单选按钮
	@FXML private RadioButton rbMilli; // 毫秒计时的单选按钮
	@FXML private Label labelSecond; // 秒级计时的标签
	@FXML private Label labelDot;
	@FXML private Label labelMilli; // 毫秒计时的标签
	@FXML private Button btnCount; // 计时按钮

	private boolean isCounting = false; // 是否正在计时
	private SecondThread secondThread; // 声明秒级计时的线程
	private MilliThread milliThread; // 声明毫秒计时的线程

	@Override
	public void initialize(URL location, ResourceBundle resources) { // 界面打开后的初始化操作
		rbSecond.setFont(Font.font("NSimSun", 20)); // 设置单选按钮的字体及大小
		rbMilli.setFont(Font.font("NSimSun", 20)); // 设置单选按钮的字体及大小
		labelSecond.setFont(Font.font("NSimSun", 40)); // 设置标签文本的字体及大小
		labelDot.setFont(Font.font("NSimSun", 40)); // 设置标签文本的字体及大小
		labelMilli.setFont(Font.font("NSimSun", 40)); // 设置标签文本的字体及大小
		btnCount.setFont(Font.font("NSimSun", 20)); // 设置按钮文本的字体及大小
		btnCount.setOnAction(e -> doWatch()); // 点击了按钮“开始计数”，开始秒表计数
	}
	
	// 开始秒表操作（开始计时或者停止计时）
	private void doWatch() {
		isCounting = !isCounting;
		btnCount.setText(isCounting ? "停止计时" : "开始计时");
		if (isCounting) { // 开始计时
			labelSecond.setText("00:00");
			labelMilli.setText("000");
			secondThread = new SecondThread(); // 创建新的秒级计时线程
			milliThread = new MilliThread(); // 创建新的毫秒计时线程
			secondThread.start(); // 启动秒级计时的线程
			milliThread.start(); // 启动毫秒计时的线程
		} else { // 停止计时
			secondThread.interrupt(); // 中断秒级计时的线程
			milliThread.interrupt(); // 中断毫秒计时的线程
		}
	}
	
	// 定义秒级计时的线程
	private class SecondThread extends Thread {
		@Override
		public void run() { // 线程开始运行
			int count = 0; // 计数值
			try {
				while (true) {
					Thread.sleep(1000); // 睡眠1秒
					count++; // 计数值加一
					String second = String.format("%02d:%02d", count/60, count%60);
					showCount(labelSecond, second); // 显示以秒为单位的时间计数
				}
			} catch (InterruptedException e) { // 在循环外面捕获中断异常
				e.printStackTrace();
			}
		}
	}

	// 定义毫秒计时的线程
	private class MilliThread extends Thread {
		@Override
		public void run() { // 线程开始运行
			int count = 0; // 计数值
			try {
				while (true) {
					Thread.sleep(1); // 睡眠1毫秒
					if (rbMilli.isSelected()) { // 选中了“毫秒计时”单选按钮
						count++; // 计数值加一
						String milli = String.format("%03d", count%1000);
						showCount(labelMilli, milli); // 显示以毫秒为单位的时间计数
					}
				}
			} catch (InterruptedException e) { // 在循环外面捕获中断异常
				e.printStackTrace();
			}
		}
	}
	
	// 在标签控件上显示计时结果
	private void showCount(Label label, String result) {
		// 定义一个JavaFX任务，任务的call方法不能操作界面，succeeded方法才能操作界面
		Task task = new Task<Void>() {
			// call方法里面的线程非主线程，不能操作界面
			protected Void call() throws Exception {
				return null;
			}

			// succeeded方法里面的线程是主线程，可以操作界面
			protected void succeeded() {
				super.succeeded();
				label.setText(result);
			}
		};
		task.run(); // 启动JavaFX任务
	}

}
