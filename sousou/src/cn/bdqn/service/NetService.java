package cn.bdqn.service;

import cn.bdqn.entity.MobileCard;

/**
 * ��������
 * @author WindLin
 *
 */
public interface NetService {

	/**
	 * 
	 * @param flow ��������
	 * @param card �û���
	 * @return
	 */
	int netPlay(int flow,MobileCard card) throws Exception;
}
