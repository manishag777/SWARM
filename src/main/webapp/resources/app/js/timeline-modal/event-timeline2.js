var fromDate, toDate;
var eventType3 = 'upcoming-event"';
var eventType2 = 'on-going-event"';
var eventType1 = 'past-event"';
var currentEventId = '';
var eventData;
var idEventDataMap;


var modelProductMap = new Object();

$(document).ready(function(){
	
	
	var date1 = "17-Feb-2015";
	var date2 = "20-dec-2015"; 
	
	
	
    $('#eventDate').daterangepicker({
    	//minDate: new Date()
    });
	
	
	$('#save-event').click(function(e){
		addEvent();
	});
	
	$('#add-event-button').click(function(e){
		$("#event-modal").modal('show');
	});
	
	
	createRangeCalender('#date-range');
	fetchSportList();
	
	$('#sport-type-filter').change(function() { 
		getFormattedDate();
		fetchEventList();
	});
	
	$('#store-type-filter2').change(function() { 
		fetchEventList();
	});
	
	$('#topCustomersTab').click(function(){
		$('#topCustomersTab').addClass("active");
		$('#topSellingProductsTab').removeClass("active");
		$('#top-product').css("display", "none");
		$('#top-customer').css("display", "block");
	});
	
	$('#topSellingProductsTab').click(function(){
		$('#topSellingProductsTab').addClass("active");
		$('#topCustomersTab').removeClass("active");
		$('#top-product').css("display", "block");
		$('#top-customer').css("display", "none");
	});
	
	

});

var createRangeCalender =  function(id) {

    function cb(start, end) {
    	console.info("start = ");
    	console.info(start);
        $(id+ ' span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
        fromDate = start.format('YYYY-MM-DD');
        toDate = end.format('YYYY-MM-DD');
        fetchEventList();
    }
    cb(moment().subtract(365, 'days'), moment().add(365,'days'));

    $(id).daterangepicker({
        ranges: {
           'Previous 1 month to next 1 month':   [moment().subtract(1, 'months'), moment().add(1, 'months')],
           'Previous 3 month to next 1 month' : [moment().subtract(3, 'months'), moment().add(1, 'months')],
           'Previous 3 month to next 3 month': [moment().subtract(3, 'months'), moment().add(3, 'months')],
           'Previous 6 month to next 1 month': [moment().subtract(6, 'months'), moment().add(1, 'months')],
           'Previous 6 month to next 3 month':  [moment().subtract(6, 'months'), moment().add(3, 'months')]
        }
    }, cb);

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
	        fetchEventList();
		},
		
		error : function(e) {
			alert ("sorry! Due to some problem couldn't  save the event");
		},
	}).done(function() {
		console.log("Done adding event");
		
		$('#event-modal').modal('hide');
	});

}

var createDiv = function(eventType, date, eventNumber, data, index){
	
	console.info("eventNumber = ");
	console.info(eventNumber);
	var article = '<article class="timeline-entry">'
		   + '<div class="timeline-entry-inner">'
		    + '<div class="timeline-icon '+eventType+' onClick = "openAnalysisModal('+eventNumber+','+eventData[index].id +')">'   
		    +  '<i class="entypo-camera"></i>'      
			+	'<div class = "date-div">'		 
			+	'<h class = "date">'+date+'</h>'		
			+	'</div>'	
		     +   '</div>'

		      +  '<div class="timeline-label">'
		      + getTimelineBox(data,eventNumber, index)
		        +'</div>'
		    +'</div> </article>' ;
	
	return article;
} 

var createEventAdder = function(){


	var curr = "'this'";
	var article = '<article class="timeline-entry begin">'	
	   + '<div class="timeline-entry-inner">'
	   + '<div class="timeline-icon" style="-webkit-transform: rotate(-90deg); -moz-transform: rotate(-90deg);" onClick = "createAddEvent()" >'   
       + '<i class="entypo-flight"></i> +'      
       + '</div>' 
       + '</div>'
       + '</article>'
     
       return article;

}

function createAddEvent(e){



	console.info("clicked");
	$("#event-modal").modal('show');
	var editor = '<label for="body">Event-detail</label>'
		+ '<textarea class="textarea form-control" id="textarea-id" name="eventDetail"  placeholder="Place some text here" style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>'
	document.getElementById('textarea-div').innerHTML = editor;
	$("#textarea-id").wysihtml5();
}

