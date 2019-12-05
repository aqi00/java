package com.javafx.widget;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//演示几种输入框
public class TestTextInput extends Application {
	private Font font = Font.font("NSimSun", 16); // 创建一个字体对象

	public static void main(String[] args) {
		launch(args); // 启动JavaFX应用，接下来会跳到start方法
	}

	@Override
	public void start(Stage stage) { // 应用程序开始运行
		stage.setTitle("测试文本输入"); // 设置舞台的标题
		BorderPane borderPane = new BorderPane(); // 创建一个边界窗格
		Scene scene = new Scene(borderPane, 400, 120, Color.WHITE); // 创建一个场景

		FlowPane flowPane = new FlowPane(); // 创建一个流式窗格
		flowPane.setAlignment(Pos.CENTER); // 设置流式窗格的对齐方式

		Button btn1 = new Button("单行输入框"); // 创建一个按钮
		btn1.setFont(font); // 设置按钮的字体
		btn1.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				HBox hbox = new HBox(); // 创建一个水平箱子
				hbox.setAlignment(Pos.CENTER); // 设置水平箱子的对齐方式
				Label label = new Label("请输入手机号码："); // 创建一个标签
				label.setFont(font); // 设置标签的字体
				TextField field = new TextField(); // 创建一个单行输入框
				field.setFont(font); // 设置单行输入框的字体
				field.setPrefSize(200, 50); // 设置单行输入框的推荐宽高
				field.setEditable(true); // 设置单行输入框能否编辑
				field.setPromptText("请输入手机号码"); // 设置单行输入框的提示语
				field.setAlignment(Pos.CENTER_LEFT); // 设置单行输入框的对齐方式
				field.setPrefColumnCount(11); // 设置单行输入框的推荐列数
				hbox.getChildren().addAll(label, field); // 给水平箱子添加一个单行输入框
				borderPane.setCenter(hbox); // 把水平箱子放到边界窗格的中央
			}
		});
		flowPane.getChildren().add(btn1); // 往流式窗格上添加按钮

		Button btn2 = new Button("密码输入框"); // 创建一个按钮
		btn2.setFont(font); // 设置按钮的字体
		btn2.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				HBox hbox = new HBox(); // 创建一个水平箱子
				hbox.setAlignment(Pos.CENTER); // 设置水平箱子的对齐方式
				Label label = new Label("请输入密码："); // 创建一个标签
				label.setFont(font); // 设置标签的字体
				PasswordField field = new PasswordField(); // 创建一个密码输入框
				field.setFont(font); // 设置密码输入框的字体
				field.setPrefSize(200, 50); // 设置密码输入框的推荐宽高
				field.setEditable(true); // 设置密码输入框能否编辑
				field.setPromptText("请输入密码"); // 设置密码输入框的提示语
				field.setAlignment(Pos.CENTER_LEFT); // 设置密码输入框的对齐方式
				field.setPrefColumnCount(11); // 设置密码输入框的推荐列数
				hbox.getChildren().addAll(label, field); // 给水平箱子添加一个密码输入框
				borderPane.setCenter(hbox); // 把水平箱子放到边界窗格的中央
			}
		});
		flowPane.getChildren().add(btn2); // 往流式窗格上添加按钮

		Button btn3 = new Button("多行输入框"); // 创建一个按钮
		btn3.setFont(font); // 设置按钮的字体
		btn3.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				HBox hbox = new HBox(); // 创建一个水平箱子
				hbox.setAlignment(Pos.CENTER); // 设置水平箱子的对齐方式
				hbox.setPrefSize(300, 80); // 设置水平箱子的推荐宽高
				Label label = new Label("请输入评价："); // 创建一个标签
				label.setFont(font); // 设置标签的字体
				TextArea area = new TextArea(); // 创建一个多行输入框
				area.setFont(font); // 设置多行输入框的字体
				area.setMaxHeight(85); // 设置多行输入框的最大高度
				//area.setMaxWidth(300); // 设置多行输入框的最大宽度
				area.setPrefSize(200, 50); // 设置多行输入框的推荐宽高
				area.setEditable(true); // 设置多行输入框能否编辑
				area.setPromptText("请输入评价"); // 设置多行输入框的提示语
				area.setWrapText(true); // 设置是否支持自动换行。true表示支持，false表示不支持。
				area.setPrefColumnCount(11); // 设置多行输入框的推荐列数
				area.setPrefRowCount(3); // 设置多行输入框的推荐行数
				hbox.getChildren().addAll(label, area); // 给水平箱子添加一个多行输入框
				borderPane.setCenter(hbox); // 把水平箱子放到边界窗格的中央
			}
		});
		flowPane.getChildren().add(btn3); // 往流式窗格上添加按钮

		borderPane.setTop(flowPane); //把流式窗格放到边界窗格的上方
		stage.setScene(scene); // 设置舞台的场景
		stage.setResizable(false); // 设置舞台的尺寸是否允许变化
		stage.show(); // 显示舞台
	}

}
