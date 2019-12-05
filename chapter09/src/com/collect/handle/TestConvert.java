package com.collect.handle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//演示容器与数组的互相转换
public class TestConvert {
	
	public static void main(String[] args) {
		setToArray(); // 将集合对象转换为数组类型
		listToArray(); // 将清单对象转换为数组类型
		arrayToList(); // 将数组变量转换为清单类型
		//createFixedList(); // 创建固定不变的清单
		//createFixedSet(); // 创建固定不变的集合
		//createFixedMap(); // 创建固定不变的映射
	}

	// 将集合对象转换为数组类型
	private static void setToArray() {
		Set<String> fruitSet = new HashSet<String>();
		fruitSet.add("苹果");
		fruitSet.add("香蕉");
		fruitSet.add("西瓜");
		String[] fruitArray = new String[fruitSet.size()]; // 先声明与集合同样大小的数组变量
		fruitArray = fruitSet.toArray(fruitArray); // 再调用toArray方法将集合对象转换为数组类型
		for (String fruit : fruitArray) {
			System.out.println("集合转换，来自数组的水果="+fruit);
		}
	}

	// 将清单对象转换为数组类型
	private static void listToArray() {
		List<String> fruitList = new ArrayList<String>();
		fruitList.add("苹果");
		fruitList.add("香蕉");
		fruitList.add("西瓜");
		String[] fruitArray = new String[fruitList.size()]; // 先声明与清单同样大小的数组变量
		fruitArray = fruitList.toArray(fruitArray); // 再调用toArray方法将清单对象转换为数组类型
		for (String fruit : fruitArray) {
			System.out.println("清单转换，来自数组的水果="+fruit);
		}
	}
	
	// 将数组变量转换为清单类型
	private static void arrayToList() {
		// 方式一：先初始化数组变量，再调用Arrays工具的asList方法将数组变量转换为清单类型
		String[] fruitArray = new String[]{"苹果", "香蕉", "西瓜"};
		List<String> fruitList = Arrays.asList(fruitArray); // 将数组变量转换为清单类型
		// 方式二：直接在asList方法的输入参数中填写数组元素的列表
		//List<String> fruitList = Arrays.asList("苹果", "香蕉", "西瓜"); // 在asList方法中直接填数据列表
		// Arrays.asList得到的清单不能做添加和删除操作，否则会报错UnsupportedOperationException
		// 因为asList方法返回的对象类型是Arrays里面的嵌套类ArrayList，并非java.util.ArrayList
		//fruitList.add("梨"); // Arrays.ArrayList这个嵌套类没有实现add方法
		//fruitList.remove(0); // Arrays.ArrayList这个嵌套类没有实现remove方法
		for (String fruit : fruitList) {
			System.out.println("数组转换，来自清单的水果="+fruit);
		}
	}
	
	// 创建固定不变的清单
	private static void createFixedList() {
		// 通过List.of创建的固定清单，既不能添加和删除，也不能修改
		List<String> fruitList = List.of("苹果", "香蕉", "西瓜");
		//fruitList.add("梨"); // 固定清单调用add方法在运行时会报错
		//fruitList.set(0, "梨"); // 固定清单调用set方法在运行时也会报错
		//fruitList.remove(0); // 固定清单调用remove方法在运行时也会报错
		for (String fruit : fruitList) {
			System.out.println("来自固定清单的水果="+fruit);
		}
	}

	// 创建固定不变的集合
	private static void createFixedSet() {
		// 通过Set.of创建的固定集合，不能添加和删除
		Set<String> fruitSet = Set.of("苹果", "香蕉", "西瓜");
		//fruitSet.add("梨"); // 固定集合调用add方法在运行时会报错
		//fruitSet.remove("苹果"); // 固定集合调用remove方法在运行时也会报错
		for (String fruit : fruitSet) {
			System.out.println("来自固定集合的水果="+fruit);
		}
	}

	// 创建固定不变的映射
	private static void createFixedMap() {
		// 通过Map.of创建的固定映射，不能增删改
		Map<String, String> fruitMap = Map.of("苹果", "apple", "香蕉", "banana", "西瓜", "watermelon");
		//fruitMap.put("梨", "pear"); // 固定映射调用put方法在运行时会报错
		//fruitMap.remove("苹果"); // 固定映射调用remove方法在运行时也会报错
		for (Map.Entry<String, String> fruit : fruitMap.entrySet()) {
			System.out.println(String.format("来自固定映射的水果=%s %s",
					fruit.getKey(), fruit.getValue()));
		}
	}

}