var fetchSportList = function(){

	var select1 = document.getElementById('sport-type-filter');
	var select2 = document.getElementById('dropdown_sport');
	$.ajax({
		url : 'fetchSportList',
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			sportData = data;
			console.info(data);
			for (var i in data) {
				var option = createOption(data[i].sportId,data[i].name);
				if(i==0)
					option.selected = 'selected';
				
				select1.appendChild(option);
				select2.appendChild(createOption(data[i].sportId,data[i].name));
			
					
			}
			fetchStoreList();
		},
	}).done(function() {
				
	});
}

var fetchStoreList = function(){



	var select1 = document.getElementById('store-type-filter2');
	//var select2 = document.getElementById('dropdown_store');
	$.ajax({
		url : 'fetchStoreList',
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			sportData = data;
			console.info(data);
			for (var i in data) {
				console.info(data[i].id);
				var option = createOption(data[i].id,data[i].name);
				if(i==0)
					option.selected = 'selected';
				select1.appendChild(option);
				//select2.appendChild(createOption(data[i].id,data[i].name));
			}
			
			fetchEventList();
		},
	}).done(function() {
				
	});
}

var fetchEventList = function(){





	var sport = $("#sport-type-filter").val();
	var today =  new Date();
	
	$(".timeline-centered").empty();
	
	if(!isRealValue(sport))
		return;
	
	
	$.ajax({
		url : 'fetchEventList',
		type : 'GET',
		contentType : "application/json",
		data : {sport:sport, fromDate:fromDate, toDate:toDate},
		success : function(data) {
			//fetchEventList();
			eventData = data;
			//console.info(data);
			eventDataIdMap = new Object();
			for(var i in data){
				var fromDate = data[i].fromDate;
				var toDate = data[i].toDate;
				var date = getStandardDate(toDate);
				$(".timeline-centered").append(createDiv(getEventType(data[i]), getFormattedDate(getStandardDate(data[i].fromDate)), getEventNumber(data[i]), data[i], i ));
				//addListenerEvent(data[i].id);
				setBreadCrumbStatusColor(data[i]);
				contactManagerRadioListener(data[i].id);
				meetingRadioListener(data[i].id)
				setDateTimePicker(data[i].id);
				assignTaskButtonListener(data[i].id)
				eventDataIdMap[data[i].id] = data[i];
				if(getEventNumber(data[i])==0){  //make all input read only
					makePastEventReadOnly("#div"+data[i].id);
				}
				acitvateSalesTab(data[i].id);
			}
		},
	}).done(function() {
		$(".timeline-centered").append(createEventAdder());
				
	});

}

function makePastEventReadOnly(divid){

	//$(divid).find('input,checkbox, button, textarea, select').attr('disbaled', 'disabled');
	$(divid).find('input,checkbox, button, textarea, select').attr('readonly', 'true');

}

var getEventType = function(data){
	var fromDate = getStandardDate(data.fromDate);
	var toDate = getStandardDate(data.toDate);
	var today = new Date();
	if(toDate<today)
		return eventType1;
	if(fromDate>today)
		return eventType3;
	return eventType2;
	
}

var getEventNumber = function(data){
	var fromDate = getStandardDate(data.fromDate);
	var toDate = getStandardDate(data.toDate);
	var today = new Date();
	if(toDate<today)
		return '0';                  //past Event
 	if(fromDate>today)
		return '1';                // future Event
	return '2';            // current event
	
}

var openAnalysisModal = function(eventNumber,id){

	if(eventNumber=='0')
		$("#analysis-modal").modal('show');
	else if(eventNumber=='1'){
		$("#future-analysis-modal").modal('show');
		
	}
	else $("#current-analysis-modal").modal('show');
	currentEventId = id;
}

var getTimelineBox = function(data,eventNumber, index){
   var timelineUnit =  '<div class = "row"><div class="col-md-12">'
    +'<div class="box box-default">'
    +'<div class="box-header with-border">'
    + getTimelineBody(data,eventNumber)
    +'<div class="box-footer no-padding">' 

    +getTimelineUnitFooter(data, eventNumber, index )     
       
    +'</div><!-- /.footer -->' 
    +'</div><!-- /.box -->'
    +'</div>'
    +'</div>';
    
    return timelineUnit;

}

