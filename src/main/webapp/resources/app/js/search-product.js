var Price, PId, PDetail_ID;
var Obj;
var selected_checkboxcount = 0;
$(document).ready(function() {
	var initPage = function() {		
		
		$("#wishlist-update-button").on("click", updateWishList);
		$("#customer-info-div").hide();
		 var datatable = $('#product-table').DataTable({
			'serverSide' : false,
			 'bFilter': false,
			 'binfo' : false,
			 "sDom": '<"top">rt<"bottom"flp><"clear">',
			  "iDisplayLength": 10,
			'ajax' : {
				url : 'returnFilteredProducts',
				type : 'POST',
				contentType : "application/json",
				data: function ( d ) {
					// send only data required by backend API
					delete(d.columns);
					delete(d.order);
					delete(d.search);
					d.sportId = $("#sport-type-filter").val();
					d.brand = $("#brand-type-filter").val();
					d.priceRange = $("#priceRange-type-filter").val();
					d.discountRange = $("#discount-type-filter").val();
					d.marginType = $("#profit-group-filter").val();
					d.searchText = $("#search-product-input").val();
					console.info("json = " +  JSON.stringify(d));	
					//console.info(d);
			        return JSON.stringify(d);
			    },
				
			    dataSrc : "productSearchFetchEntities",
	//		    xhrFields: {
	//			      withCredentials: true
	//			   }
			},
			
			
			columns: [
						{
						    "className":      'details-control',
						    "orderable":      false,
						    "data":           null,
						    "defaultContent": ''
						},
			          { data: 'modelNo' },
			          { data: 'name' },
			          { data: 'color' },
			          {data: 'size'  },
			          {data: 'discount'},
			          {data: null,
				           mRender : function(data, type, full){
				        	   var cp = data.price;
				        	   var discount = data.discount;
				        	   var margin = data.margin;
				        	   var sp1 =  parseFloat(cp + cp*margin/100).toFixed(2);
				        	   var sp2 =  parseFloat((sp1) - (sp1)*discount/100).toFixed(2); ;
				        	   
				        	   if(discount>0)
				        		   return '<h><del style = "color:red">'+sp1+'</del>&nbsp;&nbsp;&nbsp;<span style = "color:green">' + sp2+'</span></h>' ;
				        	   else 
				        		   return '<h style = "color:green">' + sp1 + '</h>' ;
				           }
				      },
			          {data: 'isAvailable'},
			          {data: null,
			           mRender: function(data, type, full) {
			        	   gdata = data;
			        	   console.info(gdata);
			        	   var parameter1 = "'"+ data.pid + "'";
			        	   var parameter2 = "'"+ data.pdetailId + "'";
			        	   var parameter3 = "'"+ data.price + "'";
			        	    var WishList = '<button type="button" class="btn btn-default btn-md" onClick = "wishListClickListener('+parameter1+','+ parameter2 +','+parameter3+')" data-toggle="modal"  data-target="#wishlist-modal">'
								 + '<span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>'
								 + '<span >Wishlist</span>'
								 + '</button>' ;
			        	    
			        	    return WishList;
			        	  
			           } 	  
			          }
					],
					sort: "true"
		});
		 
		 
		
		// Array to track the ids of the details displayed rows
		    var detailRows = [];
		 
		    $('#product-table tbody').on( 'click', 'tr td.details-control', function () {
		        var tr = $(this).closest('tr');
		        var row = datatable.row( tr );
		        var idx = $.inArray( tr.attr('id'), detailRows );
		 
		        if ( row.child.isShown() ) {
		            tr.removeClass( 'details' );
		            row.child.hide();
		 
		            // Remove from the 'open' array
		            detailRows.splice( idx, 1 );
		        }
		        else {
		            tr.addClass( 'details' );
		            row.child( format( row.data() ) ).show();
		 
		            // Add to the 'open' array
		            if ( idx === -1 ) {
		                detailRows.push( tr.attr('id') );
		            }
		        }
		    } );
		 
		    // On each draw, loop over the `detailRows` array and show any child rows
		    datatable.on( 'draw', function () {
		        $.each( detailRows, function ( i, id ) {
		            $('#'+id+' td.details-control').trigger( 'click' );
		        } );
		    } );
		 
		    
			$('#cust_id').keyup(function(e){
				console.info("manish");
				if(e.keyCode == 13)
				{
					var id = $(this).val();
					var x = getCustomerForGivenId(id);	
				}	
			});
			
			$('.emailCheckbox').click(function(){
			    if (this.checked) {
			    	selected_checkboxcount++;
			    }
			    else{
			    	selected_checkboxcount--;
			    }
			});
			
		//	$('#profit-group-filter').change(function() { $('#product-table').dataTable().fnReloadAjax(); });
			
			$('#s_pid').keyup(function(e){
				if(e.keyCode == 13)
				{
					$('#product-table').dataTable().fnReloadAjax(); 
				}	
			});

			$('#s_brand').keyup(function(e){
				if(e.keyCode == 13)
				{
					$('#product-table').dataTable().fnReloadAjax(); 
				}	
			});

			$('#s_name').keyup(function(e){
				if(e.keyCode == 13)
				{
					$('#product-table').dataTable().fnReloadAjax(); 
				}	
			});
			

			fetchProfitList();
			fetchSportList();
			fetchBrandList();
			$('#sport-type-filter').change(function() { 
				 fetchBrandList();	
				$('#product-table').dataTable().fnReloadAjax();
			});
			$('#priceRange-type-filter').change(function() { $('#product-table').dataTable().fnReloadAjax(); });
			$('#discount-type-filter').change(function() { $('#product-table').dataTable().fnReloadAjax(); });
			$('#profit-group-filter').change(function() { $('#product-table').dataTable().fnReloadAjax(); });
			$('#search-product-input').keyup(function(){ $('#product-table').dataTable().fnReloadAjax();});
			$('#brand-type-filter').change(function() { $('#product-table').dataTable().fnReloadAjax(); });

		
	}
	initPage();
});

