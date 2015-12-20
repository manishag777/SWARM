
function getRecommendedProductDiv(id){
	
	var mid = id + '"';
    var html = '<div class="col-md-4">'
    	+ '<div class="box box-default">'
    		+ '<div class="box-header with-border">'
        		+ '<h3 class="box-title"><b>Sales Forecasting</b></h3>' 
        	+ '</div><!-- /.box-header -->'
        + '<div class="box-body">'
        + '<h4>Product Recommendation</h4>' 
        + '<hr/>'
        + '<table id = "productRecommendation'+mid+'  class="table table-striped table-bordered" >' 
        	+'<thead>'
        	+ '<tr class = "table-header">'   
        		+ '<td><h5>Product type</h5></td>'     
        		+ '<td><h5> &nbsp;&nbsp;&nbsp; Expected sales(qty.)</h5></td> '     
        	+ '</tr>'
        	+'</thead>'
        + '</table>' 
         + '<hr/>'
        + '<h4>Expected Revenue <span class= "pull-right" id = "expectedRevenue'+mid+'>Rs. 45000</span></h4>' 
        + '<hr/>'
        + '<Button class = "pull-right btn btn-info" id= "notifySalesManager'+mid+'> Notify Sales Manager</Button>' 



       +'</div>'
     +'</div>'
   +'</div>' ;
   
    //console.info(html);
   return html;

}

function getEventAwarenessDiv(id){
	
	var mid = id+'"';
	var html = '<div class="col-md-4" style = "display:none">'
				    +'<div class="box box-default">'
				    +'<div class="box-header with-border">'
				      +'<h3 class="box-title"><b>Event Awareness</b></h3>'
				    +'</div><!-- /.box-header -->'
				    +'<div class="box-body">'
				      +'<h4>Releavant Customer<span class= "pull-right"style="color:green" id = "relCust'+ mid+'>2540</span></h4>'
				      +'<hr/>'
				      +'<h4>Expected exisiting customer visit <span class= "pull-right" style="color:blue" id = "existCust' +mid+'>120</span><h4>'
				      +'<hr/>'
				      +'<h4>Expected new Customer visit <span class= "pull-right" style="color:blue" id = "newCust'+mid+'>85</span><h4>'
				      +'<hr/>'
				      +'<Button class = "pull-right btn btn-info" id = "notifyCustomer'+mid+'>Notify Customers about event</Button>'
				    +'</div>'
				  +'</div>'
				+'</div>';
	//console.info(html);
	return html;
	
	
	
}

var getPostMeetingDiv = function(id){


	var divId = '"assignTaskDiv' + id+'" ' ;
	var mid = id + '"';
    var html = '<div class = "col-md-12" id = '+divId+' style = "display:none;">'
	    +'<div class="nav-tabs-custom">'
	    +'<!-- Tabs within a box -->'
	    +'<ul class="nav nav-tabs">'
	      +'<li class="active"><a href="#revenue-chart" data-toggle="tab" id = "preEventTab'+mid+'>Before Event</a></li>'
	      +'<li><a href="#sales-chart" data-toggle="tab" id = "postEventTab'+mid+'>During Event</a></li>'
	      +'<li class="pull-right header"><i class="fa fa-inbox"></i> Sales strategy</li>'
	    +'</ul>'
	    +'<div class=" row tab-content no-padding">'
	         +'<div class = "col-md-12"  id = "preEventDiv'+mid+'>'
		         +'<div class ="row">'
		         	+ getRecommendedProductDiv(id)
		         	+ getDiscountSchemeDiv(id)
		         	+ getRecommendedScheme(id) 
			        + getEventAwarenessDiv(id)
		         +'</div>'
	         +'</div>'
	         +'<div class = "col-md-12" style="display:none"  id = "postEventDiv'+mid+'>'
		         +'<div class ="row" style = "width:100%">'
		         + getPostRecommendedScheme(id)
		         + getCouponDiv(id)
			     + getStallTaskAssignmentDiv(id)
		         +'</div>'
	         +'</div>'
	      +'</div>'
	    +'</div><!-- /.nav-tabs-custom -->'
	+'</div>';
    
    //console.info(html);
    return html;
	
}

var acitvateSalesTab = function(id){


	
	console.info("acitvateSalesTab");
	
	$("#preEventTab"+id).click(function(e){
		console.info("preEventTab");
		$("#postEventDiv"+id).css("display", "none");
		$("#preEventDiv"+id).attr('style',' width: 100%; display:show;');
	});

	$("#postEventTab"+id).click(function(e){
		console.info("postEventTab");
		$("#postEventDiv"+id).attr('style',' width: 100%; display:show;');
		$("#preEventDiv"+id).css("display", "none");
	});
}