var getTimelineBody = function(data,eventNumber){
	
	

	var body = '<h3 class="box-title">'+data.eventName+'</h3>'   
    +'</div><!-- /.box-header -->' 
    +'<div class="box-body">'
    +'<p style="margin-top:2px;"><b>Sport: </b><span>'+data.sportType +'<span></p>'
    +'<p style="margin-top:2px;"><b>Place of event: </b><span>'+data.placeEvent+'<span></p>'
    +'<p style="margin-top:2px; margin-bottom:10px;"><b>Event Co-ordintor: </b><span>'+data.coName+' &nbsp;&nbsp;&nbsp;&nbsp;<span><b>Phone no.</b><span id = "phoneId">'+data.coPhone+'&nbsp;&nbsp;'
    +'&nbsp;&nbsp;</span><b>Email-Id: </b><span >'+data.coEmail +'</span></p>'
    + getBreadCrumbPanel(data.id, data)
    +'</div><!-- /.box-body -->';
	
	
	return body;
}

var getTimelineUnitFooter = function(data, eventNumber, index){
	var id = data.id + '"';
	var res = '<div class="box-footer no-padding" id = "div'+id+'>'
			  +getContactManagerDiv(data.id)
			  +getMeetingDiv(data.id)
			  +getAssignTaskDiv(data.id)
			  +getEventStatusDiv(data.id)
			  +getEventAnalysisDiv(data.id)
			  + '</div>' ;
	return res;
}

var getPendingTaskBox = function(task, index){


	console.info(task);
	index = "'"+index+"'"
	
	var buttons = '';
	
	if(task[0]==0)
		buttons  = buttons + '<p class = "taskElement" onClick = "setProuctTarget('+index+')"  style="background-color:green; color:white; padding:2px; margin:1px; display:inline-block;"><small>Set Products Target</small></p>';
	
	if(task[1]==0)
		buttons = buttons + '<p class = "taskElement" onClick = "setCustomerTarget('+index+')" style="background-color:orange; color:white; padding:2px; margin:1px;margin-right:40px; display:inline-block;"><small>Set Customers Target</small></p>';
	
	if(task[2]==0)
		buttons = buttons + '<p class = "taskElement" onClick = "sendEmail('+index+')" style="background-color:#1a75ff; color:white; padding:2px; margin:1px; display:inline-block;"><small>Email Relevant customers</small></p>';
	
	if(buttons == '')
		buttons = 'No Task'
	
	var taskBox = '<div class="col-md-4">'
	+'<div class="box" style="min-height:90px;">'
	+'<div class="box-header ">'      
	+'<span class="info-box-text">Pending Tasks</span>'      
	+'<div>'        
	+buttons          
	+'</div>'        
	+'</div>'     
	+'</div>'   
	+'</div><!-- /.col -->' ;
	
	return taskBox;
}

var currentIndex; 
 
var getBreadCrumbPanel = function(eventId, data){
	var id1 = '"contactManager'+eventId+'"';
	var id2 = '"meeting'+eventId+'"';
	var id3 = '"assignTask'+eventId+'"';
	var id4 = '"eventStatus'+eventId+'"';
	var id5 = '"eventOuput'+eventId+'"';
	
    eventId = "'" + eventId + "'" ;
		
	var res = '<button type="button" id ='+ id1 +' onClick = "contactManager('+ eventId +')" class="btn btn-arrow-right contactManagerButton">Contact Manager</button>'
	     + '<button type="button" id ='+ id2 +' onClick = "meeting('+ eventId +')" class="btn btn-arrow-right meetingButton">Meeting</button>'
	    + '<button type="button" id ='+ id3 +' onClick = "assignTask('+ eventId +')" class="btn btn-arrow-right assignTaskButton">Assign Task</button>'
	    + '<button type="button" id ='+ id4 +' onClick = "eventStatus('+ eventId +')" class="btn btn-arrow-right eventStatusButton">Event Status</button>'
	    + '<button type="button" id ='+ id5 +' onClick = "eventOuput('+ eventId +')" class="btn btn-arrow-right eventOutputButton">Event Analysis</button>' ;
	
	return res;
	
}

