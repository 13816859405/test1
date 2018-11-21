package cn.bdqn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.bdqn.dao.UserDao;
import cn.bdqn.pojo.User;
import cn.bdqn.service.UserService;
import cn.bdqn.util.PageBean;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource(name="userDao")
	private UserDao userDao;
	
	public User findByLogin(String userCode, String upwd) {
		User user=userDao.findByUserCode(userCode);
		if(user!=null&&user.getUserPassword().equals(upwd)){
			return user;
		}else{
			return null;
		}
	}

	public int upDateByPwd(User user) {
		// TODO Auto-generated method stub
		return userDao.upDateByPwd(user);
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	public PageBean<User> findByPage(String queryname, Integer roleId,Integer pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageBean<User> pageBean=new PageBean<User>();
		pageBean.setPageSize(pageSize);
		int totalCount=userDao.getcount(queryname,roleId);
		pageBean.setTotalCount(totalCount);
		pageBean.setPageNo(pageNo);
		int from=(pageBean.getPageNo()-1)*pageSize;
		List<User> pageList=userDao.findByPage2(queryname,roleId,from,pageSize);
		pageBean.setPageList(pageList);
		
		return pageBean;
	}

	public User fingByUserCode(String userCode) {
		// TODO Auto-generated method stub
		return userDao.findByUserCode(userCode);
	}

	public int addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}

	public User findById(Integer uid) {
		// TODO Auto-generated method stub
		return userDao.findById(uid);
	}

	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	public int delUser(Integer uid) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(uid);
	}

}
