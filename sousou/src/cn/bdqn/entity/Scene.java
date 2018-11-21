package cn.bdqn.entity;

import java.io.Serializable;

/**
 * 使用场景
 * 
 * @author WindLin
 * 
 */
public class Scene implements Serializable{
	
	private String type; // 场景类型
	private String description; // 场景描述
	private int data; //消费数量
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Scene(String type, String description) {
		super();
		this.type = type;
		this.description = description;
	}

	public Scene(String type, String description, int data) {
		super();
		this.type = type;
		this.description = description;
		this.data = data;
	}

	public Scene() {
		super();
		// TODO Auto-generated constructor stub
	}

}
