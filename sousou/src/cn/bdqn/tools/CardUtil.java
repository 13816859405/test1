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

	private static Map<String, MobileCard> cards; // 已注册用户列表
	private static Map<String, List<ConsumInfo>> consumInfos; // 所有卡号的消费列表
	private static List<Scene> scenes ;//使用场景
	// 初始化
	static {
		cards = new HashMap<String, MobileCard>();
		// 测试用户
		SuperPackage serPackage = new SuperPackage(78, 200, 50, 1024);
		MobileCard super1 = new MobileCard("13924221868", "tom", "123",
				serPackage, 78, 22, 0, 0, 0);
		cards.put("13924221868", super1);
		// 初始化消费列表
		consumInfos = new HashMap<String, List<ConsumInfo>>();
		List<ConsumInfo> clist = new ArrayList<>();
		ConsumInfo info1 = new ConsumInfo("13924221868", "通话", 30);
		ConsumInfo info2 = new ConsumInfo("13924221868", "上网", 30);
		ConsumInfo info3 = new ConsumInfo("13924221868", "短信", 5);
		clist.add(info1);
		clist.add(info2);
		clist.add(info3);
		consumInfos.put("13924221868", clist);
		//初始化使用场景
		scenes = new ArrayList<>();
		Scene scene0 = new Scene("通话", "问候客户,谁知其如此 难缠,通话90分钟", 90);
		Scene scene1 = new Scene("通话", "询问妈妈身体状况,本地通话30分钟", 30);
		Scene scene2 = new Scene("短信", "参与环境保护实施方案问卷调查,发送短信5条", 5);
		Scene scene3 = new Scene("短信", "通知朋友手机换号,发送短信50条", 50);
		Scene scene4 = new Scene("上网", "和女友用微信视频聊天,使用流量1GB", 1024);
		Scene scene5 = new Scene("上网", "晚上手机在线看韩剧,不留神睡着啦!使用流量2GB", 2048);
		scenes.add(scene0);
		scenes.add(scene1);
		scenes.add(scene2);
		scenes.add(scene3);
		scenes.add(scene4);
		scenes.add(scene5);
	}

	
	
	// 注册新卡成功后向已注册卡号中添加用户
	public void addCard(MobileCard card) {
		cards.put(card.getCardNumber(), card);
	}

	// 话费充值
	public void chargeMoney(String number, double money) {

	}

	// 使用嗖嗖
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
				//是否能通话
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

	// 资费说明
	public void showDescription() {
		FileReader reader = null;
		try {
			reader = new FileReader("套餐资费说明.txt");
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

	// 本月账单查询
	public void showAmountDetail(String number) {
		MobileCard card = cards.get(number);
		StringBuffer meg = new StringBuffer();
		meg.append("您的卡号:"+card.getCardNumber()+",当月账单:\n");
		meg.append("套餐资费:"+card.getSerPackage().getPrice()+"元\n");
		meg.append("合计:"+Common.dataFormat(card.getConsumAmount())+"元\n");
		meg.append("帐户余额:"+Common.dataFormat(card.getMoney())+"元\n");
		System.out.println(meg);
	}

	// 套餐余量查询
	public void showRemainDetail(String number) {
		MobileCard card = cards.get(number);
		StringBuffer meg = new StringBuffer();
		int remainTalkTime;
		int remainSmsCount;
		int remainFlow;
		meg.append("您的卡号是"+card.getCardNumber()+",套餐内剩余: \n");
		ServicePackage pack = card.getSerPackage();
		if(pack instanceof TalkPackage){
			TalkPackage tp = (TalkPackage) pack;
			remainTalkTime = tp.getTalkTime()>card.getRealTalkTime()
					?tp.getTalkTime()-card.getRealTalkTime():0;
			remainSmsCount = tp.getSmsCount()>card.getRealSMScount()
					?tp.getSmsCount()-card.getRealSMScount():0;
			meg.append("通话时长:"+remainTalkTime+"分钟\n");
			meg.append("短信条数:"+remainSmsCount+"条");
		}else if(pack instanceof NetPackage){
			NetPackage np = (NetPackage) pack;
			remainFlow = np.getFlow()>card.getRealFlow()?
					np.getFlow()-card.getRealFlow():0;
			meg.append("上网流量:"+remainFlow+"M");
		}else{
			SuperPackage sp = (SuperPackage) pack;
			remainTalkTime = sp.getTalkTime()>card.getRealTalkTime()
					?sp.getTalkTime()-card.getRealTalkTime():0;
			remainSmsCount = sp.getSmsCount()>card.getRealSMScount()
					?sp.getSmsCount()-card.getRealSMScount():0;
			remainFlow = sp.getFlow()>card.getRealFlow()?
					sp.getFlow()-card.getRealFlow():0;
			meg.append("通话时长:"+remainTalkTime+"分钟\n");
			meg.append("短信条数:"+remainSmsCount+"条\n");
			meg.append("上网流量:"+remainFlow+"M");
		}
		System.out.println(meg);
		
	}

	// 打印消费详单
	public void printAmountDetail(String number) {
		Writer fileWriter = null;
		try {
			fileWriter = new FileWriter(number+"消费记录.txt");
			Set<String> numbers = consumInfos.keySet();
			//查询此卡号是否有记录
			boolean isExist = false;
			for (String num : numbers) {
				if(num.equals(number)){
					isExist = true;
					break;
				}
			}
			if(isExist){
				//存在,获得消费列表 
				List<ConsumInfo> clist = consumInfos.get(number);
				StringBuffer content = new StringBuffer("*****"
						+number+"消费记录******\n");
				content.append("序号\t类型\t数据(通话(分钟)/上网(MB)/短信(条))\n");
				for (int i = 0; i < clist.size(); i++) {
					ConsumInfo info = clist.get(i);
					content.append((i+1)+".\t"+info.getType()+"\t"+info.getConsumData()+"\n");
				}
				fileWriter.write(content.toString());
				fileWriter.flush();
				System.out.println("消费记录打印完毕!");
			}else{
				System.out.println("对不起,不存在此号码的消费记录,不能打印!");
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

	// 套餐变更
	public void changingPack(String number, String packNum) {

	}

	// 办理退网
	public void delCard(String number) {
		cards.remove(number);
		System.out.println("卡号"+number+"办理退网成功!");
		System.out.println("谢谢使用!");
	}

	// 根据卡号和密码判断该卡登录
	public boolean isExistCard(String number, String password) {
		Set<String> numbers = cards.keySet();
		for (String num : numbers) {
			if (num.equals(number)) { // 判断卡号
				if (cards.get(num).getPassword().equals(password)) { // 判断密码
					return true;
				}
			}
		}
		return false;
	}

	// 根据卡号判断该卡是否注册
	public boolean isExistCard(String number) {
		Set<String> numbers = cards.keySet();
		for (String num : numbers) {
			if (num.equals(number)) { // 判断卡号
				return true;
			}
		}
		return false;
	}

	// 生成随机卡号
	public String createNumber() {
		Random random = new Random();
		boolean isExist = false; // 判断是否存在此卡号
		String number = "139";
		int temp = 0;
		do {
			isExist = false;
			do {
				temp = random.nextInt(100000000);
			} while (temp < 10000000);
			number = number + temp;
			// 比较
			isExist = this.isExistCard(number);
		} while (isExist);
		return number;
	}

	// 生成指定数量的卡号
	public String[] getNewNumbers(int count) {
		String[] numbers = new String[count];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = this.createNumber();
		}
		return numbers;
	}

	// 添加指定卡号的消费记录
	public void addConsumInfo(String number, ConsumInfo info) {
		Set<String> numbers = consumInfos.keySet();
		List<ConsumInfo> clist = null;
		//查询此卡号是否有记录
		boolean isExist = false;
		for (String num : numbers) {
			if(num.equals(number)){
				isExist = true;
				break;
			}
		}
		if(isExist){//已经有消费过
			clist = consumInfos.get(number);
			clist.add(info);
		}else{
			clist= new ArrayList<>();
			clist.add(info);
		}
		consumInfos.put(number, clist);
	}

	// 根据用户选择的套餐号返回套餐
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
