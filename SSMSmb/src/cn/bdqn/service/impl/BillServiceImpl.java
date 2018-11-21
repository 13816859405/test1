package cn.bdqn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.bdqn.dao.BillDao;
import cn.bdqn.pojo.Bill;
import cn.bdqn.service.BillService;
import cn.bdqn.util.PageBean;

@Service("billService")
public class BillServiceImpl implements BillService{

	@Resource(name="billDao")
	private BillDao billDao;

	public PageBean<Bill> findByPage(String queryProductName,
			Integer queryProviderId, Integer queryIsPayment, Integer pageNo,
			int pageSize) {
		PageBean<Bill> pageBean=new PageBean<Bill>();
		pageBean.setPageSize(pageSize);
		int totalCount=billDao.getcount(queryProductName, queryProviderId, queryIsPayment);
		pageBean.setTotalCount(totalCount);
		pageBean.setPageNo(pageNo);
		int from=(pageBean.getPageNo()-1)*pageSize;
		List<Bill> pageList=billDao.fingByPage(queryProductName, queryProviderId, queryIsPayment, from, pageSize);
		pageBean.setPageList(pageList);
		return pageBean;
	}

	public int addbill(Bill bill) {
		// TODO Auto-generated method stub
		return billDao.addbill(bill);
	}

	public Bill findById(Integer bid) {
		// TODO Auto-generated method stub
		return billDao.findById(bid);
	}

	public int updateBill(Bill bill) {
		// TODO Auto-generated method stub
		return billDao.updateBill(bill);
	}

	public int delUser(Integer bid) {
		// TODO Auto-generated method stub
		return billDao.delUser(bid);
	}

	public int count(Integer pid) {
		// TODO Auto-generated method stub
		return billDao.count(pid) ;
	}
	
	
}
