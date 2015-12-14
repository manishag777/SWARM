
var  fillProductRecommendationDataTable = function(id){
	
	var dataTableId = "#productRecommendation"+id;
	console.info("#productRecommendation12");
	data = {eventId:id, sportType:"marathon", participationCount: $('#participationCount'+id).val()};
	console.info(data);
	$.ajax({
		url : 'getRecommendedProductDtoList',
		data : {eventId:id, sportType:"marathon", participationCount: $('#participationCount'+id).val()},
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
			populateProductRecommendationTable(data, id);
		},
		
		error : function(e) {
			//alert ("sorry! Due to some problem couldn't update the gift-card details");
		},
	}).done(function() {
		//console.log("Done adding gift-card details");
	});
}

function  populateProductRecommendationTable(productEntities, id){

	
	var productRecommendationId = "#productRecommendation" + id ;
	
	console.info("populateProductRecommendationTable");
	//console.info(data);
	$(productRecommendationId).DataTable({
    	data: productEntities,
    	columns: [
					{ data: 'type' },
		          	{ data: 'estimatedQty' },
		      	],
        filter: false,
        sort: false,
        paging: false,
        bInfo : false,
        destroy: true
  });
}


function getSchemeOfferList(id, offerType){

	$.ajax({
		url : 'getOfferSchemesList',
		data : {eventId:id, sportType: "marathon", offerType: offerType, particpationCount:$('#participationCount'+id).val()},
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
		otherOfferId = "#recommendedOffers"+id;
	}
	else{
		recommendedOfferId = "#postRecommendedOffers"+id;
		otherOfferId = "#postRecommendedOffers"+id;
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
	    +'<input type="checkbox" name="offers" id = '+label+' value = "rishab"/>'
	    +'<label for='+label+'><small>'+ offerString +'</small></label>'
	    +'</div>';
		
		return checkBoxHtml;
	}
	else{
		var checkBoxHtml = '<div class="funkyradio-primary">'
		    +'<input type="checkbox" name="offers" id = '+label+' value = "rishab" checked/>'
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


