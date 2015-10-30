$(document).ready(function() {
	console.log("Hey Manish");
	var initPage = function() {
		console.log("Hey Manish");
		$('#employee-add-button').click(addEmployee);
		
		var dataTable = $('#employee-table').DataTable({
			'serverSide' : true,
			'ajax' : {
				url : 'admin/returnEmployeData',
				type : 'POST',
				contentType : "application/json",
				data: function ( d ) {
					// send only data required by backend API
					delete(d.columns);
					delete(d.order);
					delete(d.search);
					d.officeId = $('#employee-office-filter').val();
			      return JSON.stringify(d);
			    },
				
			    dataSrc : "employeeEntities",
			    xhrFields: {
				      withCredentials: true
				   }
			},
			
			columns: [
			          { data: 'username' },
			          { data: 'firstName' },
			          { data: 'lastName' },
			          { data: 'email' },
			          { data: 'phoneNo' }
					],
					select: "single"
		});
		
		
		$("#dropdown_office").prop("disabled", true);
		$("#dropdown_sport").prop("disabled", true);
	// Managing the employee form
		 $("#ss_office").click(function() {
			   if ($(this).is(":checked")) {
			      $("#dropdown_office").prop("disabled", false);
			   } else {
			      $("#dropdown_office").prop("disabled", true);  
			   }
			 });
			 
		 $("#sm_sports").click(function() {
			 	updateSportDropdown();
		 });	 	
			 
		 $("#cashier_office").click(function() {
			 updateSportDropdown();
		 });
			 
	  function updateSportDropdown(){
		 //console.log("Inside" + $("ss_office").is(":checked") + " "+ $("cashier_office").is(":checked")+" "+ $("#sm_sports").is(":checked"));
		 if ($("#ss_office").is(":checked") || $("#cashier_office").is(":checked") ){
			 console.log("cond1");
		      $("#dropdown_sport").prop("disabled", false);
		   } else {
			   console.log("cond2");
		      $("#dropdown_sport").prop("disabled", true);  
		   }
	 }
	
			
	}
	initPage();
}
);

var addEmployee = function(evt) {
	var formData = $('#employee-form').serializeObject();
	
	// modify role object to become an object if only one is selected
	console.info(formData);
	if (typeof(formData.roles) === 'string') {
		formData.roles = [formData.roles];
	}
	
	var url = 'admins/addEmployee';
	//console.log("formData = "+formData);
	
	$.ajax({
		url : url,
		data : JSON.stringify(formData),
		type : 'POST',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
		},
	}).done(function() {
		console.log("Done adding");
		$('#employee-add-modal').modal('hide');
		//$('#employee-table').dataTable().fnReloadAjax();
		
	});
};