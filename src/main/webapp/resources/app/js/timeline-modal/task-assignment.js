
var  getAssignTaskDiv = function(id){
	
	console.info("At getAssignTaskDiv");
	var divId = '"assignTaskDiv' + id+'" ' ;
	
	var html = '<div class = "col-md-12" id = '+divId+' style = "display:none;">'
      + '<div class="col-md-6">'
      +'<div class="box box-success">' 
       +  getAssignTaskHeader(id)
       +  getAssignTaskBody(id)
       + '</div><!-- /.box -->' 
      + '</div><!-- /.col -->'
    +'</div>' ;
	
	//return html;
	return getPostMeetingDiv(id);
	
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

		//console.info(html);
   
			return html;

		
		
	}
	
	




