package com.yzh.portal.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yzh.dao.pojo.Fav;
import com.yzh.dao.pojo.Favorite;
import com.yzh.dao.pojo.User;
import com.yzh.portal.dto.Users;
import com.yzh.service.UserService;

@Controller
@RequestMapping("/pages")
public class UserLoginController {
	
	@Resource
	private UserService userServiceImpl;
	
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
		int index = userServiceImpl.addUser(u);
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
			return "login";
		}else{
			User u = new User();
			u.setUsername(username);
			u.setPassword(password);
			User user = userServiceImpl.selUser(u);
			
			//有该用户
			if(user!=null){
				String words = (String) req.getSession().getAttribute("vercode");
				//验证码正确
				if(code.equals(words)){
					//允许登入
					if(user.getStatus()==0){
						//查找 所有爱好存入session 以便界面查找
						List<Favorite> favorite = userServiceImpl.selAllFavorite();
						session.setAttribute("favorite", favorite);
						//将用户的所有信息存人session
						List<Fav> favs = userServiceImpl.selFavByUid(user.getUid());
						Users users = new Users();
						users.setFav(favs);
						users.setUser(user);
						session.setAttribute("users", users);
						return "redirect:/pages/main";
					}
				}
			}
			return "login";
		}
	}
	
	
	/**
	 * user logout
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.setAttribute("users", null);
		return "redirect:/pages/main.jsp";
	}
	
}
