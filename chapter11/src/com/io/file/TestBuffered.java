package com.io.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

//演示文件的读写操作（使用缓存字符流）
public class TestBuffered {
	private static String mSrcName = "E:/test/aad.txt"; // 源文件
	private static String mDestName = "E:/test/aad_copy.txt"; // 目标文件
	
	public static void main(String[] arg) {
		writeBuffer(); // 使用缓存字符流写入文件
		readBuffer(); // 使用缓存字符流读取文件
		copyFileByLine(); // 通过缓存字符流逐行复制文件
		//copyFileByInt(); // 通过缓存字符流逐个字符复制文件
	}

	// 使用缓存字符流写入文件
	private static void writeBuffer() {
		String str1 = "白日依山尽，黄河入海流。";
		String str2 = "欲穷千里目，更上一层楼。";
		File file = new File(mSrcName); // 创建一个指定路径的文件对象
		// try(...)允许在圆括号内部拥有多个资源创建语句，语句之间以冒号分隔
		// 先创建文件写入器，再根据文件读取器创建缓存写入器
		try (Writer writer = new FileWriter(file);
				BufferedWriter bwriter = new BufferedWriter(writer);) {
			// FileWriter的每次write调用都会直接写入磁盘，不但效率低，性能也差。
			// BufferedWriter的每次write调用会先写入缓冲区，直到缓冲区满了才写入磁盘，
			// 缓冲区大小默认是8K，查看源码defaultCharBufferSize = 8192;
			// 资源释放的close方法会把缓冲区的剩余数据写入磁盘，
			// 或者中途调用flush方法也可提前将缓冲区的数据写入磁盘。
			bwriter.write(str1); // 往文件写入字符串
			bwriter.newLine(); // 另起一行，即在文件末尾添加换行标记（Window系统是回车加换行）
			bwriter.write(str2);  // 往文件写入字符串
			//bwriter.flush(); // 把缓冲区中的数据写入磁盘
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 使用缓存字符流读取文件
	private static void readBuffer() {
		File file = new File(mSrcName); // 创建一个指定路径的文件对象
		if (!file.exists() || !file.isFile()) {
			System.out.println("该文件不存在，或者它不是个文件");
			return;
		}
		// try(...)允许在圆括号内部拥有多个资源创建语句，语句之间以冒号分隔
		// 先创建文件读取器，再根据文件读取器创建缓存读取器
		try (Reader reader = new FileReader(file);
				BufferedReader breader = new BufferedReader(reader);) {
			breader.mark((int) file.length()); // 做个标记
			for (int i=1; ; i++) { // 第一次读文件
				// FileReader只能一个字符一个字符地读，或者一次性读进字符数组。
				// BufferedReader还支持一行一行地读。
				String line = breader.readLine(); // 从文件中读出一行文字
				if (line == null) { // 读到了空指针，表示已经到了文件末尾
					break;
				}
				System.out.println("第"+i+"行的文字为："+line);
			}
			// 下面重头又读了一遍文件，目的是演示如何使用mark与reset方法
			breader.reset(); // 重置文件指针，令其回到上次标记的位置
			for (int i=1; ; i++) { // 第二次读文件
				String line = breader.readLine(); // 从文件中读出一行文字
				if (line == null) { // 读到了空指针，表示已经到了文件末尾
					break;
				}
				System.out.println("又读了一遍 第"+i+"行的文字为："+line);
			}
			//breader.lines(); // 返回Stream<String>对象，之后可按照流式处理来加工该字符串流
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 通过缓存字符流逐行复制文件
	private static void copyFileByLine() {
		File src = new File(mSrcName); // 创建一个指定路径的源文件对象
		File dest = new File(mDestName); // 创建一个指定路径的目标文件对象
		if (!src.exists() || !src.isFile()) {
			System.out.println("源文件不存在，或者它不是个文件");
			return;
		}
		// try(...)允许在圆括号内部拥有多个资源创建语句，语句之间以冒号分隔
		// 分别创建源文件的缓存读取器，以及目标文件的缓存写入器
		try (BufferedReader breader = new BufferedReader(new FileReader(src));
				BufferedWriter bwriter = new BufferedWriter(new FileWriter(dest));) {
			for (int i=0; ; i++) {
				String line = breader.readLine(); // 从文件中读出一行文字
				if (line == null) { // 读到了空指针，表示已经到了文件末尾
					break;
				}
				if (i != 0) { // 第一行开头不用换行
					bwriter.newLine(); // 另起一行，也就是在文件末尾添加换行标记
				}
				bwriter.write(line); // 往文件写入字符串
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("文件复制完成，源文件大小="+src.length()+"，新文件大小="+dest.length());
	}

	// 通过缓存字符流逐个字符复制文件
	private static void copyFileByInt() {
		File src = new File(mSrcName); // 创建一个指定路径的源文件对象
		File dest = new File(mDestName); // 创建一个指定路径的目标文件对象
		if (!src.exists() || !src.isFile()) {
			System.out.println("源文件不存在，或者它不是个文件");
			return;
		}
		// try(...)允许在圆括号内部拥有多个资源创建语句，语句之间以冒号分隔
		// 分别创建源文件的缓存读取器，以及目标文件的缓存写入器
		try (BufferedReader breader = new BufferedReader(new FileReader(src));
				BufferedWriter bwriter = new BufferedWriter(new FileWriter(dest));) {
			while (true) { // 开始遍历文件中的所有字符
				int temp = breader.read(); // 从源文件中读出一个字符
				if (temp == -1) { // read方法返回-1表示已经读到了文件末尾
					break;
				}
				bwriter.write(temp); // 往目标文件写入一个字符
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("文件复制完成，源文件大小="+src.length()+"，新文件大小="+dest.length());
	}

}
