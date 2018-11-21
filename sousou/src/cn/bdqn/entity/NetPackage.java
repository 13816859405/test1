package cn.bdqn.entity;

import cn.bdqn.service.NetService;

/**
 * 网虫套餐
 * 
 * @author WindLin
 * 
 */
public class NetPackage extends ServicePackage implements NetService {

	private int flow = 2048; // 上网流量

	@Override
	public void showInfo() {
		System.out.println("上网套餐:上网流量为"+this.flow/1024+"GB/月");
	}

	@Override
	public int netPlay(int flow, MobileCard card) throws Exception {
		int temp = flow;
		for (int i = 0; i < flow; i++) {
			if(this.flow-card.getRealFlow()>=1){ 
				card.setRealFlow(card.getRealFlow()+1);
			}else if(card.getMoney()>=0.2){//套餐没有时间但有余额
				card.setRealFlow(card.getRealFlow()+1);
				card.setMoney(card.getMoney()-0.2);
				card.setConsumAmount(card.getConsumAmount()+0.2);
			}else{
				temp = i;
				throw new Exception("本次上网"+i+"MB,你的余额不足,请充值后再使用!");
			}
		}
		return temp;
	}

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}

	public NetPackage(double price, int flow) {
		super(price);
		this.flow = flow;
	}

	public NetPackage() {
		super();
		// TODO Auto-generated constructor stub
	}




}
