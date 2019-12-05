package com.network.im_server;

import java.net.Socket;

//定义客户端的套接字详情
public class SocketBean {
	public String id; // 客户端的服务编号，这是服务端给客户端分配的编号
	public Socket socket; // 客户端的套接字
	public String deviceId; // 客户端的设备编号，这是客户端给自己取的编号
	public String nickName; // 客户端的昵称
	public String loginTime; // 客户端的登录时间
	
	public SocketBean(String id, Socket socket) {
		this.id = id;
		this.socket = socket;
	}

}
