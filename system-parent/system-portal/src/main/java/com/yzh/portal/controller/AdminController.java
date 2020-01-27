package com.yzh.portal.controller;

import java.io.File;
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
import com.yzh.dao.pojo.Approve;
import com.yzh.dao.pojo.ConferInfor;
import com.yzh.dao.pojo.Discuss;
import com.yzh.dao.pojo.Fav;
import com.yzh.dao.pojo.Img;
import com.yzh.dao.pojo.PeoNum;
import com.yzh.dao.pojo.User;
import com.yzh.portal.dto.Confer;
import com.yzh.portal.dto.Users;
import com.yzh.portal.method.AddConfer;
import com.yzh.portal.method.ResponseString;
import com.yzh.portal.method.SaveImg;
import com.yzh.service.AdminService;

@Controller
@RequestMapping("/pages/admin")
public class AdminController {

	@Resource
	private AdminService adminServiceImpl;

	String msg = "";
	
	/**
	 * 修改图片信息
	 * @param id
	 * @param file
	 * @param res
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("changeImgById")
	public void changeImgById(HttpServletRequest req, int id,@RequestParam MultipartFile[] file, HttpServletResponse res) throws IOException{
		//获取本地的图片地址
		String url = req.getSession().getServletContext().getRealPath("//upload/");
		//删除本地会议室图片
		List<Img> imgs = adminServiceImpl.selImgByConferId(id);
		for (Img img : imgs) {
			File f = new File(url+img.getPath());
			//判断图片是不是存在
			if(f.exists()){
				//存在删除
				f.delete();
			}
		}
		//删除该会议室的图片
		int index1 = adminServiceImpl.delImgByCid(id);
		//保存图片到本地
		String[] paths = SaveImg.saveFile(file, url);
		//判断是不是都保存成功
		boolean flag = false;
		//将图片地址保存
		for (String path : paths) {
			//添加会议室的图片
			Img img = new Img();
			img.setCid(id);
			img.setPath(path);
			int index = adminServiceImpl.insImg(img);
			if(index > 0){
				flag = true;
			}
		}
		//返回数据
		if(flag && index1 > 0){
			ResponseString.respongString(res, "修改成功");
		} else {
			System.out.println("修改失败");
		}
	}
	
	/**
	 * 查找对应电话号码的订单
	 * @param tel
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selUseByTel")
	@ResponseBody
	public void selUseByTel(String tel, HttpServletResponse res) throws IOException {
		List<Approve> selUseByTel = adminServiceImpl.selUseByTel(tel);
		msg = "无信息";
		if(selUseByTel.size() > 0){
			ObjectMapper mapper = new ObjectMapper();
			msg = mapper.writeValueAsString(selUseByTel);
		}
		ResponseString.respongString(res, msg);
	}
	
	/**
	 * 查找待使用的订单信息
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selUse")
	@ResponseBody
	public void selUse(int pageNum, int pageSize, HttpServletResponse res) throws IOException {
		List<Approve> selUse = adminServiceImpl.selUse(pageNum, pageSize);
		msg = "无信息";
		if(selUse.size() > 0){
			ObjectMapper mapper = new ObjectMapper();
			msg = mapper.writeValueAsString(selUse);
		}
		ResponseString.respongString(res, msg);
	}
	
	/**
	 * 查找待使用的订单数量
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selUseCount")
	@ResponseBody
	public void selUseCount(HttpServletResponse res) throws IOException {
		msg = String.valueOf(adminServiceImpl.selUseCount());
		ResponseString.respongString(res, msg);
	}
	
	/**
	 * 回复信息
	 * @param req
	 * @param id
	 * @param asay
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("responseUser")
	@ResponseBody
	public void responseUser(HttpServletRequest req, String id, String asay, HttpServletResponse res)
			throws IOException {
		// 获取当前管理员名称
		HttpSession session = req.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		int index = adminServiceImpl.updDiscuss(Integer.parseInt(id), admin.getAdminName(), asay);
		if (index > 0) {
			msg = "信息已发送";
		} else {
			msg = "失败";
		}
		ResponseString.respongString(res, msg);
	}

	/**
	 * 查找指定用户反馈信息的数量
	 * 
	 * @param name
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selMsgCountByName")
	@ResponseBody
	public void selMsgCountByName(String name, HttpServletResponse res) throws IOException {
		msg = String.valueOf(adminServiceImpl.selMsgCountByName(name));
		ResponseString.respongString(res, msg);
	}

	/**
	 * 查找指定用户反馈信息
	 * 
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selMsgByName")
	@ResponseBody
	public void selMsgByName(String name, String pageNum, String pageSize, HttpServletResponse res) throws IOException {
		List<Discuss> selMsg = adminServiceImpl.selMsgByName(Integer.parseInt(pageNum), Integer.parseInt(pageSize),
				name);
		if (selMsg.size() < 1) {
			msg = "无该用户的反馈信息";
		} else {
			ObjectMapper mapper = new ObjectMapper();
			msg = mapper.writeValueAsString(selMsg);
		}
		ResponseString.respongString(res, msg);
	}

	/**
	 * 查找用户反馈信息的数量
	 * 
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selMsgCount")
	@ResponseBody
	public void selMsgCount(HttpServletResponse res) throws IOException {
		msg = String.valueOf(adminServiceImpl.selMsgCount());
		ResponseString.respongString(res, msg);
	}

	/**
	 * 查找用户反馈信息
	 * 
	 * @param res
	 * @param pageNum
	 * @param pageSize
	 * @throws IOException
	 */
	@RequestMapping("selMsg")
	@ResponseBody
	public void selMsg(HttpServletResponse res, String pageNum, String pageSize) throws IOException {
		List<Discuss> selMsg = adminServiceImpl.selMsg(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		if (selMsg.size() < 1) {
			msg = "无反馈信息";
		} else {
			ObjectMapper mapper = new ObjectMapper();
			msg = mapper.writeValueAsString(selMsg);
		}
		ResponseString.respongString(res, msg);
	}

	/**
	 * 修改备注
	 * 
	 * @param comm
	 * @param id
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("approveCommById")
	@ResponseBody
	public void approveCommById(String comm, String id, HttpServletResponse res) throws IOException {
		int index = adminServiceImpl.updApproveCommById(comm, Integer.parseInt(id));
		if (index > 0) {
			msg = "备注成功";
		} else {
			msg = "失败";
		}
		ResponseString.respongString(res, msg);
	}

	/**
	 * 修改状态
	 * 
	 * @param id
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("approveById")
	@ResponseBody
	public void approveById(HttpServletRequest req, String id, String status, HttpServletResponse res)
			throws IOException {
		req.setCharacterEncoding("utf-8");
		// 获取当前管理员名称
		HttpSession session = req.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			msg = "请先登入";
		} else {
			// 判断当前状态
			if (status.equals("通过审核")) {
				msg = "待缴费";
			} else if (status.equals("拒绝")) {
				msg = "已拒绝";
			} else if (status.equals("完成")) {
				msg = "待使用";
			} else if(status.equals("使用")){
				msg = "已完成";
			}
			int index = adminServiceImpl.updApproveStatusById(msg, Integer.parseInt(id), admin.getAdminName());
			if (index > 0) {
				msg = "成功";
			} else {
				msg = "失败";
			}
		}
		ResponseString.respongString(res, msg);
	}

	/**
	 * 查找相应名字未完成的信息
	 * 
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selAppByName")
	@ResponseBody
	public void selAppByName(String name, String pageNum, String pageSize, HttpServletResponse res) throws IOException {
		List<Approve> approve = adminServiceImpl.selApproveByName(name, Integer.parseInt(pageNum),
				Integer.parseInt(pageSize));
		if (approve.size() < 1) {
			msg = "无申请记录";
		} else {
			ObjectMapper mapper = new ObjectMapper();
			msg = mapper.writeValueAsString(approve);
		}
		ResponseString.respongString(res, msg);
	}

	/**
	 * 指定名字未完成的数量
	 * 
	 * @param name
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selAppByNameCount")
	@ResponseBody
	public void selAppByNameCount(String name, HttpServletResponse res) throws IOException {
		msg = String.valueOf(adminServiceImpl.selApproveByNameCount(name));
		ResponseString.respongString(res, msg);
	}

	/**
	 * 查找未完成的信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selApp")
	@ResponseBody
	public void selApp(String pageNum, String pageSize, HttpServletResponse res) throws IOException {
		List<Approve> approve = adminServiceImpl.selApprove(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		if (approve.size() < 1) {
			msg = "无申请记录";
		} else {
			ObjectMapper mapper = new ObjectMapper();
			msg = mapper.writeValueAsString(approve);
		}
		ResponseString.respongString(res, msg);
	}

	/**
	 * 未完成的数量
	 * 
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selAppCount")
	@ResponseBody
	public void selAppCount(HttpServletResponse res) throws IOException {
		msg = String.valueOf(adminServiceImpl.selApproveCount());
		ResponseString.respongString(res, msg);
	}

	/**
	 * 查找已完成的信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selAllApp")
	@ResponseBody
	public void selAllApp(String pageNum, String pageSize, HttpServletResponse res) throws IOException {
		List<Approve> approve = adminServiceImpl.selAllApprove(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		if (approve.size() < 1) {
			msg = "无记录";
		} else {
			ObjectMapper mapper = new ObjectMapper();
			msg = mapper.writeValueAsString(approve);
		}
		ResponseString.respongString(res, msg);
	}

	/**
	 * 查找已完成的数量
	 * 
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selAllAppCount")
	@ResponseBody
	public void selAllAppCount(HttpServletResponse res) throws IOException {
		msg = String.valueOf(adminServiceImpl.selAllApproveCount());
		ResponseString.respongString(res, msg);
	}

	@RequestMapping("selAllAppByName")
	@ResponseBody
	public void selAllAppByName(String name, String pageNum, String pageSize, HttpServletResponse res)
			throws IOException {
		List<Approve> approve = adminServiceImpl.selALLApproveByName(name, Integer.parseInt(pageNum),
				Integer.parseInt(pageSize));
		if (approve.size() < 1) {
			msg = "无记录";
		} else {
			ObjectMapper mapper = new ObjectMapper();
			msg = mapper.writeValueAsString(approve);
		}
		ResponseString.respongString(res, msg);
	}

	/**
	 * 查找指定名字已完成的数量
	 * 
	 * @param name
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selALLAppByNameCount")
	@ResponseBody
	public void selALLAppByNameCount(String name, HttpServletResponse res) throws IOException {
		msg = String.valueOf(adminServiceImpl.selALLApproveByNameCount(name));
		ResponseString.respongString(res, msg);
	}

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
	@ResponseBody
	@RequestMapping("addConfer")
	public void addConfer(HttpServletRequest req, HttpServletResponse res, @RequestParam MultipartFile[] file)
			throws IOException {
		AddConfer.addConfer(req, file, adminServiceImpl);
		ResponseString.respongString(res, "添加成功");
	}

	/**
	 * 找到相应的会议室信息回传 查找会议室
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("selConfer")
	@ResponseBody
	public void selConfer(HttpServletRequest req, HttpServletResponse res, String conferName, int pageNum, int pageSize)
			throws IOException {
		// 存放给前端的数据
		List<Confer> confer = new ArrayList<Confer>();
		// 存放查找的数据
		List<ConferInfor> conferinfo = new ArrayList<ConferInfor>();
		// 查找conferInfor
		// 名字为空显示所有的会议室信息
		if (conferName == "" || conferName == null) {
			conferinfo = adminServiceImpl.selAllConferInfor(pageNum, pageSize);
		} else {
			// 不为空查找信息
			conferinfo = adminServiceImpl.selConferByName(conferName, pageNum, pageSize);
		}
		if (conferinfo.size() < 1) {
			msg = "没有相关信息";
		} else {
			// 查找相应图片放入
			for (ConferInfor con : conferinfo) {
				// 封装数据dto
				Confer conf = new Confer();
				List<Img> imgs = adminServiceImpl.selImgByConferId(con.getCid());
				//判断有无图片
				if(imgs.size() > 0){
					for (Img img : imgs) {
						img.setPath("\\upload\\" + img.getPath());
					}
				} else {
					Img e = new Img();
					e.setPath("\\upload\\1.jpg");
					imgs.add(e);
				}
				conf.setCi(con);
				conf.setImg(imgs);
				confer.add(conf);
			}
		}
		msg = new ObjectMapper().writeValueAsString(confer);
		ResponseString.respongString(res, msg);
	}

	/**
	 * 返回会议室信息的数量
	 * 
	 * @param req
	 * @param name
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("selConferCount")
	public void selConferCount(HttpServletResponse res) throws IOException {
		msg = String.valueOf(adminServiceImpl.selConferInfoCount());
		ResponseString.respongString(res, msg);
	}

	/**
	 * 返回相应会议室信息的数量
	 * 
	 * @param req
	 * @param name
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("selConferCountByName")
	public void selConferCountByName(HttpServletResponse res, String name) throws IOException {
		msg = String.valueOf(adminServiceImpl.selConferInfoCountByName(name));
		ResponseString.respongString(res, msg);
	}

	/**
	 * 删除会议室
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("delConfer")
	public void delConfer(String cid, HttpServletRequest req, HttpServletResponse res) throws IOException {
		int id = Integer.parseInt(cid);
		
		int delImgByCid = 0;

		// 获取本地地址
		String url = req.getSession().getServletContext().getRealPath("//upload/");
		// 查找图片信息
		List<Img> selImgByConferId = adminServiceImpl.selImgByConferId(id);
		// 遍历删除图片
		if (selImgByConferId.size() > 0) {
			for (Img img : selImgByConferId) {
				File file = new File(url + img.getPath());
				file.delete();
			}
			// 删除会议室图片 必须要先删除图片 不然没有cid
			delImgByCid = adminServiceImpl.delImgByCid(id);
		}
		// 删除会议室信息
		int delConfer = adminServiceImpl.delConfer(id);
		String msg = "";
		if (delConfer > 0 && delImgByCid > 0) {
			msg = "删除成功";
		} else {
			msg = "删除失败";
		}
		ResponseString.respongString(res, msg);

	}

	/**
	 * 更新会议室信息
	 * 
	 * @param req
	 * @param file
	 * @param name
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("updConfer")
	public void updConfer(HttpServletRequest req, ConferInfor c, HttpServletResponse res) throws IOException {

		// 根据会议室名字 获取id
		int cid = adminServiceImpl.selOneConferByName(c.getConferName()).getCid();
		c.setCid(cid);
		
		// 获取会议室信息
		System.out.println(c.toString());
		// 更新信息
		int updConfer = adminServiceImpl.updConfer(c);
		if (updConfer < 1) {
			msg = "修改失败";
		} else {
			msg = "修改成功";
		}
		// 传回所有的会议室信息并显示
		ResponseString.respongString(res, msg);
	}

	@ResponseBody
	@RequestMapping("selPeoNum")
	public List<PeoNum> selPeoNum() {
		return adminServiceImpl.selPeoNum();
	}

	/**
	 * 搜索全部用户
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selUser")
	@ResponseBody
	public void searchUser(String pageNum, String pageSize, HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// 获取用户信息
		List<User> user;
		user = adminServiceImpl.selUser(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		// 将用户添加进Users
		List<Users> users = new ArrayList<Users>();
		if (user.size() < 1) {
			msg = "无用户";
		} else {
			for (User u : user) {
				// 获取用户的爱好
				List<Fav> selFavByUid = adminServiceImpl.selFavByUid(u.getUid());
				Users u2 = new Users();
				u2.setUser(u);
				u2.setFav(selFavByUid);
				users.add(u2);
			}
			ObjectMapper mapper = new ObjectMapper();
			msg = mapper.writeValueAsString(users);
		}
		ResponseString.respongString(res, msg);
	}

	/**
	 * 禁用用户
	 * 
	 * @param uid
	 * @throws IOException
	 * @return
	 */
	@ResponseBody
	@RequestMapping("offUser")
	public void offUser(String uid, HttpServletResponse res) throws IOException {
		int id = Integer.parseInt(uid);
		int updUserStatusById = adminServiceImpl.updUserStatusById(id, 1);
		if (updUserStatusById > 0) {
			msg = "修改成功";
		}
		ResponseString.respongString(res, msg);
	}

	/**
	 * 启用用户
	 * 
	 * @param uid
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("onUser")
	public void onUser(String uid, HttpServletResponse res) throws IOException {
		int id = Integer.parseInt(uid);
		int updUserStatusById = adminServiceImpl.updUserStatusById(id, 0);
		if (updUserStatusById > 0) {
			msg = "修改成功";
		}
		ResponseString.respongString(res, msg);
	}

	/**
	 * 用户数量
	 * 
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("selUserCount")
	public void selUserCount(HttpServletResponse res) throws IOException {
		msg = String.valueOf(adminServiceImpl.selUserCount());
		ResponseString.respongString(res, msg);
	}

	/**
	 * 指定用户的数量
	 * 
	 * @param username
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("selUserCountByName")
	public void selUserCountByName(String name, HttpServletResponse res) throws IOException {
		msg = String.valueOf(adminServiceImpl.selUserCountByName(name));
		ResponseString.respongString(res, msg);
	}

	/**
	 * 查找用户
	 * 
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @param res
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("selUserByName")
	public void selUserByName(String name, String pageNum, String pageSize, HttpServletResponse res)
			throws IOException {
		List<User> user = adminServiceImpl.selUserByName(name, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		// 将用户添加进Users
		List<Users> users = new ArrayList<Users>();
		if (user.size() < 1) {
			msg = "无该用户";
		} else {
			for (User u : user) {
				// 获取用户的爱好
				List<Fav> selFavByUid = adminServiceImpl.selFavByUid(u.getUid());
				Users u2 = new Users();
				u2.setUser(u);
				u2.setFav(selFavByUid);
				users.add(u2);
			}
			ObjectMapper mapper = new ObjectMapper();
			msg = mapper.writeValueAsString(users);
		}
		ResponseString.respongString(res, msg);
	}

	/**
	 * 修改密码
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @param res
	 * @param session
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("changeP")
	public void changeP(String oldPassword, String newPassword, HttpServletResponse res, HttpSession session)
			throws IOException {

		res.setCharacterEncoding("UTF-8");
		PrintWriter writer = res.getWriter();

		// 查找是否有响应密码的admin
		Admin admin = adminServiceImpl.selAdminByPassword(oldPassword);
		Admin sessionAdmin = (Admin) session.getAttribute("admin");

		// 判断管理员账号与密码是否一致
		if (admin != null && admin.getAdminName().equals(sessionAdmin.getAdminName())) {
			Admin a = new Admin();
			a.setAid(admin.getAid());
			a.setPassword(newPassword);
			int index = adminServiceImpl.updAdminPassword(a);
			if (index > 0) {
				writer.write("修改成功");
			} else {
				System.out.println("修改失败");
			}

		} else {
			writer.write("原密码错误");
		}
		writer.flush();
		writer.close();
	}
}
