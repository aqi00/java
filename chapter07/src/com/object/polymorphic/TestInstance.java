package com.object.polymorphic;

//演示如何检查某个实例属于哪种类
public class TestInstance {

	public static void main(String[] args) {
		// 创建了一个公鸡实例
		Chicken cock = new Cock();
		// 创建了一个母鸡实例
		Chicken hen = new Hen();
		// 以下通过属性字段type检查某实例的归属类
		checkType(cock);
		checkType(hen);
		// 以下通过类的基因检查某实例的归属类
		checkClass(cock);
		checkClass(hen);
		// 以下利用关键字instanceof检查某实例的归属类
		checkInstance(cock);
		checkInstance(hen);
	}

	// 通过属性字段type检查某实例的归属类
	private static void checkType(Chicken chicken) {
		if (chicken.sex == 0) { // 判断性别是否为雄性
			System.out.println("检查性别类型字段：这是只公鸡。");
		} else if (chicken.sex == 1) { // 判断性别是否为雌性
			System.out.println("检查性别类型字段：这是只母鸡。");
		} else {
			System.out.println("检查性别类型字段：这既不是公鸡也不是母鸡。");
		}
	}

	// 通过类的基因检查某实例的归属类
	private static void checkClass(Chicken chicken) {
		if (chicken.getClass().equals(Cock.class)) { // 判断这只鸡的DNA是不是公鸡DNA
			System.out.println("检查对象的类名：这是只公鸡。");
		} else if (chicken.getClass().equals(Hen.class)) { // 判断这只鸡的DNA是不是母鸡DNA
			System.out.println("检查对象的类名：这是只母鸡。");
		} else {
			System.out.println("检查对象的类名：这既不是公鸡也不是母鸡。");
		}
	}

	// 利用关键字instanceof检查某实例的归属类
	private static void checkInstance(Chicken chicken) {
		if (chicken instanceof Cock) { // 判断这只鸡是不是公鸡
			System.out.println("检查对象实例：这是只公鸡。");
		} else if (chicken instanceof Hen) { // 判断这只鸡是不是母鸡
			System.out.println("检查对象实例：这是只母鸡。");
		} else {
			System.out.println("检查对象实例：这既不是公鸡也不是母鸡。");
		}
	}

}
