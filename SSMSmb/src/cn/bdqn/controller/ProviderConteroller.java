package cn.bdqn.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.bdqn.pojo.Provider;
import cn.bdqn.pojo.User;
import cn.bdqn.service.BillService;
import cn.bdqn.service.ProviderService;
import cn.bdqn.util.PageBean;



@Controller
@RequestMapping(value="/pro")
public class ProviderConteroller {
	
	@Resource(name="providerService")
	private ProviderService providerService;
	@Resource(name="billService")
	private BillService billService;
	
	//分页查询
		@RequestMapping(value="/providerlist.html")
		public String showByPage(
				@RequestParam(required=false,defaultValue="1")
				Integer pageNo,
				@RequestParam(required=false)
				String queryProCode,
				@RequestParam(required=false)
				String queryProName,
				Model model){
			int pageSize=5;
			PageBean<Provider> pageBean=providerService.findByPage(queryProCode,queryProName,pageNo,pageSize);
			//查询所有供应商
			//List<Provider> proList=providerService.findAll();
			model.addAttribute("pageBean",pageBean);
			//System.out.println(queryname);
			model.addAttribute("queryProCode",queryProCode);
			model.addAttribute("queryProName",queryProName);
			
			return "providerlist";
			
		}
	//显示新增
	@RequestMapping(value="/proadd.html")
	public String showAdd(
			@ModelAttribute("provider")
			Provider provider,
			Model model){
		return "provideradd";
	}
	//新增供应商
	@RequestMapping(value="/addpro.html")
	public String addUser(Provider provider,Model model,HttpSession session){
		User userLogin=(User) session.getAttribute("userSession");
		provider.setCreatedBy(userLogin.getId());
		provider.setCreationDate(new Date());
		int ret=providerService.addpro(provider);
		if(ret>0){
			return "redirect:/pro/providerlist.html";
		}else{
			return "error";
		}
		
	}
	//查看
	@ResponseBody
	@RequestMapping(value="/showpro/{pid}")
	public Provider showpro(
			@PathVariable
			Integer pid){
		//System.out.println(pid);
		Provider provider=providerService.findById(pid);
		return provider;
	}
	
	//显示修改
	@RequestMapping(value="/view/{pid}")
	public String view(
			@PathVariable
			Integer pid,Model model){
		Provider provider=providerService.findById(pid);
		model.addAttribute("provider",provider);
		return "providermodify";
	}
	
	//修改
	@RequestMapping(value="/updatepro.html")
	public String updatepro(Provider provider,Model model,HttpSession session){
		User userLogin=(User) session.getAttribute("userSession");
		provider.setModifyBy(userLogin.getId());
		provider.setModifyDate(new Date());
		int ret=providerService.updatepro(provider);
		if(ret>0){
			return "redirect:/pro/providerlist.html";
		}else{
			return "error";
		}
				
	}
	
	//删除
	@ResponseBody
	@RequestMapping(value="/del/{pid}")
	public Object delpro(
			@PathVariable
			Integer pid,Model model){
		Map<String, Object> retMap=new HashMap<String, Object>();
		if(pid==null){
			retMap.put("ret", "notexist");
		}else{
			int billCount=billService.count(pid);
			if(billCount>0){
				retMap.put("ret", billCount);
			}else{
				int ret=providerService.delpro(pid);
				if(ret>0){
					retMap.put("ret", "true");
				}else{
					retMap.put("ret", "false");
				}
			}
			
		}
		return retMap;
	}
	
}
