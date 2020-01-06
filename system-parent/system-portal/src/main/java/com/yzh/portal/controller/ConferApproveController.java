package com.yzh.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class ConferApproveController {
	
	@RequestMapping("conferApprove")
	public String conferApprove(){
		return "conferApprove";
	}
}
