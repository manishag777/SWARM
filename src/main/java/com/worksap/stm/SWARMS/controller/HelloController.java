package com.worksap.stm.SWARMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	@RequestMapping(value = { "/hello"})
	public String HelloWorld() {
		return "hello";
	}
	
	@RequestMapping(value = {"/" })
	public String HelloWorld2() {
		return "index";
	}

}
