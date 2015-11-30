
var fetchDataOfTopProducts = function(){
	
	 var datatable = $('#top-product-table').DataTable({
			'serverSide' : false,
			'filter' : false,
			'ajax' : {
				url : 'fetchDataOfTopProducts',
				type : 'POST',
				'bFilter': false,
				'binfo' : false,
				 "sDom": '<"top">rt<"bottom"flp><"clear">',
				contentType : "application/json",
				data: function (d) {
					// send only data required by backend API
					delete(d.columns);
					delete(d.order);
					delete(d.search);
					d.eventId = 1;
					
			      return JSON.stringify(d);
			    },
				
			    dataSrc : "topProductsEntities",
	
			},
			
			columns: [
						{
							  "className" : 'details-control',
							   "orderable" : false,
							   "data" : null,
							   'defaultContent' : ''
							  
						},
						{ data: 'topCount' },
			          	{ data: 'sales' },
			          	{ data: 'percentage' },
			          	{data : 'cumPercentage'}
			      	],
					select: "single",
					sort: "true"
		});
	 
	 	var detailRows = [];
	 
	    $('#top-product-table tbody').on( 'click', 'tr td.details-control', function () {
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
	            	data: row.data().productEntities ,
	        		columns: [
	                  { data: 'pid' },
	                  { data: 'modelNo'},
	                  { data: 'name' },
	                  { data: 'amt' },
	                  { data: 'percentage' },
	                  { data: 'cumpercentage' }
	        		],
	                filter: false,
	                sort: false,
	                paging: false
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
//	var div = "<div class='p col-lg-2'>"
//			  +	"<img src =" + d.url + "> </img>" 
//			  +"</div>"
//			  +"<div class='col-lg-4'>"
//			  +	"<p><b>Brand:</b>" + d.brand  + "</p>" 
//			  + "<p>Only <b>" + d.qty  + "</b> in stock.</p>" 
//			  +  "<p" +d.info +"</p><br/>" 
//			  +"</div>" ;
	
	return '<table id="product-detail" class="table table-striped table-bordered">' +
	'<thead>' +
	'	<tr>' +
	'		<th>PID</th>' +
	'		<th>Model No.</th>' +
	'		<th>Name</th>' +
	'		<th>Amount</th>' +
	'		<th>%</th>' +
	'		<th>Cumulative(%)</th>' +
	'	</tr>' +
	'</thead>' +
	'</table>';
	
	//var div =  "<h>hello world</h>";
    //return div;
}



