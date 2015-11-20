package com.worksap.stm.SWARMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class MDController {
	
	@RequestMapping("/MDSalesReport")
    public ModelAndView manageProduct() {
		System.out.println("you called salesManager");
        return new ModelAndView("MD-sales-report", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");

    }
}
