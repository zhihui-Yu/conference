package com.yzh.portal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzh.dao.pojo.Admin;
import com.yzh.dao.pojo.ConferInfor;
import com.yzh.dao.pojo.Fav;
import com.yzh.dao.pojo.Img;
import com.yzh.dao.pojo.PeoNum;
import com.yzh.dao.pojo.User;
import com.yzh.portal.dto.Confer;
import com.yzh.portal.dto.Users;
import com.yzh.portal.method.AddConfer;
import com.yzh.portal.method.DelConfer;
import com.yzh.portal.method.Layui;
import com.yzh.portal.method.SelAllConfer;
import com.yzh.portal.method.SelConfer;
import com.yzh.service.AdminService;

@Controller
@RequestMapping("/pages/admin")
public class AdminController {

	@Resource
	private AdminService adminServiceImpl;

	/**
	 * 跳转主页面
	 * 
	 * @return
	 */
	@RequestMapping("adminPage")
	public String adminPage() {
		return "admin/adminPage";
	}

	/**
	 * 退出并消除会话中的信息
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("adminlogout")
	public String adminLogOut(HttpSession session) {
		session.setAttribute("admin", null);
		return "redirect:/pages/main.jsp";
	}

	/**
	 * 添加会议信息
	 * 
	 * @param req
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("addConfer")
	public String addConfer(HttpServletRequest req, HttpServletResponse res,@RequestParam MultipartFile[] file) throws IOException {
		AddConfer.addConfer(req, file, adminServiceImpl);
		
/*		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString("修改成功");
		PrintWriter writer = res.getWriter();
		writer.write(str);
		writer.flush();
		writer.close();
*/		
		return "redirect:/pages/admin/addConfer.jsp";
	}

	/**
	 * 找到相应的会议室信息回传
	 * 查找会议室
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("searchConfer")
	@ResponseBody
	public void searchConfer(HttpServletRequest req, HttpServletResponse res, String conferName) throws IOException {
		List<Confer> confer = new ArrayList<Confer>();
		// 查找conferInfor
		// 名字为空显示所有的会议室信息
		if(conferName == ""){
			confer = SelAllConfer.selAllConfer(adminServiceImpl, confer);
		}else{
			//不为空查找信息
			Confer c = new Confer();
			c = SelConfer.selConfer(conferName, adminServiceImpl, c);
			//没找到
			if(c == null){
				c = new Confer();
				c.setMsg("没有相关信息");
			}
			confer.add(c);
		}
		Layui data = Layui.data(confer.size(), confer);
		System.out.println(data);
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(data);
		res.setCharacterEncoding("utf-8");
		PrintWriter writer = res.getWriter();
		writer.write(result);
		writer.flush();
		writer.close();
		//return confer;
	}

	/**
	 * 跳转详细信息
	 * @param req
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selConfer")
	public Confer selConfer(HttpServletRequest req,String name) {
		System.out.println("前端的name"+name);
		// 查找conferInfor
		Confer c = new Confer();
		c = SelConfer.selConfer(name, adminServiceImpl, c);
		if(c==null){
			c = new Confer();
			c.setMsg("没有相关信息");
		}
		return c;
	}

	/**
	 * 找到相应的会议室信息回传
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("delConfer")
	public String delConfer(String name, HttpServletRequest req) throws IOException {
		// 放入查找到的conferInfor
		List<Confer> confer = new ArrayList<Confer>();
		// 删除指定的name的会议室 包括infor和img信息 以及本地图片
		String msg = DelConfer.delConfer(req, adminServiceImpl, name);
		// 显示所有的会议室信息
		//confer = SelAllConfer.selAllConfer(adminServiceImpl, confer);
		//return  "redirect:/pages/admin/updConfer.jsp";
		return msg;
	}
	
	/**
	 * 更新会议室信息
	 * @param req
	 * @param file
	 * @param name
	 * @return
	 */
	@RequestMapping("updConfer")
	public String updConfer(HttpServletRequest req,ConferInfor c) {
		
		//根据会议室名字 获取id
		int cid = adminServiceImpl.selConferByName(c.getConferName()).getCid();
		c.setCid(cid);
		c.setStatus("未使用");
		
		//获取会议室信息
		System.out.println(c.toString());
		//更新信息
		int updConfer = adminServiceImpl.updConfer(c);
		if(updConfer<1){
			System.out.println("修改失败");
		}
		//传回所有的会议室信息并显示
		return "redirect:/pages/admin/updConfer.jsp";
	}

	@ResponseBody
	@RequestMapping("selPeoNum")
	public List<PeoNum> selPeoNum() {
		return adminServiceImpl.selPeoNum();
	}

	/**
	 * 搜索用户 回传信息
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("searchUser")
	@ResponseBody
	public void searchUser(HttpServletRequest req, HttpServletResponse res,String name) throws IOException {
		// 获取用户信息
		List<User> user;
		if (name == "") {
			user = adminServiceImpl.selUser();
		} else {
			user = adminServiceImpl.selUserByName(name);
		}
		// 将用户添加进Users
		List<Users> users = new ArrayList<Users>();
		if (user == null) {
			/*return null;*/
		}
		for (User u : user) {
			// 获取用户的爱好
			List<Fav> selFavByUid = adminServiceImpl.selFavByUid(u.getUid());
			Users u2 = new Users();
			u2.setUser(u);
			u2.setFav(selFavByUid);
			users.add(u2);
		}
		Layui data = Layui.data(users.size(), users);
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(data);
		res.setCharacterEncoding("utf-8");
		PrintWriter writer = res.getWriter();
		writer.write(result);
		writer.flush();
		writer.close();
	}
	
	@ResponseBody
	@RequestMapping("offUser")
	public String offUser(String uid){
		int id = Integer.parseInt(uid);
		int updUserStatusById = adminServiceImpl.updUserStatusById(id, 1);
		if(updUserStatusById > 0){
			return "修改成功";
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping("onUser")
	public String onUser(String uid){
		int id = Integer.parseInt(uid);
		int updUserStatusById = adminServiceImpl.updUserStatusById(id, 0);
		if(updUserStatusById > 0){
			return "修改成功";
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping("selUser")
	public List<User> selUser(String username){
		List<User> user = adminServiceImpl.selUserByName(username);
		if(user != null){
			return user;
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping("changeP")
	public void changeP(String oldPassword,String newPassword,HttpServletResponse res,HttpSession session) throws IOException{
		
		res.setCharacterEncoding("UTF-8");
		PrintWriter writer = res.getWriter();
		
		//查找是否有响应密码的admin
		Admin admin = adminServiceImpl.selAdminByPassword(oldPassword);
		Admin sessionAdmin = (Admin)session.getAttribute("admin");
		
		//判断管理员账号与密码是否一致
		if(admin!= null && admin.getAdminName().equals(sessionAdmin.getAdminName())){
			Admin a = new Admin();
			a.setAid(admin.getAid());
			a.setPassword(newPassword);
			int index = adminServiceImpl.updAdminPassword(a);
			if(index > 0 ){
				writer.write("修改成功");
			}else{
				System.out.println("修改失败");
			}
			
		} else {
			writer.write("原密码错误");
		}
		writer.flush();
		writer.close();
	}
}
