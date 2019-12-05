package com.control.logic;

//演示逻辑运算符的用法
public class Bool {

	public static void main(String[] args) {
		// boolean表示布尔类型，该类型的变量只允许两个取值，即true和false
		boolean zhen = true; // true表示为真
		System.out.println("zhen=" + zhen);
		boolean jia = false; // false表示为假
		System.out.println("jia=" + jia);
		// “!”加在布尔变量前面表示开展“非”运算。若原变量值为true则运算结果为false，若原变量值为false则运算结果为true
		boolean not = !zhen;
		System.out.println("not=" + not);
		// “&”放在两个布尔变量之间表示开展“与”运算。只有两个变量都为true时，运算结果才为true，其余情况的运算结果都为false
		boolean and = zhen & jia;
		System.out.println("and=" + and);
		// “|”放在两个布尔变量之间表示开展“或”运算。只要两个变量有一个为true，运算结果就为true。只有两个变量都为false时，运算结果才为false
		boolean or = zhen | jia;
		System.out.println("or=" + or);
		// “^”放在两个布尔变量之间表示开展“异或”运算。当两个变量同为true或者同为false时，运算结果为false。当两个变量一个为true另一个为false时，运算结果为true
		boolean xor = zhen ^ jia;
		System.out.println("xor=" + xor);
		boolean value = true; // 为布尔变量赋初始值
		System.out.println("value=" + value);
		// 对布尔变量做“与”运算，且运算结果仍旧保存在该变量中，则可使用运算符“&=”
		value &= false; // 该行代码等同于 value = value&false;
		System.out.println("value=" + value);
		// 对布尔变量做“或”运算，且运算结果仍旧保存在该变量中，则可使用运算符“|=”
		value |= true; // 该行代码等同于 value = value|true;
		System.out.println("value=" + value);
		// 对布尔变量做“异或”运算，且运算结果仍旧保存在该变量中，则可使用运算符“^=”
		value ^= false; // 该行代码等同于 value = value^false;
		System.out.println("value=" + value);
	}
}
