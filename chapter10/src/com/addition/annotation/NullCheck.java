package com.addition.annotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

//演示如何利用注解校验空字段
public class NullCheck {

	// 对指定对象进行空指针校验。返回true表示该对象跟它的每个字段都非空，返回false表示对象为空或者至少一个字段为空
	public static boolean isValid(Object obj) {
		if (obj == null) {
			System.out.println("校验对象为空");
			return false;
		}
		Class cls = obj.getClass(); // 获得对象实例的基因类型
		// 声明一个字符串清单，用来保存非空校验失败的无效字段名称
		List<String> invalidList = new ArrayList<String>();
		try {
			// 获取对象的所有属性（如果使用getFields，就无法获取到private的属性）
			Field[] fields = cls.getDeclaredFields();

			for (Field field : fields) { // 依次遍历每个对象属性
				// 如果该属性声明了NotNull注解，就校验空字段
				if (field.isAnnotationPresent(NotNull.class)) {
					if (field != null) {
						field.setAccessible(true); // 将该字段设置为允许访问
						Object value = field.get(obj); // 获取某实例的字段值
						if (value == null) { // 如果发现该字段为空
							// 就把该字段的名称添加到无效清单中
							invalidList.add(field.getName());
						}
					}
				}
			}
		} catch (Exception e) { // 捕捉到了任何一种异常（错误除外）
			e.printStackTrace();
		}
		if (invalidList.size() > 0) { // 无效清单非空，表示至少有一个字段没通过非空校验
			String desc = String.format("%s类非空校验不通过的字段有：%s",
					cls.getName(), invalidList.toString());
			System.out.println(desc);
			return false;
		} else {
			return true;
		}
	}
}
