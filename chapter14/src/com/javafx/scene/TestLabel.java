package com.javafx.scene;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

//JavaFX程序的入口类继承自Application
public class TestLabel extends Application {
	private Label label = getDefaultLabel(); // 创建一个默认的标签

	public static void main(String[] args) {
		launch(args); // 启动JavaFX应用，接下来会跳到start方法
	}

	@Override
	public void start(Stage stage) { // 应用程序开始运行
		stage.setTitle("测试标签"); // 设置舞台的标题
		BorderPane borderPane = new BorderPane(); // 创建一个边界窗格
		Scene scene = new Scene(borderPane, 520, 180, Color.WHITE); // 创建一个场景

		borderPane.setCenter(label); // 把标签放到边界窗格的中央

		FlowPane flowPane = new FlowPane(); // 创建一个流式窗格
		flowPane.setAlignment(Pos.CENTER); // 设置流式窗格的对齐方式

		Button btn1 = new Button("背景黄色"); // 创建一个按钮
		btn1.setFont(Font.font("NSimSun", 16)); // 设置按钮文本的字体及大小
		// btn1.setText(""); // 设置按钮的文本
		// btn1.setPrefSize(200, 30); // 设置按钮的推荐宽高
		// btn1.setAlignment(Pos.CENTER); // 设置按钮的对齐方式
		btn1.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				// 创建一个充满指定颜色的背景
				Background bg = new Background(new BackgroundFill(Color.YELLOW, null, null));
				label.setBackground(bg); // 设置标签的背景
			}
		});
		flowPane.getChildren().add(btn1); // 往流式窗格上添加按钮

		Button btn2 = new Button("前景红色"); // 创建一个按钮
		btn2.setFont(Font.font("NSimSun", 16)); // 设置按钮文本的字体及大小
		btn2.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				label.setTextFill(Color.RED); // 设置标签的文本颜色
			}
		});
		flowPane.getChildren().add(btn2); // 往流式窗格上添加按钮

		Button btn3 = new Button("中文楷体"); // 创建一个按钮
		btn3.setFont(Font.font("NSimSun", 16)); // 设置按钮文本的字体及大小
		btn3.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				// 创建一个40号大小且为楷体的字体对象，适用于汉字。注意JavaFX不支持对中文设置斜体或粗体
				Font kaiti = Font.font("KaiTi", 40);
				label.setFont(kaiti); // 设置标签的字体
			}
		});
		flowPane.getChildren().add(btn3); // 往流式窗格上添加按钮

		Button btn4 = new Button("英文斜体"); // 创建一个按钮
		btn4.setFont(Font.font("NSimSun", 16)); // 设置按钮文本的字体及大小
		btn4.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				label.setText("Hello World");
				// 创建一个40号大小且又是斜体又是粗体的字体对象，适用于英文。注意英文不能使用中文字体
				Font italic_bold = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 40);
				label.setFont(italic_bold); // 设置标签的字体
			}
		});
		flowPane.getChildren().add(btn4); // 往流式窗格上添加按钮

		Button btn5 = new Button("显示图像"); // 创建一个按钮
		btn5.setFont(Font.font("NSimSun", 16)); // 设置按钮文本的字体及大小
		btn5.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				// 创建一个图像
				Image image = new Image(getClass().getResourceAsStream("banana.png"));
				label.setGraphic(new ImageView(image)); // 设置标签的图像
				label.setText(""); // 设置标签的文本
				// label = new Label(label.getText(), new ImageView(image)); // 创建一个既有文本又有图像的标签
				// borderPane.setCenter(label); // 把标签放到边界窗格的中央
			}
		});
		flowPane.getChildren().add(btn5); // 往流式窗格上添加按钮

		Button btn6 = new Button("恢复原状"); // 创建一个按钮
		btn6.setFont(Font.font("NSimSun", 16)); // 设置按钮文本的字体及大小
		btn6.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				label = getDefaultLabel(); // 获取默认的标签
				borderPane.setCenter(label); // 把标签放到边界窗格的中央
			}
		});
		flowPane.getChildren().add(btn6); // 往流式窗格上添加按钮

		borderPane.setTop(flowPane); //把流式窗格放到边界窗格的上方
		stage.setScene(scene); // 设置舞台的场景
		stage.setResizable(false); // 设置舞台的尺寸是否允许变化
		stage.show(); // 显示舞台
	}

	// 获取一个默认的标签
	private Label getDefaultLabel() {
		Label label = new Label("这里查看文字效果\n这里查看文字效果"); // 创建一个标签
		label.setPrefSize(400, 100); // 设置标签的推荐宽高
		// label.setText(""); // 设置标签的文本
		label.setAlignment(Pos.CENTER); // 设置标签的对齐方式
		label.setWrapText(true); // 设置标签文本是否支持自动换行。true表示支持，false表示不支持
		return label;
	}

}
