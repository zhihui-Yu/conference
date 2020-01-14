package com.yzh.portal.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzh.dao.pojo.User;
import com.yzh.service.UserService;

@Controller()
@RequestMapping("/pages")
public class UserController {
	@Resource
	private UserService userServiceImpl;
	
	/**
	 * 修改信息
	 */
	public String changeUserInfo(HttpServletRequest req,String[] fav,String uid){
		String msg = "";
		//获取参数
		String username = req.getParameter("username");
		String sex = req.getParameter("sex");
		String birth = req.getParameter("birth");
		String address = req.getParameter("address");
		String tel = req.getParameter("tel");
		String comm = req.getParameter("comm");
		//将参数放入对象
		User u = new User();
		u.setUsername(username);
		u.setAddress(address);
		u.setBirth(birth);
		u.setComm(comm);
		u.setTel(tel);
		u.setSex(sex=="1"?1:0);
		u.setUid(Integer.parseInt(uid));
		//更新数据库信息
		//更新用户信息
		int index = userServiceImpl.updUserById(u);
		//更新爱好信息
		
		if(index > 0){
			msg = "修改成功";
		} else {
			msg = "修改失败";
		}
		
		return msg;
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("changeUserPassword")
	public String changePassword(HttpServletRequest request, HttpServletResponse res) {
		//获取参数
		String old = request.getParameter("old");
		String new1 = request.getParameter("new1");
		String new2 = request.getParameter("new2");
		
		//从session中获取用户信息
		User user = (User) request.getSession().getAttribute("user");
		//执行sql的返回值
		Integer index = null;
		//返回前端的值
		String msg = "";
		//判断是否已经登入
		if (user != null) {
			//判断原密码是不是一致
			if (user.getPassword().equals(old)) {
				//判断新密码是不是一致
				if (new1 != "" && new1.equals(new2)) {
					user.setPassword(new1);
					index = userServiceImpl.updUserPassword(user);
					//判断执行是不是成功
					if(index > 0 ){
						msg = "修改成功";
					} else {
						System.out.println("修改失败");
					}
				} else {
					msg = "两次密码不一致";
				}
			} else {
				msg = "原密码错误";
			}
		} else {
			msg = "请先登入";
		}
		return msg;
	}
}
