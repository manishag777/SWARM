package com.worksap.stm.SWARMS.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.worksap.stm.SWARMS.dao.EmployeeDao;
import com.worksap.stm.SWARMS.dto.EmployeeDto;

@Controller

public class MDController {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	
	@RequestMapping("/admin")
    public ModelAndView manageProduct(Principal principal) {
		System.out.println("you called CSO");

		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<SimpleGrantedAuthority> authorities  = (Collection<SimpleGrantedAuthority>) userDetails.getAuthorities();		
		Map<String,String> model = new HashMap<String,String>();
		String role = authorities.iterator().next().getAuthority()+"";
		model.put("role", role);
		String username  = principal.getName();
		EmployeeDto  employeeDto = employeeDao.getByUsername(username);
		model.put("name", employeeDto.getFirstName() + " "+employeeDto.getLastName());

		ModelAndView model2 = new ModelAndView("MD-sales-report2");
		model2.addObject("model", model);

		return model2;
        
    }
}
