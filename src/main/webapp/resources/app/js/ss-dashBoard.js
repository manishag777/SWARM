var gd;
$(document).ready(function () {
	fetchNotification();
	
});

var fetchNotification = function(){
	//count--;
	//console.log(count);
	
	
	$.ajax({
		url : 'getSSNotifications',
		type : 'GET',
		data : {user:"mukesh"},
		contentType : "application/json",
		success : function(data) {
			updateNotificationPanel(data);
		},
		
		error : function(e) {
			//alert ("sorry! Due to some problem couldn't fetch the gift-card details");
		},
	}).done(function() {
		
	});
}

function updateNotificationPanel(data){
	console.info(data);
	gd = data;
	for(var i=0; i<data.length; i++){
		var unit = getNotifactionUnit(data[i]);
		$("#notificationList").append(unit);
	}
//	
}

function getNotifactionUnit(data){
	
	var html = '<li>'
	   + '<span class="handle">'
	    +  '<i class="fa fa-ellipsis-v"></i>'
	     + '<i class="fa fa-ellipsis-v"></i>'
	    +'</span>'
	    +'<span class="text">'+data.message+'</span>'
	    +'<span class="label label-danger pull-right"><i class="fa fa-clock-o"></i>'+jQuery.timeago(data.timesStamp)+'</span>'
	  +'</li>';
	
	return html;
}
