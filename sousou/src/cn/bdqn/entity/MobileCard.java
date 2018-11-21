package cn.bdqn.entity;

import java.io.Serializable;

/**
 * 移动卡
 * 
 * @author WindLin
 * 
 */
public class MobileCard implements Serializable {

	private String cardNumber; // 卡号
	private String userName; // 用户名
	private String password; // 密码
	private ServicePackage serPackage; // 所属套餐
	private double consumAmount; // 当月消费金额
	private double money; // 账户余额
	private int realTalkTime; // 实际通话时长
	private int realSMScount; // 实际发送短信条数
	private int realFlow; // 实际上网流量

	// 显示用户信息
	public void showMeg() {
		System.out.println("卡号：" + cardNumber + ",用户名：" + userName + ",当前余额："
				+ money + "元。");
		this.serPackage.showInfo();
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ServicePackage getSerPackage() {
		return serPackage;
	}

	public void setSerPackage(ServicePackage serPackage) {
		this.serPackage = serPackage;
	}

	public double getConsumAmount() {
		return consumAmount;
	}

	public void setConsumAmount(double consumAmount) {
		this.consumAmount = consumAmount;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getRealTalkTime() {
		return realTalkTime;
	}

	public void setRealTalkTime(int realTalkTime) {
		this.realTalkTime = realTalkTime;
	}

	public int getRealSMScount() {
		return realSMScount;
	}

	public void setRealSMScount(int realSMScount) {
		this.realSMScount = realSMScount;
	}

	public int getRealFlow() {
		return realFlow;
	}

	public void setRealFlow(int realFlow) {
		this.realFlow = realFlow;
	}

	public MobileCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MobileCard(String cardNumber, String userName, String password,
			ServicePackage serPackage, double consumAmount, double money,
			int realTalkTime, int realSMScount, int realFlow) {
		super();
		this.cardNumber = cardNumber;
		this.userName = userName;
		this.password = password;
		this.serPackage = serPackage;
		this.consumAmount = consumAmount;
		this.money = money;
		this.realTalkTime = realTalkTime;
		this.realSMScount = realSMScount;
		this.realFlow = realFlow;
	}

}
