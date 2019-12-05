package com.special.inner;

//演示嵌套类的定义
public class TreeNest {
	private String tree_name; // 树木名称

	public TreeNest(String tree_name) {
		this.tree_name = tree_name;
	}

	public void sprout() {
		System.out.println(tree_name + "发芽啦");
	}

	// Flower类虽然位于TreeNest类的里面，但是它被static修饰，故而与TreeNest类的关系比起一般的内部类要弱。
	// 为了与一般的内部类区别开来，这里的Flower类被叫做嵌套类。
	public static class Flower {
		private String flower_name; // 花朵名称

		public Flower(String flower_name) {
			this.flower_name = flower_name;
		}

		public void bloom() {
			System.out.println(flower_name + "开花啦");
		}

		public void bloomOuterTree() {
			// 注意下面的写法是错误的，嵌套类不能直接访问外层类的成员
			// System.out.println(TreeNest.this.tree_name+"的"+flower_name+"开花啦");
		}
	}

}
