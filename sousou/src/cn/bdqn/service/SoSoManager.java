package cn.bdqn.service;

import java.util.Scanner;

import cn.bdqn.entity.MobileCard;
import cn.bdqn.entity.ServicePackage;
import cn.bdqn.tools.CardUtil;

/**
 * 业务类
 * @author WindLin
 *
 */
public class SoSoManager {

	private Scanner input = new Scanner(System.in);
	private CardUtil cardUtil = new CardUtil(); //工具类
	
	//主菜单
	public void mainMenu(){
		do {
			System.out.println("\n****************欢迎使用嗖嗖移动业务大厅******************");
			System.out.println("1.用户登录  2.用户注册  3.使用嗖嗖  4.话费充值  5.资费说明  6.退出系统");
			System.out.print("请选择：");
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				System.out.println("请输入手机卡号：");
				String number = input.next();
				System.out.println("请输入密码：");
				String password = input.next();
				//调用工具类的方法验证输入是否正确
				if(cardUtil.isExistCard(number, password)){
					//正确就进入二级菜单 传入卡号
					this.cardMenu(number);
					
				}else{
					System.out.println("用户名或密码不对！");
				}
				continue;
			case 2:
				this.registCard();
				continue;
			case 3:
				System.out.println("3.使用嗖嗖");
				//先登录
				System.out.println("请输入手机卡号：");
				String userNumber = input.next();
				if(cardUtil.isExistCard(userNumber)){
					cardUtil.useSoso(userNumber);
				}else{
					System.out.println("用户不存在,无法使用嗖嗖！");
				}
				continue;
			case 4:
				System.out.println("4.话费充值");
				continue;
			case 5:
				System.out.println("5.资费说明 ");
				cardUtil.showDescription();
				continue;
			case 6:
				System.out.println("谢谢使用！");
				break;
			default:
				System.out.println("输入不对！");
				continue;
			}
			break;
		} while (true);
	}
	
	//二级菜单
	public void cardMenu(String number){
		do {
			System.out.println("\n******嗖嗖移动用户菜单***********");
			System.out.println("1.本月账单查询");
			System.out.println("2.套餐余量查询");
			System.out.println("3.打印消费详单");
			System.out.println("4.套餐变更");
			System.out.println("5.办理退网");
			System.out.print("请选择 (输入1-5选择功能，其他返回上一级): ");
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				System.out.println("*****本月账单查询******");
				cardUtil.showAmountDetail(number);
				continue;
			case 2:
				System.out.println("*****套餐余量查询******");
				cardUtil.showRemainDetail(number);
				continue;
			case 3:
				System.out.println("*****打印消费详单******");
				cardUtil.printAmountDetail(number);
				continue;
			case 4:
				System.out.println("*****套餐变更******");
				continue;
			case 5:
				System.out.println("*****办理退网******");
				cardUtil.delCard(number);
				continue;
			default:
				System.out.println("返回！");
				break;
			}
			break;
		} while (true);
	}
	
	//用户注册
	public void registCard(){
		//生成9个
		String[] numbers = cardUtil.getNewNumbers(9);
		System.out.println("*****可选择的卡号*****");
		for (int i = 0; i < numbers.length; i++) {
			System.out.print((i+1)+". "+numbers[i]+"\t");
			if((i+1)%3==0){
				System.out.println();
			}
		}
		System.out.println("请选择卡号（输入1-9的序号）：");
		int index = input.nextInt()-1;
		String number = numbers[index]; //获得卡号
		System.out.println("1.话唠套餐  2.网虫套餐   3.超人套餐    ，请选择套餐（输入序号）： ");
		int packId = input.nextInt();
		ServicePackage spk = cardUtil.createPack(packId);
		System.out.print("请输入姓名: ");
		String userName = input.next();
		System.out.print("请输入密码: ");
		String password = input.next();
		System.out.print("请输入预存话费金额: ");
		double money = input.nextDouble();
		//判断金额
		while(money<spk.getPrice()){
			System.out.println("您预存的话费金额不足以支付本月固定套餐资费，请重新充值：");
			money = input.nextDouble();
		}
		double consumAmount = spk.getPrice();
		MobileCard newCard = new MobileCard(number, userName, password, spk,
				consumAmount , money-consumAmount, 0, 0, 0);
		System.out.println("注册成功！");
		//向已注册卡号中添加用户
		cardUtil.addCard(newCard);
		//显示
		newCard.showMeg();
		
	}
	
}
