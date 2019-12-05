package com.io.file;

import java.io.RandomAccessFile;

//演示RandomAccessFile随机访问文件的用法
public class TestRandom {
	private static String mAppendFileName = "E:/test/random_appendStr.txt";
	private static String mFixsizeFileName = "E:/test/random_fixsize.txt";
	
	public static void main(String[] arg) {
		appendStr(); // 往随机文件末尾追加字符串
		appendStr(); // 往随机文件末尾追加字符串
		appendStr(); // 往随机文件末尾追加字符串
		readContent(); // 读取随机文件的文件内容
		//fixSizeInsert(); // 往固定大小的随机文件中插入数据
	}
	
	// 往随机文件末尾追加字符串
	private static void appendStr() {
		// 创建指定路径的随机文件对象（可读写）。try(...)支持在处理完毕后自动关闭随机文件
		try (RandomAccessFile raf = new RandomAccessFile(mAppendFileName, "rw")) {
			long length = raf.length(); // 获取随机文件的长度（文件大小）
			raf.seek(length); // 定位到指定长度的位置
			String str = String.format("你好世界%.10f\n", Math.random());
			raf.write(str.getBytes()); // 往随机文件写入字节数组
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 读取随机文件的文件内容
	private static void readContent() {
		// 创建指定路径的随机文件对象（只读）。try(...)支持在处理完毕后自动关闭随机文件
		try (RandomAccessFile raf = new RandomAccessFile(mAppendFileName, "r")) {
			int length = (int) raf.length(); // 获取随机文件的长度（文件大小）
			byte[] bytes = new byte[length]; // 分配长度为文件大小的字节数组
			raf.read(bytes); // 把随机文件的文件内容读取到字节数组
			String content = new String(bytes); // 把字节数组转成字符串
			System.out.println("content="+content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 往固定大小的随机文件中插入数据
	private static void fixSizeInsert() {
		// 创建指定路径的随机文件对象（可读写）。try(...)支持在处理完毕后自动关闭随机文件
		try (RandomAccessFile raf = new RandomAccessFile(mFixsizeFileName, "rw")) {
			raf.setLength(1000); // 设置随机文件的长度（文件大小）
			for (int i=0; i<=2 ;i++) {
				raf.seek(i*200); // 定位到指定长度的位置
				String str = String.format("你好世界%.10f\n", Math.random());
				raf.write(str.getBytes()); // 往随机文件写入字节数组
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
