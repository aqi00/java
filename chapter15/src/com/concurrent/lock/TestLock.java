package com.concurrent.lock;

import java.io.FileOutputStream;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import com.concurrent.PrintUtils;

//演示两种常用锁的用法
public class TestLock {
	private static String mFileName = "E:/test/aae.txt";

	public static void main(String[] args) {
		//testReentrantLock(); // 测试通过可重入锁避免资源冲突
		//testLockWithFile(); // 测试在加锁之前执行其它代码
		testReadWriteLock(); // 测试通过读写锁避免资源冲突
	}

	private final static ReentrantLock reentrantLock = new ReentrantLock(); // 创建一个可重入锁

	// 测试通过可重入锁避免资源冲突
	private static void testReentrantLock() {
		Runnable seller = new Runnable() {
			private Integer ticketCount = 100; // 可出售的车票数量
			
			@Override
			public void run() {
				while (ticketCount > 0) { // 还有余票可供出售
					reentrantLock.lock(); // 对可重入锁加锁
					int count = --ticketCount; // 余票数量减一
					reentrantLock.unlock(); // 对可重入锁解锁
//					reentrantLock.tryLock(); // 尝试加锁。加锁成功返回true，加锁失败返回false
//					reentrantLock.isLocked(); // 判断该锁是否被锁住了
//					reentrantLock.getQueueLength(); // 有多少个线程正在等待该锁的释放
					// 以下打印售票日志，包括售票时间、售票线程、当前余票等信息
					String left = String.format("当前余票为%d张", count);
					PrintUtils.print(Thread.currentThread().getName(), left);
				}
			}
		};
		new Thread(seller, "售票线程A").start(); // 启动售票线程A
		new Thread(seller, "售票线程B").start(); // 启动售票线程B
		new Thread(seller, "售票线程C").start(); // 启动售票线程C
	}

	// 测试在加锁之前执行其它代码
	private static void testLockWithFile() {
		Runnable seller = new Runnable() {
			private Integer ticketCount = 100; // 可出售的车票数量
			
			@Override
			public void run() {
				while (ticketCount > 0) { // 还有余票可供出售
					int count = 0;
					// 根据指定路径构建文件输出流对象
					try (FileOutputStream fos = new FileOutputStream(mFileName)) {
						reentrantLock.lock(); // 对可重入锁加锁
						count = --ticketCount; // 余票数量减一
						reentrantLock.unlock(); // 对可重入锁解锁
						fos.write(new String(""+count).getBytes()); // 把字节数组写入文件输出流
					} catch (Exception e) {
						e.printStackTrace();
					}
					// 以下打印售票日志，包括售票时间、售票线程、当前余票等信息
					String left = String.format("当前余票为%d张", count);
					PrintUtils.print(Thread.currentThread().getName(), left);
				}
			}
		};
		new Thread(seller, "售票线程A").start(); // 启动售票线程A
		new Thread(seller, "售票线程B").start(); // 启动售票线程B
		new Thread(seller, "售票线程C").start(); // 启动售票线程C
	}

	// 创建一个可重入的读写锁
	private final static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private final static WriteLock writeLock = readWriteLock.writeLock(); // 获取读写锁中的写锁
	private final static ReadLock readLock = readWriteLock.readLock(); // 获取读写锁中的读锁

	// 测试通过读写锁避免资源冲突
	private static void testReadWriteLock() {
		Runnable seller = new Runnable() {
			private Integer ticketCount = 100; // 可出售的车票数量
			
			@Override
			public void run() {
				while (ticketCount > 0) { // 还有余票可供出售
					int count = 0;
					// 根据指定路径构建文件输出流对象
					try (FileOutputStream fos = new FileOutputStream(mFileName)) {
						// 对读锁加锁。加了读锁之后，其它线程可以继续加读锁，但不能加写锁
						readLock.lock();
						if (ticketCount <= 0) { // 余票数量为0，表示已经卖光了，只好关门歇业
							fos.close(); // 关闭文件
							readLock.unlock(); // 对读锁解锁
							break; // 跳出售票的循环
						}
						readLock.unlock(); // 对读锁解锁
						// 对写锁加锁。一旦加了写锁，则其它线程在此既不能读也不能写
						writeLock.lock();
						count = --ticketCount; // 余票数量减一
						writeLock.unlock(); // 对写锁解锁
						fos.write(new String(""+count).getBytes()); // 把字节数组写入文件输出流
					} catch (Exception e) {
						e.printStackTrace();
					}
					// 以下打印售票日志，包括售票时间、售票线程、当前余票等信息
					String left = String.format("当前余票为%d张", count);
					PrintUtils.print(Thread.currentThread().getName(), left);
				}
			}
		};
		new Thread(seller, "售票线程A").start(); // 启动售票线程A
		new Thread(seller, "售票线程B").start(); // 启动售票线程B
		new Thread(seller, "售票线程C").start(); // 启动售票线程C
	}

}
