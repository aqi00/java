package com.concurrent.thread;

import com.concurrent.PrintUtils;

//演示如何利用Runnable启动线程
public class TestRunnable {

	public static void main(String[] args) {
		//testSignleTask(); // 测试一个线程执行一个任务
		testMultiTask(); // 测试多个线程执行同一任务
	}
	
	// 测试启动一个线程的任务
	private static void testSignleTask() {
		// 通过Runnable创建线程的第一种方式：传入普通实例
		FactorialTask task = new FactorialTask();
		new Thread(task).start(); // 创建并启动线程
		// 通过Runnable创建线程的第二种方式：传入匿名内部类的实例
		new Thread(new Runnable() {
			@Override
			public void run() {
				int product = 1;
				for (int i=1; i<=10; i++) { // 通过循环语句计算阶乘函数10!
					product *= i;
				}
				PrintUtils.print(Thread.currentThread().getName(), "阶乘结果="+product);
			}
		}).start(); // 创建并启动线程
		// 通过Runnable创建线程的第三种方式：使用Lambda表达式
		new Thread(() -> {
			int product = 1;
			for (int i=1; i<=10; i++) { // 通过循环语句计算阶乘函数10!
				product *= i;
			}
			PrintUtils.print(Thread.currentThread().getName(), "阶乘结果="+product);
		}).start(); // 创建并启动线程
	}
	
	// 定义一个求阶乘的任务
	private static class FactorialTask implements Runnable {
		@Override
		public void run() {
			int product = 1;
			for (int i=1; i<=10; i++) { // 通过循环语句计算阶乘函数10!
				product *= i;
			}
			PrintUtils.print(Thread.currentThread().getName(), "阶乘结果="+product);
		}
	}
	
	// 测试多个线程执行同一任务
	private static void testMultiTask() {
		//同一线程不能多次启动，否则重复调用会抛出异常。因为start是同步方法，不允许同一时刻多次运行
//		TicketThread seller = new TicketThread();
//		seller.start();
//		seller.start();
//		seller.start();
		
		// 创建多个线程分别启动，三个线程每个各卖100张，总共卖了300张票
//		new TicketThread("售票线程A").start();
//		new TicketThread("售票线程B").start();
//		new TicketThread("售票线程C").start();
		
		// 只创建一个售票任务，并启动三个线程一起执行售票任务，总共卖了100张票
		Runnable seller = new Runnable() {
			private int ticketCount = 100; // 可出售的车票数量
			@Override
			public void run() {
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

	// 单独定义一个售票线程
	private static class TicketThread extends Thread {
		private int ticketCount = 100; // 可出售的车票数量
		public TicketThread(String name) {
			setName(name); // 设置当前线程的名称
		}

		@Override
		public void run() {
			while (ticketCount > 0) { // 还有余票可供出售
				ticketCount--; // 余票数量减一
				// 以下打印售票日志，包括售票时间、售票线程、当前余票等信息
				String left = String.format("当前余票为%d张", ticketCount);
				PrintUtils.print(Thread.currentThread().getName(), left);
			}
		}
	}

}