var fetchProfitList = function(){
	var select = document.getElementById('profit-group-filter');
	select.appendChild(createOption(0,"All"));
		$.ajax({
		url : 'fetchProfitList',
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
			
			for (var i in data) {
//				var opt = document.createElement('option');
//				opt.value = data[i].id;
//				opt.innerHTML = data[i].name +"("+ data[i].margin+"%)";
//				select.appendChild(opt);
				select.appendChild(createOption(data[i].id,data[i].name));
			}
			
		},
	}).done(function() {
				
	});
}




var updateEmailId = function(){

	var url = 'updateEmailId';
	console.log(Obj);
	
	$.ajax({
		url : url,
		data : {id : custId, emailId : $("#customer-emailId").val() },
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
		},
	}).done(function() {
		console.log("Done adding product detail");
		$('#wishlist-modal').modal('hide');
		
		
		
	});
}

var updateWishList = function(){

	
	if($("#customer-info-div").is(":visible")){
		if(!validateEmail($("#customer-emailId").val()))
			alert("Email Id is not valid");
		else if(selected_checkboxcount<=0){
			alert("Select at least one of the mailing condition");
		}
		else{
			Obj.enableWhenPriceDown = 0;
			Obj.enableWhenProductAvailable = 0;
			
			if ($('#enalbeMailWhenPriceDown').is(':checked')) {
				Obj.enableWhenPriceDown = 1;
			}
			if ($('#enalbeMailWhenProductsAvailable').is(':checked')) {
				Obj.enableWhenProductAvailable = 1;
			}
			
			updateEmailId();
			sendWishListObject();
		}
		
	}
	else{
		alert("Add customer for subscribing");
	}
}

var sendWishListObject =  function(){
	
	var url = 'addWishList';
	console.log(Obj);
	
	$.ajax({
		url : url,
		data : JSON.stringify(Obj),
		type : 'POST',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
		},
	}).done(function() {
		console.log("Done adding product detail");
		$('#wishlist-modal').modal('hide');
		//ReloadGridProducts();
		
		
	});
	
	
}

function wishListClickListener(pid, pdetail_id, price){	

	Obj = new Object();
	console.info("pdetail_id = " + pdetail_id);
	Obj.price = price;
	Obj.id = pdetail_id;
	var dialog_header = document.getElementById("subscribe-customer-header"); 
	dialog_header.innerHTML = "Subscribe product of id : "+ pid;	
	$("#customer-info-div").hide();
	$("#customer-info-invalid-div").hide();
	$("#cust_id").val('');
}

function format (d) {
	var div = "<div class='p col-lg-2'>"
			  +	"<img src =" + d.url + "> </img>" 
			  +"</div>"
			  +"<div class='col-lg-4'>"
			  +	"<p><b>Brand:</b>" + d.brand  + "</p>" 
			  + "<p>Only <b>" + d.qty  + "</b> in stock.</p>" 
			  +  "<p" +d.info +"</p><br/>" 
			  +"</div>" ;
	//var testDiv = "<img src =" + d.url +"> </img>";
	//console.info(d.url);
	//var myDiv = document.getElementById("col2");
	//myDiv.innerHtml = testDiv;
	$("#my_image").attr("src",d.url);
	$("#my_image2").attr("src",d.url);
	$("#my_image3").attr("src",d.url);
    return div;
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
				Obj.custId = id;
				
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

var displayCutomerInfo = function(status, data, id){
	console.info(data);
	custId = id;
	
	
	if(status==1){

		$("#customer-info-div").show();
		$("#customer-info-invalid-div").hide();
		var div1 = document.getElementById("customer-id");
		var div2 = document.getElementById("customer-name");
		
		Id = "<h>Id : " + id ;
		Name = "<h>Name : " + data.firstName+ " " + data.lastName + "</h>";
		
		div1.innerHTML = Id;
		div2.innerHTML = Name;
		if(isRealValue(data.emailId)){
			$("#customer-emailId").val(data.emailId);
		}
		else{
			$("#customer-emailId").val('');
		}
					
	}
	else{
		
		$("#customer-info-div").hide();
		$("#customer-info-invalid-div").show();
		var div = document.getElementById("customer-info-invalid-div");
		div.innerHTML = "<h>Enter valid customer id</h>";
		
	}
}

function validateEmail(email) {
    var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    return re.test(email);
}


var fetchSportList = function(){

	var select = document.getElementById('sport-type-filter');
	$.ajax({
		url : 'fetchSportList',
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			sportData = data;
			console.info(data);
			for (var i in data) {
				var opt = document.createElement('option');
				opt.value = data[i].sportId;
				opt.innerHTML = data[i].name;
				select.appendChild(opt);
				
			}
			
		},
	}).done(function() {
				
	});
}

var fetchBrandList = function(){
	$('#brand-type-filter').empty();
	var select = document.getElementById('brand-type-filter');
	select.appendChild(createOption("0","All Brand"));
	console.info("Hey in fetchBrandList");
	$.ajax({
		url:'getBrandList',
		data:{'sport_id': $("#sport-type-filter").val()},
		success : function(data){
			console.info(data);
			for (var i in data) {
					select.appendChild(createOption(data[i], data[i]));
				}
		}
		
	});
}

var createOption = function(value, name){
	var opt = document.createElement('option');
	opt.value = value;
	opt.innerHTML = name;
	return opt;
}









