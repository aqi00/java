package com.network.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.network.PrintUtils;

//演示Socket客户端的运行（UDP协议的不可靠连接）
public class TestUdpClient {
	// 以下为Socket服务器的IP和端口，根据实际情况修改
	private static final String SOCKET_IP = "192.168.1.8";
	private static final int UDP_PORT = 61000; // UDP传输专用端口

	public static void main(String[] args) {
		startUdpClient("Hello World"); // 启动UDP客户端发送文本消息
		startUdpClient("你好，世界"); // 启动UDP客户端发送文本消息
	}

	// 启动UDP客户端发送文本消息
	private static void startUdpClient(String message) {
		PrintUtils.print("UDP客户端发送消息：" + message);
		// 创建一个DatagramSocket对象
		try (DatagramSocket socket = new DatagramSocket()) {
			// 根据IP地址获得对应的网络地址对象
			InetAddress serverAddress = InetAddress.getByName(SOCKET_IP);
			byte data[] = message.getBytes(); // 把字符串转换为字节数组
			// 创建一个DatagramPacket对象，构造方法的四个参数依次为：
			// 待发送的数据、数据的长度、服务器的网络地址、服务器的端口号。
			DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, UDP_PORT);
			socket.send(packet); // 向服务器发送数据包
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
