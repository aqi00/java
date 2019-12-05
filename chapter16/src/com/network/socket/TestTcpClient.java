package com.network.socket;

//演示Socket客户端的运行（TCP协议的可靠连接）
public class TestTcpClient {
	
	public static void main(String[] args) {
		//testSendText(); // 发送文本消息
		testSendFile();
	}
	
	// 发送文本消息
	private static void testSendText() {
		SendText task = new SendText(); // 创建一个文本发送任务
		new Thread(task).start(); // 为文本发送任务开启分线程
		task.sendText("你好呀"); // 命令该线程发送文本消息
		task.sendText("Hello World"); // 命令该线程发送文本消息
	}
	
	// 发送本地文件
	private static void testSendFile() {
		new Thread(new SendFile("E:/bliss.jpg")).start(); // 为文件发送任务开启分线程
		new Thread(new SendFile("E:/qq_qrcode.png")).start(); // 为文件发送任务开启分线程
	}
	
}
