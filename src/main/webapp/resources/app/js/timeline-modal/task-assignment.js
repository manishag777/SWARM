
var  getAssignTaskDiv = function(id){
	
	console.info("At getAssignTaskDiv");
	var divId = '"assignTaskDiv' + id+'" '
	
	var html = '<div class = "col-md-12" id = '+divId+' style = "display:none;">'
      + '<div class="col-md-6">'
      +'<div class="box box-success">' 
       +  getAssignTaskHeader(id)
       +  getAssignTaskBody(id)
       + '</div><!-- /.box -->' 
      + '</div><!-- /.col -->'
    +'</div>' ;
	
	return html;
	
}

function getAssignTaskHeader(id){
	
  id = "'"+ id +"'";
  var html = '<div class="box-header with-border">'
    +'<h3 class="box-title">Task Assignment</h3>'    
 +'</div><!-- /.box-header -->'  ;
  
  return html;	
}

function getAssignTaskBody(id){

	
	var taskDateTimePickerId = '"taskDateTimePicker'+ id + '"';
	var assignTaskButton = '"assignTaskButton'+ id + '"';
	id = id + '"';
	var stallCount = $("#stallNo").val();
		
		var html = '<div class="box-body">'
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
		   +'</div><!-- /.box-body -->'
		+'</div><!-- /.box -->' ;

	console.info(html);
   
			return html;

		
		
	}
	
	

function setAssiginingTaskBody(stallCount, id){
	console.info(id);
	var eventId = id;
	id = id + '"';
	var html;
	if(stallCount==1){
		var html = '<div class="funkyradio-primary">'
        + '<input type="checkbox" name="user" id="checkbox2'+id+' value = "mukesh" checked />' 
         + '<label for="checkbox2'+id+'>Mukesh Agrawal (Role - Handle Customer)</label>'
      +'</div>'  
      +'<div class="funkyradio-primary">'
        + '<input type="checkbox" name="user" id="checkbox5'+id+' value = "piyush" checked />' 
         + '<label for="checkbox5'+id+'>Piyush Dane (Role - Register Customer)</label>'
      +'</div>'
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
	      +'</div>'
	}
	
	console.info(html);
	
	document.getElementById("funkyradio"+eventId).innerHTML = html;
	
	
	
	
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

