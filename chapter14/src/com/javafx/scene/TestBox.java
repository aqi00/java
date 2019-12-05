package com.javafx.scene;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//演示JavaFX的水平箱子和垂直箱子
//JavaFX程序的入口类继承自Application
public class TestBox extends Application {
	private Font font = Font.font("NSimSun", 16); // 创建一个字体对象

	public static void main(String[] args) {
		launch(args); // 启动JavaFX应用，接下来会跳到start方法
	}

	@Override
	public void start(Stage stage) { // 应用程序开始运行
		stage.setTitle("测试箱子"); // 设置舞台的标题
		BorderPane borderPane = new BorderPane(); // 创建一个边界窗格
		Scene scene = new Scene(borderPane, 520, 180, Color.WHITE); // 创建一个场景

		FlowPane flowPane = new FlowPane(); // 创建一个流式窗格
		flowPane.setAlignment(Pos.CENTER); // 设置流式窗格的对齐方式

		Button btn1 = new Button("水平排列"); // 创建一个按钮
		btn1.setFont(font); // 设置按钮的字体
		btn1.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				HBox hbox = new HBox(); // 创建一个水平箱子
				hbox.setAlignment(Pos.CENTER); // 设置水平箱子的对齐方式
				hbox.getChildren().add(getLabel("离离原上草", Font.font("SimHei", 25))); // 给水平箱子添加一个标签
				hbox.getChildren().add(getLabel("一岁一枯荣", Font.font("KaiTi", 25))); // 给水平箱子添加一个标签
				hbox.getChildren().add(getLabel("野火烧不尽", Font.font("NSimSun", 25))); // 给水平箱子添加一个标签
				hbox.getChildren().add(getLabel("春风吹又生", Font.font("FangSong", 25))); // 给水平箱子添加一个标签
				borderPane.setCenter(hbox); // 把水平箱子放到边界窗格的中央
			}
		});
		flowPane.getChildren().add(btn1); // 往流式窗格上添加按钮

		Button btn2 = new Button("垂直排列"); // 创建一个按钮
		btn2.setFont(font); // 设置按钮的字体
		btn2.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				VBox vbox = new VBox(); // 创建一个垂直箱子
				vbox.setAlignment(Pos.CENTER); // 设置垂直箱子的对齐方式
				vbox.getChildren().add(getLabel("离离原上草", Font.font("LiSu", 30))); // 给垂直箱子添加一个标签
				vbox.getChildren().add(getLabel("一岁一枯荣", Font.font("YouYuan", 30))); // 给垂直箱子添加一个标签
				vbox.getChildren().add(getLabel("野火烧不尽", Font.font("STXingkai", 30))); // 给垂直箱子添加一个标签
				vbox.getChildren().add(getLabel("春风吹又生", Font.font("STXinwei", 30))); // 给垂直箱子添加一个标签
				borderPane.setCenter(vbox); // 把垂直箱子放到边界窗格的中央
			}
		});
		flowPane.getChildren().add(btn2); // 往流式窗格上添加按钮

		borderPane.setTop(flowPane); //把流式窗格放到边界窗格的上方
		stage.setScene(scene); // 设置舞台的场景
		stage.setResizable(false); // 设置舞台的尺寸是否允许变化
		stage.show(); // 显示舞台
	}

	// 获得指定文本及字体的标签
	private Label getLabel(String text, Font font) {
		Label label = new Label(text); // 创建一个标签
		label.setFont(font); // 设置标签的字体
		label.setAlignment(Pos.CENTER); // 设置标签的对齐方式
		label.setWrapText(true); // 设置标签文本是否支持自动换行。true表示支持，false表示不支持
		return label;
	}

}
