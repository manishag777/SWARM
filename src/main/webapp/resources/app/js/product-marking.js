$(document).ready(function() {
	var initPage = function() {		
		
		 var datatable = $('#product-table').DataTable({
			'serverSide' : false,
			'ajax' : {
				url : 'returnProductData',
				type : 'POST',
				contentType : "application/json",
				data: function ( d ) {
					// send only data required by backend API
					delete(d.columns);
					delete(d.order);
					delete(d.search);
					d.groupType = $('#profit-group-filter').val();
					d.storeType = $('#store-type-filter').val();
					d.sportType = $('#sport-type-filter').val();
					d.sellingType = $('#selling-type-filter').val(); 
					console.info("json = " +  JSON.stringify(d));	
			      return JSON.stringify(d);
			    },
				
			    dataSrc : "profitEntities",
	//		    xhrFields: {
	//			      withCredentials: true
	//			   }
			},
			
			columns: [
			          {
			        	  "className" : 'details-control',
			        	   "orderable" : false,
			        	   "data" : null,
			        	   'defaultContent' : ''
			        	  
			          },
			          { data: 'id' },
			          { data: 'previousMarking' },
			          { data: 'previousDate' },
			          { data: 'currentMarking' },
			          {data: 'currentDate'  },
			          {data : null},
			          {data : null},
			          {data : 'markingFilter'}
			      	],
					select: "single",
					sort: "true"
		});
		 
		// Array to track the ids of the details displayed rows
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
		 

		 

		
		fetchProfitList();
		fetchSportList();
		fetchStoreList();
		$('#profit-group-filter').change(function() { $('#product-table').dataTable().fnReloadAjax(); });

		
	}
	initPage();
});




var myFunction = function(select, pid, color, size){
	//console.info("value = "+vari.value);
	var url = 'upateProfitMargin';
	var postObj = new Object();
	postObj.pid = pid;
	postObj.color = color; 
	postObj.profit_id = select.value;
	postObj.size = size;
	console.log(JSON.stringify(postObj));
	
	$.ajax({
		url : url,
		data : JSON.stringify(postObj),
		type : 'POST',
		contentType : "application/json",
		success : function(data) {
			console.log("Done updating profit-percent");
		},
		
		error : function(e) {
			alert ("sorry! Due to some problem couldn't update the gift-card details");
		},
	}).done(function() {
		console.log("Done adding gift-card details");
	});

};

var fetchProfitList = function(){
	var select = document.getElementById('profit-group-filter');
	
		$.ajax({
		url : 'fetchProfitList',
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
			for (var i in data) {
				var opt = document.createElement('option');
				opt.value = data[i].id;
				opt.innerHTML = data[i].name +"("+ data[i].margin+"%)";
				select.appendChild(opt);
			}
			
		},
	}).done(function() {
				
	});
}


var fetchProfitList = function(){
	var select = document.getElementById('profit-group-filter');
	
		$.ajax({
		url : 'fetchProfitList',
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
			for (var i in data) {
				var opt = document.createElement('option');
				opt.value = data[i].id;
				opt.innerHTML = data[i].name +"("+ data[i].margin+"%)";
				select.appendChild(opt);
			}
			
		},
	}).done(function() {
				
	});
}

var fetchSportList = function(){

	var select = document.getElementById('sport-type-filter');
	$.ajax({
		url : 'fetchSportList',
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			sportData = data;
			console.info(data);
			for (var i in data) {
				var opt = document.createElement('option');
				opt.value = data[i].sportId;
				opt.innerHTML = data[i].name;
				select.appendChild(opt);				
			}
			
		},
	}).done(function() {
				
	});
}


var fetchStoreList = function(){

	var select = document.getElementById('store-type-filter');
	$.ajax({
		url : 'fetchStoreList',
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			sportData = data;
			console.info(data);
			for (var i in data) {
				var opt = document.createElement('option');
				opt.value = data[i].id;
				opt.innerHTML = data[i].name;
				select.appendChild(opt);				
			}
			
		},
	}).done(function() {
				
	});
}





