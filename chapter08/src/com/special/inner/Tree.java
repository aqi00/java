package com.special.inner;

//演示内部类的简单定义。这是一个树木类
public class Tree {
	private String tree_name; // 树木名称

	public Tree(String tree_name) {
		this.tree_name = tree_name;
	}

	public void sprout() {
		System.out.println(tree_name + "发芽啦");
		// 外部类访问它的内部类，就像访问其它类一样，都要先创建类的实例，再访问它的成员
		Flower flower = new Flower("花朵");
		flower.bloom(); // 调用花儿对象的开花方法
	}

	// Flower类位于Tree类的内部，它是个内部类
	public class Flower {
		private String flower_name; // 花朵名称

		public Flower(String flower_name) {
			this.flower_name = flower_name;
		}

		public void bloom() {
			System.out.println(flower_name + "开花啦");
		}
	}

}
