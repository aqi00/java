package com.concurrent.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.concurrent.PrintUtils;

//演示定时线程池的用法
public class TestScheduled {

	public static void main(String[] args) {
//		testSingleScheduleOnce(); // 测试延迟一次的单线程定时器
//		testSingleScheduleRate(); // 测试固定速率的单线程定时器
//		testSingleScheduleDelay(); // 测试固定延迟的单线程定时器
//		testMultiScheduleOnce(); // 测试延迟一次的多线程定时器
//		testMultiScheduleRate(); // 测试固定速率的多线程定时器
		testMultiScheduleDelay(); // 测试固定延迟的多线程定时器
	}

	// 测试延迟一次的单线程定时器
	private static void testSingleScheduleOnce() {
		// 创建延迟一次的单线程定时器
		ScheduledExecutorService pool = (ScheduledExecutorService) Executors.newSingleThreadScheduledExecutor();
		for (int i=0; i<5; i++) { // 循环开展5个调度
			Visit visit = new Visit("延迟一次的单线程定时器", i); // 创建一个参观任务
			// 命令线程池调度任务。延迟1秒后执行参观任务
			pool.schedule(visit, 1, TimeUnit.SECONDS);
		}
	}

	// 测试固定速率的单线程定时器
	private static void testSingleScheduleRate() {
		// 创建固定速率的单线程定时器
		ScheduledExecutorService pool = (ScheduledExecutorService) Executors.newSingleThreadScheduledExecutor();
		for (int i=0; i<5; i++) { // 循环开展5个调度
			Visit visit = new Visit("固定速率的单线程定时器", i); // 创建一个参观任务
			// 命令线程池调度任务。第一次延迟1秒后执行参观任务，以后每间隔3秒执行下一个任务
			pool.scheduleAtFixedRate(visit, 1, 3, TimeUnit.SECONDS);
		}
	}

	// 测试固定延迟的单线程定时器
	private static void testSingleScheduleDelay() {
		// 创建固定速率的单线程定时器
		ScheduledExecutorService pool = (ScheduledExecutorService) Executors.newSingleThreadScheduledExecutor();
		for (int i=0; i<5; i++) { // 循环开展5个调度
			Visit visit = new Visit("固定延迟的单线程定时器", i); // 创建一个参观任务
			// 命令线程池调度任务。第一次延迟1秒后执行参观任务，以后每3秒执行下一个任务
			pool.scheduleWithFixedDelay(visit, 1, 3, TimeUnit.SECONDS);
		}
	}

	// 测试延迟一次的多线程定时器
	private static void testMultiScheduleOnce() {
		// 创建延迟一次的多线程定时器（线程池大小为3）
		ScheduledExecutorService pool = (ScheduledExecutorService) Executors.newScheduledThreadPool(3);
		for (int i=0; i<5; i++) { // 循环开展5个调度
			Visit visit = new Visit("延迟一次的多线程定时器", i); // 创建一个参观任务
			// 命令线程池调度任务。延迟1秒后执行参观任务
			pool.schedule(visit, 1, TimeUnit.SECONDS);
		}
	}

	// 测试固定速率的多线程定时器
	private static void testMultiScheduleRate() {
		// 创建固定速率的多线程定时器（线程池大小为3）
		ScheduledExecutorService pool = (ScheduledExecutorService) Executors.newScheduledThreadPool(3);
		for (int i=0; i<5; i++) { // 循环开展5个调度
			// 创建一个参观任务
			Visit visit = new Visit("固定速率的多线程定时器", i);
			// 命令线程池调度任务。第一次延迟1秒执行参观任务，以后每间隔3秒执行下一个参观任务
			pool.scheduleAtFixedRate(visit, 1, 3, TimeUnit.SECONDS);
		}
	}

	// 测试固定延迟的多线程定时器
	private static void testMultiScheduleDelay() {
		// 创建固定速率的多线程定时器（线程池大小为3）
		ScheduledExecutorService pool = (ScheduledExecutorService) Executors.newScheduledThreadPool(3);
		for (int i=0; i<5; i++) { // 循环开展5个调度
			// 创建一个参观任务
			Visit visit = new Visit("固定延迟的多线程定时器", i);
			// 命令线程池调度任务。第一次延迟1秒执行参观任务，以后每3秒执行下一个参观任务
			pool.scheduleWithFixedDelay(visit, 1, 3, TimeUnit.SECONDS);
		}
	}

	// 定义一个参观任务
	private static class Visit implements Runnable {
		private String name; // 任务名称
		private int index; // 任务序号
		public Visit(String name, int index) {
			this.name = name;
			this.index = index;
		}
		
		@Override
		public void run() {
			// 以下打印操作日志，包括操作时间、操作线程、操作描述等信息
			String desc = String.format("%s的第%d个任务到此一游", name, index);
			PrintUtils.print(Thread.currentThread().getName(), desc);
		}
	};

}
