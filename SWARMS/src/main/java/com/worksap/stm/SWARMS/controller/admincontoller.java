package com.worksap.stm.SWARMS.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping("/admin")
    public ModelAndView helloAjaxTest() {
		System.out.println("you called admin");
        return new ModelAndView("adminpanel", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");

    }
		
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/admin/addEmployee", method = RequestMethod.POST )
	@ResponseBody
	public void addUserAccount(@RequestBody EmployeeAccountCreationEntity employeeAccountCreationEntity) {
		
		employeeService.insert(employeeAccountCreationEntity);
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/admin/returnEmployeData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
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
	
	@PreAuthorize("hasAuthority('MD')")
	 @RequestMapping(value="/application/doajax.do", method = RequestMethod.POST)
	    public @ResponseBody String doAjax() {
	 
	        //Create the response, a well formed JSON including Datatables required vars.
	        //e.g.
	        String str = "{  \"sEcho\": 2 ," +
	            "   \"iTotalRecords\": 2," +
	            "   \"iTotalDisplayRecords\": 2," +
	            "   \"aaData\": [" +
	            "       [" +
	            "           \"Gecko\"," +
	            "           \"Firefox 1.0\"," +
	            "           \"Win 98+ / OSX.2+\"," +
	            "           \"1.7\"," +
	            "           \"A\"" +
	            "       ]," +
	            "       [" +
	            "           \"Gecko\"," +
	            "           \"Firefox 1.5\"," +
	            "           \"Win 98+ / OSX.2+\"," +
	            "           \"1.8\"," +
	            "           \"A\"" +
	            "       ]" +
	            "   ]" +
	            "}";
	        
	      /*  List<TestEntity> list = new ArrayList<TestEntity>();
			list.add(new TestEntity());
			list.add(new TestEntity());
			list.add(new TestEntity());*/
			//TestListEntity TestListEntity = new TestListEntity();
			//System.out.println(" size of list = " + employeeListEntity.getEmployeeEntities().size() + " draw = "+employeeListEntity.getDraw() + " recordsTotal = " + employeeListEntity.getRecordsTotal() + "recordsFiltered = " + employeeListEntity.getRecordsFiltered());
			//return list;
	 
	        return str;
	    }
	
	
}
