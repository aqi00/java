package com.database.autocode;

import java.util.Date;

//教师信息表
public class Teacher {
	private int gonghao; // 工号
	private String name; // 姓名
	private Date birthday; // 生日
	private int sex; // 性别。0 男性；1 女性
	private String course; // 任教课程

	public void setGonghao(int gonghao) {
		this.gonghao = gonghao;
	}
	public int getGonghao() {
		return this.gonghao;
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
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getSex() {
		return this.sex;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getCourse() {
		return this.course;
	}
}
