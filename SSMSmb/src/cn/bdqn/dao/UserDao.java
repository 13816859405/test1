package cn.bdqn.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.bdqn.pojo.User;

public interface UserDao {
	
	//登录
	User findByUserCode(String userCode);
	
	int count();//统计user表人数
	
	List<User> findAll();
	
	int addUser(User user);
	int updateUser(User user);
	int deleteUser(int uid);
	
	List<User> findUsersByRoleId(int roleId);
	
	List<User> findByRoles(Integer[] roleIds);
	
	List<User> findByRoles2(List<Integer> roleIds);
	
	List<User> findByRoles3(Map<String, Object> conMap);
	
	List<User> findByCondition123(User user);
	
	User findById(int uid);//根据id查询用户
	
	List<User> findByName(String name);
	
	List<User> findByCondition1(User user);
	
	List<User> findByCondition2(Map<String,Object> map);
	
	List<User> findByCondition3(
			@Param("userName")
			String name,
			@Param("userRole")
			Integer userRole,
			@Param("gender")
			Integer gender
			);
	
	List<User> findTwoAll();
	
	List<User> findByPage(
			@Param("from")
			int from,
			@Param("pageSize")
			int pageSize
			);

	int upDateByPwd(User user);

	int getcount(
			@Param("queryName")
			String queryname,
			@Param("roleId")
			Integer roleId);

	List<User> findByPage2(
			@Param("queryName")
			String queryname, 
			@Param("roleId")
			Integer roleId, 
			@Param("from")
			int from,
			@Param("pageSize")
			int pageSize);
			
}
