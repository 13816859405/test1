package cn.bdqn.service;

import cn.bdqn.entity.MobileCard;
/**
 * ͨ������
 * @author WindLin
 *
 */
public interface CallService {

	/**
	 *  ͨ������
	 * @param minCount ͨ��������
	 * @param card �û���
	 * @return
	 */
	int call(int minCount,MobileCard card)throws Exception;
}
