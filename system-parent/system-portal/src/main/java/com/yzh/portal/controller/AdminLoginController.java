package com.yzh.portal.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzh.dao.pojo.Admin;
import com.yzh.dao.pojo.PeoNum;
import com.yzh.portal.method.ResponseString;
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
	
	/**
	 * 跳转后台管理界面
	 * @return
	 */
	@RequestMapping("adminPage")
	public String adminPage(){
		return "redirect:/pages/admin/adminPage.jsp";
	}
	/**
	 * 管理员登入
	 * @param req
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("toadminLogin")
	public void toadminLogin(HttpServletRequest req,HttpSession session, HttpServletResponse res) throws IOException{
		List<PeoNum> selPeoNum = adminServiceImpl.selPeoNum();
		session.setAttribute("selPeoNum", selPeoNum);
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Admin admin = new Admin();
		admin.setAdminName(username);
		admin.setPassword(password);
		Admin checkAdmin = adminServiceImpl.checkAdmin(admin);
		
		if(checkAdmin!=null){
			if(checkAdmin.getPassword().equals(password)){
				session.setAttribute("admin", checkAdmin);
				ResponseString.respongString(res, "true");
			} else {
				ResponseString.respongString(res, "密码错误");
			}
		}else{
			ResponseString.respongString(res, "无此用户");
		}
	}
}
