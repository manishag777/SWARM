var issue_amt = 0;
var amt1 = 0;
var threshold = 0;
var amt2 = 0;
var validity = 0;
var hasGiftCard = 0;  //No gift card
var remainingBalanace = 0;
var GiftCardDto = new Object();


$(document).ready(function () {
	$("#payment").click(getGiftCardSpecification);
	$("#pay-button").click(payTheAmount);
	
});

var getGiftCardSpecification = function(){
	
	if(custId==0 ||  custId==null || custId =='0' || custId =='')
	{
	//	alert("Insert valid customer");
		return;
	}
	
	//$('#payment-modal').modal('show');
	
	$("#sub-total").val(total);
	$("#gift-cardBalance").val(0);
	$("#gift-card-discount").val(0);
	$("#total-amt").val(total);
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
			console.info("data = " +data.id);
			if(!isRealValue(data.id)){
				hasGiftCard = 0;
				console.info("hasGiftCard = "+hasGiftCard);
			}
			else{
				hasGiftCard = 1;
				console.info(data);
				var date = data.expiry ;
				if(date )
				$("#gift-cardBalance").val(data.amt);
				var discount = Math.min(data.amt,total);
				$("#gift-cardBalance").val(data.amt);
				$("#gift-card-discount").val(discount);
				$("#total-amt").val(total-discount);
				GiftCardDto['id'] =  data.id;
				if(compareWithCurrentDate(new Date (data.expiry)))
				{
					$("#gift-cardBalance").val(data.amt);
					$("#gift-card-discount").val(discount);
					orderDto['gc_discount'] = discount;
					$("#total-amt").val(total-discount);
					remainingBalanace = data.amt - discount;

				}

			}
			
		},
		
		error : function(e) {
			console.info("null data");
		},
	}).done(function() {
		console.log("Done fetching gift-card details");
		processPayment();
	});

	
}

var processPayment = function(){
	var payment_header = document.getElementById("payment-header");
	payment_header.innerHTML = '';
	
	if(hasGiftCard==0){
		if(total < issue_amt){
			payment_header.innerHTML = "Gift-card with balance Rs." + amt1 +" will be issued on more purchase of Rs. "+ (issue_amt - total);
		}
		else{
			amt = amt1 + Math.floor(((total-issue_amt)/threshold))*amt2;
			extra  = threshold - total + issue_amt + Math.floor(((total-issue_amt)/threshold))*threshold;
			payment_header.innerHTML = "Customer is eligible for Gift-card with balance Rs." + amt +". <br\> Extra amount of Rs. " +amt2 +  " will be added on more purchase of Rs." + extra;
			GiftCardDto['amt'] = remainingBalanace + amt;
			GiftCardDto['id'] = -1;
			GiftCardDto['expiry'] = newExpiryDate();
		}
	}
	else if(hasGiftCard==1){
		if(total < amt1){
			payment_header.innerHTML = "Amount of Rs." + amt1 +" will be added on more purchase of Rs. "+ (amt1 - total);
		}
		else{
			amt = Math.floor(((total)/threshold))*amt2;
			extra  = threshold - total  + Math.floor(((total)/threshold))*threshold;
			payment_header.innerHTML = "Customer is eligible for extra Rs. " + amt +" in the gift-card. <br\> Extra amount of Rs. " +amt2+  " will be added on more purchase of Rs. " + extra;
			GiftCardDto['amt'] = remainingBalanace + amt;
			GiftCardDto['expiry'] = newExpiryDate();
			console.info("remainingBalanace = " +remainingBalanace + " extra = " + extra + "total = " + GiftCardDto['amt'] );
		} 
	}
	

}

var payTheAmount = function(){
	
	var object = new Object();
	orderDto['gcDiscount'] = 10;
	orderDto['id'] = -1 ;
	object['orderDto'] = orderDto;
	object['orderDetailDtoList'] = productDetailArray;
	object['giftCardDetailDto'] = GiftCardDto;
	console.info(object);
	
	var url = 'saveOrderDetail';
	//var url = 'saveOrderDto';

	$.ajax({
		url : url,
		data : JSON.stringify(object),
		type : 'POST',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
		//	displayCutomerInfo(1,data,id);
			
		},
		error: function(){
			console.info("error");
			//displayCutomerInfo(0,"",id);
		}
	}).done(function() {
		console.log("Done adding");
		orderDto = new Object();
		productDetailArray =  [];
		location.reload();
		
	});
	
	
}

var compareWithCurrentDate = function(gc_date){

	var date = new Date();
	var d   = date.getDate();
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	
	 date = new Date(y, m, d);
	
	if(gc_date < date){
		return false;
	}
	else{
		return true;
	}
	
}

var newExpiryDate =  function(){
	var date = new Date();
	date.setMonth(date.getMonth() + validity);
	var d   = date.getDate();
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	//console.info();
	return y+"-"+m+"-"+d;
}

