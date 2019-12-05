package com.javafx.widget;

import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//演示几种提示对话框
public class TestDialog extends Application {

	public static void main(String[] args) {
		launch(args); // 启动JavaFX应用，接下来会跳到start方法
	}

	@Override
	public void start(Stage stage) { // 应用程序开始运行
		stage.setTitle("测试提示对话框"); // 设置舞台的标题
		BorderPane borderPane = new BorderPane(); // 创建一个边界窗格
		Scene scene = new Scene(borderPane, 420, 100, Color.WHITE); // 创建一个场景

		FlowPane flowPane = new FlowPane(); // 创建一个流式窗格
		flowPane.setAlignment(Pos.CENTER); // 设置流式窗格的对齐方式

		Label label = new Label("这里查看对话框结果"); // 创建一个标签
		label.setPrefSize(400, 70); // 设置标签的推荐宽高
		label.setFont(Font.font("NSimSun", 16)); // 设置标签文本的字体及大小
		label.setAlignment(Pos.CENTER); // 设置标签的对齐方式
		label.setWrapText(true); // 设置标签文本是否支持自动换行。true表示支持，false表示不支持
		borderPane.setCenter(label); // 把标签放到边界窗格的中央

		Button btn1 = new Button("消息对话框"); // 创建一个按钮
		btn1.setFont(Font.font("NSimSun", 16)); // 设置按钮文本的字体及大小
		btn1.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				Alert alert = new Alert(Alert.AlertType.INFORMATION); // 创建一个消息对话框
				// alert.setTitle("请注意"); // 设置对话框的标题
				alert.setHeaderText("今日天气"); // 设置对话框的头部文本
				// 设置对话框的内容文本
				alert.setContentText("今天白天晴转多云，北转南风2、3间4级，最高气温28℃；夜间多云转阴，南风2级左右，最低气温16℃。");
				alert.show(); // 显示对话框
			}
		});
		flowPane.getChildren().add(btn1); // 往流式窗格上添加按钮

		Button btn2 = new Button("警告对话框"); // 创建一个按钮
		btn2.setFont(Font.font("NSimSun", 16)); // 设置按钮文本的字体及大小
		btn2.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				Alert alert = new Alert(Alert.AlertType.WARNING); // 创建一个警告对话框
				// alert.setTitle("请注意"); // 设置对话框的标题
				alert.setHeaderText("编译警告"); // 设置对话框的头部文本
				// 设置对话框的内容文本
				alert.setContentText("您在本代码的第60行未初始化变量，可能导致空指针异常。");
				alert.show(); // 显示对话框
			}
		});
		flowPane.getChildren().add(btn2); // 往流式窗格上添加按钮

		Button btn3 = new Button("错误对话框"); // 创建一个按钮
		btn3.setFont(Font.font("NSimSun", 16)); // 设置按钮文本的字体及大小
		btn3.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				Alert alert = new Alert(Alert.AlertType.ERROR); // 创建一个错误对话框
				// alert.setTitle("请注意"); // 设置对话框的标题
				alert.setHeaderText("致命错误"); // 设置对话框的头部文本
				// 设置对话框的内容文本
				alert.setContentText("系统即将关机，请赶紧保存文件。");
				alert.show(); // 显示对话框
			}
		});
		flowPane.getChildren().add(btn3); // 往流式窗格上添加按钮

		Button btn4 = new Button("确认对话框"); // 创建一个按钮
		btn4.setFont(Font.font("NSimSun", 16)); // 设置按钮文本的字体及大小
		btn4.setOnAction(new EventHandler<ActionEvent>() { // 设置按钮的单击事件
			@Override
			public void handle(ActionEvent arg0) { // 处理单击事件
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION); // 创建一个确认对话框
				// alert.setTitle("请确认"); // 设置对话框的标题
				alert.setHeaderText("温馨提示"); // 设置对话框的头部文本
				// 设置对话框的内容文本
				alert.setContentText("尊敬的用户，你真的要卸载我吗？");
				// 显示对话框，并等待按钮返回
				Optional<ButtonType> buttonType = alert.showAndWait();
				//System.out.println("getButtonData="+buttonType.get().getButtonData().toString());
				// 判断返回的按钮类型是确定还是取消，再据此分别进一步处理
				if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) { // 单击了确定按钮OK_DONE
					label.setText("您选择了“确定”按钮。虽然依依不舍，但是只能离开了");
				} else { // 单击了取消按钮CANCEL_CLOSE
					label.setText("您选择了“取消”按钮。让我再陪你三百六十五个日夜");
				}
			}
		});
		flowPane.getChildren().add(btn4); // 往流式窗格上添加按钮

		borderPane.setTop(flowPane); //把流式窗格放到边界窗格的上方
		stage.setScene(scene); // 设置舞台的场景
		stage.setResizable(false); // 设置舞台的尺寸是否允许变化
		stage.show(); // 显示舞台
	}

}
