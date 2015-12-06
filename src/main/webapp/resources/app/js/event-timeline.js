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
	//var store = $('#store-type-filter2').val();
	//console.info(store+" "+sport +" "+ startDate+ " "+endDate);
	var today =  new Date();
	//console.info(todayDate);
	
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
    +'</div><!-- /.box-body -->' ;
	
	return body;
}

var getTimelineUnitFooter = function(data, eventNumber, index){


	var task = [data.task1, data.task2, data.task3];
	var timeLineUnit = '' ;
	if(eventNumber==0){
		timeLineUnit = '<div class="row">'
			+ getCommonTaskBox("Generated Revenue", data.revenueGenerated)
			+ getCommonTaskBox("Cutomer Visited", data.customerVisited)
			+'</div>';
	}
	else{
		var revenueTaskBox ="";
		
		if(data.task1==0){
			var revenue = (data.expectedRevenue / 1000000).toFixed(2) + "M";
			revenueTaskBox = getCommonTaskBox("Expected Revenue", revenue);
		}
		else{
			revenueTaskBox = getProgressBarBox("Revenue", data.revenueGenerated, data.targetedRevenue);
		}
		
		var customerTaskBox = "";
		
		if(data.task2==0){
			customerTaskBox = getCommonTaskBox("Expected No. of Customer visit", data.expectedCustomerVisit);
		}
		else{
			customerTaskBox = getProgressBarBox("Customer Visit", data.customerVisited, data.targetedCustomer);
		}
		
			
		timeLineUnit = '<div class="row">'
			+ getPendingTaskBox(task,index)
			+ revenueTaskBox
			+ customerTaskBox
			+'</div>';
	}
	
	
	return timeLineUnit;

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
 function setCustomerTarget(index){
	 currentIndex  = index;
	console.info(eventData[index].expectedCustomerVisit);
	$("#expectedCustomerCount").val(eventData[index].expectedCustomerVisit);
	$("#targetCustomerCount").val(eventData[index].expectedCustomerVisit);
	$("#customer-visit-Modal").modal('show');
	
}
 
 function saveCustomerTarget(){
	 $.ajax({
			url : 'saveCustomerTarget',
			type : 'GET',
			data : {target: $("#targetCustomerCount").val(), eventId:eventData[currentIndex].id },
			contentType : "application/json",
			success : function(data) {
				console.log(data);
				swal("Customer target updated!", "", "success")
			},
			
			error : function(e) {
				alert ("sorry! Due to some problem couldn't fetch the gift-card details");
			},
		}).done(function() {
			console.log("Done fetching gift-card details");
			$("#customer-visit-Modal").modal("hide");
			fetchEventList();
		});
 }
 
 function saveProductTarget(){

	 console.info(currentIndex);
	 $.ajax({
			url : 'saveProductTarget',
			type : 'GET',
			data : {target: updateTotal(), eventId:eventData[currentIndex].id },
			contentType : "application/json",
			success : function(data) {
				console.log(data);
				swal("Product target updated!", "", "success")
				$("#product-target-setting").modal("hide");
			},
			
			error : function(e) {
				alert ("sorry! Due to some problem couldn't fetch the gift-card details");
			},
		}).done(function() {
			console.log("Done fetching gift-card details");
			$("#customer-visit-Modal").modal("hide");
			fetchEventList();
		});
 }

 function sendEmail(index){
	 	currentIndex = index;	
	 
	$("#subjectId").val("Products Available For Event- "+eventData[index].eventName);
	document.getElementById("relevantCustomerCount").innerHTML = (eventData[index].expectedCustomerVisit * 3.6).toFixed(0);
	console.info(eventData[index].id);
	$("#email-Modal").modal('show');
	var editor = '<label for="body">Body</label>'
		+ '<textarea class="textarea form-control" id="mailSubject" name="mailSubject"  placeholder="Place some text here" style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>'
	document.getElementById('SubjectArea-div').innerHTML = editor;
	$("#mailSubject").val("<p>"

		+	"<p>Dear Customer,</p><p>The Product is available in your nearest SportsWARM store. Hurry Up! The Stock is limited. Avail the discount on refering new Customers.</p><p>Thanks</p><p>Manish Agrawal</p><p>Sales Manager </p><p>SportsWarm </p><p></p>"

		+	"<br></p>");
	$("#mailSubject").wysihtml5();
}
 
function saveMailStatus(){
	console.info("At mail status");
	var val = $("#dropdown_customer").val();
	swal({   title: "Sending mail to "+val+"% relevant customers",   text: "",   type: "info",   showCancelButton: true,   closeOnConfirm: false,   showLoaderOnConfirm: true, }, 
			 function(){
					setTimeout(function(){
						swal("Successfully sent mail to the customers"); 
						$("#email-Modal").modal('hide');
						saveMailStatusInDatabase();
					}, val*100);  });
} 

function saveMailStatusInDatabase(){
	 $.ajax({
			url : 'saveMailTask',
			type : 'GET',
			data : {eventId:eventData[currentIndex].id},
			contentType : "application/json",
			success : function(data) {
				console.log(data);
			//	swal("Customer target created!", "", "success")
			},
			
			error : function(e) {
				//alert ("sorry! Due to some problem couldn't fetch the gift-card details");
				
			},
		}).done(function() {
			//console.log("Done fetching gift-card details");
			//$("#customer-visit-Modal").modal("hide");
			fetchEventList();
		});

}

var getTop10RelevantProducts = function(eventId, customerCount){
	
	$.ajax({
		url : 'getTop10RelevantProducts',
		type : 'GET',
		contentType : "application/json",
		data : {eventId:eventId, customerCount:customerCount},
		success : function(data) {
			console.info(data);
			updateProductDataTable(data);
			
		},
	}).done(function() {
		//$(".timeline-centered").append(createEventAdder());
				
	});
	
}

var updateProductDataTable = function(productData){
	
	for(var i in productData ){
		productData[i].target = productData[i].expectedSales;
		modelProductMap[productData[i].modelNo] = productData[i];
	}
	document.getElementById("expectedTarget").innerHTML = "Rs." + (updateTotal()/1000000).toFixed(3) + "M";
	
	$('#product-table').DataTable({
    	data: productData,
    	columns: [
					{ data: 'modelNo' },
		          	{ data: 'name' },
		          	{data : 'expectedSales'},
		          	{data : null ,
		          	  mRender : function(data, type, full){
		          		  var val = '"'+data.expectedSales+'"';
		          		  var modelNo = "'"+data.modelNo + "'";
		          		  var html =  '<input name="target" type="number" value = '+ val+' class="form-control" id="targetCustomerCount" onKeyUp = "evaluateTotalTarget(this,'+modelNo+')"/>' ;
		          		  return html;
		          	  }	
		          	}
		      	],
        filter: false,
        sort: false,
        paging: true
  });
	
}

var evaluateTotalTarget = function(input, modelNo ){
	console.info($(input).val() + " "+modelNo);
	modelProductMap[modelNo].target = $(input).val();
	updateTotal();
}

var updateTotal = function(){	
		var total = 0;
		$.each( modelProductMap, function(index,value){
			total += (value.price)*(value.target);
		});
		
		document.getElementById("setTarget").innerHTML = "Rs." + (total/1000000).toFixed(3) + "M";
		
		console.info(total);
		return total;
}

function setProuctTarget(index){
	 currentIndex = index;
	 console.info(eventData);
	 console.info(index);
	 console.info(eventData[index].id);
	 $("#product-target-setting").modal('show');
	 getTop10RelevantProducts(eventData[index].id,eventData[index].expectedCustomerVisit);
	
}

var getCommonTaskBox = function(header,value){
	
	if(value>10000)
		value = "Rs." + (value/100000).toFixed(3) + "M" ;
	var commonTaskBox = '<div class="col-md-4">'
		+'<div class="box" style="min-height:90px;">'
		+'<div class="box-header ">'      
		+'<span class="info-box-text">'+ header+'</span>'      
		+'<div>'        
		+ value
		+'</div>'        
		+'</div>'     
		+'</div>'   
		+'</div><!-- /.col -->' ;
	
	return commonTaskBox;
}

var getProgressBarBox = function(type, achieved, target){
	
	var per = ((achieved/target) * 100).toFixed(0);
	
	console.info(per);
	if(type=="Revenue"){
		achieved = (achieved/100000).toFixed(3);
		target = (target/100000).toFixed(3);
		var progressBarBox = '<div class="col-md-4">' 
		+'<div class="box">'
		+'<div class="box-header ">'      
		+'<div class="progress-group">'       
		+'<span class="progress-text">'+type+'</span>'          
		+'<span class="progress-number"><b>'+achieved+'M</b>/'+ target+'M</span>'          
		+'<div class="progress" style = "margin-top:7px">'          
		+' <div class="progress-bar progress-bar-aqua" style="width:'+ per +'%">'           
		+ per +'% Complete'             
		+'</div>'            
		+'</div>'          
		+' </div><!-- /.progress-group -->'       
		+'</div>'     
		+'</div>' 
		+'</div>' ;
		
		return progressBarBox;
	}
	else{
		
		var progressBarBox = '<div class="col-md-4">' 
			+'<div class="box">'
			+'<div class="box-header ">'      
			+'<div class="progress-group">'       
			+'<span class="progress-text">'+type+'</span>'          
			+'<span class="progress-number"><b>'+achieved+'</b>/'+ target+'</span>'          
			+'<div class="progress" style = "margin-top:7px">'          
			+' <div class="progress-bar progress-bar-aqua" style="width:'+ per +'%">'           
			+ per +'% Complete'             
			+'</div>'            
			+'</div>'          
			+' </div><!-- /.progress-group -->'       
			+'</div>'     
			+'</div>' 
			+'</div>';

		return progressBarBox;
	}
	
}