function getStallTaskAssignmentDiv(id){

	var taskDateTimePickerId = '"taskDateTimePicker'+ id + '"';
	var taskDateTimeInputId = '"taskDateTimeInput'+ id + '"';
	var assignTaskButton = '"assignTaskButton'+ id + '"';
	id = id + '"';
	var stallCount = $("#stallNo").val();
	

	var boxBody = '<div class="box-body">'
					    +'<div class="form-group">'
					    	 + '<form id="task-assign-form'+id+'>'			        
					          +'<h4 style= "margin-bottom:0px;">Recommened and available sales staff</h4>'
					          +'<div class="funkyradio" id = "funkyradio'+ id +'>'
					         +'</div>'
					    	 + '<h4>Suggested Time For Training</h4>'
					         +'<div class="input-group date" id='+taskDateTimePickerId+'>'
					            + '<span class="input-group-addon">'
					                 +'<span class="glyphicon glyphicon-calendar"></span>'
					            +'</span>' 
					             +'<input type="text" name = "trainingTime" class="form-control" id ='+taskDateTimeInputId+'/>'
					         +'</div>'
				
					         + '</form>'
					     +'</div>'
					     +'<button type="button" id='+assignTaskButton+' class="btn btn-info assignTaskButton">Assign Task</button>'
					   +'</div><!-- /form -->'
					+'</div><!-- /.box-body -->' ;
	
	var html = '<div class="col-md-6">'
		    		+'<div class="box box-default">'
		    		+ '<div class="box-header with-border">'
		        		+ '<h3 class="box-title"><b>Stall task Assignment</b></h3>'
		        	+ '</div><!-- /.box-header -->'
		        		+boxBody
		        	+'</div>'
		        +'</div>' ;

   
	return html;
	
}

function assignTaskButtonListener(id){
	
	var input = "#assignTaskButton" + id ;
	$(document).on('click', input ,function(){
		var formData = $('#task-assign-form'+id).serializeObject();
		console.info(formData);
		formData.eventId = id;
		
		$.ajax({
			url : 'saveAssignedUser',
			data : JSON.stringify(formData),
			type : 'POST',
			contentType : "application/json",
			success : function(data) {
				console.info(data);
			},
		}).done(function() {
			console.log("Done adding");
			removeAllClass("#assignTask"+id);
			$("#assignTask"+id).addClass("btn-success");
			
			removeAllClass("#eventStatus"+id);
			$("#eventStatus"+id).addClass("btn-info");
			
			
			swal("Task Assigned and notification sent!", "", "success")
			
		});
		
	});
}

function setAssiginingTaskBody(stallCount, id){
	console.info(stallCount + " setAssiginingTaskBody " + id );
	var eventId = id;
	id = id + '"';
	var html = "hello";
	if(stallCount==1){
		var html = '<div class="funkyradio-primary">'
        + '<input type="checkbox" name="user" id="checkbox2'+id+' value = "mukesh" checked />' 
         + '<label for="checkbox2'+id+'>Mukesh Agrawal (Role - Handle Customer)</label>'
      +'</div>'  
      +'<div class="funkyradio-primary">'
        + '<input type="checkbox" name="user" id="checkbox5'+id+' value = "piyush" checked />' 
         + '<label for="checkbox5'+id+'>Piyush Dane (Role - Register Customer)</label>'
      +'</div>';
	}
	else{
		
		html = '<div class="funkyradio-primary">'
	        + '<input type="checkbox" name="user" id="checkbox2'+id+' value = "mukesh" checked />' 
	         + '<label for="checkbox2'+id+'>Mukesh Agrawal (Role - Handle Customer)</label>'
	      +'</div>'
	      +'<div class="funkyradio-primary">'
	         + '<input type="checkbox" name="user" id="checkbox3'+id+' value = "rishab" checked  />'
	         + '<label for="checkbox3'+id+'>Rishab jain (Role - Handle Customer)</label>'
	     +'</div>' 
	      +'<div class="funkyradio-primary">'
	       +  '<input type="checkbox" name="user" id="checkbox4'+id+' value = "mandeep" checked />' 
	        + '<label for="checkbox4'+id+'>Mandeep Singh (Role - Register customer)</label>' 
	     +'</div>' 
	      +'<div class="funkyradio-primary">'
	        + '<input type="checkbox" name="user" id="checkbox5'+id+' value = "piyush" checked />' 
	         + '<label for="checkbox5'+id+'>Piyush Dane (Role - Register Customer)</label>'
	      +'</div>';
	}
	
	document.getElementById("funkyradio"+eventId).innerHTML = html;
	//return html;
	
}

