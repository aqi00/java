package com.network.im_client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

//QQ聊天界面的控制器
public class ChatController implements Initializable {
	@FXML private ScrollPane spChat; // 聊天消息的滚动面板
	@FXML private TextArea areaContent; // 待发送消息的多行输入框
	@FXML private Button btnSend; // 发送按钮

	private VBox vbChat; // 聊天消息的箱子
	private Background bg_white = new Background(new BackgroundFill(Color.WHITE, null, null));
	private Background bg_green = new Background(new BackgroundFill(Color.LIGHTGREEN, null, null));
	private Background bg_pink = new Background(new BackgroundFill(Color.PINK, null, null));

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { // 界面打开后的初始化操作
		areaContent.setFont(Font.font("NSimSun", 20)); // 设置输入框的字体
		btnSend.setFont(Font.font("NSimSun", 20)); // 设置按钮的字体

		spChat.setBackground(bg_white); // 设置滚动面板的背景
		vbChat = new VBox(); // 创建一个垂直箱子
		vbChat.setMinHeight(270); // 设置垂直箱子的最小高度
		vbChat.setPadding(new Insets(0, 20, 0, 10)); // 设置垂直箱子的四周间距
		vbChat.setBackground(bg_white); // 设置垂直箱子的背景
		spChat.setContent(vbChat); // 把垂直箱子作为滚动面板的内容
		vbChat.prefWidthProperty().bind(spChat.widthProperty()); // 让垂直箱子与滚动面板等宽
		
		btnSend.setOnAction(e -> sendMyMessage() ); // 设置发送按钮的单击事件
		QQPartner partner = QQPartner.getInstance(""); // 获取当前的QQ伴侣
		// 设置QQ伴侣的回调监听器。回调时通过showOtherMessage方法把对方消息添加到界面上
		partner.setListener((String content) -> showOtherMessage(content));
	}

	// 发送我的消息
	private void sendMyMessage() {
		String message = areaContent.getText(); // 获取要发送的消息
		addMyMessage(message); // 把我的消息添加到界面上
		QQPartner partner = QQPartner.getInstance(""); // 获取当前的QQ伴侣
		Friend friend = partner.getFriend(); // 获取好友信息
		partner.sendAction(QQPartner.SENDMSG, friend.device_id, message); // 吩咐QQ伴侣发送消息
		areaContent.setText(""); // 清空消息输入框
	}
	
	// 显示对方的消息
	private void showOtherMessage(String content) {
		int pos = content.indexOf(QQPartner.SPLIT_LINE);
		String head = content.substring(0, pos); // 消息头部
		String body = content.substring(pos + 1); // 消息主体
		String[] splitArray = head.split(QQPartner.SPLIT_ITEM);
		if (splitArray[0].equals(QQPartner.RECVMSG)) { // 收到消息
			// 定义一个JavaFX任务，任务的call方法不能操作界面，succeeded方法才能操作界面
			Task task = new Task<Void>() {
				// call方法里面的线程非主线程，不能操作界面
				protected Void call() throws Exception {
					return null;
				}

				// succeeded方法里面的线程是主线程，可以操作界面
				protected void succeeded() {
					super.succeeded();
					addOtherMessage(body); // 把对方消息添加到界面上
				}
			};
			task.run(); // 启动JavaFX任务
		} else { // 出错分支
		}
	}
	
	// 获取消息的标签
	private Label getMessageLabel(String name, String message) {
		Label label = new Label(); // 创建一个标签
		label.setMaxWidth(300); // 设置标签的最大宽度
		String content = String.format("<%s>\n%s", name, message);
		label.setText(content); // 设置标签的文本
		label.setFont(Font.font("NSimSun", 20)); // 设置标签的字体
		label.setWrapText(true); // 设置标签文本是否支持自动换行。true表示支持，false表示不支持
		label.setGraphic(new ImageView(QQPartner.QQ_LOGO)); // 设置标签的图标
		label.setGraphicTextGap(20); // 设置图标与文字的间距
		return label;
	}
	
	// 添加我的消息
	private void addMyMessage(String message) {
		QQPartner partner = QQPartner.getInstance(""); // 获取当前的QQ伴侣
		HBox hb = new HBox(); // 创建一个水平箱子
		Label label = getMessageLabel(partner.getNickName(), message); // 获取消息的标签
		label.setAlignment(Pos.CENTER_RIGHT); // 设置标签的对齐方式。我的消息靠右对齐
		label.setContentDisplay(ContentDisplay.RIGHT); // 把图标放到文字右边。默认图标在文字左边
		label.setBackground(bg_green); // 设置标签的背景。我的消息背景为绿色
		HBox hbLeft = new HBox(); // 创建一个顶在左边的空箱子
		HBox.setHgrow(hbLeft, Priority.ALWAYS); // 设置箱子在水平方向上允许拉伸
		hb.getChildren().addAll(hbLeft, label); // 把空箱子和标签一起添加进水平箱子
		hb.setPadding(new Insets(0, 0, 10, 0)); // 设置水平箱子的四周间距
		vbChat.getChildren().add(hb); // 把水平箱子添加到聊天窗口
		scrollBottom(); // 聊天窗口滚到底部
	}

	// 添加对方消息
	private void addOtherMessage(String message) {
		QQPartner partner = QQPartner.getInstance(""); // 获取当前的QQ伴侣
		HBox hb = new HBox(); // 创建一个水平箱子
		Label label = getMessageLabel(partner.getFriend().nick_name, message); // 获取消息的标签
		label.setAlignment(Pos.CENTER_LEFT); // 设置标签的对齐方式。对方消息靠左对齐
		label.setBackground(bg_pink); // 设置标签的背景。对方消息背景为粉色
		hb.getChildren().add(label); // 把标签添加进水平箱子
		hb.setPadding(new Insets(0, 0, 10, 0)); // 设置水平箱子的四周间距
		vbChat.getChildren().add(hb); // 把水平箱子添加到聊天窗口
		scrollBottom(); // 聊天窗口滚到底部
	}

	// 滚动到最底部
	private void scrollBottom() {
		// 定义一个JavaFX任务，任务的call方法不能操作界面，succeeded方法才能操作界面
		Task task = new Task<Void>() {
			// call方法里面的线程非主线程，不能操作界面
			protected Void call() throws Exception {
				Thread.sleep(100);
				return null;
			}

			// succeeded方法里面的线程是主线程，可以操作界面
			protected void succeeded() {
				super.succeeded();
				// 总高度
				double totalHeight = spChat.getContent().getBoundsInLocal().getHeight();
				// 可见高度
				double visibleHeight = spChat.getViewportBounds().getHeight();
				// 待滚动高度
				double scrollHeight = totalHeight - visibleHeight;
				if (scrollHeight > 0) {
					spChat.setVvalue(scrollHeight);
				}
			}
		};
		task.run(); // 启动JavaFX任务
	}

}
