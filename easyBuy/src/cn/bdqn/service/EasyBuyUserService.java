package cn.bdqn.service;

import java.sql.Date;
import java.util.List;

import cn.bdqn.dao.EasyBuyUserDao;
import cn.bdqn.entity.EasyBuyUser;
import cn.bdqn.entity.EasybuyNews;
import cn.bdqn.util.PageBean;

public class EasyBuyUserService {
	EasyBuyUserDao userDao=new EasyBuyUserDao();
	//µÇÂ¼ÑéÖ¤
	public EasyBuyUser findByNamePad(String name,String pwd){
		return userDao.findByNamePad(name, pwd);
	}
	
	//×¢²á
		public int addUser(String userName,String nickName,String userPwd,
				Integer userSex,Date birthday,String identityCode,String email,
				String mobile,String address,Integer status){
			return userDao.addUser(userName, nickName, userPwd, userSex, birthday, identityCode, email, mobile, address, status);
		}

		public String findByNamePad(String userName) {
			// TODO Auto-generated method stub
			return userDao.findByNamePad(userName);
		}

		public PageBean<EasyBuyUser> fingByPage(int pageNo,int pageSize) {
			PageBean<EasyBuyUser> pageBean=new PageBean<EasyBuyUser>();
			pageBean.setPageSize(pageSize);
			int totalCount=userDao.getTotalCount();
			pageBean.setTotalCount(totalCount);
			pageBean.setPageNo(pageNo);
			List<EasyBuyUser> pageList=userDao.findByPage(pageBean.getPageNo(),pageBean.getPageSize());
			pageBean.setPageList(pageList);
			return pageBean;
		}

		public EasyBuyUser findById(Integer userId) {
			// TODO Auto-generated method stub
			return userDao.findById(userId);
		}

		public int updateUser(EasyBuyUser updateUser) {
			// TODO Auto-generated method stub
			return userDao.updateUser(updateUser);
		}

		public int delUser(Integer userId) {
			// TODO Auto-generated method stub
			return userDao.delUser(userId);
		}
}
