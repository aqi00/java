package com.io.huffman;

//定义哈夫曼树的节点类
public class HuffmNode {
	private int data; // 节点值
	private int index; // 字符的ASCII码值
	private HuffmNode left; // 左边的子节点
	private HuffmNode right; // 右边的子节点

	public HuffmNode(int data, int index) {
		this.data = data;
		this.index = index;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public HuffmNode getLeft() {
		return left;
	}

	public void setLeft(HuffmNode left) {
		this.left = left;
	}

	public HuffmNode getRight() {
		return right;
	}

	public void setRight(HuffmNode right) {
		this.right = right;
	}

}
