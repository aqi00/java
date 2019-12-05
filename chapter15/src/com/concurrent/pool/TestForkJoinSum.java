package com.concurrent.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

//演示进行求和的Fork/Join框架
public class TestForkJoinSum {

	public static void main(String[] args) {
		testInternalTask(); // 测试任务自带的线程池框架
		//testPoolTask(); // 测试任务以外的线程池框架
	}
	
	// 测试任务自带的线程池框架
	private static void testInternalTask() {
		// 下面初始化从0到99的整型数组
		int[] arr = new int[100];
		for (int i = 0; i < 100; i++) {
			arr[i] = i + 1;
		}
		SumTask task = new SumTask(arr, 0, arr.length); // 创建一个求和的递归任务
		try {
			// 执行同步任务，并返回执行结果。任务的invoke方法使用了内部的ForkJoinPool
			Integer result = task.invoke();
			System.out.println("最终计算结果: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 测试任务以外的线程池框架
	private static void testPoolTask() {
		// 下面初始化从0到99的整型数组
		int[] arr = new int[100];
		for (int i = 0; i < 100; i++) {
			arr[i] = i + 1;
		}
		SumTask task = new SumTask(arr, 0, arr.length); // 创建一个求和的递归任务
		ForkJoinPool pool = new ForkJoinPool(6); // 创建一个用于分而治之的线程池，并发数量为6
		// 命令线程池执行求和任务，并返回存放执行结果的任务对象
		ForkJoinTask<Integer> taskResult = pool.submit(task);
		try {
			Integer result = taskResult.get(); // 等待执行完成，并获取求和的结果数值
			System.out.println("最终计算结果: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pool.shutdown(); // 关闭线程池
//		// 总结ForkJoinPool的三种task调用方式
//		pool.execute(task); // 异步执行指定任务，无返回值
//		pool.invoke(task); // 同步执行指定任务，并等待返回值，返回值就是最终的运算结果。
//		pool.submit(task); // 异步执行指定任务，且返回结果任务对象。之后可择机调用结果任务的get方法获取最终的运算结果
	}

}
