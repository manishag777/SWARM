var startDate, endDate;

$(document).ready(function(){
	console.info("dd");
	$('#future-analysis-modal').on('show.bs.modal', function(e){
		console.info("modal shown called");
		futureModalOpenedEvent();
		
		
	});
	
});

var futureModalOpenedEvent = function(){

	setTimeout(function(e){
		createFutureRangeCalender('#future-date-range');
		drawLineChart();
	},200);
}
		
var createFutureRangeCalender =  function(id) {
	
    function cb2(start, end) {
        $(id+ ' span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
        startDate = start.format('YYYY-MM-DD');
        endDate = end.format('YYYY-MM-DD');
        //
        if ( ! $.fn.DataTable.isDataTable('#future-product-table') ) {
        	getFutureEventData();
        }
        else{
        	console.info("reloading data table");
        	$('#future-product-table').dataTable().fnReloadAjax();
        }
        
        
       
    }
    cb2(moment().add(getDays(7), 'days'), moment().add(getDays(0), 'days'));

    $(id).daterangepicker({
        ranges: {
           '1 week Earlier':   [moment().add(getDays(7), 'days'), moment().add(getDays(0), 'days')],
           '15 days Earlier': [moment().add(getDays(15), 'days'), moment().add(getDays(0), 'days')],
           '1 month Earlier': [moment().add(getDays(30), 'days'), moment().add(getDays(0), 'days')],
        }
    }, cb2);
    
}

var getDays = function(days){
	var eventDate = getStandardDate("2015-12-20");
	var today = new Date();
	var diff = eventDate.getDate() - today.getDate();
	var reqDaysDiff = diff-days;
	console.info("diff = "+reqDaysDiff);
	return reqDaysDiff;

}

var drawLineChart = function(){

	 var lineChartCanvas = $("#lineChart").get(0).getContext("2d");
     var lineChart = new Chart(lineChartCanvas);
     var options  = lineChartOptions;
     options.datasetFill = false;
     lineChart.Line(lineChartData, options);
     //lineChart.Line(lineChartData2, options);
     console.info("hi manish2");
}

var lineChartData = {

        labels: ["January", "February", "March", "April", "May", "June", "July"],
        datasets: [
          {
            label: "Electronics",
            fillColor: "rgba(210, 214, 222, 1)",
            strokeColor: "rgba(210, 214, 222, 1)",
            pointColor: "rgba(210, 214, 222, 1)",
            pointStrokeColor: "#c1c7d1",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(220,220,220,1)",
            data: [65, 59, 80, 81, 56, 55, 40]
          },
          {
            label: "Digital Goods",
            fillColor: "rgba(60,141,188,0.9)",
            strokeColor: "rgba(60,141,188,0.8)",
            pointColor: "#3b8bba",
            pointStrokeColor: "rgba(60,141,188,1)",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(60,141,188,1)",
            data: [null,null,40, 19, 86, 27, 90]
          }
        ]
 };




var getFutureEventData = function(){

	var datatable = $('#future-product-table').DataTable({
		'serverSide' : false,
		'filter' : false,
		'ajax' : {
			url : 'getFutureEventData',
			type : 'POST',
			contentType : "application/json",
			data: function (d) {
				// send only data required by backend API
				delete(d.columns);
				delete(d.order);
				delete(d.search);
				d.eventId = currentEventId;
				d.startDate = startDate;
				d.endDate = endDate;
		      return JSON.stringify(d);
		    },
			
		    dataSrc : "productEntities",

		},
		
		columns: [
					{
						  "className" : 'details-control',
						   "orderable" : false,
						   "data" : null,
						   'defaultContent' : ''
						  
					},
					{ data: 'pid' },
		          	{ data: 'modelNo' },
		          	{ data: 'name' },
		          	{data : 'expectedSales'},
		          	{data : 'remainingSales'}
		      	],
				select: "single",
				sort: "true"
	});
 
 var detailRows = [];
 
    $('#future-product-table tbody').on( 'click', 'tr', function () {
    	console.info();
        var tr = $(this).closest('tr');
        var row = datatable.row( tr );
        var idx = $.inArray( tr.attr('id'), detailRows );
        console.info(row.data().pid);

    } );
 

	
}

function format (d) {
	
	return '<h> manish </h>' ;
	
	//var div =  "<h>hello world</h>";
    //return div;
}








