package com.network.im_server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.network.DateUtil;

//聊天服务器
public class ChatServer {
	private static final int SOCKET_PORT = 52000; // 聊天服务的侦听端口
	public static List<SocketBean> mSocketList = new ArrayList<SocketBean>(); // 容纳客户端套接字的列表
	
	public static void main(String[] args) {
		ChatServer server = new ChatServer();
		server.initServer(); // 初始化服务器
	}

	// 初始化服务器
	private void initServer() {
		System.out.println("Socket聊天服务已启动");
		try {
			// 创建一个ServerSocket，用于监听客户端Socket的连接请求
			ServerSocket server = new ServerSocket(SOCKET_PORT);
			while (true) {
				// 每当接收到客户端的Socket请求，服务器端也相应的创建一个Socket
				Socket clientSocket = server.accept();
				// 为该客户端创建单独的套接字详情对象
				SocketBean socket = new SocketBean(DateUtil.getTimeId(), clientSocket);
				mSocketList.add(socket); // 把新的客户端套接字添加进列表
				System.out.println("连接了一个客户端");
				// 每连接一个客户端，就启动一个服务线程伺候该客户端
				new Thread(new ServerTask(socket)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
