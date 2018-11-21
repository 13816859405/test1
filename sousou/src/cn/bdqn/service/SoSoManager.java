package cn.bdqn.service;

import java.util.Scanner;

import cn.bdqn.entity.MobileCard;
import cn.bdqn.entity.ServicePackage;
import cn.bdqn.tools.CardUtil;

/**
 * ҵ����
 * @author WindLin
 *
 */
public class SoSoManager {

	private Scanner input = new Scanner(System.in);
	private CardUtil cardUtil = new CardUtil(); //������
	
	//���˵�
	public void mainMenu(){
		do {
			System.out.println("\n****************��ӭʹ�����ƶ�ҵ�����******************");
			System.out.println("1.�û���¼  2.�û�ע��  3.ʹ����  4.���ѳ�ֵ  5.�ʷ�˵��  6.�˳�ϵͳ");
			System.out.print("��ѡ��");
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				System.out.println("�������ֻ����ţ�");
				String number = input.next();
				System.out.println("���������룺");
				String password = input.next();
				//���ù�����ķ�����֤�����Ƿ���ȷ
				if(cardUtil.isExistCard(number, password)){
					//��ȷ�ͽ�������˵� ���뿨��
					this.cardMenu(number);
					
				}else{
					System.out.println("�û��������벻�ԣ�");
				}
				continue;
			case 2:
				this.registCard();
				continue;
			case 3:
				System.out.println("3.ʹ����");
				//�ȵ�¼
				System.out.println("�������ֻ����ţ�");
				String userNumber = input.next();
				if(cardUtil.isExistCard(userNumber)){
					cardUtil.useSoso(userNumber);
				}else{
					System.out.println("�û�������,�޷�ʹ���ಣ�");
				}
				continue;
			case 4:
				System.out.println("4.���ѳ�ֵ");
				continue;
			case 5:
				System.out.println("5.�ʷ�˵�� ");
				cardUtil.showDescription();
				continue;
			case 6:
				System.out.println("ллʹ�ã�");
				break;
			default:
				System.out.println("���벻�ԣ�");
				continue;
			}
			break;
		} while (true);
	}
	
	//�����˵�
	public void cardMenu(String number){
		do {
			System.out.println("\n******���ƶ��û��˵�***********");
			System.out.println("1.�����˵���ѯ");
			System.out.println("2.�ײ�������ѯ");
			System.out.println("3.��ӡ�����굥");
			System.out.println("4.�ײͱ��");
			System.out.println("5.��������");
			System.out.print("��ѡ�� (����1-5ѡ���ܣ�����������һ��): ");
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				System.out.println("*****�����˵���ѯ******");
				cardUtil.showAmountDetail(number);
				continue;
			case 2:
				System.out.println("*****�ײ�������ѯ******");
				cardUtil.showRemainDetail(number);
				continue;
			case 3:
				System.out.println("*****��ӡ�����굥******");
				cardUtil.printAmountDetail(number);
				continue;
			case 4:
				System.out.println("*****�ײͱ��******");
				continue;
			case 5:
				System.out.println("*****��������******");
				cardUtil.delCard(number);
				continue;
			default:
				System.out.println("���أ�");
				break;
			}
			break;
		} while (true);
	}
	
	//�û�ע��
	public void registCard(){
		//����9��
		String[] numbers = cardUtil.getNewNumbers(9);
		System.out.println("*****��ѡ��Ŀ���*****");
		for (int i = 0; i < numbers.length; i++) {
			System.out.print((i+1)+". "+numbers[i]+"\t");
			if((i+1)%3==0){
				System.out.println();
			}
		}
		System.out.println("��ѡ�񿨺ţ�����1-9����ţ���");
		int index = input.nextInt()-1;
		String number = numbers[index]; //��ÿ���
		System.out.println("1.�����ײ�  2.�����ײ�   3.�����ײ�    ����ѡ���ײͣ�������ţ��� ");
		int packId = input.nextInt();
		ServicePackage spk = cardUtil.createPack(packId);
		System.out.print("����������: ");
		String userName = input.next();
		System.out.print("����������: ");
		String password = input.next();
		System.out.print("������Ԥ�滰�ѽ��: ");
		double money = input.nextDouble();
		//�жϽ��
		while(money<spk.getPrice()){
			System.out.println("��Ԥ��Ļ��ѽ�����֧�����¹̶��ײ��ʷѣ������³�ֵ��");
			money = input.nextDouble();
		}
		double consumAmount = spk.getPrice();
		MobileCard newCard = new MobileCard(number, userName, password, spk,
				consumAmount , money-consumAmount, 0, 0, 0);
		System.out.println("ע��ɹ���");
		//����ע�Ῠ��������û�
		cardUtil.addCard(newCard);
		//��ʾ
		newCard.showMeg();
		
	}
	
}
