package cn.bdqn.entity;

import java.io.Serializable;

public class ShoppingCatItem implements Serializable{
	private EasyBuyProduct product;
	private int quantity;
	private double cost;
	
	
	public ShoppingCatItem(EasyBuyProduct product, int quantity) {
		this.product = product;
		this.quantity = quantity;
		this.cost =product.getPrice()*quantity ;//ÿ�м۸�= ��Ʒ����*��Ʒ����
	}

	public void setProduct(EasyBuyProduct product) {
		this.product = product;
	}

	//��������ʱ�Զ�����cost
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.cost=product.getPrice()*quantity;
	}


	public double getCost() {
		return cost;
	}

	public EasyBuyProduct getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

}
