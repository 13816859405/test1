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

import cn.bdqn.pojo.Bill;
import cn.bdqn.pojo.Provider;
import cn.bdqn.pojo.Role;
import cn.bdqn.pojo.User;
import cn.bdqn.service.BillService;
import cn.bdqn.service.ProviderService;
import cn.bdqn.util.PageBean;



@Controller
@RequestMapping(value="/bill")
public class BillConteroller {
	
	@Resource(name="billService")
	private BillService billService;
	@Resource(name="providerService")
	private ProviderService providerService;
	
	//分页查询
	@RequestMapping(value="/billlist.html")
	public String showByPage(
			@RequestParam(required=false,defaultValue="1")
			Integer pageNo,
			@RequestParam(required=false)
			String queryProductName,
			@RequestParam(required=false)
			Integer queryProviderId,
			@RequestParam(required=false)
			Integer queryIsPayment,
			Model model){
		int pageSize=5;
		PageBean<Bill> pageBean=billService.findByPage(queryProductName,queryProviderId,queryIsPayment,pageNo,pageSize);
		//查询所有供应商
		List<Provider> proList=providerService.findAll();
		model.addAttribute("providerList",proList);
		model.addAttribute("pageBean",pageBean);
		//System.out.println(queryname);
		model.addAttribute("queryProductName",queryProductName);
		model.addAttribute("queryProviderId",queryProviderId);
		model.addAttribute("queryIsPayment",queryIsPayment);
		return "billlist";
		
	}
	
	//显示新增billadd.jsp
	@RequestMapping(value="/billadd.html")
	public String showbilladd(
			@ModelAttribute("bill")
			Bill bill,
			Model model){
		return "billadd";
	}
	
	//新增订单
	@RequestMapping(value="/addbill.html")
	public String addbill(Bill bill,HttpSession session){
		User userLogin=(User) session.getAttribute("userSession");
		bill.setCreatedBy(userLogin.getId());
		bill.setCreationDate(new Date());
		int ret=billService.addbill(bill);
		if(ret>0){
			return "redirect:/bill/billlist.html";
		}else{
			return "error";
		}
	}
	
	//使用AJAX请求用户角色列表
	@RequestMapping(value="/getpronamelist")
	@ResponseBody
	public List<Provider> getpronamelist(){
		List<Provider> pronamelist=providerService.findAll();
		return pronamelist;
	}
	
	//查看
	@ResponseBody
	@RequestMapping(value="/showbill/{bid}")
	public Bill showbill(
			@PathVariable
			Integer bid){
		System.out.println(bid);
		Bill bill=billService.findById(bid);
		return bill;
	}
	
	//显示修改
	@RequestMapping(value="/view/{bid}")
	public String view(
			@PathVariable
			Integer bid,Model model){
		Bill bill=billService.findById(bid);
		model.addAttribute("bill",bill);
		return "billmodify";
	}
	
	//修改
	@RequestMapping(value="/updatebill.html")
	public String updatebill(Bill bill,HttpSession session){
		User userLogin=(User) session.getAttribute("userSession");
		bill.setModifyBy(userLogin.getId());
		bill.setModifyDate(new Date());
		int ret=billService.updateBill(bill);
		if(ret>0){
			return "redirect:/bill/billlist.html";
		}else{
			return "error";
		}
				
	}
	
	//删除
		@ResponseBody
		@RequestMapping(value="/del/{bid}")
		public Object delBill(
				@PathVariable
				Integer bid,Model model){
			Map<String, String> retMap=new HashMap<String, String>();
			if(bid==null){
				retMap.put("ret", "notexist");
			}else{
				int ret=billService.delUser(bid);
				if(ret>0){
					retMap.put("ret", "true");
				}else{
					retMap.put("ret", "false");
				}
			}
			return retMap;
		}
}
