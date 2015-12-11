
var  getAssignTaskDiv = function(id){
	
	console.info("At getAssignTaskDiv");
	var divId = '"assignTaskDiv' + id+'" '
	
	var html = '<div class = "col-md-12" id = '+divId+' style = "display:none;">'
      + '<div class="col-md-4">'
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
	var html = '<div class="box-body">'
			    +'<div class="form-group">'
			    	 + '<form id="task-assign-form'+id+'>'
			    	 + '<h4>Set Time For Training</h4>'
			         +'<div class="input-group date" id='+taskDateTimePickerId+'>'
			            + '<span class="input-group-addon">'
			                 +'<span class="glyphicon glyphicon-calendar"></span>'
			            +'</span>' 
			             +'<input type="text" name = "trainingTime" class="form-control" />'
			         +'</div>'
			        
			          +'<h4 style= "margin-bottom:0px;">Assign Task to</h4>'
			         +'<div class="funkyradio">'
			             +'<div class="funkyradio-primary">'
			               + '<input type="checkbox" name="user" id="checkbox2'+id+' value = "mukesh" checked = "1"/>' 
			                + '<label for="checkbox2'+id+'>Mukesh Agrawal</label>'
			             +'</div>'
			             +'<div class="funkyradio-primary">'
			                + '<input type="checkbox" name="user" id="checkbox3'+id+' value = "rishab" checked = "0" />'
			                + '<label for="checkbox3'+id+'>Rishab jain</label>'
			            +'</div>' 
			             +'<div class="funkyradio-primary">'
			              +  '<input type="checkbox" name="user" id="checkbox4'+id+' value = "mandeep"/>' 
			               + '<label for="checkbox4'+id+'>Mandeep Singh</label>' 
			            +'</div>' 
			             +'<div class="funkyradio-primary">'
			               + '<input type="checkbox" name="user" id="checkbox5'+id+' value = "piyush"/>' 
			                + '<label for="checkbox5'+id+'>Piyush Dane</label>'
			             +'</div>'
			             +'<div class="funkyradio-primary">'
			                + '<input type="checkbox" name="user" id="checkbox6'+id+' value = "prashant"/>'
			                + '<label for="checkbox6'+id+'>Prashant Singh</label>'
			             +'</div>'
			         +'</div>'
			         + '</form>'
			     +'</div>'
			     +'<button type="button" id='+assignTaskButton+' class="btn btn-info assignTaskButton">Assign Task</button>'
			   +'</div><!-- /.box-body -->'
			+'</div><!-- /.box -->' ;
	
/*	console.info(html);
*/   
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


