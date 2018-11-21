package cn.bdqn.entity;

import java.io.Serializable;

/**
 * ʹ�ó���
 * 
 * @author WindLin
 * 
 */
public class Scene implements Serializable{
	
	private String type; // ��������
	private String description; // ��������
	private int data; //��������
	
	
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
