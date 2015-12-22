var idDataMap = new Object();


$(document).ready(function () {
	
	var url = 'fetchEventStarterList';
	
			$.ajax({
				url : url,
				type : 'GET',
				contentType : "application/json",
				success : function(data) {
					console.info(data);
					fillEventTable(data);
				},
				error: function(){
					console.info("error");
				}
			}).done(function() {
				console.log("Done adding");
			});
			fetchSportList();
			$('#save-event').click(function(e){
				addEvent();
			});
	
	
});


function fillEventTable(data){
	
	for(var i=0; i<data.length; i++){
		idDataMap[data[i].id] = data[i];
		var table = document.getElementById("eventTable");
		var row = table.insertRow(-1);
		row.innerHTML = getRow(data[i]);
		addToolTipEvent(data[i]);
	}
	

	
}

function getRow(data){
	var id = "'"+data.id+"'";
	console.info(id);
	var mid = data.id+'"';
	
	var html = '<td id = "toolTip'+mid+'><a>'+data.eventName+'</a></td>';
	var tag = "";
	
	//html = html + '<td>'+'<b>'+data.relevanceFactor+'</b>&nbsp;&nbsp;'+getRelevanceTag(data.relevanceFactor)+'</td>' ;
	html = html + '<td>'+getRelevanceTag(data.relevanceFactor)+'</td>' ;

	if(data.isSeen==0)
		html+='<td><span class="label label-danger">New</span></td>';
	else
		html+='<td><span class="label label-success">Seen</span></td>';
	if(data.isAdded == 0)
		html+='<td><Button onClick = "openEventAdder('+id+')">Add this event</Button></td>';
	else
		html+='<td><Button>View Status</Button></td>';
	console.info(html);
	return html;
}

function addToolTipEvent(data){
	
	$('#toolTip'+data.id).tooltipster({
        content: $(getToolTipBody(data))
  });
}


function getToolTipHeader(data){
	console.info(data.eventName);
	return "Manish Agrawal";
}

function getToolTipBody(data){
	//return "body";
	console.info(data);
	var html = '<p style="margin-top:2px;"><b>Event Date: </b><span>'+data.fromDate +'<span></p>'
	+'<p style="margin-top:2px;"><b>Sport: </b><span>'+data.sportType +'<span></p>'
    +'<p style="margin-top:2px;"><b>Place of event: </b><span>'+data.placeEvent+'<span></p>'
    +'<p style="margin-top:2px; margin-bottom:10px;"><b>Event Co-ordintor: </b><span>'+data.coName+'<br/><span><b>Phone no.</b><span id = "phoneId">'+data.coPhone
    +'<br/></span><b>Email-Id: </b><span >'+data.coEmail +'</span></p>';
	
	return html;
}



function getRelevanceTag(relevanceFactor){
	if(relevanceFactor > 6){
		return '<span class="label label-danger">High<span>';
	}
	else if(relevanceFactor > 4 ){
		return '<span class="label label-info">Medium<span>';
	}
	else{
		return '<span class="label label-success">Low<span>';
	}
}

function openEventAdder(eventId){

	console.info(eventId);
	var data = idDataMap[eventId];
	$("#event-modal").modal("show");
	$("#eventName").val(data.eventName);
	$("#coName").val(data.coName);
	$("#coEmail").val(data.coEmail);
	$("#coPhone").val(data.coPhone);
	$("#eventDate").val(data.eventDate);
	$("#placeEvent").val(data.placeEvent);
	$("#sportType").val(data.sportType);
	
	var editor = '<label for="body">Event-detail</label>'
		+ '<textarea class="textarea form-control" id="textarea-id" name="eventDetail"  placeholder="Place some text here" style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>'
	document.getElementById('textarea-div').innerHTML = editor;
	$("#textarea-id").val(data.detail);
	$("#textarea-id").wysihtml5();
	
	//$("#eventData").val(data.eventName);	
}

var fetchSportList = function(){


//	//var select2 = document.getElementById('dropdown_sport');
//	$.ajax({
//		url : 'fetchSportList',
//		type : 'GET',
//		contentType : "application/json",
//		success : function(data) {
//			sportData = data;
//			console.info(data);
//			for (var i in data) {
//				var option = createOption(data[i].sportId,data[i].name);
//				if(i==0)
//					option.selected = 'selected';
//				
//				//select2.appendChild(createOption(data[i].sportId,data[i].name));
//			
//					
//			}
//		},
//	}).done(function() {
//				
//	});
}

var addEvent = function(){

	var formData = $('#template-form').serializeObject();
	delete formData._wysihtml5_mode;	
	console.info(formData);
	$.ajax({
		url : 'addEvent',
		data : JSON.stringify(formData),
		type : 'POST',
		contentType : "application/json",
		success : function(data) {
			console.log("Done saving event");
	        //fetchEventList();
		},
		
		error : function(e) {
			alert ("sorry! Due to some problem couldn't  save the event");
		},
	}).done(function() {
		console.log("Done adding event");
		$('#event-modal').modal('hide');
		window.location.href = "manageEvent";
	});

}

