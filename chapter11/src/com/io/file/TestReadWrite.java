package com.io.file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//演示文件的读写操作（使用文件字符流）
public class TestReadWrite {
	private static String mFileName = "E:/test/aac.txt";
	
	public static void main(String[] arg) {
		writeFileSimple(); // 存在隐患的写文件代码
		//writeFileWithFinally(); // 改进后的写文件代码
		//writeFileWithTry(); // 采取自动释放资源的写文件代码
		//readFileSimple(); // 存在隐患的读文件代码
		//readFileWithFinally(); // 改进后的读文件代码
		readFileWithTry(); // 采取自动释放资源的读文件代码
	}

	// 存在隐患的写文件代码。发生异常时不会关闭文件
	private static void writeFileSimple() {
		String str = "白日依山尽，黄河入海流。\n";
		File file = new File(mFileName); // 创建一个指定路径的文件对象
		try {
			FileWriter writer = new FileWriter(file); // 创建一个文件写入器
			writer.write(str); // 往文件写入字符串
			//writer.append(null); // append方法会把空指针当作“null”写入文件，而write方法不支持写入空指针
			writer.close(); // 关闭文件
		} catch (IOException e) { // 捕捉到输入输出异常
			e.printStackTrace();
		}
	}

	// 改进后的写文件代码。在finally代码块中关闭文件
	private static void writeFileWithFinally() {
		String str = "白日依山尽，黄河入海流。\n";
		File file = new File(mFileName); // 创建一个指定路径的文件对象
		FileWriter writer = null;
		try {
			writer = new FileWriter(file); // 创建一个文件写入器
			writer.write(str); // 往文件写入字符串
		} catch (IOException e) { // 捕捉到输入输出异常
			e.printStackTrace();
		} finally { // 无论是否遇到异常，都要释放文件资源
			if (writer != null) {
				try {
					writer.close(); // 关闭文件
				} catch (IOException e) { // 捕捉到输入输出异常
					e.printStackTrace();
				}
			}
		}
	}

	// 采取自动释放资源的写文件代码
	private static void writeFileWithTry() {
		String str = "白日依山尽，黄河入海流。\n";
		File file = new File(mFileName); // 创建一个指定路径的文件对象
		// Java7的新增功能，在try(...)里声明的资源，会在try/catch结束后自动释放。
		// 相当于编译器自动补充了finally代码块中的资源释放操作。
		// 资源类必须实现java.lang.AutoCloseable接口，这样close方法才会由系统调用。
		// 一般说来，文件I/O、套接字、数据库连接等均已实现该接口。
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(str); // 往文件写入字符串
		} catch (IOException e) { // 捕捉到输入输出异常
			e.printStackTrace();
		}
	}

	// 存在隐患的读文件代码。发生异常时不会关闭文件
	private static void readFileSimple() {
		File file = new File(mFileName); // 创建一个指定路径的文件对象
		if (!file.exists() || !file.isFile()) {
			System.out.println("该文件不存在，或者它不是个文件");
			return;
		}
		try {
			FileReader reader = new FileReader(file); // 创建一个文件读取器
			//reader.skip(2); // 字符流的skip方法跳过的是字符数，不是字节数
			char[] temp = new char[(int) file.length()]; // 创建与文件大小等长的字符数组
			reader.read(temp); // 从文件读取数据到字节数组
			String content = new String(temp); // 把字符数组转为字符串
			System.out.println("content="+content);
			reader.close(); // 关闭文件
		} catch (IOException e) { // 捕捉到输入输出异常
			e.printStackTrace();
		}
	}

	// 改进后的读文件代码
	private static void readFileWithFinally() {
		File file = new File(mFileName); // 创建一个指定路径的文件对象
		if (!file.exists() || !file.isFile()) {
			System.out.println("该文件不存在，或者它不是个文件");
			return;
		}
		FileReader reader = null;
		try {
			reader = new FileReader(file); // 创建一个文件读取器
			char[] temp = new char[(int) file.length()]; // 创建与文件大小等长的字符数组
			reader.read(temp); // 从文件读取数据到字节数组
			String content = new String(temp); // 把字符数组转为字符串
			System.out.println("content="+content);
		} catch (IOException e) { // 捕捉到输入输出异常
			e.printStackTrace();
		} finally { // 无论是否遇到异常，都要释放文件资源
			if (reader != null) {
				try {
					reader.close(); // 关闭文件
				} catch (IOException e) { // 捕捉到输入输出异常
					e.printStackTrace();
				}
			}
		}
	}

	// 采取自动释放资源的读文件代码
	private static void readFileWithTry() {
		File file = new File(mFileName); // 创建一个指定路径的文件对象
		if (!file.exists() || !file.isFile()) {
			System.out.println("该文件不存在，或者它不是个文件");
			return;
		}
		// Java7的新增功能，在try(...)里声明的资源，会在try/catch结束后自动释放。
		// 相当于编译器自动补充了finally代码块中的资源释放操作。
		// 资源类必须实现java.lang.AutoCloseable接口，这样close方法才会由系统调用。
		// 一般说来，文件I/O、套接字、数据库连接等均已实现该接口。
		try (FileReader reader = new FileReader(file)) {
			char[] temp = new char[(int) file.length()]; // 创建与文件大小等长的字符数组
			reader.read(temp); // 从文件读取数据到字节数组
			String content = new String(temp); // 把字符数组转为字符串
			System.out.println("content="+content);
		} catch (IOException e) { // 捕捉到输入输出异常
			e.printStackTrace();
		}
	}

}
