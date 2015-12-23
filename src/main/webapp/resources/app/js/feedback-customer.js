var modifyStatus = "add";
var idParentMap = new Object();
var idProductDetailMap = new Object();
var notEligible = true;
var pincode;
var geometry;

$(document).ready(function() {
	var status = 0;

	var initPage = function() {
		$("#payment").click(serializeProductInfo);

		var custId = 0;
		$('#customer-save-button').click(updateCustomer);
		addRow();
		$('#addRow').click(addRow);
		$('#addCustomer').click(function(e) {
			console.info("at add Customer");
			modifyStatus = "add";
			console.info("modifyStatus=" + modifyStatus);
			document.getElementById("referralId").readOnly = false;
			clearForm();
		});

		$('#cid').keyup(function(e) {
			console.info("key up from cid");
			if (e.keyCode == 13) {
				var id = $(this).val();
				var x = getCustomerForGivenId(id);
			}
		});

		$('#cphone').keyup(function(e) {
			console.info("manish");
			if (e.keyCode == 13) {
				var pno = $(this).val();
				var x = getCustomerForGivenPhoneNo(pno);
			}
		});

		$('#otherDetails').hide();
		$('#others-checkbox').change(function(event) {
			var checkbox = event.target;
			if (checkbox.checked) {
				console.info("checked")
				$('#otherDetails').show();
			} else {
				console.info("unchecked")
				$('#otherDetails').hide();
			}
		});
		$('#feedback-save-button').click(addFeedback);
	}
	initPage();
});

var addFeedback = function() {
	console.info("Save Button Clicked")
	var feedback = new Object();
	feedback.pid = feedbackItemPID;
	feedback.customerId = document.getElementById('cid').value;
	feedback.higherThanAmazon = document.getElementById('amazon-checkbox').checked;
	feedback.higherThanEbay = document.getElementById('ebay-checkbox').checked;
	if (document.getElementById('others-checkbox').checked) {
		feedback.higherThanOthers = document.getElementById('otherDetails').value;
	}
	console.info(feedback);
	var url = 'addFeedback';
	$.ajax({
		url : url,
		data : JSON.stringify(feedback),
		type : 'POST',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
		},
	}).done(function() {
		$('#product-feedback-modal').modal('hide');
	});
}

var clearForm = function() {
	document.getElementById('firstName').value = '';
	document.getElementById('lastName').value = '';
	document.getElementById('emailId').value = '';
	document.getElementById('phoneNo').value = '';
	document.getElementById('gender').value = '';
	document.getElementById('dob').value = '';
	document.getElementById('city').value = '';
	document.getElementById('pinCode').value = '';
	document.getElementById('state').value = '';
	document.getElementById('referralId').value = '';
}

var addRow = function(evt) {

	var rowHTML = "<td style = 'width : 4%'><Button name='pid' class='deleteRow' text = '-'  /> </td>"
			+ "<td style= 'width: 7%'>"
			+ "<input name='pid' type='text' class='form-control pid'  />"
			+ "</td>"
			+ "<td style= 'width: 10%'>"
			+ "<input name='modelNo' type='text' class='form-control modelNo' disabled />"
			+ "</td>"
			+ "<td style= 'width: 20%'>"
			+ "<input name='name' type='text' class='form-control name' disabled />"
			+ "</td>"
			+ "<td style= 'width: 10%'>"
			+ "<input name='size' type='text' class='form-control size' disabled />"
			+ "</td>"
			+ "<td style= 'width: 10%'>"
			+ "<input name='color' type='text' class='form-control color' disabled />"
			+ "</td>"
			+ "<td style= 'width: 7%'>"
			+ "<input name='qty' type='number' class='form-control qty' disabled />"
			+ "</td>"
			+ "<td style= 'width: 10%'>"
			+ "<input name='price' class='form-control price' disabled/>"
			+ "</td>"
			+ "<td style= 'width: 7%'>"
			+ "<input name='discount' type='number' class='form-control discount' disabled />"
			+ "</td>"
			+ "<td style= 'width: 10%'>"
			+ "<input name='amount' class='form-control amount'  disabled  />"
			+ "</td>"
			+ "<td style= 'display:none;'>"
			+ "<input name='aQty' type='number' class='form-control pid'  />"
			+ "</td>"
			+ "<td style= 'display:none;'>"
			+ "<input name='costPrice' type='number' class='form-control pid'  />"
			+ "</td>"
			+ "<td style= 'display:none;'>"
			+ "<input name='margin' type='number' class='form-control pid'  />"
			+ "</td>"
			+ "<td style= 'display:none;'>"
			+ "<input name='duplicateId' type='number' class='form-control pid'  />"
			+ "</td>"

	var table = document.getElementById("billing-table");
	var row = table.insertRow(-1);
	row.innerHTML = rowHTML;

	$('.pid').keyup(function(e) {
		if (e.keyCode == 13) {
			//Testing part 
			var myRowIndex = $(this).parent().index();
			var parent = $(this).parent().parent();
			var child = parent.children().eq(2).children();
			var childName = child.prop('nodeName');

			// Application Part	 
			var pid = $(this).val();
			//var x = getProductForGivenPid(parent,pid);	
			var x = getProductDetailById(parent, pid);
		}
	});

	$('.qty').keyup(function(e) {
		if (e.keyCode == 13) {
			var parent = $(this).parent().parent();
			checkQty(parent);
		}
	});

	//deleteRow function
	$(".deleteRow").click(function() {
		var parent = $(this).parent().parent();
		clearAll(parent);
		parent.remove();

	});

}

