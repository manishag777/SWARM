
var fillProductRecommendationDataTable = function(id){
	
	var newData = modifyRecommendProductData(id);
	    $('#productRecommendation'+id).DataTable( {
	        data: newData,
	        columns: [
	            { title: "Product Type" },
	            { title: "Expected sales(qty.)" },
	            { title: " Expected Revenue(Rs.)" },
	            { title: "Expected Profit(Rs.)" },
	        ],
	        filter: false,
	        sort: false,
	        paging: false,
	        bInfo : false,
	        destroy: true	
	    } );
	  
	    
}

function modifyRecommendProductData(id){
	var particpantCount = $('#participationCount'+id).val();
	console.info("particpantCount = "+particpantCount);
	newData = recommendedProductData;
	var totalProfit=0;
	var totalRevenue = 0;
	for(var i=0; i<newData.length; i++){
		newData[i][1] = particpantCount*newData[i][1];
		newData[i][2] = newData[i][1]*newData[i][2];
		newData[i][3] = newData[i][1]*newData[i][3];
		totalProfit += newData[i][3];
		totalRevenue += newData[i][2];
	}
	
	document.getElementById("expectedRevenue"+id).innerHTML = "Rs."+totalRevenue;
	document.getElementById("expectedProfit"+id).innerHTML = "Rs."+totalProfit;
	
	$('#setExpenditureBudgetButton'+id).click(function(e){
		expenditureBudgetIdMap[id] = $("#setExpenditureBudget"+id).val();
		swal("Budget updated successfully!","","success");
	});
	
	return newData;
}

function expenditureBudget(){
	
}


function getSchemeOfferList(id, offerType){
	
	console.info("offerType "+ offerType);
	

	$.ajax({
		url : 'getOfferSchemesList',
		data : {eventId:id, sportType: eventDataIdMap[id].sportType, offerType: offerType, particpationCount:$('#participationCount'+id).val()},
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			console.info("getOfferSchemesList");
			console.info(data);
			addSchemeCheckBoxes(data, id, offerType);
		},
		
		error : function(e) {
			//alert ("sorry! Due to some problem couldn't update the gift-card details");
		},
	}).done(function() {
		//console.log("Done adding gift-card details");
	});
}

function addSchemeCheckBoxes(data, id, offerType){
	var recommendedOfferId;
	var otherOfferId;
	if(offerType=="preEvent"){
		recommendedOfferId = "#recommendedOffers"+id;
		otherOfferId = "#otherOffers"+id;
	}
	else{
		recommendedOfferId = "#postRecommendedOffers"+id;
		otherOfferId = "#postOtherOffers"+id;
	}
	
	var recommendedSchemesList = data[0];
	$(recommendedOfferId).empty();
	for(var i=0; i<recommendedSchemesList.length; i++){
		$(recommendedOfferId).append(checkBoxUnit(recommendedSchemesList[i], 1, id, offerType));
	}
	
	var otherSchemesList = data[1];
	
	$(otherOfferId).empty();
	for(var i=0; i<otherSchemesList.length; i++){
		$(otherOfferId).append(checkBoxUnit(otherSchemesList[i], 0, id, offerType));
	}

}

function checkBoxUnit(data, isChecked, id, offerType){
	
	var value = data.id;
	var offerString = data.offer;
	var label = '"offer'+offerType+value+'_'+id+'"';
	console.info("isChecked" + isChecked);
	if(isChecked==0){
		var checkBoxHtml = '<div class="funkyradio-primary">'
	    +'<input type="radio" name="offers" id = '+label+' value = "rishab"/>'
	    +'<label for='+label+'><small>'+ offerString +'</small></label>'
	    +'</div>';
		
		return checkBoxHtml;
	}
	else{
		var checkBoxHtml = '<div class="funkyradio-primary">'
		    +'<input type="radio" name="offers" id = '+label+' value = "rishab" checked/>'
		    +'<label for='+label+'><small>'+ offerString +'</small></label>'
		    +'</div>';
			
			return checkBoxHtml;
	}
	
}

