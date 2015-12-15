var count = 0;
var idDataMap = new Object();

$(document).ready(function () {
	fetchEventNotification();
});

function fetchEventNotification(){



		
	$.ajax({
		url : 'fetchEventNotification',
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			fillEventTable(data);
		},
		error: function(){
			//console.info("error");
		}
	}).done(function() {
		//console.log("Done adding");
	});

}

function fillEventTable(data){

	$('#eventTable tbody').empty();
	for(var i=0; i<data.length; i++){
		idDataMap[data[i].id] = data[i];
		var table = document.getElementById("eventTable");
		var row = table.insertRow(-1);
		row.innerHTML = getRow(data[i]);
	}
	
	if(count==0){
		document.getElemetById("newEventCount").innerHTML = "";
	}
	else{
		document.getElemetById("newEventCount").innerHTML = count+" new";
	}
}

function getRow(data){


	idDataMap[data.id] = data;
	var id = "'"+data.id+"'";
	console.info(id);
	var html = '<td>'+data.eventName+'</td>';
	if(data.isNotificationSeen==0){
		html+='<td><span class="label label-danger">New</span></td>';
		count++;
	}
	else
		html+='<td><span class="label label-success">Seen</span></td>';
	if(data.notificationProgress == 0)
		html+='<td><Button onClick = "updateStocks('+id+')">Update Stocks</Button></td>';
	else
		html+='<td><p class = "label label-success">Purchase List Updated</p></td>';
	console.info(html);
	return html;
}

var eventId;

function updateStocks(id){
	console.info(idDataMap[id]);
	eventId = id;
	$("#update-stock-setting").modal("show");
	document.getElementById("update-stock-setting-title").innerHTML = "Prepare Stocks for the Event - "+ idDataMap[id].eventName;
	
	$.ajax({
		url : 'getRecommendedProductDetailDtoList',
		data : {eventId:id, sportType:idDataMap[id].sportType, participationCount: idDataMap[id].participantCount},
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
			updateStockTable(data, id);
		},
		
		error : function(e) {
			//alert ("sorry! Due to some problem couldn't update the gift-card details");
		},
	}).done(function() {
		//console.log("Done adding gift-card details");
	});
	
}

function updateStockTable(productEntities,id){
	
	var datatable = $("#product-table").DataTable({
    	data: productEntities,
    	columns: [
					{
						  "className" : 'details-control',
						   "orderable" : false,
						   "data" : null,
						   'defaultContent' : ''
						  
					},
					{ data: 'type' },
		          	{ data: 'estimatedQty' },
		      	],
        filter: false,
        sort: false,
        paging: false,
        bInfo : false,
        destroy: true
  });
	
	 var detailRows = [];
	 
	    $('#product-table tbody').on( 'click', 'tr td.details-control', function () {
	        var tr = $(this).closest('tr');
	        var row = datatable.row( tr );
	        var idx = $.inArray( tr.attr('id'), detailRows );
	 
	        if ( row.child.isShown() ) {
	            tr.removeClass( 'details' );
	            row.child.hide();
	 
	            // Remove from the 'open' array
	            detailRows.splice( idx, 1 );
	        }
	        else {
	            tr.addClass( 'details' );
	            row.child( format( row.data() ) ).show();
	            console.info(row.data().productEntities);
	            $('#product-detail').DataTable({
	            	data: row.data().productEntityList ,
	        		columns: [
	                  { data: 'modelNo' },
	                  { data: 'name' },
	                  { data: 'expectedSales'},
	                  { data: 'availableQty' },
	                  { data: null ,
	                	  mRender: function(data, type, full) {
	                		  var rem = '"'+ data.remainingSales+'"';
	                		  return '<input type="text" class="form-control pull-right" style = "width:85px;" value ='+rem+'>';
	                	  }   

	                  }
	                  
	        		],
	                filter: false,
	                sort: false,
	                paging: false,
	                destroy: true
	          });
	 
	            // Add to the 'open' array
	            if ( idx === -1 ) {
	                detailRows.push( tr.attr('id') );
	            }
	        }
	    } );
	 
	    // On each draw, loop over the `detailRows` array and show any child rows
	    datatable.on( 'draw', function () {
	        $.each( detailRows, function ( i, id ) {
	            $('#'+id+' td.details-control').trigger( 'click' );
	        } );
	    } );
}

function format (d) {
	console.info("Hey! At row exapnding");
	return '<table id="product-detail" class="table table-striped table-bordered">' +
	'<thead>' +
	'	<tr>' +
	'		<th style= "width: 20%">Model No.</th>' +
	'		<th style= "width: 40%">Name</th>' +
	'		<th style= "width: 13%">Expected Sale(Qty.)</th>' +
	'		<th style= "width: 13%">Available(Qty)</th>' +
	'		<th style= "width: 14%">Purchase(Qty)</th>' +
	'	</tr>' +
	'</thead>' +
	'</table>';
}

function updatePurchaseList(){
	$.ajax({
		url : 'updateManagerTimelineProgress',
		data : {eventId:eventId},
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			$("#update-stock-setting").modal("hide");
			swal("Purchase List updated","", "success");
			fetchEventNotification();
			
		},
		
		error : function(e) {
			//alert ("sorry! Due to some problem couldn't update the gift-card details");
		},
	}).done(function() {
		//console.log("Done adding gift-card details");
	});
}



