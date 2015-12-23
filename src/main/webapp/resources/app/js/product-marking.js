$(document).ready(function() {
	var initPage = function() {		
		
		 var datatable = $('#product-table').DataTable({
			'serverSide' : false,
			'ajax' : {
				url : 'returnProductData',
				type : 'POST',
				'bFilter': false,
				'binfo' : false,
				 "sDom": '<"top">rt<"bottom"flp><"clear">',
				contentType : "application/json",
				data: function ( d ) {
					// send only data required by backend API
					delete(d.columns);
					delete(d.order);
					delete(d.search);
					d.profitType = $('#profit-group-filter').val();
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
			          { data: 'preDate' },
			          { data: null,
			        	  mRender: function(data, type, full){
			        		  var effMargin = (data.preProfitPercent - (1+data.preProfitPercent/100)*data.preDiscountPercent );
			        		  var html = '<h><b style = "color:green">m:</b> <small>'+data.preProfitPercent +'('+data.preProfitType +')' +'</small><h><br/>'
			        		  + '<h><b style = "color:red">d:</b> <small>'+data.preDiscountPercent+'('+data.preDiscountType +')'+'</small><h><br/>'
			        		  + '<h><b style = "color:blue">eff. m:</b> <small>'+effMargin+ '</small><h><br/>' ;
			        		  return html;
			        	  }
			          },
			          { data: 'currDate' },
			          { data: null,
			        	  mRender: function(data, type, full){
			        		  var effMargin = (data.currProfitPercent - (1+data.currProfitPercent/100)*data.currDiscountPercent) ;
			        		  var html = '<h><b style = "color:green">m:</b> <small>'+data.currProfitPercent +'('+data.currProfitType +')' +'</small><h><br/>'
			        		  + '<h><b style = "color:red">d:</b> <small>'+data.currDiscountPercent+'('+data.currDiscountType +')'+'</small><h><br/>'
			        		  + '<h><b style = "color:blue">eff. m:</b> <small>'+effMargin+ '</small><h><br/>' ;
			        		  return html;

			        	  }
			          },
			          {data: null,
				           mRender: function(data, type, full) {
				        	  // var parameter = "'"+ data.profitStatus + "'";
				        	   if(data.profitStatus < 0)
				        		   return  '<h>'+ data.profitStatus +'&nbsp;</h><i class="fa  fa-arrow-down" style = "color:red; float:right"></i>' ;
				        	   else 
				        		   return  '<h>'+ data.profitStatus +'&nbsp;</h><i class="fa fa-arrow-up" style = "color:green; float:right"></i>' ;
				           } 	  
				      },
				      {data: null,
				           mRender: function(data, type, full) {
				        	 //  var parameter = "'"+ data.volumeStatus + "'";
				        	   if(data.volumeStatus < 0)
				        		   return  '<h>'+ data.volumeStatus +'</h><i class="fa  fa-arrow-down" style = "color:red; float:right"></i>' ;
				        	   else 
				        		   return  '<h>'+ data.volumeStatus +'</h><i class="fa fa-arrow-up" style = "color:green; float:right"></i>' ;
				           } 	  
				      },
			          {data : 'profitSelect'},
			          {data : 'discountSelect'}
			      	],
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
		fetchDiscountList();
		$('#store-type-filter').change(function() { $('#product-table').dataTable().fnReloadAjax(); });
		$('#sport-type-filter').change(function() { $('#product-table').dataTable().fnReloadAjax(); });
		$('#profit-group-filter').change(function() { $('#product-table').dataTable().fnReloadAjax(); });
		$('#selling-type-filter').change(function() { $('#product-table').dataTable().fnReloadAjax(); });
		
	}
	initPage();
});


function format (d) {
	var div = "<h>Information to be displayed later</h>";
    return div;
}





//var myFunction = function(select, pid, color, size){
//	//console.info("value = "+vari.value);
//	var url = 'upateProfitMargin';
//	var postObj = new Object();
//	postObj.pid = pid;
//	postObj.color = color; 
//	postObj.profit_id = select.value;
//	postObj.size = size;
//	console.log(JSON.stringify(postObj));
//	
//	$.ajax({
//		url : url,
//		data : JSON.stringify(postObj),
//		type : 'POST',
//		contentType : "application/json",
//		success : function(data) {
//			console.log("Done updating profit-percent");
//		},
//		
//		error : function(e) {
//			alert ("sorry! Due to some problem couldn't update the gift-card details");
//		},
//	}).done(function() {
//		console.log("Done adding gift-card details");
//	});
//
//};

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

var fetchDiscountList = function (){
	
	var select = document.getElementById('discount-group-filter');
	$.ajax({
		url : 'fetchDiscountList',
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

var myFunction = function(select, id)
{
	//console.info("value = "+vari.value);
	var url = 'upateProfitMarginById';
	
	$.ajax({
		url : url,
		data : {id: id, profitId : select.value},
		type : 'GET',
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



var profitFunction = function(select , id){
	console.info(select.value + " "+select.name);
	
	$.ajax({
		url :'upateProfitMarginById',
		data : {id: id, profitId : select.value},
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			console.log("Done updating profit-percent");
		},
		
		error : function(e) {
			alert ("Got error while updating margin");
		},
	}).done(function() {
		//console.log("Got error while updating margin");
	});

	
}

var discountFunction = function(select , id){
	console.info(select.value + " "+select.name);
	
	$.ajax({
		url : 'updateDiscountByID',
		data : {id: id, discountId : select.value},
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			console.log("Done updating profit-percent");
		},
		
		error : function(e) {
			alert ("Got error while updating discount");
		},
	}).done(function() {
		//console.log("Got error while updating discount");
	});
	
}