function getDiscountSchemeDiv(id){
 
  var mid = id+'"';	
  
  var html = '<div class="col-xs-8">'  
		    +'<div class="box box-primary">'
		    + '<div class="box-header">' 
		    +  '<h3 class="box-title">Set Discount</h3>'  
		    +  '</div><!-- /.box-header -->'
		    +  '<div class="box-body">'
		    +   '<div class="row margin">' 
		    +    '<div class="col-sm-12">'  
		    +     '<p>Discount: <span id = "dper'+mid+'>20%</span></p>'   
		    +      '<input type="text" id ="discSlider'+mid+'value="" class="slider form-control" data-slider-min="0" data-slider-max="100" data-slider-step="5" data-slider-value="20" data-slider-orientation="horizontal" data-slider-selection="before" data-slider-tooltip="show" data-slider-id="red">'  
		    +       '<br/>' 
		    +        '<p>Expected Revenue -  &nbsp;&nbsp;<span id = "revenue'+mid+'>Rs. 20,000</span><span> &nbsp;&nbsp;&nbsp;&nbsp;</span><Button class="btn-info pull-right" style = "width:150px;" id = "maximizeRevenue'+mid+'>Maximize Revenue</Button></p>'
		    +        '<p>Expected Profit - &nbsp;&nbsp;<span id = "profit'+mid+'>Rs. 20,00</span><span> &nbsp;&nbsp;&nbsp;&nbsp;'
		    +        '</span><Button style = "width:150px;" class="pull-right btn-info" id = "maximizeProfit'+mid+'>Maximize Profit</Button></p>'
		    +        '<Button style = "margin: auto" id = "GeneratePamphlet'+mid+' class = "btn btn-info">Generate Offers Pamphlet</Button>'
		    +      '</div>'
		    +    '</div>'
		    +  '</div><!-- /.box-body -->'
		    +'</div><!-- /.box -->'
		  +'</div><!-- /.col -->';
  
  return html;
  
}


function getCouponDiv(id){
	 
	  var mid = id+'"';	
	  
	  
	  var html = '<div class="col-xs-6">'
				      +'<div class="box box-primary">'
				      +'<div class="box-header">'
				        +'<h3 class="box-title">Generate Coupon</h3>'
				      +'</div><!-- /.box-header -->'
				      +'<div class="box-body">'
				        +'<div class="row margin">'
				          +'<div class="col-sm-12">'
				            +'<p><b>Expected Number of coupon : &nbsp;</b><span id = "expCC'+mid+'>100</span></p>'
				            +'<p><b>Set budget</b> <input type="number" id = "budget'+mid+'></input></p>'
				           
				            +'<p><b>Coupon Amount:</b> <span id = "cAmt'+mid+'>Rs. 1000</span></p>'
				            +'<input type="text" id ="couponSlider'+mid+' value="" class="cAmtSlider slider form-control" data-slider-min="0" data-slider-max="1000" data-slider-step="50" data-slider-value="20" data-slider-orientation="horizontal" data-slider-selection="before" data-slider-tooltip="show" data-slider-id="red">'
				            +'<p><b>Number of coupon : &nbsp;</b><span id = "couponCount'+mid+'>100</span></p>'
				            +'<Button style = "margin: auto" id = "generateCoupon'+mid+'>Generate Coupon</Button>'
				            
				          +'</div>'
				        +'</div>'
				      +'</div><!-- /.box-body -->'
				    +'</div><!-- /.box -->'
				  +'</div><!-- /.col -->';	  
	  return html;
} 



function getPostRecommendedScheme(id){
	   var mid = id+'"';	
	   var html =  '<div class="col-md-6" style = "display:none">'
	    + '<div class="box box-default">'
	    	+  '<div class="box-header with-border">'
	        		+'<h3 class="box-title"><b>Scheme selection</b></h3>'
	        +'</div><!-- /.box-header -->'
	       +'<div class="box-body">'
	         +'<div class="form-group">'
	           +'<h4>Recommended Schemes</h4>'
	            +'<div class="funkyradio" style = "margin-left:30px;" id = "postRecommendedOffers'+mid+'>'
	            +'</div>'
	           +'<h4>Other relevant schemes</h4>'
	           
	               +'<div class="funkyradio" style = "margin-left:30px;" id = "postOtherOffers'+mid+'>'
	               +'</div>'
	           +'</div>'
	           +'<Button class = "pull-right btn btn-info" id = "addTheSelectedPostScheme'+mid+'>Add the selected scheme</Button>'
	       +'</div>'
	     +'</div>'
	 +'</div>' ;
	   
	   

	 	return html;		
}

function getRecommendedScheme(id){
	var mid = id+'"';	
	   var html =  '<div class="col-md-4" style = "display:none">'
	    + '<div class="box box-default">'
	    	+  '<div class="box-header with-border">'
	        		+'<h3 class="box-title"><b>Scheme selection</b></h3>'
	        +'</div><!-- /.box-header -->'
	       +'<div class="box-body">'
	         +'<div class="form-group">'
	           +'<h4>Recommended Schemes</h4>'
	            +'<div class="funkyradio" style = "margin-left:30px;" id = "recommendedOffers'+mid+'>'
	            +'</div>'
	           +'<h4>Other relevant schemes</h4>'
	           
	               +'<div class="funkyradio" style = "margin-left:30px;" id = "otherOffers'+mid+'>'
	                  
	               +'</div>'
	           +'</div>'
	           +'<Button class = "pull-right btn btn-info" id = "addTheSelectedScheme'+mid+'>Add the selected scheme</Button>'
	       +'</div>'
	     +'</div>'
	 +'</div>' ;

	 	return html;
		
	}



