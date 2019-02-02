package cn.bdqn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bdqn.entity.EasybuyNews;
import cn.bdqn.service.EasyBuyNewsService;
import cn.bdqn.util.PageBean;

public class NewsServlet extends HttpServlet {
	private EasyBuyNewsService easyBuyNewsService=new EasyBuyNewsService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String pro=request.getParameter("pro");
		if(pro.equals("addNews")){
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			int ret=easyBuyNewsService.addNews(title,content);
			if(ret>0){
				request.getRequestDispatcher("manage-result.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("../error.jsp").forward(request, response);
			}
		}else if(pro.equals("findAll")){
			
			String pageNoStr=request.getParameter("pageNo");
			int pageNo=1;
			if(pageNoStr!=null){
				pageNo=Integer.parseInt(pageNoStr);
			}
			int pageSize=8;
			PageBean<EasybuyNews> newsPageBean=easyBuyNewsService.fingByPage(pageNo, pageSize);
			request.setAttribute("newsPageBean",newsPageBean);
			
			request.getRequestDispatcher("news.jsp").forward(request, response);
		}else if(pro.equals("showUpdateNews")){
			String enIdStr=request.getParameter("enId");
			if(enIdStr!=null){
				Integer enId=Integer.parseInt(enIdStr);
				EasybuyNews news=easyBuyNewsService.findById(enId);
				request.setAttribute("news", news);
				
				request.getRequestDispatcher("news-modify.jsp").forward(request, response);
			}
		}else if(pro.equals("updateNews")){
			String enIdStr=request.getParameter("enId");
			if(enIdStr!=null){
				Integer enId=Integer.parseInt(enIdStr);
				String title=request.getParameter("title");
				String content=request.getParameter("content");
				Date createTime=null;
				EasybuyNews updateNews=new EasybuyNews(enId,title,content,createTime);
				int ret=easyBuyNewsService.updateNews(updateNews);
				if(ret>0){
					request.getRequestDispatcher("manage-result.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("../error.jsp").forward(request, response);
				}
			}
		}else if(pro.equals("delNews")){
			String enIdStr=request.getParameter("enId");
			if(enIdStr!=null){
				Integer enId=Integer.parseInt(enIdStr);
				int ret=easyBuyNewsService.delNews(enId);
				if(ret>0){
					request.getRequestDispatcher("manage-result.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("../error.jsp").forward(request, response);
				}
			}
		}
	}

}
