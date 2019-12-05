package com.concurrent.lock;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import com.concurrent.PrintUtils;

//定义一个买票的任务
public class BuyTicket implements Runnable {
	public final static int FULL_PAITIENCE = 1; // 极有耐心
	public final static int SOME_PAITIENCE = 2; // 有些耐心
	public final static int LACK_PAITIENCE = 3; // 缺少耐心
	public final static int ACCEPT_INTERRUPT = 4; // 接受中断
	private Semaphore semaphore; // 信号量
	private int person_type; // 用户类型
	
	public BuyTicket(Semaphore semaphore, int person_type) {
		this.semaphore = semaphore;
		this.person_type = person_type;
	}

	@Override
	public void run() {
		if (person_type == FULL_PAITIENCE) { // 极有耐心的旅客
			// 尝试向信号量申请许可证，并且不接受中断。
			// 如果信号量无空闲许可证，那么愿意继续等待直到获得许可证。
			semaphore.acquireUninterruptibly();
			wait_a_moment(); // 稍等一会儿
			PrintUtils.print(Thread.currentThread().getName(), "买到票啦");
			semaphore.release(); // 释放信号量资源
		} else if (person_type == SOME_PAITIENCE) { // 有些耐心的旅客
			try {
				// 尝试向信号量申请许可证，但只愿意等待80毫秒。
				// 如果在规定时间内获得许可证就返回true，如果未获得许可证就返回false。
				boolean result = semaphore.tryAcquire(80, TimeUnit.MILLISECONDS);
				if (result) { // 已获得许可证
					wait_a_moment(); // 稍等一会儿
					PrintUtils.print(Thread.currentThread().getName(), "买到票啦");
				} else { // 未获得许可证
					PrintUtils.print(Thread.currentThread().getName(), "等太久，不买票了");
				}
			} catch (InterruptedException e) { // 等待期间接受中断
				e.printStackTrace();
			} finally {
				semaphore.release(); // 释放信号量资源
			}
		} else if (person_type == LACK_PAITIENCE) { // 缺少耐心的旅客
			// 尝试向信号量立即申请许可证，哪怕1毫秒都不愿意等待。
			// 获得许可证就返回true，未获得许可证就返回false。
			boolean result = semaphore.tryAcquire();
			if (result) { // 已获得许可证
				wait_a_moment(); // 稍等一会儿
				PrintUtils.print(Thread.currentThread().getName(), "买到票啦");
			} else { // 未获得许可证
				PrintUtils.print(Thread.currentThread().getName(), "一会都不想等，不买票了");
			}
			semaphore.release(); // 释放信号量资源
		} else if (person_type == ACCEPT_INTERRUPT) { // 接受中断的旅客。一边排队一边约顺风车
			try {
				// 尝试向信号量申请许可证，并且接受中断。
				// 如果信号量无空闲许可证，那么愿意继续等待，但收到中断信号除外。
				semaphore.acquire();
				wait_a_moment(); // 稍等一会儿
				PrintUtils.print(Thread.currentThread().getName(), "买到票啦");
			} catch (InterruptedException e) { // 收到了顺风车接单的通知
				PrintUtils.print(Thread.currentThread().getName(), "约到顺风车，不买票了");
			} finally {
				semaphore.release(); // 释放信号量资源
			}
		}
	}
	
	// 稍等一会儿，模拟窗口买票的时间消耗
	public static void wait_a_moment() {
		int delay = new Random().nextInt(100); // 生成100以内的随机整数
		try {
			Thread.sleep(delay); // 睡眠若干毫秒
		} catch (InterruptedException e2) {
		}
	}

}
