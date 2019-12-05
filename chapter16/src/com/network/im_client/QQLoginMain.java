package com.network.im_client;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

//聊天工具登录窗口的程序入口
public class QQLoginMain extends Application {

	@Override
	public void start(Stage stage) throws Exception { // 应用程序开始运行
		stage.setTitle("请登录QQ"); // 设置舞台的标题
		stage.getIcons().add(QQPartner.QQ_LOGO); // 设置标题栏图标
		// 从FXML资源文件中加载程序的初始界面
		Parent root = FXMLLoader.load(getClass().getResource("qq_login.fxml"));
		Scene scene = new Scene(root, 500, 420); // 创建一个场景
		stage.setScene(scene); // 设置舞台的场景
		stage.setResizable(false); // 设置舞台的尺寸是否允许变化
		stage.show(); // 显示舞台
		// 设置舞台的关闭请求事件，在用户单击右上角叉号按钮时，会触发这里的handle方法
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				// 关闭窗口之前，要先通知服务器注销当前账号
				QQPartner partner = QQPartner.getInstance(""); // 获取当前的QQ伴侣
				partner.sendAction(QQPartner.LOGOUT, "", ""); // 吩咐QQ伴侣去注销
			}
		});
	}

	public static void main(String[] args) {
		launch(args); // 启动JavaFX应用，接下来会跳到start方法
	}

}