function updateEventAwarenessBox(id)
{
	var pc = $('#participationCount'+id).val();
	console.info("pc="+pc);
	var relCust = "relCust"+id;
	var existCust = "existCust"+id;
	var newCust = "newCust"+id;
	
	document.getElementById(relCust).innerHTML = (pc*4.2).toFixed(0);
	document.getElementById(existCust).innerHTML = (pc*0.185).toFixed(0);
	document.getElementById(newCust).innerHTML = (pc*0.087).toFixed(0);
	
	document.getElementById("expectedRevenue"+id).innerHTML = "Rs. " + ((pc*0.087 + pc*0.185)*980).toFixed(0);
	
}

function addPostMeetingListener(id){

	acitvateSalesTab(id);   //Listener 1
	
	var notifySalesManagerButtonId = "#notifySalesManager"+id;
	var addTheSelectedSchemeId = "#addTheSelectedScheme"+id;
	var addTheSelectedPostSchemeId = "#addTheSelectedPostScheme"+id;
	var addTheSelectedPostSchemeId = "#addTheSelectedPostScheme"+id;
	var notifyCustomerId = "#notifyCustomer"+id;
	$(notifySalesManagerButtonId).click(function(e){
		updateManagerNotification(id);
	});
	
	$(addTheSelectedSchemeId).click(function(e){
		updateTheScheme(id, "Pre");
	});
	
	$(addTheSelectedPostSchemeId).click(function(e){
		updateTheScheme(id, "Post");
	});
	
	$(notifyCustomerId).click(function(e){
		notifyCustomer(id);
	});
	
	
}

function updateManagerNotification(id){

	$.ajax({
		url : 'updateManagerNotification',
		data : {eventId:id},
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			//console.info("getOfferSchemesList");
			//console.info(data);
			//addSchemeCheckBoxes(data, id, offerType);
			swal("Notification sent to sales manager","" ,"success");
		},
		
		error : function(e) {
			//alert ("sorry! Due to some problem couldn't update the gift-card details");
		},
	}).done(function() {
		//console.log("Done adding gift-card details");
	});

}

function updateTheScheme(id, type){
	
	$.ajax({
		url : 'updateTheScheme',
		data : {eventId:id, type: type},
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			swal(type+"event scheme updated ","" ,"success");
		}, 
		
		error : function(e) {
			//alert ("sorry! Due to some problem couldn't update the gift-card details");
		},
	}).done(function() {
		//console.log("Done adding gift-card details");
	});
	
}

var eventId;

function notifyCustomer(id){
	
	console.info("At notify customer"+id);
	eventId = id;
	$("#email-Modal2").modal("show");
	console.info(eventDataIdMap);
	$("#subjectId2").val("Discount offers for the particpant of "+ eventDataIdMap[id].eventName);
//	document.getElementById("relevantCustomerCount").innerHTML = "";
//	//console.info(eventData[index].id);
//	$("#email-Modal").modal('show');
	var editor = '<label for="body">Body</label>'
		+ '<textarea class="textarea form-control" id="mailSubject" name="mailSubject"  placeholder="Place some text here" style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>'
	document.getElementById('SubjectArea-div2').innerHTML = editor;
	$("#mailSubject").val("<p>"

		+	"<p>Dear Customer,</p><p>The Product is available on discount offer in your nearest SportsWARM store. Hurry Up! The Stock is limited. Avail the discount on refering new Customers.</p><p>Thanks</p><p>Manish Agrawal</p><p>Sales Manager </p><p>SportsWarm </p><p></p>"

		+	"<br></p>");
	$("#mailSubject").wysihtml5();

	
}

function saveMailStatus(){

	console.info(eventId);
	var val = $("#dropdown_customer").val()+"%";
	if(val=="100%") val = "all"
	swal({   title: "Sending mail to "+val+" relevant customers",   text: "",   type: "info",   showCancelButton: true,   closeOnConfirm: false,   showLoaderOnConfirm: true, }, 
			 function(){
					setTimeout(function(){
						swal("Successfully sent mail to the customers"); 
						$("#email-Modal2").modal('hide');
						saveMailStatusInDatabase();
					}, 20*100)  });
}

