package com.worksap.stm.SWARMS.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.worksap.stm.SWARMS.dao.EmployeeDao;
import com.worksap.stm.SWARMS.dto.EmployeeDto;
import com.worksap.stm.SWARMS.entity.EmployeeAccountCreationEntity;
import com.worksap.stm.SWARMS.entity.EmployeeEntity;
import com.worksap.stm.SWARMS.entity.EmployeeFetchEntity;
import com.worksap.stm.SWARMS.entity.EmployeeListEntity;
import com.worksap.stm.SWARMS.entity.TestEntity;
import com.worksap.stm.SWARMS.service.spec.EmployeeService;

@Controller
public class admincontoller {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	
	@RequestMapping("/employeeManagement")
    public ModelAndView helloAjaxTest(Principal principal) {
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<SimpleGrantedAuthority> authorities  = (Collection<SimpleGrantedAuthority>) userDetails.getAuthorities();
		

		Map<String,String> model = new HashMap<String,String>();
		String role = authorities.iterator().next().getAuthority()+"";
		model.put("role", role);
		String username  = principal.getName();
		EmployeeDto  employeeDto = employeeDao.getByUsername(username);
		model.put("name", employeeDto.getFirstName() + " "+employeeDto.getLastName());

		ModelAndView model2 = new ModelAndView("adminpanel2");
		model2.addObject("model", model);

		return model2;

		
		
		//return new ModelAndView("adminpanel2", model);

    }
		
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/admin/addEmployee", method = RequestMethod.POST )
	@ResponseBody
	public void addUserAccount(@RequestBody EmployeeAccountCreationEntity employeeAccountCreationEntity) {
		
		employeeService.insert(employeeAccountCreationEntity);
	}
	
	@PreAuthorize("hasAuthority('MDS')")
	@RequestMapping(value = "/admin/returnEmployeData", method = RequestMethod.POST)
	@ResponseBody
	public EmployeeListEntity returnEmployeData(@RequestBody EmployeeFetchEntity entity) {
		System.out.println("/admin/returnEmployeData = ");
		EmployeeListEntity employeeListEntity = employeeService.getAllEmployeeData(entity);
		System.out.println(" size of list = " + employeeListEntity.getEmployeeEntities().size() + " draw = "+employeeListEntity.getDraw() + " recordsTotal = " + employeeListEntity.getRecordsTotal() + "recordsFiltered = " + employeeListEntity.getRecordsFiltered());
		
		for(EmployeeEntity entitys : employeeListEntity.getEmployeeEntities()){
			System.out.println(entitys.getUsername());
		}
		return employeeListEntity;
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/admin/test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TestEntity> returnEmployeData() {
		
		List<TestEntity> list = new ArrayList<TestEntity>();
		list.add(new TestEntity());
		list.add(new TestEntity());
		list.add(new TestEntity());
		//TestListEntity TestListEntity = new TestListEntity();
		//System.out.println(" size of list = " + employeeListEntity.getEmployeeEntities().size() + " draw = "+employeeListEntity.getDraw() + " recordsTotal = " + employeeListEntity.getRecordsTotal() + "recordsFiltered = " + employeeListEntity.getRecordsFiltered());
		return list;
	}
		
	
}
