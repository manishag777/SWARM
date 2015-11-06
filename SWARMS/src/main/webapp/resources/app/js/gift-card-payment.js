var issue_amt = 0;
var amt1 = 0;
var threshold = 0;
var amt2 = 0;
var validity = 0;
var hasGiftcard = 0;


$(document).ready(function () {
	$("#payment").click(getGiftCardSpecification);
	
});

var getGiftCardSpecification = function(){
	console.log("At getGiftCardSpecification custId = " + custId );
	var url = 'getGiftCardSpecification';
	$.ajax({
		url : url,
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			console.log(data);
			issue_amt = data.issueAmt;
			amt1  = data.amt1;
			threshold = data.thresholdAmt;
			amt2 = data.amt2;
			validity = data.validity;
			customerGiftCardStatus();
		},
		
		error : function(e) {
			//alert ("sorry! Due to some problem couldn't fetch the gift-card details");
		},
	}).done(function() {
		console.log("Done fetching gift-card details");
	});
	
}

var customerGiftCardStatus = function(){
	
	var url = 'giftCardStatusByCustomerId';
	$.ajax({
		url : url,
		type : 'GET',
		data : {id:custId},
		contentType : "application/json",
		success : function(data) {
			console.info("data =" +data.issueAmt+"ma");
			if(typeof data.id === "undefined")
				hasGiftcard = 0;
			else{
				hasGiftcard = 1;
				console.info(data);
			}
		},
		
		error : function(e) {
			console.info("null data");
		},
	}).done(function() {
		console.log("Done fetching gift-card details");
	});

	
}