function setBreadCrumbStatusColor(data){
	var eventId = data.id;
	var contactManagerId = "#contactManager"+eventId;
	var meetingId = "#meeting"+eventId;
	var assignTaskId = "#assignTask"+eventId;
	var eventStatusId = "#eventStatus"+eventId;
	var eventOuput = "#eventOuput"+eventId;
	checkTheContactRadioButton(eventId, data.cmStatus);
	fillTheDiv(eventId,data);
	if(data.cmStatus==0)
	{
		$(contactManagerId).addClass("btn-info");
	}
	else if(data.cmStatus==1)
	{
		// set meeting time paragraph
		checkTheMeetingRadioButton(eventId, data.mstatus);
		 mpid = "mpid"+eventId;
		 meetingHeaderId = "meetingHeader"+eventId ;
		 document.getElementById(mpid).innerHTML = "Meeting on " + data.cmTime ;
		 document.getElementById(meetingHeaderId).innerHTML = "Meeting on " +  data.cmTime ;
		 
		$(contactManagerId).addClass("btn-success");
		//console.info(data);
		if(data.mstatus==1){
			$(meetingId).addClass("btn-success");
			
			stallParaId = "stallPara" + eventId;
			feesParaId = "feesPara" + eventId;
			//document.getElementById(stallParaId).innerHTML = "Number of Stall is "+ data.stallNo;
			//document.getElementById(feesParaId).innerHTML = "Total fees Rs. "+ data.fees;
			$("#participationCount"+eventId).val(data.participantCount);
			$("#stallCount"+eventId).val(data.stallNo);
			$("#fees"+eventId).val(data.fees);
			setAssiginingTaskBody(data.stallNo, eventId);
			$("#taskDateTimePicker"+eventId+" input").val(data.trainingTime);
			fillProductRecommendationDataTable(eventId);
			getSchemeOfferList(eventId, "preEvent");
			getSchemeOfferList(eventId, "postEvent");
			updateEventAwarenessBox(eventId);

			if(data.taskStatus==0){
				$(assignTaskId).addClass("btn-info");
				//fillProductRecommendationDataTable();
			}
			else{
				$(assignTaskId).addClass("btn-success");
				if(data.eventStatus==0){ //Not started
					//$(eventStatusId).addClass("btn-info");
				}
				else if(data.eventStatus==-1){ //finished
					$(eventStatusId).addClass("btn-success");
					$(eventOuput).addClass("btn-info");
					
					
				}
				else{
					$(eventStatusId).addClass("btn-info");   // on going event
					
				}
			}
			
		}
		else if(data.mstatus==-1){
			$(meetingId).addClass("btn-danger");
		}
		else if(data.mstatus==0){
			$(meetingId).addClass("btn-info");
		}
	}
	else if(data.cmStatus == -1){
		$(contactManagerId).addClass("btn-danger");
	}
	
}

function fillTheDiv(eventId, data){



	var leadGeneratedId = "leadGeneratedId"+eventId;  
	var leadConvertedId = "leadConvertedId"+eventId;
	var inputCostId = "inputCostId"+eventId; 
	var revenueId = "revenueId"+eventId; 
	var profitId = "profitId"+eventId;
	var regCustCountId = "regCustCountId"+eventId; 
	var eventStatusHeaderId = "eventStatusHeaderId" + eventId;
	
	document.getElementById(leadGeneratedId).innerHTML = data.rcustomer ;
	document.getElementById(leadConvertedId).innerHTML = data.acustomer ;
	document.getElementById(inputCostId).innerHTML = (data.profit * 0.40).toFixed(0);
	document.getElementById(revenueId).innerHTML = data.revenue;
	document.getElementById(profitId).innerHTML = data.profit;
	document.getElementById(regCustCountId).innerHTML = data.rcustomer;
	
	var eventStatusHeaderTitle = "";
	if(data.eventStatus==-1) eventStatusHeaderTitle = "Event has Finished" ;     // finished
	if(data.eventStatus==1)  eventStatusHeaderTitle = "Event is going on";
	if(data.eventStatus==0)  eventStatusHeaderTitle = "Event has not started";
	document.getElementById(eventStatusHeaderId).innerHTML = eventStatusHeaderTitle;


}

function setDateTimePicker(eventId){

	console.info("at dateTimePicker" + eventId );
	var id = "#taskDateTimePicker"+eventId;
	$(id).datetimepicker();
}

