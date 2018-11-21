package cn.bdqn.entity;

import cn.bdqn.service.NetService;

/**
 * �����ײ�
 * 
 * @author WindLin
 * 
 */
public class NetPackage extends ServicePackage implements NetService {

	private int flow = 2048; // ��������

	@Override
	public void showInfo() {
		System.out.println("�����ײ�:��������Ϊ"+this.flow/1024+"GB/��");
	}

	@Override
	public int netPlay(int flow, MobileCard card) throws Exception {
		int temp = flow;
		for (int i = 0; i < flow; i++) {
			if(this.flow-card.getRealFlow()>=1){ 
				card.setRealFlow(card.getRealFlow()+1);
			}else if(card.getMoney()>=0.2){//�ײ�û��ʱ�䵫�����
				card.setRealFlow(card.getRealFlow()+1);
				card.setMoney(card.getMoney()-0.2);
				card.setConsumAmount(card.getConsumAmount()+0.2);
			}else{
				temp = i;
				throw new Exception("��������"+i+"MB,�������,���ֵ����ʹ��!");
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
