package com.concurrent.pool;

import java.util.concurrent.RecursiveTask;

//定义一个求和的递归任务
public class SumTask extends RecursiveTask<Integer> {
	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD = 20; // 不可再切割的元素个数门槛
	private int src[]; // 待求和的整型数组
	private int start; // 待求和的下标起始值
	private int end; // 待求和的下标终止值

	public SumTask(int[] src, int start, int end) {
		this.src = src;
		this.start = start;
		this.end = end;
	}

	// 对指定区间的数组元素求和
	private Integer subTotal() {
		Integer sum = 0;
		for (int i = start; i < end; i++) { // 求数组在指定区间的元素之和
			sum += src[i];
		}
		// 打印求和日志，包括当前线程的名称、起始数值、终止数值、区间之和
		String desc = String.format("%s 求和结果(%d到%d)=%d", 
				Thread.currentThread().getName(), start, end, sum);
		System.out.println(desc);
		return sum;
	}	

	@Override
	protected Integer compute() {
		if ((end - start) <= THRESHOLD) { // 不可再切割了
			return subTotal(); // 对指定区间的数组元素求和
		} else { // 区间过大，还能继续切割
			int middle = (start + end) / 2; // 计算区间中线的位置
			SumTask left = new SumTask(src, start, middle); // 创建左边分区的求和任务
			left.fork(); // 把左边求和任务添加到处理队列中
			SumTask right = new SumTask(src, middle, end); // 创建右边分区的求和任务
			right.fork(); // 把右边求和任务添加到处理队列中
			// 左边子任务的求和结果加上右边子任务的求和结果，等于当前任务的求和结果
			int sum = left.join() + right.join();
			// 打印求和日志，包括当前线程的名称、起始数值、终止数值、区间之和
			String desc = String.format("%s 求和结果(%d到%d)=%d", 
					Thread.currentThread().getName(), start, end, sum);
			System.out.println(desc);
			return sum; // 返回本次任务的求和结果
		}
	}
}
