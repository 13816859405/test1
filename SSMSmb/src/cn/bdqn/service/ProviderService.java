package cn.bdqn.service;

import java.util.List;

import cn.bdqn.pojo.Provider;
import cn.bdqn.util.PageBean;

public interface ProviderService {

	List<Provider> findAll();

	PageBean<Provider> findByPage(String queryProCode, String queryProName,
			Integer pageNo, int pageSize);

	int addpro(Provider provider);

	Provider findById(Integer pid);

	int updatepro(Provider provider);

	int delpro(Integer pid);

	

}
