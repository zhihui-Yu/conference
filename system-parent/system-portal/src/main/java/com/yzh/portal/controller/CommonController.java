package com.yzh.portal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	/**
	 * 重定向到主界面
	 * @return
	 */
	@RequestMapping("main")
	public String main(){
		return "redirect:/pages/main.jsp";
	}
	
	@RequestMapping("conferInfo")
	@ResponseBody
	public void conferInfo(HttpServletRequest req,HttpServletResponse res) throws IOException{
		//创建一个存放所有会议室信息的对象
		List<Confer> confer = new ArrayList<>();
		//查找所有的会议室信息
		List<ConferInfor> conferInfor = adminServiceImpl.selAllConferInfor();
		//遍历所有的会议室  查找相应的图片信息 存放如confer中
		//图片所在路径
		String url = "/upload";
		for (ConferInfor c : conferInfor) {
			Confer con = new Confer();
			List<Img> img = adminServiceImpl.selImgByConferId(c.getCid());
			if(img.size() < 1){
				img = new ArrayList<>();
				Img i = new Img();
				i.setPath(url+"1.jpg");
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
		String string = mapper.writeValueAsString(confer);
		 ResponseString.respongString(res, string);
	}
}
