package cn.bdqn.dao;

import java.util.List;

import cn.bdqn.entity.EasyBuyNews;

public interface NewsDao {
	
	List<EasyBuyNews> findAll();
}
