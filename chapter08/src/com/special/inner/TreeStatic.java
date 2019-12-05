package com.special.inner;

//演示关键字static的用法
public class TreeStatic {
	private String tree_name; // 树木名称

	public TreeStatic(String tree_name) {
		this.tree_name = tree_name;
		leaf_count++; // 每调用一次构造方法，叶子数量就加一
		System.out.println("这里是构造方法，此时叶子数量为" + leaf_count);
	}

	public void sprout() {
		System.out.println(tree_name + "发芽啦");
	}

	// static的字面意思是“静态的”，意味着无需动态创建即可直接使用。
	// 利用static修饰成员属性，外部即可通过“类名.属性名”直接访问静态属性。
	public static int TYPE_ARBOR = 1; // 乔木类型
	public static int TYPE_BUSH = 2; // 灌木类型

	// 利用static修饰成员方法，外部即可通过“类名.方法名”直接访问静态方法。
	public static String getTypeName(int type) {
		String type_name = ""; // 类型名称
		if (type == TYPE_ARBOR) {
			type_name = "乔木";
		} else if (type == TYPE_BUSH) {
			type_name = "灌木";
		}
		return type_name;
	}

	// 叶子数量，用来演示构造方法与初始静态代码块的执行顺序
	public static int leaf_count = 0;

	// static还能用来包裹某个代码块，一旦当前类加载进内存，静态代码块就立即执行
	static {
		leaf_count++;
		System.out.println("这里是初始的静态代码块，此时叶子数量为" + leaf_count);
	}

	public static int annual_ring = 0; // 树木年轮，用来演示静态属性的持久性

	// 注意每次读取静态属性，得到的都是该属性最近一次的数值
	public void grow() {
		annual_ring++; // 每调用一次grow方法，树木年轮就加一
		System.out.println(tree_name + "的树龄为" + annual_ring);
	}

	// 若想静态属性始终如一保持不变，就得给该属性添加final修饰符，表示终态属性只能被赋值一次
	public final static int FINAL_TYPE_ARBOR = 1;
	public final static int FINAL_TYPE_BUSH = 2;

}
