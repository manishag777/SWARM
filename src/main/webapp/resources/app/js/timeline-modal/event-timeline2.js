var fromDate, toDate;
var eventType3 = 'upcoming-event"';
var eventType2 = 'on-going-event"';
var eventType1 = 'past-event"';
var currentEventId = '';
var eventData;


var modelProductMap = new Object();

$(document).ready(function(){
	
	
	var date1 = "17-Feb-2015";
	var date2 = "20-dec-2015"; 
	
	
	
    $('#reservation').daterangepicker({
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
			console.info(data);
			for(var i in data){
				var fromDate = data[i].fromDate;
				var toDate = data[i].toDate;
				var date = getStandardDate(toDate);
				$(".timeline-centered").append(createDiv(getEventType(data[i]), getFormattedDate(getStandardDate(data[i].fromDate)), getEventNumber(data[i]), data[i], i ));
				//addListenerEvent(data[i].id);
				contactManagerRadioListener(data[i].id);
				meetingRadioListener(data[i].id)
			}
		},
	}).done(function() {
		$(".timeline-centered").append(createEventAdder());
				
	});

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
    + data.eventDetail
    +' <p><b> Location of event: </b>'+data.address+'</p>'  
    +'<p><b>Number of particpant: </b>'+ data.participantCount +'</p>'   
    +'<p><b>Sport Type : </b>'+ data.sportType +'</p>' 
    + getBreadCrumbPanel(data.id)
    +'</div><!-- /.box-body -->';
	
	
	return body;
}

var getTimelineUnitFooter = function(data, eventNumber, index){
	
	var res = '<div class="box-footer no-padding">'
			  +getContactManagerDiv(data.id)
			  +getMeetingDiv(data.id)
			  +getAssignTaskDiv(data.id)
			  +getEventStatusDiv(data.id)
			  +getEventOutputDiv(data.id)
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
 
var getBreadCrumbPanel = function(eventId){



	var id1 = '"contactManager'+eventId+'"';
	var id2 = '"meeting'+eventId+'"';
	var id3 = '"assignTask'+eventId+'"';
	var id4 = '"eventStatus'+eventId+'"';
	var id5 = '"eventOuput'+eventId+'"';
	
    eventId = "'" + eventId + "'" ;
	
	
	
	var res = '<button type="button" id ='+ id1 +' onClick = "contactManager('+ eventId +')" class="btn btn-info btn-arrow-right contactManagerButton">Contact Manager</button>'
	     + '<button type="button" id ='+ id2 +' onClick = "meeting('+ eventId +')" class="btn btn-warning btn-arrow-right meeting meetingButton">Meeting</button>'
	    + '<button type="button" id ='+ id3 +' onClick = "assignTask('+ eventId +')" class="btn btn-danger btn-arrow-right assignTaskButton">Assign Task</button>'
	    + '<button type="button" id ='+ id4 +' onClick = "eventStatus('+ eventId +')" class="btn btn-success btn-arrow-right eventStatusButton">Event Status</button>'
	    + '<button type="button" id ='+ id5 +' onClick = "eventOuput('+ eventId +')" class="btn btn-default btn-arrow-right eventOutputButton">Event Output</button>' ;
	return res;
	
}

function getAssignTaskDiv(id){

	var divId = '"assignTaskDiv' + id+'" ';
	var res = '<div class = "col-md-12 " id = '+divId+' style = "display:none;">'
    +'<h> Assign Task</h>'
    +'</div>' ;
	return res;
	
}

function getMeetingDiv2(id){

	var divId = '"meetingDiv' + id+'" ';
	var res = '<div class = "col-md-12 " id = '+divId+' style = "display:none;">'
    +'<h> Meeting</h>'
    +'</div>' ;	
	
	return res;
}

function getEventStatusDiv2(id){

	var divId = '"eventStatusDiv' + id+'" ';
	var res = '<div class = "col-md-12 " id = '+divId+' style = "display:none;">'
    +'<h>Event Status</h>'
    +'</div>' ;	
	
	return res;
}

function getEventOutputDiv(id){

	var divId = '"eventOutputDiv' + id+'" ';
	var res = '<div class = "col-md-12 " id = '+divId+' style = "display:none;">'
    +'<h> Event OutPut</h>'
    +'</div>' ;	
	
	return res;
}



