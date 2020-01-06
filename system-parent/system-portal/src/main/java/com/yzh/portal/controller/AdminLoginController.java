package com.yzh.portal.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yzh.dao.pojo.Admin;
import com.yzh.dao.pojo.PeoNum;
import com.yzh.service.AdminService;

@Controller
@RequestMapping("/pages")
public class AdminLoginController {
	@Resource
	private AdminService adminServiceImpl;
	
	/**
	 * admin login page
	 * @return
	 */
	@RequestMapping("adminLogin")
	public String adminLogin(){
		return "adminLogin";
	}
	
	@RequestMapping("toadminLogin")
	public String toadminLogin(HttpServletRequest req,HttpSession session){
		List<PeoNum> selPeoNum = adminServiceImpl.selPeoNum();
		session.setAttribute("selPeoNum", selPeoNum);
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Admin admin = new Admin();
		admin.setAdminName(username);
		admin.setPassword(password);
		Admin checkAdmin = adminServiceImpl.checkAdmin(admin);
		
		if(checkAdmin!=null){
			session.setAttribute("admin", checkAdmin);
			return "redirect:/pages/admin/adminPage.jsp";
		}else{
			session.setAttribute("Error", "Unkonw Admin");
			return "adminLogin";
		}
	}
}