var checkQty = function(parent) {

	console.info(total);
	total = total - parent.children().eq(9).children().val();

	var id = parent.children().eq(13).children().val();
	var qty = parent.children().eq(10).children().val();
	var currQty = parent.children().eq(6).children().val();
	var discount = parent.children().eq(8).children().val();
	var price = parent.children().eq(7).children().val();
	if (qty == 0)
		alert("Sorry! Product is not Available");

	console.info("id = " + id + "qty = " + qty);
	if (parseInt(qty) < parseInt(currQty)) {
		alert("Sorry! Available product quantity is only " + qty + " "
				+ currQty);
		parent.children().eq(9).children().val(0);
		parent.children().eq(6).children().val(0);
		grandTotal();
		idParentMap[id] = null;
		return;
	}
	idParentMap[id] = parent;
	var amt = (price - (price * discount) / 100) * currQty;
	console.info(price + " " + discount + " " + (price * discount) / 100);
	total += amt;
	console.info(total);
	parent.children().eq(9).children().val(amt);
	grandTotal();

}

var grandTotal = function() {
	$('#total').val(total);
	console.info(total);
}

var getProductDetailById = function(parent, id) {

	var url = 'getProductDetailByIdAndStore';
	clearAll(parent);
	$.ajax({
		url : url,
		data : {
			"id" : id,
			"storeId" : "ranchi"
		},
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
			if (!isRealValue(data) || data.qty == 0) {
				console.info(data);
				alert("Sorry! Product is not Available ");
				return;
			}

			sp = data.price + (data.price * data.margin) / 100;

			parent.children().eq(2).children().val(data.modelNo);
			parent.children().eq(3).children().val(data.name);
			parent.children().eq(4).children().val(data.size);
			parent.children().eq(5).children().val(data.color);
			parent.children().eq(6).children().prop('disabled', false);
			parent.children().eq(7).children().val(sp);
			parent.children().eq(8).children().val(data.discount);
			parent.children().eq(10).children().val(data.qty);
			parent.children().eq(11).children().val(data.price);
			parent.children().eq(12).children().val(data.margin);
			parent.children().eq(13).children().val(data.pdetailId);

			console.info(idParentMap);

		},
		error : function() {
			//console.info("At error");
			alert("Sorry! Product is not Available for this id");
			clearAll(parent);

			parent.children().eq(9).children().val(0);

		}
	}).done(function() {
		console.log("Done adding");

	});
}

var clearAll = function(parent) {

	var val = parent.children().eq(13).children().val();
	if (val != 0) {
		idParentMap[val] = null;
	}

	total = total - parent.children().eq(9).children().val();
	parent.children().eq(2).children().val('');
	parent.children().eq(3).children().val('');
	parent.children().eq(4).children().val('');
	parent.children().eq(5).children().val('');
	parent.children().eq(6).children().prop('disabled', true);
	parent.children().eq(7).children().val('');
	parent.children().eq(8).children().val('');
	parent.children().eq(9).children().val(0);
	parent.children().eq(10).children().val(0);
	parent.children().eq(11).children().val(0);
	parent.children().eq(12).children().val(0);
	parent.children().eq(13).children().val(0);
	grandTotal();
}

var updateCustomer = function(evt) {

	var firstName = $("#firstName").val();
	var phoneNo = $("#phoneNo").val();
	if (!isRealValue(firstName) || !isRealValue(phoneNo)) {
		alert("Phone No and First Name can't be empty");
	}
	console.info("modifyStatus=" + modifyStatus);

	var formData = $('#customer-form').serializeObject();
	console.info(formData);
	console.info(pincode);
	if (isRealValue(pincode)) {
		formData.pinCode = pincode;
		formData.lat = geometry.lat;
		formData.lng = geometry.lng;
	}

	if (typeof (formData.sportsInterest) === 'string') {
		formData.sportsInterest = [ formData.sportsInterest ];
	}
	console.info(notEligible);
	if (notEligible)
		formData.referrerId = 0;

	if (modifyStatus.localeCompare("add") == 0) {
		console.info("add");
		var url = 'addCustomer';
		formData["id"] = -1;
	} else if (modifyStatus.localeCompare("edit") == 0) {
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
			console.log("success and Done adding and data = " + data);
			console.info(data);
			if (formData["id"] == -1)
				displayCutomerInfo(1, formData, data);
			else
				displayCutomerInfo(1, formData, custId);
		},
	}).done(function() {
		console.log("Done adding");
		$('#customer-modal').modal('hide');
		//$('#employee-table').dataTable().fnReloadAjax();		
	});

}

