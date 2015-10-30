package com.worksap.stm.SWARMS.controller;

import java.sql.Date;
import java.util.Random;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AjaxController {
	
	@PreAuthorize("MD")
	@RequestMapping("/ajax")
    public ModelAndView helloAjaxTest() {
        return new ModelAndView("ajax", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
    }
	
	@RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
    public @ResponseBody
    String getTime() {
        String result = "<br>Response from Ajax</b>";
        System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller..");
        return result;
    }

}
