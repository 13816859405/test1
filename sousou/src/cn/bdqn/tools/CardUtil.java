package cn.bdqn.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import cn.bdqn.entity.ConsumInfo;
import cn.bdqn.entity.MobileCard;
import cn.bdqn.entity.NetPackage;
import cn.bdqn.entity.Scene;
import cn.bdqn.entity.ServicePackage;
import cn.bdqn.entity.SuperPackage;
import cn.bdqn.entity.TalkPackage;
import cn.bdqn.service.CallService;
import cn.bdqn.service.NetService;
import cn.bdqn.service.SendService;

public class CardUtil {

	private static Map<String, MobileCard> cards; // ��ע���û��б�
	private static Map<String, List<ConsumInfo>> consumInfos; // ���п��ŵ������б�
	private static List<Scene> scenes ;//ʹ�ó���
	// ��ʼ��
	static {
		cards = new HashMap<String, MobileCard>();
		// �����û�
		SuperPackage serPackage = new SuperPackage(78, 200, 50, 1024);
		MobileCard super1 = new MobileCard("13924221868", "tom", "123",
				serPackage, 78, 22, 0, 0, 0);
		cards.put("13924221868", super1);
		// ��ʼ�������б�
		consumInfos = new HashMap<String, List<ConsumInfo>>();
		List<ConsumInfo> clist = new ArrayList<>();
		ConsumInfo info1 = new ConsumInfo("13924221868", "ͨ��", 30);
		ConsumInfo info2 = new ConsumInfo("13924221868", "����", 30);
		ConsumInfo info3 = new ConsumInfo("13924221868", "����", 5);
		clist.add(info1);
		clist.add(info2);
		clist.add(info3);
		consumInfos.put("13924221868", clist);
		//��ʼ��ʹ�ó���
		scenes = new ArrayList<>();
		Scene scene0 = new Scene("ͨ��", "�ʺ�ͻ�,˭֪����� �Ѳ�,ͨ��90����", 90);
		Scene scene1 = new Scene("ͨ��", "ѯ����������״��,����ͨ��30����", 30);
		Scene scene2 = new Scene("����", "���뻷������ʵʩ�����ʾ����,���Ͷ���5��", 5);
		Scene scene3 = new Scene("����", "֪ͨ�����ֻ�����,���Ͷ���50��", 50);
		Scene scene4 = new Scene("����", "��Ů����΢����Ƶ����,ʹ������1GB", 1024);
		Scene scene5 = new Scene("����", "�����ֻ����߿�����,������˯����!ʹ������2GB", 2048);
		scenes.add(scene0);
		scenes.add(scene1);
		scenes.add(scene2);
		scenes.add(scene3);
		scenes.add(scene4);
		scenes.add(scene5);
	}

	
	
	// ע���¿��ɹ�������ע�Ῠ��������û�
	public void addCard(MobileCard card) {
		cards.put(card.getCardNumber(), card);
	}

	// ���ѳ�ֵ
	public void chargeMoney(String number, double money) {

	}

