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
	
	@RequestMapping("approveInfo")
	@ResponseBody
	public void approveInfo(String uname,String pageNum,String pageSize,HttpServletResponse res) throws IOException{
		List<Approve> approves = userServiceImpl.selApproveByUid(uname, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		System.out.println(approves.get(0).getTime()+"---------------");
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(approves);
		ResponseString.respongString(res, str);
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
	public void orderConfer(String id,String money,String uid,HttpServletResponse res) throws IOException, ParseException{
		//返回的消息
		String msg = "预约失败";
		//没有登入的情况下不允许预约
		if( uid != null && uid != ""){
			//将数据封装
			Approve app = new Approve();
			app.setCname(adminServiceImpl.selConferByCid(Integer.parseInt(id)).getConferName());
			app.setUname(userServiceImpl.selNameUserById(Integer.parseInt(uid)));
			//规定日期格式
			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date());*/
			app.setTime(new Date());
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
		String string = "无会议室信息";
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
			string = mapper.writeValueAsString(confer);
		}
		ResponseString.respongString(res, string);
		
	}
	
	/**
	 * 重定向到主界面
	 * @return
	 */
	@RequestMapping("main")
	public String main(){
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
		
		if(conferInfor!=null){
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
		String string = mapper.writeValueAsString(confer);
		 ResponseString.respongString(res, string);
	}
}
