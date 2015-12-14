

var  getMeetingDiv = function(id){
	
	var divId = '"meetingDiv' + id+'" '
	
	var html = '<div class = "col-md-12 contactManager" id = '+divId+' style = "display:none;">'
      + '<div class="col-md-8">'
      +'<div class="box box-success">' 
       +  getMeetingHeader(id)
       + getMeetingBody(id)
       + getMeetingFooter(id)
       + '</div><!-- /.box -->' 
      + '</div><!-- /.col -->'
    +'</div>' ;
	
	return html;
	
}

function getMeetingHeader(id){

	var meetingHeaderId = '"meetingHeader' + id + '"' ; 
	id = "'"+ id +"'";
  var html = '<div class="box-header with-border">'
    +'<h3 class="box-title" id ='+meetingHeaderId+'>Meeting on 5 pm tomorrow</h3>'
    
 +'</div><!-- /.box-header -->'  ;
  
  return html;
	
	
}

function getMeetingBody(id){



	var mid = id+'"';
	var mid2 = "'"+id+"'" ;
	var name = '"meetingRadio' + id+'" ';
	
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
		+'<button type="button" onClick = "saveMeetingParam('+mid2+')" class="btn btn-info">Save</button>'
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

var eventId;

function meetingRadioListener(id){
	
	//console.info("eventId" + eventId);

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
        	//$("#set-fees").modal("show");
        	$("#meetingFooter"+id).attr('style',' width: 100%; display:show;');
        	$("#meetingParamDiv"+id).attr('style','width: 100%; display:show;');
        	
        }
        if(this.value==-1)
        	$("#meeting" + id).addClass("btn-danger");
		
        updateMStatus(this.value, id);
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
			updateEventAwarenessBox(id);
		},
	}).done(function() {
		//$("#set-fees").modal("hide");
	});
	
	
}

