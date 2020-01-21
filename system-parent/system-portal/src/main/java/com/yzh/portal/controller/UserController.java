package com.yzh.portal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzh.dao.pojo.Fav;
import com.yzh.dao.pojo.User;
import com.yzh.portal.dto.Users;
import com.yzh.portal.method.ResponseString;
import com.yzh.service.UserService;

@Controller()
@RequestMapping("/pages")
public class UserController {
	@Resource
	private UserService userServiceImpl;
	
	String msg = "";
	
	/**
	 * 用户详细信息页面
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selUser")
	@ResponseBody
	public void selUser(HttpServletRequest req, HttpServletResponse res) throws IOException{
		HttpSession session = req.getSession();
		Users users = (Users)session.getAttribute("users");
		List<Fav> favs = userServiceImpl.selFavByUid(users.getUser().getUid());
		users.setFav(favs);
		users.setUser(userServiceImpl.selUser(users.getUser()));
		msg = new ObjectMapper().writeValueAsString(users);
		ResponseString.respongString(res, msg);
	}
	
	
	/**
	 * 充值
	 * @param req
	 * @param money
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("addMoney")
	@ResponseBody
	public void addMoney(HttpServletRequest req, String money, HttpServletResponse res) throws IOException{
		HttpSession session = req.getSession();
		Users users = (Users)session.getAttribute("users");
		//没登入 先登入
		if(users == null){
			msg = "请先登入";
		} else {
			double mon = Double.parseDouble(money);
			//增加金额
			int index = userServiceImpl.updUserMoneyAdd(users.getUser().getUid(), mon);
			if(index > 0){
				msg = "充值成功";
			} else {
				msg = "系统出现问题";
			}
		}
		ResponseString.respongString(res, msg);
	}
	
	/**
	 * 缴费
	 * @param req
	 * @param needMoney
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("payMoney")
	@ResponseBody
	public void payMoney(HttpServletRequest req,int id, String needMoney, HttpServletResponse res) throws IOException{
		HttpSession session = req.getSession();
		Users users = (Users)session.getAttribute("users");
		//没登入 先登入
		if(users == null){
			msg = "请先登入";
		} else {
			//判断账户余额是不是足够
			double money = users.getUser().getMoney();
			double mon = Double.parseDouble(needMoney);
			if(money >= mon){
				//减少用的金额
				int index = userServiceImpl.updUserMoneyDes(users.getUser().getUid(), mon);
				if(index > 0){
					//修改订单状态
					userServiceImpl.updApproveStatus(id, "待通过");
					msg = "付款成功";
				} else {
					msg = "系统出现问题";
				}
			} else {
				msg = "";
			}
		}
		
		ResponseString.respongString(res, msg);
	}
	
	/**
	 * 反馈信息
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("BackMsg")
	@ResponseBody
	public void backMsg(HttpServletRequest req,HttpServletResponse res) throws IOException{
		//获取参数
		String msg = req.getParameter("msg");
		String uid = req.getParameter("uid");
		
		//保存信息
		int index = userServiceImpl.insUserMsg(Integer.parseInt(uid), msg);
		//判断是不是保存成功
		if(index < 0){
			msg = "提交失败";
		} else {
			msg = "提交成功";
		}
		res.setCharacterEncoding("utf-8");
		PrintWriter writer = res.getWriter();
		writer.write(msg);
		writer.flush();
		writer.close();
	}
	
	/**
	 * 修改信息
	 */
	@RequestMapping("changeUserInfo")
	@ResponseBody
	public void changeUserInfo(HttpServletRequest req,String[] favs,@RequestParam(value="uid")String uid,HttpServletResponse res) throws IOException{
		System.out.println(favs);
		
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
		u.setSex(sex.equals("1")?1:0);
		u.setUid(Integer.parseInt(uid));
		//更新数据库信息
		//更新用户信息
		int index = userServiceImpl.updUserById(u);
		//更新爱好信息
		boolean flag = true;
		//删除所有的信息
		userServiceImpl.delUserFav(u.getUid());
		
		//如果前端传来的favs不是是空
		if(favs!=null){
			for (String fname : favs) {
				Fav f = new Fav();
				f.setFname(fname);
				f.setUid(u.getUid());
				int index2 = userServiceImpl.insFavByUid(f);
				if(index2 < 0){
					flag = false;
				}
			}
		}
		if(index > 0 && flag){
			msg = "修改成功";
		} else {
			msg = "修改失败";
		}
		
		//将新的信息放入session 查找用户，爱好 将信息放入session
		//获取session中的用户对象
		Users users = (Users)req.getSession().getAttribute("users");
		//将用对象中的用户信息放入一个 新的对象
		User user = users.getUser();
		//将重新查找出来的用户信息放入之前创建的对象中
		user = userServiceImpl.selUser(user);
		//重新查找用户的爱好
		List<Fav> fav = userServiceImpl.selFavByUid(user.getUid());
		//将爱好放入用户对象
		users.setFav(fav);
		//将用户信息放入用户对象
		users.setUser(user);
		//替换之前的session对象
		req.getSession().setAttribute("users", users);
		
		//返回信息
		res.setCharacterEncoding("utf-8");
		PrintWriter writer = res.getWriter();
		writer.write(msg);
		writer.flush();
		writer.close();
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("changeUserPassword")
	public void changePassword(HttpServletRequest request, HttpServletResponse res) throws IOException {
		//获取参数
		String old = request.getParameter("old");
		String new1 = request.getParameter("new1");
		String new2 = request.getParameter("new2");
		
		//从session中获取用户信息
		Users users = (Users) request.getSession().getAttribute("users");
		User user = users.getUser();
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
		res.setCharacterEncoding("utf-8");
		PrintWriter writer = res.getWriter();
		writer.write(msg);
		writer.flush();
		writer.close();
	}
}
