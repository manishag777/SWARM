var fromDate, toDate;
var eventType3 = 'upcoming-event"';
var eventType2 = 'on-going-event"';
var eventType1 = 'past-event"';
var currentEventId = '';

$(document).ready(function(){
	
	
	var date1 = "17-Feb-2015";
	var date2 = "20-dec-2015"; 
//	$('#customer-product').css("display", "none");
	
	
	
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
	if (typeof(formData.sportId) === 'string') {
		//console.info(formData.sportId);
		formData.sportId = [formData.sportId];
	}
	if (typeof(formData.storeId) === 'string') {
		//console.info(formData.storeId);
		formData.storeId = [formData.storeId];
	}
	
	console.info(formData);
	$.ajax({
		url : 'addEvent',
		data : JSON.stringify(formData),
		type : 'POST',
		contentType : "application/json",
		success : function(data) {
			console.log("Done saving event");
			
		},
		
		error : function(e) {
			alert ("sorry! Due to some problem couldn't  save the event");
		},
	}).done(function() {
		console.log("Done adding event");
		$('#event-modal').modal('hide');
	});

}

var createDiv = function(eventType, date, title, eventInfo, eventNumber, eventId){


	console.info("eventNumber = ");
	console.info(eventNumber);
	var article = '<article class="timeline-entry">'
		
		   + '<div class="timeline-entry-inner">'

		    + '<div class="timeline-icon '+eventType+' onClick = "openAnalysisModal('+eventNumber+','+eventId +')">'   
		    +  '<i class="entypo-camera"></i>'      
			+	'<div class = "date-div">'		 
			+	'<h class = "date">'+date+'</h>'		
			+	'</div>'	
		     +   '</div>'

		      +  '<div class="timeline-label">'
		       +  '<h2>'+ title+ '</h2>'   

		        + eventInfo
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
	var select2 = document.getElementById('dropdown_store');
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
				select2.appendChild(createOption(data[i].id,data[i].name));
			}
			
			fetchEventList();
		},
	}).done(function() {
				
	});
}

var fetchEventList = function(){

	var sport = $("#sport-type-filter").val();
	var store = $('#store-type-filter2').val();
	//console.info(store+" "+sport +" "+ startDate+ " "+endDate);
	var today =  new Date();
	//console.info(todayDate);
	
	$(".timeline-centered").empty();
	
	if(!isRealValue(sport) || !isRealValue(store))
		return;
	
	
	$.ajax({
		url : 'fetchEventList',
		type : 'GET',
		contentType : "application/json",
		data : {sport:sport, store:store, fromDate:fromDate, toDate:toDate},
		success : function(data) {
			//fetchEventList();
			console.info(data);
			for(var i in data){
				var fromDate = data[i].fromDate;
				var toDate = data[i].toDate;
				var date = getStandardDate(toDate);
				$(".timeline-centered").append(createDiv(getEventType(data[i]), getFormattedDate(getStandardDate(data[i].fromDate)), data[i].eventName, data[i].eventDetail, getEventNumber(data[i]),data[i].id ));
				
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
	console.info("openAnalysisModal = " + eventNumber);
	if(eventNumber=='0')
		$("#analysis-modal").modal('show');
	else if(eventNumber=='1'){
		$("#future-analysis-modal").modal('show');
		
	}
	else $("#current-analysis-modal").modal('show');
	currentEventId = id;
	//fetchDataOfTopProducts();
	//fetchDataOfTopCustomers();
	
}


