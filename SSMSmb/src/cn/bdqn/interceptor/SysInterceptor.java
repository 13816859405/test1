package cn.bdqn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.bdqn.pojo.User;

public class SysInterceptor extends HandlerInterceptorAdapter {

		@Override
		public boolean preHandle(HttpServletRequest request,
				HttpServletResponse response, Object handler) throws Exception {
			HttpSession session = request.getSession();
			User userLogin = (User) session.getAttribute("userSession");
			if(userLogin==null){
				response.sendRedirect(request.getContextPath()+"/index.jsp");
				return false;
			}else{
				return true;
			}
		}
}

