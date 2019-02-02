package cn.bdqn.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import cn.bdqn.entity.EasyBuyUser;
import cn.bdqn.service.EasyBuyUserService;

public class AddressAddServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EasyBuyUserService userService=new EasyBuyUserService();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session=request.getSession();
		EasyBuyUser user=(EasyBuyUser) session.getAttribute("userLogin");
		String newAdd=request.getParameter("addr");
		String adds=user.getAddress();
		if(adds==null){
			adds=newAdd;
		}else{
			adds=adds+";"+newAdd;
		}
		user.setAddress(adds);
		session.setAttribute("userLogin", user);
		int ret=userService.updateUser(user);
		String msg="添加失败！";
		if(ret>0){
			msg="添加成功！";
		}
		PrintWriter out = response.getWriter();
		String json=JSON.toJSONString(msg);
		out.println(json);
		out.flush();
		out.close();
	}

}
