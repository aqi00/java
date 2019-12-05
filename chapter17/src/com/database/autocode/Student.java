package com.database.autocode;

import java.util.Date;

//学生信息表
public class Student {
	private int xuehao; // 学号
	private String name; // 姓名
	private Date birthday; // 生日

	public void setXuehao(int xuehao) {
		this.xuehao = xuehao;
	}
	
	public int getXuehao() {
		return this.xuehao;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Date getBirthday() {
		return this.birthday;
	}
	
}
