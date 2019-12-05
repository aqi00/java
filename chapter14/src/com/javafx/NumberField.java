package com.javafx;

import javafx.scene.control.TextField;

//自定义数字输入框。只允许输入0到9，以及小数点
public class NumberField extends TextField {

	@Override
	public void replaceText(int start, int end, String text) {
		if (text.matches("[0-9|.]")) { // 字符串由数字与小数点组成
			super.replaceText(start, end, text);
		}
	}

	@Override
	public void replaceSelection(String text) {
		if (text.matches("[0-9|.]")) { // 字符串由数字与小数点组成
			super.replaceSelection(text);
		}
	}
}
