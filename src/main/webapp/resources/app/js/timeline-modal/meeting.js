var eventId;


var getMeetingDiv = function(id){

	var divId = '"meetingDiv' + id+'" ' ;
	var mid = id + '"';
    var html = '<div class = "col-md-12" id = '+divId+' style = "display:none;">'
	    +'<div class="nav-tabs-custom">'
	    +'<!-- Tabs within a box -->'
	    +'<ul class="nav nav-tabs">'
	      +'<li class="active"><a data-toggle="tab" id = "beforeMeetingTab'+mid+'>Before Meeting</a></li>'
	      +'<li><a data-toggle="tab" id = "afterMeetingTab'+mid+'>After Meeting</a></li>'
	      +'<li class="pull-right header"><i class="fa fa-calendar-minus-o"></i><span id = "meetingHeader'+mid+'> Sales strategy</li>'
	    +'</ul>'
	    +'<div class=" row tab-content no-padding">'
		    +'<div class = "col-md-8"  id = "beforeMeetingDiv'+mid+'>'
		        +'<div class ="row">'
		        	+createProposalDiv(id)
		        +'</div>'
		    +'</div>'
	         +'<div class = "col-md-8" style="display:none" id = "afterMeetingDiv'+mid+'>'
		         +'<div class ="row">'
		         	+ createAfterMeetingDiv(id)
		        	+ getMeetingFooter(id)
		         +'</div>'
	         +'</div>'
	      +'</div>'
	    +'</div><!-- /.nav-tabs-custom -->'
	+'</div>';
    
    return html;
	
}

var createAfterMeetingDiv = function(id){
	
	var mid = id + '"';
	var html = '<div class="box box-default">'
				    +'<div class="box-header">'
					    +'<i class="fa fa-save"></i>'
					    +'<h3 class="box-title">Update meeting detail</h3>'
					 +'</div><!-- /.box-header -->'
					 + getMeetingBody(id)
					+'</div><!-- /.box -->';
	
		return html;
}



var acitvateMeetingTab = function(id){
	console.info("acitvateMeetingTab");
	
	$("#beforeMeetingTab"+id).click(function(e){
		$("#afterMeetingDiv"+id).css("display", "none");
		$("#beforeMeetingDiv"+id).attr('style',' width: 100%; display:show;');
	});
	
	
	$("#afterMeetingTab"+id).click(function(e){
		$("#beforeMeetingDiv"+id).css("display", "none");
		$("#afterMeetingDiv"+id).attr('style',' width: 100%; display:show;');
	});

}

var createProposalDiv = function(id){
	
	var mid = id + '"';
	var html = '<div class="box box-default">'
				    +'<div class="box-header">'
					    +'<i class="ion ion-clipboard"></i>'
					    +'<h3 class="box-title">Create proposal</h3>'
					 +'</div><!-- /.box-header -->'
					  +'<div class="box-body">'
					    +'<ul class="todo-list" id = "todo-list'+mid+'>'
							+addListOfProposalItem()
					    +'</ul>'
					  +'</div>'
					  +'<div class="box-footer clearfix no-border">'
					    +'<button class="btn btn-default pull-right" id = "addProposalTask'+mid+'><i class="fa fa-plus"></i> Add item</button>'
					  +'</div>'
					+'</div><!-- /.box -->';
	
		return html;
}

var addListOfProposalItem = function(id){
	
	var task = ["Discuss number of particpants", "Request for stalls installation", "Discuss stall installation fees", "Request for pamphlet distribution with goodies"];
	
	var html="";
	
	for(var i=0; i<task.length; i++){
		html+=getLiOfProposalItem(task[i]);
	}

	return html;	
}

var getLiOfProposalItem = function(item){
	
	var html = '<li>'
      +'<span class="handle">'
        +'<i class="fa fa-ellipsis-v"></i>'
        +'<i class="fa fa-ellipsis-v"></i>'
      +'</span>'
      +'<span class="text">'+item+'</span>'
      +'<div class="tools">'
        +'<i class="fa fa-trash-o deleteLI" ></i>'
      +'</div>'
    +'</li>';
	
	return html;
	
}

