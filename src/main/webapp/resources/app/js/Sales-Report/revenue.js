
$(document).ready(function () {
	fetchStoreList("store-type-filter-revenue");
	fetchSportList("sport-type-filter-revenue");
	$("#r1c1").click(function(){
		//console.info("r1c1");
		$("#total-revenue-analysis-modal").modal('show');
		setTimeout(function(e){
			getRevenueByDays();
			//drawLineChart("#totalrevenuelineChart");
		},200);
	});
	
	$("#r1c2").click(function(){
		//console.info("r1c1");
		$("#storewise-revenue-analysis-modal").modal('show');
		setTimeout(function(e){
			drawPieChart("#sportWiseChart");
		},200);
	});
	
	
	$("#r1c3").click(function(){
		//console.info("r1c1");
		$("#sportwise-revenue-analysis-modal").modal('show');
		setTimeout(function(e){
			drawPieChart("#storeWiseChart");
		},200);
	});
	

});


var drawPieChart = function(id){
	var pieChartCanvas = $(id).get(0).getContext("2d");
    var pieChart = new Chart(pieChartCanvas);
    var options  = pieOptions;
    options.datasetFill = false;
    pieChart.Pie(PieData, options);
}

var PieData = [
               {
                 value: 700,
                 color: "#f56954",
                 highlight: "#f56954",
                 label: "Chrome"
               },
               {
                 value: 500,
                 color: "#00a65a",
                 highlight: "#00a65a",
                 label: "IE"
               },
               {
                 value: 400,
                 color: "#f39c12",
                 highlight: "#f39c12",
                 label: "FireFox"
               },
               {
                 value: 600,
                 color: "#00c0ef",
                 highlight: "#00c0ef",
                 label: "Safari"
               },
               {
                 value: 300,
                 color: "#3c8dbc",
                 highlight: "#3c8dbc",
                 label: "Opera"
               },
               {
                 value: 100,
                 color: "#d2d6de",
                 highlight: "#d2d6de",
                 label: "Navigator"
               }
         ];

var getRevenueByDays = function(){
	
	var d = new Object();
	d.fromDate = "2015-11-15";
	d.toDate = "2015-12-03";
	
	$.ajax({
		url : 'getRevenueByDays',
		data : JSON.stringify(d),
		type : 'POST',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
			populateTotalRevenueTable(data.revenueEntities);
			console.info(data.revenueEntities);
			drawLineGraph2(data.revenueEntities);
			
		},
		
		error : function(e) {
			alert ("sorry! Due to some problem couldn't update the gift-card details");
		},
	}).done(function() {
		console.log("Done adding gift-card details");
	});
	
}	

var populateTotalRevenueTable = function(revenueEntities){
	
	$('#daybydays-revenue-table').DataTable({
    	data: revenueEntities,
    	columns: [
					{ data: 'date' },
		          	{ data: 'revenue' },
		          	{data : 'percentage'}
		      	],
        filter: false,
        sort: false,
        paging: true
  });
}

var drawLineGraph2 = function(re){
	var labels = [], data = [];
	
	for(var i=0; i<re.length; i++){
		labels.push(re[i].date);
		data.push(re[i].revenue);
		console.info(re[i].revenue);
	}
	console.info(labels);
	console.info(data);
	var lineChartData = getLineChartData(labels,data);
	var lineChartCanvas = $("#totalrevenuelineChart").get(0).getContext("2d");
	var lineChart = new Chart(lineChartCanvas);
	var options  = lineChartOptions;
	options.datasetFill = false;
	lineChart.Line(lineChartData, options);
	
}



