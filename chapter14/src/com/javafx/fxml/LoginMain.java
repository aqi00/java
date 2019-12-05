package com.javafx.fxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//登录窗口的程序入口（FXML布局控件）
public class LoginMain extends Application {

	@Override
	public void start(Stage stage) throws Exception { // 应用程序开始运行
		stage.setTitle("登录窗口"); // 设置舞台的标题
		// 从FXML资源文件中加载程序的初始界面
		Parent root = FXMLLoader.load(getClass().getResource("login_with_expand.fxml"));
		Scene scene = new Scene(root, 410, 240); // 创建一个场景
		stage.setScene(scene); // 设置舞台的场景
		stage.setResizable(true); // 设置舞台的尺寸是否允许变化
		stage.show(); // 显示舞台
	}

	public static void main(String[] args) {
		launch(args); // 启动JavaFX应用，接下来会跳到start方法
	}

}