function addProposalTask(){
	var item = $("#proposalTask").val();
	console.info(item);
	var html = getLiOfProposalItem(item);
	console.info("eventId"+ eventId);
	$( "#todo-list"+eventId);
	$( "#todo-list"+eventId).append(html);
	addDeleteLIListener();
}





function getMeetingBody(id){

	var mid = id+'"';
	var mid2 = "'"+id+"'" ;
	var name = '"meetingRadio' + id+'" ';
	var pamphletName = '"pamphletName' + id+'" ';
	var html = '<div class="box-body">' 
    +'<h4 style = "margin-top:1px"> Proposal status</h4>'
    +'<div class="form-group">'
    	+'<label style = "margin"> '
        +'<input type="radio" name='+name+ 'class="flat-red" value = "0"/>'
        +' Meeting yet to be held&emsp;'
      +'</label>'
      +'<label>'
       +'<input type="radio" name='+name+ 'class="flat-red" value = "1"/>' 
        +'Proposal Accepted &emsp;' 
      +'</label>'
      +'<label> '
       +' <input type="radio" name='+name+ 'class="flat-red" value = "-1" />'
        +'Proposal denied &emsp;'
      +'</label>'
    +'</div>'
    +'<div id = "meetingParamDiv'+mid+'>'
	    +'<hr/>'
		+'<div class="input-group" style = "margin:4px;">'
		    +'<div class="input-group-btn">'
		      +'<h5><b>Number of participant</b></h5>'
		      +'</div><!-- /btn-group -->'
		      +'<input type="number" id = "participationCount'+mid+' class="form-control pull-right" style = "width:85px; margin-right:300px;">'
		+'</div><!-- /input-group -->'
		+'<div class="input-group" style = "margin:4px;">'
		    +'<div class="input-group-btn">'
		    +'<h5><b>Number of stall</b></h5>'
		    +'</div><!-- /btn-group -->'
		    +'<input type="text" class="form-control pull-right" id = "stallCount'+mid+' style = "width:85px; margin-right:300px;">'
		+'</div><!-- /input-group -->'
		+'<div class="input-group" style = "margin:4px;">'
		    +'<div class="input-group-btn">'
		    +'<h5><b>Fees paid</b></h5>'
		    +'</div><!-- /btn-group -->'
		    +'<input type="text" class="form-control pull-right" id = "fees'+mid+' style = "width:85px; margin-right:300px;">'
		+'</div><!-- /input-group -->'
		
		+'<div class="input-group" style = "margin:4px;">'
		    +'<div class="input-group-btn">'
		    +'<h5><b>Phamplet distribution</b></h5>'
		    +'</div><!-- /btn-group -->'
		    +'<div class = "pull-right" style = "margin-right:320px; margin-top:5px;">'
			    +'<label>'
			    	+'<input type="radio" name='+pamphletName+ 'class="flat-red" value = "1"/>'
			    	+'Yes'
			    +'</label>'
			    +'<label> '
		    		+'<input type="radio" name='+pamphletName+ 'class="flat-red" value = "0" style = "margin-left:4px;"/>'
		    		+ 'NO'
		    	+'</label>'
	    	+'</div>'
	  	+'</div><!-- /input-group -->'
	  	+ '<label for="body">Other details</label>'
		+ '<textarea class="textarea form-control" id="textarea-id" name="bodyText"  placeholder="Place some text here" style="width: 73%; height: 100px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>'

		
		
		+'<button type="button" onClick = "saveMeetingParam('+mid2+')" class="btn btn-info" style="margin-top:10px;">Save</button>'
	   +'</div><!-- /.box-body -->' 
	+'</div>';

	return html;
	
}




function getMeetingFooter(id){
	
	var stallParaId = '"stallPara'+id+'"' ;
	var feesParaId = '"feesPara'+id+'"' ;
	var meetingFooterId = '"meetingFooter'+id+'"';
	id = "'"+ id +"'";

	var html = '<div class = "box-footer">'
    +'<table style = "width:100%" id ='+ meetingFooterId +'>'
     +'<tr>' 
      +'<td style = "width:60%"><p id = '+ stallParaId +'></p></td>'
     +'</tr>'
     +'<tr>' 
  		+'<td style = "width:60%"><p id = '+ feesParaId +'><p></td>'
  	 +'</tr>'

  +'</table>'
+'</div>' ;
	
	//console.info(html);
	return html;
}


