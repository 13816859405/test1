package cn.bdqn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bdqn.entity.AllOrder;
import cn.bdqn.entity.EasyBuyOrder;
import cn.bdqn.entity.EasyBuyOrderDetail;
import cn.bdqn.entity.EasyBuyProduct;
import cn.bdqn.entity.EasyBuyUser;
import cn.bdqn.entity.EasybuyNews;
import cn.bdqn.entity.ShoppingCart;
import cn.bdqn.entity.ShoppingCatItem;
import cn.bdqn.service.OrderService;
import cn.bdqn.util.PageBean;

public class OrderServlet extends HttpServlet {
	private OrderService orderService=new OrderService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String pro=request.getParameter("pro");
		if(pro.equals("findOrder")){
			/*List<EasyBuyOrder> OrderList=orderService.findOrder();
			request.setAttribute("OrderList", OrderList);*/
			String eoId=request.getParameter("entityId");
			String userName=request.getParameter("userName");
			String pageNoStr=request.getParameter("pageNo");
			int pageNo=1;
			if(pageNoStr!=null){
				pageNo=Integer.parseInt(pageNoStr);
			}
			int pageSize=2;
			PageBean<EasyBuyOrder> pageBean=orderService.fingByPage(pageNo, pageSize,eoId,userName);
			request.setAttribute("pageBean",pageBean);
			request.setAttribute("eoId",eoId);
			request.setAttribute("userName",userName);
			
			request.getRequestDispatcher("order.jsp").forward(request, response);

		}else if(pro.equals("addOrder")){
			
			String address=request.getParameter("address");
			EasyBuyUser user=(EasyBuyUser) request.getSession().getAttribute("userLogin");
			String eoId=UUID.randomUUID().toString().replaceAll("-","");
			EasyBuyProduct buyPro=(EasyBuyProduct) request.getSession().getAttribute("buyPro");
			
			if(buyPro!=null){//直接购买
				int ret=orderService.addOrder(eoId,user.getUserId(),address,buyPro.getPrice(),1,0,user.getUserName());
				int ret1=orderService.addOrderDetail(eoId,buyPro.getEpId(),1,buyPro.getPrice());
				if(ret>0&&ret1>0){
					request.getSession().removeAttribute("buyPro");
					request.getRequestDispatcher("shopping-result.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("../error.jsp").forward(request, response);
				}
			}else{//购物车结算
				ShoppingCart cart=(ShoppingCart)request.getSession().getAttribute("cart");
				int ret=orderService.addOrder(eoId, user.getUserId(), address, cart.getTotalCost(), 1, 0,user.getUserName());
				int ret1=0;
				for(ShoppingCatItem item:cart.items){
					ret1=orderService.addOrderDetail(eoId,item.getProduct().getEpId(),item.getQuantity(),item.getCost());
				}
				if(ret>0&&ret1>0){
					request.getSession().removeAttribute("cart");
					request.getRequestDispatcher("shopping-result.jsp").forward(request, response);
					
				}else{
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			}
		}
	}

}
