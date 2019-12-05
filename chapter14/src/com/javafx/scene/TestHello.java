package com.javafx.scene;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//演示简单的JavaFX程序
//JavaFX程序的入口类继承自Application
public class TestHello extends Application {

	public static void main(String[] args) {
		launch(args); // 启动JavaFX应用，接下来会跳到start方法
	}

	@Override
	public void start(Stage stage) { // 应用程序开始运行
		stage.setTitle("Hello World"); // 设置舞台的标题
		Group group = new Group(); // 创建一个小组
		Scene scene = new Scene(group, 400, 50, Color.WHITE); // 创建一个场景
		stage.setScene(scene); // 设置舞台的场景
		stage.setResizable(false); // 设置舞台的尺寸是否允许变化
		stage.show(); // 显示舞台。相当于JFrame的setVisible(true)
	}

}
