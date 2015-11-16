var editingButtonStatus = 0;
var oldData;
$(document).ready(function () {
	
	console.info("I'm here");
	 var initPage = function() {
		 console.info("I'm here");
		 
		 $('#gift-cancel').hide();
		 $('#gift-cancel').click(cancelGiftCardUpdation);
		 $('#saveoredit').click(updateGiftCardInfo);
		 getGiftCardSpecification();
	 }
	 initPage();
});


var cancelGiftCardUpdation = function() {
	
	
	console.info(oldData);
	$("#issueAmt").val(oldData.issueAmt);
	$("#amt1").val(oldData.amt1);
	$("#thresholdAmt").val(oldData.thresholdAmt);
	$("#amt2").val(oldData.amt2);
	$("#validity").val(oldData.validity);
	$("#saveoredit").html('Edit');
	$('#gift-cancel').hide();
	
	$("#issueAmt").prop("disabled",true);
	$("#amt1").prop("disabled",true);
	$("#thresholdAmt").prop("disabled",true);
	$("#amt2").prop("disabled",true);
	$("#validity").prop("disabled",true);
	editingButtonStatus = 0;
}

var updateGiftCardInfo = function(e)
{	
	
	
	if(editingButtonStatus==0){
		$('#gift-cancel').show();
		console.info("cancel");
		$("#issueAmt").prop("disabled",false);
		$("#amt1").prop("disabled",false);
		$("#thresholdAmt").prop("disabled",false);
		$("#amt2").prop("disabled",false);
		$("#validity").prop("disabled",false);
		oldData = $('#gift-card-form').serializeObject();
		$("#saveoredit").html('Save');
		editingButtonStatus  = 1;	
	}
	else if(editingButtonStatus == 1){
		
		console.info("cancel");
		var formData = $('#gift-card-form').serializeObject();
		console.info(formData);
		updateGiftCardSpecification(formData);
		editingButtonStatus  = 0;
	}
}

var updateGiftCardSpecification = function(formData){
	var url = 'updateGiftCard';
	$.ajax({
		url : url,
		data : JSON.stringify(formData),
		type : 'POST',
		contentType : "application/json",
		success : function(data) {
			console.log("Done updating gift-card detail");
			$('#gift-cancel').hide();
			$("#issueAmt").prop("disabled",true);
			$("#amt1").prop("disabled",true);
			$("#thresholdAmt").prop("disabled",true);
			$("#amt2").prop("disabled",true);
			$("#validity").prop("disabled",true);
			$("#saveoredit").html('Edit');
		},
		
		error : function(e) {
			alert ("sorry! Due to some problem couldn't update the gift-card details");
		},
	}).done(function() {
		console.log("Done adding gift-card details");
	});
	
}

var getGiftCardSpecification = function(){
	var url = 'getGiftCardSpecification';
	$.ajax({
		url : url,
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			console.log(data);
			$("#issueAmt").val(data.issueAmt);
			$("#amt1").val(data.amt1);
			$("#thresholdAmt").val(data.thresholdAmt);
			$("#amt2").val(data.amt2);
			$("#validity").val(data.validity);
		},
		
		error : function(e) {
			alert ("sorry! Due to some problem couldn't fetch the gift-card details");
		},
	}).done(function() {
		console.log("Done fetching gift-card details");
	});
	
}








