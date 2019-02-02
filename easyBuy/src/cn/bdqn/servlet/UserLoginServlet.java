package cn.bdqn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bdqn.entity.EasyBuyUser;
import cn.bdqn.service.EasyBuyUserService;

public class UserLoginServlet extends HttpServlet {

	private EasyBuyUserService userService=new EasyBuyUserService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action=request.getParameter("action");
		if(action.equals("userLogin")){
			String code=request.getParameter("code");
			String msg=null;
			String numrand=(String)request.getSession().getAttribute("numrand");
			if(code.equals(numrand)){
				String userName=request.getParameter("userId");
				String userPwd=request.getParameter("password");
				
				EasyBuyUser userLogin=userService.findByNamePad(userName, userPwd);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Date now=new Date();
				String today=sdf.format(now);
				if(userLogin!=null){
					request.getSession().setAttribute("today", today);
					request.getSession().setAttribute("userLogin",userLogin);
					response.sendRedirect("index.jsp");
				
				}else{
					msg = "用户名或密码有误，请重新登录!";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}else{
				msg = "验证码有误，请重新输入!";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
		}else if(action.equals("exite")){
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}
		
	}

}
