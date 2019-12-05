package com.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

//演示字节缓存的用法
public class TestBuffer {
	private static String mSrcName = "E:/test/aah.txt";
	private static String mDestName = "E:/test/aah_copy.txt";
	private static String mLargeFile = "F:/StudioProjects/android2-master.zip";
	
	public static void main(String[] arg) {
		writeChannel(); // 通过文件通道写入文件
		readChannel(); // 通过文件通道读取文件
		//copyChannelBuffer(); // 使用文件通道和字节缓存复制文件
		copyChannelDirect(); // 使用文件通道直接复制文件
		//readLargeFileByByte(); // 使用普通的字节缓存读取大文件
		readLargeFileByMap(); // 使用映射的字节缓存读取大文件
	}

	// 通过文件通道写入文件
	private static void writeChannel() {
		String str = "春眠不觉晓，处处闻啼鸟。\n夜来风雨声，花落知多少。";
		// 根据文件输出流获得可写的文件通道。注意文件通道支持try(...)的自动关闭操作
		try (FileChannel channel = new FileOutputStream(mSrcName).getChannel()) {
			// 生成字符串对应的字节缓存对象
			ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
			channel.write(buffer); // 往文件通道写入字节缓存
			//buffer.put(str.getBytes()); // 往字节缓存的缓冲区写入字节数组
			//buffer.clear(); // 缓冲区数据写入通道之后，如果还想继续使用缓冲区，就要调用clear方法清空它
			//buffer.compact(); // 只清除已经读过的数据，剩余的未读数据会移到缓冲区开头，新数据将加到未读数据后面
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 通过文件通道读取文件
	private static void readChannel() {
		// 根据文件输入流获得可读的文件通道。注意文件通道支持try(...)的自动关闭操作
		try (FileChannel channel = new FileInputStream(mSrcName).getChannel()) {
			int size = (int) channel.size(); // 获取文件通道的大小（即文件长度）
			ByteBuffer buffer = ByteBuffer.allocateDirect(size); // 分配指定大小的字节缓存
			channel.read(buffer); // 把文件通道中的数据读到字节缓存
			String content = getStringFromBuffer(buffer); // 从字节缓存中获取字符串
			System.out.println("content="+content);
//			buffer.rewind(); // 让缓冲区的指针回到开头，以便重新再来一遍
//			buffer.mark(); // 在字节缓存的当前位置做个标记
//			buffer.reset(); // 重置字节缓存的指针，令其回到上次标记的位置
//			buffer.remaining(); // 获取字节缓存的未读数据大小
//			buffer.position(); // 获取字节缓存的当前位置
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 从字节缓存中获取字符串
	private static String getStringFromBuffer(ByteBuffer buffer) {
		buffer.flip(); // 把缓冲区切换到读模式。从缓冲区读取数据之前，必须先调用flip方法
		int size = buffer.remaining(); // 获取字节缓存的未读数据大小
		byte[] bytes = new byte[size]; // 创建指定大小的字节数组
		buffer.get(bytes); // 把字节缓存中的数据取到字节数组
		String content = new String(bytes); // 把字节数组转换为字符串
		return content;
	}

	// 使用文件通道和字节缓存复制文件
	private static void copyChannelBuffer() {
		// 分别创建源文件的文件通道，以及目标文件的文件通道
		try (FileChannel src = new FileInputStream(mSrcName).getChannel();
				FileChannel dest = new FileOutputStream(mDestName).getChannel()) {
			int size = (int) src.size(); // 获取源文件的大小
			ByteBuffer buffer = ByteBuffer.allocateDirect(size); // 分配指定大小的字节缓存
			src.read(buffer); // 把源文件中的数据读到字节缓存
			buffer.flip(); // 从缓冲区读取数据之前，必须先调用flip方法
			dest.write(buffer); // 把字节缓存中的数据写入目标文件
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 使用文件通道直接复制文件
	private static void copyChannelDirect() {
		// 分别创建源文件的文件通道，以及目标文件的文件通道
		try (FileChannel src = new FileInputStream(mSrcName).getChannel();
				FileChannel dest = new FileOutputStream(mDestName).getChannel();) {
			// 下面的transferTo和transferFrom都可以完成文件复制功能，选择其中一个即可
			src.transferTo(0, src.size(), dest); // 操作源文件通道，把数据传给目标文件通道
			//dest.transferFrom(src, 0, src.size()); // 操作目标文件通道，从源文件通道传入数据
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 使用普通的字节缓存读取大文件
	private static void readLargeFileByByte() {
		// 根据文件输入流获得可读的文件通道
		try (FileChannel channel = new FileInputStream(mLargeFile).getChannel()) {
			int size = (int) channel.size(); // 获取文件通道的大小（即文件长度）
			long timeBegin = System.currentTimeMillis(); // 获取开始时间
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(size); // 分配指定大小的字节缓存
			channel.read(byteBuffer); // 把文件通道中的数据读到字节缓存
			byteBuffer.flip(); // 把缓冲区从写模式切换到读模式
			byte[] bytes = new byte[size]; // 创建与文件大小相同长度的字节数组
			byteBuffer.get(bytes); // 把字节缓存中的数据取到字节数组
			long timeEnd = System.currentTimeMillis(); // 获取结束时间
			long duration = timeEnd-timeBegin; // 计算文件读取的耗时
			System.out.println("byte duration="+duration);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 使用映射的字节缓存读取大文件
	private static void readLargeFileByMap() {
		// 根据文件输入流获得可读的文件通道
		try (FileChannel channel = new FileInputStream(mLargeFile).getChannel()) {
			int size = (int) channel.size(); // 获取文件通道的大小（即文件长度）
			long timeBegin = System.currentTimeMillis(); // 获取开始时间
			// 把文件通道指定大小的文件内容投影到映射字节缓存
			MappedByteBuffer mapBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
			byte[] bytes = new byte[size]; // 创建与文件大小相同长度的字节数组
			mapBuffer.get(bytes); // 把映射字节缓存中的数据取到字节数组
			long timeEnd = System.currentTimeMillis(); // 获取结束时间
			long duration = timeEnd-timeBegin; // 计算文件读取的耗时
			System.out.println("map duration="+duration);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
