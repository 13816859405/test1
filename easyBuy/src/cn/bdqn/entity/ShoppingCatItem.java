package cn.bdqn.entity;

import java.io.Serializable;

public class ShoppingCatItem implements Serializable{
	private EasyBuyProduct product;
	private int quantity;
	private double cost;
	
	
	public ShoppingCatItem(EasyBuyProduct product, int quantity) {
		this.product = product;
		this.quantity = quantity;
		this.cost =product.getPrice()*quantity ;//每行价格= 商品单价*商品数量
	}

	public void setProduct(EasyBuyProduct product) {
		this.product = product;
	}

	//设置数量时自动计算cost
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
