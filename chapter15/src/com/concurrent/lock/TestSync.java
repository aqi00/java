package com.concurrent.lock;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.concurrent.PrintUtils;

//演示线程同步的处理
public class TestSync {

	public static void main(String[] args) {
		//testConflict(); // 多个线程同时操作某个资源，可能会产生冲突
		//testSyncMethod(); // 将多个线程都会执行的方法设置为同步方法
		testSyncBlock(); // 将引起资源冲突的代码块设置为同步代码块
		//testSyncMinMethod(); // 把操作共享资源的代码单独提取出来作为同步方法
	}
	
	// 多个线程同时操作某个资源，可能会产生冲突
	private static void testConflict() {
		// 创建一个售票任务
		Runnable seller = new Runnable() {
			private Integer ticketCount = 100; // 可出售的车票数量
			
			@Override
			public void run() {
				while (ticketCount > 0) { // 还有余票可供出售
					ticketCount--; // 余票数量减一
					// 以下打印售票日志，包括售票时间、售票线程、当前余票等信息
//					String left = String.format("当前余票为%d张", ticketCount);
//					PrintUtils.print(Thread.currentThread().getName(), left);
					// 为更好地重现资源冲突情况，下面尽量拉大访问ticketCount的时间间隔
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
					String dateTime = sdf.format(new Date());
					String desc = String.format("%s %s 当前余票为%d张", dateTime, 
							Thread.currentThread().getName(), ticketCount);
					System.out.println(desc);
				}
			}
		};
		new Thread(seller, "售票线程A").start(); // 启动售票线程A
		new Thread(seller, "售票线程B").start(); // 启动售票线程B
		new Thread(seller, "售票线程C").start(); // 启动售票线程C
	}

	// 将多个线程都会执行的方法设置为同步方法
	private static void testSyncMethod() {
		// 创建一个售票任务
		Runnable seller = new Runnable() {
			private Integer ticketCount = 100; // 可出售的车票数量
			
			// 指定整个run方法为同步方法，这样同一时刻只允许一个线程执行该方法
			public synchronized void run() {
				while (ticketCount > 0) { // 还有余票可供出售
					ticketCount--; // 余票数量减一
					// 以下打印售票日志，包括售票时间、售票线程、当前余票等信息
					String left = String.format("当前余票为%d张", ticketCount);
					PrintUtils.print(Thread.currentThread().getName(), left);
				}
			}
		};
		new Thread(seller, "售票线程A").start(); // 启动售票线程A
		new Thread(seller, "售票线程B").start(); // 启动售票线程B
		new Thread(seller, "售票线程C").start(); // 启动售票线程C
	}

	// 将引起资源冲突的代码块设置为同步代码块
	private static void testSyncBlock() {
		// 创建一个售票任务
		Runnable seller = new Runnable() {
			private Integer ticketCount = 100; // 可出售的车票数量
			
			@Override
			public void run() {
				while (ticketCount > 0) { // 还有余票可供出售
					int count;
					// 指定某个代码块为同步代码块，这样同一时刻只允许一个线程执行该段代码
					synchronized (this) {
						count = --ticketCount; // 余票数量减一
					}
					// 以下打印售票日志，包括售票时间、售票线程、当前余票等信息
					String left = String.format("当前余票为%d张", count);
					PrintUtils.print(Thread.currentThread().getName(), left);
				}
			}
		};
		new Thread(seller, "售票线程A").start(); // 启动售票线程A
		new Thread(seller, "售票线程B").start(); // 启动售票线程B
		new Thread(seller, "售票线程C").start(); // 启动售票线程C
	}

	// 把操作共享资源的代码单独提取出来作为同步方法
	private static void testSyncMinMethod() {
		// 创建一个售票任务
		Runnable seller = new Runnable() {
			private Integer ticketCount = 100; // 可出售的车票数量
			
			@Override
			public void run() {
				while (ticketCount > 0) { // 还有余票可供出售
					// 获得减一后的余票数量。注意getDecreaseCount是个同步方法
					int count = getDecreaseCount();
					// 以下打印售票日志，包括售票时间、售票线程、当前余票等信息
					String left = String.format("当前余票为%d张", count);
					PrintUtils.print(Thread.currentThread().getName(), left);
				}
			}
			
			// 将余票数量减一，并返回减后的余票数量
			private synchronized int getDecreaseCount() {
				return --ticketCount; // 余票数量减一
			}
		};
		new Thread(seller, "售票线程A").start(); // 启动售票线程A
		new Thread(seller, "售票线程B").start(); // 启动售票线程B
		new Thread(seller, "售票线程C").start(); // 启动售票线程C
	}

}
