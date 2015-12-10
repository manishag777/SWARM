
var  getContactManagerDiv = function(id){
	
	
	var divId = '"contactManagerDiv' + id+'" '
	
	var html = '<div class = "col-md-12 contactManager" id = '+divId+' style = "display:inline-block;">'
      + '<div class="col-md-8">'
      +'<div class="box box-success">' 
       +  getContactManagerHeader(id)
       + getContactManagerBody(id)
       + getContactManagerFooter(id)
       + '</div><!-- /.box -->' 
      + '</div><!-- /.col -->'
    +'</div>' ;
	
	return html;
	
}

function getContactManagerHeader(id){
	
	id = "'"+ id +"'";
  var html = '<div class="box-header with-border">'
    +'<h3 class="box-title">Contact events co-ordinator</h3>'
    +'<div class="box-tools pull-right">'
     +'<table style = "border-spacing: 10px 0px; border-collapse:separate;  ">' 
      +'<tr>'  
       +' <td ><button onClick = "callOnThisNumber('+ id +')" class="btn btn-block btn-primary">call&nbsp;&nbsp;<i class="fa  fa-phone"></i></button> </td>'
      +'<td style="margin-left:3px;"><button onClick = "sendMail('+ id +')" class="btn btn-block btn-warning">mail &nbsp;&nbsp;<i class="fa  fa-envelope-o"></i></button></td>'
    +'</tr>'
    +'</table>'
 +'</div><!-- /.box-tools -->'
 +'</div><!-- /.box-header -->'  ;
  
  return html;
	
	
}

function getContactManagerBody(id){

	
	var name = '"contactRadio' + id+'" ';
	
	var html = '<div class="box-body">' 
    +'<h4 style = "margin-top:1px"> Meeting status</h4>'
    +'<div class="form-group">'
    	+'<label style = "margin"> '
        +'<input type="radio" name='+name+ '  class="flat-red" value = "0"/>'
        +'No call yet &emsp;'
      +'</label>'
      +'<label>'
       +'<input type="radio" name='+name+ 'class="flat-red" value = "1"/>' 
        +'Ready for Meeting &emsp;' 
      +'</label>'
      +'<label> '
       +' <input type="radio" name='+name+ 'class="flat-red" value = "-1" />'
        +'No approval &emsp;'
      +'</label>'
  +'</div>'
   +'</div><!-- /.box-body -->'   ;

	return html;
	
}

function getContactManagerFooter(id){


	var mpid = '"mpid' + id + '"'; 
	var rescheduleid = '"reschedule' + id + '"' ;
	id = "'"+ id +"'";
	var html = '<div class = "box-footer">'
    +'<table style = "width:100%" id ='+rescheduleid+'  >'
     +'<tr>' 
      +'<td style = "width:60%"><p id ='+ mpid +'>Meeting time not decided</p></td>'
    +'<td style = "width:60%"><button class="btn btn-block btn-primary pull-right" onClick = "meetingReschedule('+id+')" style = "width:52%">Reschedule &nbsp;&nbsp;<i class="fa  fa-calendar-times-o"></i></button></td>'
  +'</tr>'
  +'</table>'
+'</div>' ;
	
	console.info(html);
	return html;
}

function contactManagerRadioListener(id){

	var name = "contactRadio" +  id;
	var input = "input[type=radio][name="+name+"]";	
	$(document).on('change', input ,function(){
		
		console.info(name);
		removeAllClass("#contactManager" + id);
		
        if(this.value==0)
        	$("#contactManager" + id).addClass("btn-info");
        if(this.value==1){
        	$("#contactManager" + id).addClass("btn-success");
        	$("#meeting" + id).addClass("btn-info");
        	$("#set-timeModal").modal("show");
        	
        }
        if(this.value==-1)
        	$("#contactManager" + id).addClass("btn-danger");
        
        updateCmStatus(this.value, id);
		
	});
}

function checkTheContactRadioButton(id, value){
	
	var name = "contactRadio" +  id;
	var input = "input[type=radio][name="+name+"]";
	if(value == 0){
		$(input)[0].checked = true;
		//$("#reschedule" + id).css("display", "none");
	}
	else if(value == 1){
		$(input)[1].checked = true;
		//$("#reschedule"+id).css("display", "show");
		
	}
	else if(value == -1){
		$(input)[2].checked = true;
		//$("#reschedule" +id).css("display", "none");
	}
}

var currentEventId;
function updateCmStatus(value, eventId){
	currentEventId = eventId;
	console.info(value);
	
//	if(value==1)
//		$("#reschedule"+id).css("display", "show");
//	else
//		$("#reschedule" + id).css("display", "none");
		
	$.ajax({
		url : 'updateCmStatus',
		type : 'GET',
		data : {value : value, eventId : eventId},
		contentType : "application/json",
		success : function() {
			
		},
	}).done(function() {
				
	});
}

function saveMeetingTime(){

	console.info("saveMeetingTime" + " "+ $("#meetingDateTime").val());
		

	$.ajax({
		url : 'upDateMeetingTime',
		type : 'GET',
		data : {dateTime : $("#meetingDateTime").val(), eventId : currentEventId},
		contentType : "application/json",
		success : function() {
			 mpid = "mpid"+currentEventId;
			// rescheduleid = "reschedule"+currentEventId; 
			 document.getElementById(mpid).innerHTML = "Meeting at " +  $("#meetingDateTime").val();
			 
		},
	}).done(function() {
	});
}


function meetingReschedule(id){
	currentEventId = id;
	console.info(id);
	$("#set-timeModal").modal("show");
}


function callOnThisNumber(id){
	$("#callOnThisNumberModal").modal("show");
}

function sendMail(id){
	$("#send-mail-Modal").modal("show");
	var editor = '<label for="body">Body</label>'
		+ '<textarea class="textarea form-control" id="mailSubject" name="mailSubject"  placeholder="Place some text here" style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>'
	document.getElementById('SubjectArea-div').innerHTML = editor;
	$("#mailSubject").val("<p>"

		+	"<p>Dear Customer,</p><p>The Product is available in your nearest SportsWARM store. Hurry Up! The Stock is limited. Avail the discount on refering new Customers.</p><p>Thanks</p><p>Manish Agrawal</p><p>Sales Manager </p><p>SportsWarm </p><p></p>"

		+	"<br></p>");
	$("#mailSubject").wysihtml5();
}


