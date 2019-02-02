package cn.bdqn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bdqn.entity.EasyBuyCategory;
import cn.bdqn.service.EasyBuyCategoryService;
import cn.bdqn.util.PageBean;



public class CategoryServlet extends HttpServlet {
	private EasyBuyCategoryService categoryService=new EasyBuyCategoryService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String pro=request.getParameter("pro");
		if(pro.equals("addCat")){
			String parentIdStr=request.getParameter("parentId");
			if(parentIdStr.equals("0")){
				String parentNew=request.getParameter("parentNew");
				int parentId=categoryService.findCount()+1;
				int ret1=categoryService.addParenCat(parentNew,parentId);
				String className=request.getParameter("className");
				int ret=categoryService.addChildCat(className,parentId);
				if(ret>0&&ret1>0){
					request.getRequestDispatcher("manage-result.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("../error.jsp").forward(request, response);
				}
			}else if(parentIdStr!=null){
				Integer paretId=Integer.parseInt(parentIdStr);
				String className=request.getParameter("className");
				int ret=categoryService.addChildCat(className,paretId);
				if(ret>0){
					request.getRequestDispatcher("manage-result.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("../error.jsp").forward(request, response);
				}
			}
		}else if(pro.equals("findPage")){
			
			String pageNoStr=request.getParameter("pageNo");
			int pageNo=1;
			if(pageNoStr!=null){
				pageNo=Integer.parseInt(pageNoStr);
			}
			int pageSize=8;
			PageBean<EasyBuyCategory> pageBean=categoryService.findByPage(pageNo, pageSize);
			request.setAttribute("pageBean", pageBean);
			request.getRequestDispatcher("productClass.jsp").forward(request, response);
			
		}else if(pro.equals("showUpate")){
			String epcIdStr=request.getParameter("epcId");
			if(epcIdStr!=null){
				int epcId=Integer.parseInt(epcIdStr);
				EasyBuyCategory category=categoryService.findById(epcId);
				request.setAttribute("category", category);
				request.getRequestDispatcher("productClass-modify.jsp").forward(request, response);
			}
		}else if(pro.equals("upateCategory")){
			String epcIdStr=request.getParameter("epcId");
			if(epcIdStr!=null){
				int epcId=Integer.parseInt(epcIdStr);
				int parentId=Integer.parseInt(request.getParameter("parentId"));
				String className=request.getParameter("className");
				EasyBuyCategory upateCategory=new EasyBuyCategory(epcId,className,parentId);
				int ret=categoryService.upateCategory(upateCategory);
				if(ret>0){
					request.getRequestDispatcher("manage-result.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("../error.jsp").forward(request, response);
				}
			}
		}else if(pro.equals("delCategory")){
			String epcIdStr=request.getParameter("epcId");
			if(epcIdStr!=null){
				int epcId=Integer.parseInt(epcIdStr);
				int ret=categoryService.delCategory(epcId);
				if(ret>0){
					request.getRequestDispatcher("manage-result.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("../error.jsp").forward(request, response);
				}
			}
		}
	}

}
