package cn.bdqn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.bdqn.pojo.Bill;


public interface BillDao {
	

	

	List<Bill> findAll();

	int getcount(
			@Param("queryProductName")
			String queryProductName, 
			@Param("queryProviderId")
			Integer queryProviderId,
			@Param("queryIsPayment")
			Integer queryIsPayment);

	List<Bill> fingByPage(
			@Param("queryProductName")
			String queryProductName, 
			@Param("queryProviderId")
			Integer queryProviderId,
			@Param("queryIsPayment")
			Integer queryIsPayment,
			@Param("from")
			int from, 
			@Param("pageSize")
			int pageSize);

	int addbill(Bill bill);

	Bill findById(Integer bid);

	int updateBill(Bill bill);

	int delUser(Integer bid);

	int count(Integer pid);
}
