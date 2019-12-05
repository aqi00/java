package com.io.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

//演示文件的筛选操作
public class TestFilter {
	private static String mPath = "E:/test/";
	
	public static void main(String[] arg) {
		selectAllFiles(); // 查找指定路径下的所有文件
		selectHiddenFiles(); // 查找指定路径下的隐藏文件
		selectTxtFiles(); // 查找指定路径下的文本文件
	}
	
	// 查找指定路径下的所有文件
	private static void selectAllFiles() {
		File path = new File(mPath); // 创建一个指定路径的文件对象
		if (!path.exists() || !path.isDirectory()) {
			System.out.println("该目录不存在，或者它不是个目录");
			return;
		}
		String[] files = path.list(); // list方法返回的是字符串数组
		//File[] files = path.listFiles(); // listFiles方法返回的是文件数组
		if (files != null) {
			for (int i=0; i<files.length; i++) {
				String filename = files[i];
				int pos = i+1;
				System.out.println("第"+pos+"个文件的名称是："+filename);
			}
		}
	}
	
	// 查找指定路径下的隐藏文件
	private static void selectHiddenFiles() {
		File path = new File(mPath); // 创建一个指定路径的文件对象
		if (!path.exists() || !path.isDirectory()) {
			System.out.println("该目录不存在，或者它不是个目录");
			return;
		}
		File[] hiddens; // 声明一个隐藏文件的文件数组
		// 匿名内部类的写法。通过文件过滤器FileFilter来筛选文件
		hiddens = path.listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.isHidden(); // 是隐藏文件
			}
		});
		hiddens = path.listFiles(file -> file.isHidden()); // Lambda表达式的写法
		hiddens = path.listFiles(File::isHidden); // 方法引用的写法
		if (hiddens != null) { // 打印所有隐藏文件的名称
			for (int i=0; i<hiddens.length; i++) {
				File file = hiddens[i];
				int pos = i+1;
				String filename = file.getName();
				System.out.println("第"+pos+"个隐藏文件的名称是："+filename);
			}
		}
	}

	// 查找指定路径下的文本文件
	private static void selectTxtFiles() {
		File path = new File(mPath); // 创建一个指定路径的文件对象
		if (!path.exists() || !path.isDirectory()) {
			System.out.println("该目录不存在，或者它不是个目录");
			return;
		}
		File[] txts; // 声明一个文本文件的文件数组
		// 匿名内部类的写法。通过文件名称过滤器FilenameFilter来筛选文件
		txts = path.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".txt"); // 文件扩展名为txt
			}
		});
		// Lambda表达式的写法
		txts = path.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
		if (txts != null) { // 打印所有文本文件的名称
			for (int i=0; i<txts.length; i++) {
				File file = txts[i];
				int pos = i+1;
				String filename = file.getName();
				System.out.println("第"+pos+"个文本文件的名称是："+filename);
			}
		}
	}

}
