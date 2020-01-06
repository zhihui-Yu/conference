package com.yzh.portal.intercepetor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class DefaultIntercepetor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取URl
		String url = request.getRequestURI();
		HttpSession session = request.getSession();
		if(url.toLowerCase().indexOf("vcode") > 0){
				return true;
		}
		//判断是不是包含main 是则放行
		if(url.toLowerCase().indexOf("main")>=0 || url.toLowerCase().indexOf("login") > 0 || url.toLowerCase().indexOf("register") > 0){
			return true;
		}
		//判断管理员是不是登入过  是则放行
		if(url.toLowerCase().indexOf("adminpage") > 0){
			if(session.getAttribute("admin") != null){
				return true;
			}
			response.sendRedirect("/pages/adminLogin.jsp");
			return false;
		}
		//判断用户是不是登入过  是则放行 无则进入登入页面
		if(url.toLowerCase().indexOf("confer") > 0 || url.toLowerCase().indexOf("self") > 0){
			if(session.getAttribute("user") != null){
				return true;
			}
			response.sendRedirect("/pages/login.jsp");
			return false;
		}
		//其他情况都拦截 并返回登入界面
		response.sendRedirect("/pages/main.jsp");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
