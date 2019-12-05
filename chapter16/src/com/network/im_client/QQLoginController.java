package com.network.im_client;

import java.net.URL;
import java.util.ResourceBundle;

import com.network.ToastUtil;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//QQ登录界面的控制器
public class QQLoginController implements Initializable {
	@FXML private VBox vbRoot; // 根布局
	@FXML private Label labelTop; // 头部背景的标签
	@FXML private Label labelLogo; // 左侧LOGO的标签
	@FXML private TextField fieldUser; // QQ号码的输入框
	@FXML private TextField fieldPassword; // QQ密码的输入框
	@FXML private Button btnLogin; // 发送请求按钮

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { // 界面打开后的初始化操作
		fieldUser.setFont(Font.font("NSimSun", 20)); // 设置输入框的字体
		fieldPassword.setFont(Font.font("NSimSun", 20)); // 设置输入框的字体
		btnLogin.setFont(Font.font("NSimSun", 25)); // 设置按钮的字体
		Image bgImage = new Image(getClass().getResourceAsStream("qq_login_bg.png"));
		labelTop.setGraphic(new ImageView(bgImage)); // 设置标签的图片
		Image logoImage = new Image(getClass().getResourceAsStream("qq_logo_100.png"));
		labelLogo.setGraphic(new ImageView(logoImage)); // 设置标签的图片
		btnLogin.setOnAction(e -> doLogin() ); // 设置按钮的单击事件
	}
	
	// 登录
	private void doLogin() {
		if (fieldUser.getText().length() <= 0) {
			ToastUtil.show("请输入用户名");
			return;
		}
		if (fieldPassword.getText().length() <= 0) {
			ToastUtil.show("请输入密码");
			return;
		}
		QQPartner partner = QQPartner.getInstance(fieldUser.getText()); // 获取当前用户的QQ伴侣
		partner.sendAction(QQPartner.LOGIN, "", ""); // 吩咐QQ伴侣去登录
		Stage stage = (Stage) vbRoot.getScene().getWindow(); // 获得当前布局所在的舞台
		FriendListMain listMain = new FriendListMain(stage); // 创建好友列表窗口
		listMain.show(); // 显示好友列表窗口
	}

}
