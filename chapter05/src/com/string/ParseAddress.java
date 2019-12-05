package com.string;

//演示如何从字符串中解析出详细的收件人信息
public class ParseAddress {

	public static void main(String[] arg) {
		String[] infoArray = new String[]{
				"张三 15960238696 北京市海淀区双清路30号",
				"059187727932,福建省福州市闽侯县上街镇工贸路3号,李四",
				"15960238696 王五 四川省凉山彝族自治州西昌市大水井12号",
				"西藏自治区阿里地区噶尔县狮泉河镇迎宾大道26号,赵六,059187727932",
				"刘七 内蒙古自治区阿拉善盟额济纳旗达来呼布镇黑水城遗址 15960238696"
		};
		for (String info : infoArray) { // 依次解析几个字符串中的收件人信息
			parseReceiverInfo(info); // 解析收件人信息
		}
	}
	
	// 解析收件人信息
	private static void parseReceiverInfo(String info) {
		String[] splits = info.split(" |,"); // 以空格或者逗号分割字符串
		String name="", phone="", address=""; // 分别声明姓名、号码、地址三个字符串变量
		for (String str : splits) {
			if (isPhone(str)) { // 找到电话号码
				phone = str;
			} else if (name.equals("")) { // 非号码的字符串先放到姓名变量这里
				name = str;
			} else if (str.length() > name.length()) { // 地址串应当长于姓名
				address = str;
			} else { // 地址串不如姓名长，说明姓名变量放错东西了，要纠正过来
				address = name;
				name = str;
			}
		}
		System.out.println(String.format("姓名：%s, 电话：%s, 收件地址：%s", name, phone, address));

		// 声明一个字符串数组，其中第一个元素存放区域名称，第二个元素存放剩下的地址
		String[] areaArray = new String[]{"", address};
		// 获取省级行政区的名称
		areaArray = getAreaName(areaArray[1], new String[]{"省", "自治区"});
		String province = areaArray[0]; // 省份名称
		// 获取地级行政区的名称
		areaArray = getAreaName(areaArray[1], new String[]{"自治州", "地区", "盟", "市"});
		String city = areaArray[0]; // 地市名称
		// 获取县级行政区的名称
		areaArray = getAreaName(areaArray[1], new String[]{"县", "市", "区", "旗"});
		String district = areaArray[0]; // 区县名称
		String detail = areaArray[1]; // 详细地址
		if (province.length() <= 0) { // 未找到省份名称，说明这是直辖市
			province = city;
		}
		System.out.println(String.format("省份：%s, 地市：%s, 区县：%s, 详细地址：%s", 
				province, city, district, detail));
	}

	// 利用正则表达式检查字符串是否为纯数字的号码（包括手机号与固定电话）
	private static boolean isPhone(String phone) {
		// “\\d”代表数字，后面的加号代表允许有一个到多个前面的字符
		String regex = "\\d+";
		// 字符串变量的matches方法返回正则表达式对该串的检验结果，true表示符合字符串规则，false表示不符合规则
		return phone.matches(regex);
	}

	// 获取区域名称以及剩下的地址
	private static String[] getAreaName(String address, String[] suffixArray) {
		// 声明一个字符串数组，其中第一个元素存放区域名称，第二个元素存放剩下的地址
		String[] areaArray = new String[]{"", address};
		int pos = 0; // 后缀文字的位置
		for (String suffix : suffixArray) { // 遍历所有的后缀字符串
			pos = address.indexOf(suffix); // 查找后缀文字所处的位置
			if (pos > 0) { // 在地址串中找到后缀文字
				// 从原地址中截取该后缀对应的区域名称
				areaArray[0] = address.substring(0, pos+suffix.length());
				// 从原地址中截取剩下的地址字符串
				areaArray[1] = address.substring(pos+suffix.length());
				break; // 已找到区域名称，就退出循环
			}
		}
		return areaArray;
	}

}
