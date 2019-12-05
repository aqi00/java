package com.network.im_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.network.DateUtil;

//定义客户端聊天的服务逻辑
public class ServerTask implements Runnable {
	private SocketBean mSocket; // 客户端的套接字详情
	private BufferedReader mReader; // 客户端消息的缓存读取器

	public ServerTask(SocketBean socket) throws IOException {
		mSocket = socket;
		// 先获得套接字对象的输入流，再把输入流转换为缓存读取器
		mReader = new BufferedReader(new InputStreamReader(mSocket.socket.getInputStream(), "UTF8"));
	}

	@Override
	public void run() {
		try {
			String content = null;
			// 循环不断地从Socket中读取客户端发送过来的数据
			while ((content = mReader.readLine()) != null) {
				System.out.println("content="+content);
				int pos = content.indexOf("|");
				// 包头格式为：动作名称|设备编号|昵称|时间|对方设备编号
				String head = content.substring(0, pos);
				String body = content.substring(pos+1);
				String[] splitArray = head.split(",");
				String action = splitArray[0];
				System.out.println("action="+action);
				if (action.equals("LOGIN")) { // 登录动作
					login(splitArray[1], splitArray[2], splitArray[3]);
				} else if (action.equals("LOGOUT")) { // 注销动作
					logout(splitArray[1]);
					break;
				} else if (action.equals("SENDMSG")) { // 发送消息
					sendMsg("RECVMSG", splitArray[2], splitArray[4], splitArray[1], body);
				} else if (action.equals("GETLIST")) { // 获取好友列表
					getList(splitArray[1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 登录。保存客户端的设备编号、昵称、登录时间
	private void login(String deviceId, String nickName, String loginTime) throws IOException {
		for (int i=0; i<ChatServer.mSocketList.size(); i++) {
			SocketBean item = ChatServer.mSocketList.get(i);
			if (item.id.equals(mSocket.id)) {
				item.deviceId = deviceId;
				item.nickName = nickName;
				item.loginTime = loginTime;
				ChatServer.mSocketList.set(i, item);
				break;
			}
		}
	}
	
	// 把好友列表拼接成字符串
	private String getFriend() {
		String friends = "GETLIST,";
		for (SocketBean item : ChatServer.mSocketList) {
			if (item.deviceId!=null && item.deviceId.length()>0) {
				String friend = String.format("|%s,%s,%s", item.deviceId, item.nickName, item.loginTime);
				friends += friend;
			}
		}
		return friends;
	}
	
	// 获取好友列表
	private void getList(String deviceId) throws IOException {
		for (int i=0; i<ChatServer.mSocketList.size(); i++) {
			SocketBean item = ChatServer.mSocketList.get(i);
			if (item.id.equals(mSocket.id) && item.deviceId.equals(deviceId)) {
				PrintStream ps = new PrintStream(item.socket.getOutputStream());
				ps.println(getFriend());
				break;
			}
		}
	}
	
	// 注销
	private void logout(String deviceId) throws IOException {
		for (int i=0; i<ChatServer.mSocketList.size(); i++) {
			SocketBean item = ChatServer.mSocketList.get(i);
			if (item.id.equals(mSocket.id) && item.deviceId.equals(deviceId)) {
				item.socket.close();
				ChatServer.mSocketList.remove(i);
				break;
			}
		}
	}

	// 发送消息
	private void sendMsg(String respAction, String otherName, String otherId, String selfId, String message) throws IOException {
		for (int i=0; i<ChatServer.mSocketList.size(); i++) {
			SocketBean item = ChatServer.mSocketList.get(i);
			if (item.deviceId.equals(otherId)) { // 找到对方的设备编号，表示该item为对方的套接字
				String content = String.format("%s,%s,%s,%s|%s", 
						respAction, selfId, otherName, DateUtil.getNowTime(), message);
				System.out.println("resp="+content);
				PrintStream ps = new PrintStream(item.socket.getOutputStream());
				ps.println(content);
				break;
			}
		}
	}

}