function meetingRadioListener(id){

	var name = "meetingRadio" +  id;
	var input = "input[type=radio][name="+name+"]";	
	$(document).on('change', input ,function(){
		eventId = id;
		console.info(this.value);
		removeAllClass("#meeting" + id);
		$("#meetingFooter"+id).attr('style',' display:none;');
		$("#meetingParamDiv"+id).attr('style',' display:none;');
        if(this.value==0)
        	$("#meeting" + id).addClass("btn-info");
        if(this.value==1){
        	$("#meeting" + id).addClass("btn-success");
        	$("#meetingParamDiv"+id).attr('style','width: 100%; display:show;');
        	//var assignTaskId = "#assignTask"+eventId;
        	$("#assignTask"+id).addClass("btn-info");
        }
        if(this.value==-1)
        	$("#meeting" + id).addClass("btn-danger");
		
        updateMStatus(this.value, id);
        
        //Add task Listener
       
        
	});
}

function checkTheMeetingRadioButton(id, value){

	var name = "meetingRadio" +  id;
	var input = "input[type=radio][name="+name+"]";
	$("#meetingFooter"+id).attr('style',' display:none;');
	$("#meetingParamDiv"+id).attr('style',' display:none;');
	
	if(value == 0){
		$(input)[0].checked = true;
		
	}
	else if(value == 1){
		$(input)[1].checked = true;
		$("#meetingFooter"+id).attr('style',' width: 100%; display:show;');
		$("#meetingParamDiv"+id).attr('style','width: 100%; display:show;');
		
	}
	else if(value == -1){
		$(input)[2].checked = true;
	}
	
	 console.info("addProposalTaskBefore");
     $("#addProposalTask"+id).click(function(){
     	console.info("addProposalTask");
     	$("#addTaskModal").modal("show");
     	 eventId = id;
     })
     
     $("#addCommentTask"+id).click(function(){
     	console.info("addCommentTask");
     	$("#addCommentModal").modal("show");
     	 eventId = id;
     })
     
     addDeleteLIListener();
     
	
}

function addDeleteLIListener(){
	
	$(".deleteLI").click(function(){
   	 console.info("at deleteLI");
   	 $(this).parent().parent().remove();
    });
}

function updateMStatus(value, id){
	$.ajax({
		url : 'updateMStatus',
		type : 'GET',
		data : {value : value, eventId : id},
		contentType : "application/json",
		success : function() {
			
		},
	}).done(function() {
				
	});
}

function saveFeesAndStallCount(){


	removeAllClass("#assignTask"+eventId);
	$("#assignTask"+eventId).addClass("btn-info");
	console.info("At saveFeesAndStallCount" + " "+ $("#stallNo").val());
	stallParaId = "stallPara" + eventId;
	feesParaId = "feesPara" + eventId;
	
	$("#meetingFooter"+eventId).attr('style',' width: 100%; display:show;');
	document.getElementById(stallParaId).innerHTML = "Number of Stall is "+ $("#stallNo").val();
	document.getElementById(feesParaId).innerHTML = "Total fees Rs. "+ $("#fees").val();
	$.ajax({
		url : 'saveFeesAndStallCount',
		type : 'GET',
		data : {fees : $("#fees").val(), eventId : eventId, stallCount : $("#stallNo").val()},
		contentType : "application/json",
		success : function() {
			
			
		},
	}).done(function() {
		$("#set-fees").modal("hide");
	});
}

function saveMeetingParam(id){
	
	var paticipationCountId = "#participationCount"+id;
	var stallCountId = "#stallCount"+id;
	var feesId = "#fees"+id;
	
	$.ajax({
		url : 'saveMeetingParam',
		type : 'GET',
		data : {fees : $(feesId).val(), eventId : id, stallCount : $(stallCountId).val(), participantCount : $(paticipationCountId).val()},
		contentType : "application/json",
		success : function() {
			swal("Saved", "", "success");
			console.info("success");
			fillProductRecommendationDataTable(id);
			getSchemeOfferList(id, "preEvent");
			getSchemeOfferList(eventId, "postEvent");
			updateEventAwarenessBox(id);
			setAssiginingTaskBody($(stallCountId).val(), id);
		},
	}).done(function() {
		//$("#set-fees").modal("hide");
	});
	
	
}

