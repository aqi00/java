package com.network.socket;

//演示Socket服务器的运行（TCP协议的可靠连接）
public class TestTcpServer {
	
	public static void main(String[] args) {
		new Thread(new ReceiveText()).start(); // 启动一个文本接收线程
		new Thread(new ReceiveFile()).start(); // 启动一个文件接收线程
	}
	
}
