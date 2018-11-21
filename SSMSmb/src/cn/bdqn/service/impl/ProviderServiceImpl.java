package cn.bdqn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.bdqn.dao.ProviderDao;


import cn.bdqn.pojo.Provider;
import cn.bdqn.service.ProviderService;
import cn.bdqn.util.PageBean;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService{
	
	@Resource(name="providerDao")
	private ProviderDao providerDao;

	public List<Provider> findAll() {
		// TODO Auto-generated method stub
		return providerDao.findAll();
	}

	public PageBean<Provider> findByPage(String queryProCode,
			String queryProName, Integer pageNo, int pageSize) {
		PageBean<Provider> pageBean=new PageBean<Provider>();
		pageBean.setPageSize(pageSize);
		int totalCount=providerDao.getcount(queryProCode, queryProName);
		pageBean.setTotalCount(totalCount);
		pageBean.setPageNo(pageNo);
		int from=(pageBean.getPageNo()-1)*pageSize;
		List<Provider> pageList=providerDao.fingByPage(queryProCode, queryProName, from, pageSize);
		pageBean.setPageList(pageList);
		return pageBean;
	}

	public int addpro(Provider provider) {
		// TODO Auto-generated method stub
		return providerDao.addpro(provider);
	}

	public Provider findById(Integer pid) {
		// TODO Auto-generated method stub
		return providerDao.findById(pid);
	}

	public int updatepro(Provider provider) {
		// TODO Auto-generated method stub
		return providerDao.updatepro(provider);
	}

	public int delpro(Integer pid) {
		// TODO Auto-generated method stub
		return providerDao.delpro(pid);
	}
	
}
