package com.yzh.portal.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzh.common.VerCode;

@Controller
public class VerCodeController {
	@ResponseBody
	@RequestMapping("/pages/Vcode")
	public String vercode(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedImage img = null;
		String words = "";
		VerCode verCode = new VerCode();
		Map<BufferedImage, String> grap = verCode.createGrap();

		for (Entry<BufferedImage, String> arg : grap.entrySet()) {
			img = arg.getKey();
			words = arg.getValue();
		}
		System.out.println("Code:"+words);
		ServletOutputStream outputStream = res.getOutputStream();

		ImageIO.write(img, "jpg", outputStream);

		HttpSession session = req.getSession();

		session.setAttribute("vercode", words);

		return null;
	}
}
