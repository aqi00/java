package com.concurrent.pool;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

//演示归并排序的Fork/Join框架
public class TestForkJoinSort {
	private static int MAX = 10000000; // 整型数组的长度
	private static int inits[] = new int[MAX]; // 待排序的整型数组

	// 初始化一个长度为MAX的随机整数数组
	static {
		Random r = new Random();
		for(int index = 1 ; index <= MAX ; index++) {
			inits[index - 1] = r.nextInt(10000000);
		}
	}

	public static void main(String[] args) {
		testInternalTask(); // 测试任务自带的线程池框架
		//testPoolTask(); // 测试任务以外的线程池框架
	}

	// 测试任务自带的线程池框架
	private static void testInternalTask() {
		System.out.println("排序之前，第一个元素=" + inits[0] + ", 最后一个元素=" + inits[inits.length-1]);
		// 获取开始任务的时间
		long beginTime = System.currentTimeMillis();
		// 创建一个排序的递归任务
		SortTask task = new SortTask(inits);
		try {
			// 执行同步任务，并返回执行结果。任务的invoke方法使用了内部的ForkJoinPool
			int[] ends = task.invoke();
			System.out.println("排序之后，第一个元素=" + ends[0] + ", 最后一个元素=" + ends[ends.length-1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 获取结束任务的时间
		long endTime = System.currentTimeMillis();
		System.out.println("排序任务耗时=" + (endTime - beginTime) + "毫秒"); 
	}

	// 测试任务以外的线程池框架
	private static void testPoolTask() {
		System.out.println("排序之前，第一个元素=" + inits[0] + ", 最后一个元素=" + inits[inits.length-1]);
		// 获取开始任务的时间
		long beginTime = System.currentTimeMillis();
		// 创建一个排序的递归任务
		SortTask task = new SortTask(inits);
		// 创建一个用于分而治之的线程池
		ForkJoinPool pool = new ForkJoinPool();
		// 命令线程池执行排序任务，并返回存放执行结果的任务对象
		ForkJoinTask<int[]> taskResult = pool.submit(task);
		try {
			int[] ends = taskResult.get(); // 等待执行完成，并获取排序好的整型数组
			System.out.println("排序之后，第一个元素=" + ends[0] + ", 最后一个元素=" + ends[ends.length-1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pool.shutdown(); // 关闭线程池
		// 获取结束任务的时间
		long endTime = System.currentTimeMillis();
		System.out.println("排序任务耗时=" + (endTime - beginTime) + "毫秒"); 
	}

}
