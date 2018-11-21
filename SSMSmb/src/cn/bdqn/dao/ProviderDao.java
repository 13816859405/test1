package cn.bdqn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.bdqn.pojo.Provider;




public interface ProviderDao {
	
	
	List<Provider> findAll();

	int getcount(
			@Param("queryProCode")
			String queryProCode,
			@Param("queryProName")
			String queryProName);

	List<Provider> fingByPage(
			@Param("queryProCode")
			String queryProCode,
			@Param("queryProName")
			String queryProName,
			@Param("from")
			int from, 
			@Param("pageSize")
			int pageSize);

	int addpro(Provider provider);

	Provider findById(Integer pid);

	int updatepro(Provider provider);

	int delpro(Integer pid);
}
