package com.concurrent.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.concurrent.PrintUtils;

//演示存在返回值的代码段
public class TestCallable {

	public static void main(String[] args) {
		//testCallInMain(); // 在主线程中运行Callable代码段
		testCallInThread(); // 在分线程中运行Callable代码段
	}
	
	// 在主线程中运行Callable代码段
	private static void testCallInMain() {
		// 定义一个Callable代码段，返回100以内的随机整数
		// 第一种方式：采取匿名内部类方式书写
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() { // 返回值为Integer类型
				int random = new Random().nextInt(100); // 获取100以内的随机整数
				// 以下打印随机数日志，包括当前时间、当前线程、随机数值等信息
				PrintUtils.print(Thread.currentThread().getName(), "任务生成的随机数="+random);
				return random;
			}
		};
		// 第二种方式：采取Lambda表达式书写
//		Callable<Integer> callable = () -> {
//			int random = new Random().nextInt(100); // 获取100以内的随机整数
//			PrintUtils.print(Thread.currentThread().getName(), "任务生成的随机数="+random);
//			return random;
//		};
		// 第三种方式：进一步精简后的Lambda表达式
//		Callable<Integer> callable = () -> new Random().nextInt(100);
		// 根据代码段实例创建一个未来任务
		FutureTask<Integer> future = new FutureTask<Integer>(callable);
		future.run(); // 运行未来任务
		try {
			Integer result = future.get(); // 获取未来任务的执行结果
			PrintUtils.print(Thread.currentThread().getName(), "主线程的执行结果="+result);
		} catch (InterruptedException | ExecutionException e) {
			// get方法会一直等到未来任务的执行完成，
			// 由于等待期间可能收到中断信号，因此这里得捕捉中断异常
			e.printStackTrace();
		}
	}

	// 在分线程中运行Callable代码段
	private static void testCallInThread() {
		// 定义一个Callable代码段，返回100以内的随机整数
		Callable<Integer> callable = new Callable<Integer>() {
			public Integer call() {
				int random = new Random().nextInt(100); // 获取100以内的随机整数
				// 以下打印随机数日志，包括当前时间、当前线程、随机数值等信息
				PrintUtils.print(Thread.currentThread().getName(), "任务生成的随机数="+random);
				return random;
			}
		};
		// 根据代码段实例创建一个未来任务
		FutureTask<Integer> future = new FutureTask<Integer>(callable);
		new Thread(future).start(); // 把未来任务放入新创建的线程中，并启动分线程处理
		try {
			Integer result = future.get(); // 获取未来任务的执行结果
			PrintUtils.print(Thread.currentThread().getName(), "主线程的执行结果="+result);
//			future.isDone(); // 判断未来任务是否执行完毕
//			future.cancel(true); // 取消未来任务
//			future.isCancelled(); // 判断未来任务是否取消
		} catch (InterruptedException | ExecutionException e) {
			// get方法会一直等到未来任务的执行完成，
			// 由于等待期间可能收到中断信号，因此这里得捕捉中断异常
			e.printStackTrace();
		}
	}

}
