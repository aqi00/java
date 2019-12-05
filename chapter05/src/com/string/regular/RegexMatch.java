package com.string.regular;

//演示如何利用正则表达式校验合法的手机号码、身份证号，以及电子邮箱
public class RegexMatch {

	public static void main(String[] arg) {
		String phone = "13901234567";
		// 检查字符串是否为合法的手机号码
		boolean isPhone = isPhone(phone);
		System.out.println("phone = " + phone + ", isPhone = " + isPhone);
		// checkYear(); // 检查身份证中间的四位年份数字
		// checkMonth(); // 检查身份证中间的两位月份数字
		// checkDay(); // 检查身份证中间的两位日期数字
		checkLastFour(); // 检查身份证末尾的四位编号
		String icno = "350111199801011231";
		// 检查字符串是否为合法的身份证号码
		boolean isICNO = isICNO(icno);
		System.out.println("icno = " + icno + ", isICNO = " + isICNO);
		String email = "aqi88@163.com";
		// 检查字符串是否为合法的电子邮箱
		boolean isEmail = isEmail(email);
		System.out.println("email = " + email + ", isEmail = " + isEmail);
	}

	// 利用正则表达式检查字符串是否为合法的手机号码
	public static boolean isPhone(String phone) {
		// 开头的"1"代表第一位为数字1，"[3-9]"代表第二位可以为3到9的某个数字，"\\d{9}"代表后面是9位数字
		String regex = "1[3-9]\\d{9}";
		// 字符串变量的matches方法返回正则表达式对该串的检验结果，true表示符合字符串规则，false表示不符合规则
		return phone.matches(regex);
	}

	// 校验四位的年份字符串
	public static void checkYear() {
		String regex = "(19|20)\\d{2}"; // 四位年份数字必须以19或者20开头
		for (int i = 1899; i <= 2100; i++) {
			if (i > 1910 && i < 2090) { // 缩小待校验年份的区间范围
				continue;
			}
			String year = i + "";
			boolean check = year.matches(regex); // 校验该年份是否匹配正则表达式的规则
			System.out.println("year = " + year + ", check = " + check);
		}
	}

	// 校验两位的月份字符串
	public static void checkMonth() {
		String regex = "0[1-9]|1[0-2]"; // 月份的校验规则，合法的月份数字从01到12
		for (int i = 0; i <= 13; i++) {
			String month = String.format("%02d", i);
			boolean check = month.matches(regex); // 校验该月份是否匹配正则表达式的规则
			System.out.println("month = " + month + ", check = " + check);
		}
	}

	// 校验两位的日期字符串
	public static void checkDay() {
		String regex = "0[1-9]|[12]\\d|3[01]"; // 日期的校验规则
		for (int i = 0; i <= 32; i++) {
			String day = String.format("%02d", i);
			boolean check = day.matches(regex); // 校验该日期是否匹配正则表达式的规则
			System.out.println("day = " + day + ", check = " + check);
		}
	}

	// 校验身份证号码末尾的四位编号串
	public static void checkLastFour() {
		String regex = "\\d{3}([0-9xX])"; // 身份证末尾四位的校验规则
		for (int i = 0; i < 36; i++) { // 循环生成多个待校验的四位字符串
			char last;
			if (i < 10) { // 小于10的时候，取数字符号
				last = (char) ('0' + i); // 转换成数字字符
			} else { // 大等于10的时候，取字母符号
				last = (char) ('A' + i - 10); // 转换成字母字符
			}
			String lastFour = String.format("%03d%c", i * 13, last);
			boolean check = lastFour.matches(regex); // 校验该字符串是否合法的身份证末四位
			System.out.println("lastFour = " + lastFour + ", check = " + check);
		}
	}

	// 利用正则表达式检查字符串是否为合法的身份证号码
	public static boolean isICNO(String icno) {
		// String regex = "(六位地区编码)(四位年份)(两位月份)(两位日期)(末尾四位编号)";
		String regex = "(\\d{6})((19|20)\\d{2})(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(\\d{3}([0-9xX]))";
		return icno.matches(regex);
	}

	// 利用正则表达式检查字符串是否为合法的电子邮箱
	public static boolean isEmail(String email) {
		String regex = "([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)";
		return email.matches(regex);
	}

}
