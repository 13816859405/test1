package cn.bdqn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.bdqn.entity.EasyBuyUser;
import cn.bdqn.service.EasyBuyUserService;

public class RegisterServlet extends HttpServlet {
	
	private EasyBuyUserService userService=new EasyBuyUserService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String pro=request.getParameter("pro");
		if(pro.equals("register")){
			String userName=request.getParameter("userId");
			String nickName=request.getParameter("userName"); 
			String userPwd=request.getParameter("password"); 
			String Sex=request.getParameter("sex"); 
			Integer userSex=1;
			if(Sex.equals("female")){
				userSex=0;
			}
			Date birthday=Date.valueOf(request.getParameter("birthday")); 
			String identityCode=request.getParameter("identityCode"); 
			String email=request.getParameter("email"); 
			String mobile=request.getParameter("mobile"); 
			String address=request.getParameter("address");
			Integer status=0;
			int ret=userService.addUser(userName, nickName, userPwd, userSex, birthday, identityCode, email, mobile, address, status);
			if(ret>0){//转发成功页面
				request.getRequestDispatcher("reg-result.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}else if(pro.equals("findName")){
			PrintWriter out = response.getWriter();
			String userName=request.getParameter("userName");
			String userLogin=userService.findByNamePad(userName);
			String json=JSON.toJSONString(userLogin);
			/*if(userLogin!=null){
				out.println("<font color='red'>用户已存在，不能使用</font>");
			}*/
			out.println(json);
			//System.out.print(userLogin);
			out.flush();
			out.close();
		}
		
	}

}
