package com.string;

//演示如何从18位的身份证号码字符串中提取信息
public class IcnoExtract {

	public static void main(String[] arg) {
		String[] icnoArray = new String[]{
				"110108208802290199", "11011420880903294x", "110101208806180030"
		};
		for (String icno : icnoArray) { // 依次校验几个可能的北京身份证号码
			String result = extractIc(icno); // 获取身份证号的校验结果
			System.out.println(String.format("%s的校验结果为：%s", icno, result));
		}
	}

	// 检查身份证号码，并返回它包含的信息描述
	private static String extractIc(String icno) {
		if (!isICNO(icno)) { // 不符合身份证号的格式定义
			return "该身份证的号码不合法";
		}
		if (!isValidIc(icno)) { // 最后一位的校验字符非法
			return "该身份证的最后一位计算有误";
		}
		// 定义北京市的各区域编码
		String[] areaIds = { "110101", "110102", "110105", "110106", 
				"110107", "110108", "110109", "110111", "110112", "110113", 
				"110114", "110115", "110116", "110117", "110118", "110119" };
		// 定义北京市的各区域名称
		String[] areaNames = { "东城区", "西城区", "朝阳区", "丰台区", 
				"石景山区", "海淀区", "门头沟区", "房山区", "通州区", "顺义区", 
				"昌平区", "大兴区", "怀柔区", "平谷区", "密云区", "延庆区" };
		String icPrefix = icno.substring(0, 6); // 截取身份证号码的前六位（即地区编码）
		String icArea = "";
		for (int i = 0; i < areaIds.length; i++) { // 通过匹配区域编码来查找该地区的名称
			if (icPrefix.equals(areaIds[i])) {
				icArea = "北京市" + areaNames[i];
				break;
			}
		}
		if (icArea.isEmpty()) { // 未找到对应的居住地址
			return "该身份证不是北京号码";
		}
		// 从身份证号码中截取出生年月日
		String birthday = String.format("%s年%s月%s日", icno.substring(6, 10),
				icno.substring(10, 12), icno.substring(12, 14));
		String seqNro = icno.substring(14, 17); // 从身份证号码中截取出生序号
		int sexSign = Integer.parseInt(icno.substring(16, 17)); // 从身份证号码中截取性别标志
		// 身份证号码的倒数第二位，奇数表示男性，偶数表示女性
		String sexName = (sexSign % 2 == 1) ? "男" : "女";
		return String.format("居住地址是%s，出生日期是%s，出生序号是%s，性别是%s。",
					icArea, birthday, seqNro, sexName);
	}

	// 利用正则表达式检查字符串是否为合法的身份证号码（增加地区编码的校验）
	public static boolean isICNO(String icno) {
		String regex = "((1[1-5]|2[1-3]|3[1-7]|4[1-6]|5[0-4]|6[1-5]|8[1-3])\\d{4})((19|20)\\d{2})(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(\\d{3}([0-9xX]))";
		return icno.matches(regex);
	}

	// 校验身份证号码开头的六位地区编码
	public static void checkArea() {
		String regex = "(1[1-5]|2[1-3]|3[1-7]|4[1-6]|5[0-4]|6[1-5]|8[1-3])\\d{4}";
		for (int i = 0; i <= 90; i++) {
			String area = String.format("%06d", i * 10000);
			boolean check = area.matches(regex);
			System.out.println("area = " + area + ", check = " + check);
		}
	}

	// 检查身份证号码的最后一位校验码是否正确。最后一位校验码的计算方法为：
	// 1、将身份证号码的前17位数分别乘以不同的系数，
	// 2、从第1位到第17位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
	// 3、将这17位数字和系数相乘的结果累加。
	// 4、累加的总和除以11，看看余数是多少。
	// 5、余数可能是0 1 2 3 4 5 6 7 8 9 10这11个数字，分别对应的校验码为1 0 X 9 8 7 6 5 4 3 2。
	// 6、如果余数为2，校验码（身份证的第18位）就是字母X。
	public static boolean isValidIc(String icno) {
		// 定义相乘的系数列表
		int[] factors = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
		int sum = 0; // 累加之和
		for (int i = 0; i < 17; i++) {
			// 提取指定位置的数字，注意要转换成整型数值
			// int perNum = Integer.parseInt(icno.substring(i, i+1));
			int perNum = icno.charAt(i) - '0';
			sum += perNum * factors[i]; // 累加每次的乘积
		}
		int remainder = sum % 11; // 求除以11的余数
		// 定义余数对应的校验码列表
		char[] lastChars = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
		char lastChar = lastChars[remainder]; // 获得余数对应的校验码
		// 获取身份证号码的最后一位字符，注意统一转换为大写，避免x和X的判断问题
		char realLastChar = Character.toUpperCase(icno.charAt(17));
		//System.out.println("lastChar=" + lastChar + ",realLastChar=" + realLastChar);
		if (lastChar == realLastChar) { // 最后一位校验码与计算结果是吻合的
			return true;
		} else { // 最后一位校验失败
			return false;
		}
	}

}
