package com.object.inherit;

//定义了一个继承自鸟类的鸭子类
public class Duck extends Bird {
	private String sexName; // 定义一个家禽类的性别名称

	public Duck(String name, int sex) {
		super(name, sex, "嘎嘎"); // 利用super指代父类的构造方法名称
	}

	public void setSexType(int sexType) {
		// 方法内部再调用自身方法，会变成递归调用，如果没有退出机制就变成死循环了
		// setSexType(sexType);
		// 在方法前面添加前缀“super.”，表示这里调用的是父类的方法
		super.setSexType(sexType);
		// 修改家禽类的性别名称，此时父类和子类都有属性sexName，不加前缀的话默认为子类的属性
		sexName = (sexType == 0) ? "公" : "母";
		// this.sexName = (sexType==0) ? "公" : "母";
	}

	// 父类的getSexName方法需要重写，否则父类的方法会使用父类的属性
	public String getSexName() {
		return this.sexName;
	}

	// 父类的toString方法需要重写，否则父类的方法会使用父类的属性
	public String toString() {
		String desc = String.format("这是一只%s%s，它会%3$s、%3$s地叫。", this.sexName,
				getName(), getVoice());
		return desc;
	}

}
