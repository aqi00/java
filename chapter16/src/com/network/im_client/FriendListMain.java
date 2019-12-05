package com.network.im_client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//聊天工具好友列表的程序入口
public class FriendListMain {
	private Stage mStage; // 当前程序的舞台
	
	public FriendListMain(Stage stage) {
		mStage = stage;
	}

	// 显示窗口
	public void show() {
		try {
			mStage.setTitle("好友列表"); // 设置舞台的标题
			mStage.getIcons().add(QQPartner.QQ_LOGO); // 设置标题栏图标
			// 从FXML资源文件中加载程序的初始界面
			Parent root = FXMLLoader.load(getClass().getResource("friend_list.fxml"));
			Scene scene = new Scene(root, 300, 300); // 创建一个场景
			mStage.setScene(scene); // 设置舞台的场景
			mStage.setResizable(false); // 设置舞台的尺寸是否允许变化
			mStage.show(); // 显示舞台
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
