package com.network.im_client;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.util.Callback;

//好友列表界面的控制器
@SuppressWarnings("unchecked")
public class FriendListController implements Initializable {
	@FXML private Label labelTop; // 头部文字的标签
	@FXML private ListView listFriend; // 好友列表

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { // 界面打开后的初始化操作
		labelTop.setFont(Font.font("NSimSun", 20)); // 设置标签的字体
		// 设置列表视图的字体
		listFriend.setStyle(String.format("-fx-font: %f \"%s\";", 20f, "NSimSun"));
		// 设置列表视图的选择监听器
		listFriend.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Friend>() {
			@Override
			public void changed(ObservableValue<? extends Friend> arg0, Friend old_fri, Friend new_fri) {
				QQPartner partner = QQPartner.getInstance(""); // 获取当前的QQ伴侣
				partner.setFriend(new_fri); // 吩咐QQ伴侣记下好友信息
				ChatMain chat = new ChatMain(); // 创建聊天窗口
				chat.show(); // 显示聊天窗口
			}
		});
		
		QQPartner partner = QQPartner.getInstance(""); // 获取当前的QQ伴侣
		partner.setListener((String content) -> showFriend(content)); // 设置QQ伴侣的回调监听器
		partner.setListener(new QQListener() { // 设置QQ伴侣的回调监听器
			public void doEvent(String content) {
				showFriend(content); // 把好友列表显示在窗口上
			}
		});
		partner.sendAction(QQPartner.GETLIST, "", ""); // 吩咐QQ伴侣去获取好友列表
		refreshList(); // 定时刷新好友列表
		labelTop.setText(String.format("%s的在线好友", partner.getNickName()));
	}

	// 定时刷新好友列表
	private void refreshList() {
		Timer timer = new Timer(); // 创建一个定时器
		timer.schedule(new TimerTask() { // 定时器每十秒调度一次
			public void run() {
				QQPartner partner = QQPartner.getInstance(""); // 获取当前的QQ伴侣
				partner.sendAction(QQPartner.GETLIST, "", ""); // 吩咐QQ伴侣去获取好友列表
			}
		}, 0, 10*1000);
	}
	
	// 显示好友列表
	private void showFriend(String content) {
		List<Friend> friendList = new ArrayList<Friend>();
		int pos = content.indexOf(QQPartner.SPLIT_LINE);
		String head = content.substring(0, pos); // 消息头部
		String body = content.substring(pos + 1); // 消息主体
		String[] splitArray = head.split(QQPartner.SPLIT_ITEM);
		if (splitArray[0].equals(QQPartner.GETLIST)) { // 是获取好友列表
			String[] bodyArray = body.split("\\|"); // 每条好友记录之间以竖线分隔
			for (String oneBody : bodyArray) { // 遍历所有的好友记录
				String[] itemArray = oneBody.split(QQPartner.SPLIT_ITEM);
				if (oneBody.length() > 0 && itemArray.length >= 3) {
					// itemArray数组内容依次为：设备编号、好友昵称、登录时间
					friendList.add(new Friend(itemArray[0], itemArray[1], itemArray[2]));
				}
			}
		} else { // 出错分支
		}
		if (friendList.size() <= 0) { // 无好友
			return;
		}
		if (friendList.size() == listFriend.getItems().size()) { // 好友列表没有变化
			return;
		}
		// 把清单对象转换为JavaFX控件能够识别的数据对象
		ObservableList<Friend> obList = FXCollections.observableArrayList(friendList);
		listFriend.setItems(obList); // 设置列表视图的数据对象
		// 设置列表视图的单元工厂（每个列表单元将如何展示）
		listFriend.setCellFactory(new Callback<ListView<Friend>, ListCell<Friend>>() {
			@Override
			public ListCell<Friend> call(ListView<Friend> listFriend) {
				return new ListCell<Friend>() { // 返回重新设定的列表单元
					@Override
					public void updateItem(Friend item, boolean empty) {
						super.updateItem(item, empty);
						if (!empty) {
							// 每个列表单元都是标签Label，左图标右文字
							setGraphic(new ImageView(QQPartner.QQ_LOGO));
							setText(item.nick_name);
						}
					}
				};
			}
		});
	}
	
}
