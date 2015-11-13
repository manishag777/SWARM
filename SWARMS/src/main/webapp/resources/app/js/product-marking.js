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
					d.storeFilter = $('#store-filter').val();
					console.info("json = " +  JSON.stringify(d));	
			      return JSON.stringify(d);
			    },
				
			    dataSrc : "employeeEntities",
	//		    xhrFields: {
	//			      withCredentials: true
	//			   }
			},
			
			columns: [
			          { data: 'pid' },
			          { data: 'name' },
			          { data: 'brand' },
			          { data: 'color' },
			          {data: 'size'  },
			          {data: 'margin'}
					],
					select: "single",
					sort: "true"
		});
		 

		
		fetchProfitList();
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





