package cn.bdqn.entity;

import java.io.Serializable;

/**
 * �ײ͸���
 * @author WindLin
 *
 */
public abstract class ServicePackage implements Serializable{

	private double price = 50; //ÿ���¹̶��ʷ�
	
	public abstract void showInfo();

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ServicePackage(double price) {
		super();
		this.price = price;
	}

	public ServicePackage() {
		super();
	}
	
	
}
