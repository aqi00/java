package com.database.poem;

import javafx.beans.property.SimpleStringProperty;

//定义诗歌列的信息类
public class PoemColumn {
	private SimpleStringProperty id; // 编号
	private SimpleStringProperty title; // 名称
	private SimpleStringProperty author; // 类型
	private SimpleStringProperty dynasty; // 说明

	public PoemColumn(String id, String title, String author, String dynasty) {
		this.id = new SimpleStringProperty(id);
		this.title = new SimpleStringProperty(title);
		this.author = new SimpleStringProperty(author);
		this.dynasty = new SimpleStringProperty(dynasty);
	}

	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public String getAuthor() {
		return author.get();
	}

	public void setAuthor(String author) {
		this.author.set(author);
	}

	public String getDynasty() {
		return dynasty.get();
	}

	public void setDynasty(String dynasty) {
		this.dynasty.set(dynasty);
	}
}
