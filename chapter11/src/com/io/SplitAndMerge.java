package com.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

//演示如何分割文件与合并文件
public class SplitAndMerge {
	
	public static void main(String[] args) {
		splitFile("F:/test/aaa.rmvb", 10); // 把指定文件分割为若干段
		mergeFile("F:/test/aaa.rmvb", "F:/test/aaa2.rmvb"); // 把指定文件的各分段合并为新文件
	}
	
	// 把指定文件分割为若干段
	private static void splitFile(String srcName, long splitCount) {
		File file = new File(srcName); // 创建指定路径的文件对象
		if (!file.exists()) { // 如果该文件不存在，就直接返回
			return;
		}
		// 创建指定路径的随机文件对象（只读）
		try (RandomAccessFile raf = new RandomAccessFile(srcName, "r")) {
			long length = raf.length(); // 获取随机文件的长度（文件大小）
			System.out.println(srcName+"准备分割，源文件大小为"+length);
			int singleLength = (int) (length/splitCount + 1); // 计算单个分段的大小
			for (int i=0; i<splitCount; i++) { // 将源文件依次分割为若干段
				raf.seek(i*splitCount); // 定位到指定长度的位置
				String splitFile = String.format("%s.%03d", srcName, i); // 分段后的文件名
				// 根据指定路径构建文件输出流对象
				try (FileOutputStream fos = new FileOutputStream(splitFile)) {
					if (i == splitCount-1) { // 最后一个分段的文件大小要重新计算
						singleLength = (int)(length - singleLength*(splitCount-1));
					}
					byte[] bytes = new byte[singleLength]; // 分配指定长度的字节数组
					raf.read(bytes); // 把随机文件的文件内容读取到字节数组
					fos.write(bytes); // 把字节数组写入文件输出流
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(splitFile+"分割完毕，文件大小为"+singleLength);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 把指定文件的各分段合并为新文件
	private static void mergeFile(String srcName, String destName) {
		// 创建目标文件的文件通道
		try (FileChannel dest = new FileOutputStream(destName).getChannel()) {
			for (int i=0; i<1000; i++) { // 支持1000之内的分段数量
				String splitFile = String.format("%s.%03d", srcName, i);
				File file = new File(splitFile); // 创建指定路径的文件对象
				if (!file.exists()) { // 如果该分段不存在，就跳出循环
					break;
				}
				// 创建源文件（各分段）的文件通道
				try (FileChannel src = new FileInputStream(splitFile).getChannel()) {
					src.transferTo(0, src.size(), dest); // 操作源文件通道，把数据传给目标文件通道
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(splitFile+"合并完毕");
			}
			System.out.println(destName+"合并完成，新文件大小为"+dest.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
