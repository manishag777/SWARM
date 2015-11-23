package com.worksap.stm.SWARMS.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.worksap.stm.SWARMS.utils.PopulateData;

@Controller
public class AjaxController {
	
	@Autowired
	PopulateData populateData;
	
	
	@PreAuthorize("MD")
	@RequestMapping("/ajax")
    public ModelAndView helloAjaxTest() {
		
		populateData.populateMarkingStatusTable();
        return new ModelAndView("ajax", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
    }
	
	

}
