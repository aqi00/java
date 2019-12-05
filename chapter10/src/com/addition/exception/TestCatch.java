package com.addition.exception;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//演示异常的扔出与捕捉
public class TestCatch {

	public static void main(String[] args) throws ParseException {
		getDateFromFormat(); // 按照指定格式解析日期
		// getDateWithCatch(); // 在解析日期时捕捉异常
		// testDateByAdd(); // 进行日期的添加测试
		// testArrayByIndexWithNull(); // 进行数组的下标访问测试（数组为空）
		// testArrayByIndexWithOut(); // 进行数组的下标访问测试（下标越界）
		// testArrayByIndexWithAny(); // 进行数组的下标访问测试（捕获所有异常）
	}

	// 解析异常：指定日期不是真实的日子
	// java.text.ParseException: Unparseable date: "2021/02/28"
	// ParseException属于编译时异常，在编码时就要处理，否则无法编译通过。
	// 处理方式有两种：一种是往外丢异常，另一种是通过try...catch...语句捕捉异常
	private static void getDateFromFormat() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = "2021/02/28";
		Date date = sdf.parse(strDate);
	}

	// 通过try...catch...语句捕捉日期的解析异常
	// java.text.ParseException: Unparseable date: "2021/02/28"
	private static void getDateWithCatch() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = "2021/02/28";
		try { // 开始小心翼翼地尝试，随时准备捕捉异常
			Date date = sdf.parse(strDate);
		} catch (ParseException e) { // 捕捉到了解析异常
			e.printStackTrace(); // 打印出错时的栈轨迹信息
		}
	}

	// 日期的添加测试
	private static void testDateByAdd() {
		String firstDay = addSomeDays("2020-01-31", 3);
		System.out.println("firstDay=" + firstDay);
		String secondDay = addSomeDays("2020/02/31", 3);
		System.out.println("secondDay=" + secondDay);
	}

	// 给指定日期加上若干天。如果日期解析失败，则自动用当前日期代替
	private static String addSomeDays(String strDate, int number) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try { // 开始小心翼翼地尝试，随时准备捕捉异常
			date = sdf.parse(strDate);
		} catch (ParseException e) { // 捕捉到了解析异常
			// e.printStackTrace();
			date = new Date();
		} finally { // 无论是否发生异常，都要执行最终的代码块
			if (date == null) {
				date = new Date();
			}
			long time = date.getTime() + number * 24 * 60 * 60 * 1000;
			date.setTime(time);
		}
		return sdf.format(date);
	}

	// 数组的下标访问测试（数组为空）
	private static void testArrayByIndexWithNull() {
		int[] array = null;
		try { // 开始小心翼翼地尝试，随时准备捕捉异常
			int item = getItemByIndex(array, 3); // 根据下标获取指定数组对应位置的元素
			System.out.println("item=" + item);
		} catch (ArrayIsNullException e) { // 捕捉到了数组为空异常
			e.printStackTrace(); // 打印出错时的栈轨迹信息
		} catch (ArrayOutOfException e) { // 捕捉到了下标越界异常
			e.printStackTrace(); // 打印出错时的栈轨迹信息
		}
	}

	// 数组的下标访问测试（下标越界）
	private static void testArrayByIndexWithOut() {
		int[] array = { 1, 2, 3 };
		try { // 开始小心翼翼地尝试，随时准备捕捉异常
			int item = getItemByIndex(array, 3); // 根据下标获取指定数组对应位置的元素
			System.out.println("item=" + item);
		} catch (ArrayIsNullException | ArrayOutOfException e) { // 捕捉到了数组为空异常或下标越界异常
			e.printStackTrace(); // 打印出错时的栈轨迹信息
		}
	}

	// 数组的下标访问测试（捕获所有异常）
	private static void testArrayByIndexWithAny() {
		int[] array = null;
		try { // 开始小心翼翼地尝试，随时准备捕捉异常
			int item = getItemByIndex(array, 3); // 根据下标获取指定数组对应位置的元素
			System.out.println("item=" + item);
		} catch (Exception e) { // 捕捉到了任何一种异常
			e.printStackTrace(); // 打印出错时的栈轨迹信息
		}
	}

	// 根据下标获取指定数组对应位置的元素
	private static int getItemByIndex(int[] array, int index)
			throws ArrayIsNullException, ArrayOutOfException { // 同时扔出了多个异常
		if (array == null) { // 如果数组为空
			throw new ArrayIsNullException("这是个空数组"); // 就扔出数组为空异常
		} else if (index < 0 || index >= array.length) { // 如果下标超出了数组范围
			throw new ArrayOutOfException("下标超出了数组范围"); // 就扔出数组越界异常
		}
		return array[index];
	}

}
