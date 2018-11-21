package cn.bdqn.service;

import cn.bdqn.entity.MobileCard;

/**
 * 短信服务
 * @author WindLin
 *
 */
public interface SendService {

	/**
	 * 
	 * @param count 发送短信的数量
	 * @param card 用户卡
	 * @return
	 */
	int send(int count,MobileCard card)throws Exception;
}
