package com.yzh.portal.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzh.dao.pojo.Approve;
import com.yzh.dao.pojo.ConferInfor;
import com.yzh.dao.pojo.Img;
import com.yzh.dao.pojo.Used;
import com.yzh.dao.pojo.User;
import com.yzh.portal.dto.Confer;
import com.yzh.portal.dto.Users;
import com.yzh.portal.method.ResponseString;
import com.yzh.service.AdminService;
import com.yzh.service.UserService;

@Controller
@RequestMapping("/pages")
public class CommonController {

	@Resource
	private UserService userServiceImpl;

	@Resource
	private AdminService adminServiceImpl;

	String uname = "";
	String msg = "";
	int count = 0;

	@RequestMapping("selConferInfoCount")
	@ResponseBody
	public void selConferInfoCount(String address, String size, String peoCount, String time, HttpServletResponse res)
			throws IOException {
		// 计算长度
		count = userServiceImpl.selConferInfoCount(address, Integer.parseInt(size == "" ? "0" : size), peoCount, time);
		// 返回长度
		ResponseString.respongString(res, String.valueOf(count));
	}

	/**
	 * 按照要求查找会议室信息
	 * 
	 * @param address
	 * @param size
	 * @param peoCount
	 * @param time
	 * @param pageNum
	 * @param pageSize
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selConferInfo")
	@ResponseBody
	public void selConferInfo(String address, String size, String peoCount, String time, String pageNum,
			String pageSize, HttpServletResponse res) throws IOException {
		List<ConferInfor> conferInfo = userServiceImpl.selConferInfo(address, Integer.parseInt(size == "" ? "0" : size),
				peoCount, time, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		// 有找到结果
		if (conferInfo.size() > 0) {
			// 封装数据实体
			List<Confer> confer = new ArrayList<>();
			// 图片所在路径
			String url = "/upload";
			// 找出图片地址放入
			for (ConferInfor c : conferInfo) {
				Confer con = new Confer();
				List<Img> img = adminServiceImpl.selImgByConferId(c.getCid());
				if (img.size() < 1) {
					img = new ArrayList<>();
					Img i = new Img();
					i.setPath(url + "\\1.jpg");
					img.add(i);
				} else {
					// 将图片全路径给前台
					for (Img im : img) {
						im.setPath(url + "\\" + im.getPath());
					}
				}
				con.setCi(c);
				con.setImg(img);
				confer.add(con);
			}
			ObjectMapper mapper = new ObjectMapper();
			msg = mapper.writeValueAsString(confer);
		} else {
			msg = "无符合信息";
		}
		ResponseString.respongString(res, msg);
	}

	/**
	 * 查询相应会议室名的所有的订单
	 * 
	 * @param name
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selAllAppByName")
	@ResponseBody
	public void selAllAppByName(String name, String pageNum, String pageSize, HttpServletResponse res)
			throws IOException {
		List<Approve> approves = userServiceImpl.selAllApproveByName(name == null ? "" : name,
				Integer.parseInt(pageNum), Integer.parseInt(pageSize), uname);
		ObjectMapper mapper = new ObjectMapper();
		msg = mapper.writeValueAsString(approves);
		ResponseString.respongString(res, msg);
	}

	/**
	 * 查询相应会议室名的未完成的订单
	 * 
	 * @param name
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selAppByName")
	@ResponseBody
	public void selAppByName(String name, String pageNum, String pageSize, HttpServletResponse res) throws IOException {
		List<Approve> approves = userServiceImpl.selApproveByName(name == null ? "" : name, Integer.parseInt(pageNum),
				Integer.parseInt(pageSize), uname);
		ObjectMapper mapper = new ObjectMapper();
		msg = mapper.writeValueAsString(approves);
		ResponseString.respongString(res, msg);
	}

	/**
	 * 查找未完成订单(相应名字)的总数
	 * 
	 * @param name
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selCount")
	@ResponseBody
	public void selCount(String name, HttpServletResponse res) throws IOException {
		int count = userServiceImpl.selCountByName(name == null ? "" : name, uname);
		ResponseString.respongString(res, String.valueOf(count));
	}

	/**
	 * 查找所有订单(相应名字)的总数
	 * 
	 * @param name
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selAllCount")
	@ResponseBody
	public void selAllCount(String name, HttpServletResponse res) throws IOException {
		int count = userServiceImpl.selAllCountByName(name == null ? "" : name, uname);
		ResponseString.respongString(res, String.valueOf(count));
	}

	/**
	 * 返回还在进行中的数量
	 * 
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("getCount")
	@ResponseBody
	public void getCount(HttpServletResponse res) throws IOException {
		int count = userServiceImpl.selApproveCount(uname);
		ResponseString.respongString(res, String.valueOf(count));
	}

	/**
	 * 返回所有的数量
	 * 
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("getAllCount")
	@ResponseBody
	public void getAllCount(HttpServletResponse res) throws IOException {
		int count = userServiceImpl.selAllApproveCount(uname);
		ResponseString.respongString(res, String.valueOf(count));
	}

	@RequestMapping("showAllApprove")
	@ResponseBody
	public void showAllApprove(String uname, String pageNum, String pageSize, HttpServletResponse res)
			throws IOException {
		List<Approve> approves = userServiceImpl.selAllApproveByUname(uname, Integer.parseInt(pageNum),
				Integer.parseInt(pageSize));
		if (approves.size() < 1) {
			msg = "无记录";
		} else {
			ObjectMapper mapper = new ObjectMapper();
			msg = mapper.writeValueAsString(approves);
		}
		ResponseString.respongString(res, msg);
	}

	/**
	 * 修改预约时间
	 * 
	 * @param appid
	 * @param date
	 * @param res
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("updApprove")
	@ResponseBody
	public void updApprove(String appid, int usedid, String date, HttpServletResponse res)
			throws IOException, ParseException {
		// 修改 时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = sdf.parse(date);

		boolean flag = true;
		// 判断会议室和时间是不是冲突
		List<Used> useds = this.userServiceImpl.selUsed();
		Used u = new Used();
		//获取useid 对应的对象
		for (Used used : useds) {
			if(usedid == used.getId()){
				u = used;
			}
		}
		//判断是不是冲突
		if(u.getTime() != null) {
			for (Used used : useds) {
				if (usedid != used.getId() && used.getCid() == u.getCid() && used.getTime().equals(sdf.parse(date))) {
					flag = false;
				}
			}
		}
		System.out.println(u);
		//没冲突
		if(flag) {
			int index = userServiceImpl.updAppTimeById(Integer.parseInt(appid), date2);
			if (index > 0) {
				msg = "修改成功";
			} else {
				msg = "网络问题,请刷新重试";
			}
		} else {
			msg = "改时间段已经被预约";
		}
		ResponseString.respongString(res, msg);
	}

	@RequestMapping("delRecord")
	@ResponseBody
	public void delRecord(int appid, HttpServletResponse res) throws IOException{
		int index = this.userServiceImpl.delApproveByid(appid);
		if(index>0){
			msg = "";
		} else {
			msg = "删除失败";
		}
		ResponseString.respongString(res, msg);
	}
	
	/**
	 * 取消预约
	 * 
	 * @param appid
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("delApprove")
	@ResponseBody
	public void delApprove(String appid, int uid, int usedid, double money, HttpServletResponse res)
			throws IOException {

		// 在表中删除信息
		int index = userServiceImpl.delApproveByid(Integer.parseInt(appid));

		// 将余额返回
		index += this.userServiceImpl.updUserMoneyAdd(uid, money);

		// 取消预约时间表
		index += this.userServiceImpl.delUsed(usedid);

		if (index > 2) {
			msg = "取消成功";
		} else {
			msg = "由于网络问题取消失败,请联系管理员,谢谢";
		}
		ResponseString.respongString(res, msg);
	}

	/**
	 * 查看预约的信息
	 * 
	 * @param uname
	 * @param pageNum
	 * @param pageSize
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("approveInfo")
	@ResponseBody
	public void approveInfo(String uname, String pageNum, String pageSize, HttpServletResponse res) throws IOException {
		List<Approve> approves = userServiceImpl.selApproveByUname(uname, Integer.parseInt(pageNum),
				Integer.parseInt(pageSize));
		if (approves.size() < 1) {
			msg = "无预约信息";
		} else {
			ObjectMapper mapper = new ObjectMapper();
			msg = mapper.writeValueAsString(approves);
		}
		ResponseString.respongString(res, msg);
	}

	/**
	 * 预约
	 * 
	 * @param id
	 * @param money
	 * @param uid
	 * @param res
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("orderConfer")
	@ResponseBody
	public void orderConfer(String id, String money,int cid, String uid, String date, HttpServletResponse res)
			throws IOException, ParseException {

		// 返回结果
		int index = 0;

		boolean flag = true;

		// 规定日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 判断会议室和时间是不是冲突
		List<Used> useds = this.userServiceImpl.selUsed();
		for (Used used : useds) {
			if (used.getCid() == cid && used.getTime().equals(sdf.parse(date))) {
				flag = false;
			}
		}

		// 没有登入的情况下不允许预约
		if (uid != null && uid != "") {

			// 时间不冲突
			if (flag) {

				// 修改用户余额
				User user = this.adminServiceImpl.selUserById(Integer.parseInt(uid));
				double lastMoney = user.getMoney() - Double.parseDouble(money);

				// 余额足够
				if (lastMoney > 0) {

					// 将预约的时间存在表里
					Used used = new Used();
					used.setCid(Integer.parseInt(id));
					used.setTime(sdf.parse(date));
					index += userServiceImpl.insUsed(used);

					// 更新余额
					index += this.userServiceImpl.updUserMoneyAdd(Integer.parseInt(uid), -Double.parseDouble(money));

					// 更新预定
					Approve app = new Approve();
					app.setCname(adminServiceImpl.selConferByCid(Integer.parseInt(id)).getConferName());
					app.setUname(userServiceImpl.selNameUserById(Integer.parseInt(uid)));
					app.setTime(sdf.parse(date));
					app.setDealtime(new Date());
					app.setMoney(Double.parseDouble(money));
					app.setStatus("待审核");
					app.setUsedid(used.getId());
					index += userServiceImpl.insApprove(app);

				} else {
					msg = "余额不足";
				}

			} else {
				msg = "已有其他用户预定了该时间段";
			}

		} else {
			msg = "请先登入";
		}
		// 判断是不是插入成功
		if (index > 2) {
			msg = "预约成功";
		}
		ResponseString.respongString(res, msg);
	}

	/**
	 * 指定id的会议室详细信息
	 * 
	 * @param id
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("detailConfer")
	@ResponseBody
	public void detailConfer(String id, HttpServletResponse res) throws IOException {
		// 返回的信息
		msg = "无会议室信息";
		// 封装dto
		Confer confer = new Confer();
		// 查找相应的confer信息
		ConferInfor conferInfor = adminServiceImpl.selConferByCid(Integer.parseInt(id));
		// 如果不为空则查找相应的图片信息
		if (conferInfor != null) {
			List<Img> img = adminServiceImpl.selImgByConferId(Integer.parseInt(id));
			// 为空则给默认图片
			if (img.size() < 1) {
				img = new ArrayList<>();
				Img i = new Img();
				i.setPath("\\upload\\1.jpg");
				img.add(i);
			} else {
				// 将图片全路径给前台
				for (Img im : img) {
					im.setPath("\\upload\\" + im.getPath());
				}
			}
			confer.setCi(conferInfor);
			confer.setImg(img);
			ObjectMapper mapper = new ObjectMapper();
			msg = mapper.writeValueAsString(confer);
		}
		ResponseString.respongString(res, msg);

	}

	/**
	 * 重定向到主界面
	 * 
	 * @return
	 */
	@RequestMapping("main")
	public String main(HttpSession session) {
		session.setAttribute("peoNum", userServiceImpl.selPeoNum());
		return "redirect:/pages/main.jsp";
	}

