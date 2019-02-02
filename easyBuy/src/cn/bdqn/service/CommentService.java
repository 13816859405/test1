package cn.bdqn.service;

import java.util.List;

import cn.bdqn.dao.CommentDao;
import cn.bdqn.entity.EasyBuyComment;

import cn.bdqn.util.PageBean;

public class CommentService {
	private CommentDao commentDao=new CommentDao();
	
	public PageBean<EasyBuyComment> findcomByPage(int pageNo, int pageSize){
		PageBean<EasyBuyComment> comPage=new PageBean<EasyBuyComment>();
		comPage.setPageSize(pageSize);
		int totalCount=commentDao.getTotalCount();
		comPage.setTotalCount(totalCount);
		comPage.setPageNo(pageNo);
		List<EasyBuyComment> pageList=commentDao.findcomByPage(pageNo, pageSize);
		comPage.setPageList(pageList);
		
		return comPage;
	}

	public int addComment(String nickName, String guestContent) {
		// TODO Auto-generated method stub
		return commentDao.addComment(nickName,guestContent);
	}

	public EasyBuyComment findById(Integer ecId) {
		// TODO Auto-generated method stub
		return commentDao.findById(ecId);
	}

	public int updateCom(EasyBuyComment updateCom) {
		// TODO Auto-generated method stub
		return commentDao.updateCom(updateCom);
	}

	public int delCom(Integer ecId) {
		// TODO Auto-generated method stub
		return commentDao.delCom(ecId);
	}

	
}
