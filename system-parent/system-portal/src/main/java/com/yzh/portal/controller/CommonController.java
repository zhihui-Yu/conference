package com.yzh.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
	@RequestMapping("main")
	public String main(){
		return "redirect:/pages/main.jsp";
	}
}
