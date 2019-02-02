package cn.bdqn.servlet;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bdqn.entity.EasyBuyCategory;
import cn.bdqn.entity.EasyBuyProduct;
import cn.bdqn.entity.EasybuyNews;
import cn.bdqn.service.EasyBuyCategoryService;
import cn.bdqn.service.EasyBuyNewsService;
import cn.bdqn.service.EasyBuyPorductService;
import cn.bdqn.util.PageBean;

public class IndexServlet extends HttpServlet {
	private EasyBuyCategoryService categoryService=new EasyBuyCategoryService();
	private EasyBuyNewsService easyBuyNewsService=new EasyBuyNewsService();
	private EasyBuyPorductService easyBuyPorductService=new EasyBuyPorductService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<EasyBuyCategory, List<EasyBuyCategory>> map=categoryService.findAllCategory();
		List<EasybuyNews> newList=easyBuyNewsService.findNews();
		request.setAttribute("newList", newList);
		request.getSession().setAttribute("map", map);
		//读Cookie
		List<EasyBuyProduct> proList=new ArrayList<EasyBuyProduct>();
		Cookie ckprolist=null;
		//先找之前的cookie
		Cookie[] cke=request.getCookies();
		for(Cookie cookie:cke){
			if("ckprolist".equals(cookie.getName())){
				ckprolist=cookie;//找到了
				break;
			}
		}
		if(ckprolist!=null){
			String value=ckprolist.getValue();
			String[] pidlist=value.split(",");
			int end=0;
			if(pidlist.length>3){
				end=pidlist.length-3;
			}
			for(int i=pidlist.length-1;i>=end;i--){
				//System.out.println(pidlist[i]);
				int epId=Integer.parseInt(pidlist[i]);
				EasyBuyProduct product=easyBuyPorductService.findById(epId);
				proList.add(product);
			}
		}
		request.getSession().setAttribute("proList", proList);
		//分页查询商品
		String pageNoStr=request.getParameter("pageNo");
		int pageNo=1;
		if(pageNoStr!=null){
			pageNo=Integer.parseInt(pageNoStr);
		}
		int pageSize=8;
		PageBean<EasyBuyProduct> pageBean=easyBuyPorductService.findByProductPage(pageNo, pageSize);
		
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
