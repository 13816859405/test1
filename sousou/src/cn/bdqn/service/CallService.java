package cn.bdqn.service;

import cn.bdqn.entity.MobileCard;
/**
 * 通话服务
 * @author WindLin
 *
 */
public interface CallService {

	/**
	 *  通话服务
	 * @param minCount 通话分钟数
	 * @param card 用户卡
	 * @return
	 */
	int call(int minCount,MobileCard card)throws Exception;
}
