package cn.bdqn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.bdqn.dao.NewsDao;
import cn.bdqn.entity.EasyBuyNews;

@Service("newsService")
public class NewsServiceImpl implements NewsService{
	@Resource(name="newsDao")
	private NewsDao newsDao;

	public List<EasyBuyNews> findAll() {
		// TODO Auto-generated method stub
		return newsDao.findAll();
	}
	
	
	
}
