package com.object.inherit;

// 演示关键字protected的用法
public class DuckProtected extends Bird {

	public DuckProtected(String name, int sex) {
		super(name, sex, "嘎嘎");
	}

	public void setSexType(int sexType) {
		super.setSexType(sexType);
		// 若想对父类的属性直接赋值，又不想对外开放该属性，可将父类的属性从private改为protected
		// 被protected修饰的成员，表示受保护，它允许子类访问，但不允许外部访问。
		// 倘若父类的属性被protected修饰，则子类可以直接读写该属性；
		// 倘若父类的方法被protected修饰，则子类可以直接读写该方法；
		// 所谓读方法，就是方法的调用操作；所谓写方法，就是方法的重载操作。
		sexName = (sexType == 0) ? "公" : "母";
		// super.sexName = (sexType==0) ? "公" : "母";
	}

}
