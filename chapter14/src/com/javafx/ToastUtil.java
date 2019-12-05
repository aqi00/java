package com.javafx;

import javafx.scene.control.Alert;

//定义提示框工具类
public class ToastUtil {
	// 弹出提示框
	public static void show(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION); // 创建消息对话框
		alert.setContentText(message); // 设置对话框的内容文本
		alert.show(); // 显示对话框
	}
}
