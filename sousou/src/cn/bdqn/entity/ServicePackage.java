package cn.bdqn.entity;

import java.io.Serializable;

/**
 * 套餐父类
 * @author WindLin
 *
 */
public abstract class ServicePackage implements Serializable{

	private double price = 50; //每个月固定资费
	
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
