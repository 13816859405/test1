package cn.bdqn.service;

import cn.bdqn.entity.MobileCard;

/**
 * 上网服务
 * @author WindLin
 *
 */
public interface NetService {

	/**
	 * 
	 * @param flow 上网流量
	 * @param card 用户卡
	 * @return
	 */
	int netPlay(int flow,MobileCard card) throws Exception;
}
