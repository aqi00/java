package com.object.inherit;

//定义一个名叫Bird的鸟类（作为可被其它类继承的基类）
public class Bird {
	private String name; // 定义鸟的名称
	private String voice; // 定义鸟的叫声
	private int sexType; // 定义鸟的性别类型。0表示雄性，1表示雌性
	// 定义鸟的性别名称
	// private String sexName; // 与Duck搭配使用
	// public String sexName; // 与DuckPublic搭配使用
	protected String sexName; // 与DuckProtected搭配使用

	// 鸟类的构造方法（无任何输入参数）
	public Bird() {
		this.name = "鸟";
	}

	// 鸟类的构造方法（输入参数包含：名称、性别、叫声）
	public Bird(String name, int sexType, String voice) {
		this.name = name;
		this.voice = voice;
		// this.sexType = sexType;
		setSexType(sexType); // 该方法内部同时修改性别类型和性别名称
	}

	// 设置鸟的名称
	public void setName(String name) {
		this.name = name;
	}

	// 获取鸟的名称
	public String getName() {
		return this.name;
	}

	// 设置鸟的叫声
	public void setVoice(String voice) {
		this.voice = voice;
	}

	// 获取鸟的叫声
	public String getVoice() {
		return this.voice;
	}

	// 设置鸟的性别类型，并自动调整性别名称
	public void setSexType(int sexType) {
		this.sexType = sexType;
		this.sexName = (sexType == 0) ? "雄" : "雌";
	}

	// 获取鸟的性别类型
	public int getSexType() {
		return this.sexType;
	}

	// 获取鸟的性别名称
	public String getSexName() {
		return this.sexName;
	}

	// 输出鸟类的基本信息描述文字
	public String toString() {
		String desc = String.format("这是一只%s%s，它会%3$s、%3$s地叫。", this.sexName,
				this.name, this.voice);
		return desc;
	}

}
