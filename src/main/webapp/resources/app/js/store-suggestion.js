
$(document).ready(function () {
	
	$.ajax({
		url : 'fetchClusterCustomerDto',
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			//updateNotificationPanel(data);
		},
		
		error : function(e) {
			//alert ("sorry! Due to some problem couldn't fetch the gift-card details");
		},
	}).done(function() {
		
	});
	
});