package cn.bdqn.service;

import cn.bdqn.entity.MobileCard;

/**
 * ���ŷ���
 * @author WindLin
 *
 */
public interface SendService {

	/**
	 * 
	 * @param count ���Ͷ��ŵ�����
	 * @param card �û���
	 * @return
	 */
	int send(int count,MobileCard card)throws Exception;
}
