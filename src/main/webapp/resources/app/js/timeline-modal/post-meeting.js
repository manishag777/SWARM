
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
        + '<Button class = "pull-right btn btn-info"> Notify Sales Manager</Button>' 



       +'</div>'
     +'</div>'
   +'</div>' ;
   
    console.info(html);
   return html;

}

function getRecommendedScheme(id){

   var mid = id+'"';	
   var html =  '<div class="col-md-4">'
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
           +'<Button class = "pull-right btn btn-info">Add the selected scheme</Button>'
       +'</div>'
     +'</div>'
 +'</div>' ;

 	return html;
	
}

function getEventAwarenessDiv(id){
	
	var mid = id+'"';
	var html = '<div class="col-md-4">'
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
				      +'<Button class = "pull-right btn btn-info">Notify Customers about event</Button>'
				    +'</div>'
				  +'</div>'
				+'</div>';
	console.info(html);
	return html;
	
	
	
}

var getPostMeetingDiv = function(id){

	
	var divId = '"assignTaskDiv' + id+'" ' ;
	var mid = id + '"';
    var html = '<div class = "col-md-12" id = '+divId+'>'
	    +'<div class="nav-tabs-custom">'
	    +'<!-- Tabs within a box -->'
	    +'<ul class="nav nav-tabs pull-right">'
	      +'<li class="active"><a href="#revenue-chart" data-toggle="tab" id = "preEventTab'+mid+'>preEvent</a></li>'
	      +'<li><a href="#sales-chart" data-toggle="tab" id = "postEventTab'+mid+'>pstEvent</a></li>'
	      +'<li class="pull-left header"><i class="fa fa-inbox"></i> Sales</li>'
	    +'</ul>'
	    +'<div class=" row tab-content no-padding">'
	         +'<div class = "col-md-12"  id = "preEventDiv'+mid+'>'
		         +'<div class ="row">'
			         + getRecommendedProductDiv(id)
			         + getRecommendedScheme(id)
			         + getEventAwarenessDiv(id)
		         +'</div>'
	         +'</div>'
	         +'<div class = "col-md-12" style="display:none"  id = "postEventDiv'+mid+'>'
		         +'<div class ="row" style = "width:100%">'
		         + getPostRecommendedScheme(id)
			     + getStallTaskAssignmentDiv(id)
		         +'</div>'
	         +'</div>'
	      +'</div>'
	    +'</div><!-- /.nav-tabs-custom -->'
	+'</div>';
    
    console.info(html);
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
	var assignTaskButton = '"assignTaskButton'+ id + '"';
	id = id + '"';
	var stallCount = $("#stallNo").val();
	

	var boxBody = '<div class="box-body">'
					    +'<div class="form-group">'
					    	 + '<form id="task-assign-form'+id+'>'			        
					          +'<h4 style= "margin-bottom:0px;">Recommened and available sales staff</h4>'
					          +'<div class="funkyradio" id = "funkyradio'+ id +'>'
					         +'</div>'
					    	 + '<h4>Set Time For Training</h4>'
					         +'<div class="input-group date" id='+taskDateTimePickerId+'>'
					            + '<span class="input-group-addon">'
					                 +'<span class="glyphicon glyphicon-calendar"></span>'
					            +'</span>' 
					             +'<input type="text" name = "trainingTime" class="form-control" />'
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

function getPostRecommendedScheme(id){



	   var mid = id+'"';	
	   var html =  '<div class="col-md-6">'
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
	           +'<Button class = "pull-right btn btn-info">Add the selected scheme</Button>'
	       +'</div>'
	     +'</div>'
	 +'</div>' ;
	   
	   

	 	return html;		
}




