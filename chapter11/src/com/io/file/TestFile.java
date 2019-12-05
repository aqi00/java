package com.io.file;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//演示File工具的基本用法
public class TestFile {
	
	public static void main(String[] arg) {
		checkFileStatus(); // 检查文件的状态
		getFileInfo(); // 获取文件的路径信息
		manageFile(); // 管理文件操作
	}
	
	// 检查文件的状态
	private static void checkFileStatus() {
		File file = new File("E:/test/aaa.txt"); // 创建一个指定路径的文件对象
		boolean exists = file.exists(); // 检查文件是否存在
		System.out.println("该文件(目录)"+(exists?"存在":"不存在"));
		if (!file.exists()) {
			return;
		}
		boolean canExecute = file.canExecute(); // 文件是否允许执行
		System.out.println("该文件"+(canExecute?"可以":"不可")+"执行");
		boolean canRead = file.canRead(); // 文件是否允许读取
		System.out.println("该文件"+(canRead?"可以":"不可")+"读取");
		boolean canWrite = file.canWrite(); // 文件是否允许写入
		System.out.println("该文件"+(canWrite?"可以":"不可")+"写入");
		boolean isHidden = file.isHidden(); // 是否为隐藏文件
		System.out.println("这个"+(isHidden?"是":"不是")+"隐藏文件");
		boolean isDirectory = file.isDirectory(); // 是否为目录
		System.out.println("这个"+(isDirectory?"是":"不是")+"目录");
		boolean isFile = file.isFile(); // 是否为文件
		System.out.println("这个"+(isFile?"是":"不是")+"文件");
	}

	// 获取文件的路径信息
	private static void getFileInfo() {
		File file = new File("E:/test/"); // 创建一个指定路径的文件对象
		if (!file.exists()) { // 检查文件是否存在
			System.out.println("该文件不存在");
			return;
		}
		String aAbsolutePath = file.getAbsolutePath(); // 获取该文件的绝对路径名称
		System.out.println("该文件的绝对路径是"+aAbsolutePath);
		String name = file.getName(); // 获取该文件的名称（不包含目录）
		System.out.println("该文件的名称是"+name);
		String parent = file.getParent(); // 获取该文件所在上级目录的完整路径
		System.out.println("该文件的上级目录是"+parent);
		String path = file.getPath(); // 获取该文件的路径名称
		System.out.println("该文件的路径是"+path);
		long length = file.length(); // 获取该文件的大小
		System.out.println("该文件的大小是"+length);
		long lastModified = file.lastModified(); // 获取该文件的最后修改时间
		Date date = new Date();
		date.setTime(lastModified);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 将当前日期时间按照指定格式输出格式化后的日期时间字符串
		String dateDesc = sdf.format(new Date());
		System.out.println("该文件的最后修改时间是："+dateDesc);
	}

	// 管理文件操作
	private static void manageFile() {
		File file = new File("E:/test2"); // 创建一个指定路径的文件对象
		try {
			file.createNewFile(); // 创建新文件
		} catch (IOException e) { // 如果文件路径中的目录不存在，就会扔出异常
			e.printStackTrace();
		}
		// 删除文件，也可删除空目录，但不可删除非空目录。在删除非空目录时会返回false
		boolean deleteResult = file.delete();
		System.out.println("该文件的删除结果为"+(deleteResult?"成功":"失败"));
		// 只创建最后一级目录，如果上级目录不存在就返回false
		boolean mkdirResult = file.mkdir();
		System.out.println("该目录的创建结果为"+(mkdirResult?"成功":"失败"));
//		// 创建文件路径中所有不存在的目录
//		boolean mkdirsResult = file.mkdirs();
//		// 文件重命名，把源文件的名称改为目标名称
//		boolean renameResult = file.renameTo(dest);
	}

}
