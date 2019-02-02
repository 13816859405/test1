package cn.bdqn.service;

import java.util.List;

import cn.bdqn.dao.EasyBuyNewsDao;
import cn.bdqn.entity.EasybuyNews;

import cn.bdqn.util.PageBean;

public class EasyBuyNewsService {
	EasyBuyNewsDao easyBuyNewsDao=new EasyBuyNewsDao();
	
	public int addNews(String title, String content) {
		// TODO Auto-generated method stub
		return easyBuyNewsDao.addNews(title,content);
	}
	
	//∑÷“≥≤È—Ø
	public PageBean<EasybuyNews> fingByPage(int pageNo,int pageSize){
		PageBean<EasybuyNews> pageBean=new PageBean<EasybuyNews>();
		pageBean.setPageSize(pageSize);
		int totalCount=easyBuyNewsDao.getTotalCount();
		pageBean.setTotalCount(totalCount);
		pageBean.setPageNo(pageNo);
		List<EasybuyNews> pageList=easyBuyNewsDao.findByPage(pageBean.getPageNo(),pageBean.getPageSize());
		pageBean.setPageList(pageList);
		return pageBean;
	}
	
	public List<EasybuyNews> findNews(){
		return easyBuyNewsDao.findNews();
	}

	public EasybuyNews findById(Integer enId) {
		// TODO Auto-generated method stub
		return easyBuyNewsDao.findById(enId);
	}

	public int updateNews(EasybuyNews updateNews) {
		// TODO Auto-generated method stub
		return easyBuyNewsDao.updateNews(updateNews);
	}

	public int delNews(Integer enId) {
		// TODO Auto-generated method stub
		return easyBuyNewsDao.delNews(enId);
	}

}
