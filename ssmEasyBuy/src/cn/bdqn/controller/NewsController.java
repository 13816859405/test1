package cn.bdqn.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.bdqn.entity.EasyBuyNews;
import cn.bdqn.service.NewsService;

@Controller
public class NewsController {
	
	@Resource(name="newsService")
	private NewsService newsService;
	
	@RequestMapping("/showAll.html")
	public String ShowAll(Model model){
		List<EasyBuyNews> newList=newsService.findAll();
		model.addAttribute("newList",newList);
		return "showAll";
	}
}
