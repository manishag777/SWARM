package com.worksap.stm.SWARMS.controller;


import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.worksap.stm.SWARMS.dao.EmployeeDao;
import com.worksap.stm.SWARMS.dto.EmployeeDto;
import com.worksap.stm.SWARMS.utils.CsvFileReader;
import com.worksap.stm.SWARMS.utils.PopulateData;
import com.worksap.stm.SWARMS.utils.PopulateMarkingTable;

@Controller
public class AjaxController {
	
	@Autowired
	PopulateMarkingTable populateMarkingTable;
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	CsvFileReader csvFileReader;
	
	
	@PreAuthorize("MD")
	@RequestMapping("/ajax")
    public ModelAndView helloAjaxTest(Principal principal) {
		
		 //String filename = "/WEB-INF/customer.csv";
	        
	     //ServletContext context = getServletContext();
		//String fileName = System.getProperty("user.home")+"/customer.csv";
		//System.out.println(fileName);
		//csvFileReader.readCsvFile(fileName);
		
		//populateMarkingTable.populateMarkingTable();
		return createModelAndView(principal,"event-timeline");
      //  return new ModelAndView("search-product2", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
    }
	
	private ModelAndView createModelAndView(Principal principal, String htmlPage){
			
			UserDetails userDetails =
					 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Collection<SimpleGrantedAuthority> authorities  = (Collection<SimpleGrantedAuthority>) userDetails.getAuthorities();
			
			Map<String,String> model = new HashMap<String,String>();
			String role = authorities.iterator().next().getAuthority()+"";
			model.put("role", role);
			String username  = principal.getName();
			EmployeeDto  employeeDto = employeeDao.getByUsername(username);
			model.put("name", employeeDto.getFirstName() + " "+employeeDto.getLastName());
			ModelAndView model2 = new ModelAndView(htmlPage);
			model2.addObject("model", model);
			return model2;
			
	}
		
	

}
