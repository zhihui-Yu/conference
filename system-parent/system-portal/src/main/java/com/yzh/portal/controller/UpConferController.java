package com.yzh.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class UpConferController {
	
	@RequestMapping("upConfer")
	public String upConfer(){
		return "upConfer";
	}
}
