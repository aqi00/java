package com.concurrent.thread;

import com.concurrent.PrintUtils;

//演示线程类Thread的基本用法
//包括如何启动线程、如何调度线程、如何停止线程等
public class TestThread {

	public static void main(String[] args) {
		//testSimple(); // 测试线程的基本方法
		//testJoin(); // 测试线程的插队操作
		//testPriority(); // 测试线程的优先级顺序
		//testActiveCheck(); // 线程自己主动检查是否要停止运行
		testPassiveInterrupt(); // 线程被动接收外部的中断信号
	}
	
	// 测试线程的基本方法
	private static void testSimple() {
		CountThread thread = new CountThread(); // 创建一个计数器线程
		thread.start(); // 开始线程运行
//		thread.stop(); // 停止线程运行。该方法已过时，因为它不安全
//		thread.suspend(); // 暂停线程运行。该方法已过时，因为它不安全
//		thread.resume(); // 恢复线程运行。该方法已过时，因为它不安全
	}

	// 测试线程的插队操作
	private static void testJoin() {
		CountThread thread1 = new CountThread(); // 创建第一个计数器线程
		thread1.start(); // 第一个线程开始运行
		CountThread thread2 = new CountThread(); // 创建第二个计数器线程
		thread2.start(); // 第二个线程开始运行
		try {
			thread2.join(); // 第二个线程说：“我很着急，我要插队”
		} catch (InterruptedException e1) { // 插队行为可能会被中断，需要捕获中断异常
			e1.printStackTrace();
		}
	}

	// 测试线程的优先级顺序
	private static void testPriority() {
		CountThread thread1 = new CountThread(); // 创建第一个计数器线程
		thread1.setPriority(1); // 第一个线程的优先级为1
		thread1.start(); // 第一个线程开始运行
		CountThread thread2 = new CountThread(); // 创建第二个计数器线程
		thread2.setPriority(9); // 第二个线程的优先级为9，值越大优先级越高
		thread2.start(); // 第二个线程开始运行
	}

	// 线程自己主动检查是否要停止运行
	private static void testActiveCheck() {
		// 创建一个会自行检查运行标志的线程
		ActiveCheckThread thread = new ActiveCheckThread();
		thread.start(); // 开始线程运行
		try {
			Thread.sleep(50); // 睡眠50毫秒
		} catch (InterruptedException e) { // 睡眠可能会被打断，需要捕获中断异常
			e.printStackTrace();
		}
		thread.setCanRun(false); // 告知该线程不要再跑了，请择机退出
	}

	// 线程被动接收外部的中断信号
	private static void testPassiveInterrupt() {
		// 创建一个会接收外部中断信号的线程
		PassiveInterruptThread thread = new PassiveInterruptThread();
		thread.start(); // 开始线程运行
		try {
			Thread.sleep(100); // 睡眠50毫秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt(); // 不管你正在干什么，先停下来再说
	}
	
	// 定义一个计数器线程
	private static class CountThread extends Thread {
		@Override
		public void run() {
			for (int i=0; i<1000; i++) { // 一千次计数，并打印每次计数的日志
				// getName方法获取当前线程的名称，getId方法获取当前线程的编号
				PrintUtils.print(getName(), "当前计数值为"+i);
			}
		}
	}

	// 定义一个主动检查运行标志的线程
	private static class ActiveCheckThread extends Thread {
		private boolean canRun = true; // 能否运行的标志
		// 设置当前线程能否继续运行的标志
		public void setCanRun(boolean canRun) {
			this.canRun = canRun;
		}

		@Override
		public void run() {
			for (int i=0; i<1000; i++) {
				PrintUtils.print(getName(), "当前计数值为"+i);
				if (!canRun) { // 如果不允许运行，就打印停止运行的日志，并跳出线程的循环处理
					PrintUtils.print(getName(), "主动停止运行");
					break;
				}
			}
		}
	}

	// 定义一个被动接受中断信号的线程
	private static class PassiveInterruptThread extends Thread {
		@Override
		public void run() {
			try {
				for (int i=0; i<1000; i++) {
					PrintUtils.print(getName(), "当前计数值为"+i);
					Thread.sleep(10); // 睡眠10毫秒，睡眠期间允许接收中断信号
				}
			} catch (InterruptedException e) { // 收到了异常中断的信号，打印日志并退出线程运行
				PrintUtils.print(getName(), "被中断运行了");
			}
		}
	}

}