var getCustomerForGivenId = function(id) {

	console.info("Getting customer for given id")
	var url = 'getCustomerInfoById';
	$.ajax({
		url : url,
		data : {
			"id" : id
		},
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
			displayCutomerInfo(1, data, id);
		},
		error : function() {
			console.info("error");
			displayCutomerInfo(2, "", id);
		}
	}).done(function() {
		console.log("Done adding");

	});
}

var getCustomerForGivenPhoneNo = function(phoneNo) {
	var url = 'getCustomerInfoByPhoneNo';
	$.ajax({
		url : url,
		data : {
			"phoneNo" : phoneNo
		},
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
			displayCutomerInfo(1, data, data.id);
		},
		error : function() {
			console.info("error");
			displayCutomerInfo(3, "", "");
		}
	}).done(function() {
		console.log("Done adding");
	});
}

var displayCutomerInfo = function(status, data, id) {
	custId = id;
	var div = document.getElementById("customer-info-div");
	if (status == 1) {
		html = "<h>Customer Id : "
				+ id
				+ "<br/>Name : "
				+ data.firstName
				+ " "
				+ data.lastName
				+ "</h><br/><button type='button' class=' editbtn btn btn-default btn-md'"
				+ "data-toggle='modal' data-target='#customer-modal'>"
				+ "<span class='glyphicon  glyphicon-pencil' aria-hidden='true' ></span> <span >Edit</span> </button>";

		div.innerHTML = html;

		$(".editbtn")
				.click(
						function() {
							status = 1; //edit
							document.getElementById('firstName').value = data.firstName;
							document.getElementById('lastName').value = data.lastName;
							document.getElementById('emailId').value = data.emailId;
							document.getElementById('phoneNo').value = data.phoneNo;
							document.getElementById('gender').value = data.gender;
							document.getElementById('dob').value = data.dob;
							document.getElementById('city').value = data.city;
							document.getElementById('pinCode').value = data.pinCode;
							document.getElementById('state').value = data.state;
							document.getElementById('country').value = data.country;
							if (isRealValue(data.referredId)
									&& data.referredId != 0) {
								document.getElementById('referralId').value = data.referredId;
							}
							document.getElementById("referralId").readOnly = true;
							document.getElementById("myModalLabel").innerHTML = "Edit Customer Information";
							custId = id;
							modifyStatus = "edit";
						});

	} else if (status == 2)
		div.innerHTML = "<h>Enter valid customer id</h>";
	else if (status == 3)
		div.innerHTML = "<h>Enter registered phone No.</h>";

}

var serializeProductInfo = function() {
	if (custId == 0 || custId == null || custId == '0' || custId == '') {
		alert("Insert valid customer");
		return;
	}
	var count = 0;
	productDetailArray = [];
	$.each(idParentMap, function(index, value) {
		var objectData = new Object();
		if (idParentMap[index] != null) {
			objectData['id'] = -1;
			objectData['orderID'] = -1;
			objectData['pid'] = idParentMap[index].children().eq(13).children()
					.val()
			objectData['qty'] = idParentMap[index].children().eq(6).children()
					.val();
			objectData['cp'] = idParentMap[index].children().eq(11).children()
					.val();
			;
			objectData['margin'] = idParentMap[index].children().eq(12)
					.children().val();
			objectData['discount'] = idParentMap[index].children().eq(8)
					.children().val();
			productDetailArray.push(objectData);
			count++;
			console.info(objectData);
		}
	});
	if (count == 0) {
		alert("No product has been added");
		return;
	}
	console.info("productDetailArray = ");
	console.info(productDetailArray);

	$('#payment-modal').modal('show');

	orderDto = new Object();
	orderDto['custId'] = custId;
	orderDto['subTotal'] = total;
	orderDto['storeId'] = 'ranchi'; // change it later 
	orderDto['staffId'] = $('#staff-filter').val();
	console.info("StaffId = " + orderDto['StaffId']);
}

var deleteTableRow = function() {
	console.log("deleteTableRow");
	var table = document.getElementById("billing-table");
	for (var i = 1, row; row = table.rows[i]; i++) {
		row.remove();
	}
	addRow();
}

var createOption = function(value, name) {
	var opt = document.createElement('option');
	opt.value = value;
	opt.innerHTML = name;
	return opt;
}