package com.network.im_client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

import com.network.DateUtil;

import javafx.scene.image.Image;

//定义一个QQ伴侣，专门处理与服务端之间的信息交互
public class QQPartner {
	public static Image QQ_LOGO;
	static { // 读取QQ图标
		QQ_LOGO = new Image(QQPartner.class.getResourceAsStream("qq_logo_30.png"));
	}
	// 以下为Socket服务器的IP和端口，根据实际情况修改
	private static final String SOCKET_IP = "192.168.0.212";
	private static final int SOCKET_PORT = 52000;
	private Socket mSocket; // 声明一个套接字对象
	private BufferedReader mReader; // 声明一个缓存读取器对象
	private OutputStream mWriter; // 声明一个输出流对象
	// 以下定义了各个常量标识串
	public static String CONTENT = "CONTENT";
	public static String SPLIT_LINE = "|"; // 行分隔符
	public static String SPLIT_ITEM = ","; // 列分隔符
	public static String LOGIN = "LOGIN"; // 登录
	public static String LOGOUT = "LOGOUT"; // 注销
	public static String SENDMSG = "SENDMSG"; // 发送消息
	public static String RECVMSG = "RECVMSG"; // 接收消息
	public static String GETLIST = "GETLIST"; // 获取在线好友列表

	private String mNickName; // 昵称
	private String mSerial; // 序列号，用作当前账号的设备编号
	private Friend mFriend; // 正在聊天的好友
	private static QQPartner mInstance; // QQ伴侣的单例
	private QQListener mListener; // 回调监听器

	// 获取QQ伴侣的单例
	public static QQPartner getInstance(String nickName) {
		if (mInstance == null) {
			mInstance = new QQPartner(); // 创建QQ伴侣的实例
		}
		if (nickName != null && nickName.length() > 0) {
			mInstance.setNickName(nickName); // 设置用户昵称
		}
		return mInstance;
	}

	private QQPartner() {
		mSocket = new Socket(); // 创建一个套接字对象
		try {
			System.out.println("connect");
			// 命令套接字连接指定地址的指定端口
			mSocket.connect(new InetSocketAddress(SOCKET_IP, SOCKET_PORT), 3000);
			// 根据套接字的输入流，构建缓存读取器
			mReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream(), "UTF8"));
			mWriter = mSocket.getOutputStream(); // 获得套接字的输出流
			new RecvThread().start(); // 启动一条子线程来读取服务器相应的数据
		} catch (Exception e) {
			e.printStackTrace();
			notify(99, e.getMessage()); // 发送异常的消息通知
		}
	}

	public void setNickName(String nickName) { // 设置昵称
		mNickName = nickName;
		mSerial = String.format("%010d", new Random().nextInt(1000 * 1000));
	}

	public String getNickName() { // 获取昵称
		return mNickName;
	}

	public void setFriend(Friend friend) { // 设置好友
		mFriend = friend;
	}

	public Friend getFriend() { // 获取好友
		return mFriend;
	}

	public void setListener(QQListener listener) { // 设置回调监听器
		mListener = listener;
	}

	// 向服务端发送动作消息
	public void sendAction(String action, String otherId, String msgText) {
		// 拼接完整的聊天消息
		String content = String.format("%s,%s,%s,%s,%s%s%s\r\n", action,
				mSerial, mNickName, DateUtil.getNowTime(), otherId, SPLIT_LINE, msgText);
		System.out.println("sendAction : " + content);
		try {
			mWriter.write(content.getBytes("UTF8")); // 往输出流对象中写入数据
		} catch (Exception e) {
			e.printStackTrace();
			notify(98, e.getMessage()); // 发送异常的消息通知
		}
	}

	// 定义消息接收子线程，让App从后台服务器接收消息
	private class RecvThread extends Thread {
		@Override
		public void run() {
			String content;
			try {
				// 读取到来自服务器的数据
				while ((content = mReader.readLine()) != null) {
					QQPartner.this.notify(0, content); // 发送正常的消息通知
				}
			} catch (Exception e) {
				e.printStackTrace();
				QQPartner.this.notify(97, e.getMessage()); // 发送异常的消息通知
			}
		}
	}

	// 发送服务端来的消息通知
	private void notify(int type, String message) {
		System.out.println("type=" + type + ", message=" + message);
		if (mListener != null) {
			if (type == 0) { // 正常消息
				mListener.doEvent(message); // 事件回调处理
			} else { // 连接异常
				String content = String.format("%s%s%s%s", "ERROR", SPLIT_ITEM, SPLIT_LINE, message);
				mListener.doEvent(content); // 事件回调处理
			}
		}
	}

}