	// ʹ����
	public void useSoso(String number) {
		MobileCard card = cards.get(number);
		ServicePackage pack = card.getSerPackage();
		Random random = new Random();
		int ranNum = 0;
		int temp = 0;
		do {
			ranNum = random.nextInt(6);
			Scene scene = scenes.get(ranNum);
			switch (ranNum) {
			case 0:
			case 1:
				//�Ƿ���ͨ��
				if(pack instanceof CallService){
					System.out.println(scene.getDescription());
					CallService callService = (CallService) pack;
					try {
						temp = callService.call(scene.getData(), card);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					addConsumInfo(number, new ConsumInfo(number, scene.getType(), temp));
					break;
				}else{
					continue;
				}
			case 2:
			case 3:
				if(pack instanceof SendService){
					System.out.println(scene.getDescription());
					SendService sendService = (SendService)pack;
					try {
						temp = sendService.send(scene.getData(), card);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					addConsumInfo(number, new ConsumInfo(number, scene.getType(), temp));
					break;
				}else{
					continue;
				}
			case 4:
			case 5:
				if(pack instanceof NetService){
					System.out.println(scene.getDescription());
					NetService netService = (NetService)pack;
					try {
						temp = netService.netPlay(scene.getData(), card);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					addConsumInfo(number, new ConsumInfo(number, scene.getType(), temp));
					break;
				}else{
					continue;
				}
			}
			break;
		} while (true);
	}

	// �ʷ�˵��
	public void showDescription() {
		FileReader reader = null;
		try {
			reader = new FileReader("�ײ��ʷ�˵��.txt");
			BufferedReader br = new BufferedReader(reader);
			String line;
			while((line = br.readLine())!=null){
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	// �����˵���ѯ
	public void showAmountDetail(String number) {
		MobileCard card = cards.get(number);
		StringBuffer meg = new StringBuffer();
		meg.append("���Ŀ���:"+card.getCardNumber()+",�����˵�:\n");
		meg.append("�ײ��ʷ�:"+card.getSerPackage().getPrice()+"Ԫ\n");
		meg.append("�ϼ�:"+Common.dataFormat(card.getConsumAmount())+"Ԫ\n");
		meg.append("�ʻ����:"+Common.dataFormat(card.getMoney())+"Ԫ\n");
		System.out.println(meg);
	}

	// �ײ�������ѯ
	public void showRemainDetail(String number) {
		MobileCard card = cards.get(number);
		StringBuffer meg = new StringBuffer();
		int remainTalkTime;
		int remainSmsCount;
		int remainFlow;
		meg.append("���Ŀ�����"+card.getCardNumber()+",�ײ���ʣ��: \n");
		ServicePackage pack = card.getSerPackage();
		if(pack instanceof TalkPackage){
			TalkPackage tp = (TalkPackage) pack;
			remainTalkTime = tp.getTalkTime()>card.getRealTalkTime()
					?tp.getTalkTime()-card.getRealTalkTime():0;
			remainSmsCount = tp.getSmsCount()>card.getRealSMScount()
					?tp.getSmsCount()-card.getRealSMScount():0;
			meg.append("ͨ��ʱ��:"+remainTalkTime+"����\n");
			meg.append("��������:"+remainSmsCount+"��");
		}else if(pack instanceof NetPackage){
			NetPackage np = (NetPackage) pack;
			remainFlow = np.getFlow()>card.getRealFlow()?
					np.getFlow()-card.getRealFlow():0;
			meg.append("��������:"+remainFlow+"M");
		}else{
			SuperPackage sp = (SuperPackage) pack;
			remainTalkTime = sp.getTalkTime()>card.getRealTalkTime()
					?sp.getTalkTime()-card.getRealTalkTime():0;
			remainSmsCount = sp.getSmsCount()>card.getRealSMScount()
					?sp.getSmsCount()-card.getRealSMScount():0;
			remainFlow = sp.getFlow()>card.getRealFlow()?
					sp.getFlow()-card.getRealFlow():0;
			meg.append("ͨ��ʱ��:"+remainTalkTime+"����\n");
			meg.append("��������:"+remainSmsCount+"��\n");
			meg.append("��������:"+remainFlow+"M");
		}
		System.out.println(meg);
		
	}

	// ��ӡ�����굥
	public void printAmountDetail(String number) {
		Writer fileWriter = null;
		try {
			fileWriter = new FileWriter(number+"���Ѽ�¼.txt");
			Set<String> numbers = consumInfos.keySet();
			//��ѯ�˿����Ƿ��м�¼
			boolean isExist = false;
			for (String num : numbers) {
				if(num.equals(number)){
					isExist = true;
					break;
				}
			}
			if(isExist){
				//����,��������б� 
				List<ConsumInfo> clist = consumInfos.get(number);
				StringBuffer content = new StringBuffer("*****"
						+number+"���Ѽ�¼******\n");
				content.append("���\t����\t����(ͨ��(����)/����(MB)/����(��))\n");
				for (int i = 0; i < clist.size(); i++) {
					ConsumInfo info = clist.get(i);
					content.append((i+1)+".\t"+info.getType()+"\t"+info.getConsumData()+"\n");
				}
				fileWriter.write(content.toString());
				fileWriter.flush();
				System.out.println("���Ѽ�¼��ӡ���!");
			}else{
				System.out.println("�Բ���,�����ڴ˺�������Ѽ�¼,���ܴ�ӡ!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// �ײͱ��
	public void changingPack(String number, String packNum) {

	}

	// ��������
	public void delCard(String number) {
		cards.remove(number);
		System.out.println("����"+number+"���������ɹ�!");
		System.out.println("ллʹ��!");
	}

	// ���ݿ��ź������жϸÿ���¼
	public boolean isExistCard(String number, String password) {
		Set<String> numbers = cards.keySet();
		for (String num : numbers) {
			if (num.equals(number)) { // �жϿ���
				if (cards.get(num).getPassword().equals(password)) { // �ж�����
					return true;
				}
			}
		}
		return false;
	}

	// ���ݿ����жϸÿ��Ƿ�ע��
	public boolean isExistCard(String number) {
		Set<String> numbers = cards.keySet();
		for (String num : numbers) {
			if (num.equals(number)) { // �жϿ���
				return true;
			}
		}
		return false;
	}

	// �����������
	public String createNumber() {
		Random random = new Random();
		boolean isExist = false; // �ж��Ƿ���ڴ˿���
		String number = "139";
		int temp = 0;
		do {
			isExist = false;
			do {
				temp = random.nextInt(100000000);
			} while (temp < 10000000);
			number = number + temp;
			// �Ƚ�
			isExist = this.isExistCard(number);
		} while (isExist);
		return number;
	}

	// ����ָ�������Ŀ���
	public String[] getNewNumbers(int count) {
		String[] numbers = new String[count];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = this.createNumber();
		}
		return numbers;
	}

	// ���ָ�����ŵ����Ѽ�¼
	public void addConsumInfo(String number, ConsumInfo info) {
		Set<String> numbers = consumInfos.keySet();
		List<ConsumInfo> clist = null;
		//��ѯ�˿����Ƿ��м�¼
		boolean isExist = false;
		for (String num : numbers) {
			if(num.equals(number)){
				isExist = true;
				break;
			}
		}
		if(isExist){//�Ѿ������ѹ�
			clist = consumInfos.get(number);
			clist.add(info);
		}else{
			clist= new ArrayList<>();
			clist.add(info);
		}
		consumInfos.put(number, clist);
	}

	// �����û�ѡ����ײͺŷ����ײ�
	public ServicePackage createPack(int packId) {
		switch (packId) {
		case 1:
			return new TalkPackage(58,200,50);
		case 2:
			return new NetPackage(68,1024*6);
		case 3:
			return new SuperPackage(78,200,100,1024*5);
		default:
			return null;
		}

	}
}
