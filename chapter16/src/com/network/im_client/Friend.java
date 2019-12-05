package com.network.im_client;

//定义一个好友信息类
public class Friend {
	public String device_id; // 好友的设备编号
	public String nick_name; // 好友的昵称
	public String login_time; // 好友的登录时间

	public Friend(String device_id, String nick_name, String login_time) {
		this.device_id = device_id;
		this.nick_name = nick_name;
		this.login_time = login_time;
	}
}
