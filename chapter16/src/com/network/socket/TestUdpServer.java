package com.network.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.network.PrintUtils;

//演示Socket服务器的运行（UDP协议的不可靠连接）
public class TestUdpServer {
	private static final int UDP_PORT = 61000; // UDP传输专用端口

	public static void main(String[] args) {
		startUdpServer(); // 启动UDP服务器接收文本消息
	}

	// 启动UDP服务器接收文本消息
	private static void startUdpServer() {
		PrintUtils.print("UDP服务器已启动");
		// 创建一个监听指定端口的DatagramSocket对象
		try (DatagramSocket socket = new DatagramSocket(UDP_PORT)) {
			byte[] data = new byte[1024]; // 接收数据的字节数组
			// 创建一个DatagramPacket对象，并指定数据包的字节数组及其大小
			DatagramPacket packet = new DatagramPacket(data, data.length);
			while (true) { // 持续侦听
				socket.receive(packet); // 接收到了数据包
				// 把收到的数据转换为字符串。字符串构造方法的三个参数依次为：
				// 已收到的数据、起始偏移、数据的长度。
				String message = new String(packet.getData(),
						packet.getOffset(), packet.getLength());
				PrintUtils.print("UDP服务器收到消息：" + message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
