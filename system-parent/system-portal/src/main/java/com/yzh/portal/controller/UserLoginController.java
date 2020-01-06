package com.yzh.portal.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yzh.dao.pojo.User;
import com.yzh.service.UserLoginService;

@Controller
@RequestMapping("/pages")
public class UserLoginController {
	
	@Resource
	private UserLoginService userLoginServiceImpl;

	@RequestMapping("main")
	public String main(){
		return "redirect:/pages/main.jsp";
	}
	
	/**
	 * user register page
	 * @return
	 */
	@RequestMapping("register")
	public String register(){
		return "register";
	}
	
	/**
	 * check add user whether success
	 * @param req
	 * @return
	 */
	@RequestMapping("toRegister")
	public String toRegister(HttpServletRequest req, HttpSession session){
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String sex = req.getParameter("sex");
		String tel = req.getParameter("tel");
		double money = 0;
		int status = 0;
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setComm(" ");
		u.setSex(Integer.parseInt(sex));
		u.setTel(tel);
		u.setMoney(money);
		u.setStatus(status);
		int index = userLoginServiceImpl.addUser(u);
		if(index == 1 ){
			session.setAttribute("user", u);
			return "redirect:/main";
		}
		else{
			return "register";
		}
	}
	
	/**
	 * user login
	 * @return
	 */
	@RequestMapping("login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/tologin")
	public String tologin(HttpServletRequest req,HttpSession session){
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String code = req.getParameter("code");
		
		if(username.equals("") || password.equals("") || code.equals("")){
			/*if(code.equals("")){
				req.setAttribute("message", "Code must not be null");
			}else{
				req.setAttribute("message", "username or password is null");
			}*/
			return "login";
		}else{
			User u = new User();
			u.setUsername(username);
			u.setPassword(password);
			User user = userLoginServiceImpl.checkUser(u);
			
			
			if(user!=null){
				String words = (String) req.getSession().getAttribute("vercode");
				if(code.equals(words)){
					if(user.getStatus()==0){
						session.setAttribute("user", u);
						return "redirect:/main";
					}
				}
			}
			//req.setAttribute("message", "username or password is error");
			return "login";
		}
	}
	
	
	/**
	 * user logout
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.setAttribute("user", null);
		return "redirect:/pages/main.jsp";
	}
	
}
