package cn.bdqn.service;

import java.util.List;

import cn.bdqn.pojo.User;
import cn.bdqn.util.PageBean;

public interface UserService {
	
	//µÇÂ¼
	User findByLogin(String userCode,String upwd);

	int upDateByPwd(User user);

	List<User> findAll();

	PageBean<User> findByPage(String queryname, Integer roleId, Integer pageNo, int pageSize);

	User fingByUserCode(String userCode);

	int addUser(User user);

	User findById(Integer uid);

	int updateUser(User user);

	int delUser(Integer uid);
}
