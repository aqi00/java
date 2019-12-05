package com.concurrent.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.concurrent.PrintUtils;

//演示普通线程池的用法
public class TestExecutor {

	public static void main(String[] args) {
//		testSinglePool(); // 测试单线程的线程池
//		testFixedPool(); // 测试固定数量的线程池
//		testUnlimitPool(); // 测试无限数量的线程池
		testCustomPool(); // 测试自定义的线程池
	}

	// 测试单线程的线程池
	private static void testSinglePool() {
		// 创建只有一个线程的线程池
		ExecutorService pool = (ExecutorService) Executors.newSingleThreadExecutor();
		for (int i=0; i<10; i++) { // 循环启动10个任务
			Operation operation = new Operation("单线程的线程池", i); // 创建一个操作任务
			pool.execute(operation); // 命令线程池执行该任务
		}
		pool.shutdown(); // 关闭线程池
	}

	// 测试固定数量的线程池
	private static void testFixedPool() {
		// 创建线程数量为3的线程池
		ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
		for (int i=0; i<10; i++) { // 循环启动10个任务
			Operation operation = new Operation("固定数量的线程池", i); // 创建一个操作任务
			pool.execute(operation); // 命令线程池执行该任务
		}
		pool.shutdown(); // 关闭线程池
	}

	// 测试无限数量的线程池
	private static void testUnlimitPool() {
		// 创建不限制线程数量的线程池
		ExecutorService pool = (ExecutorService) Executors.newCachedThreadPool();
		for (int i=0; i<10; i++) { // 循环启动10个任务
			Operation operation = new Operation("无限数量的线程池", i); // 创建一个操作任务
			pool.execute(operation); // 命令线程池执行该任务
		}
		// 关闭线程池。关闭之后不能再往线程池中添加任务，不过要等已添加的任务执行完，才最终关掉线程池。
		pool.shutdown();
		// 立即关闭线程池。之后同样不能再往线程池中添加任务，同时会给已添加的任务发送中断信号，直到所有任务都退出才最终关掉线程池
//		pool.shutdownNow();
//		pool.isShutdown(); // 判断线程池是否已经关闭
//		pool.getCompletedTaskCount(); // 获取已完成的任务个数
//		pool.getTaskCount(); // 获取所有的任务个数
//		pool.getActiveCount(); // 获取活跃的线程个数
//		pool.getCorePoolSize(); // 获取核心的线程个数（即线程池的最小线程个数）
//		pool.getMaximumPoolSize(); // 获取最大的线程个数（即线程池的最大线程个数）
//		pool.getPoolSize(); // 获取线程池的当前大小（即线程池的当前线程个数）
//		pool.remove(task); // 从等待队列中移除指定任务
	}

	// 测试自定义的线程池
	private static void testCustomPool() {
		// 创建自定义规格的线程池（最小线程个数为2，最大线程个数为5，每个线程保持活跃的时长为60，时长单位秒，等待队列大小为19）
		ThreadPoolExecutor pool = new ThreadPoolExecutor(
				2, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(19));
		for (int i=0; i<60; i++) { // 循环启动10个任务
			Operation operation = new Operation("自定义的线程池", i); // 创建一个操作任务
			pool.execute(operation); // 命令线程池执行该任务
		}
		pool.shutdown(); // 关闭线程池
	}

	// 定义一个操作任务
	private static class Operation implements Runnable {
		private String name; // 任务名称
		private int index; // 任务序号
		public Operation(String name, int index) {
			this.name = name;
			this.index = index;
		}
		
		@Override
		public void run() {
			// 以下打印操作日志，包括操作时间、操作线程、操作描述等信息
			String desc = String.format("%s执行到了第%d个任务", name, index+1);
			PrintUtils.print(Thread.currentThread().getName(), desc);
		}
	};

}
