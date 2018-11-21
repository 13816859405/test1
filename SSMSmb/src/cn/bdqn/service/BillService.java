package cn.bdqn.service;

import cn.bdqn.pojo.Bill;
import cn.bdqn.util.PageBean;

public interface BillService {

	PageBean<Bill> findByPage(String queryProductName, Integer queryProviderId,
			Integer queryIsPayment, Integer pageNo, int pageSize);

	int addbill(Bill bill);

	Bill findById(Integer bid);

	int updateBill(Bill bill);

	int delUser(Integer bid);

	int count(Integer pid);

	

	

}
