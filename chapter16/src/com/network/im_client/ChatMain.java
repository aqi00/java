package com.network.im_client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//聊天界面的程序入口
public class ChatMain {

	// 显示窗口
	public void show() {
		Stage stage = new Stage(); // 创建一个舞台
		QQPartner partner = QQPartner.getInstance(""); // 获得QQ伴侣
		try {
			String title = String.format("与%s聊天", partner.getFriend().nick_name);
			stage.setTitle(title); // 设置舞台的标题
			stage.getIcons().add(QQPartner.QQ_LOGO); // 设置标题栏图标
			// 从FXML资源文件中加载程序的初始界面
			Parent root = FXMLLoader.load(getClass().getResource("chat.fxml"));
			Scene scene = new Scene(root, 500, 470); // 创建一个场景
			stage.setScene(scene); // 设置舞台的场景
			stage.setResizable(false); // 设置舞台的尺寸是否允许变化
			stage.show(); // 显示舞台
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
