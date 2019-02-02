package cn.bdqn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bdqn.entity.EasyBuyUser;

import cn.bdqn.service.EasyBuyUserService;
import cn.bdqn.util.PageBean;

public class UserServlet extends HttpServlet {
	private EasyBuyUserService easyBuyUserService=new EasyBuyUserService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//PrintWriter out = response.getWriter();
		String pro=request.getParameter("pro");
		if(pro.equals("findUserPage")){
			String pageNoStr=request.getParameter("pageNo");
			int pageNo=1;
			if(pageNoStr!=null){
				pageNo=Integer.parseInt(pageNoStr);
			}
			int pageSize=8;
			PageBean<EasyBuyUser> PageBean=easyBuyUserService.fingByPage(pageNo, pageSize);
			request.setAttribute("PageBean",PageBean);
			
			request.getRequestDispatcher("user.jsp").forward(request, response);
		}else if(pro.equals("showUpdateUser")){
			String userIdStr=request.getParameter("userId");
			if(userIdStr!=null){
				Integer userId=Integer.parseInt(userIdStr);
				EasyBuyUser user=easyBuyUserService.findById(userId);
				request.setAttribute("user", user);
				
				request.getRequestDispatcher("user-modify.jsp").forward(request, response);
			}
		}else if(pro.equals("updateUser")){
			String userIdStr=request.getParameter("userId");
			if(userIdStr!=null){
				Integer userId=Integer.parseInt(userIdStr);
				String userName=request.getParameter("userName");
				String nickName=request.getParameter("name");
				String userPwd=request.getParameter("passWord");
				Integer userSex=Integer.parseInt(request.getParameter("sex"));
				Date birthday=Date.valueOf(request.getParameter("birthday"));
				String mobile=request.getParameter("mobile");
				String address=request.getParameter("address");
				EasyBuyUser updateUser=new EasyBuyUser(userId,userName,nickName,userPwd,userSex,birthday,null,null,mobile,address,0);
				int ret=easyBuyUserService.updateUser(updateUser);
				if(ret>0){
					request.getRequestDispatcher("manage-result.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("../error.jsp").forward(request, response);
				}
			}
		}else if(pro.equals("delUser")){
			String userIdStr=request.getParameter("userId");
			if(userIdStr!=null){
				Integer userId=Integer.parseInt(userIdStr);
				int ret=easyBuyUserService.delUser(userId);
				if(ret>0){
					request.getRequestDispatcher("manage-result.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("../error.jsp").forward(request, response);
				}
			}
		}
		
	}

}
