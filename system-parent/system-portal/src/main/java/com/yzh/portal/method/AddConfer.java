package com.yzh.portal.method;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.yzh.dao.pojo.ConferInfor;
import com.yzh.dao.pojo.Img;
import com.yzh.service.AdminService;

public class AddConfer {
	public static String addConfer(HttpServletRequest req,MultipartFile[] file, AdminService adminServiceImpl){
		String conferName = req.getParameter("conferName");
		String size = req.getParameter("size");
		String peoCount = req.getParameter("peoNum");
		String price = req.getParameter("price");
		String address = req.getParameter("address");
		String people = req.getParameter("people");
		String tel = req.getParameter("tel");
		String status = "未使用";
		String comm = req.getParameter("comm");
		
		String url = req.getSession().getServletContext().getRealPath("/upload");
		System.out.println("upload --- -->"+url);
		String[] paths = SaveImg.saveFile(file, url);
		
		//返回消息
		String msg = "";
		//将会议室信息插入
		ConferInfor ci = new ConferInfor();
		ci.setConferName(conferName);
		ci.setPeoCount(peoCount);
		ci.setPrice(Double.parseDouble(price));
		ci.setSize(Integer.parseInt(size));
		ci.setAddress(address);
		ci.setPeople(people);
		ci.setTel(tel);
		ci.setComm(comm);
		// 插入conferInfor
		int insConfer = adminServiceImpl.insConfer(ci);
		if (insConfer > 0) {
			msg = "添加会议室信息成功";
		} else {
			msg = "添加会议室信息失败，请联系技术人员";
		}

		// 查找cid
		int conferId = adminServiceImpl.selOneConferByName(conferName).getCid();
		//将图像路径和cid一起插入数据库
		List<Img> img = new ArrayList<Img>();
		for (int j = 0; j < file.length; j++) {
			Img im = new Img();
			im.setPath(paths[j]);
			im.setCid(conferId);
			img.add(im);
			int insImg = adminServiceImpl.insImg(img.get(j));
			if (insImg > 0) {
				msg = "添加图片成功";
			} else {
				msg = "添加图片失败，请联系技术人员";
			}
		}

		return msg;
	}
}
