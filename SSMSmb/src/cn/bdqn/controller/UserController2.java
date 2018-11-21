package cn.bdqn.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bdqn.pojo.User;
import cn.bdqn.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController2 {
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(value="/view/{uid}")
	public String view(
			@PathVariable
			Integer uid,Model model){
		User user=userService.findById(uid);
		model.addAttribute("user",user);
		return "usermodify";
	}
	
	//É¾³ý
	@ResponseBody
	@RequestMapping(value="/del/{uid}")
	public Object delUser(
			@PathVariable
			Integer uid,Model model){
		Map<String, String> retMap=new HashMap<String, String>();
		if(uid==null){
			retMap.put("ret", "notexist");
		}else{
			int ret=userService.delUser(uid);
			if(ret>0){
				retMap.put("ret", "true");
			}else{
				retMap.put("ret", "false");
			}
		}
		return retMap;
	}
	
	//ÏÔÊ¾
	@ResponseBody
	@RequestMapping(value="/showUser/{uid}")
	public User showUser(
			@PathVariable
			Integer uid){
		System.out.println(uid);
		User user=userService.findById(uid);
		return user;
	}
}
