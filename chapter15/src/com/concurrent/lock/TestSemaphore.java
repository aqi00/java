package com.concurrent.lock;

import java.util.concurrent.Semaphore;

import com.concurrent.PrintUtils;

//演示信号量的用法
public class TestSemaphore {

	public static void main(String[] args) {
		//testTwoTask(); // 测试两个旅客一起买票的场景
		testManyTask(); // 测试许多旅客一起买票的场景
	}
	
	// 测试两个旅客一起买票的场景
	private static void testTwoTask() {
		Semaphore semaphore = new Semaphore(1); // 创建拥有一个许可证的信号量
		// 创建一个排队买票的任务
		Runnable queueBuy = new Runnable() {
			@Override
			public void run() {
				try {
					// 尝试向信号量申请许可证，如果信号量无可用的许可证就一直等待
					semaphore.acquire();
					Thread.sleep(100); // 稍等一会儿
				} catch (InterruptedException e) {
				}
				PrintUtils.print(Thread.currentThread().getName(), "买到票啦");
				semaphore.release(); // 释放信号量资源
			}
		};
		// 创建一个立即买票的任务
		Runnable immediateBuy = new Runnable() {
			@Override
			public void run() {
				// 尝试向信号量立即申请许可证。成功返回true，失败返回false
				boolean result = semaphore.tryAcquire();
				if (result) { // 已获得许可证
					PrintUtils.print(Thread.currentThread().getName(), "买到票啦");
				} else { // 未获得许可证
					PrintUtils.print(Thread.currentThread().getName(), "一会都不想等，不买票了");
				}
				semaphore.release(); // 释放信号量资源
			}
		};
		new Thread(queueBuy, "愿意排队买车票的旅客").start(); // 启动排队买票线程
		new Thread(immediateBuy, "需要立即买到票的旅客").start(); // 启动立即买票线程
	}
	
	// 测试许多旅客一起买票的场景
	private static void testManyTask() {
		Semaphore semaphore = new Semaphore(3); // 创建拥有三个许可证的信号量
		// 一定要买到车票
		BuyTicket alwaysBuy = new BuyTicket(semaphore, BuyTicket.FULL_PAITIENCE);
		// 为了买到车票愿意排队一会儿，但要是等太久，就放弃买票
		BuyTicket awhileBuy = new BuyTicket(semaphore, BuyTicket.SOME_PAITIENCE);
		// 需要立即买到票，否则马上离开
		BuyTicket immediateBuy = new BuyTicket(semaphore, BuyTicket.LACK_PAITIENCE);
		// 先排队看看，如果有其它途径可以回家，就不用买票了
		BuyTicket caseBuy = new BuyTicket(semaphore, BuyTicket.ACCEPT_INTERRUPT);
		Thread[] caseThread = new Thread[5]; // 创建接受中断的排队买票线程数组
		for (int i=0; i<20; i++) { // 下面依次创建并启动20个买票线程
			if (i%4 == 0) { // 这些旅客一定要买到车票
				new Thread(alwaysBuy, "一定要买到车票的旅客").start(); // 启动买票线程A
			} else if (i%4 == 1) { // 这些旅客愿意排一会儿队
				new Thread(awhileBuy, "愿意排一会儿队的旅客").start(); // 启动买票线程B
			} else if (i%4 == 2) { // 这些旅客需要立即买到票
				new Thread(immediateBuy, "需要立即买到票的旅客").start(); // 启动买票线程C
			} else if (i%4 == 3) { // 这些旅客一边排队一边约顺风车
				// 创建一个接受中断的排队买票线程
				caseThread[i/4] = new Thread(caseBuy, "一边排队一边约顺风车的旅客");
				caseThread[i/4].start(); // 启动买票线程D
			}
		}
		BuyTicket.wait_a_moment(); // 稍等一会儿
		for (Thread thread : caseThread) { // 给一边排队一边约顺风车的买票线程们发送中断信号
			thread.interrupt(); // 发送中断通知，比如顺风车接单了等等
		}
	}
	
}
