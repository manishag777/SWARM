var count= 10;
var id = 0;
var nid = 0;


$(document).ready(function () {
	window.setInterval(function(){
		fetchNotification();
	}, 2000);
	
	
});


var notificationBlock = function(data){
//	console.log(data.message);
	var variableArray = data.message.split("&");    //parameter would be form of name&brand&pid&id&size&color
	
	var li = document.createElement("li");
	
	//if(variableArray[4]=="#") 
	var idParam ="'"+  variableArray[3]+ "'" ;
	var nids = "'"+ data.id +"'" ;
	
	var block = '<a href="#" onclick = "openNotificationDialog('+idParam+","+ nids +')">'
    			+'<h4> S.No. '
    			+ variableArray[2]
    			+'<small><i class="fa fa-clock-o"></i>'+jQuery.timeago(data.timesStamp)+'</small>'
    			+'</h4>'
    			+'<p>' + variableArray[0] + '<br/>' +"#"+variableArray[1] +" #"+variableArray[4] +" #"+ variableArray[5] +'</p>'
    			+'</a>'
	li.innerHTML = block;
	return li;
}

var openNotificationDialog = function(idParam, nids){
	console.info("dialog");
	$("#notification-dialog").modal("show");
	id = idParam;
	$.ajax({
		url : 'getProductDetailById',
		type : 'GET',
		data : {id : idParam },
		contentType : "application/json",
		success : function(data) {
			console.log(data);
			$("#psno").val(data.pid);
			$("#pname").val(data.name);
			$("#pcolor").val(data.color);
			$("#psize").val(data.size);
			$("#wq").val(data.wqty);
			$("#pq").val(data.qty);
			nid = nids;
		},
		
		error : function(e) {
			//alert ("sorry! Due to some problem couldn't fetch the gift-card details");
		},
	}).done(function() {
		
	});
}



var fetchNotification = function(){
	//count--;
	//console.log(count);
	
	
	$.ajax({
		url : 'getNotifications',
		type : 'GET',
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

var updateNotificationPanel = function(data){
	
	//console.log(data);
	if(isRealValue(data)){
		var unseenCount = data.length;
		document.getElementById('out-of-stock-warning').innerHTML = unseenCount;
		document.getElementById('out-of-stock-header').innerHTML = unseenCount + ' more products are going to be out-of-stocks';
		var ul = document.getElementById("out-of-stock-list");
		$('#out-of-stock-list').empty()
		for (var i in data) {
			ul.appendChild(notificationBlock(data[i]));
		}
	}
	
}

var updateNotificationProduct = function(status){
	
	var wq = $("#wq").val();
	var pq = $("#pq").val();
	var ipq = $("#ipq").val()
	
	
	
	if(status==3 && pq+ipq<= wq){
		alert("Make sure total quantity is greater the warning quantity");
		return;
	}
	
	$.ajax({
		url : 'updateProductQty',
		type : 'GET',
		data : {id : id, wq : wq, ipq : ipq, nid:nid, status:status },
		contentType : "application/json",
		success : function(data) {
			console.log(data);
		},
		
		error : function(e) {
			//alert ("sorry! Due to some problem couldn't fetch the gift-card details");
		},
	}).done(function() {
		$("#notification-dialog").modal("hide");
	});	
}



