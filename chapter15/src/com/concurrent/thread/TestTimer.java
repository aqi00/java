package com.concurrent.thread;

import java.util.Timer;
import java.util.TimerTask;

import com.concurrent.PrintUtils;

//演示定时器与定时任务的用法
public class TestTimer {

	public static void main(String[] args) {
		//testScheduleOnce(); // 测试只跑一次的定时器调度
		testScheduleFixDelay(); // 测试固定间隔的定时器调度
		//testScheduleFixRate(); // 测试固定速率的定时器调度
	}

	// 测试只跑一次的定时器调度
	private static void testScheduleOnce() {
		CountTask timerTask = new CountTask(); // 创建一个计数的定时任务
		Timer timer = new Timer(); // 创建一个定时器
		timer.schedule(timerTask, 50); // 命令定时器启动定时任务。调度规则为：延迟50毫秒后启动
		try {
			Thread.sleep(1000); // 睡眠1秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.cancel(); // 取消定时器
	}
	
	// 测试固定间隔的定时器调度
	private static void testScheduleFixDelay() {
		CountTask timerTask = new CountTask(); // 创建一个计数的定时任务
		Timer timer = new Timer(); // 创建一个定时器
		// 命令定时器启动定时任务。
		// 调度规则为：延迟50毫秒后启动，且上一个任务执行完毕间隔100毫秒再执行下一个任务
		timer.schedule(timerTask, 50, 100);
		try {
			Thread.sleep(1000); // 睡眠1秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timerTask.cancel(); // 取消定时任务。取消原来的定时任务之后，还能通过定时器启动新任务
		//timer.cancel(); // 取消定时器。一旦取消了定时器，就不能再通过该定时器启动新任务
		CountTask timerTask2 = new CountTask(); // 创建新的定时任务
		timer.schedule(timerTask2, 50, 100); // 命令定时器启动新的定时任务
	}

	// 测试固定速率的定时器调度
	private static void testScheduleFixRate() {
		CountTask timerTask = new CountTask(); // 创建一个计数的定时任务
		Timer timer = new Timer(); // 创建一个定时器
		// 命令定时器启动定时任务。
		// 调度规则为：延迟50毫秒后启动，且之后每间隔100毫秒再执行一个任务
		timer.scheduleAtFixedRate(timerTask, 50, 100);
		try {
			Thread.sleep(1000); // 睡眠1秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.cancel(); // 取消定时器
	}

	// 定义一个用于计数的定时任务
	private static class CountTask extends TimerTask {
		private int count = 0; // 计数值

		@Override
		public void run() {
			// 以下打印计数日志，包括当前时间、当前线程、计数值等信息
			PrintUtils.print(Thread.currentThread().getName(), "当前计数值为"+count);
			count++;
		}
	}
	
}
