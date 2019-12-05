package com.database.autocode;

import javafx.beans.property.SimpleStringProperty;

//定义表格列的信息类
public class Column {
	private SimpleStringProperty name; // 名称
	private SimpleStringProperty type; // 类型
	private SimpleStringProperty comment; // 说明

	public Column(String name, String type, String comment) {
		this.name = new SimpleStringProperty(name);
		this.type = new SimpleStringProperty(type);
		this.comment = new SimpleStringProperty(comment);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getType() {
		return type.get();
	}

	public void setType(String type) {
		this.type.set(type);
	}

	public String getComment() {
		return comment.get();
	}

	public void setComment(String comment) {
		this.comment.set(comment);
	}
}
