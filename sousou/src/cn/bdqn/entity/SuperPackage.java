package cn.bdqn.entity;

import cn.bdqn.service.CallService;
import cn.bdqn.service.NetService;
import cn.bdqn.service.SendService;

/**
 * �����ײ�
 * @author WindLin
 * 
 */
public class SuperPackage extends ServicePackage implements CallService,
		SendService, NetService {

	private int talkTime = 200; // ͨ��ʱ��
	private int smsCount = 50; // ��������
	private int flow = 1024; // ��������

	@Override
	public void showInfo() {
		System.out.println("�����ײ�:ͨ��ʱ��Ϊ"+this.talkTime+"����/��,��������Ϊ"
				+this.smsCount+"��/��,��������Ϊ"+this.flow/1024+"GB/��");
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

	@Override
	public int send(int count, MobileCard card) throws Exception  {
		int temp = count;
		for (int i = 0; i < count; i++) {
			if(this.smsCount-card.getRealSMScount()>=1){ 
				card.setRealSMScount(card.getRealSMScount()+1);
			}else if(card.getMoney()>=0.2){//�ײ�û��ʱ�䵫�����
				card.setRealSMScount(card.getRealSMScount()+1);
				card.setMoney(card.getMoney()-0.2);
				card.setConsumAmount(card.getConsumAmount()+0.2);
			}else{
				temp = i;
				throw new Exception("�����ѷ��Ͷ���"+i+"��,�������,���ֵ����ʹ��!");
			}
		}
		return temp;
	}

	@Override
	public int call(int minCount, MobileCard card) throws Exception {
		int temp = minCount;
		for (int i = 0; i < minCount; i++) {
			if(this.talkTime-card.getRealTalkTime()>=1){ //�ײͻ���ʱ��
				card.setRealTalkTime(card.getRealTalkTime()+1);
			}else if(card.getMoney()>=0.2){//�ײ�û��ʱ�䵫�����
				card.setRealTalkTime(card.getRealTalkTime()+1);
				card.setMoney(card.getMoney()-0.2);
				card.setConsumAmount(card.getConsumAmount()+0.2);
			}else{
				temp = i;
				throw new Exception("������ͨ��"+i+"����,�������,���ֵ����ʹ��!");
			}
		}
		return temp;
	}

	public int getTalkTime() {
		return talkTime;
	}

	public void setTalkTime(int talkTime) {
		this.talkTime = talkTime;
	}

	public int getSmsCount() {
		return smsCount;
	}

	public void setSmsCount(int smsCount) {
		this.smsCount = smsCount;
	}

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}

	public SuperPackage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SuperPackage(int talkTime, int smsCount, int flow) {
		super();
		this.talkTime = talkTime;
		this.smsCount = smsCount;
		this.flow = flow;
	}

	public SuperPackage(double price, int talkTime, int smsCount, int flow) {
		super(price);
		this.talkTime = talkTime;
		this.smsCount = smsCount;
		this.flow = flow;
	}

}
