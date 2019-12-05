package com.io.bio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//演示对象输入输出流的用法
public class TestSerialize {
	private static String mFileName = "E:/test/user.txt";
	
	public static void main(String[] arg) {
		writeObject(); // 把序列化对象写入文件
		readObject(); // 从文件中读取序列化对象
	}
	
	// 利用对象输出流把序列化对象写入文件
	private static void writeObject() {
		UserInfo user = new UserInfo(); // 创建可序列化的用户信息对象
		user.setName("王五");
		user.setPhone("15960238696");
		user.setPassword("111111");
		// 根据指定文件路径构建文件输出流对象，然后据此构建对象输出流对象
		try (FileOutputStream fos = new FileOutputStream(mFileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(user); // 把对象信息写入文件
			System.out.println("对象序列化成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 利用对象输入流从文件中读取序列化对象
	private static void readObject() {
		UserInfo user = new UserInfo(); // 创建可序列化的用户信息对象
		// 根据指定文件路径构建文件输入流对象，然后据此构建对象输入流对象
		try (FileInputStream fos = new FileInputStream(mFileName);
				ObjectInputStream ois = new ObjectInputStream(fos);) {
			user = (UserInfo) ois.readObject(); // 从文件读取对象信息
			System.out.println("对象反序列化成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 如果用户信息的密码字段未作特殊处理，则文件读到的密码字段为明文
		// 如果用户信息的密码字段设置了禁止序列化，则文件读到的密码字段为空
		String desc = String.format("姓名=%s,手机号=%s,密码=%s", 
				user.getName(), user.getPhone(), user.getPassword());
		System.out.println("用户信息如下："+desc);
	}

}
