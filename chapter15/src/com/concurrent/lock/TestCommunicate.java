package com.concurrent.lock;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.concurrent.PrintUtils;

//演示如何在线程间通信
public class TestCommunicate {

	public static void main(String[] args) {
		//testWaitNotify(); // 测试通过wait和notify方法进行线程间通信
		testCondition(); // 测试通过Condition对象进行线程间通信
	}

	private static Integer salary = 5000; // 员工与老板之间通过工资条通信
	
	// 测试通过wait和notify方法进行线程间通信
	private static void testWaitNotify() {
		// 创建雇员的工作任务
		Runnable employee = new Runnable() {
			@Override
			public void run() {
				PrintUtils.print(Thread.currentThread().getName(), "等着发工资。");
				synchronized (salary) { // 工资是我的，你们别抢
					try {
						salary.wait(); // 等待发工资
						// 打印拿到工资后的庆祝日志
						PrintUtils.print(Thread.currentThread().getName(), "今晚赶紧吃大餐。");
					} catch (InterruptedException e) { // 等待期间允许接收中断信号
						e.printStackTrace();
					}
				}
			}
		};
		// 创建雇主的工作任务
		Runnable boss = new Runnable() {
			@Override
			public void run() {
				// 老板线程的同步代码务必在员工线程的同步代码后运行，否则员工线程将一直等待
				wait_a_moment(); // 稍等一会儿
				PrintUtils.print(Thread.currentThread().getName(), "开始发工资。");
				synchronized (salary) { // 由我发工资，你们别闹
					wait_a_moment(); // 银行转账也需要时间
					//salary.notify(); // 随机通知其中一个等待线程
					salary.notifyAll(); // 通知所有的等待线程
					// 手好酸，发工资也是个体力活，记个账
					PrintUtils.print(Thread.currentThread().getName(), "发完工资了。");
				}
			}
		};
		new Thread(employee, "同步机制的员工").start(); // 启动员工等工资的线程
		new Thread(boss, "同步机制的老板").start(); // 启动老板发工资的线程
	}

	// 稍等一会儿，模拟日常事务的时间消耗
	private static void wait_a_moment() {
		int delay = new Random().nextInt(500); // 生成500以内的随机整数
		try {
			Thread.sleep(delay); // 睡眠若干毫秒
		} catch (InterruptedException e) {
		}
	}

	private final static ReentrantLock reentrantLock = new ReentrantLock(); // 创建一个可重入锁
	private static Condition condition = reentrantLock.newCondition(); // 获取可重入锁的条件对象
	
	// 测试通过Condition对象进行线程间通信
	private static void testCondition() {
		// 创建雇员的工作任务
		Runnable employee = new Runnable() {
			@Override
			public void run() {
				PrintUtils.print(Thread.currentThread().getName(), "等着发工资。");
				reentrantLock.lock(); // 对可重入锁加锁
				try {
					condition.await(); // 这里在等待条件对象的信号
					// 打印拿到工资后的庆祝日志
					PrintUtils.print(Thread.currentThread().getName(), "今晚赶紧吃大餐。");
				} catch (InterruptedException e) { // 等待期间允许接收中断信号
					e.printStackTrace();
				}
				reentrantLock.unlock(); // 对可重入锁解锁
			}
		};
		// 创建雇主的工作任务
		Runnable boss = new Runnable() {
			@Override
			public void run() {
				// 老板线程的加锁务必在员工线程的加锁之后执行，否则员工线程将一直等待
				wait_a_moment(); // 稍等一会儿
				PrintUtils.print(Thread.currentThread().getName(), "开始发工资。");
				reentrantLock.lock(); // 对可重入锁加锁
				wait_a_moment(); // 银行转账也需要时间
				condition.signal(); // 给条件对象发送信号
				// 手好酸，发工资也是个体力活，记个账
				PrintUtils.print(Thread.currentThread().getName(), "发完工资了。");
				reentrantLock.unlock(); // 对可重入锁解锁
			}
		};
		new Thread(employee, "加锁机制的员工").start(); // 启动员工等工资的线程
		new Thread(boss, "加锁机制的老板").start(); // 启动老板发工资的线程
	}
	
}
