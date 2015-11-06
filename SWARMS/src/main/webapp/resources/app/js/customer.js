var modifyStatus = "add";
var total = 0;
//var custId;
$(document).ready(function () {
	 var status = 0;
	 
	 var initPage = function() {
		 $('#dob').datepicker({
		     dateFormat: 'dd-mm-yy',     
		 });
		 
		
		var custId = 0;
		$('#customer-save-button').click(updateCustomer);
		addRow();
		$('#addRow').click(addRow);
		$('#addCustomer').click(function(e){
			console.info("at add Customer");
			modifyStatus = "add";
			console.info("modifyStatus=" + modifyStatus);
		});
		
		$('#cid').keyup(function(e){
			console.info("manish");
			if(e.keyCode == 13)
			{
				var id = $(this).val();
				var x = getCustomerForGivenId(id);	
			}	
		});
		
	 }
	 
	 initPage();
});

var addRow = function(evt){
	
		var rowHTML = "<td style = 'width : 4%'><Button name='pid' class='deleteRow' text = '-'  /> </td>" 
			  +				"<td style= 'width: 11%'>" 
			  +      		"<input name='pid' type='text' class='form-control pid'  />"	
			  +	 			"</td>"
			  +      		"<td style= 'width: 20%'>" 
			  +      		"<input name='name' type='text' class='form-control name' disabled />"	
			  +	 			"</td>"
			  +      		"<td style= 'width: 10%'>" 
			  +      		"<select name='size' class='size form-control' >"
			  +	 			"</td>"
			  +      		"<td style= 'width: 10%'>" 
			  +      		"<select name='color' class='color form-control' >"
			  +	 			"</td>"
			  +      		"<td style= 'width: 10%'>" 
			  +      		"<input name='qty' type='number' class='form-control qty' disabled />"	
			  +	 			"</td>"
			  +      		"<td style= 'width: 10%'>" 
			  +      		"<input name='price' type='number' class='form-control price' disabled/>"	
			  +	 			"</td>"
			  +      		"<td style= 'width: 10%'>" 
			  +      		"<input name='discount' type='number' class='form-control discount' disabled />"	
			  +	 			"</td>"
			  +      		"<td style= 'width: 15%'>" 
			  +      		"<input name='amount' type='number' class='form-control amount'  disabled  />"	
			  +	 			"</td>"
	 
			var table = document.getElementById("billing-table");
			var row = table.insertRow(-1);
			row.innerHTML = rowHTML;		 
			
			$('.pid').keyup(function(e){
			if(e.keyCode == 13)
			{
				//Testing part 
				var myRowIndex = $(this).parent().index();
				var parent = $(this).parent().parent();
				var child = parent.children().eq(2).children();
				var childName = child.prop('nodeName');
					 
				// Application Part	 
				var pid = $(this).val();
				var x = getProductForGivenPid(parent,pid);		 
			}
			});
			
			$('.qty').keyup(function(e){
				if(e.keyCode == 13)
				{
					//Testing part 
					//var myRowIndex = $(this).parent().index();
					var parent = $(this).parent().parent();
					//var child = parent.children().eq(2).children();
					//var childName = child.prop('nodeName');
						 
					// Application Part	 
					var pid = $(this).val();
					var x = getProductDetails(parent);		 
				}
			});
			
			//deleteRow function
			$( ".deleteRow" ).click(function() {
				var parent = $(this).parent().parent();
				clearAll(parent);
				parent.remove();
			});
			
			$('.color').on('change', function() {
				 var parent = $(this).parent().parent();
				 clearAll(parent);
				});
			$('.size').on('change', function() {
				 var parent = $(this).parent().parent();
				 clearAll(parent); 
				});
			
	
}  

var getProductDetails = function(parent){
	
	var pid =  parent.children().eq(1).children().val();
	var size =  parent.children().eq(3).children().val();
	var color =  parent.children().eq(4).children().val();
	var qty = parent.children().eq(5).children().val();
	console.info("pid = "+pid +" size = "+size+ "color = "+color);
	 var url = 'getProductDetails';		
		$.ajax({
			url : url,
			data : {"pid" : pid, "size" : size, "color" : color},
			type : 'GET',
			contentType : "application/json",
			success : function(data) {
				clearAll(parent);
				console.info(data);
				if(qty > data.qty){
					AlertForQuantityIsNotSufficient(data.qty, parent);
				}
				else{
					var cp = data.price;
					var marginPercentage = data.margin;
					var sp = (data.margin)*0.01*cp + cp;
					var disc = (data.discount)*(sp);
					var amt = (sp-disc)*qty;
					console.info("sp = "+sp +" disc = "+disc+ "amt = "+amt);
					parent.children().eq(5).children().val(qty);
					parent.children().eq(6).children().val(sp);
					parent.children().eq(7).children().val(disc);
					parent.children().eq(8).children().val(amt);
					total = total+amt;
					grandTotal();
				}
			},
			error : function(data){
				AlertForQuantityIsNotSufficient(0,parent);
			}
		}).done(function() {
			console.log("Done adding");
		});
}

var AlertForQuantityIsNotSufficient = function(qty,parent){
	
	if(qty==0) alert("Sorry! Product is not Available");
	else alert("Sorry! Available product quantity is only "+ qty);
	clearAll(parent);
}

var grandTotal = function(){
	$('#total').val(total);
	var count = $("#billing_table").length;
	console.info("count = "+count);
}

