package cn.bdqn.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import cn.bdqn.dao.OrderDao;

import cn.bdqn.entity.AllOrder;
import cn.bdqn.entity.EasyBuyOrder;
import cn.bdqn.entity.EasyBuyOrderDetail;

import cn.bdqn.util.PageBean;

public class OrderService {
	private OrderDao orderDao=new OrderDao();
	
	
	public PageBean<EasyBuyOrder> fingByPage(int pageNo, int pageSize,String eoId,String userName) {
		PageBean<EasyBuyOrder> pageBean=new PageBean<EasyBuyOrder>();
		pageBean.setPageSize(pageSize);
		//Integer userId=orderDao.findIdByName();
		int totalCount=orderDao.getTotalCount(eoId,userName);
		
		pageBean.setTotalCount(totalCount);
		pageBean.setPageNo(pageNo);
		List<EasyBuyOrder> pageList=orderDao.fingByPage(pageBean.getPageNo(),pageBean.getPageSize(),eoId,userName);
		//List<AllOrder> list=orderDao.findOr(eoId);
		for(EasyBuyOrder order:pageList ){
			List<AllOrder> list=orderDao.findOr(order.getEoId());
			order.setAllOrder(list);
		}
		pageBean.setPageList(pageList);
		return pageBean;
	}
	public int addOrder(String eoId, Integer userId, String address,
			double price, int status, int type,String userName) {
		// TODO Auto-generated method stub
		return orderDao.addOrder(eoId,userId,address,price,status,type,userName);
	}
	public int addOrderDetail(String eoId, Integer epId, int quantity, double price) {
		// TODO Auto-generated method stub
		return orderDao.addOrderDetail(eoId,epId,quantity,price);
	}
	/*public List<EasyBuyOrder> findOrder() {
		// TODO Auto-generated method stub
		return orderDao.findOrder();
	}*/
	public List<AllOrder> findSelect(String eoId, String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
