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
import com.yzh.portal.dto.Confer;
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
	
	String msg = "";
	int count = 0;
	
	@RequestMapping("selConferInfoCount")
	@ResponseBody
	public void selConferInfoCount(String address, String size, String peoCount, String time,HttpServletResponse res) throws IOException{
		//计算长度
		count = userServiceImpl.selConferInfoCount(address, Integer.parseInt(size==""?"0":size), peoCount, time);
		//返回长度
		ResponseString.respongString(res, String.valueOf(count));
	}
	
	@RequestMapping("selConferInfo")
	@ResponseBody
	public void selConferInfo(String address, String size, String peoCount, String time,  
										String pageNum, String pageSize, HttpServletResponse res) throws IOException{
		List<ConferInfor> conferInfo = userServiceImpl.selConferInfo(address, Integer.parseInt(size==""?"0":size), 
																		peoCount, time, Integer.parseInt(pageNum),
																						Integer.parseInt(pageSize));
		// 有找到结果
		if(conferInfo.size() > 0){
			//封装数据实体
			List<Confer> confer = new ArrayList<>();
			//图片所在路径
			String url = "/upload";
			//找出图片地址放入
			for (ConferInfor c : conferInfo) {
				Confer con = new Confer();
				List<Img> img = adminServiceImpl.selImgByConferId(c.getCid());
				if(img.size() < 1){
					img = new ArrayList<>();
					Img i = new Img();
					i.setPath(url+"\\1.jpg");
					img.add(i);
				} else {
					//将图片全路径给前台
					for (Img im : img) {
						im.setPath(url+"\\"+im.getPath());
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
	 * @param name
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selAllAppByName")
	@ResponseBody
	public void selAllAppByName(String name, String pageNum, String pageSize, HttpServletResponse res) throws IOException{
		List<Approve> approves = userServiceImpl.selAllApproveByName(name==null?"":name, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		ObjectMapper mapper = new ObjectMapper();
		msg = mapper.writeValueAsString(approves);
		ResponseString.respongString(res, msg);
	}
	
	/**
	 * 查询相应会议室名的未完成的订单
	 * @param name
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selAppByName")
	@ResponseBody
	public void selAppByName(String name, String pageNum, String pageSize, HttpServletResponse res) throws IOException{
		List<Approve> approves = userServiceImpl.selApproveByName(name==null?"":name, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		ObjectMapper mapper = new ObjectMapper();
		msg = mapper.writeValueAsString(approves);
		ResponseString.respongString(res, msg);
	}
	
	/**
	 * 查找未完成订单(相应名字)的总数
	 * @param name
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selCount")
	@ResponseBody
	public void selCount(String name, HttpServletResponse res) throws IOException{
		int count = userServiceImpl.selCountByName(name==null?"":name);
		ResponseString.respongString(res, String.valueOf(count));
	}

	/**
	 * 查找所有订单(相应名字)的总数
	 * @param name
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("selAllCount")
	@ResponseBody
	public void selAllCount(String name, HttpServletResponse res) throws IOException{
		int count = userServiceImpl.selAllCountByName(name==null?"":name);
		ResponseString.respongString(res, String.valueOf(count));
	}
	
	/**
	 * 返回还在进行中的数量
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("getCount")
	@ResponseBody
	public void getCount(HttpServletResponse res) throws IOException{
		int count = userServiceImpl.selApproveCount();
		ResponseString.respongString(res, String.valueOf(count));
	}
	
	/**
	 * 返回所有的数量
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("getAllCount")
	@ResponseBody
	public void getAllCount(HttpServletResponse res) throws IOException{
		int count = userServiceImpl.selAllApproveCount();
		ResponseString.respongString(res, String.valueOf(count));
	}
	
	@RequestMapping("showAllApprove")
	@ResponseBody
	public void showAllApprove(String uname,String pageNum,String pageSize,HttpServletResponse res) throws IOException{
		List<Approve> approves = userServiceImpl.selAllApproveByUid(uname, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		if(approves.size() < 1){
			msg = "无记录";
		} else {
			ObjectMapper mapper = new ObjectMapper();
			msg = mapper.writeValueAsString(approves);
		}
		ResponseString.respongString(res, msg);
	}
	
	/**
	 * 修改预约时间
	 * @param appid 
	 * @param date
	 * @param res
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("updApprove")
	@ResponseBody
	public void updApprove(String appid,String date,HttpServletResponse res) throws IOException, ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		/*String date = sdf.format(new Date());*/
		Date date2 = sdf.parse(date);
		System.out.println(date +"-----"+appid);
		
		int index = userServiceImpl.updAppTimeById(Integer.parseInt(appid), date2);
		if(index > 0){
			msg = "修改成功";
		} else {
			msg = "网络问题,请刷新重试";
		}
		ResponseString.respongString(res, msg);
	}
	
	/**
	 * 取消预约
	 * @param appid
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("delApprove")
	@ResponseBody
	public void delApprove(String appid,HttpServletResponse res) throws IOException{
	
		int index = userServiceImpl.delApproveByid(Integer.parseInt(appid));
		if(index>0){
			msg = "取消成功";
		} else {
			msg = "由于网络问题取消失败,请联系管理员,谢谢";
		}
		ResponseString.respongString(res, msg);
	}
	
	/**
	 * 查看预约的信息
	 * @param uname
	 * @param pageNum
	 * @param pageSize
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("approveInfo")
	@ResponseBody
	public void approveInfo(String uname,String pageNum,String pageSize,HttpServletResponse res) throws IOException{
		List<Approve> approves = userServiceImpl.selApproveByUid(uname, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		if(approves.size() < 1){
			msg = "无预约信息";
		} else {
			ObjectMapper mapper = new ObjectMapper();
			msg = mapper.writeValueAsString(approves);
		}
		ResponseString.respongString(res, msg);
	}
	
	/**
	 * 预约
	 * @param id
	 * @param money
	 * @param uid
	 * @param res
	 * @throws IOException
	 * @throws ParseException 
	 */
	@RequestMapping("orderConfer")
	@ResponseBody
	public void orderConfer(String id,String money,String uid,String date,HttpServletResponse res) throws IOException, ParseException{
		//返回的消息
		msg = "预约失败";
		//没有登入的情况下不允许预约
		if( uid != null && uid != ""){
			//将数据封装
			Approve app = new Approve();
			app.setCname(adminServiceImpl.selConferByCid(Integer.parseInt(id)).getConferName());
			app.setUname(userServiceImpl.selNameUserById(Integer.parseInt(uid)));
			//规定日期格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			/*String date = sdf.format(new Date());*/
			app.setTime(sdf.parse(date));
			app.setDealtime(new Date());
			app.setMoney(Double.parseDouble(money));
			app.setStatus("待审核");
			//插入数据
			int index = userServiceImpl.insApprove(app);
			//判断是不是插入成功
			if(index > 0){
				msg = "预约成功";
			}
		} else {
			msg = "请先登入";
		}
		ResponseString.respongString(res, msg);
	}
	
	/**
	 * 指定id的会议室详细信息
	 * @param id
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("detailConfer")
	@ResponseBody
	public void detailConfer(String id,HttpServletResponse res) throws IOException{
		//返回的信息
		msg = "无会议室信息";
		//封装dto
		Confer confer = new Confer();
		//查找相应的confer信息
		ConferInfor conferInfor = adminServiceImpl.selConferByCid(Integer.parseInt(id));
		//如果不为空则查找相应的图片信息
		if(conferInfor != null){
			List<Img> img = adminServiceImpl.selImgByConferId(Integer.parseInt(id));
			//为空则给默认图片 
			if(img.size() < 1){
				img = new ArrayList<>();
				Img i = new Img();
				i.setPath("\\upload\\1.jpg");
				img.add(i);
			} else {
				//将图片全路径给前台
				for (Img im : img) {
					im.setPath("\\upload\\"+im.getPath());
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
	 * @return
	 */
	@RequestMapping("main")
	public String main(HttpSession session){
		session.setAttribute("peoNum", userServiceImpl.selPeoNum());
		return "redirect:/pages/main.jsp";
	}
	
	/**
	 * 主页显示会议室信息
	 * @param req
	 * @param res
	 * @param first
	 * @param last
	 * @throws IOException
	 */
	@RequestMapping("conferInfo")
	@ResponseBody
	public void conferInfo(HttpServletRequest req,HttpServletResponse res,int first,int last) throws IOException{
		//创建一个存放所有会议室信息的对象
		List<Confer> confer = new ArrayList<>();
		//查找所有的会议室信息
		List<ConferInfor> conferInfor = adminServiceImpl.selAllConferInfor(first, last);
		
		if(conferInfor.size() > 1){
			//遍历所有的会议室  查找相应的图片信息 存放如confer中
			
			//图片所在路径
			String url = "/upload";
			for (ConferInfor c : conferInfor) {
				Confer con = new Confer();
				List<Img> img = adminServiceImpl.selImgByConferId(c.getCid());
				if(img.size() < 1){
					img = new ArrayList<>();
					Img i = new Img();
					i.setPath(url+"\\1.jpg");
					img.add(i);
				} else {
					//将图片全路径给前台
					for (Img im : img) {
						im.setPath(url+"\\"+im.getPath());
					}
				}
				con.setCi(c);
				con.setImg(img);
				confer.add(con);
			}
			//计算会议室数量
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