var getProductForGivenPid = function(parent, pid){
	 var url = 'getProductInfoByPid';		
		$.ajax({
			url : url,
			data : {"pid" : pid},
			type : 'GET',
			contentType : "application/json",
			success : function(data) {
				console.info(data);
				clearAll(parent);		
				parent.children().eq(2).children().val(data.productName);
				parent.children().eq(5).children().prop('disabled',false);
				updateDropDown(parent.children().eq(3).children(),data.sizes);
				updateDropDown(parent.children().eq(4).children(),data.colors);
			},
			error : function(){
				console.info("At error");
				alert("wrong Id");
				clearAll(parent);		
				parent.children().eq(2).children().val('');
				parent.children().eq(5).children().prop('disabled',true);
				updateDropDown(parent.children().eq(3).children(),null);
				updateDropDown(parent.children().eq(4).children(),null);
				
			}
		}).done(function() {
			console.log("Done adding");
			//$('#customer-modal').modal('hide');
			//$('#employee-table').dataTable().fnReloadAjax();		
		});
}

var clearAll = function(parent){
	if(parent.children().eq(8).children().val()!=null)
		total = total - parent.children().eq(8).children().val() ;
	parent.children().eq(5).children().val('');
	parent.children().eq(6).children().val('');
	parent.children().eq(7).children().val('');
	parent.children().eq(8).children().val('');
	grandTotal();
}

 var updateCustomer = function(evt) {
	
	 console.info("modifyStatus=" + modifyStatus);
	 
	
	 
	 var formData = $('#customer-form').serializeObject();
	 console.info(formData);
	 
	 
	 
	 if (typeof(formData.sportsInterest) === 'string') {
			formData.sportsInterest = [formData.sportsInterest];
		}
		
	 
	 if(modifyStatus.localeCompare("add")==0){
		 console.info("add");
		 var url = 'addCustomer';
		 formData["id"] = -1;
	 }
	 else if(modifyStatus.localeCompare("edit")==0) {
		 console.info("edit");
		 var url = 'editCustomer';
		 formData["id"] = custId;
	 }
	 console.info(formData);
		
		//console.log("formData = "+formData);
		
		$.ajax({
			url : url,
			data : JSON.stringify(formData),
			type : 'POST',
			contentType : "application/json",
			success : function(data) {
				console.log("success and Done adding and data = "+ data);
				console.info(data);
				if(formData["id"] == -1)
					displayCutomerInfo(1,formData, data);
				else
					displayCutomerInfo(1,formData, custId);
			},
		}).done(function() {
			console.log("Done adding");
			$('#customer-modal').modal('hide');
			//$('#employee-table').dataTable().fnReloadAjax();		
		});
		
		
 }
 
 var getCustomerForGivenId = function(id){
	 var url = 'getCustomerInfoById';		
		$.ajax({
			url : url,
			data : {"id" : id},
			type : 'GET',
			contentType : "application/json",
			success : function(data) {
				console.info(data);
				displayCutomerInfo(1,data,id);
				//var h = document.getElementById("customer-info");
				//h.innerHTML = data.firstName+ " " + data.lastName ;
			},
			error: function(){
				console.info("error");
				displayCutomerInfo(0,"",id);
			}
		}).done(function() {
			console.log("Done adding");
			
			//$('#customer-modal').modal('hide');
			//$('#employee-table').dataTable().fnReloadAjax();		
		});
 }
 
 var updateDropDown = function(elem, dataString){
	 	
		elem.find('option').remove();
		if(dataString!=null){
		 	var Arr = dataString.split("_");
			for(var i = 0; i < Arr.length; i++) {
			    var opt = document.createElement('option');
			    opt.innerHTML = Arr[i];
			    opt.value = Arr[i];
			    console.log("innerHTML = " + elem.innerHTML);
			    elem.append(opt);
			}
 		}

 }
 
var displayCutomerInfo = function(status, data, id){
	custId = id;
	var div = document.getElementById("customer-info-div");
	if(status==1){
		html = "<h>Customer Id : " + id +  "<br/>Name : " + data.firstName+ " " + data.lastName + "</h><br/><button type='button' class=' editbtn btn btn-default btn-md'"
			+"data-toggle='modal' data-target='#customer-modal'>"
		 + "<span class='glyphicon  glyphicon-pencil' aria-hidden='true' ></span> <span >Edit</span> </button>" ;
		
		div.innerHTML = html;
		
		$( ".editbtn" ).click(function() {
			status = 1;  //edit
			console.info("hello= "+data);
			document.getElementById('firstName').value = data.firstName;
			document.getElementById('lastName').value = data.lastName;
			document.getElementById('emailId').value = data.emailId;
			document.getElementById('phoneNo').value = data.phoneNo;
			document.getElementById('gender').value = data.gender;
			document.getElementById('dob').value = data.DOB;
			document.getElementById('city').value = data.city;
			document.getElementById('pinCode').value = data.pinCode;
			document.getElementById('state').value = data.state;
			document.getElementById('country').value = data.country;
			var select  = document.getElementById('sportsInterest');
			for ( var i = 0, l = select.options.length, o; i < l; i++ )
			{
			  var o = select.options[i];
			  o.selected = false;
			  console.info(data.sportsInterest + " "+ o.value);
			  if (data.sportsInterest.indexOf(o.value) != -1 )
			  {
			    o.selected = true;
			  }
			}
			
			document.getElementById("myModalLabel").innerHTML = "Edit Customer Information";
			custId = id;
			modifyStatus = "edit";

			
		});
		
		
	}
	else
		div.innerHTML = "<h>Enter valid customer id</h>";
	
	
	
	

}


 
 
 
