package cn.bdqn.service;

import java.util.List;

import cn.bdqn.dao.EasyBuyProductDao;

import cn.bdqn.entity.EasyBuyProduct;
import cn.bdqn.util.PageBean;

public class EasyBuyPorductService {
	private EasyBuyProductDao easyBuyProductDao=new EasyBuyProductDao();
	
	public PageBean<EasyBuyProduct> findByProductPage(int pageNo,int pageSize){
		PageBean<EasyBuyProduct> pageBean=new PageBean<EasyBuyProduct>();
		pageBean.setPageSize(pageSize);
		int totalCount=easyBuyProductDao.getProductCount();
		pageBean.setTotalCount(totalCount);
		pageBean.setPageNo(pageNo);
		List<EasyBuyProduct> pageList=easyBuyProductDao.findByProductPage(pageBean.getPageNo(),pageBean.getPageSize());
		pageBean.setPageList(pageList);
		return pageBean;
	}

	public EasyBuyProduct findById(Integer epId) {
		// TODO Auto-generated method stub
		return easyBuyProductDao.findById(epId);
	}

	public PageBean<EasyBuyProduct> findProductList(int pageNo, int pageSize,
			Integer epcId) {
		PageBean<EasyBuyProduct> pageBean=new PageBean<EasyBuyProduct>();
		pageBean.setPageSize(pageSize);
		int totalCount=easyBuyProductDao.classProducCount(epcId);
		pageBean.setTotalCount(totalCount);
		pageBean.setPageNo(pageNo);
		List<EasyBuyProduct> pageList=easyBuyProductDao.findProductList(pageBean.getPageNo(),pageBean.getPageSize(),epcId);
		pageBean.setPageList(pageList);
		return pageBean;
	}

	public int addProduct(String epName, String description, Integer epcId,
			double price, Integer stock,String pic) {
		// TODO Auto-generated method stub
		return easyBuyProductDao.addProduct(epName,description,epcId,price,stock,pic);
	}

	public int upateProduct(String epName, String description, Integer epcId,
			double price, Integer stock,Integer epId) {
		
		return easyBuyProductDao.upateProduct(epName,description,epcId,price,stock,epId);
	}

	public int delProduct(Integer epId) {
		// TODO Auto-generated method stub
		return easyBuyProductDao.delProduct(epId);
	}

	public int upateProduct(EasyBuyProduct product) {
		// TODO Auto-generated method stub
		return easyBuyProductDao.upateProduct(product);
	}
}
