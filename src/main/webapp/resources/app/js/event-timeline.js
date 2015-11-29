
$(document).ready(function(){
	
	var eventType2 = 'upcoming-event"';
	var eventType1 = 'past-event"';
	
	var date1 = "17-Feb-2015";
	var date2 = "20-dec-2015"; 
	
	var title = "Sunfire Run 10K  New Delhi DL";
	
	var divInfo =  '<blockquote>Pianoforte principles our unaffected not for astonished travelling are particular.</blockquote>'  

        +  '<img src="http://themes.laborator.co/neon/assets/images/timeline-image-3.png" class="img-responsive img-rounded full-width"/>' ;
	
    $('#reservation').daterangepicker({
    	minDate: new Date()
    });
	$(".timeline-centered").append(createDiv(eventType1,date1,title,divInfo));
	$(".timeline-centered").append(createDiv(eventType2,date1,title,divInfo));
	$(".timeline-centered").append(createDiv(eventType2,date1,title,divInfo));
	$(".timeline-centered").append(createEventAdder);
	
	$('#save-event').click(function(e){
		addEvent();
	});
	
	$('#add-event-button').click(function(e){
		console.info("Hi manish");
		$("#event-modal").modal('show');
	});
	
	fetchSportList();
	fetchStoreList();
	createRangeCalender('#date-range');

	

});

var createRangeCalender =  function(id) {
	
	console.info("yes");
	
    function cb(start, end) {
        $(id+ ' span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
    }
    cb(moment().subtract(29, 'days'), moment().add(29,'days'));

    $(id).daterangepicker({
        ranges: {
           '<small>Last 1 month to next 1 mon.</small>':   [moment().subtract(1, 'months'), moment().add(1, 'months')],
           '<small>Last 3 month to next 1 mon.</small>': [moment().subtract(3, 'months'), moment().add(1, 'months')],
           '<small>Last 3 month to next 3 mon.</small>': [moment().subtract(3, 'months'), moment().add(3, 'months')],
           '<small>Last 6 month to next 1 mon.</small>': [moment().subtract(6, 'months'), moment().add(1, 'months')],
           '<small>Last 6 month to next 3 mon.</small>': [moment().subtract(6, 'months'), moment().add(3, 'months')]
        }
    }, cb);

}

var addEvent = function(){
	var formData = $('#template-form').serializeObject();
	delete formData._wysihtml5_mode;
	if (typeof(formData.sportId) === 'string') {
		console.info(formData.sportId);
		formData.sportId = [formData.sportId];
	}
	if (typeof(formData.storeId) === 'string') {
		console.info(formData.storeId);
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

var createDiv = function(eventType, date, title, eventInfo ){
	
	var article = '<article class="timeline-entry">'
		
		   + '<div class="timeline-entry-inner">'

		    + '<div class="timeline-icon '+eventType+'>'   
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

	var select = document.getElementById('sport-type-filter');
	$.ajax({
		url : 'fetchSportList',
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			sportData = data;
			console.info(data);
			for (var i in data) {
				var opt = document.createElement('option');
				opt.value = data[i].sportId;
				opt.innerHTML = data[i].name;
				select.appendChild(opt);				
			}
			
		},
	}).done(function() {
				
	});
}


var fetchStoreList = function(){

	var select = document.getElementById('store-type-filter');
	$.ajax({
		url : 'fetchStoreList',
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			sportData = data;
			console.info(data);
			for (var i in data) {
				var opt = document.createElement('option');
				opt.value = data[i].id;
				opt.innerHTML = data[i].name;
				select.appendChild(opt);				
			}
			
		},
	}).done(function() {
				
	});
}




