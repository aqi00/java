package com.concurrent.mouse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//打地鼠游戏的程序入口
public class HitMouseMain extends Application {

	@Override
	public void start(Stage stage) throws Exception { // 应用程序开始运行
		stage.setTitle("打地鼠游戏"); // 设置舞台的标题
		// 从FXML资源文件中加载程序的初始界面
		Parent root = FXMLLoader.load(getClass().getResource("hit_mouse.fxml"));
		Scene scene = new Scene(root, 560, 700); // 创建一个场景
		stage.setScene(scene); // 设置舞台的场景
		stage.setResizable(false); // 设置舞台的尺寸是否允许变化
		stage.show(); // 显示舞台
	}

	public static void main(String[] args) {
		launch(args); // 启动JavaFX应用，接下来会跳到start方法
	}

}
