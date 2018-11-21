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
	
	//ʹ��AJAX�����û���ɫ�б�
		@RequestMapping(value="/getrolelist")
		@ResponseBody
		public List<Role> getrolelist(){
			List<Role> roleList=roleService.findAll();
			return roleList;
		}

	//ʹ��AJAX�����û���ɫ�б�
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
	
	//��ʾ�鿴userview.html
	@RequestMapping(value="/userview.html")
	public String userview(
			@RequestParam(required=true)
			Integer uid,
			Model model){
		User user=userService.findById(uid);
		model.addAttribute("user",user);
		return "userview";
	}
	
	//ɾ��
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
	
	//��ʾ�޸�showModify.html
	@RequestMapping(value="/showModify.html")
	public String showModify(
			@RequestParam(required=true)
			Integer uid,
			Model model){
		User user=userService.findById(uid);
		model.addAttribute("user",user);
		return "usermodify";
	}
	
	//�޸�
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
		
	//��ʾ����
	@RequestMapping(value="/useradd.html")
	public String showAdd(
			@ModelAttribute("user")
			User user,
			Model model){
		return "useradd";
	}
	
	//�����û�
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
	
	//��ʾ����2
	@RequestMapping(value="/useradd2.html")
	public String showAdd2(
			@ModelAttribute("user")
			User user,
			Model model){
		return "useradd3";
	}
	
	//�����û�2
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
		
	//��ʾ����
	//@RequestMapping("/userList.html")
	public String showAll(Model model){
		List<User> userList=userService.findAll();
		model.addAttribute("userList",userList);
		List<Role> roleList=roleService.findAll();
		model.addAttribute("roleList",roleList);
		return "userlist";
	}
	
	//��ʾ����
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
		//��ѯ���н�ɫ
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
			return "redirect:success.html";//�ض���
		}else{
			model.addAttribute("error","��¼ʧ�ܣ��û������������");
			return "login";//ת��
		}
	}
	
	//�û���¼��֤
	@RequestMapping(value="/success.html")
	public String success(HttpSession session){
		User user=(User) session.getAttribute("userSession");
		if(user==null){
			return "redirect:login.html";
		}else{
			return "frame";
		}
		
	}
	
	//ע��
	@RequestMapping(value="/logout.html")
	public String logout(HttpSession session){
		//session.removeAttribute("userSession");
		session.invalidate();
		return "redirect:index.html";
	}
	
	//��ʾ�޸�
	@RequestMapping(value="/pwdmodify.html")
	public String showmo(){
		return "pwdmodify";
	}
	
	//ʹ��AJAX��֤������
	@RequestMapping(value="/checkOldPwd.html")
	@ResponseBody
	public Object checkOldPwd(String oldpassword,HttpSession session){
		Map<String, String> retMap=new HashMap<String, String>();
		//����ҵ����ж�checkOldPwd�Ƿ���ȷ
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
	
	//�޸�����
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
	
	//�����쳣Ҫ��ת��ҳ��
	@ExceptionHandler(RuntimeException.class)
	public String handlerException(RuntimeException exception,HttpServletRequest request){
		request.setAttribute("exception", exception);
		return "error";
	}
}
