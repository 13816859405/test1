package cn.bdqn.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.bdqn.entity.EasyBuyProduct;
import cn.bdqn.entity.EasyBuyUser;
import cn.bdqn.service.EasyBuyPorductService;


public class AddressServlet extends HttpServlet {
	private EasyBuyPorductService productService=new EasyBuyPorductService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String epIdStr=request.getParameter("epId");
		EasyBuyUser user=(EasyBuyUser) request.getSession().getAttribute("userLogin");
		if(user==null){
			response.sendRedirect("login.jsp");
		}else{
			if(epIdStr!=null){
				Integer epId=Integer.parseInt(epIdStr);
				EasyBuyProduct buyPro=productService.findById(epId);
				request.getSession().setAttribute("buyPro",buyPro);
			}
			String address=user.getAddress();
			String[] addList=address.split(";");
			request.setAttribute("addList", addList);
			
			request.getRequestDispatcher("address.jsp").forward(request, response);
		}
			
	}

}
