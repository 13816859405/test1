package cn.bdqn.servlet;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.bdqn.entity.EasyBuyComment;

import cn.bdqn.service.CommentService;
import cn.bdqn.util.PageBean;

public class CommentServlet extends HttpServlet {
	private CommentService commentService=new CommentService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String pro=request.getParameter("pro");
		if(pro.equals("findPage")){
			String pageNoStr=request.getParameter("pageNo");
			int pageNo=1;
			if(pageNoStr!=null){
				pageNo=Integer.parseInt(pageNoStr);
			}
			int pageSize=3;
			PageBean<EasyBuyComment> comPageBean=commentService.findcomByPage(pageNo, pageSize);
			request.setAttribute("comPageBean",comPageBean);
			request.getRequestDispatcher("guestbook.jsp").forward(request, response);
		}else if(pro.equals("addComm")){
			String nickName=request.getParameter("guestName");
			String guestContent=request.getParameter("guestContent");
			int ret=commentService.addComment(nickName,guestContent);
			if(ret>0){
				request.getRequestDispatcher("guestbook.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("../error.jsp").forward(request, response);
			}
		}else if(pro.equals("findComPage")){
			
			String pageNoStr=request.getParameter("pageNo");
			int pageNo=1;
			if(pageNoStr!=null){
				pageNo=Integer.parseInt(pageNoStr);
			}
			int pageSize=8;
			PageBean<EasyBuyComment> PageBean=commentService.findcomByPage(pageNo, pageSize);
			request.setAttribute("PageBean",PageBean);
			
			request.getRequestDispatcher("guestbook.jsp").forward(request, response);
		}else if(pro.equals("showUpdateCom")){
			String ecIdStr=request.getParameter("ecId");
			if(ecIdStr!=null){
				Integer ecId=Integer.parseInt(ecIdStr);
				EasyBuyComment comment=commentService.findById(ecId);
				request.setAttribute("comment", comment);
				
				request.getRequestDispatcher("guestbook-modify.jsp").forward(request, response);
			}
		}else if(pro.equals("updateCom")){
			String ecIdStr=request.getParameter("ecId");
			if(ecIdStr!=null){
				Integer ecId=Integer.parseInt(ecIdStr);
				String nickName=request.getParameter("nickName");
				String content=request.getParameter("content");
				String reply=request.getParameter("replyContent");
				EasyBuyComment updateCom=new EasyBuyComment(ecId,reply,content,null,null,nickName);
				int ret=commentService.updateCom(updateCom);
				if(ret>0){
					request.getRequestDispatcher("manage-result.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("../error.jsp").forward(request, response);
				}
			}
		}else if(pro.equals("delCom")){
			String ecIdStr=request.getParameter("ecId");
			if(ecIdStr!=null){
				Integer ecId=Integer.parseInt(ecIdStr);
				int ret=commentService.delCom(ecId);
				if(ret>0){
					request.getRequestDispatcher("manage-result.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("../error.jsp").forward(request, response);
				}
			}
		}
		
	}

}
