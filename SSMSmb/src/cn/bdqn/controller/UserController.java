package cn.bdqn.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;






import com.alibaba.fastjson.JSON;

import cn.bdqn.pojo.Role;
import cn.bdqn.pojo.User;
import cn.bdqn.service.RoleService;
import cn.bdqn.service.UserService;
import cn.bdqn.util.PageBean;

@Controller
public class UserController {
	
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="roleService")
	private RoleService roleService;
	
	//使用AJAX请求用户角色列表
		@RequestMapping(value="/getrolelist")
		@ResponseBody
		public List<Role> getrolelist(){
			List<Role> roleList=roleService.findAll();
			return roleList;
		}

	//使用AJAX请求用户角色列表
		@RequestMapping(value="/checkUserCode")
		@ResponseBody
		public boolean checkUserCode(String userCode){
			User user=userService.fingByUserCode(userCode);
			if(user!=null){
				return true;
			}else{
				return false;
			}
		}
	
	//显示查看userview.html
	@RequestMapping(value="/userview.html")
	public String userview(
			@RequestParam(required=true)
			Integer uid,
			Model model){
		User user=userService.findById(uid);
		model.addAttribute("user",user);
		return "userview";
	}
	
	//删除
	@ResponseBody
	@RequestMapping(value="/userdel")
	public Object delUser(Integer uid){
		
		int ret=userService.delUser(uid);
		System.out.println("========"+ret);
		User user=userService.findById(uid);
		/*if(user.getUserName()==null){
			return "notexist";
		}else{*/
			if(ret>0){
				return "success";
			}else{
				return "false";
			}
		/*}*/
		
				
	}
	
	//显示修改showModify.html
	@RequestMapping(value="/showModify.html")
	public String showModify(
			@RequestParam(required=true)
			Integer uid,
			Model model){
		User user=userService.findById(uid);
		model.addAttribute("user",user);
		return "usermodify";
	}
	
	//修改
	@RequestMapping(value="/updateuser.html")
	public String updateUser(User user,Model model,HttpSession session){
		User userLogin=(User) session.getAttribute("userSession");
		user.setModifyBy(userLogin.getId());
		user.setModifyDate(new Date());
		int ret=userService.updateUser(user);
		if(ret>0){
			return "redirect: userList.html";
		}else{
			return "error";
		}
			
	}
		
	//显示新增
	@RequestMapping(value="/useradd.html")
	public String showAdd(
			@ModelAttribute("user")
			User user,
			Model model){
		return "useradd";
	}
	
	//新增用户
	@RequestMapping(value="/adduser.html")
	public String addUser(User user,Model model,HttpSession session){
		User userLogin=(User) session.getAttribute("userSession");
		user.setCreatedBy(userLogin.getId());
		user.setCreationDate(new Date());
		int ret=userService.addUser(user);
		if(ret>0){
			return "redirect: userList.html";
		}else{
			return "error";
		}
		
	}
	
	//显示新增2
	@RequestMapping(value="/useradd2.html")
	public String showAdd2(
			@ModelAttribute("user")
			User user,
			Model model){
		return "useradd3";
	}
	
	//新增用户2
	@RequestMapping(value="/douseradd2.html")
	public String addUser2(
			@Valid
			User user,
			BindingResult bindingResult, Model model,HttpSession session){
		if(bindingResult.hasErrors()){
			return "useradd3";
		}
		User userLogin=(User) session.getAttribute("userSession");
		user.setCreatedBy(userLogin.getId());
		user.setCreationDate(new Date());
		int ret=userService.addUser(user);
		if(ret>0){
			return "redirect: userList.html";
		}else{
			return "error";
		}
			
	}
		
	//显示所有
	//@RequestMapping("/userList.html")
	public String showAll(Model model){
		List<User> userList=userService.findAll();
		model.addAttribute("userList",userList);
		List<Role> roleList=roleService.findAll();
		model.addAttribute("roleList",roleList);
		return "userlist";
	}
	
	//显示所有
	@RequestMapping("/userList.html")
	public String showByPage(
			@RequestParam(required=false,defaultValue="1")
			Integer pageNo,
			@RequestParam(required=false)
			String queryname,
			@RequestParam(required=false)
			Integer queryUserRole,
			Model model){
		
		int pageSize=5;
		PageBean<User> pageBean=userService.findByPage(queryname,queryUserRole,pageNo,pageSize);
		//查询所有角色
		List<Role> roleList=roleService.findAll();
		model.addAttribute("roleList",roleList);
		model.addAttribute("pageBean",pageBean);
		//System.out.println(queryname);
		model.addAttribute("queryUserName",queryname);
		model.addAttribute("queryUserRole",queryUserRole);
		return "userlist";
	}
		
	@RequestMapping("/index.html")
	public String showLogin(){
		return "login";
	}
	
	@RequestMapping(value="/login.html")
	public String doLogin(String userCode,String userPassword,Model model,HttpSession session){
		User userLogin=userService.findByLogin(userCode, userPassword);
		if(userLogin!=null){
			//int a=5/0;
			session.setAttribute("userSession", userLogin);
			return "redirect:success.html";//重定向
		}else{
			model.addAttribute("error","登录失败，用户名或密码错误！");
			return "login";//转发
		}
	}
	
	//用户登录验证
	@RequestMapping(value="/success.html")
	public String success(HttpSession session){
		User user=(User) session.getAttribute("userSession");
		if(user==null){
			return "redirect:login.html";
		}else{
			return "frame";
		}
		
	}
	
	//注销
	@RequestMapping(value="/logout.html")
	public String logout(HttpSession session){
		//session.removeAttribute("userSession");
		session.invalidate();
		return "redirect:index.html";
	}
	
	//显示修改
	@RequestMapping(value="/pwdmodify.html")
	public String showmo(){
		return "pwdmodify";
	}
	
	//使用AJAX验证旧密码
	@RequestMapping(value="/checkOldPwd.html")
	@ResponseBody
	public Object checkOldPwd(String oldpassword,HttpSession session){
		Map<String, String> retMap=new HashMap<String, String>();
		//调用业务层判断checkOldPwd是否正确
		if(oldpassword==null||oldpassword.equals("")){
			retMap.put("result", "error");
		}else{
			User user=(User) session.getAttribute("userSession");
			if(user==null){
				retMap.put("result", "sessionerror");
			}else{
				if(user.getUserPassword().equals(oldpassword)){
					retMap.put("result", "true");
				}else{
					retMap.put("result", "false");
				}
			}
		}
		return JSON.toJSONString(retMap);
	}
	
	//修改密码
	@RequestMapping(value="/domodify.html")
	public String domodify(String newpassword, HttpSession session){
		User user=(User) session.getAttribute("userSession");
		user.setUserPassword(newpassword);
		int ret=userService.upDateByPwd(user);
		if(ret>0){
			session.setAttribute("userSession", user);
			return "redirect:success.html";
		}else{
			return "error";
		}
	}
	
	//处理异常要跳转的页面
	@ExceptionHandler(RuntimeException.class)
	public String handlerException(RuntimeException exception,HttpServletRequest request){
		request.setAttribute("exception", exception);
		return "error";
	}
}