	/**
	 * 主页显示会议室信息
	 * 
	 * @param req
	 * @param res
	 * @param first
	 * @param last
	 * @throws IOException
	 */
	@RequestMapping("conferInfo")
	@ResponseBody
	public void conferInfo(HttpServletRequest req, HttpServletResponse res, int first, int last) throws IOException {

		HttpSession session = req.getSession();
		Users users = (Users) session.getAttribute("users");
		if (users != null) {
			uname = users.getUser().getUsername();
		}

		// 创建一个存放所有会议室信息的对象
		List<Confer> confer = new ArrayList<>();
		// 查找所有的会议室信息
		List<ConferInfor> conferInfor = adminServiceImpl.selAllConferInfor(first, last);

		if (conferInfor.size() > 1) {
			// 遍历所有的会议室 查找相应的图片信息 存放如confer中

			// 图片所在路径
			String url = "/upload";
			for (ConferInfor c : conferInfor) {
				Confer con = new Confer();
				List<Img> img = adminServiceImpl.selImgByConferId(c.getCid());
				if (img.size() < 1) {
					img = new ArrayList<>();
					Img i = new Img();
					i.setPath(url + "\\1.jpg");
					img.add(i);
				} else {
					// 将图片全路径给前台
					for (Img im : img) {
						im.setPath(url + "\\" + im.getPath());
					}
				}
				con.setCi(c);
				con.setImg(img);
				confer.add(con);
			}
			// 计算会议室数量
			int count = adminServiceImpl.selConferInfoCount();
			confer.get(0).setMsg(String.valueOf(count));
		} else {
			Confer c = new Confer();
			c.setMsg("0");
			confer.add(c);
		}

		ObjectMapper mapper = new ObjectMapper();
		msg = mapper.writeValueAsString(confer);
		ResponseString.respongString(res, msg);
	}
}
