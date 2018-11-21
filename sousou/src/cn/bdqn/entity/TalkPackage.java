package cn.bdqn.entity;

import cn.bdqn.service.CallService;
import cn.bdqn.service.SendService;

/**
 * 话唠套餐
 * @author WindLin
 * 
 */
public class TalkPackage extends ServicePackage implements CallService,
		SendService {

	private int talkTime = 100; // 通话时长
	private int smsCount = 50; // 短信数量

	@Override
	public void showInfo() {
		System.out.println("话唠套餐:通话时长为"+this.talkTime+"分钟/月,短信条数为"
				+this.smsCount+"条/月.");
	}

	@Override
	public int send(int count, MobileCard card) throws Exception  {
		int temp = count;
		for (int i = 0; i < count; i++) {
			if(this.smsCount-card.getRealSMScount()>=1){ 
				card.setRealSMScount(card.getRealSMScount()+1);
			}else if(card.getMoney()>=0.2){//套餐没有时间但有余额
				card.setRealSMScount(card.getRealSMScount()+1);
				card.setMoney(card.getMoney()-0.2);
				card.setConsumAmount(card.getConsumAmount()+0.2);
			}else{
				temp = i;
				throw new Exception("本次已发送短信"+i+"条,你的余额不足,请充值后再使用!");
			}
		}
		return temp;
	}

	@Override
	public int call(int minCount, MobileCard card) throws Exception{
		int temp = minCount;
		for (int i = 0; i < minCount; i++) {
			if(this.talkTime-card.getRealTalkTime()>=1){ //套餐还有时间
				card.setRealTalkTime(card.getRealTalkTime()+1);
			}else if(card.getMoney()>=0.2){//套餐没有时间但有余额
				card.setRealTalkTime(card.getRealTalkTime()+1);
				card.setMoney(card.getMoney()-0.2);
				card.setConsumAmount(card.getConsumAmount()+0.2);
			}else{
				temp = i;
				throw new Exception("本次已通话"+i+"分钟,你的余额不足,请充值后再使用!");
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

	public TalkPackage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TalkPackage(int talkTime, int smsCount) {
		super();
		this.talkTime = talkTime;
		this.smsCount = smsCount;
	}

	public TalkPackage(double price, int talkTime, int smsCount) {
		super(price);
		this.talkTime = talkTime;
		this.smsCount = smsCount;
	}

}
