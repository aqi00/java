package com.special.inner;

//演示内部类如何访问外部类的成员
public class TreeInner {
	private String tree_name;

	public TreeInner(String tree_name) {
		this.tree_name = tree_name;
	}

	public void sprout() {
		System.out.println(tree_name + "发芽啦");
	}

	// Flower类位于TreeInner类的内部，它是个内部类
	public class Flower {
		private String flower_name;
		private String tree_name;

		public Flower(String flower_name) {
			this.flower_name = flower_name;
		}

		public Flower(String tree_name, String flower_name) {
			this.tree_name = tree_name;
			this.flower_name = flower_name;
		}

		// 该方法访问默认的flower_name字段
		public void bloom() {
			System.out.println(flower_name + "开花啦");
		}

		// 该方法访问内部类自身的tree_name字段
		public void bloomInnerTree() {
			// 内部类里面的this关键字指代内部类自身
			System.out.println(this.tree_name + "的" + flower_name + "开花啦");
		}

		// 该方法访问外部类TreeInner的tree_name字段
		public void bloomOuterTree() {
			// 要想在内部类里面访问外部类的成员，就必须在this之前添加“外部类的名称.”
			System.out.println(TreeInner.this.tree_name + "的" + flower_name
					+ "开花啦");
		}
	}

}
