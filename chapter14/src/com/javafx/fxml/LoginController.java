package com.javafx.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

//登录窗口的界面控制器
public class LoginController implements Initializable {
	@FXML private Label labelType; // 登录类型标签
	@FXML private RadioButton rbPassword; // 密码登录对应的单选按钮
	@FXML private RadioButton rbVerifycode; // 验证码登录对应的单选按钮
	@FXML private Label labelUser; // 用户名标签
	@FXML private TextField fieldUser; // 用户名输入框
	@FXML private Label labelPassword; // 密码标签
	@FXML private PasswordField fieldPassword; // 密码输入框
	@FXML private Button btnLogin; // 登录按钮
	@FXML private Label labelLoginResult; // 登录结果标签

	@Override
	public void initialize(URL location, ResourceBundle resources) { // 界面打开后的初始化操作
		labelType.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		rbPassword.setFont(Font.font("NSimSun", 20)); // 设置单选按钮的字体及大小
		rbVerifycode.setFont(Font.font("NSimSun", 20)); // 设置单选按钮的字体及大小
		labelUser.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		fieldUser.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		labelPassword.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		fieldPassword.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		btnLogin.setFont(Font.font("NSimSun", 20)); // 设置按钮文本的字体及大小
		labelLoginResult.setFont(Font.font("NSimSun", 20)); // 设置标签文本的字体及大小
		rbPassword.setOnAction(e -> { // 选中“密码登录”单选按钮后触发的事件
			labelUser.setText("用户名：");
			labelPassword.setText("密　码：");
		});
		rbVerifycode.setOnAction(e -> { // 选中“验证码登录”单选按钮后触发的事件
			labelUser.setText("手机号：");
			labelPassword.setText("验证码：");
		});
		btnLogin.setOnAction(e -> { // 单击“登录”按钮后触发的事件
			String result = String.format("您输入的用户名为%s，密码为%s", 
					fieldUser.getText(), fieldPassword.getText());
			labelLoginResult.setText(result); // 在登录结果标签上显示登录信息
		});
	}
}
